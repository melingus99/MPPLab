import Controller.Controller;
import domain.Student;
import domain.validators.StudentValidator;
import domain.validators.Validator;
import repository.InMemoryRepository;
import repository.Repository;
import repository.StudentFileRepository;
import userInterface.UI;

import java.util.Scanner;

public class Main {

    public static void main(String arg[])
    {
        Validator<Student> studentValidator = new StudentValidator();
        Repository<Long, Student> studentRepository = new StudentFileRepository(studentValidator, "C:\\Users\\crist\\MPPLab\\src\\main\\java\\data\\students");
        //Repository<Long, Student> studentRepository = new InMemoryRepository<>(studentValidator);
        Controller controller = new Controller(studentRepository);
        UI ui = new UI(controller);
        ui.run();
    }
}
