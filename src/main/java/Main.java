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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.*;
import repository.DbRepository.AssignmentDbRepository;
import repository.DbRepository.LabProblemsDbRepository;
import repository.DbRepository.StudentDbRepository;
import repository.FileRepository.AssignmentFileRepository;
import repository.FileRepository.LabProblemFileRepository;
import userInterface.UI;


public class Main {

    public static void main(String arg[])
    {
        System.out.println("hello");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "Config"
                );

        context.getBean(UI.class).run();

        System.out.println("bye");



        /*
        Validator<Student> studentValidator = new StudentValidator();
        SortingRepository<Long, Student> studentRepository = new StudentDbRepository(studentValidator);
        StudentController studentController = new StudentController(studentRepository);

        Validator<LabProblem> labProblemValidator= new LabProblemValidator();
        SortingRepository<Long,LabProblem> labProblemRepository=new LabProblemsDbRepository(labProblemValidator);
        LabProblemController labProblemController=new LabProblemController(labProblemRepository);

        Validator<Assignment> assignmentValidator=new AssignmentValidator();
        SortingRepository<Long,Assignment> assignmentRepository=new AssignmentDbRepository(assignmentValidator);
        AssignmentController assignmentController =new AssignmentController(assignmentRepository);
        UI ui = new UI(studentController,labProblemController,assignmentController);
        ui.run();
         */
    }
}
