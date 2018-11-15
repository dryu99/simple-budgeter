package tests;

import model.ExpenseManager;
import model.Transaction;
import model.TransactionManager;
import model.enums.ExpGenre;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ExpenseManagerTest {
    private TransactionManager testManager;

    @Before
    public void setup() {
        testManager = new ExpenseManager(null);
    }

    @Test
    public void testToStringEmpty() {
        String managerString = "Expenses:\n" +
                "---------\n" +
                "(no expenses for this manager)\n";

        assertEquals(managerString, testManager.toString());
    }

    @Test
    public void testToString() {
        loadTransactions();

        String managerString = "Expenses:\n" +
                "---------\n" +
                "$2.00 - mcds (FOOD)\n" +
                "$5.00 - beer (DRINK)\n" +
                "$25.00 - clothes (SHOPPING)\n";

        assertEquals(managerString, testManager.toString());
    }

    private void loadTransactions() {
        assertTrue(testManager.addTransaction(new Transaction(-2,"mcds", ExpGenre.FOOD)));
        assertTrue(testManager.addTransaction(new Transaction(-5,"beer", ExpGenre.DRINK)));
        assertTrue(testManager.addTransaction(new Transaction(-25,"clothes", ExpGenre.SHOPPING)));
    }
}
