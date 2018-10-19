package model;

import model.exceptions.NullParameterGiven;

import java.util.ArrayList;
import java.util.List;

// TODO: give entry a id field?
// TODO: how to avoid duplication of add/prompt revenue/transaction methods

public class Entry {
    private static int nextEntryId = 1;
    private int id;
    private SimpleDate date;
    private List<Revenue> revenueList;
    private List<Expense> expenseList;

    public Entry(SimpleDate date) {
        id = nextEntryId++;
        this.date = date;
        revenueList = new ArrayList<>();
        expenseList = new ArrayList<>();
    }

    // Getters:
    public SimpleDate getDate() { return date; }

    // Setters:
    public void setDate(SimpleDate date) { this.date = date; }


    // EFFECTS: returns size of revenue list
    public int revenueListSize() {
        return revenueList.size();
    }

    // EFFECTS: returns size of expense list
    public int expenseListSize() { return expenseList.size(); }

    // EFFECTS: returns size of revenue + expense list
    public int transactionListSize() { return revenueListSize() + expenseListSize(); }

    // MODIFIES: this
    // EFFECTS: add revenue to revenue list
    public void addRevenue(Revenue r) {
        if (r == null) {
            throw new NullParameterGiven();
        }
        revenueList.add(r);
    }

    // MODIFIES: this
    // EFFECTS: add expense to expense list
    public void addExpense(Expense e) {
        if (e == null) {
            throw new NullParameterGiven();
        }
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

    @Override
    // EFFECTS: returns entry in string form
    public String toString() {
        return id + ". " + "ENTRY " + "(" + date + ")";
    }

    public String toCompleteString() {
        String header = this.toString();
        return header;
    }

    // TODO: add statistic print statements add the bottom
    // EFFECTS: print entry out, showing accumulated revenues and expenses
    public void print() {
        System.out.println("ENTRY " + "(" + date + ")");
        printLine("ENTRY " + "(" + date + ")", "=");

        printRevenues();
        System.out.println();
        printExpenses();
    }

    // EFFECTS: print vertical list of revenues from this revenueList.
    //          if revenueList is empty, print out "(no revenues for this entry)"
    private void printRevenues() {
        System.out.println("Revenues:");
        printLine("Revenues:", "-");

        if (!revenueList.isEmpty()) {
            for (Revenue r : revenueList) {
                System.out.println(r);
            }
        } else {
            System.out.println("(no revenues for this entry)");
        }
    }

    // EFFECTS: print vertical list of revenues from this revenueList
    //          if expense is empty, print out "(no expenses for this entry)"
    private void printExpenses() {
        System.out.println("Expenses:");
        printLine("Expenses:", "-");

        if (!expenseList.isEmpty()) {
            for (Expense e : expenseList) {
                System.out.println(e);
            }
        } else {
            System.out.println("(no expenses for this entry)");
        }
    }

    // TODO: after giving each entry a unique id field, take out the if statement here
    // EFFECTS: prints out line of a given type with length of given string (if type = "=", make length + 3)
    private void printLine(String str, String lineType) {
        String line = "";

        if (lineType.equals("=")) {
            line += "===";
        }

        for (int i = 0, n = str.length(); i < n; i++) {
            line += lineType;
        }
        System.out.println(line);
    }











}
