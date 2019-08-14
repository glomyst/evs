package com.company;

import java.io.FileNotFoundException;

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

        String[][] eligible_voters = new String[voterscount][voterscount*4];
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
}
