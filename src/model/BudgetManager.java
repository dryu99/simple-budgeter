package model;

import java.util.*;

// TODO: how to keep entry managers sorted when printing out (lowest date to highest date)
// SINGLETON CLASS: stores entries in buckets corresponding to their month and year
public class BudgetManager extends Observable implements Iterable<String> {

    // TODO: have to change map to <SimpleDateFormat, EntryManager> in order to be able to order?
    private HashMap<String, Entry> entries;

    // Constructor:
    public BudgetManager() {
        entries = new HashMap<>();
    }

    // Getters:
    public Collection<Entry> getAllEntries() { return entries.values(); }

    // EFFECTS: returns list of available months in budget manager in order
    public List<String> getMonths() {
        List<String> monthAsList = new ArrayList<>(entries.keySet());
        Collections.sort(monthAsList, Comparator.comparingInt(BudgetManager::getDateCompareNum));

        return monthAsList;
    }

    // ASSUME: given date IS in the key set //TODO make this more robust?
    // EFFECTS: returns index of given date in the key set
    public int getIndexOfDate(String date) {
        List<String> monthAsList = getMonths();

        for (int i = 0, n = monthAsList.size(); i < n; i++) {
            if (date.equals(monthAsList.get(i))) {
                return i;
            }
        }
        return -1;
    }

    // TODO: maybe I can overload this method to take in strings and simpledates?
    // EFFECTS: if date exists, returns entry manager of given date, ow return null
    public Entry getEntryFromDate(String date) {
        return entries.get(date);
    }

    // TODO write a unit test for this
    // EFFECTS: returns total specified transaction amount from entry manager of given date
    public double getSpecifiedTransactionTotalFromDate(String date, Boolean isRevenue) {
        if (isRevenue) {
            return entries.get(date).totalRevenue();
        }
        return entries.get(date).totalExpenses();
    }



    // TODO write a unit test for this
    // EFFECTS: returns total net value from entry manager of given date
    public double getNetValueFromDate(String date) {
        return entries.get(date).netValue();
    }

    // TODO write a unit test for this
    // EFFECTS: returns ALL transactions from the entry manager of the given date
    public List<Transaction> getAllTransactionsFromDate(String date) {
        return entries.get(date).getTransactions();
    }

    // TODO: write a unitttt test for thisss
    // EFFECTS: returns ALL specified transactions from the entry manager of the given date
    public List<Transaction> getAllSpecifiedTransactionsFromDate(String date, Boolean isRevenue) {
        if (isRevenue) {
            return entries.get(date).getRevenues();
        } else {
            return entries.get(date).getExpenses();
        }
    }

    // TODO: use regex to check given string? or should i use SimpleDates as key values
    // MODIFIES: this
    // EFFECTS: if hashmap doesn't contain given date key, add a new date-entry key-value pair and return true,
    //          ow do nothing and return false
    public boolean createEntryFromDate(String date) {
        if (!entries.containsKey(date)) {
            entries.put(date, new Entry(date));
            return true;
        }
        return false;
    }

    // TODO: when should the sorting occur? in this method after an entry is added? or should i create a method that sorts from entry and call it here. or should sorting occur only when i need to see the entry on the ui (so when retrieving)
    // TODO: sort transactions when added/retrieved?
    // MODIFIES: this
    // EFFECTS: adds a transaction to the bud-manager in its respective entry (according to month),
    //          if no such entry exists, create one first and then add transaction
    public void addTransaction(Transaction t) {
        String transactionDate = t.getDate().simpleFormat();

        if (!entries.keySet().contains(transactionDate)) {
            createEntryFromDate(transactionDate);
            // TODO this is frustrating because the update method for the monthlistdisplay observer updates differently depending on if I create a new month or not, what should i change?? should the month list display update method ONLY be able to set the new selected index? or should i make a conditional idk
        }

        entries.get(transactionDate).addTransaction(t);
        setChanged();
        notifyObservers(transactionDate);
    }

    // TODO: implement toString
//    @Override
//    public String toString() {
//        String budgetManString = BudgetStringer.underlinedHeaderString("ALL ENTRIES BY MONTH", "=") + "\n";
//        for (EntryManager em : entries.values()) {
//            budgetManString += em + "\n";
//        }
//
//        return budgetManString;
//    }

    // EFFECTS: returns iterator for budget manager's month list, sorted
    @Override
    public Iterator<String> iterator() {
        List<String> months = getMonths();

        return months.iterator();
    }

    // EFFECTS: returns the respective num representation of the month/year of the given date (to compare)
    private static int getDateCompareNum(String date) {
        int val = 0;
        String month = "";
        int year = 0;

        if(date.contains(" ")) {
            String[] dateArray = date.split(" ");
            month = dateArray[0];
            year = Integer.parseInt(dateArray[1]);
        }

        switch(month){
            case "January":
                val += 1;
                break;
            case "February":
                val += 2;
                break;
            case "March":
                val += 3;
                break;
            case "April":
                val += 4;
                break;
            case "May":
                val += 5;
                break;
            case "June":
                val += 6;
                break;
            case "July":
                val += 7;
                break;
            case "August":
                val += 8;
                break;
            case "September":
                val += 9;
                break;
            case "October":
                val += 10;
                break;
            case "November":
                val += 11;
                break;
            case "December":
                val += 12;
                break;
        }
        return (val * 2) + (year * 29);
    }



}
