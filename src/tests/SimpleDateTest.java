package tests;

import model.date.SimpleDate;
import model.exceptions.InvalidDateException;
import model.exceptions.InvalidDayException;
import model.exceptions.InvalidMonthException;
import model.exceptions.InvalidYearException;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class SimpleDateTest {
    private SimpleDate testDate;

    @Before
    public void setup() {
        testDate = new SimpleDate(1999, 2, 20);
    }

    @Test
    public void testConstructorArgs() {
        assertEquals(testDate.get(Calendar.YEAR), 1999);
        assertEquals(testDate.get(Calendar.MONTH), 2);
        assertEquals(testDate.get(Calendar.DATE), 20);

        assertEquals("February 1999", testDate.getSimpleFormat());
    }

    @Test
    public void testSetMonthPass() {
        try {
            testDate.set(Calendar.MONTH, 1);
            testDate.set(Calendar.MONTH, 5);
            testDate.set(Calendar.MONTH, 12);
            assertEquals(12, testDate.get(Calendar.MONTH));
        } catch (InvalidDateException e) {
            fail();
        }
    }

    @Test (expected = InvalidMonthException.class)
    public void testSetMonthFailBelowOne() throws InvalidDateException {
        testDate.set(Calendar.MONTH,0);
    }

    @Test (expected = InvalidMonthException.class)
    public void testSetMonthFailAboveTwelve() throws InvalidDateException {
        testDate.set(Calendar.MONTH,14);
    }

    @Test
    public void testSetDayPass() {
        try {
            testDate.set(Calendar.DATE,1);
            testDate.set(Calendar.DATE,15);
            testDate.set(Calendar.DATE,28);
            assertEquals(28, testDate.get(Calendar.DATE));
        } catch (InvalidDateException e){
            fail();
        }
    }

    @Test (expected = InvalidDayException.class)
    public void testSetDayFailBelowOne() throws InvalidDateException {
        testDate.set(Calendar.DATE,0);
    }

    @Test (expected = InvalidDayException.class)
    public void testSetDayFailAboveThirtyOne() throws InvalidDateException {
        testDate.set(Calendar.DATE,32);
    }

    @Test
    public void testSetYearPass() {
        try {
            testDate.set(Calendar.YEAR,0);
            testDate.set(Calendar.YEAR,1);
            testDate.set(Calendar.YEAR,2019);
            assertEquals(2019, testDate.get(Calendar.YEAR));
        } catch (InvalidDateException e) {
            fail();
        }
    }

    @Test (expected = InvalidYearException.class)
    public void testSetYearFailBelowZero() throws InvalidDateException {
        testDate.set(Calendar.YEAR,-1);
    }

    @Test
    public void testToString() {
        assertEquals("20/02/99", testDate.toString());
    }


}
