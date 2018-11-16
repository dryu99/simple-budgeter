package tests;

import model.RevenueManager;
import model.TransactionManager;
import model.statistics.TransactionManagerStats;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionManagerStatsTest {
    private final double DELTA = 10e-8;
    private TransactionManagerStats testStats;
    private TransactionManager testManager;

    @Before
    public void setup() {
        testManager = new RevenueManager(null);
        testStats = new TransactionManagerStats(testManager);
    }

    @Test
    public void testConstructor() {
        assertEquals(testManager, testStats.getHomeTransManager());
        assertEquals(0, testStats.getNumOfTransactions());
        assertEquals(0, testStats.getNetValue(), DELTA);
    }
}
