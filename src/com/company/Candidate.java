package com.company;

import java.io.FileNotFoundException;

public class Candidate extends Base {

    //add candidate function. adds a new candidates
    public static void addCandidate(String name, String idcardno, String candno,String box){
        String path = "candidates/box/"+box+".txt";
        String content = name+","+idcardno+",0,"+candno;

        fileWriter(path, content);
        System.out.println("New candidate Added!");
    }

    //shows the whole candidates list
    public static void viewCandiatesList(int boxid) throws FileNotFoundException {
        int boxcnt;
        if(boxid >= 1 && boxid <= 3){
            boxcnt = boxid;
        }else{
            boxcnt = 1;
        }

        while(boxcnt <= 3){
            System.out.println("===========================================================");
            System.out.println("                          BOX "+boxcnt+"                            ");
            System.out.println("===========================================================");

            System.out.format("| %-3s| %-20s| %-20s| %-7s|", "#", "Name", "ID #", "Cand #");
            System.out.println();
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            getFileContents("candidates/box/"+boxcnt+".txt");
            showList(fileContent, "CANDIDATES");

            if(boxid >= 1 && boxid <= 3){
                break;
            }
            boxcnt++;
        }
    }

    //shows the results of each of the candidates
    public static void showResults() throws FileNotFoundException {
        clearScreen();
        System.out.println("#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#");
        System.out.println("#                         RESULTS                         #");
        System.out.println("#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#");
        int boxcnt = 1;
        while(boxcnt <= 3){
            System.out.println("===========================================================");
            System.out.println("                          BOX "+boxcnt+"                            ");
            System.out.println("===========================================================");

            System.out.format("| %-3s| %-20s| %-10s| %-7s| %-8s|", "#", "Name", "ID #", "Cand #", "Votes");
            System.out.println();
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            getFileContents("candidates/box/"+boxcnt+".txt");
            showList(fileContent, "RESULTS");

            boxcnt++;
        }

        waitUser();
        Main.mainMenu();
    }
}
