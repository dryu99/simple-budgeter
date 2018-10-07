package model;

public abstract class Transaction {
    protected double value;
    protected String description;

    public Transaction(double value, String desc) {
        this.value = value;
        this.description = desc;
    }

    public Transaction() {
        value = 0;
        description = null;
    }

    // Getters:
    public double getValue() { return value; }
    public String getDesc() { return description; }

    // Setters:
    public void setValue(double amount) {
        this.value = amount;
    }
    public void setDescription(String newDesc) { description = newDesc; }

}
