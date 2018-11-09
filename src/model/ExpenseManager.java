package model;

// CLASS: Grouped Expense data and methods are stored here
public class ExpenseManager extends TransactionManager {

    public ExpenseManager(Entry e) {
        super(e);
    }

    // EFFECTS: returns vertical list of expenses from expense list.
    @Override
    public String toString() {
        String expensesString = BudgeterStringer.underlinedHeaderString("Expenses:", "-");
        expensesString += BudgeterStringer.bodyString("expenses", transactionList);

        return expensesString;
    }

}
