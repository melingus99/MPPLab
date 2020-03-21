import Controller.AssignmentController;
import Controller.LabProblemController;
import Controller.StudentController;
import domain.Assignment;
import domain.LabProblem;
import domain.Student;
import domain.validators.AssignmentValidator;
import domain.validators.LabProblemValidator;
import domain.validators.StudentValidator;
import domain.validators.Validator;
import repository.*;
import repository.DbRepository.StudentDbRepository;
import userInterface.UI;


public class Main {

    public static void main(String arg[])
    {

        Validator<Student> studentValidator = new StudentValidator();
        SortingRepository<Long, Student> studentRepository = new StudentDbRepository(studentValidator);
        StudentController studentController = new StudentController(studentRepository);

        Validator<LabProblem> labProblemValidator= new LabProblemValidator();
        Repository<Long,LabProblem> labProblemRepository=new LabProblemFileRepository(labProblemValidator,"data\\Lab Problems.txt");
        LabProblemController labProblemController=new LabProblemController(labProblemRepository);

        Validator<Assignment> assignmentValidator=new AssignmentValidator();
        Repository<Long,Assignment> assignmentRepository=new AssignmentFileRepository(assignmentValidator,"data\\Assignments.txt");
        AssignmentController assignmentController =new AssignmentController(assignmentRepository);
        UI ui = new UI(studentController,labProblemController,assignmentController);
        ui.run();
    }
}
