package model;

import java.util.*;

// TODO: how to keep entry managers sorted when printing out (lowest date to highest date)
// SINGLETON CLASS: stores entries in buckets corresponding to their month and year
public class BudgetManager implements Iterable<String> {
    private static BudgetManager instance = new BudgetManager();

    // TODO: have to change map to <SimpleDateFormat, EntryManager> in order to be able to order
    private HashMap<String, EntryManager> entryManagers;

    // Constructor:
    private BudgetManager() {
        entryManagers = new HashMap<>();
    }

    // EFFECTS: returns only BudgeterManager instance
    public static BudgetManager getInstance() {
        return instance;
    }

    // Getters:
    public Collection<EntryManager> getAllEntryManagers() { return entryManagers.values(); }

    // EFFECTS: returns list of available months in budget manager in order
    public List<String> getMonths() {
        List<String> monthAsList = new ArrayList<>(entryManagers.keySet());
        Collections.sort(monthAsList, Comparator.comparingInt(BudgetManager::getMonthNum));

        return monthAsList;
    }

    // TODO: maybe I can overload this method to take in strings and simpledates?
    // EFFECTS: if date exists, returns entry manager of given date, ow return null
    public EntryManager getEntryManagerFromDate(String date) {
        return entryManagers.get(date);
    }

    // TODO write a unit test for this
    // EFFECTS: returns ALL transactions from the entry manager of the given date
    public List<Transaction> getAllTransactionsFromDate(String date) {
        return entryManagers.get(date).getAllTransactions();
    }

    // TODO: write a unitttt test for thisss
    // EFFECTS: returns ALL specified transactions from the entry manager of the given date
    public List<Transaction> getAllSpecifiedTransactionsFromDate(String date, Boolean isRevenue) {
        return entryManagers.get(date).getAllSpecifiedTransactions(isRevenue);
    }


    // TODO: use regex to check given string? or should i use SimpleDates as key values
    // MODIFIES: this
    // EFFECTS: if hashmap doesn't contain given date key, add a new date-empty manager map and return true,
    //          ow do nothing and return false
    public boolean createEntryManFromDate(String date) {
        if (!entryManagers.containsKey(date)) {
            entryManagers.put(date, new EntryManager(date));
            return true;
        }
        return false;
    }

    // TODO: when should the sorting occur? in this method after an entry is added? or should i create a method that sorts from entry and call it here. or should sorting occur only when i need to see the entry on the ui (so when retrieving)
    // MODIFIES: this
    // EFFECTS: adds an entry to the bud-manager in its respective e-manager (according to month),
    //          if no such e-manager exists, create one first and then add entry
    public void addEntry(Entry e) {
        String simpleFormattedDate = e.getDate().simpleFormat();

        if (!entryManagers.keySet().contains(simpleFormattedDate)) {
            createEntryManFromDate(simpleFormattedDate);
        }

        entryManagers.get(simpleFormattedDate).addEntry(e);
    }

    // TODO: Should I be able to add transactions from a budgeter manager? the method should take in an ENTRY and TRANSACTION, and adds the TRANSACTION to the ENTRY

    // TODO: implement toString to return every entry manager under their respective month
    @Override
    public String toString() {
        String budgetManString = BudgetStringer.underlinedHeaderString("ALL ENTRIES BY MONTH", "=") + "\n";
        for (EntryManager em : entryManagers.values()) {
            budgetManString += em + "\n";
        }

        return budgetManString;
    }

    // EFFECTS: returns iterator for budget manager's month list
    @Override
    public Iterator<String> iterator() {
        List<String> months = getMonths();

        return months.iterator();
    }

    // TODO: the sorting doesn't consider the year, so higher numbers are placed before lower ones. have to refactor
    // EFFECTS: returns the respective num representation of the month of the given date
    private static int getMonthNum(String date) {
        if(date.contains(" ")) {
            date = date.split(" ")[0];
        }

        switch(date){
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;

        }
        return -1;
    }



}
