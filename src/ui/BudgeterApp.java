package ui;

import java.io.IOException;

// TODO: is it always necessary to try-catch for null parameters? (e.g. setDate in Entry class) can i just have a runtime exceptoin for that one
public class BudgeterApp {
    public static void main(String[] args) throws IOException {
        BudgeterPrompter budgeter = new BudgeterPrompter();

        budgeter.start();

    }
}
