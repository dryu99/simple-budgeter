package tests;

import model.ExpenseManager;
import model.RevenueManager;
import model.Transaction;
import model.TransactionManager;
import model.enums.RevGenre;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class RevenueManagerTest {
    private TransactionManager testManager;

    @Before
    public void setup() {
        testManager = new RevenueManager(null);
    }

    @Test
    public void testToStringEmpty() {
        TransactionManager testExpenseManager = new ExpenseManager(null);
        String managerString = "Expenses:\n" +
                "---------\n" +
                "(no expenses for this manager)\n";

        assertEquals(managerString,testExpenseManager.toString());
    }

    @Test
    public void testToString() {
        loadTransactions();

        String managerString = "Revenues:\n" +
                "---------\n" +
                "$2.00 - save-ons (PAYCHEQUE)\n" +
                "$5.00 - yon iou (IOU)\n" +
                "$25.00 - ta (PAYCHEQUE)\n";

        assertEquals(managerString, testManager.toString());
    }

    private void loadTransactions() {
        assertTrue(testManager.addTransaction(new Transaction(-2,"save-ons", RevGenre.PAYCHEQUE)));
        assertTrue(testManager.addTransaction(new Transaction(-5,"yon iou", RevGenre.IOU)));
        assertTrue(testManager.addTransaction(new Transaction(-25,"ta", RevGenre.PAYCHEQUE)));
    }
}
