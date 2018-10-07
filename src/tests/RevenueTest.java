package tests;

import model.enums.RevGenre;
import model.Revenue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RevenueTest {
    private Revenue testRevenue;

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
}
