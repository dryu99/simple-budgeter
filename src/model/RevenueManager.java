package model;

// CLASS: Grouped Revenue data and methods are stored here
public class RevenueManager extends TransactionManager {

    public RevenueManager(Entry e) {
        super(e);
    }

    // EFFECTS: returns vertical list of revenues from revenue list.
    public String toString() {
        String revenuesString = BudgeterStringer.underlinedHeaderString("Revenues:", "-");
        revenuesString += BudgeterStringer.bodyString("revenues", transactionList);

        return revenuesString;
    }

}
