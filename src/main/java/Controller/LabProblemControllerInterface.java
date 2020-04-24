package Controller;

import domain.LabProblem;
import domain.Student;

import java.util.List;
import java.util.Optional;

public interface LabProblemControllerInterface {

    void add(String[] labProblemstr);

    List<LabProblem> getAll();

    void update(String[] labProblemStr);

    Optional<LabProblem> GetByEntityId(Long id);

    void delete(Long id);



}
