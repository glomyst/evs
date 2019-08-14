package com.company;

import java.io.*;
import java.util.*;
public class Base {
    public static String[][] fileContent;
    public static int recordCount = 0;
    public static void showMenu(String menu) throws FileNotFoundException {
        clearScreen();
        String curFile = "";
        switch (menu){
            case "main":
                curFile = "database/menu/mainmenu.txt";
                break;
            case "manage_candidates":
                curFile = "database/menu/candidate/managecandidates.txt";
                break;
        }
        File file = new File(curFile);
        Scanner scanFile = new Scanner(file);

        while(scanFile.hasNext()){
            System.out.println(scanFile.nextLine());
        }
    }

    public static void fileWriter(String filetowrite, String content){
        try (FileWriter writer = new FileWriter("database/"+filetowrite,true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.append(content+"\n");
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void getFileContents(String readfile) throws FileNotFoundException {
        recordCount = 0;
        File file = new File("database/"+readfile);
        Scanner scanfile = new Scanner(file);

        countRecords(readfile);
        fileContent = new String[recordCount][recordCount*3];

        int count = 0;
        while(scanfile.hasNextLine()){
            String[] fileStuff = scanfile.nextLine().split(",");
            String name = fileStuff[0];
            String idcardno = fileStuff[1];
            String numvotes = fileStuff[2];

            fileContent[count][0] = name;
            fileContent[count][1] = idcardno;
            fileContent[count][2] = numvotes;
            count++;
        }

    }

    public static void showList(String[][] fileContent){
        for(int i = 0; i <= fileContent.length-1; i++){
            System.out.format("| %-3s| %-20s| %-20s|", i+1, fileContent[i][0], fileContent[i][1]);
            System.out.println();
            System.out.println("--------------------------------------------------");
        }
    }

    public static void countRecords(String readfile) throws FileNotFoundException {
        File file = new File("database/"+readfile);
        Scanner scanfile = new Scanner(file);

        while(scanfile.hasNextLine()){
            recordCount++;
            String line = scanfile.nextLine();
        }


    }

    public static void updateContent(String[][] fileContent, String selectedbox) throws FileNotFoundException {
        String file = "candidates/box/"+selectedbox+".txt";
        clearFileContent(file);
        for(int i = 0; i <= fileContent.length-1; i++){
            if(fileContent[i][0] != "" && fileContent[i][1] != ""){
                fileWriter(file, fileContent[i][0]+","+fileContent[i][1]+","+fileContent[i][2]);
            }
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    public static void  clearFileContent(String file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("database/"+file);
        writer.print("");
        writer.close();
    }
}

