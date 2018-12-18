package model;

import model.date.SimpleDate;
import model.enums.Genre;

import java.text.DecimalFormat;

// CLASS: financial transaction
public class Transaction {
    private final DecimalFormat df = new DecimalFormat("#.00");
    private double value;
    private String description;
    private Genre genre;
    private SimpleDate date;
    private TransactionManager manager;

    // Constructor:

    // EFFECTS: creates a transaction with a value, description, and genre
    public Transaction(double value, String desc, Genre genre, SimpleDate date) {
        this.value = value;
        this.description = desc;
        this.genre = genre;
        this.date = date;
        manager = null;
    }

    // EFFECTS: creates a transaction with a value and description (no genre/date)
    public Transaction(double value, String desc) {
        this(value, desc, null, null);
    }

    // EFFECTS: creates a transaction with no description (used for ui tooltip setting)
    public Transaction(double value, Genre genre, SimpleDate date) {
        this(value, null, genre, date);
    }

    // Getters:
    public double getValue() { return value; }
    public String getDesc() { return description; }
    public Genre getGenre() { return genre; }
    public SimpleDate getDate() { return date; }
    public TransactionManager getManager() { return manager; }

    // Setters:
    public void setValue(double amount) { this.value = amount; }
    public void setDescription(String newDesc) { description = newDesc; }
    public void setGenre(Genre newGenre) { genre = newGenre; }

    // MODIFIES: this, Entry
    // EFFECTS: sets the manager the transaction belongs to and vice versa.
    //          as well, deletes the link the old manager had with this transaction
    public void setManager(TransactionManager newManager) {
        if (manager != null) {
            TransactionManager temp = manager;
            manager = null;

            temp.removeTransaction(this);
        }

        manager = newManager;

        if (newManager != null && !newManager.contains(this)) {
            newManager.addTransaction(this);
        }
    }

    // EFFECTS: returns true if given object is a transaction and contains the same contents (excluding desc)
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }

        if (this.getClass() != o.getClass()) { return false; }

        Transaction compared = (Transaction) o;

        if (value != compared.value) { return false; }

        if (genre != null ? !genre.equals(compared.genre) : compared.genre != null) {
            return false;
        }
        if (date != null ? !date.equals(compared.date) : compared.date != null) {
            return false;
        }
        return true;
    }

    // EFFECTS: returns unique number for transaction based on its contents
    @Override
    public int hashCode() {
        int result = (int) value * 100; // e.g. 20.25 -> 2025 (to avoid rounding when typecasting)
        result += genre != null ? genre.hashCode() : 0;
        result += date != null ? date.hashCode() : 0;

        return result;
    }


}
