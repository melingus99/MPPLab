package Controller;


import domain.Assignment;
import domain.Student;
import domain.validators.AssignmentValidator;
import domain.validators.ValidatorException;
import repository.FileRepository.AssignmentFileRepository;
import repository.Repository;
import repository.Sort;
import repository.SortingRepository;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class AssignmentController {

    private SortingRepository<Long, Assignment> repository;
    private AssignmentValidator assignmentValidator;

    public AssignmentController(SortingRepository<Long, Assignment> assignmentRepository){
        this.repository=assignmentRepository;
        this.assignmentValidator=new AssignmentValidator();
    }

    public void add(String[] assignmentstr)throws ValidatorException{
        Assignment assignment=new Assignment(Long.valueOf(assignmentstr[1]),Long.valueOf(assignmentstr[2]));
        assignment.setId(Long.valueOf(assignmentstr[0]));


        repository.add(assignment);


    }
    public String PrintAll(){
        Sort<Assignment> sort=new Sort("asc","grade","asc","sid");

        ArrayList<Assignment> assignments=(ArrayList<Assignment>)this.repository.findAll(sort);
        String str=assignments.stream().map(entity->entity.toString()).reduce("",(s1,s2)->s1+="\n"+s2);
        return str;
    }

    public void update(String[] assignmentStr) throws ValidatorException{

        Assignment assignment=new Assignment(Long.valueOf(assignmentStr[1]),Long.valueOf(assignmentStr[2]));
        assignment.setId(Long.valueOf(assignmentStr[0]));
        assignment.setGrade(Float.valueOf(assignmentStr[3]));
        repository.update(assignment);

    }

    public String GetEntityById(Long id){
        return repository.findOne(id).toString();
    }

    public Set<Long> filterByStudentId(Long studentId){
        ArrayList<Assignment> filteredEntities=(ArrayList<Assignment>) repository.findAll();
        Set<Long>filteredEntitiesId=filteredEntities.stream().filter(assignment -> assignment.getStudentId()==studentId)
                .map(assignment -> assignment.getId()).collect(Collectors.toSet());
        return filteredEntitiesId;
    }

    public Set<Long> filterByLabProblemId(Long labProblemId){
        ArrayList<Assignment> filteredEntities=(ArrayList<Assignment>)repository.findAll();
        Set<Long>filteredEntitiesId=filteredEntities.stream().filter(assignment -> assignment.getLabProblemId()==labProblemId)
                .map(assignment -> assignment.getId()).collect(Collectors.toSet());
        return filteredEntitiesId;
    }


    public void delete(Long id){
        repository.delete(id);
    }

    public void saveRepository(){
        ((AssignmentFileRepository) repository).saveToFile();
    }
}
