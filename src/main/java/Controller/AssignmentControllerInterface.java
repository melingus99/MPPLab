package Controller;

import domain.Assignment;
import domain.Student;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AssignmentControllerInterface {

    void add(String[] assignmentstr);

    List<Assignment> getAll();

    void update(String[] assignmentstr);

    Optional<Assignment> GetByEntityId(Long id);

    void delete(Long id);

    Set<Long> filterByStudentId(Long studentId);

    Set<Long> filterByLabProblemId(Long labProblemId);



}
