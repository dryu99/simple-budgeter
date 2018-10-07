package model;

import model.enums.ExpGenre;
import model.enums.RevGenre;
import ui.Prompter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Budgeter {
    private Scanner reader;
    private Prompter prompter;
    private List<Transaction> transactionList;

    // Constructor
    public Budgeter() {
        reader = new Scanner(System.in);
        prompter = new Prompter();
        transactionList = new ArrayList<>();

        start();
    }

    // TODO: implement a user input class
    // EFFECTS: starts Budgeter application
    private void start() {
        System.out.println("BUDGETING APPLICATION");
        System.out.println();

        while (true) {
            System.out.println("What would you like to do?");
            printOptions();

            String command = prompter.returnUserCommand();

            if (command.equals("exit")) {
                System.out.println("See you later!");
                break;
            }
            handleCommand(command);
        }
    }

    // TODO: implement executions for each respective case
    // TODO: implement adding entry (addEntry, creates new entry and prompts user for revnue/expense then value,desc,type)
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

    // MODIFIES: this
    // EFFECTS: prompts user for revenue amount and adds it to transaction list
    private void addRevenue() {
        Revenue newRevenue = new Revenue();
        System.out.println("--ADDING REVENUE--");

        double amount = prompter.returnUserDouble("How much did you receive?");
        String desc = prompter.returnUserString("Description:");
        RevGenre genre = prompter.returnUserRevGenre();

        newRevenue.setValue(amount);
        newRevenue.setDescription(desc);
        newRevenue.setGenre(genre);

        System.out.println("Adding revenue of $" + amount + " - " + desc + " (" + genre + ") to transaction list");
        transactionList.add(newRevenue);
    }

    // MODIFIES: this
    // EFFECTS: prompts user for expense amount and adds it to transaction list
    private void addExpense() {
        Expense newExpense = new Expense();
        System.out.println("--ADDING EXPENSE--");

        double amount = prompter.returnUserDouble("How much did you spend?");
        String desc = prompter.returnUserString("Description:");
        ExpGenre genre = prompter.returnUserExpGenre();

        newExpense.setValue(amount);
        newExpense.setDescription(desc);
        newExpense.setGenre(genre);

        System.out.println("Adding expense of $" + amount + " - " + desc + " (" + genre + ") to transaction list");
        transactionList.add(newExpense);
    }

    // EFFECTS: prints out transactions
    private void showTransactions() {
        if (transactionList.isEmpty()) {
            System.out.println("No transactions have been entered!");
            return;
        }

        System.out.println("--RECORDED TRANSACTIONS--");

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

    // TODO: when implementing save/load functionality, put load() and save() at beginning and break; of enter()
//    @Override
//    public void load() throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get("savedtransactionsfile.txt"));
//        for (String line : lines) {
//
//            ArrayList<String> partsOfLine = splitOnSpace(line);
//
//            if (partsOfLine.get(0).equals("Revenue")) {
//                Revenue revenue = new Revenue(Double.parseDouble(partsOfLine.get(1)), "Save-On Payroll", RevGenre.WORK);
//                transactionList.add(revenue);
//            } else {
//                Expense expense = new Expense(Double.parseDouble(partsOfLine.get(1)), "McDonalds", ExpGenre.FOOD);
//                transactionList.add(expense);
//            }
//        }
//    }
//
//    @Override
//    public void save() throws FileNotFoundException, UnsupportedEncodingException {
//        PrintWriter writer = new PrintWriter("savedtransactionsfile.txt","UTF-8");
//        for (Transaction t : transactionList) {
//            String transaction = t.toString();
//
//            writer.println(transaction);
//        }
//        writer.close();
//    }
//
//    private ArrayList<String> splitOnSpace(String line){
//        String[] splits = line.split(" ");
//        return new ArrayList<>(Arrays.asList(splits));
//    }
}
