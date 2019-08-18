package com.company;

import java.io.FileNotFoundException;

public class Statistics extends Base{
    static int allvoters;
    static int totalcandidates = 0;
    static int votedvoters;
    static int unvotedvoters;
    static int numberofCandBoxOne;
    static int numberofCandBoxTwo;
    static int numberofCandBoxThree;
    static int numberofVotedBoxOne;
    static int numberofVotedBoxTwo;
    static int numberofVotedBoxThree;
    static int numberofUnVotedBoxOne;
    static int numberofUnVotedBoxTwo;
    static int numberofUnVotedBoxThree;
    static int numberofAllVotersBoxOne;
    static int numberofAllVotersBoxTwo;
    static int numberofAllVotersBoxThree;

    Statistics() throws FileNotFoundException {
        countVoters();
        countCandidates(0);

        votedvoters = numberofVotedBoxOne + numberofVotedBoxTwo + numberofVotedBoxThree;
        unvotedvoters = numberofUnVotedBoxOne + numberofUnVotedBoxTwo + numberofUnVotedBoxThree;
    }

    Statistics(String type){

    }

    private static void countVoters() throws FileNotFoundException {
        getFileContents("voters/voters.txt");
        allvoters = fileContent.length;

        int cnt = 1;
        while(cnt <= 3){
            countBoxVoter(cnt);
            Voting.getVotersList(Integer.toString(cnt));

            switch (cnt){
                case 1:
                    numberofUnVotedBoxOne = eligible_voters.length;
                    numberofVotedBoxOne = numberofAllVotersBoxOne - numberofUnVotedBoxOne;
                    break;
                case 2:
                    numberofUnVotedBoxTwo = eligible_voters.length;
                    numberofVotedBoxTwo = numberofAllVotersBoxTwo - numberofUnVotedBoxTwo;
                    break;
                case 3:
                    numberofUnVotedBoxThree = eligible_voters.length;
                    numberofVotedBoxThree = numberofAllVotersBoxThree - numberofUnVotedBoxThree;
                    break;
            }

            cnt++;
        }
    }

    private static void countCandidates(int box) throws FileNotFoundException {
        int cnt = (box >= 1 && box <= 3) ? box : 1;

        if(box >= 1 && box <= 3) {
            getFileContents("candidates/box/" + cnt + ".txt");
            switch (box){
                case 1:
                    numberofCandBoxOne = fileContent.length;
                    break;
                case 2:
                    numberofCandBoxTwo = fileContent.length;
                    break;
                case 3:
                    numberofCandBoxThree = fileContent.length;
                    break;
            }
        }else{
            while (cnt <= 3) {
                countCandidates(cnt);
                getFileContents("candidates/box/" + cnt + ".txt");
                totalcandidates += fileContent.length;
                cnt++;
            }
        }

    }

    private static void countBoxVoter(int box) throws FileNotFoundException {
        getFileContents("voters/voters.txt");
        int voterscount = 0;

        for(int i = 0; i <= fileContent.length-1; i++){
            if(fileContent[i][2].equals(Integer.toString(box)) && fileContent[i][2].equals(Integer.toString(box))) {
                voterscount++;
            }
        }

        switch (box){
            case 1:
                numberofAllVotersBoxOne = voterscount;

                break;
            case 2:
                numberofAllVotersBoxTwo = voterscount;

                break;
            case 3:
                numberofAllVotersBoxThree = voterscount;
                break;
        }
    }

}
