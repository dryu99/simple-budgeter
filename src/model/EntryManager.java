//package model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//// CLASS: Entry manager for the budgeting app. Stores entry data.
//public class EntryManager {
//    private String date;// TODO: should I make this field a String or SimpleDate
//    private List<Entry> entryList;
//
//    // Constructor:
//    public EntryManager(String date) {
//        this.date = date;
//        entryList = new ArrayList<>();
//    }
//
//    // Getters:
//    public String getDate() { return date; }
//    public List<Entry> getEntryList() { return entryList; }
//
//    // TODO: test this method + rid duplication by making entry getters take in booleans too?
//    // EFFECTS: returns total revenue amount in entry list
//    public double getSpecifiedTransactionTotal(Boolean isRevenue) {
//        double totalValue = 0;
//
//        if (isRevenue) {
//            for (Entry e : entryList) {
//                totalValue += e.totalRevenue();
//            }
//        } else {
//            for (Entry e : entryList) {
//                totalValue += e.totalExpenses();
//            }
//        }
//        return totalValue;
//    }
//
//    // TODO: test this method
//    // EFFECTS: returns total net value in entry list
//    public double getTotalNetValue() {
//        double totalValue = 0;
//
//        for (Entry e : entryList) {
//            totalValue += e.netValue();
//        }
//
//        return totalValue;
//    }
//
//    // TODO: test this method
//    // EFFECTS: returns all transactions in entry list
//    public List<Transaction> getAllTransactions() {
//        List<Transaction> transactions = new ArrayList<>();
//
//        for (Entry e : entryList) {
//            for (Transaction t : e.getTransactions()) { //TODO implement iterable?
//                transactions.add(t);
//            }
//        }
//        return transactions;
//    }
//
//    // TODO: test this method + rid duplication by making entry getters take in booleans too?
//    // EFFECTS: returns all specified transactions in entry list
//    public List<Transaction> getAllSpecifiedTransactions(Boolean isRevenue) {
//        List<Transaction> transactions = new ArrayList<>();
//
//        if (isRevenue) {
//            for (Entry e : entryList) {
//                for (Transaction t : e.getRevenues()) {
//                    transactions.add(t);
//                }
//            }
//        } else {
//            for (Entry e : entryList) {
//                for (Transaction t : e.getExpenses()) {
//                    transactions.add(t);
//                }
//            }
//        }
//        return transactions;
//    }
//
//
//    // EFFECTS: returns entryList size
//    public int size() {
//        return entryList.size();
//    }
//
//    // EFFECTS: returns true if entryList is empty, false ow
//    public boolean isEmpty() {
//        return entryList.isEmpty();
//    }
//
//    // EFFECTS: returns true if entryList contains the given entry, false ow
//    public boolean contains(Entry e) { return entryList.contains(e); }
//
//    // TODO: refactor this method so that when you add an entry that has the same date, you don't create a new entry, you just add to the existing entry
//
//    // MODIFIES: this
//    // EFFECTS: if null is given as a parameter, throw IllegalArgumentException.
//    //          ow, add given manager to this entryList (if not already in list, ow do nothing)
//    public void addTransaction(Entry newEntry) {
//        if (newEntry == null) {
//            throw new IllegalArgumentException();
//        }
//
//        if (!entryList.contains(newEntry)) {
//            entryList.add(newEntry);
//        }
//    }
//
//    // TODO: implement this method, have it run a for loop just returning all entry complete strings
//    @Override
//    public String toString() {
//        String entryManString = BudgetStringer.underlinedHeaderString(date, "-");
//        if (entryList.isEmpty()) {
//            return entryManString + "No entries have been written yet!";
//        } else {
//            for (Entry e : entryList) {
//                entryManString += e.toCompleteString() + "\n";
//            }
//        }
//
//        return entryManString.trim();
//    }
//
//}
