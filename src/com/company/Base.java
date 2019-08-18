package com.company;

import java.io.*;
import java.util.*;
public class Base {
    static String[][] fileContent;
    private static int recordCount = 0;
    static String[][] eligible_voters;
    static void showMenu(String menu) throws FileNotFoundException {
        clearScreen();
        String curFile = "";
        switch (menu){
            case "main":
                curFile = "database/menu/mainmenu.txt";
                break;
            case "manage_candidates":
                curFile = "database/menu/candidate/managecandidates.txt";
                break;
            case "manage_voters":
                curFile = "database/menu/voter/managevoters.txt";
                break;
        }
        File file = new File(curFile);
        Scanner scanFile = new Scanner(file);

        while(scanFile.hasNext()){
            System.out.println(scanFile.nextLine());
        }
    }

    static void fileWriter(String filetowrite, String content){
        try (FileWriter writer = new FileWriter("database/"+filetowrite,true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.append(content).append("\n");
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    static void getFileContents(String readfile) throws FileNotFoundException {
        recordCount = 0;
        File file = new File("database/"+readfile);
        Scanner scanfile = new Scanner(file);

        countRecords(readfile);
        fileContent = new String[recordCount][recordCount*4];

        int count = 0;
        while(scanfile.hasNextLine()){
            String[] fileStuff = scanfile.nextLine().split(",");
            String name = fileStuff[0];
            String idcardno = fileStuff[1];
            String numvotes = fileStuff[2];
            String optval = fileStuff[3];


            fileContent[count][0] = name;
            fileContent[count][1] = idcardno;
            fileContent[count][2] = numvotes;
            fileContent[count][3] = optval;
            count++;
        }

    }

    static void showList(String[][] fileContent, String type){
        switch (type){
            case "CANDIDATES":
                for(int i = 0; i <= fileContent.length-1; i++){
                    System.out.format("| %-3s| %-20s| %-20s| %-7s|", i+1, fileContent[i][0], fileContent[i][1],fileContent[i][3]);
                    System.out.println();
                    System.out.println("-----------------------------------------------------------");
                }
                break;
            case "RESULTS":
                for(int i = 0; i <= fileContent.length-1; i++){
                    System.out.format("| %-3s| %-20s| %-10s| %-7s| %-8s|", i+1, fileContent[i][0], fileContent[i][1],fileContent[i][3],fileContent[i][2]);
                    System.out.println();
                    System.out.println("-----------------------------------------------------------");
                }
                break;

            case "VOTERS":
                for(int i = 0; i <= fileContent.length-1; i++){
                    System.out.format("| %-3s| %-20s| %-11s| %-7s| %-7s|", i+1, fileContent[i][0], fileContent[i][1],fileContent[i][2],fileContent[i][3]);
                    System.out.println();
                    System.out.println("-----------------------------------------------------------");
                }
                break;
        }
    }

    private static void countRecords(String readfile) throws FileNotFoundException {
        File file = new File("database/"+readfile);
        Scanner scanfile = new Scanner(file);

        while(scanfile.hasNextLine()){
            recordCount++;
            String line = scanfile.nextLine();
        }
    }

    static void updateContent(String[][] fileContent, String selectedbox, String type) throws FileNotFoundException {
        String file="";
        switch (type){
            case "CANDIDATES":
                file = "candidates/box/"+selectedbox+".txt";
                break;
            case "VOTERS":
                file = "voters/voters.txt";
                break;
            case "VOTING":
                file = "voting/box"+selectedbox+".txt";
                break;
        }


        clearFileContent(file);
        for(int i = 0; i <= fileContent.length-1; i++){
            if(!fileContent[i][0].equals("") && !fileContent[i][1].equals("")){
                fileWriter(file, fileContent[i][0]+","+fileContent[i][1]+","+fileContent[i][2]+","+fileContent[i][3]);
            }
        }
    }

    static void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    private static void  clearFileContent(String file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("database/"+file);
        writer.print("");
        writer.close();
    }

    static void waitUser(){
        System.out.println("Input any value and press enter to continue");
        Main.userIn.next();
    }

    public static void authenticate(String func) throws FileNotFoundException {
        getFileContents("database/auth/password.txt");
        switch (func){
            case "candidates":
                Main.manageCandidates();
                break;
            case "voters":
                Main.manageVoters();
                break;
            case "voting":
                Main.voting("");
                break;
            case "results":
                Candidate.showResults();
                break;
            case "stats":
                Main.viewstats();
                break;
        }
    }
}

