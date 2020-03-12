import Controller.LabProblemController;
import Controller.StudentController;
import domain.LabProblem;
import domain.Student;
import domain.validators.LabProblemValidator;
import domain.validators.StudentValidator;
import domain.validators.Validator;
import repository.LabProblemFileRepository;
import repository.Repository;
import repository.StudentFileRepository;
import userInterface.UI;

public class Main {

    public static void main(String arg[])
    {
        Validator<Student> studentValidator = new StudentValidator();
        Repository<Long, Student> studentRepository = new StudentFileRepository(studentValidator, "data\\students.txt");
        //Repository<Long, Student> studentRepository = new InMemoryRepository<>(studentValidator);
        StudentController studentController = new StudentController(studentRepository);
        Validator<LabProblem> labProblemValidator= new LabProblemValidator();
        Repository<Long,LabProblem> labProblemRepository=new LabProblemFileRepository(labProblemValidator,"data\\Lab Problems.txt");
        LabProblemController labProblemController=new LabProblemController(labProblemRepository);
        UI ui = new UI(studentController,labProblemController);
        ui.run();
    }
}
