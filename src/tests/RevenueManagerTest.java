package tests;

import model.Transaction;
import model.TransactionManager;
import model.date.SimpleDate;
import model.enums.RevGenre;
import org.junit.Before;

public class RevenueManagerTest {
    private TransactionManager testManager;
    private SimpleDate testDate;

    @Before
    public void setup() {
        testManager = new TransactionManager(null);
        testDate = new SimpleDate(2018,2,20);

        testManager.addTransaction(new Transaction(-2,"save-ons", RevGenre.PAYCHEQUE, testDate));
        testManager.addTransaction(new Transaction(-5,"yon iou", RevGenre.IOU,testDate));
        testManager.addTransaction(new Transaction(-25,"ta", RevGenre.PAYCHEQUE,testDate));
    }

    private void loadTransactions() {

    }
}
