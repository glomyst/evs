package com.company;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Voting extends Base{

    public static void voting(){

    }

    public static void getVotersList(String box) throws FileNotFoundException {
        getFileContents("voters/voters.txt");
        int voterscount = 0;

        for(int i = 0; i <= fileContent.length-1; i++){
            if(fileContent[i][2].equals(box) && fileContent[i][3].equals("no")) {
                voterscount++;
            }
        }

        eligible_voters = new String[voterscount][voterscount*4];
        System.out.println(voterscount);

        System.out.println("===========================================================");
        System.out.println("             ELIGIBLE VOTERS LIST FOR BOX "+box+"                 ");
        System.out.println("===========================================================");

        int indexcount = 0;
        for(int i = 0; i <= fileContent.length-1; i++){
            if(fileContent[i][2].equals(box) && fileContent[i][3].equals("no")){
                eligible_voters[indexcount][0] = fileContent[i][0];
                eligible_voters[indexcount][1] = fileContent[i][1];
                eligible_voters[indexcount][2] = fileContent[i][2];
                eligible_voters[indexcount][3] = fileContent[i][3];
                indexcount++;
            }
        }

        showList(eligible_voters, "VOTERS");
    }

    public static void openVoting(String box) throws FileNotFoundException {
        getFileContents("voting/box"+box+".txt");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        if(!(fileContent[0][1].equals("0"))){
            System.out.println("Box "+box+" is closed");
            waitUser();
            Main.mainMenu();
        }

        if(fileContent[0][0].equals("0")){
            fileContent[0][0] = dtf.format(now);
            updateContent(fileContent, box, "VOTING");
        }else{
            System.out.println("Box "+box+" is already open for voting");
            waitUser();
        }

    }

    public static void closeVoting(String box) throws FileNotFoundException {
        getFileContents("voting/box"+box+".txt");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        fileContent[0][1] = dtf.format(now);

        updateContent(fileContent, box, "VOTING");

        System.out.println("Voting closed for box "+box);
        waitUser();
        Main.mainMenu();
    }

    public static void addvote(String voter, String candidate, String box) throws FileNotFoundException {
        getFileContents("voters/voters.txt");

        for(int i = 0; i <= fileContent.length-1; i++){
            if(fileContent[i][1].equals(voter)){
                fileContent[i][3] = "yes";
            }
        }

        updateContent(fileContent, "", "VOTERS");

        getFileContents("candidates/box/"+box+".txt");
        int currentVotes = Integer.parseInt(fileContent[Integer.parseInt(candidate)-1][2]);
        fileContent[Integer.parseInt(candidate)-1][2] = Integer.toString(++currentVotes);

        updateContent(fileContent, box, "CANDIDATES");
    }
}
