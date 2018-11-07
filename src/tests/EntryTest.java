package tests;

import model.*;
import model.enums.ExpGenre;
import model.enums.RevGenre;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntryTest {
    private Entry testEntry;
    private Revenue testRevenue;
    private Expense testExpense;
    private SimpleDate testDate;

    @Before
    public void setup() {
        testDate = new SimpleDate(2, 20, 1999);
        testEntry = new Entry(testDate);
        testRevenue = new Revenue(30, "Save-On Payroll", RevGenre.PAYCHEQUE);
        testExpense = new Expense(20, "McDonalds", ExpGenre.FOOD);

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
        assertEquals(0 , testEntry.totalRevenue(), 0.00001);

        testEntry.addRevenue(testRevenue);

        assertEquals(30, testEntry.totalRevenue(), 0.00001);
    }

    @Test
    public void testTotalExpenses() {
        // Checks that current manager revenue is 0
        assertEquals(0 , testEntry.totalExpenses(), 0.00001);

        testEntry.addExpense(testExpense);

        assertEquals(20, testEntry.totalExpenses(), 0.00001);
    }

    // TODO: can I test testTotalExpense and testTotalRevenue in here, or is it better to have separate tests
    @Test
    public void testNetValue() {
        assertEquals(0, testEntry.netValue(), 0.00001);

        testEntry.addRevenue(testRevenue);
        testEntry.addExpense(testExpense);

        assertEquals(30-20, testEntry.netValue(), 0.00001);
    }

    @Test
    public void testToCompleteString() {
        testEntry.addRevenue(testRevenue);
        testEntry.addExpense(testExpense);

        String completeString = testEntry.getId() + ". ENTRY (" + testEntry.getDate() + ")\n";
        completeString += "====================\n";
        completeString += "Revenues:\n---------\n";
        completeString += testRevenue.toString() +"\n\n";

        completeString += "Expenses:\n---------\n";
        completeString += testExpense.toString() + "\n\n";

        assertEquals(completeString, testEntry.toCompleteString());
    }


}
