package model;

import java.util.ArrayList;
import java.util.List;

public class Entry {
    // TODO: implement date functionality later
    //private Date date;
    private int entryNum;
    private List<Revenue> revenueList;
    private List<Expense> expenseList;

    public Entry(int entryNum) {
        //this.date = date;
        this.entryNum = entryNum;
        revenueList = new ArrayList<>();
        expenseList = new ArrayList<>();
    }

    // REQUIRES: r != null
    // MODIFIES: this
    // EFFECTS: add revenue to revenue list
    public void addRevenue(Revenue r) {
        revenueList.add(r);
    }

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


    // Helpers:

    // TODO: How to do this lol
    // REQUIRES: given list contains double type
    // EFFECTS: returns sum of given list
    private double sumOfListOfTransaction(List<Transaction> lot) {
        double sum = 0;

        for (int i = 0, n = lot.size(); i < n; i++) {
            sum += lot.get(i).getValue();
        }

        return sum;
    }








}
