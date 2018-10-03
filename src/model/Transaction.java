package model;

public interface Transaction {

    // EFFECTS: returns value of transaction
    double getValue();

    // MODIFIES: this
    // EFFECTS: sets value of transaction to amount
    void setValue(double amount);

}
