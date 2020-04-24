package userInterface;


import Controller.*;
import domain.Assignment;
import domain.LabProblem;
import domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;
@Component
public class UI {
    @Autowired
    private StudentControllerInterface studentController;
    @Autowired
    private LabProblemControllerInterface labProblemController;
    @Autowired
    private AssignmentControllerInterface assignmentController;
/*
    public UI(StudentController studentController, LabProblemController labProblemController,AssignmentController assignmentController){
        this.studentController = studentController;
        this.labProblemController=labProblemController;
        this.assignmentController=assignmentController;
    }*/



    private void add(){
        Scanner scan=new Scanner(System.in);
        Scanner scan2=new Scanner(System.in);
        System.out.println("type: 1 to add a student \n 2 to add a lab problem \n 3 to add an assignment");
        int input=scan.nextInt();
        switch (input){
            case 1:{
                System.out.println("type: Id,name,group");
                String[] studentStr=scan2.nextLine().split(",");
                try{
                    Long id=Long.valueOf(studentStr[0]);
                    Long group=Long.valueOf(studentStr[2]);
                    if(studentController.GetByEntityId(id).toString()!="Optional.empty")
                        System.out.println("student with id:"+studentStr[0]+" already exists");

                }catch (NumberFormatException exc){
                    System.out.println("Id must be Long type");
                    return;
                }
                try {
                    studentController.add(studentStr);
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
                    if(labProblemController.GetByEntityId(id).toString()!="Optional.empty")
                        System.out.println("lab problem with id:"+labProblemStr[0]+" already exists");

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
            case 3: {
                System.out.println("type: AssignmentId,StudentId,LabProblemId");
                String[] assignmentStr = scan2.nextLine().split(",");
                try {
                    Long id = Long.valueOf(assignmentStr[0]);
                    Long studentId = Long.valueOf(assignmentStr[1]);
                    Long labProblemId = Long.valueOf(assignmentStr[2]);
                    if (assignmentController.GetByEntityId(id).toString() != "Optional.empty")
                        System.out.println("Assignment with id:" + assignmentStr[0] + " already exists");

                    if (studentController.GetByEntityId(studentId).toString() == "Optional.empty")
                        System.out.println("no student with id:" + assignmentStr[1]);

                    if (labProblemController.GetByEntityId(labProblemId).toString() == "Optional.empty")
                        System.out.println("no lab problem with id:" + assignmentStr[2]);
                    else{
                        try {
                            assignmentController.add(assignmentStr);
                        } catch (ValidatorException exc) {
                            System.out.println(exc);
                        }
                    }

                } catch (NumberFormatException exc) {
                    System.out.println("Id must be Long type");
                    return;
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
        System.out.println("type: 1 to update a student \n 2 to update a lab problem \n 3 to update an assignment");
        int input = scan.nextInt();

        switch (input) {
            case 1: {
                System.out.println("{entity id,new Student Name,new group}");
                String[] studentStr = scan2.nextLine().split(",");
                try {
                    Long id = Long.valueOf(studentStr[0]);
                    Long group = Long.valueOf(studentStr[2]);
                    if (studentController.GetByEntityId(id).toString() == "Optional.empty")
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
                    if (labProblemController.GetByEntityId(id).toString() == "Optional.empty")
                        System.out.println("no lab problem with id:" + labProblemStr[0]);

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
            case 3: {
                System.out.println("type: AssignmentId,StudentId,LabProblemId,grade");
                String[] assignmentStr = scan2.nextLine().split(",");
                try {
                    Long id = Long.valueOf(assignmentStr[0]);
                    Long studentId = Long.valueOf(assignmentStr[1]);
                    Long labProblemId = Long.valueOf(assignmentStr[2]);
                    float grade=Float.valueOf(assignmentStr[3]);
                    if (assignmentController.GetByEntityId(id).toString() == "Optional.empty")
                        System.out.println("no assignment with id: " + assignmentStr[0]);

                    if (studentController.GetByEntityId(studentId).toString() == "Optional.empty")
                        System.out.println("no student with id:" + assignmentStr[1]);

                    if (labProblemController.GetByEntityId(labProblemId).toString() == "Optional.empty")
                        System.out.println("no lab problem with id:" + assignmentStr[2]);

                    else{
                        try {
                            assignmentController.update(assignmentStr);
                        } catch (ValidatorException exc) {
                            System.out.println(exc);
                        }
                    }
                } catch (NumberFormatException exc) {
                    System.out.println("Id must be Long type,grade must be float tyoe");
                    return;
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
        System.out.println("type: 1 to get a student \n 2 to get a lab problem \n 3 to get an assignment");
        int input=scan.nextInt();
        switch (input){
            case 1:{

                System.out.println("type the entity id");
                String idStr=scan2.nextLine();
                try {
                    Long id=Long.valueOf(idStr);
                    if(studentController.GetByEntityId(id).toString()=="Optional.empty")
                        System.out.println("no student with id:"+idStr);
                    else
                        System.out.println(studentController.GetByEntityId(id));
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
                    if(labProblemController.GetByEntityId(id).toString()=="Optional.empty")
                        System.out.println("no Lab Problem with id:"+idStr);
                    else
                        System.out.println(labProblemController.GetByEntityId(id));
                }
                catch (ValidatorException exc){
                    System.out.println("Input must be a number");
                }
                break;
            }
            case 3: {
                System.out.println("type: AssignmentId");
                String idStr = scan2.nextLine();
                try {
                    Long id = Long.valueOf(idStr);
                    if (assignmentController.GetByEntityId(id).toString() == "Optional.empty")
                        System.out.println("no assignment with id:" + idStr);
                    else
                        System.out.println(assignmentController.GetByEntityId(id).toString());

                } catch (NumberFormatException exc) {
                    System.out.println("Id must be Long type");
                    return;
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
        System.out.println("type: 1 to delete a student \n 2 to delete a lab problem \n 3 to delete an assignment" );
        int input=scan.nextInt();
        switch (input){
            case 1:{
                System.out.println("type the entity id");
                String idStr=scan2.nextLine();
                try {
                    Long id = Long.valueOf(idStr);
                    if(studentController.GetByEntityId(id).toString()=="Optional.empty")
                        System.out.println("no student with id:"+idStr);
                    else
                       //assignmentController.filterByStudentId(id).stream().forEach(assignmentId->assignmentController.delete(assignmentId));
                        studentController.delete(id);
                }
                catch (NumberFormatException exe){
                    System.out.println("Input must be a number");
                }
                break;
            }
            case 2: {
                System.out.println("type the entity id");
                String idStr = scan2.nextLine();
                try {
                    Long id = Long.valueOf(idStr);
                    if (labProblemController.toString() == "Optional.empty")
                        System.out.println("no lab problem with id:" + idStr);
                    else
                       //assignmentController.filterByLabProblemId(id).stream().forEach(assignmentId->assignmentController.delete(assignmentId));
                        labProblemController.delete(id);
                } catch (Exception exe) {
                    System.out.println("Input must be a number");
                }
                break;
            }
            case 3:{
                System.out.println("type: AssignmentId");
                String idStr = scan2.nextLine();
                try {
                    Long id = Long.valueOf(idStr);
                    if (assignmentController.GetByEntityId(id).toString() == "Optional.empty")
                        System.out.println("no assignment with id:" + idStr);
                    else
                        assignmentController.delete(id);
                } catch (NumberFormatException exc) {
                    System.out.println("Input must be a number");
                    return;
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
                    System.out.println("Students:"+studentController.getAll().toString()+"\nLab Problems:"
                                        +labProblemController.getAll().toString()+"\nAssignments:"+assignmentController.getAll().toString());
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
                case 0:return;
                default:{
                    System.out.println(" '"+input+"' "+"not a valid option");
                    break;
                }
            }
           }
        }
    }

