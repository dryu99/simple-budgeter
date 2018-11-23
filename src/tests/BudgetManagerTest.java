package tests;

import model.BudgetManager;
import model.Entry;
import model.EntryManager;
import model.date.SimpleDate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

// TODO: how to test singleton classes
public class BudgetManagerTest {
    private BudgetManager testBudgetManager;

    @Before
    public void setUp() {
        testBudgetManager = new BudgetManager();
    }

    @Test
    public void testGetMonths() {
        assertTrue(testBudgetManager.createEntryFromDate("April 2018"));
        assertTrue(testBudgetManager.createEntryFromDate("February 2018"));
        assertTrue(testBudgetManager.createEntryFromDate("February 2019"));
        assertTrue(testBudgetManager.createEntryFromDate("January 2019"));

        List<String> expectedList = new ArrayList<>();
        expectedList.add("February 2018");
        expectedList.add("April 2018");
        expectedList.add("January 2019");
        expectedList.add("February 2019");

        assertEquals(expectedList, testBudgetManager.getMonths());
    }

    @Test
    public void testCreateEntryManFromDateSuccess() {
        createFebruaryEntryMan();
    }

    @Test
    public void testCreateEntryManFromDateUnsuccessAlreadyExists() {
        createFebruaryEntryMan();

        assertFalse(testBudgetManager.createEntryFromDate("February 2018"));
    }

    @Test
    public void addEntryMonthBucketAlreadyExists() {
        createFebruaryEntryMan();

        SimpleDate testDate = new SimpleDate(2018, 2, 20);
        Entry testEntry = new Entry(testDate);

        testBudgetManager.addTransaction(testEntry);

        // retrieves entry manager that should exist in the given date key
        EntryManager testEntryMan = testBudgetManager.getEntryFromDate(testDate.simpleFormat());

        assertTrue(testEntryMan.contains(testEntry));
    }

    @Test
    public void addEntryMonthBucketDoesntExists() {
        SimpleDate testDate = new SimpleDate(2018, 2, 20);
        Entry testEntry = new Entry(testDate);

        testBudgetManager.addTransaction(testEntry);

        EntryManager testEntryMan = testBudgetManager.getEntryFromDate(testDate.simpleFormat());

        assertTrue(testEntryMan.contains(testEntry));
    }

    private void createFebruaryEntryMan() {
        assertTrue(testBudgetManager.createEntryFromDate("February 2018"));
        assertTrue(testBudgetManager.getEntryFromDate("February 2018") != null);
    }


}
