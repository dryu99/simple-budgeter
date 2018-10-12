package model;

import model.exceptions.NullParameterGiven;

import java.util.List;
import java.util.ArrayList;

public class EntryManager {
    private List<Entry> entryList;

    // Constructor:
    public EntryManager() {
        entryList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if null is given as a parameter, throw NullParameterGiven exception.
    //          ow, add given entry to this entryList
    public void addEntry(Entry newEntry) throws NullParameterGiven {
        if (newEntry == null) {
            throw new NullParameterGiven();
        }
        entryList.add(newEntry);
    }








}
