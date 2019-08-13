package com.company;

public class Candidate extends Base {
    public void candidate(){

    }

    public static void addCandidate(String name, String idcardno, String box){
        String path = "candidates/box/"+box+".txt";
        String content = name+","+idcardno;

        fileWriter(path, content);
        System.out.println("New candidate Added!");
    }
}
