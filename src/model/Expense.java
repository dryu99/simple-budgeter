package model;

public class Expense implements Transaction {
    private ExpGenre genre;
    private double value;

    public Expense(double value, ExpGenre genre) {
        this.value = value;
        this.genre = genre;
    }

    // Getters:
    @Override
    public double getValue() { return value; }

    @Override
    public void setValue(double amount) {
        this.value = amount;
    }

    public ExpGenre getGenre() { return genre; }

    @Override
    public String toString() {
        return "Expense " + value;
    }
}
