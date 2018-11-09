package tests;

import model.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BudgeterStringerTest {
    private List<Transaction> testTransactionList;

    @Before
    public void setup() {
        testTransactionList = new ArrayList<>();
    }

    @Test
    public void testBodyStringEmptyList() {

    }
}
