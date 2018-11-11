package model;

import model.exceptions.NullParameterGiven;

import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    protected Entry entry;
    private List<Transaction> transactionList;

    // EFFECTS: creates a transaction manager that's linked to the given entry
    public TransactionManager(Entry e) {
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
    public void addTransaction(Transaction t) {
        if (t == null) {
            throw new NullParameterGiven();
        }

        if (!transactionList.contains(t)) {
            transactionList.add(t);
            if (!this.equals(t.getManager())) {
                t.setManager(this);
            }
        }
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

    @Override
    public String toString() {
        if (transactionList.get(0).getValue() > 0) {
            String revenuesString = BudgeterStringer.underlinedHeaderString("Revenues:", "-");
            revenuesString += BudgeterStringer.bodyString("revenues", transactionList);

            return revenuesString;
        } else {
            String expensesString = BudgeterStringer.underlinedHeaderString("Expenses:", "-");
            expensesString += BudgeterStringer.bodyString("expenses", transactionList);

            return expensesString;
        }
    }

}
