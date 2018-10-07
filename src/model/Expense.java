package model;

import model.enums.ExpGenre;

public class Expense extends Transaction {
    private ExpGenre genre;

    public Expense(double value, String desc, ExpGenre genre) {
        super(value, desc);
        this.genre = genre;
    }

    public Expense() {
        super();
        genre = null;
    }

    // Getters:
    public ExpGenre getGenre() { return genre; }

    // Setters:
    public void setGenre(ExpGenre genre) { this.genre = genre; }

    // EFFECTS: returns expense object in string form "Expense: $<value>"
    @Override
    public String toString() {
        return "Expense: $" + value + " - " + description + " (" + genre + ")";
    }
}
