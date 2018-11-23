package tests;

import model.*;
import model.date.SimpleDate;
import model.enums.ExpGenre;
import model.enums.RevGenre;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

// TODO: test setters?
public class TransactionTest {
    private final double DELTA = 10e-8;
    private SimpleDate testDate;
    private Transaction testRevenue;
    private Transaction testExpense;
    private TransactionManager testManager;

    @Before
    public void setup() {
        testDate = new SimpleDate(1999, 2, 20);

        testRevenue = new Transaction(30, "Save-On-Foods", RevGenre.PAYCHEQUE, testDate);
        testExpense = new Transaction(-20, "McDonalds", ExpGenre.FOOD, testDate);
        testManager = new RevenueManager(null);
    }

    @Test
    public void testConstructorRevenue() {
        assertEquals(30.0, testRevenue.getValue(), DELTA);
        assertEquals("Save-On-Foods", testRevenue.getDesc());
        assertEquals(RevGenre.PAYCHEQUE, testRevenue.getGenre());
    }

    @Test
    public void testConstructorExpense() {
        assertEquals(-20.0, testExpense.getValue(), DELTA);
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
        assertEquals(null, testRevenue.getManager());

        testRevenue.setManager(testManager);

        assertBiDirectionalAssociationTestTransactionAndTestManager(testRevenue);
    }

    @Test
    public void testSetTransactionManagerFromSthToNull() {
        assertEquals(null, testRevenue.getManager());

        testRevenue.setManager(testManager);
        assertBiDirectionalAssociationTestTransactionAndTestManager(testRevenue);

        testRevenue.setManager(null);

        // testExpense is no longer associated with a manager
        assertEquals(null, testRevenue.getManager());

        // testManager no longer contains the previously associated expense
        assertFalse(testManager.contains(testRevenue));
    }

    @Test
    public void testSetTransactionManagerFromSthToSth() {
        assertEquals(null, testRevenue.getManager());

        testRevenue.setManager(testManager);
        assertBiDirectionalAssociationTestTransactionAndTestManager(testRevenue);

        TransactionManager newManager = new RevenueManager(null);
        testRevenue.setManager(newManager);

        assertEquals(newManager, testRevenue.getManager());
        assertTrue(newManager.contains(testRevenue));

        assertFalse(testManager.contains(testRevenue));
    }

    @Test
    public void testToStringExpense() {
        assertEquals("$20.00 - McDonalds (FOOD)", testExpense.toString());
    }

    @Test
    public void testToStringRevenue() {
        assertEquals("$30.00 - Save-On-Foods (PAYCHEQUE)", testRevenue.toString());
    }

    // TODO: do i need to test situations where the compared object has slightly different content values (e.g. everything's the same except the genre)
    @Test
    public void testEquals() {
        Transaction testExpense2 = new Transaction(-20, "McDonalds", ExpGenre.FOOD, testDate);

        assertTrue(testExpense.equals(testExpense2));
    }

    @Test
    public void testHashCode() {
        Transaction testRevenue2 = new Transaction(30, "Save-On-Foods", RevGenre.PAYCHEQUE, testDate);

        assertEquals(testRevenue2.hashCode(), testRevenue.hashCode());
    }

    private void assertBiDirectionalAssociationTestTransactionAndTestManager(Transaction t) {
        assertEquals(testManager, t.getManager());
        assertTrue(testManager.contains(t));
    }



}
