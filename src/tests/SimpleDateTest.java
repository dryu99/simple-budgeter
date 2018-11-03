package tests;

import model.SimpleDate;
import model.exceptions.InvalidDayException;
import model.exceptions.InvalidMonthException;

import model.exceptions.InvalidYearException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

// TODO: finish testing setters
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
    public void testSetMonthPass() {
        try {
            testDate.setMonth(1);
            testDate.setMonth(5);
            testDate.setMonth(12);
            assertEquals(12, testDate.getMonth());
        } catch (InvalidMonthException e) {
            fail();
        }
    }

    @Test (expected = InvalidMonthException.class)
    public void testSetMonthFailBelowOne() throws InvalidMonthException {
        testDate.setMonth(0);
    }

    @Test (expected = InvalidMonthException.class)
    public void testSetMonthFailAboveTwelve() throws InvalidMonthException {
        testDate.setMonth(14);
    }

    @Test
    public void testSetDayPass() {
        try {
            testDate.setDay(1);
            testDate.setDay(15);
            testDate.setDay(31);
            assertEquals(31, testDate.getDay());
        } catch (InvalidDayException e){
            fail();
        }
    }

    @Test (expected = InvalidDayException.class)
    public void testSetDayFailBelowOne() throws InvalidDayException {
        testDate.setDay(0);
    }

    @Test (expected = InvalidDayException.class)
    public void testSetDayFailAboveThirtyOne() throws InvalidDayException {
        testDate.setDay(32);
    }

    @Test
    public void testSetYearPass() {
        try {
            testDate.setYear(0);
            testDate.setYear(1);
            testDate.setYear(2019);
            assertEquals(2019, testDate.getYear());
        } catch (InvalidYearException e) {
            fail();
        }
    }

    @Test (expected = InvalidYearException.class)
    public void testSetYearFailBelowZero() throws InvalidYearException {
        testDate.setYear(-1);
    }

    @Test
    public void testToString() {
        assertEquals("2/20/1999", testDate.toString());
    }
}
