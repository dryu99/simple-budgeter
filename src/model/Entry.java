package model;

import model.exceptions.NullParameterGiven;

import java.util.ArrayList;
import java.util.List;

// TODO: how to avoid duplication of add/prompt revenue/transaction methods

public class Entry {
    private static int nextEntryId = 1;
    private int id;
    private SimpleDate date;
    // TODO: transaction manager class? I want to keep these lists separate tho, setting bi=directional relationship will be annoying with two lists
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

    // TODO: add transactions like this? will get rid of duplication, but want to avoid typecasting
//    public void addTransaction(Transaction t) {
//        if (t == null) {
//            throw new NullParameterGiven();
//        }
//
//        if (t instanceof Revenue) {
//            addRevenue((Revenue) t);
//        } else if (t instanceof Expense) {
//            addExpense((Expense) t);
//        }
//    }

    // TODO: add exception decsription in specification
    // MODIFIES: this
    // EFFECTS: add revenue to revenue list
    public void addRevenue(Revenue r) {
        if (r == null) {
            throw new NullParameterGiven();
        }

        if (!revenueList.contains(r)) {
            revenueList.add(r);
            r.setEntry(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: add expense to expense list
    public void addExpense(Expense e) {
        if (e == null) {
            throw new NullParameterGiven();
        }
        if (!expenseList.contains(e)) {
            expenseList.add(e);
            e.setEntry(this);
        }
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

    // TODO: create a separate class for calculating stats?
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
        String completeString = headerString(this.toString(), "=");

        completeString += revenueString() + "\n";
        completeString += expenseString() + "\n";

        return completeString;
    }

    // TODO: is it preferred to write out entire string in one variable
    // EFFECTS: returns vertical list of revenues from revenues.
    //          if there are no revenues, return a "no rev" message
    private String revenueString() {
        String revenueString = headerString("Revenues:", "-");

        if (!revenueList.isEmpty()) {
            for (Revenue r : revenueList) {
                revenueString += r + "\n";
            }
        } else {
            revenueString += "(no revenues for this entry)\n";
        }

        return revenueString;
    }

    // EFFECTS: returns vertical list of revenues from revenues.
    //          if there are no revenues, return a "no rev" message
    private String expenseString() {
        String expenseString = headerString("Expenses:", "-");

        if (!expenseList.isEmpty()) {
            for (Expense e : expenseList) {
                expenseString += e + "\n";
            }
        } else {
            expenseString += "(no expenses for this entry)\n";
        }

        return expenseString;
    }

    // EFFECTS:
    private String headerString(String str, String lineType) {
        String header = str;
        String headerLine = lineString(header, lineType);
        return header + "\n" + headerLine + "\n";
    }

    // EFFECTS: returns line of a given type with length of given string
    private String lineString(String str, String lineType) {
        String line = "";

        for (int i = 0, n = str.length(); i < n; i++) {
            line += lineType;
        }

        return line;
    }

    // TODO: redo hashcode and equals, and how to compare lists?
    // EFFECTS: returns true if entry has same id and date as compared
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        Entry compared = (Entry) o;

        return id == compared.id && date.equals(compared.date);
    }

    // EFFECTS: returns unique id based on the entry's id and date
    @Override
    public int hashCode() {
        if (date == null) {
            return id + 7;
        }

        return id + date.hashCode();
    }











}
