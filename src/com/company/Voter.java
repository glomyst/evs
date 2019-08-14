package com.company;

import java.io.FileNotFoundException;

public class Voter extends Base{
    public void candidate(){

    }

    public static void addVoter(String name, String idcardno, String box){
        String path = "voters/voters.txt";
        String content = name+","+idcardno+","+box;

        fileWriter(path, content);
        System.out.println("New voter Added!");
    }

    public static void viewVotersList() throws FileNotFoundException {

            System.out.println("===========================================================");
            System.out.println("=                       VOTERS LIST                       =");
            System.out.println("===========================================================");

            System.out.format("| %-3s| %-20s| %-11s| %-7s| %-7s|", "#", "Name", "ID #","Box #", "Voted");
            System.out.println();
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            getFileContents("voters/voters.txt");
            showList(fileContent, "VOTERS");
    }
}
