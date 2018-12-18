package tests;

import model.Transaction;
import model.TransactionManager;
import model.date.SimpleDate;
import model.enums.ExpGenre;
import org.junit.Before;

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

}
