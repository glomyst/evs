package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.company.Candidate.addCandidate;



















//Define main class
public class Main extends Base{
    // make a variable to hold all user inputs though out the program lifetime
    static Scanner userIn = new Scanner(System.in);
    //variable to store the menu choice user selects to navigates to
    private static int userMenuchoise;

    //java main function
    public static void main(String[] args) throws FileNotFoundException {
        //shows main menu as soon as program runs
        mainMenu();
    }

    //main menu function
    static void mainMenu() throws FileNotFoundException {
        //call showMenu function of Base class
        showMenu("main");
        //asks user to select an option form main menu and assigns it to a variable
        System.out.println("Please select an option from above menu");
        userMenuchoise = userIn.nextInt();

        //depending on the option calls the required function
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

    //manage candidates function
    static void manageCandidates() throws FileNotFoundException {
        //variables to store which box and which record of that box users chooses
        int selectedbox, selectedrecord;
        //shows manage candidates menu
        showMenu("manage_candidates");
        //asks the user to select an option from above menu and stores it in submenuopt variable
        System.out.println("Please select an option from above");
        int submenuopt = userIn.nextInt();
        //clears the screen to keep the UI clean
        clearScreen();

        //depending on the submenu option user chose, take the user to required process
        switch (submenuopt){
            //Add candidate
            case 1:
                //authenticates and gets all the required info and calls add candidate method of Candidate class
                authenticate("candidates");
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
            //Update candidate details
            case 2:
                //authenticates and show the candidates list and let user select a box
                authenticate("candidates");
                Candidate.viewCandiatesList(0);
                System.out.println("Enter the box number you want to make changes");
                selectedbox = userIn.nextInt();
                clearScreen();

                //user that box to load candidates list and let user enter a record id  to update
                Candidate.viewCandiatesList(selectedbox);
                System.out.println("Please select the record id you want to update");
                selectedrecord =  userIn.nextInt();

                //get all the required info to update and call updateContent class
                //update name
                System.out.println("Current name: "+fileContent[selectedrecord-1][0]);
                System.out.print("New name: ");
                userIn.nextLine();
                fileContent[selectedrecord-1][0] = userIn.nextLine();

                //update ID card
                System.out.println("Current ID card number: "+fileContent[selectedrecord-1][1]);
                System.out.print("New ID card number: ");
                fileContent[selectedrecord-1][1] = userIn.nextLine();

                //update candidate number
                System.out.println("Current candidate number: "+fileContent[selectedrecord-1][3]);
                System.out.print("New candidate number: ");
                fileContent[selectedrecord-1][3] = userIn.nextLine();

                //show updated list before updating
                System.out.println();
                showList(fileContent,"CANDIDATES");

                //update
                updateContent(fileContent, Integer.toString(selectedbox),"CANDIDATES");
                System.out.println("Candidate details updated!");

                //waits user to show a message
                waitUser();
                break;
            //Delete a candidate
            case 3:
                //authenticates and show the list of candidates and let user choose a box.
                authenticate("candidates");
                Candidate.viewCandiatesList(0);
                System.out.println("Enter the box number you want to make changes");
                selectedbox = userIn.nextInt();
                clearScreen();

                //based on the box selected show the candidates list and let user choose the record id to delete
                Candidate.viewCandiatesList(selectedbox);
                System.out.println("Please select the record id you want to delete");
                selectedrecord =  userIn.nextInt();

                //empty name,idcardno,candidate number and number of votes from the deleting record
                fileContent[selectedrecord-1][0] = "";
                fileContent[selectedrecord-1][1] = "";
                fileContent[selectedrecord-1][2] = "";
                fileContent[selectedrecord-1][3] = "";

                //update with the changes
                updateContent(fileContent, Integer.toString(selectedbox),"CANDIDATES");
                System.out.println("Candidate deleted!");
                waitUser();
                break;
            //show candidates list
            case 4:
                Candidate.viewCandiatesList(0);
                waitUser();
                break;
            //go back to main menu
            case 9:
                mainMenu();
                break;
        }

        manageCandidates();
    }

    //manage voters function
    static void manageVoters() throws FileNotFoundException {
        //show voters menu and let user choose and option
        showMenu("manage_voters");
        System.out.println("Please select an option from above");
        int submenuopt = userIn.nextInt();
        clearScreen();

        //depending on the submenu option user chose, take the user to required process
        switch (submenuopt) {
            //Add a new voter
            case 1:
                //authenticate and get all the data required for the new candidate and call addVoter method to add
                authenticate("voters");
                System.out.println("Please enter the voter name: ");
                userIn.nextLine();
                String name = userIn.nextLine();
                System.out.println("Please enter the ID card number of the voter: ");
                String idcardno = userIn.nextLine();
                System.out.println("Please enter the box number of voter");
                String box = userIn.next();
                Voter.addVoter(name,idcardno,box);
                break;
            //Update voter details
            case 2:
                //authenticate and show voters list and let user choose a record to edit
                authenticate("voters");
                int selectedrecord;
                Voter.viewVotersList();

                System.out.println("Please select the record id you want to update");
                selectedrecord =  userIn.nextInt();

                //get the data to update from chosen record
                System.out.println("Current name: "+fileContent[selectedrecord-1][0]);
                System.out.print("New name: ");
                userIn.nextLine();
                fileContent[selectedrecord-1][0] = userIn.nextLine();

                System.out.println("Current ID card number: "+fileContent[selectedrecord-1][1]);
                System.out.print("New ID card number: ");
                fileContent[selectedrecord-1][1] = userIn.nextLine();

                System.out.println("Current Box number: "+fileContent[selectedrecord-1][2]);
                System.out.print("New box number: ");
                fileContent[selectedrecord-1][2] = userIn.nextLine();

                //show updating data before update
                System.out.println();
                showList(fileContent,"VOTERS");

                //update data
                updateContent(fileContent, "", "VOTERS");
                System.out.println("Voter details updated!");
                waitUser();
                break;
            //delete a voter
            case 3:
                //authenticate and show voters list and let user choose a record to delete
                authenticate("voters");
                Voter.viewVotersList();

                System.out.println("Please select the record id you want to delete");
                selectedrecord =  userIn.nextInt();

                //empty name,idcardno,boxid and vote status from the seleted record
                fileContent[selectedrecord-1][0] = "";
                fileContent[selectedrecord-1][1] = "";
                fileContent[selectedrecord-1][2] = "";
                fileContent[selectedrecord-1][3] = "";

                //update with the changes
                updateContent(fileContent, "","VOTERS");
                System.out.println("Voter deleted!");
                waitUser();
                break;
            //show voters list
            case 4:
                Voter.viewVotersList();
                waitUser();
                break;
            //go back to main menu
            case 9:
                mainMenu();
                break;
        }
        //recursive functionality
        manageVoters();
    }

    //open / close and voting process function
    public static void voting(String box) throws FileNotFoundException {
        clearScreen();
        //initially ask for box number
        if(box.equals("")){
            authenticate("main");
            System.out.println("Please enter the box number to open for voting");
            box = userIn.next();
            Voting.openVoting(box);
        }
        clearScreen();

        //show eligible voters of that box who have not voted
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

        //show candidates list to choose from
        Candidate.viewCandiatesList(Integer.parseInt(box));
        System.out.println("Select the candidate you want to vote for");
        String votingcand = userIn.next();

        //after all the data is received, call add vote function to update
        Voting.addvote(voter, votingcand, box);

        //recursive call to let user stay on the voting box selected
        voting(box);
    }

    //view statistics function
    static void viewstats() throws FileNotFoundException {
        clearScreen();
        //create a new statistics object
        Statistics stats = new Statistics();

        //put all the data received from object to a multi dimensional array so we can loop for three boxes
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

        //print out the statistics
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
