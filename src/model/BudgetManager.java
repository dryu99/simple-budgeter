package model;

import java.util.Collection;
import java.util.HashMap;

// TODO: how to keep entry managers sorted when printing out (lowest date to highest date)
// SINGLETON CLASS: stores entries in buckets corresponding to their month and year
public class BudgetManager {
    private static BudgetManager instance = new BudgetManager();

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

    public Collection<EntryManager> getAllEntryManagers() {
        return entryManagers.values();
    }

    // TODO: maybe I can overload this method to take in strings and simpledates?
    // EFFECTS: if date exists, returns entry manager of given date, ow return null
    public EntryManager getEntryManagerFromDate(String date) {
        return entryManagers.get(date);
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

    // TODO: when should the sorting occur? in this method after an entry is added? or should i create a method that sorts from entry and call it here
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

    // TODO: Should I be able to add transactions from a budgeter manager?

    // TODO: implement toString to return every entry manager under their respective month
    @Override
    public String toString() {
        String budgetManString = BudgetStringer.underlinedHeaderString("ALL ENTRIES BY MONTH", "=") + "\n";
        for (EntryManager em : entryManagers.values()) {
            budgetManString += em + "\n";
        }

        return budgetManString;
    }


}
