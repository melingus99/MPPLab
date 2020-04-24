package repository.DbRepository;


import domain.LabProblem;
import domain.validators.StudentValidator;
import domain.validators.Validator;
import domain.validators.ValidatorException;
//import repository.Sort;
import repository.SortingRepository;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class LabProblemsDbRepository implements SortingRepository<Long, LabProblem> {

    private Map<Long, LabProblem> entities;
    private Validator<LabProblem> validator;

    private final String URL = "jdbc:postgresql://localhost:5432/MPPLab5";

    public LabProblemsDbRepository(Validator<LabProblem> validator) {
        this.validator = validator;
        entities = new HashMap<>();
        List<LabProblem> students=(List<LabProblem>) findAll();
        students.stream().forEach(labProblem -> entities.put(labProblem.getId(),labProblem));
    }
/*
    @Override
    public Iterable<LabProblem> findAll(Sort sort) {
        List<LabProblem> allEntities = (List<LabProblem>) this.findAll();
        Collections.sort(allEntities, sort);
        return allEntities;

    }

 */

    @Override
    public Optional<LabProblem> findOne(Long id) {
        String sql = "select * from labproblem where id=?";
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
                Long labProblemId = (long) resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dueTime = resultSet.getString("duetime");
                LabProblem labProblem= new LabProblem(name,dueTime);
                labProblem.setId(labProblemId);
            }
        } finally {
            return Optional.ofNullable(entities.get(id));
        }
    }

    @Override
    public Iterable<LabProblem> findAll() {
        List<LabProblem> result = new ArrayList<>();
        String sql = "select * from labproblem";
        try {
            Connection connection = DriverManager.getConnection(URL, "postgres", "mpplab2020");
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = (long)resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dueTime = resultSet.getString("duetime");
                LabProblem labProblem=new LabProblem(name,dueTime);
                labProblem.setId(id);
                result.add(labProblem);
            }
        } finally {
            return result;
        }

    }

    @Override
    public Optional<LabProblem> add(LabProblem entity) throws ValidatorException {
        String sql = "insert into labproblem (id,name, duetime) values(?,?,?)";
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
            preparedStatement.setString(3, entity.getDueTime());
            preparedStatement.executeUpdate();
        } finally {
            return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
        }

    }

    @Override
    public Optional<LabProblem> delete(Long id) {
        String sql = "delete from labproblem where id=?";
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
    public Optional<LabProblem> update(LabProblem entity) throws ValidatorException {
        String sql = "update labproblem set name=?, duetime=? where id=?";
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
            preparedStatement.setString(2, entity.getDueTime());

            preparedStatement.executeUpdate();
        }
        finally {
            return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
        }


    }

    @Override
    public String toString() {

        List<LabProblem> entities = (List<LabProblem>) this.findAll();
        String str = entities.stream().map(entity -> entity.toString()).reduce("", (s1, s2) -> s1 += "\n" + s2);
        return str;
    }

    public void saveToFile(){}

}