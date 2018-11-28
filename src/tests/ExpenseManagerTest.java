package tests;

import model.Transaction;
import model.TransactionManager;
import model.date.SimpleDate;
import model.enums.ExpGenre;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// TODO might be able to delete this class
public class ExpenseManagerTest {
    private TransactionManager testManager;
    private SimpleDate testDate;

    @Before
    public void setup() {
        testManager = new TransactionManager(null);
        testDate = new SimpleDate(2018,2,20);

        testManager.addTransaction(new Transaction(-2,"mcds", ExpGenre.FOOD, testDate));
        testManager.addTransaction(new Transaction(-5,"beer", ExpGenre.DRINK, testDate));
        testManager.addTransaction(new Transaction(-25,"clothes", ExpGenre.SHOPPING, testDate));
    }

    @Test
    public void testToStringEmpty() {
        String managerString = "Expenses:\n" +
                "---------\n" +
                "(no expenses for this manager)\n";

        assertEquals(managerString, testManager.toString());
    }

    @Test
    public void testToString() {

        String managerString = "Expenses:\n" +
                "---------\n" +
                "$2.00 - mcds (FOOD)\n" +
                "$5.00 - beer (DRINK)\n" +
                "$25.00 - clothes (SHOPPING)\n";

        assertEquals(managerString, testManager.toString());
    }

}
