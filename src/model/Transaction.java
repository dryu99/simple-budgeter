package model;

import model.enums.Genre;

public abstract class Transaction {
    protected double value;
    protected String description;
    protected Genre genre;
    protected Entry entry;

    public Transaction(double value, String desc, Genre genre) {
        this.value = value;
        this.description = desc;
        this.genre = genre;
        entry = null;
    }

    public Transaction() {
        value = 0;
        description = null;
        genre = null;
        entry = null;
    }

    // Getters:
    public double getValue() { return value; }
    public String getDesc() { return description; }
    public Genre getGenre() { return genre; }

    // TODO: make this throw a negative amount excpetion
    // Setters:
    public void setValue(double amount) { this.value = amount; }
    public void setDescription(String newDesc) { description = newDesc; }
    public void setGenre(Genre newGenre) { genre = newGenre; }

    // TODO: implement the bi-directional relationship functionality
    // MODIFIES: this, Entry
    // EFFECTS: sets the entry the transaction belongs to
    public abstract void setEntry(Entry newEntry);

    // EFFECTS: returns transaction object in string form "$<value> - <desc> (<genre>)"
    @Override
    public String toString() {
        return "$" + value + " - " + description + " (" + genre + ")";
    }

    // EFFECTS: returns true if given object is a transaction and contains the same contents
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }

        if (!(o instanceof Transaction)) { return false; }

        Transaction compared = (Transaction) o;

        if (value != compared.value) { return false; }

        if (description != null ? !description.equals(compared.description) : compared.description != null) {
            return false;
        }

        if (genre != null ? !genre.equals(compared.genre) : compared.genre != null) {
            return false;
        }

        return true;
    }

    // EFFECTS: returns unique number for transaction based on its contents
    @Override
    public int hashCode() {
        int result = (int) value * 100; // e.g. 20.25 -> 2025 (to avoid rounding when typecasting)
        result += description != null ? description.hashCode() : 0;
        result += genre != null ? genre.hashCode() : 0;

        return result;
    }


}
