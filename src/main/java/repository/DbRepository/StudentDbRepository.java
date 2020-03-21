package repository.DbRepository;


import domain.Student;
import domain.validators.StudentValidator;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import repository.Sort;
import repository.SortingRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

public class StudentDbRepository implements SortingRepository<Long, Student> {

    private Map<Long, Student> entities;
    private Validator<Student> validator;

    private final String URL = "jdbc:postgresql://localhost:5432/MPPLab5";

    public StudentDbRepository(Validator<Student> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    @Override
    public Iterable<Student> findAll(Sort sort) {
        List<Student> allEntities = (List<Student>)this.findAll();
        Collections.sort(allEntities,sort);
        return allEntities;

    }

    @Override
    public Optional<Student> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Student> findAll() {
        List<Student> result = new ArrayList<>();
        String sql = "select * from student";
        try {
            Connection connection = DriverManager.getConnection(URL, "postgres","mpplab2020");
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Long groupNumber = Long.valueOf(resultSet.getInt("groupnr"));
                Student student =new Student(name, groupNumber);
                student.setId(id);
                result.add(student);
            }
        }catch (Exception exc){
            System.out.println(exc.getMessage());
        }
        return result;
    }

    @Override
    public Optional<Student> add(Student entity) throws ValidatorException {
        String sql = "insert into student (id,name, groupnr) values(?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(URL,"postgres","mpplab2020");
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setInt(1,entity.getId().intValue());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getGroup().intValue());
            preparedStatement.executeUpdate();
        }catch (Exception exc){
            System.out.println(exc.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Student> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Student> update(Student entity) throws ValidatorException {
        return Optional.empty();
    }
    @Override
    public String toString(){

        List<Student> entities=(List<Student>)this.findAll();
        String str=entities.stream().map(entity->entity.toString()).reduce("",(s1,s2)->s1+="\n"+s2);
        return str;
    }

    public String toString(Sort sort){

        List<Student> entities=(List<Student>)this.findAll(sort);
        String str=entities.stream().map(entity->entity.toString()).reduce("",(s1,s2)->s1+="\n"+s2);
        return str;
    }
}
