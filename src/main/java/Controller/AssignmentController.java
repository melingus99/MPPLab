package Controller;

import domain.Assignment;
import domain.Student;
import domain.validators.AssignmentValidator;
import domain.validators.StudentValidator;
import domain.validators.ValidatorException;
import repository.AssignmentFileRepository;
import repository.InMemoryRepository;
import repository.Repository;
import repository.StudentFileRepository;

public class AssignmentController {

    private Repository<Long, Assignment> repository;
    private AssignmentValidator assignmentValidator;

    public AssignmentController(Repository<Long, Assignment> assignmentRepository){
        this.repository=assignmentRepository;
        this.assignmentValidator=new AssignmentValidator();
    }

    public void add(String[] assignmentstr)throws ValidatorException{
        Assignment assignment=new Assignment(Long.valueOf(assignmentstr[1]),Long.valueOf(assignmentstr[2]));
        assignment.setId(Long.valueOf(assignmentstr[0]));


        repository.add(assignment);


    }
    public String PrintAll(){
        return repository.toString();
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

    public void delete(Long id){
        repository.delete(id);
    }

    public void saveRepository(){
        AssignmentFileRepository fileRepository=(AssignmentFileRepository) repository;
        fileRepository.saveToFile();
    }
}
