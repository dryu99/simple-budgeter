package model;

import model.enums.ExpGenre;
import model.enums.RevGenre;
import ui.Prompter;

import java.util.ArrayList;
import java.util.List;

// TODO: give entry a id field?

public class Entry {
    private SimpleDate date;
    private List<Revenue> revenueList;
    private List<Expense> expenseList;
    private Prompter prompter;

    public Entry() {
        date = new SimpleDate();
        revenueList = new ArrayList<>();
        expenseList = new ArrayList<>();
        prompter = new Prompter();
    }

    // TODO: here and also in the yes/no part of addEntry, I'm not sure how to use a handleCommand method here as I need to break and I can't break from a loop in a diff method. I don't want to use if statements
    // MODIFIES: this
    // EFFECTS: adds transaction to respective list
    public void addTransaction() {
        while (true) {
            System.out.println("What kind of transaction do you want to add?");
            System.out.println("[0] Revenue");
            System.out.println("[1] Expense");
            System.out.println();

            String command = prompter.returnUserCommand("User Command: ");

            if (command.equals("0")) {
                this.promptRevenue();
                break;
            } else if (command.equals("1")) {
                this.promptExpense();
                break;
            } else {
                System.out.println("That's not a real command! Try again.");
            }
        }

        // TODO: implement "do u want to do more" method (you'll probably have to remove the break; in the if statements above)
    }

    // TODO: change expense/revenue attribute "description" to sth else, and change the prompt here
    // TODO: so separating the user inputs and the actual adding of the revenue is the right thing to do for tests? How should I apply this systematic design to all methods involving scanners?
    // TODO: maybe change method name
    // MODIFIES: this
    // EFFECTS: prompts user for revenue amount, description, and genre, and adds it to revenue list
    private void promptRevenue() {
        Revenue newRevenue = new Revenue();
        System.out.println("--ADDING REVENUE TO ENTRY (" + date + ")--");

        double amount = prompter.returnUserDouble("How much did you receive?");
        String desc = prompter.returnUserString("Description:");
        RevGenre genre = prompter.returnUserRevGenre();

        newRevenue.setValue(amount);
        newRevenue.setDescription(desc);
        newRevenue.setGenre(genre);

        System.out.println("**" + newRevenue + " added to Entry (" + date + ")**");
        this.addRevenue(newRevenue);
    }

    // TODO: change expense/revenue attribute "description" to sth else, and change the prompt here
    // TODO: so separating the user inputs and the actual adding of the revenue is the right thing to do for tests? How should I apply this systematic design to all methods involving scanners?
    // TODO: maybe change method name
    // MODIFIES: this
    // EFFECTS: prompts user for expense amount, description, and genre, and adds it to transaction list
    private void promptExpense() {
        Expense newExpense = new Expense();
        System.out.println("--ADDING EXPENSE TO ENTRY (" + date + ")--");

        double amount = prompter.returnUserDouble("How much did you spend?");
        String desc = prompter.returnUserString("Description:");
        ExpGenre genre = prompter.returnUserExpGenre();

        newExpense.setValue(amount);
        newExpense.setDescription(desc);
        newExpense.setGenre(genre);

        System.out.println("**" + newExpense + " added to Entry (" + date + ")**");
        this.addExpense(newExpense);
    }

    // TODO: want to make this method private but can't because of tests
    // REQUIRES: r != null
    // MODIFIES: this
    // EFFECTS: add revenue to revenue list
    public void addRevenue(Revenue r) {
        revenueList.add(r);
    }

    // TODO: want to make this method private but can't because of tests
    // REQUIRES: e != null
    // MODIFIES: this
    // EFFECTS: add expense to expense list
    public void addExpense(Expense e) {
        expenseList.add(e);
    }

    // REQUIRES: r != null
    // MODIFIES: this
    // EFFECTS: if given expense is in expense list, remove it and return true, ow return false;
    public boolean removeRevenue(Revenue r) {
        if (revenueList.contains(r)) {
            revenueList.remove(r);
            return true;
        }
        return false;
    }

    // REQUIRES: e != null
    // MODIFIES: this
    // EFFECTS: if given expense is in expense list, remove it and return true, ow return false;
    public boolean removeExpense(Expense e) {
        if (expenseList.contains(e)) {
            expenseList.remove(e);
            return true;
        }
        return false;
    }

    // EFFECTS: returns total revenues of this entry
    public double totalRevenue() {
        double totalRev = 0;

        for (Revenue r : revenueList) {
            totalRev += r.getValue();
        }
        return totalRev;
    }

    // EFFECTS: returns total expenses of this entry
    public double totalExpenses() {
        double totalExp = 0;

        for (Expense e : expenseList) {
            totalExp += e.getValue();
        }
        return totalExp;
    }

    // EFFECTS: returns net value of this entry (revenues - expenses)
    public double netValue() {
        double netVal = totalRevenue() - totalExpenses();

        return netVal;
    }

    // EFFECTS: returns size of given transaction type list
    public int sizeRevenueList() {
        return revenueList.size();
    }

    // EFFECTS: returns total size of both transaction type lists
    public int sizeExpenseList() {
        return expenseList.size();
    }

    // TODO: make this toString?
    // TODO: add statistic print statements add the bottom
    // EFFECTS: print entry out, showing accumulated revenues and expenses
    public void print() {
        System.out.println("ENTRY " + "(" + date + ")");
        System.out.println("==========");

        System.out.println("Revenues:");
        System.out.println("----------");
        printRevenues();
        System.out.println();

        System.out.println("Expenses:");
        System.out.println("----------");
        printExpenses();
        System.out.println();
    }

    // EFFECTS: print vertical list of revenues from this revenueList
    private void printRevenues() {
        for (Revenue r : revenueList) {
            System.out.println(r);
        }
    }

    // EFFECTS: print vertical list of revenues from this revenueList
    private void printExpenses() {
        for (Expense e : expenseList) {
            System.out.println(e);
        }
    }









}
