package Ui;


import common.CommunicationService;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class Console {
    private CommunicationService communicationService;
    private String command;
    private String parameters;
    private Future<String> response;
    private int defaultFlag;
    public Console(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }


    public void runConsole() {
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        String input;
        while (true) {
            printMenu();
            command = "";
            parameters = "";
            defaultFlag=0;
            input = scan.nextLine();
            command+=input;
            switch (input) {
                case "1": {
                    this.add();
                    break;
                }
                case "2": {
                    response = communicationService.sendMessage(command, parameters);
                    break;
                }
                case "3": {
                    this.update();
                    break;
                }
                case "4": {
                    this.getEntity();
                    break;
                }
                case "5": {
                    this.delete();
                    break;
                }
                case "6": {
                    response = communicationService.sendMessage(command, parameters);
                    break;
                }
                case "0":
                    return;
                default: {
                    defaultFlag=1;
                    System.out.println(" '" + input + "' " + "not a valid option");
                    break;
                }
            }
            try {
                if(defaultFlag==1) {
                    String result = response.get(); //blocking :(
                    //todo: client side operations should be non-blocking

                    System.out.println(result);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
        private void add () {
            Scanner scan = new Scanner(System.in);
            Scanner scan2 = new Scanner(System.in);
            System.out.println("type: 1 to add a student \n 2 to add a lab problem \n 3 to add an assignment");
            String input = scan.nextLine();
            command+=" "+input;
            switch (input) {
                case "1": {
                    System.out.println("type: Id,name,group");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                case "2": {
                    System.out.println("type: Id,name,due time");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                case "3": {
                    System.out.println("type: AssignmentId,StudentId,LabProblemId");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                default: {
                    defaultFlag=1;
                    System.out.println(" '" + input + "' " + "not a valid option");
                    break;
                }
            }
        }

        private void update () {
            Scanner scan = new Scanner(System.in);
            Scanner scan2 = new Scanner(System.in);
            System.out.println("type: 1 to update a student \n 2 to update a lab problem \n 3 to update an assignment");
            String input = scan.nextLine();
            command+=" "+input;
            switch (input) {
                case "1": {
                    System.out.println("{entity id,new Student Name,new group}");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                case "2": {
                    System.out.println("{entity id,new Lab Problem Name,new due time}");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                case "3": {
                    System.out.println("type: AssignmentId,StudentId,LabProblemId,grade");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                default: {
                    defaultFlag=1;
                    System.out.println(" '" + input + "' " + "not a valid option");
                    break;
                }
            }
        }

        private void getEntity () {
            Scanner scan = new Scanner(System.in);
            Scanner scan2 = new Scanner(System.in);
            System.out.println("type: 1 to get a student \n 2 to get a lab problem \n 3 to get an assignment");
            String input = scan.nextLine();
            command+=" "+input;
            switch (input) {
                case "1": {

                    System.out.println("type the entity id");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                case "2": {
                    System.out.println("type the entity id");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                case "3": {
                    System.out.println("type: AssignmentId");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                default: {
                    defaultFlag=1;
                    System.out.println(" '" + input + "' " + "not a valid option");
                    break;
                }
            }
        }

        private void delete () {
            Scanner scan = new Scanner(System.in);
            Scanner scan2 = new Scanner(System.in);
            System.out.println("type: 1 to delete a student \n 2 to delete a lab problem \n 3 to delete an assignment");
            String input = scan.nextLine();
            command+=" "+input;
            switch (input) {
                case "1": {
                    System.out.println("type the entity id");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                case "2": {
                    System.out.println("type the entity id");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                case "3": {
                    System.out.println("type: AssignmentId");
                    parameters = scan2.nextLine();
                    response= communicationService.sendMessage(command,parameters);
                    break;
                }
                default: {
                    defaultFlag=1;
                    System.out.println(" '" + input + "' " + "not a valid option");
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


}
