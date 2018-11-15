package model;

// CLASS: holds only revenue data
public class RevenueManager extends TransactionManager {

    public RevenueManager(Entry e) {
        super(e);
    }

    @Override
    public String toString() {
        String revenuesString = BudgetStringer.underlinedHeaderString("Revenues:", "-");
        revenuesString += BudgetStringer.bodyString("revenues", transactionList);

        return revenuesString;
    }
}
