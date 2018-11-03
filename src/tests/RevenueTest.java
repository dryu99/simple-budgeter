package tests;

import model.Transaction;
import model.enums.RevGenre;
import model.Revenue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RevenueTest {
    private Transaction testRevenue;

    @Before
    public void setup() {
        testRevenue = new Revenue(20.0, "Save-Ons Payroll", RevGenre.PAYCHEQUE);
    }

    @Test
    public void testConstructor() {
        assertEquals(20.0, testRevenue.getValue(), 0.000001);
        assertEquals("Save-Ons Payroll", testRevenue.getDesc());
        assertEquals(RevGenre.PAYCHEQUE, testRevenue.getGenre());
    }

    @Test
    public void testToString() {
        assertEquals("$20.00 - Save-Ons Payroll (PAYCHEQUE)", testRevenue.toString());
    }


}
