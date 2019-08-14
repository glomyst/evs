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
            case 2:
                manageVoters();
                break;
            case 3:
                voting();
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
                System.out.println("Please enter the candidate number");
                String candno = userIn.next();
                System.out.println("Please enter the box number of candidate");
                String box = userIn.next();
                Candidate:addCandidate(name,idcardno,candno,box);
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

                System.out.println("Current candidate number: "+fileContent[selectedrecord-1][3]);
                System.out.print("New candidate number: ");
                fileContent[selectedrecord-1][3] = userIn.nextLine();

                System.out.println();
                showList(fileContent,"CANDIDATES");

                updateContent(fileContent, Integer.toString(selectedbox),"CANDIDATES");
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
                fileContent[selectedrecord-1][2] = "";
                fileContent[selectedrecord-1][3] = "";

                updateContent(fileContent, Integer.toString(selectedbox),"CANDIDATES");
                System.out.println("Candidate deleted!");
                System.out.println("Input any value and press enter to continue");
                userIn.next();
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

    public static void manageVoters() throws FileNotFoundException {
        showMenu("manage_voters");
        System.out.println("Please select an option from above");
        int submenuopt = userIn.nextInt();
        clearScreen();
        switch (submenuopt) {
            case 1:
                System.out.println("Please enter the voter name: ");
                userIn.nextLine();
                String name = userIn.nextLine();
                System.out.println("Please enter the ID card number of the voter: ");
                String idcardno = userIn.nextLine();
                System.out.println("Please enter the box number of voter");
                String box = userIn.next();
                Voter.addVoter(name,idcardno,box);
                break;
            case 2:
                int selectedrecord;
                Voter.viewVotersList();

                System.out.println("Please select the record id you want to update");
                selectedrecord =  userIn.nextInt();

                System.out.println("Current name: "+fileContent[selectedrecord-1][0]);
                System.out.print("New name: ");
                userIn.nextLine();
                fileContent[selectedrecord-1][0] = userIn.nextLine();

                System.out.println("Current ID card number: "+fileContent[selectedrecord-1][1]);
                System.out.print("New ID card number: ");
                fileContent[selectedrecord-1][1] = userIn.nextLine();

                System.out.println("Current Box number: "+fileContent[selectedrecord-1][1]);
                System.out.print("New box number: ");
                fileContent[selectedrecord-1][2] = userIn.nextLine();

                System.out.println();
                showList(fileContent,"VOTERS");

                updateContent(fileContent, "", "VOTERS");
                System.out.println("Voter details updated!");
                System.out.println("Input any value and press enter to continue");
                userIn.next();
                break;
            case 3:
                Voter.viewVotersList();

                System.out.println("Please select the record id you want to delete");
                selectedrecord =  userIn.nextInt();

                fileContent[selectedrecord-1][0] = "";
                fileContent[selectedrecord-1][1] = "";
                fileContent[selectedrecord-1][2] = "";
                fileContent[selectedrecord-1][3] = "";

                updateContent(fileContent, "","VOTERS");
                System.out.println("Voter deleted!");
                System.out.println("Input any value and press enter to continue");
                userIn.next();
                break;
            case 4:
                Voter.viewVotersList();
                System.out.println("Input any value and press enter to continue");
                userIn.next();
                break;
            case 9:
                mainMenu();
                break;
        }

        manageVoters();
    }

    public static void voting() throws FileNotFoundException {
        System.out.println("Please enter the box number to open for voting");
        String box = userIn.next();
        Voting.getVotersList(box);
        //System.out.println("Input any value and press enter to continue");
        //userIn.next();
        System.out.println("Please select a voter: ");
        String voter = userIn.next();

        Candidate.viewCandiatesList(Integer.parseInt(box));
        System.out.println("Select the candidate you want to vote for");
        String votingcand = userIn.next();
    }
}
