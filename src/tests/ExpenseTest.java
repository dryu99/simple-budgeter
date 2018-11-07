package tests;

import model.*;
import model.enums.ExpGenre;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

// TODO: test setters?
public class ExpenseTest {
    private Transaction testExpense;
    private TransactionManager testManager;

    @Before
    public void setup() {
        testExpense = new Expense(20, "McDonalds", ExpGenre.FOOD);
        testManager = new ExpenseManager(null);
    }

    @Test
    public void testConstructor() {
        assertEquals(20.0, testExpense.getValue(), 0.000001);
        assertEquals("McDonalds", testExpense.getDesc());
        assertEquals(ExpGenre.FOOD, testExpense.getGenre());
    }

    @Test
    public void testSetTransactionManagerFromNullToNull() {
        assertEquals(null, testExpense.getManager());

        testExpense.setManager(null);

        assertEquals(null, testExpense.getManager());
    }

    @Test
    public void testSetTransactionManagerFromNullToSth() {
        assertEquals(null, testExpense.getManager());

        testExpense.setManager(testManager);

        assertEquals(testManager, testExpense.getManager());
        assertTrue(testManager.contains(testExpense));
    }

    @Test
    public void testSetTransactionManagerFromSthToNull() {
        assertEquals(null, testExpense.getManager());

        testExpense.setManager(testManager);
        // TODO: make a helper?
        assertEquals(testManager, testExpense.getManager());
        assertTrue(testManager.contains(testExpense));

        testExpense.setManager(null);

        // testExpense is no longer associated with a manager
        assertEquals(null, testExpense.getManager());

        // testManager no longer contains the previously associated expense
        assertFalse(testManager.contains(testExpense));
    }

    @Test
    public void testSetTransactionManagerFromSthToSth() {
        assertEquals(null, testExpense.getManager());

        testExpense.setManager(testManager);
        assertEquals(testManager, testExpense.getManager());
        assertTrue(testManager.contains(testExpense));

        TransactionManager newManager = new ExpenseManager(null);
        testExpense.setManager(newManager);

        assertEquals(newManager, testExpense.getManager());
        assertTrue(newManager.contains(testExpense));

        assertFalse(testManager.contains(testExpense));
    }

    @Test
    public void testToString() {
        assertEquals("$20.00 - McDonalds (FOOD)", testExpense.toString());
    }



}
