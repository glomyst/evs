package com.company;

import java.io.*;
import java.util.*;
public class Base {
    //array to store contents received from file
    static String[][] fileContent;
    // store result of array
    private static int recordCount = 0;
    //store eligible voters for a box in this array
    static String[][] eligible_voters;

    //show menu function
    static void showMenu(String menu) throws FileNotFoundException {
        //depending on the argument received choose which menu file to read
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
        //read the file required
        File file = new File(curFile);
        Scanner scanFile = new Scanner(file);

        //output all the contents of the menu file
        while(scanFile.hasNext()){
            System.out.println(scanFile.nextLine());
        }
    }

    //file writer function
    static void fileWriter(String filetowrite, String content){
        //get file name and content to write to that file. than open the file and append the content to it
        try (FileWriter writer = new FileWriter("database/"+filetowrite,true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.append(content).append("\n");
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    //get file contents function
    static void getFileContents(String readfile) throws FileNotFoundException {
        //get file name and read all the content in it
        recordCount = 0;
        File file = new File("database/"+readfile);
        Scanner scanfile = new Scanner(file);

        //crate and array to put the content in
        countRecords(readfile);
        fileContent = new String[recordCount][recordCount*4];

        //put all the content in the array fileContent
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

    //show lists function
    static void showList(String[][] fileContent, String type){
        //get a fileContent array and output all the data depending on the type provided
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

    //count records function
    private static void countRecords(String readfile) throws FileNotFoundException {
        //counts the number of records in a file
        File file = new File("database/"+readfile);
        Scanner scanfile = new Scanner(file);

        while(scanfile.hasNextLine()){
            recordCount++;
            String line = scanfile.nextLine();
        }
    }

    //update contents function
    static void updateContent(String[][] fileContent, String selectedbox, String type) throws FileNotFoundException {
        //get a fileContent array and replace the whole file with the new updated data
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

        // first clear existing file and feed the content to file writer to write to it
        clearFileContent(file);
        for(int i = 0; i <= fileContent.length-1; i++){
            if(!fileContent[i][0].equals("") && !fileContent[i][1].equals("")){
                fileWriter(file, fileContent[i][0]+","+fileContent[i][1]+","+fileContent[i][2]+","+fileContent[i][3]);
            }
        }
    }

    //add new line to the console to replicate clearing the screen
    static void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    //clear all the contents in a file
    private static void  clearFileContent(String file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("database/"+file);
        writer.print("");
        writer.close();
    }

    //waits the user until an input is given. used to show messages.
    static void waitUser(){
        System.out.println("Input any value and press enter to continue");
        Main.userIn.next();
    }

    //gets the password from a file and compare it with the entered password.
    //depending on the call pack function user it taken back to the menu if password is wrong
    public static void authenticate(String func) throws FileNotFoundException {
        getFileContents("auth/password.txt");
        System.out.println("Please enter admin password: ");
        String password = Main.userIn.next();

        if(!(fileContent[0][0].equals(encrypt(password)))){
            System.out.println("Access Deined!");
            waitUser();
            switch (func){
                case "main":
                    Main.mainMenu();
                    break;
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
        //System.out.println(encrypt("verystrongpass"));
    }

    //encrypts the password to base 64
    public static String encrypt(String plain) {
        String b64encoded = Base64.getEncoder().encodeToString(plain.getBytes());

        // Reverse the string
        String reverse = new StringBuffer(b64encoded).reverse().toString();

        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < reverse.length(); i++) {
            tmp.append((char)(reverse.charAt(i) + OFFSET));
        }
        return tmp.toString();
    }
}


