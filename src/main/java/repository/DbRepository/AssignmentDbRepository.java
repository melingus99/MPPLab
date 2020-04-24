package repository.DbRepository;


import domain.Assignment;
import domain.validators.StudentValidator;
import domain.validators.Validator;
import domain.validators.ValidatorException;
//import repository.Sort;
import repository.SortingRepository;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class AssignmentDbRepository implements SortingRepository<Long, Assignment> {

    private Map<Long, Assignment> entities;
    private Validator<Assignment> validator;

    private final String URL = "jdbc:postgresql://localhost:5432/MPPLab5";

    public AssignmentDbRepository(Validator<Assignment> validator) {
        this.validator = validator;
        entities = new HashMap<>();
        List<Assignment> students=(List<Assignment>) findAll();
        students.stream().forEach(student -> entities.put(student.getId(),student));
    }

    /*
    @Override
    public Iterable<Assignment> findAll(Sort sort) {
        List<Assignment> allEntities = (List<Assignment>) this.findAll();
        Collections.sort(allEntities, sort);
        return allEntities;

    }
*/
    @Override
    public Optional<Assignment> findOne(Long id) {
        String sql = "select * from assignment where id=?";
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
                Long assignmentId = (long) resultSet.getInt("id");
                Long studentId = (long) resultSet.getInt("sid");
                Long labPoblemId = (long) resultSet.getInt("lid");
                Float grade = resultSet.getFloat("grade");
                Assignment assignment=new Assignment(studentId,labPoblemId);
                assignment.setId(assignmentId);
                assignment.setGrade(grade);
            }
        } finally {
            return Optional.ofNullable(entities.get(id));
        }
    }

    @Override
    public Iterable<Assignment> findAll() {
        List<Assignment> result = new ArrayList<>();
        String sql = "select * from assignment";
        try {
            Connection connection = DriverManager.getConnection(URL, "postgres", "mpplab2020");
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = (long)resultSet.getInt("id");
                Long studentId = (long)resultSet.getInt("sid");
                Long labProblemId = (long)resultSet.getInt("lid");
                float grade = resultSet.getFloat("grade");
                Assignment assignment=new Assignment(studentId,labProblemId);
                assignment.setId(id);
                assignment.setGrade(grade);
                result.add(assignment);
            }
        } finally {
            return result;
        }

    }

    @Override
    public Optional<Assignment> add(Assignment entity) throws ValidatorException {
        String sql = "insert into assignment (id,sid,lid,grade) values(?,?,?,?)";
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        try {
            Connection connection = DriverManager.getConnection(URL, "postgres", "mpplab2020");
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId().intValue());
            preparedStatement.setInt(2, entity.getStudentId().intValue());
            preparedStatement.setInt(3, entity.getLabProblemId().intValue());
            preparedStatement.setFloat(4, entity.getGrade());

            preparedStatement.executeUpdate();
        } finally {
            return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
        }

    }

    @Override
    public Optional<Assignment> delete(Long id) {
        String sql = "delete from assignment where id=?";
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
    public Optional<Assignment> update(Assignment entity) throws ValidatorException {
        String sql = "update assignment set sid=?, lid=?,grade=? where id=?";
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        try {
            Connection connection = DriverManager.getConnection(URL, "postgres", "mpplab2020");
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setInt(4, entity.getId().intValue());
            preparedStatement.setInt(1, entity.getStudentId().intValue());
            preparedStatement.setInt(2, entity.getLabProblemId().intValue());
            preparedStatement.setFloat(3, entity.getGrade());

            preparedStatement.executeUpdate();
        }
        finally {
            return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
        }


    }

    @Override
    public String toString() {

        List<Assignment> entities = (List<Assignment>) this.findAll();
        String str = entities.stream().map(entity -> entity.toString()).reduce("", (s1, s2) -> s1 += "\n" + s2);
        return str;
    }

}