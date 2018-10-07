package ui;

import model.enums.RevGenre;
import model.enums.ExpGenre;

import java.util.Scanner;

public class Prompter {
    private Scanner reader;

    public Prompter() {
        this.reader = new Scanner(System.in);
    }

    // EFFECTS: prints out "User Command: " and returns lower-case user command
    public String returnUserCommand() {
        System.out.print("User Command: ");
        String command = reader.nextLine();
        System.out.println();

        command = command.trim().toLowerCase();

        return command;
    }

    // EFFECTS: prints out given prompt, and returns user string
    public String returnUserString(String prompt) {
        System.out.print(prompt + " ");

        return reader.nextLine();
    }

    // TODO: make the returned double be in form 0.00
    // EFFECTS: prints out given prompt, then prompts user for a double then returns it
    //          if double isn't given, re-prompt user
    public Double returnUserDouble(String prompt) {
        System.out.print(prompt + " ");

        while (!reader.hasNextDouble()) {
            reader.nextLine();

            System.out.println("Please give a double.");
            System.out.println();
            System.out.print(prompt + " ");
        }

        return Double.parseDouble(reader.nextLine());
    }

    // EFFECTS: prompts user for a valid revenue genre, then returns it.
    //          if valid genre isn't given, re-prompt user.
    public RevGenre returnUserRevGenre() {
        String genre;

        System.out.println("What type of revenue is this?");

        while (true) {
            RevGenre.printUserInputList();
            System.out.print("Choose one of the above: ");

            genre = reader.nextLine();
            genre = genre.toUpperCase();

            for (RevGenre rg : RevGenre.values()) {
                if (rg.toString().equals(genre)) {
                    return rg;
                }
            }
            System.out.println("Please give a valid revenue genre.");
            System.out.println();
        }
    }

    // EFFECTS: prompts user for a valid expense genre, then returns it.
    //          if valid genre isn't given, re-prompt user.
    public ExpGenre returnUserExpGenre() {
        String genre;

        System.out.println("What type of expense is this?");

        while (true) {
            ExpGenre.printUserInputList();
            System.out.print("Choose one of the above: ");

            genre = reader.nextLine();
            genre = genre.toUpperCase();

            for (ExpGenre eg : ExpGenre.values()) {
                if (eg.toString().equals(genre)) {
                    return eg;
                }
            }
            System.out.println("Please give a valid expense genre.");
            System.out.println();
        }
    }




}
