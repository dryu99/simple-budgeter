package tests;

import model.*;
import model.date.SimpleDate;
import model.enums.ExpGenre;
import model.enums.RevGenre;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// TODO test rest of methods in this class
// TODO because some tests are very similar because theyre just higher level call equivalents of the lower class, do i still have to test all those similar methods?
public class EntryTest {
    private final double DELTA = 10e-8;
    private Entry testEntry;
    private Transaction testRevenue;
    private Transaction testExpense;

    @Before
    public void setup() {
        testEntry = new Entry("February 2018");
        testRevenue = new Transaction(30, "Save-On Payroll", RevGenre.PAYCHEQUE, new SimpleDate(2018, 2, 20));
        testExpense = new Transaction(-20, "McDonalds", ExpGenre.FOOD, new SimpleDate(2018,2,25));
    }

    @Test
    public void testAddRevenue() {
        assertEquals(0, testEntry.revenueListSize());

        testEntry.addTransaction(testRevenue);

        assertEquals(1, testEntry.revenueListSize());
    }

    @Test
    public void testAddExpense() {
        assertEquals(0, testEntry.expenseListSize());

        testEntry.addTransaction(testExpense);

        assertEquals(1, testEntry.expenseListSize());
    }

    @Test
    public void testRemoveRevenue() {
        assertFalse(testEntry.removeTransaction(testRevenue));

        testEntry.addTransaction(testRevenue);

        assertTrue(testEntry.removeTransaction(testRevenue));
    }

    @Test
    public void testRemoveExpense() {
        assertFalse(testEntry.removeTransaction(testExpense));

        testEntry.addTransaction(testExpense);

        assertTrue(testEntry.removeTransaction(testExpense));
    }

    @Test
    public void testRemoveFail() {
        assertFalse(testEntry.removeTransaction(testExpense));

        testEntry.addTransaction(testRevenue);

        assertFalse(testEntry.removeTransaction(testExpense));
    }

    @Test
    public void testTransactionListSize() {
        testEntry.addTransaction(testRevenue);
        testEntry.addTransaction(testExpense);

        assertEquals(2, testEntry.transactionListSize());
    }

    @Test
    public void testTotalRevenue() {
        // Checks that current manager revenue is 0
        assertEquals(0 , testEntry.totalRevenue(), DELTA);

        testEntry.addTransaction(testRevenue);

        assertEquals(30, testEntry.totalRevenue(), DELTA);
    }

    @Test
    public void testTotalExpenses() {
        // Checks that current manager expense is 0
        assertEquals(0 , testEntry.totalExpenses(), DELTA);

        testEntry.addTransaction(testExpense);

        assertEquals(-20, testEntry.totalExpenses(), DELTA);
    }

    // TODO: can I test testTotalExpense and testTotalRevenue in here, or is it better to have separate tests
    @Test
    public void testNetValue() {
        assertEquals(0, testEntry.netValue(), DELTA);

        testEntry.addTransaction(testRevenue);
        testEntry.addTransaction(testExpense);

        assertEquals(30-20, testEntry.netValue(), DELTA);
    }

    @Test
    public void testEquals() {
        Entry testEntry2 = new Entry("February 2018");

        assertTrue(testEntry.equals(testEntry2));
    }

    @Test
    public void testHashCode() {
        Entry testEntry2 = new Entry("February 2018");

        assertEquals(testEntry2.hashCode(), testEntry.hashCode());
    }

//    // TODO: keep tests?
//    @Test
//    public void testCompareToReturnPositive() {
//        Entry compared = new Entry(new SimpleDate(1999, 2, 19));
//
//        assertTrue(testEntry.compareTo(compared) > 0);
//    }
//
//    @Test
//    public void testCompareToReturnNegative() {
//        Entry compared = new Entry(new SimpleDate(1999, 2, 21));
//
//        assertTrue(testEntry.compareTo(compared) < 0);
//    }






}
