package tests;

import model.ExpGenre;
import model.Expense;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExpenseTest {
    private Expense testExpense;

    @Before
    public void setup() {
        testExpense = new Expense(20, ExpGenre.FOOD);
    }

    @Test
    public void testConstructor() {
        assertEquals(20.0, testExpense.getValue(), 0.000001);
        assertEquals(ExpGenre.FOOD, testExpense.getGenre());
    }
}
