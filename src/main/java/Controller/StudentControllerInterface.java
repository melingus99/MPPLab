package Controller;

import domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentControllerInterface {

    void add(String[] studentstr);

    List<Student> getAll();

    void update(String[] studentStr);

    Optional<Student> GetByEntityId(Long id);

    void delete(Long id);



}
