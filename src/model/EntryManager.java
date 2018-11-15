package model;

import model.exceptions.NullParameterGiven;

import java.util.ArrayList;
import java.util.List;

// CLASS: Entry manager for the budgeting app. Stores entry data.
public class EntryManager {
    private List<Entry> entryList;
    private String date;// TODO: should I make this field a String or SimpleDate

    // Constructor:
    public EntryManager() {
        entryList = new ArrayList<>();
    }

    // Getters:
    public List<Entry> getEntryList() { return entryList; }

    // TODO: refactor this method so that when you add an entry that has the same date, you don't create a new entry, you just add to the existing entry

    // MODIFIES: this
    // EFFECTS: if null is given as a parameter, throw NullParameterGiven exception.
    //          ow, add given manager to this entryList (if not already in list, ow do nothing)
    public void addEntry(Entry newEntry) {
        if (newEntry == null) {
            throw new NullParameterGiven();
        }

        if (!entryList.contains(newEntry)) {
            entryList.add(newEntry);
        }
    }

    // EFFECTS: returns entryList size
    public int size() {
        return entryList.size();
    }

    // EFFECTS: returns true if entryList is empty, false ow
    public boolean isEmpty() {
        return entryList.isEmpty();
    }

    // EFFECTS: returns true if entryList contains the given entry, false ow
    public boolean contains(Entry e) { return entryList.contains(e); }

    // TODO: implement this method, have it run a for loop just returning all entry complete strings
    @Override
    public String toString() {
        return "";
    }

}
