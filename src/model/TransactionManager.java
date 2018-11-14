package model;

import model.exceptions.NullParameterGiven;

import java.util.ArrayList;
import java.util.List;

// CLASS: manages transactions in a list
public class TransactionManager {
    // TODO: how to better identify whether a manager is revenue or expense. need to know because of tostring method, right now im using a boolean opposed to subclasses
    private boolean isRevenue;
    private Entry entry;
    private List<Transaction> transactionList;

    // EFFECTS: creates a transaction manager that's linked to the given entry
    public TransactionManager(Entry e, boolean type) {
        isRevenue = type;
        entry = e;
        transactionList = new ArrayList<>();
    }

    // EFFECTS: returns size of revenue list
    public int size() {
        return transactionList.size();
    }

    // EFFECTS: returns true if transaction list contains given transaction, false ow
    public boolean contains(Transaction t) { return transactionList.contains(t); }

    // TODO: create a separate class for calculating stats?
    // EFFECTS: returns total revenues of this manager
    public double totalValue() {
        double totalVal = 0;

        for (Transaction t : transactionList) {
            totalVal += t.getValue();
        }
        return totalVal;
    }

    // MODIFIES: this, t
    // EFFECTS: if t == null, throw NullParameterGiven exception,
    //          ow add given transaction to transaction list if it hasn't already been,
    //          as well, assign the transaction to this transaction manager
    public boolean addTransaction(Transaction t) {
        if (t == null) {
            throw new NullParameterGiven();
        }

        if (!transactionList.contains(t)) {
            transactionList.add(t);
            if (!this.equals(t.getManager())) {
                t.setManager(this);
            }
            return true;
        }
        return false;
    }

    // MODIFIES: this, t
    // EFFECTS: if t == null, throw NullParameterGiven exception,
    //          ow eliminate the given transaction's relationship to this manager
    //          as well, remove the transaction from this manager
    public boolean removeTransaction(Transaction t) {
        if (t == null) {
            throw new NullParameterGiven();
        }

        if (transactionList.contains(t)) {
            t.setManager(null);
            transactionList.remove(t);
            return true;
        }
        return false;
    }

    // TODO: how to code this better lol
    @Override
    public String toString() {
        if (isRevenue) {
            String revenuesString = BudgetStringer.underlinedHeaderString("Revenues:", "-");
            revenuesString += BudgetStringer.bodyString("revenues", transactionList);

            return revenuesString;
        } else {
            String expensesString = BudgetStringer.underlinedHeaderString("Expenses:", "-");
            expensesString += BudgetStringer.bodyString("expenses", transactionList);

            return expensesString;
        }
    }

}
