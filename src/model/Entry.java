package model;

public class Entry {
    private static int nextEntryId = 1;
    private int id;
    private SimpleDate date;
//    private List<Transaction> transactionList;
    // TODO: do i want to make the apparent classes ALWAYS be as abstract as possible??? or should I be making separate unique methods for each subclass (i.e. addExpense addRevenue). I could also ovverride the addTransaction method in each class
    private RevenueManager revenueManager;
//    private List<Transaction> expenseList;
    private ExpenseManager expenseManager;

    public Entry(SimpleDate date) {
        id = nextEntryId++;
        this.date = date;
        revenueManager = new RevenueManager(this);
        expenseManager = new ExpenseManager(this);
    }

    // Getters:
    public int getId() { return id; }
    public SimpleDate getDate() { return date; }

    // Setters:
    public void setDate(SimpleDate date) { this.date = date; }

    // EFFECTS: returns size of revenue list
    public int revenueListSize() {
        return revenueManager.size();
    }

    // EFFECTS: returns size of expense list
    public int expenseListSize() { return expenseManager.size(); }

    // EFFECTS: returns size of revenue + expense list
    public int transactionListSize() { return revenueListSize() + expenseListSize(); }

    // TODO: add transactions like this? will get rid of duplication, but want to avoid typecasting
//    public void addTransaction(Transaction t) {
//        if (t == null) {
//            throw new NullParameterGiven();
//        }
//
//        if (t instanceof Revenue) {
//            addTransaction((Revenue) t);
//        } else if (t instanceof Expense) {
//            addExpense((Expense) t);
//        }
//    }

    // MODIFIES: this
    // EFFECTS: add revenue to revenue list
    public void addRevenue(Revenue r) {
        revenueManager.addTransaction(r);
    }

    // MODIFIES: this
    // EFFECTS: add expense to expense list
    public void addExpense(Expense e) {
        expenseManager.addTransaction(e);
    }

    // TODO: even if I know that this modfies r, do I need to put in MODIFIES clause? Also do i need to put the REQUIRES for r != null?
    // MODIFIES: this
    // EFFECTS: if given expense is in expense list, remove it and return true, ow return false;
    public boolean removeRevenue(Revenue r) {
        return revenueManager.removeTransaction(r);
    }

    // REQUIRES: e != null
    // MODIFIES: this
    // EFFECTS: if given expense is in expense list, remove it and return true, ow return false;
    public boolean removeExpense(Expense e) {
        return expenseManager.removeTransaction(e);
    }

    // TODO: create a separate class for calculating stats?
    // EFFECTS: returns total revenues of this manager
    public double totalRevenue() {
        return revenueManager.totalValue();
    }

    // EFFECTS: returns total expenses of this manager
    public double totalExpenses() {
        return expenseManager.totalValue();
    }

    // EFFECTS: returns net value of this manager (revenues - expenses)
    public double netValue() {
        double netVal = totalRevenue() - totalExpenses();

        return netVal;
    }

    @Override
    // EFFECTS: returns manager in string form
    public String toString() {
        return id + ". ENTRY " + "(" + date + ")";
    }

    public String toCompleteString() {
        String completeString = BudgeterStringer.underlinedHeaderString(this.toString(), "=");

        completeString += revenueManager.toString() + "\n";
        completeString += expenseManager.toString() + "\n";

        return completeString;
    }

    // EFFECTS: returns true if manager has same id and date as compared
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }

        if (!(o instanceof Entry)) { return false; }

        Entry compared = (Entry) o;

        if (id != compared.id) { return false; }

        if (date != null ? !date.equals(compared.date) : compared.date != null) {
            return false;
        }

        return true;
    }

    // EFFECTS: returns unique id based on the manager's id and date
    @Override
    public int hashCode() {
        int result = id * 7;
        result += date != null ? date.hashCode() : 0;

        return result;
    }











}
