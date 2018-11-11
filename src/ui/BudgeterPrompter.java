package ui;

// TODO: remove import statements that you dont need (i.e. the test ones)
// TODO: turn this into a singleton?
import model.*;
import model.date.SimpleDate;
import model.enums.ExpGenre;
import model.enums.RevGenre;

// Budgeting application UI, prompting user for app commands upon initialization
public class BudgeterPrompter {
    private Prompter prompter;
    private EntryPrompter entryPrompter;
    private EntryManager entryManager;

    // Constructor
    public BudgeterPrompter() {
        prompter = Prompter.getInstance();
        entryPrompter = EntryPrompter.getInstance();
        entryManager = EntryManager.getInstance();
    }

    // EFFECTS: Starts budgeting app, and prompts user for a command.
    public void start() {
        initialize();

        System.out.println("BUDGETING APPLICATION\n");

        while (true) {
            System.out.println("What would you like to do?");
            printOptions();

            // TODO: do I want to throw an exception if the incorrect user command is given? not sure if i should since there are a distinct number of options
            String command = prompter.returnUserCommand("User Command: ");
            System.out.println();

            if (!handleBudgeterCommands(command)) {
                break;
            }
        }
    }

    // TODO: is it worth making an interface "CommandHandler" with only one method in it
    // EFFECTS: If valid command is given, execute it and return true. ow print "That's not a real command!" and return true
    //          If command is "exit" return false instead.
    private boolean handleBudgeterCommands(String command) {
        switch (command) {
            case "exit":
                System.out.println("See you later!");
                return false;
            case "0":
                addEntryCommand();
                return true;
            case "1":
                viewEntriesCommand();
                return true;
            case "2":
                editEntryCommand();
                default:
                    System.out.println("That's not a real command! Try again.\n");
                    return true;
        }
    }

    // TODO: modifies EntryManager or this?
    // MODIFIES: EntryManager
    // EFFECTS: Creates and adds new Entry made through user input into entry manager
    private void addEntryCommand() {
        System.out.println(BudgeterStringer.dashedHeaderString("ADDING ENTRY"));

        Entry newEntry = entryPrompter.createEntry();
//        entryPrompter.addMoreTransactions(newEntry);

        // TODO: make sure it prints out the manager list size too
        System.out.println("**New Entry (" + newEntry.getDate() + ") successfully added with "
                + newEntry.transactionListSize() + " transactions**\n");

        entryManager.addEntry(newEntry);
    }

    // EFFECTS: prints out transactions
    private void viewEntriesCommand() {
        if (entryManager.isEmpty()) {
            System.out.println("No entries have been written yet!");
            return;
        }

        System.out.println(BudgeterStringer.dashedHeaderString("RECORDED ENTRIES"));

        for (Entry e : entryManager.getEntryList()) {
            System.out.println(e.toCompleteString());
        }
    }

    // MODIFIES: EntryManager //TODO: or this?
    // EFFECTS: edits
    private void editEntryCommand() {
        System.out.println(BudgeterStringer.dashedHeaderString("EDITING ENTRY"));


    }

    // EFFECTS: prints out budget application options
    private void printOptions() {
        System.out.println("[0] Add a new Entry");
        System.out.println("[1] View all Entries");
        System.out.println("[exit] Exit application");
        System.out.println();
    }

    // TODO: does this method modify 'this' or 'entryManager'
    // MODIFIES: this
    // EFFECTS: initialize test manager for entryManager
    private void initialize() {
        Entry testEntry = new Entry(new SimpleDate(1999, 2, 20));
        Transaction revenue = new Transaction(10, "ta payroll", RevGenre.PAYCHEQUE);
        Transaction expense = new Transaction(20, "mcds", ExpGenre.FOOD);
        testEntry.addRevenue(revenue);
        testEntry.addExpense(expense);
        entryManager.addEntry(testEntry);
    }

    // TODO: when implementing save/load functionality, put load() and save() at beginning and break; of enter()
//    @Override
//    public void load() throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get("savedtransactionsfile.txt"));
//        for (String line : lines) {
//
//            ArrayList<String> partsOfLine = splitOnSpace(line);
//
//            if (partsOfLine.get(0).equals("Revenue")) {
//                Revenue revenue = new Revenue(Double.parseDouble(partsOfLine.get(1)), "Save-On Payroll", RevGenre.WORK);
//                transactionList.add(revenue);
//            } else {
//                Expense expense = new Expense(Double.parseDouble(partsOfLine.get(1)), "McDonalds", ExpGenre.FOOD);
//                transactionList.add(expense);
//            }
//        }
//    }
//
//    @Override
//    public void save() throws FileNotFoundException, UnsupportedEncodingException {
//        PrintWriter writer = new PrintWriter("savedtransactionsfile.txt","UTF-8");
//        for (Transaction t : transactionList) {
//            String transaction = t.toString();
//
//            writer.println(transaction);
//        }
//        writer.close();
//    }
//
//    private ArrayList<String> splitOnSpace(String line){
//        String[] splits = line.split(" ");
//        return new ArrayList<>(Arrays.asList(splits));
//    }
}
