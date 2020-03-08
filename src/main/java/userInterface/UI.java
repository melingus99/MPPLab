package userInterface;


import Controller.Controller;

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
                    System.out.println("input student name:");
                    String name=scan2.nextLine();
                    //controller.validate()
                    controller.addStudent(name);
                    break;
                }
                case 2:{
                    System.out.println(controller.PrintStudents());
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

