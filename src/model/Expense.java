package model;

import model.enums.ExpGenre;

public class Expense extends Transaction {

    public Expense(double value, String desc, ExpGenre genre) {
        super(value, desc, genre);
    }

    public Expense() {
        super();
    }
}
