package model;

import model.enums.Genre;

// CLASS: financial transaction
public class Transaction {
    private double value; // +value = revenue, -value = expense
    private String description;
    private Genre genre;
    private TransactionManager manager;

    // Constructor:
    public Transaction(double value, String desc, Genre genre) {
        this.value = value;
        this.description = desc;
        this.genre = genre;
        manager = null;
    }

    // Getters:
    public double getValue() { return value; }
    public String getDesc() { return description; }
    public Genre getGenre() { return genre; }
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
            manager.addTransaction(this);
        }
    }

    // EFFECTS: returns transaction object in string form "$<value> - <desc> (<genre>)"
    @Override
    public String toString() {
        double value = this.value < 0 ? this.value * -1 : this.value;

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
