package userInterface;


import Controller.LabProblemController;
import Controller.StudentController;
import domain.LabProblem;
import domain.validators.ValidatorException;

import java.util.Scanner;

public class UI {

    private StudentController studentController;
    private LabProblemController labProblemController;
    public UI(StudentController studentController, LabProblemController labProblemController){
        this.studentController = studentController;
        this.labProblemController=labProblemController;
    }

    private void add(){
        Scanner scan=new Scanner(System.in);
        Scanner scan2=new Scanner(System.in);
        System.out.println("type: 1 to add a student \n 2 to add a lab problem");
        int input=scan.nextInt();
        switch (input){
            case 1:{
                System.out.println("type: Id,name,group");
                String[] studentStr=scan2.nextLine().split(",");
                try{
                    Long id=Long.valueOf(studentStr[0]);
                    Long group=Long.valueOf(studentStr[2]);
                    if(studentController.GetStudentByEntityId(id).toString()!="Optional.empty")
                        System.out.println("student with id:"+studentStr[0]+" already exists");

                }catch (NumberFormatException exc){
                    System.out.println("Id must be Long type");
                    return;
                }
                try {
                    studentController.addStudent(studentStr);
                }
                catch (ValidatorException exc){
                    System.out.println(exc);
                }
                break;
            }
            case 2:{
                System.out.println("type: Id,name,due time");
                String[] labProblemStr=scan2.nextLine().split(",");
                try{
                    Long id=Long.valueOf(labProblemStr[0]);
                    if(labProblemController.GetEntityById(id).toString()!="Optional.empty")
                        System.out.println("student with id:"+labProblemStr+" already exists");

                }catch (NumberFormatException exc){
                    System.out.println("Id must be Long type");
                    return;
                }
                try {
                    labProblemController.add(labProblemStr);
                }
                catch (ValidatorException exc){
                    System.out.println(exc);
                }
                break;
            }
            default:{
                System.out.println(" '"+input+"' "+"not a valid option");
                break;
            }
        }
    }

    private void update() {
        Scanner scan = new Scanner(System.in);
        Scanner scan2=new Scanner(System.in);
        System.out.println("type: 1 to update a student \n 2 to update a lab problem");
        int input = scan.nextInt();

        switch (input) {
            case 1: {
                System.out.println("{entity id,new Student Name,new group}");
                String[] studentStr = scan2.nextLine().split(",");
                try {
                    Long id = Long.valueOf(studentStr[0]);
                    Long group = Long.valueOf(studentStr[2]);
                    if (studentController.GetStudentByEntityId(id).toString() == "Optional.empty")
                        System.out.println("no student with id:" + studentStr[0]);

                } catch (NumberFormatException exc) {
                    System.out.println("Id must be Long type");
                    return;
                }
                try {
                    studentController.update(studentStr);
                } catch (ValidatorException exc) {
                    System.out.println(exc.getMessage());
                }
                break;
            }
            case 2:{
                System.out.println("{entity id,new Lab Problem Name,new due time}");
                String[] labProblemStr = scan2.nextLine().split(",");
                try {
                    Long id = Long.valueOf(labProblemStr[0]);
                    if (labProblemController.GetEntityById(id).toString() == "Optional.empty")
                        System.out.println("no student with id:" + labProblemStr[0]);

                } catch (NumberFormatException exc) {
                    System.out.println("Id must be Long type");
                    return;
                }
                try {
                    labProblemController.update(labProblemStr);
                } catch (ValidatorException exc) {
                    System.out.println(exc.getMessage());
                }
                break;
            }
            default:{
                System.out.println(" '"+input+"' "+"not a valid option");
                break;
            }
        }
    }

    private void getEntity(){
        Scanner scan=new Scanner(System.in);
        Scanner scan2=new Scanner(System.in);
        System.out.println("type: 1 to get a student \n 2 to get a lab problem");
        int input=scan.nextInt();
        switch (input){
            case 1:{

                System.out.println("type the entity id");
                String idStr=scan2.nextLine();
                try {
                    Long id=Long.valueOf(idStr);
                    if(studentController.GetStudentByEntityId(id).toString()=="Optional.empty")
                        System.out.println("no student with id:"+idStr);
                    else
                        System.out.println(studentController.GetStudentByEntityId(id));
                }
                catch (ValidatorException exc){
                    System.out.println("Input must be a number");
                }
                break;
            }
            case 2:{

                System.out.println("type the entity id");
                String idStr=scan2.nextLine();
                try {
                    Long id=Long.valueOf(idStr);
                    if(labProblemController.GetEntityById(id).toString()=="Optional.empty")
                        System.out.println("no Lab Problem with id:"+idStr);
                    else
                        System.out.println(labProblemController.GetEntityById(id));
                }
                catch (ValidatorException exc){
                    System.out.println("Input must be a number");
                }
                break;
            }
            default:{
                System.out.println(" '"+input+"' "+"not a valid option");
                break;
            }
        }
    }

    private void delete(){
        Scanner scan=new Scanner(System.in);
        Scanner scan2=new Scanner(System.in);
        System.out.println("type: 1 to delete a student \n 2 to delete a lab problem");
        int input=scan.nextInt();
        switch (input){
            case 1:{
                System.out.println("type the entity id");
                String idStr=scan2.nextLine();
                try {
                    Long id = Long.valueOf(idStr);
                    if(studentController.GetStudentByEntityId(id).toString()=="Optional.empty")
                        System.out.println("no student with id:"+idStr);
                    else
                        studentController.deleteStudent(id);
                }
                catch (Exception exe){
                    System.out.println("Input must be a number");
                }
                break;
            }
            case 2:{
                System.out.println("type the entity id");
                String idStr=scan2.nextLine();
                try {
                    Long id = Long.valueOf(idStr);
                    if(labProblemController.toString()=="Optional.empty")
                        System.out.println("no student with id:"+idStr);
                    else
                        labProblemController.delete(id);
                }
                catch (Exception exe){
                    System.out.println("Input must be a number");
                }
                break;
            }
        }
    }

    public static void printMenu()
    {
        System.out.println("press 1 to add an entity");
        System.out.println("press 2 to print all entities");
        System.out.println("press 3 to update an entity");
        System.out.println("press 4 to get an entity");
        System.out.println("press 5 to delete an entity");
        System.out.println("press 6 to save");
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
                    this.add();
                    break;
                }
                case 2:{
                    System.out.println("Students:"+studentController.PrintStudents()+"\nLab Problems:"+labProblemController.PrintAll());
                    break;
                }
                case 3:{
                    this.update();
                    break;
                }
                case 4:{
                    this.getEntity();
                    break;
                }
                case 5:{
                    this.delete();
                    break;
                }
                case 6:{
                    studentController.saveRepository();
                    labProblemController.saveRepository();
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

