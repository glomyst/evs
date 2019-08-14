package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.company.Candidate.addCandidate;

public class Main extends Base{
    public static Scanner userIn = new Scanner(System.in);
    public static int userMenuchoise;

    public static void main(String[] args) throws FileNotFoundException {
        mainMenu();
    }

    public static void mainMenu() throws FileNotFoundException {
        showMenu("main");
        System.out.println("Please select an option from above menu");
        userMenuchoise = userIn.nextInt();

        switch (userMenuchoise){
            case 1:
                manageCandidates();
                break;
        }
    }

    public static void manageCandidates() throws FileNotFoundException {
        int selectedbox, selectedrecord;
        showMenu("manage_candidates");
        System.out.println("Please select an option from above");
        int submenuopt = userIn.nextInt();
        clearScreen();
        switch (submenuopt){
            case 1:
                System.out.println("Please enter the candidate name: ");
                userIn.nextLine();
                String name = userIn.nextLine();
                System.out.println("Please enter the ID card number of the candidate: ");
                String idcardno = userIn.nextLine();
                System.out.println("Please enter the box number of candidate");
                String box = userIn.next();
                Candidate:addCandidate(name,idcardno,box);
                break;
            case 2:
                Candidate.viewCandiatesList(0);
                System.out.println("Enter the box number you want to make changes");
                selectedbox = userIn.nextInt();
                clearScreen();

                Candidate.viewCandiatesList(selectedbox);
                System.out.println("Please select the record id you want to update");
                selectedrecord =  userIn.nextInt();

                System.out.println("Current name: "+fileContent[selectedrecord-1][0]);
                System.out.print("New name: ");

                userIn.nextLine();
                fileContent[selectedrecord-1][0] = userIn.nextLine();
                System.out.println("Current ID card number: "+fileContent[selectedrecord-1][1]);
                System.out.print("New ID card number: ");

                fileContent[selectedrecord-1][1] = userIn.nextLine();

                System.out.println();
                showList(fileContent);

                updateContent(fileContent, Integer.toString(selectedbox));
                System.out.println("Candidate details updated!");
                System.out.println("Input any value and press enter to continue");
                userIn.next();
                break;
            case 3:
                Candidate.viewCandiatesList(0);
                System.out.println("Enter the box number you want to make changes");
                selectedbox = userIn.nextInt();
                clearScreen();

                Candidate.viewCandiatesList(selectedbox);
                System.out.println("Please select the record id you want to delete");
                selectedrecord =  userIn.nextInt();

                fileContent[selectedrecord-1][0] = "";
                fileContent[selectedrecord-1][1] = "";
                updateContent(fileContent, Integer.toString(selectedbox));
                break;
            case 4:
                Candidate.viewCandiatesList(0);
                System.out.println("Input any value and press enter to continue");
                userIn.next();
                break;
            case 9:
                mainMenu();
                break;
        }

        manageCandidates();
    }
}
