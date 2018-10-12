package model;

import ui.Prompter;

import java.util.ArrayList;
import java.util.List;

// TODO: how to make only one prompter (i.e. one scanner) for all classes to use
// TODO: feel like there has to be a difference between adding stuff and prompting stuff (i.e. entry.addTransaction) this is important for tests?
// TODO: I'm not sure how to use a handleCommand method here as I need to break and I can't break from a loop in a diff method. I don't want to use if + while statements (want to put whole thing in a method)
public class Budgeter {
    private Prompter prompter;
    private List<Entry> entryList;

    // Constructor
    public Budgeter() {
        prompter = new Prompter();
        entryList = new ArrayList<>();

        start();
    }

    // EFFECTS: starts Budgeter application
    private void start() {
        System.out.println("BUDGETING APPLICATION\n");

        while (true) {
            System.out.println("What would you like to do?");
            printOptions();

            String command = prompter.returnUserCommand("User Command: ");
            System.out.println();

            if (command.equals("exit")) {
                System.out.println("See you later!");
                break;
            }
            handleBudgeterCommand(command);
        }
    }

    // TODO: implement executions for each respective case
    // EFFECTS: if valid command is given execute it, ow print "That's not a real command!"
    private void handleBudgeterCommand(String command) {
        switch (command) {
            case "0":
                this.addEntry();
                break;
            case "1":
                this.viewAllEntries();
                break;
//            case "2":
//                this.viewAllEntries();
//                break;
                default:
                    System.out.println("That's not a real command! Try again.");
                    break;
        }
        System.out.println();
    }

    // TODO: make a separate add entry method? (for testing)
    // TODO: I'm not sure how to use a handleCommand method here as I need to break and I can't break from a loop in a diff method. I don't want to use if + while statements (want to put whole thing in a method)
    // MODIFIES: this
    // EFFECTS: pr
    private void addEntry() {
        Entry newEntry = new Entry();
        System.out.println("--ADDING ENTRY--");

        SimpleDate date = prompter.returnUserDate();
        newEntry.setDate(date);
        System.out.println();

    // TODO: make a method for yes/no qs since theres a similar one in entry? (maybe a command handler that handles yes/no questions, and takes the needed object as an add param)
        // TODO: look at what steven did here with the command handler
        while (true) {
            String answer = prompter.returnUserCommand("Would you like to add a transaction to the new entry? ");

                if (answer.equals("no")) {
                    break;
                } else if (answer.equals("yes")) {
                    newEntry.addTransaction();
                    newEntry.promptMoreTransactions();
                    break;
                } else {
                    System.out.println("Please type 'yes' or 'no'.");
                    System.out.println();
                }
        }

        // TODO: make sure it prints out the entry list size too
        System.out.println("**New Entry (" + date + ") successfully added**");
        System.out.println();

        entryList.add(newEntry);
    }

    private boolean HandleCommandYesNo(Entry newEntry, String answer) {
        if (answer.equals("no")) {
            return true;
        } else if (answer.equals("yes")) {
            newEntry.addTransaction();
            newEntry.promptMoreTransactions();
            return true;
        } else {
            System.out.println("Please type 'yes' or 'no'.");
            System.out.println();
        }
        return false;
    }

    // EFFECTS: prints out transactions
    private void viewAllEntries() {
        if (entryList.isEmpty()) {
            System.out.println("No entries have been written yet!");
            return;
        }

        System.out.println("--RECORDED ENTRIES--");
        System.out.println();

        for (int i = 0, n = entryList.size(); i < n; i++) {
            System.out.print((i + 1) + ": ");
            entryList.get(i).print();
            System.out.println();
        }
    }

    // EFFECTS: prints out budget application options
    private void printOptions() {
        System.out.println("[0] Add a new Entry");
        System.out.println("[1] View all Entries");
        System.out.println("[exit] Exit application");
        System.out.println();
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
