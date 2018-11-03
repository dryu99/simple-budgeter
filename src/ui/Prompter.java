package ui;

import model.SimpleDate;
import model.enums.ExpGenre;
import model.enums.RevGenre;

import java.util.Scanner;

// TODO: implement try and catch stuff to reprompt users for invalid inputs
// SINGLETON CLASS: only one prompter object for budgeting app.
public class Prompter {
    private static Prompter instance = new Prompter();
    private Scanner reader;

    private Prompter() {
        reader = SingleScanner.getInstance();
    }

    // EFFECTS: returns singular Prompter instance
    public static Prompter getInstance() { return instance; }


    // EFFECTS: prints out "User Command: " and returns lower-case user command
    public String returnUserCommand(String prompt) {
        System.out.print(prompt);
        String command = reader.nextLine();

        command = command.trim().toLowerCase();

        return command;
    }

    // TODO: if I decide not to use the Calendar library, how to implement exception for checking valid dates? The returnUserDateValue would throw the exception "InvalidMonthException" etc and this method would tach it
    // TODO: have to add exceptions for invalid dates yaaaaa
    // EFFECTS: Returns a simple date created by user input
    public SimpleDate returnUserSimpleDate() {
        int month = returnUserDateValue("Month");
        int day = returnUserDateValue("Day");
        int year = returnUserDateValue("Year");

        return new SimpleDate(month, day, year);
    }

    // TODO: do we have to make specification mention exceptions?
    // TODO: make the returned double be in form 0.00
    // EFFECTS: prints out given prompt, then prompts user for a double then returns it
    //          if double isn't given, re-prompt user
    public Double returnUserDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double d = Double.parseDouble(reader.nextLine());
                return d;
            } catch (NumberFormatException ime) {
                System.out.println("Please give a double.\n");
            }
        }
    }

    // TODO: better to instantiate String genre outside while loop or inside?
    // TODO: make toLowerCase instaed? will have to change implementation of toString for genres
    // TODO: how to implement a try-catch here
    // EFFECTS: prompts user for a valid revenue genre, then returns it.
    //          if valid genre isn't given, re-prompt user.
    public RevGenre returnUserRevGenre() {
        String genre;
        System.out.println("What type of revenue is this?");

        while (true) {
            RevGenre.printUserInputList();
            System.out.print("Choose one of the above: ");
            genre = reader.nextLine().toUpperCase().trim();

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
            genre = reader.nextLine().toUpperCase().trim();

            for (ExpGenre eg : ExpGenre.values()) {
                if (eg.toString().equals(genre)) {
                    return eg;
                }
            }
            System.out.println("Please give a valid expense genre.");
            System.out.println();
        }
    }

    // EFFECTS: Prompts user for the specified integer date value and returns it
    //          if user does not give an integer, throw a NumberFormatException and re-prompt.
    private int returnUserDateValue(String dateType) {

        while (true) {
            System.out.print(dateType + ": ");

            try {
                int dateValue = Integer.parseInt(reader.nextLine());
                return dateValue;
            } catch (NumberFormatException nfe) {
                System.out.println("Please give a valid " + dateType + "\n");
            }
        }
    }





}
