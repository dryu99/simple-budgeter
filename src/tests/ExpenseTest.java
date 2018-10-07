package tests;

import model.Expense;
import model.enums.ExpGenre;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExpenseTest {
    private Expense testExpense;

    @Before
    public void setup() {
        testExpense = new Expense(20, "McDonalds", ExpGenre.FOOD);
    }

    @Test
    public void testConstructor() {
        assertEquals(20.0, testExpense.getValue(), 0.000001);
        assertEquals("McDonalds", testExpense.getDesc());
        assertEquals(ExpGenre.FOOD, testExpense.getGenre());
    }
}
