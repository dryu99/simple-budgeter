package model;

import model.enums.ExpGenre;

public class Expense extends Transaction {

    public Expense(double value, String desc, ExpGenre genre) {
        super(value, desc, genre);
    }

    public Expense() {
        super();
    }

    // MODIFIES: this
    // EFFECTS: sets the entry the expense belongs to
    @Override
    public void setEntry(Entry newEntry) {
        if (entry == null || !entry.equals(newEntry)) {
            this.entry = newEntry;
            newEntry.addExpense(this);
        }
    }

}
