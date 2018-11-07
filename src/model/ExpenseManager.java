package model;

// CLASS: Grouped Expense data and methods are stored here
public class ExpenseManager extends TransactionManager {

    public ExpenseManager(Entry e) {
        super(e);
    }

    // REQUIRES: e != null
    // MODIFIES: this
    // EFFECTS: if given expense is in expense list, remove it and return true, ow return false;
    public boolean removeExpense(Expense e) {
        if (transactionList.contains(e)) {
            transactionList.remove(e);
            return true;
        }
        return false;
    }

    // EFFECTS: returns total expenses of this manager
    public double totalExpenses() {
        double totalExp = 0;

        for (Transaction e : transactionList) {
            totalExp += e.getValue();
        }
        return totalExp;
    }

    // TODO: make this toString
    // EFFECTS: returns vertical list of expenses from expense list.
    @Override
    public String toString() {
        String expensesString = EntryStringReturner.headerString("Expenses:", "-");
        expensesString += EntryStringReturner.bodyString("expenses", transactionList);

        return expensesString;
    }

}
