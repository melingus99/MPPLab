package userInterface;


import Controller.Controller;
import domain.Student;
import domain.validators.ValidatorException;

import java.util.Scanner;

public class UI {

    private Controller controller;
    public UI(Controller controller){
        this.controller=controller;
    }

    public static void printMenu()
    {
        System.out.println("press 1 to add student");
        System.out.println("press 2 to print all students");
        System.out.println("press 3 to update a student");
        System.out.println("press 4 to get an entity");
        System.out.println("press 5 to delete an entity");
        System.out.println("press 6 to save Student repository");
        System.out.println("press 0 to exit");
    }

    public void run()
    {
        Scanner scan=new Scanner(System.in);
        Scanner scan2=new Scanner(System.in);
        int input;
        while(true)
        {
            printMenu();
            input= scan.nextInt();
            switch (input) {
                case 1:{
                    System.out.println("type: Id,name,group");
                    String[] studentStr=scan2.nextLine().split(",");
                    try{
                        Long id=Long.valueOf(studentStr[0]);
                        Long group=Long.valueOf(studentStr[2]);
                        if(controller.GetStudentByEntityId(id).toString()!="Optional.empty")
                            System.out.println("student with id:"+studentStr[0]+" already exists");

                    }catch (NumberFormatException exc){
                        System.out.println("Id must be Long type");
                        continue;
                    }
                    try {
                        controller.addStudent(studentStr);
                    }
                    catch (ValidatorException exc){
                        System.out.println(exc);
                    }
                    //throw message if entity id already exists
                    break;
                }
                case 2:{
                    System.out.println(controller.PrintStudents());
                    break;
                }
                case 3:{
                    System.out.println("{entity id,new Student Name,new group}");
                    String[] studentStr=scan2.nextLine().split(",");
                    try{
                        Long id=Long.valueOf(studentStr[0]);
                        Long group=Long.valueOf(studentStr[2]);
                        if(controller.GetStudentByEntityId(id).toString()=="Optional.empty")
                            System.out.println("no student with id:"+studentStr[0]);

                    }catch (NumberFormatException exc){
                        System.out.println("Id must be Long type");
                        continue;
                    }
                    try {
                        controller.update(studentStr);
                    }
                    catch (ValidatorException exc){
                        System.out.println(exc.getMessage());
                    }
                    break;
                }
                case 4:{
                    System.out.println("type the entity id");
                    String idStr=scan2.nextLine();
                    try {
                        Long id=Long.valueOf(idStr);
                        if(controller.GetStudentByEntityId(id).toString()=="Optional.empty")
                            System.out.println("no student with id:"+idStr);
                        else
                            controller.deleteStudent(id);
                    }
                    catch (Exception exc){
                        System.out.println("Input must be a number");
                    }
                    break;
                }
                case 5:{
                    System.out.println("type the entity id");
                    String idStr=scan2.nextLine();
                    try {
                        Long id = Long.valueOf(idStr);
                        if(controller.GetStudentByEntityId(id).toString()!="Optional.empty")
                            controller.deleteStudent(id);
                        else
                            System.out.println("no student with id:"+idStr);
                    }
                    catch (Exception exe){
                        System.out.println("Input must be a number");
                    }
                    break;
                }
                case 6:{
                    controller.saveRepository();
                    break;
                }
                case 0:return;
                default:{
                    System.out.println(" '"+input+"' "+"not a valid option");
                    break;
                }
            }
           }
        }
    }

