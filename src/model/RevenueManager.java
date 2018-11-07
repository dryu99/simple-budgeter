package model;

// CLASS: Grouped Revenue data and methods are stored here
public class RevenueManager extends TransactionManager {

    public RevenueManager(Entry e) {
        super(e);
    }

    // TODO: have to implement bidirectional removing
    // REQUIRES: r != null
    // MODIFIES: this
    // EFFECTS: if given expense is in expense list, remove it and return true, ow return false;
    public boolean removeRevenue(Revenue r) {
        if (transactionList.contains(r)) {
            transactionList.remove(r);
            return true;
        }
        return false;
    }

    // TODO: create a separate class for calculating stats?
    // EFFECTS: returns total revenues of this manager
    public double totalRevenue() {
        double totalRev = 0;

        for (Transaction r : transactionList) {
            totalRev += r.getValue();
        }
        return totalRev;
    }

    // TODO: is it preferred to write out entire string in one variable
    // EFFECTS: returns vertical list of revenues from revenue list.
    public String toString() {
        String revenuesString = EntryStringReturner.headerString("Revenues:", "-");
        revenuesString += EntryStringReturner.bodyString("revenues", transactionList);

        return revenuesString;
    }

}
