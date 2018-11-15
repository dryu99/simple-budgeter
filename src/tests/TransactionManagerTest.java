package tests;

import model.ExpenseManager;
import model.RevenueManager;
import model.Transaction;
import model.TransactionManager;
import model.enums.RevGenre;
import model.exceptions.NullParameterGiven;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

// TODO: seems like im testing redundant methods, as things like addtransaction and size are also tested in EntryTest. What do?
// TODO: test a rev manager and exp manager both (YES) test all transactionMan methods in one class, and tostring methods in two other test classes for rev/exp man
public class TransactionManagerTest {
    private final double DELTA = 10e-8;
    private TransactionManager testManager;

    @Before
    public void setup() {
        testManager = new RevenueManager(null);
    }

    @Test
    public void testConstructor() {

    }

    @Test
    public void testSize() {
        assertEquals(0, testManager.size());

        loadTransactions();

        assertEquals(3, testManager.size());
    }

    @Test
    public void testContains() {
        Transaction testTransaction = new Transaction(2,"save-ons", RevGenre.PAYCHEQUE);
        assertFalse(testManager.contains(testTransaction));

        loadTransactions();

        assertTrue(testManager.contains(testTransaction));
    }

    @Test
    public void testTotalValue() {
        assertEquals(0, testManager.totalValue(), DELTA);
        loadTransactions();
        assertEquals(32, testManager.totalValue(), DELTA);
    }

    @Test
    public void testAddDuplicateTransaction() {
        loadTransactions();
        assertFalse(testManager.addTransaction(new Transaction(2,"save-ons", RevGenre.PAYCHEQUE)));
    }

    @Test (expected = NullParameterGiven.class)
    public void testAddTransactionFailNullParameter() throws NullParameterGiven {
        testManager.addTransaction(null);
    }

    @Test
    public void testRemoveTransactionSucess() {
        loadTransactions();
        assertTrue(testManager.removeTransaction(new Transaction(2,"save-ons", RevGenre.PAYCHEQUE)));
    }

    @Test
    public void testRemoveUnsuccessNonExistentTransaction() {
        loadTransactions();
        assertFalse(testManager.removeTransaction(new Transaction(200,"save-ons", RevGenre.PAYCHEQUE)));
    }

    @Test (expected = NullParameterGiven.class)
    public void testRemoveTransactionFailNullParamater() throws NullParameterGiven {
        testManager.removeTransaction(null);
    }

    @Test
    public void testToStringEmptyRevenueManager() {
        String managerString = "Revenues:\n" +
                "---------\n" +
                "(no revenues for this manager)\n";

        assertEquals(managerString, testManager.toString());
    }

    @Test
    public void testToStringEmptyExpenseManager() {
        TransactionManager testExpenseManager = new ExpenseManager(null);
        String managerString = "Expenses:\n" +
                "---------\n" +
                "(no expenses for this manager)\n";

        assertEquals(managerString,testExpenseManager.toString());
    }

    @Test
    public void testToStringRevenueManager() {
        loadTransactions();

        String managerString = "Revenues:\n" +
                "---------\n" +
                "$2.00 - save-ons (PAYCHEQUE)\n" +
                "$5.00 - yon iou (IOU)\n" +
                "$25.00 - ta (PAYCHEQUE)\n";

        assertEquals(managerString, testManager.toString());
    }

    // TODO: do this one, you prob want to think about making another private field to represent an expense manager
    @Test
    public void testToStringExpenseManager() {

    }


    private void loadTransactions() {
        assertTrue(testManager.addTransaction(new Transaction(2,"save-ons", RevGenre.PAYCHEQUE)));
        assertTrue(testManager.addTransaction(new Transaction(5,"yon iou", RevGenre.IOU)));
        assertTrue(testManager.addTransaction(new Transaction(25,"ta", RevGenre.PAYCHEQUE)));
    }

}
