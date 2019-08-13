package com.company;

import java.io.FileNotFoundException;

public class Candidate extends Base {
    public void candidate(){

    }

    public static void addCandidate(String name, String idcardno, String box){
        String path = "candidates/box/"+box+".txt";
        String content = name+","+idcardno;

        fileWriter(path, content);
        System.out.println("New candidate Added!");
    }

    public static void viewCandiatesList(int boxid) throws FileNotFoundException {
        int boxcnt;
        if(boxid >= 1 && boxid <= 3){
            boxcnt = boxid;
        }else{
            boxcnt = 1;
        }

        while(boxcnt <= 3){
            System.out.println("==================================================");
            System.out.println("                     BOX "+boxcnt+"                        ");
            System.out.println("==================================================");

            System.out.format("| %-3s| %-20s| %-20s|", "#", "Name", "ID Card Number");
            System.out.println();
            System.out.println("==================================================");
            getFileContents("candidates/box/"+boxcnt+".txt");
            showList(fileContent);

            if(boxid >= 1 && boxid <= 3){
                break;
            }
            boxcnt++;
        }
    }
}
