package tests;

import model.Entry;
import model.EntryManager;
import model.date.SimpleDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// TODO: implement tests
public class EntryManagerTest {
    private EntryManager testEntryManager;
    private Entry testEntry;
    private SimpleDate testDate;

    @Before
    public void setup() {
        testEntryManager = new EntryManager();
        testDate = new SimpleDate(2, 20, 1999);
        testEntry = new Entry (testDate);
    }

    @Test
    public void testConstructor() {
        assertEquals("February 1999", testEntryManager.getDate());
        assertTrue(testEntryManager.isEmpty());
    }

    @Test
    public void testAddEntry() {
        testEntryManager.addEntry(testEntry);
        assertEquals(1, testEntryManager.size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddEntryFail() throws IllegalArgumentException {
            testEntryManager.addEntry(null);
    }


}
