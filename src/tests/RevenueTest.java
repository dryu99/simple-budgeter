package tests;

import model.RevGenre;
import model.Revenue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RevenueTest {
    private Revenue testRevenue;

    @Before
    public void setup() {
        testRevenue = new Revenue(20.0, RevGenre.WORK);
    }

    @Test
    public void testConstructor() {
        assertEquals(20.0, testRevenue.getValue(), 0.000001);
        assertEquals(RevGenre.WORK, testRevenue.getGenre());
    }
}
