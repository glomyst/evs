package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.company.Candidate.addCandidate;

public class Main extends Base{
    public static Scanner userIn = new Scanner(System.in);
    public static int userMenuchoise;
    public static void main(String[] args) throws FileNotFoundException {
        mainMenu();
    }

    public static void mainMenu() throws FileNotFoundException {
        showMenu("main");
        System.out.println("Please select an option from above menu");
        userMenuchoise = userIn.nextInt();

        switch (userMenuchoise){
            case 1:
                manageCandidates();
                break;
        }
    }

    public static void manageCandidates() throws FileNotFoundException {
        showMenu("manage_candidates");
        System.out.println("Please select an option from above");
        int submenuopt = userIn.nextInt();

        switch (submenuopt){
            case 1:
                System.out.println("Please enter the candidate name: ");
                userIn.nextLine();
                String name = userIn.nextLine();
                System.out.println("Please enter the ID card number of the candidate: ");
                String idcardno = userIn.nextLine();
                System.out.println("Please enter the box number of candidate");
                String box = userIn.next();
                Candidate:addCandidate(name,idcardno,box);
                break;
            case 9:
                mainMenu();
                break;
        }

        manageCandidates();
    }
}
