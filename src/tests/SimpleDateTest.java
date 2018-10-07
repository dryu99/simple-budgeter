package tests;

import model.SimpleDate;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SimpleDateTest {
    private SimpleDate testDate;

    @Before
    public void setup() {
        testDate = new SimpleDate(2, 20, 1999);
    }

    @Test
    public void testConstructor() {
        assertEquals(testDate.getMonth(), 2);
        assertEquals(testDate.getDay(), 20);
        assertEquals(testDate.getYear(), 1999);
    }

    @Test
    public void testToString() {
        assertEquals("2/20/1999", testDate.toString());
    }
}
