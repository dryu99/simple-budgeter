package tests;

import model.Entry;
import model.Transaction;
import model.TransactionManager;
import model.date.SimpleDate;
import model.enums.RevGenre;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

// TODO need to test rest of methods in this class
// TODO: seems like im testing redundant methods, as things like addtransaction and size are also tested in EntryTest. What do?
public class TransactionManagerTest {
    private final double DELTA = 10e-8;
    private TransactionManager testManager;

    @Before
    public void setup() {
        testManager = new TransactionManager(new Entry("February 2018"));
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testManager.size());
    }

    @Test
    public void testContains() {
        Transaction testTransaction = new Transaction(2,"save-ons", RevGenre.PAYCHEQUE, new SimpleDate(2018,2,20));
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
    public void testAddTransactionSuccess() {
        assertTrue(testManager.addTransaction(new Transaction(2, "save-ons", RevGenre.PAYCHEQUE, new SimpleDate(2018,2,20))));
    }

    @Test
    public void testAddDuplicateTransaction() {
        loadTransactions();
        assertFalse(testManager.addTransaction(new Transaction(2,"save-ons", RevGenre.PAYCHEQUE, new SimpleDate(2018,2,20))));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddTransactionFailIllegalArg() throws IllegalArgumentException {
        testManager.addTransaction(null);
    }

    @Test
    public void testRemoveTransactionSuccess() {
        loadTransactions();
        assertTrue(testManager.removeTransaction(new Transaction(2,"save-ons", RevGenre.PAYCHEQUE, new SimpleDate(2018,2,20))));
    }

    @Test
    public void testRemoveUnsuccessNonExistentTransaction() {
        loadTransactions();
        assertFalse(testManager.removeTransaction(new Transaction(200,"save-ons", RevGenre.PAYCHEQUE, new SimpleDate(2018,2,20))));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveTransactionFailIllegalArg() throws IllegalArgumentException {
        testManager.removeTransaction(null);
    }

    private void loadTransactions() {
        assertTrue(testManager.addTransaction(new Transaction(2,"save-ons", RevGenre.PAYCHEQUE, new SimpleDate(2018,2,20))));
        assertTrue(testManager.addTransaction(new Transaction(5,"yon iou", RevGenre.IOU, new SimpleDate(2018,2,20))));
        assertTrue(testManager.addTransaction(new Transaction(25,"ta", RevGenre.PAYCHEQUE, new SimpleDate(2018,2,20))));
    }

}
