package repository.DbRepository;


import domain.Student;
import domain.validators.StudentValidator;
import domain.validators.Validator;
import domain.validators.ValidatorException;
//import repository.Sort;
import repository.SortingRepository;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class StudentDbRepository implements SortingRepository<Long, Student> {

    private Map<Long, Student> entities;
    private Validator<Student> validator;

    private final String URL = "jdbc:postgresql://localhost:5432/MPPLab5";

    public StudentDbRepository(Validator<Student> validator) {
        this.validator = validator;
        entities = new HashMap<>();
        List<Student > students=(List<Student>) findAll();
        students.stream().forEach(student -> entities.put(student.getId(),student));
    }
/*
    @Override
    public Iterable<Student> findAll(Sort sort) {
        List<Student> allEntities = (List<Student>) this.findAll();
        Collections.sort(allEntities, sort);
        return allEntities;

    }

 */

    @Override
    public Optional<Student> findOne(Long id) {
        String sql = "select * from student where id=?";
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        try {
            Connection connection = DriverManager.getConnection(URL, "postgres", "mpplab2020");
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setInt(1, id.intValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long studentId = (long) resultSet.getInt("id");
                String name = resultSet.getString("name");
                Long groupNumber = (long) resultSet.getInt("groupnr");
                Student student = new Student(name, groupNumber);
                student.setId(id);
            }
        } finally {
            return Optional.ofNullable(entities.get(id));
        }
    }

    @Override
    public Iterable<Student> findAll() {
        List<Student> result = new ArrayList<>();
        String sql = "select * from student";
        try {
            Connection connection = DriverManager.getConnection(URL, "postgres", "mpplab2020");
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Long groupNumber = (long) resultSet.getInt("groupnr");
                Student student = new Student(name, groupNumber);
                student.setId(id);
                result.add(student);
            }
        } finally {
            return result;
        }

    }

    @Override
    public Optional<Student> add(Student entity) throws ValidatorException {
        String sql = "insert into student (id,name, groupnr) values(?,?,?)";
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        try {
            Connection connection = DriverManager.getConnection(URL, "postgres", "mpplab2020");
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId().intValue());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getGroup().intValue());
            preparedStatement.executeUpdate();
        } finally {
            return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
        }

    }

    @Override
    public Optional<Student> delete(Long id) {
        String sql = "delete from student where id=?";
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        try {
            Connection connection = DriverManager.getConnection(URL, "postgres", "mpplab2020");
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setInt(1, id.intValue());
            preparedStatement.executeUpdate();
        }
        finally {
            return Optional.ofNullable(entities.remove(id));
        }
    }

    @Override
    public Optional<Student> update(Student entity) throws ValidatorException {
        String sql = "update student set name=?, groupnr=? where id=?";
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        try {
            Connection connection = DriverManager.getConnection(URL, "postgres", "mpplab2020");
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setInt(3, entity.getId().intValue());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getGroup().intValue());

            preparedStatement.executeUpdate();
        }
        finally {
            return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
        }


    }

    @Override
    public String toString() {

        List<Student> entities = (List<Student>) this.findAll();
        String str = entities.stream().map(entity -> entity.toString()).reduce("", (s1, s2) -> s1 += "\n" + s2);
        return str;
    }

}