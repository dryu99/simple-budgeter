package ui;

import model.Entry;
import model.Expense;
import model.Revenue;
import model.SimpleDate;
import model.enums.ExpGenre;
import model.enums.RevGenre;

// Entry creator through user input
public class EntryPrompter {
    private static EntryPrompter instance = new EntryPrompter();

    private Prompter prompter;

    // Constructor:
    private EntryPrompter() {
        prompter = Prompter.getInstance();
    }

    // EFFECTS: returns singular EntryPrompter instance
    public static EntryPrompter getInstance() {
        return instance;
    }

    // EFFECTS: New manager is created with a date prompted by the user.
    //          User is then prompted with the option to add additional transactions.
    //          Returns the manager after user decision is made.
    public Entry createEntry() {
        System.out.println("Please provide the new manager's date in number form.");
        SimpleDate date = prompter.returnUserSimpleDate();

        // TODO: maybe move this line of code to the top or bot of this method, just to make it cleaner. If you do this, you have to re-implement how to set a date.
        // New manager is created!
        Entry newEntry = new Entry(date);

        // TODO: maybe turn this code into a separate method
        while (true) {
            String command = prompter.returnUserCommand("Would you like to add a transaction to the manager? ");

                if (!handleEntryCommands(command, newEntry)) {
                    break;
                }
        }
        return newEntry;
    }

    // REQUIRES: e != null
    // EFFECTS: If "no" is given, return false. If "yes" is given, execute add transaction command and return true.
    //          ow print "That's not a real command!" and return true.
    private boolean handleEntryCommands(String command, Entry e) {
        switch (command) {
            case "no":
                return false;
            case "yes":
                System.out.println();
                addTransactionCommand(e);
                // TODO: decide if I want to implement the recursive call here to have the "would u like to add ANOTHER transaction" text appear.
//                addMoreTransactions(e);
                return true;
                // TODO: also think about making a TransactionPrompter class
            default:
                System.out.println("Please type 'yes' or 'no'.\n");
                return true;
        }
    }

    // TODO: maybe make anotehr command handler for this method (handleTransactionCommands)
    // REQUIRES: e != null
    // EFFECTS: Prompts user for transaction type and executes the respective add command
    private void addTransactionCommand(Entry e) {
        while (true) {
            System.out.println("What kind of transaction do you want to add?");
            System.out.println("[0] Revenue");
            System.out.println("[1] Expense\n");

            String command = prompter.returnUserCommand("User Command: ");
            System.out.println();

            if (command.equals("0")) {
                addRevenueCommand(e);
                break;
            } else if (command.equals("1")) {
                addExpenseCommand(e);
                break;
            } else {
                System.out.println("That's not a real command! Try again.\n");
            }
        }
    }

    // TODO: consider moving these methods into a transactionPrompter class that creates transaction objects and reutrns them. Then this method will actually add them. Similar to budgeterPrompter way of adding entries
    // TODO: also consider moving all "return" methods into a separate singleton return class
    // TODO: change expense/revenue attribute "description" to sth else, and change the prompt here
    // REQUIRES: e != null
    // MODIFIES: e
    // EFFECTS: prompts user for revenue amount, description, and genre, and adds it to the given manager.
    private void addRevenueCommand(Entry e) {
        System.out.println("--ADDING REVENUE TO ENTRY (" + e.getDate() + ")--");

        double amount = prompter.returnUserDouble("How much did you receive? ");
        String desc = prompter.returnUserCommand("Description: ");
        RevGenre genre = prompter.returnUserRevGenre();

        Revenue newRevenue = new Revenue(amount, desc, genre);

        System.out.println("**Revenue: " + newRevenue + " added to Entry (" + e.getDate() + ")**\n");
        e.addRevenue(newRevenue);
    }

    // REQUIRES: e != null
    // MODIFIES: e
    // EFFECTS: prompts user for expense amount, description, and genre, and adds it to the given manager.
    private void addExpenseCommand(Entry e) {
        System.out.println("--ADDING EXPENSE TO ENTRY (" + e.getDate() + ")--");

        double amount = prompter.returnUserDouble("How much did you spend? ");
        String desc = prompter.returnUserCommand("Description: ");
        ExpGenre genre = prompter.returnUserExpGenre();

        Expense newExpense = new Expense(amount, desc, genre);

        System.out.println("**Expense: " + newExpense + " added to Entry (" + e.getDate() + ")**\n");
        e.addExpense(newExpense);
    }

//        // TODO: does this needs modifies?
//    // EFFECTS: prompts user whether they want to add another transaction.
//    public void addMoreTransactions(Entry e) {
//        while (true) {
//            String command = returnUserCommand("Would you like to add another transaction? ");
//            System.out.println();
//
//            if (!handleEntryCommands(command, e)) {
//                break;
//            }
//        }
//    }
}
