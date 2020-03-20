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
import repository.AssignmentFileRepository;
import repository.LabProblemFileRepository;
import repository.Repository;
import repository.StudentFileRepository;
import userInterface.UI;

public class Main {

    public static void main(String arg[])
    {

        Validator<Student> studentValidator = new StudentValidator();
        Repository<Long, Student> studentRepository = new StudentFileRepository(studentValidator, "data\\students.txt");
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
