package com.company;

import java.io.*;
import java.util.*;
public class Base {
    public static void showMenu(String menu) throws FileNotFoundException {
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
        File file = new File(readfile);
        Scanner scanfile = new Scanner(file);

        List<String> fileContent = new ArrayList<>();

        while(scanfile.hasNext()){
            
        }
    }
}

