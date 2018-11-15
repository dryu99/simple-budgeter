package model;

// CLASS: holds only expense data
public class ExpenseManager extends TransactionManager {

    public ExpenseManager(Entry e) {
        super(e);
    }

    @Override
    public String toString() {
        String expensesString = BudgetStringer.underlinedHeaderString("Expenses:", "-");
        expensesString += BudgetStringer.bodyString("expenses", transactionList);

        return expensesString;
    }
}
