package tests;

import model.BudgetManager;
import model.Entry;
import model.EntryManager;
import model.date.SimpleDate;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

// TODO: how to test singleton classes
public class BudgetManagerTest {
    private BudgetManager testBudgetManager = BudgetManager.getInstance();

    @Test
    public void testCreateEntryManFromDateSuccess() {
        createFebruaryEntryMan();
    }

    @Test
    public void testCreateEntryManFromDateUnsuccessAlreadyExists() {
        createFebruaryEntryMan();

        assertFalse(testBudgetManager.createEntryManFromDate("February 2018"));
    }

    @Test
    public void addEntryMonthBucketAlreadyExists() {
        createFebruaryEntryMan();

        SimpleDate testDate = new SimpleDate(2018, 2, 20);
        Entry testEntry = new Entry(testDate);

        testBudgetManager.addEntry(testEntry);

        // retrieves entry manager that should exist in the given date key
        EntryManager testEntryMan = testBudgetManager.getEntryManagerFromDate(testDate.simpleFormat());

        assertTrue(testEntryMan.contains(testEntry));
    }

    @Test
    public void addEntryMonthBucketDoesntExists() {
        SimpleDate testDate = new SimpleDate(2018, 2, 20);
        Entry testEntry = new Entry(testDate);

        testBudgetManager.addEntry(testEntry);

        EntryManager testEntryMan = testBudgetManager.getEntryManagerFromDate(testDate.simpleFormat());

        assertTrue(testEntryMan.contains(testEntry));
    }

    private void createFebruaryEntryMan() {
        assertTrue(testBudgetManager.createEntryManFromDate("February 2018"));
        assertTrue(testBudgetManager.getEntryManagerFromDate("February 2018") != null);
    }


}
