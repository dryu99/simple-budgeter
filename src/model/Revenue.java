package model;

// TODO: have to have transaction genre be an enum of an enum (separate into rev/exp genres)
// TODO: implement transaction description
// TODO: have transaction be an interface with revenue and expense classes that implement it?
// TODO: I want to have two separate genre enum classes, one for rev and exp.

public class Revenue implements Transaction {
    private RevGenre genre;
    private double value;

    public Revenue(double value, RevGenre genre) {
        this.value = value;
        this.genre = genre;
    }

    // Getters:
    @Override
    public double getValue() { return value; }

    // Setters:
    @Override
    public void setValue(double amount) {
        this.value = amount;
    }

    public RevGenre getGenre() { return genre; }

    @Override
    public String toString() {
        return "Revenue " + value;
    }



}
