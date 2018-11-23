package tests;

import model.BudgetManager;
import model.Entry;
import model.Transaction;
import model.date.SimpleDate;
import model.enums.RevGenre;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

// TODO test other methods in the class
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
        assertTrue(testBudgetManager.createEntryFromDate("December 2018"));
        assertTrue(testBudgetManager.createEntryFromDate("February 2019"));
        assertTrue(testBudgetManager.createEntryFromDate("January 2019"));
        assertTrue(testBudgetManager.createEntryFromDate("May 2019"));

        List<String> expectedList = new ArrayList<>();
        expectedList.add("February 2018");
        expectedList.add("April 2018");
        expectedList.add("December 2018");
        expectedList.add("January 2019");
        expectedList.add("February 2019");
        expectedList.add("May 2019");

        assertEquals(expectedList, testBudgetManager.getMonths());
    }

    @Test
    public void testCreateEntryManFromDateSuccess() {
        createFebruaryEntry();
    }

    @Test
    public void testCreateEntryManFromDateUnsuccessAlreadyExists() {
        createFebruaryEntry();

        assertFalse(testBudgetManager.createEntryFromDate("February 2018"));
    }

    @Test
    public void addEntryMonthBucketAlreadyExists() {
        createFebruaryEntry();

        SimpleDate testDate = new SimpleDate(2018, 2, 20);
        Transaction testTransaction = new Transaction(20,"work", RevGenre.PAYCHEQUE, testDate);

        testBudgetManager.addTransaction(testTransaction);

        // retrieves entry that should exist in the given date key
        Entry testEntry = testBudgetManager.getEntryFromDate(testDate.simpleFormat());

        assertTrue(testEntry.contains(testTransaction));
    }

    @Test
    public void addEntryMonthBucketDoesntExist() {
        SimpleDate testDate = new SimpleDate(2018, 2, 20);
        Transaction testTransaction = new Transaction(20,"work", RevGenre.PAYCHEQUE, testDate);

        testBudgetManager.addTransaction(testTransaction);

        Entry testEntry = testBudgetManager.getEntryFromDate(testDate.simpleFormat());

        assertTrue(testEntry.contains(testTransaction));
    }

    private void createFebruaryEntry() {
        assertTrue(testBudgetManager.createEntryFromDate("February 2018"));
        assertTrue(testBudgetManager.getEntryFromDate("February 2018") != null);
    }


}
