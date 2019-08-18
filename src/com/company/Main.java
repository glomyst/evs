package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.company.Candidate.addCandidate;

public class Main extends Base{
    static Scanner userIn = new Scanner(System.in);
    private static int userMenuchoise;

    public static void main(String[] args) throws FileNotFoundException {
        mainMenu();
    }

    static void mainMenu() throws FileNotFoundException {
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
                voting("");
                break;
            case 4:
                Candidate.showResults();
                break;
            case 5:
                viewstats();
                break;
        }
    }

    static void manageCandidates() throws FileNotFoundException {
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

                waitUser();
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
                waitUser();
                break;
            case 4:
                Candidate.viewCandiatesList(0);
                waitUser();
                break;
            case 9:
                mainMenu();
                break;
        }

        manageCandidates();
    }

    static void manageVoters() throws FileNotFoundException {
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
                waitUser();
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
                waitUser();
                break;
            case 4:
                Voter.viewVotersList();
                waitUser();
                break;
            case 9:
                mainMenu();
                break;
        }

        manageVoters();
    }

    public static void voting(String box) throws FileNotFoundException {
        clearScreen();
        if(box.equals("")){
            System.out.println("Please enter the box number to open for voting");
            box = userIn.next();
            Voting.openVoting(box);
        }
        clearScreen();

        System.out.println("===========================================================");
        System.out.println("             ELIGIBLE VOTERS LIST FOR BOX "+box+"                 ");
        System.out.println("===========================================================");
        Voting.getVotersList(box);
        showList(eligible_voters, "VOTERS");
        System.out.println("Please select a voter");
        System.out.println("(Type 'back' to go back to Main menu)");
        System.out.println("(Input 'close' to close voting)");
        userIn.nextLine();
        String voter = userIn.nextLine();

        if(voter.equals("back")){
            mainMenu();
        }else if(voter.equals("close")){
            Voting.closeVoting(box);
        }else{
            voter = eligible_voters[Integer.parseInt(voter)-1][1];
        }

        clearScreen();

        Candidate.viewCandiatesList(Integer.parseInt(box));
        System.out.println("Select the candidate you want to vote for");
        String votingcand = userIn.next();

        Voting.addvote(voter, votingcand, box);

        voting(box);
    }

    public static void viewstats() throws FileNotFoundException {
        clearScreen();
        Statistics stats = new Statistics();
        String line = "-------------------------------------------------";

        int[][] data = new int[3][4];
        data[0][0] = stats.numberofCandBoxOne;
        data[0][1] = stats.numberofAllVotersBoxOne;
        data[0][2] = stats.numberofVotedBoxOne;
        data[0][3] = stats.numberofUnVotedBoxOne;

        data[1][0] = stats.numberofCandBoxTwo;
        data[1][1] = stats.numberofAllVotersBoxTwo;
        data[1][2] = stats.numberofVotedBoxTwo;
        data[1][3] = stats.numberofUnVotedBoxTwo;

        data[2][0] = stats.numberofCandBoxThree;
        data[2][1] = stats.numberofAllVotersBoxThree;
        data[2][2] = stats.numberofVotedBoxThree;
        data[2][3] = stats.numberofUnVotedBoxThree;

        int count = 0;

        System.out.println("================================================");
        System.out.println("=                 EVS STATISTICS               =");
        System.out.println("================================================");
        while(count < 3){
            if(count != 0){
                System.out.println();
            }
            System.out.println("Box # "+(count+1));

            System.out.format("| %-40s| %-3s|", "--- Number of candidates: ", data[count][0]);
            System.out.println();
            System.out.format("| %-40s| %-3s|", "--- Eligible voters: ", data[count][1]);
            System.out.println();
            System.out.format("| %-40s| %-3s|", "--- People who voted:  ", data[count][2]);
            System.out.println();
            System.out.format("| %-40s| %-3s|", "--- People who didnt vote: ", data[count][3]);
            count++;
        }

        //System.out.format("| %-40s| %-3s|", "--- People who didnt vote: ", data[count][3]);
        System.out.println();
        System.out.println("Total: ");
        System.out.format("| %-40s| %-3s|", "--- Number of eligible voters: ", stats.allvoters);
        System.out.println();
        System.out.format("| %-40s| %-3s|", "--- Number of candidates: ", stats.totalcandidates);
        System.out.println();
        System.out.format("| %-40s| %-3s|", "--- Number of people who voted: ", stats.votedvoters);
        System.out.println();
        System.out.format("| %-40s| %-3s|", "--- Number of people who didnt vote: ", stats.unvotedvoters);
        System.out.println("\n \n");
        waitUser();
        mainMenu();
    }
}
