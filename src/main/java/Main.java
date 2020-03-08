import Controller.Controller;
import repository.StudentRepository;
import userInterface.UI;

import java.util.Scanner;

public class Main {

    public static void main(String arg[])
    {
        StudentRepository studentRepository= new StudentRepository();
        Controller controller = new Controller(studentRepository);
        UI ui = new UI(controller);
        ui.run();
    }
}
