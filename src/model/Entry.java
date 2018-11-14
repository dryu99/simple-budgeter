package model;

import model.date.SimpleDate;

public class Entry implements Comparable<Entry> {
    private static int nextEntryId = 1;
    private int id;
    private SimpleDate date;
    private TransactionManager revenueManager;
    private TransactionManager expenseManager;

    public Entry(SimpleDate date) {
        id = nextEntryId++;
        this.date = date;
        revenueManager = new TransactionManager(this,true);
        expenseManager = new TransactionManager(this, false);
    }

    // Getters:
    public int getId() { return id; }
    public SimpleDate getDate() { return date; }

    // Setters:
    public void setDate(SimpleDate date) { this.date = date; }

    // EFFECTS: returns size of revenue list
    public int revenueListSize() {
        return revenueManager.size();
    }

    // EFFECTS: returns size of expense list
    public int expenseListSize() { return expenseManager.size(); }

    // EFFECTS: returns size of revenue + expense list
    public int transactionListSize() { return revenueListSize() + expenseListSize(); }

    // TODO: do i want to make one method addTransaction? (can check the value)
    // MODIFIES: this
    // EFFECTS: add revenue to revenue list
    public void addRevenue(Transaction r) {
        revenueManager.addTransaction(r);
    }

    // MODIFIES: this
    // EFFECTS: add expense to expense list
    public void addExpense(Transaction e) {
        expenseManager.addTransaction(e);
    }

    // TODO: even if I know that this modfies r, do I need to put in MODIFIES clause? Also do i need to put the REQUIRES for r != null (even tho that clause is in the manager method)
    // MODIFIES: this
    // EFFECTS: if given expense is in expense list, remove it and return true, ow return false;
    public boolean removeRevenue(Transaction r) {
        return revenueManager.removeTransaction(r);
    }

    // REQUIRES: e != null
    // MODIFIES: this
    // EFFECTS: if given expense is in expense list, remove it and return true, ow return false;
    public boolean removeExpense(Transaction e) {
        return expenseManager.removeTransaction(e);
    }

    // TODO: create a separate class for calculating stats?
    // EFFECTS: returns total revenues of this manager
    public double totalRevenue() {
        return revenueManager.totalValue();
    }

    // EFFECTS: returns total expenses of this manager
    public double totalExpenses() {
        return expenseManager.totalValue();
    }

    // EFFECTS: returns net value of this manager (revenues - expenses)
    public double netValue() {
        return totalRevenue() + totalExpenses();
    }

    @Override
    // EFFECTS: returns manager in simple string form
    public String toString() {
        return id + ". ENTRY " + "(" + date + ")";
    }

    // EFFECTS: returns manager in complete string form listing all revenues and expenses
    public String toCompleteString() {
        String completeString = BudgetStringer.underlinedHeaderString(this.toString(), "=");

        completeString += revenueManager.toString() + "\n";
        completeString += expenseManager.toString() + "\n";

        return completeString;
    }

    // EFFECTS: returns true if manager has same id and date as compared
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }

        if (this.getClass() != o.getClass()) { return false; }

        Entry compared = (Entry) o;

//        if (id != compared.id) { return false; }

        if (date != null ? !date.equals(compared.date) : compared.date != null) {
            return false;
        }

        return true;
    }

    // EFFECTS: returns unique id based on the manager's date
    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;

        return result * 31;
    }

    // EFFECTS: returns a positive number if this Entry's date is older than compared
    @Override
    public int compareTo(Entry compared) {
        return date.compareTo(compared.date);
    }
}
