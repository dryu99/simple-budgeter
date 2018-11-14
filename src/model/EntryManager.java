package model;

import model.exceptions.NullParameterGiven;

import java.util.List;
import java.util.ArrayList;

// CLASS: Entry manager for the budgeting app. Stores entry data.
public class EntryManager {
    private List<Entry> entryList;

    // Constructor:
    public EntryManager() {
        entryList = new ArrayList<>();
    }

    // Getters:
    public List<Entry> getEntryList() { return entryList; }

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

}
