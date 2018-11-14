package tests;

import model.*;
import model.date.SimpleDate;
import model.enums.ExpGenre;
import model.enums.RevGenre;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntryTest {
    private final double DELTA = 10e-8;
    private Entry testEntry;
    private Transaction testRevenue;
    private Transaction testExpense;

    @Before
    public void setup() {
        testEntry = new Entry(new SimpleDate(1999, 2, 20));
        testRevenue = new Transaction(30, "Save-On Payroll", RevGenre.PAYCHEQUE);
        testExpense = new Transaction(-20, "McDonalds", ExpGenre.FOOD);

    }

    @Test
    public void testAddRevenue() {
        assertEquals(0, testEntry.revenueListSize());

        testEntry.addRevenue(testRevenue);

        assertEquals(1, testEntry.revenueListSize());
    }

    @Test
    public void testAddExpense() {
        assertEquals(0, testEntry.expenseListSize());

        testEntry.addExpense(testExpense);

        assertEquals(1, testEntry.expenseListSize());
    }

    @Test
    public void testRemoveRevenue() {
        assertFalse(testEntry.removeRevenue(testRevenue));

        testEntry.addRevenue(testRevenue);

        assertTrue(testEntry.removeRevenue(testRevenue));
    }

    @Test
    public void testRemoveExpense() {
        assertFalse(testEntry.removeExpense(testExpense));

        testEntry.addExpense(testExpense);

        assertTrue(testEntry.removeExpense(testExpense));
    }

    @Test
    public void testRemoveFail() {
        assertFalse(testEntry.removeExpense(testExpense));

        testEntry.addRevenue(testRevenue);

        assertFalse(testEntry.removeExpense(testExpense));
    }

    @Test
    public void testTransactionListSize() {
        testEntry.addRevenue(testRevenue);
        testEntry.addExpense(testExpense);

        assertEquals(2, testEntry.transactionListSize());
    }

    @Test
    public void testTotalRevenue() {
        // Checks that current manager revenue is 0
        assertEquals(0 , testEntry.totalRevenue(), DELTA);

        testEntry.addRevenue(testRevenue);

        assertEquals(30, testEntry.totalRevenue(), DELTA);
    }

    @Test
    public void testTotalExpenses() {
        // Checks that current manager expense is 0
        assertEquals(0 , testEntry.totalExpenses(), DELTA);

        testEntry.addExpense(testExpense);

        assertEquals(-20, testEntry.totalExpenses(), DELTA);
    }

    // TODO: can I test testTotalExpense and testTotalRevenue in here, or is it better to have separate tests
    @Test
    public void testNetValue() {
        assertEquals(0, testEntry.netValue(), DELTA);

        testEntry.addRevenue(testRevenue);
        testEntry.addExpense(testExpense);

        assertEquals(30-20, testEntry.netValue(), DELTA);
    }

    @Test
    public void testToCompleteString() {
        testEntry.addRevenue(testRevenue);
        testEntry.addExpense(testExpense);

        String completeString = testEntry.getId() + ". ENTRY (" + testEntry.getDate() + ")\n";
        completeString += "===================\n";
        completeString += "Revenues:\n---------\n";
        completeString += testRevenue.toString() +"\n\n";

        completeString += "Expenses:\n---------\n";
        completeString += testExpense.toString() + "\n\n";

        assertEquals(completeString, testEntry.toCompleteString());
    }

    @Test
    public void testEquals() {
        SimpleDate testDate2 = new SimpleDate(1999, 2, 20);
        Entry testEntry2 = new Entry(testDate2);

        // TODO: id is auto incremented when object is created causing this test to fail
        assertTrue(testEntry.equals(testEntry2));
    }

    @Test
    public void testHashCode() {
        SimpleDate testDate2 = new SimpleDate(1999, 2, 20);
        Entry testEntry2 = new Entry(testDate2);

        // TODO: id is auto incremented when object is created causing this test to fail
        assertEquals(testEntry2.hashCode(), testEntry.hashCode());
    }

    // TODO: should i test sorting?
    @Test
    public void testCompareToReturnPositive() {
        Entry compared = new Entry(new SimpleDate(1999, 2, 19));

        assertTrue(testEntry.compareTo(compared) > 0);
    }

    @Test
    public void testCompareToReturnNegative() {
        Entry compared = new Entry(new SimpleDate(1999, 2, 21));

        assertTrue(testEntry.compareTo(compared) < 0);
    }






}
