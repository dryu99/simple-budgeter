package model;


import model.enums.RevGenre;

public class Revenue extends Transaction {

    public Revenue(double value, String desc, RevGenre genre) {
        super(value, desc, genre);
    }

    public Revenue() {
        super();
    }

    // TODO: implement removing functionality
    // MODIFIES: this
    // EFFECTS: sets the entry the revenue belongs to
    @Override
    public void setEntry(Entry newEntry) {
        if (newEntry != null) {

        }

        if (entry == null || !entry.equals(newEntry)) {
            this.entry = newEntry;
            newEntry.addRevenue(this);
        }
    }

}
