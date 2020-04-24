package Controller;


import domain.Assignment;
import domain.Student;
import domain.validators.AssignmentValidator;
import domain.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AssignmentRepositoryInterface;
import repository.FileRepository.AssignmentFileRepository;
import repository.LabProblemsRepositoryInterface;
import repository.Repository;
//import repository.Sort;
import repository.SortingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AssignmentController implements AssignmentControllerInterface {

    public static final Logger log = LoggerFactory.getLogger(AssignmentController.class);
    @Autowired
    private AssignmentRepositoryInterface repository;


    @Override
    public void add(String[] assignmentstr)throws ValidatorException{
        Assignment assignment=new Assignment(Long.valueOf(assignmentstr[1]),Long.valueOf(assignmentstr[2]));
        assignment.setId(Long.valueOf(assignmentstr[0]));


        repository.save(assignment);


    }
    @Override
    @Transactional
    public void update(String[] assignmentStr) throws ValidatorException{

        Assignment assignment=new Assignment(Long.valueOf(assignmentStr[1]),Long.valueOf(assignmentStr[2]));
        assignment.setId(Long.valueOf(assignmentStr[0]));
        assignment.setGrade(Float.valueOf(assignmentStr[3]));
        log.trace("updateAssignment - method entered: Assignment={}", assignment);
        repository.findById(assignment.getId())
                .ifPresent(a -> {
                    a.setStudentId(a.getStudentId());
                    a.setLabProblemId(a.getLabProblemId());
                    a.setGrade(a.getGrade());
                    log.debug("updateAssignment - updated: a={}", a);
                });
        log.trace("updateAssignment - method finished");

    }
    @Override
    public Optional<Assignment> GetByEntityId(Long id){
        return repository.findById(id);
    }

    @Override
    public Set<Long> filterByStudentId(Long studentId){
        ArrayList<Assignment> filteredEntities=(ArrayList<Assignment>) repository.findAll();
        Set<Long>filteredEntitiesId=filteredEntities.stream().filter(assignment -> assignment.getStudentId()==studentId)
                .map(assignment -> assignment.getId()).collect(Collectors.toSet());
        return filteredEntitiesId;
    }

    @Override
    public Set<Long> filterByLabProblemId(Long labProblemId){
        ArrayList<Assignment> filteredEntities=(ArrayList<Assignment>)repository.findAll();
        Set<Long>filteredEntitiesId=filteredEntities.stream().filter(assignment -> assignment.getLabProblemId()==labProblemId)
                .map(assignment -> assignment.getId()).collect(Collectors.toSet());
        return filteredEntitiesId;
    }

    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Override
    public List<Assignment> getAll(){
        return repository.findAll();
    }
}
