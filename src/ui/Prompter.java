package ui;

import model.enums.RevGenre;
import model.enums.ExpGenre;
import model.SimpleDate;

import java.util.Scanner;

// TODO: implement try and catch stuff to reprompt users for invalid inputs
public class Prompter {
    private Scanner reader;

    public Prompter() {
        this.reader = new Scanner(System.in);
    }

    // EFFECTS: prints out "User Command: " and returns lower-case user command
    public String returnUserCommand(String prompt) {
        System.out.print(prompt);
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

    // TODO: how to avoid duplication from this method and returnUserExpGenre method? (put under one method? abstract?)
    // TODO: better to instantiate String genre outside while loop or inside?
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

    // TODO: implement checking functionality to reprompt user if non-int input is given (try and catch???)
    // EFFECTS: prompts user to give month, day, and year.
    //          returns a respective SimpleDate
    public SimpleDate returnUserDate() {
        System.out.println("Please provide the new entry's date in number form.");
        System.out.print("Month: ");
        int month = Integer.parseInt(reader.nextLine());

        System.out.print("Day: ");
        int day = Integer.parseInt(reader.nextLine());

        System.out.print("Year: ");
        int year = Integer.parseInt(reader.nextLine());

        return new SimpleDate(month, day, year);
    }




}
