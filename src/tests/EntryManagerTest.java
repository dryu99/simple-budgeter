package tests;

import model.Entry;
import model.EntryManager;

import model.SimpleDate;
import model.exceptions.NullParameterGiven;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EntryManagerTest {
    private EntryManager testEntryManager;
    private Entry testEntry;
    private SimpleDate testDate;

    @Before
    public void setup() {
        testEntryManager = EntryManager.getInstance();
        testDate = new SimpleDate(2, 20, 1999);
        testEntry = new Entry (testDate);
    }

    @Test
    public void testAddEntry() {
        assertEquals(0, testEntryManager.size());

        try {
            testEntryManager.addEntry(testEntry);
            assertEquals(1, testEntryManager.size());
        } catch (NullParameterGiven npg) {
            System.out.println("Null value was given");
        }
    }
}
