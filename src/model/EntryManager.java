package model;

import model.exceptions.NullParameterGiven;

import java.util.List;
import java.util.ArrayList;

// SINGLETON CLASS: only one manager manager for the budgeting app. All manager data stored here.
public class EntryManager {
    private static EntryManager instance = new EntryManager();

    private List<Entry> entryList;
    // TODO: create map field

    // Constructor:
    private EntryManager() {
        entryList = new ArrayList<>();
    }

    // EFFECTS: returns singular instance of EntryManager
    public static EntryManager getInstance() {
        return instance;
    }

    // Getters:
    public List<Entry> getEntryList() { return entryList; }

    // MODIFIES: this
    // EFFECTS: if null is given as a parameter, throw NullParameterGiven exception.
    //          ow, add given manager to this entryList
    public void addEntry(Entry newEntry) {
        if (newEntry == null) {
            throw new NullParameterGiven();
        }
        entryList.add(newEntry);
    }

    // EFFECTS: returns entryList size
    public int size() {
        return entryList.size();
    }

    // EFFECTS: returns true if entryList is empty, false ow
    public boolean isEmpty() {
        return entryList.isEmpty();
    }











}
