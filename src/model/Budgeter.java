package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Budgeter implements Saveable, Loadable {
    private Scanner reader;
    private List<Transaction> transactionList;

    // Constructor
    public Budgeter() {
        reader = new Scanner(System.in);
        transactionList = new ArrayList<>();
    }

    // EFFECTS: starts Budgeter application
    public void start() throws IOException {
        this.load();
        System.out.println("BUDGETING APPLICATION");
        System.out.println();

        while (true) {
            System.out.println("What would you like to do?");
            printOptions();

            System.out.print("User Command: ");
            String command = reader.nextLine();
            command = command.trim().toLowerCase();

            if (command.equals("exit")) {
                this.save();
                System.out.println("See you later!");
                break;
            }

            handleCommand(command);
        }
    }

    // TODO: implement executions for each respective case
    // EFFECTS: if valid command is given execute it, ow print "That's not a real command!"
    private void handleCommand(String command) {
        switch (command) {
            case "0":
                this.addRevenue();
                break;
            case "1":
                this.addExpense();
                break;
            case "2":
                this.showTransactions();
                break;
                default:
                    System.out.println("That's not a real command! Try again.");
                    break;
        }
        System.out.println();
    }

    // EFFECTS: prompts user for revenue amount and adds it to transaction list
    private void addRevenue() {
        System.out.println("How much did you receive?");

        double amount = Double.parseDouble(reader.nextLine());

        System.out.println("Adding revenue of $" + amount + " to transaction list");
        transactionList.add(new Revenue(amount, RevGenre.WORK));
    }

    // EFFECTS: prompts user for expense amount and adds it to transaction list
    private void addExpense() {
        System.out.println("How much did you spend?");

        double amount = Double.parseDouble(reader.nextLine());

        System.out.println("Adding expense of $" + amount + " to transaction list");
        transactionList.add(new Expense(amount, ExpGenre.FOOD));
    }

    // EFFECTS: prints out transactions
    private void showTransactions() {
        if (transactionList.isEmpty()) {
            System.out.println("No transactions have been entered!");
            return;
        }

        System.out.println("Recorded Transactions:");

        for (int i = 0, n = transactionList.size(); i < n; i++) {
            System.out.print((i + 1) + ": ");
            System.out.println(transactionList.get(i));
        }
    }

    // EFFECTS: prints out budget application options
    private void printOptions() {
        System.out.println("[0] Add a Revenue");
        System.out.println("[1] Add an Expense");
        System.out.println("[2] Show all transactions");
        System.out.println("[exit] Exit application");
        System.out.println();
    }


    @Override
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("savedtransactionsfile.txt"));
        for (String line : lines) {
            Transaction t;

            ArrayList<String> partsOfLine = splitOnSpace(line);

        }
    }

    @Override
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("savedtransactionsfile.txt","UTF-8");
        for (Transaction t : transactionList) {
            String transaction = t.toString();

            writer.println(transaction);
        }
        writer.close();
    }

    private ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
