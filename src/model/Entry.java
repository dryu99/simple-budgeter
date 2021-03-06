package model;

import java.util.ArrayList;
import java.util.List;

public class Entry {
    private String myDate;
    private TransactionManager revenueManager;
    private TransactionManager expenseManager;

    public Entry(String date) {
        myDate = date;
        revenueManager = new TransactionManager(this);
        expenseManager = new TransactionManager(this);
    }

    // Getters:
    public String getDate() { return myDate; }
    public List<Transaction> getRevenues() { return revenueManager.getTransactionList(); }
    public List<Transaction> getExpenses() { return expenseManager.getTransactionList(); }
    public List<Transaction> getTransactions() { //TODO: have to test this method
        List<Transaction> combinedList = new ArrayList<>();
        combinedList.addAll(getRevenues());
        combinedList.addAll(getExpenses());

        return combinedList;
    }

    // EFFECTS: returns complete transaction using incomplete transaction data from the appropriate manager //todo test
    public Transaction getCompleteTransactionFromManager(Transaction incompleteTransaction) {
        if (incompleteTransaction.getValue() > 0) {
            return revenueManager.getCompleteTransaction(incompleteTransaction);
        } else {
            return expenseManager.getCompleteTransaction(incompleteTransaction);
        }
    }

    // Setters:
    public void setDate(String date) { myDate = date; }

    // EFFECTS: returns true if given transaction is contained in the appropriate manager //tODO test this method
    public boolean contains(Transaction t) {
        if (t.getValue() > 0) {
            return revenueManager.contains(t);
        } else {
            return expenseManager.contains(t);
        }
    }

    // EFFECTS: returns size of revenue list
    public int revenueListSize() {
        return revenueManager.size();
    }

    // EFFECTS: returns size of expense list
    public int expenseListSize() { return expenseManager.size(); }

    // EFFECTS: returns size of revenue + expense list
    public int transactionListSize() { return revenueListSize() + expenseListSize(); }

    // MODIFIES: this
    // EFFECTS: add transaction to appropriate manager
    public void addTransaction(Transaction t) {
        if (t.getValue() > 0) {
            revenueManager.addTransaction(t);
        } else {
            expenseManager.addTransaction(t);
        }
    }

    // TODO: even if I know that this modfies t, do I need to put in MODIFIES clause? Also do i need to put the REQUIRES for t != null (even tho that clause is in the manager method)
    // MODIFIES: this
    // EFFECTS: if given transaction is in its appropriate list, remove it and return true, ow return false;
    public boolean removeTransaction(Transaction t) {
        if (t.getValue() > 0) {
            return revenueManager.removeTransaction(t);
        } else {
            return expenseManager.removeTransaction(t);
        }
    }

    // EFFECTS: returns total revenue value of this entry
    public double totalRevenue() {
        return revenueManager.totalValue();
    }

    // EFFECTS: returns total expense value of this entry
    public double totalExpenses() {
        return expenseManager.totalValue();
    }

    // EFFECTS: returns net value of this manager (revenues - expenses)
    public double netValue() {
        return totalRevenue() + totalExpenses();
    }

    // EFFECTS: returns true if manager has same id and date as compared
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }

        if (this.getClass() != o.getClass()) { return false; }

        Entry compared = (Entry) o;

        if (myDate != null ? !myDate.equals(compared.myDate) : compared.myDate != null) {
            return false;
        }

        return true;
    }

    // EFFECTS: returns unique id based on the manager's date
    @Override
    public int hashCode() {
        int result = myDate != null ? myDate.hashCode() : 0;

        return result * 31;
    }

}
