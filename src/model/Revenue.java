package model;

// TODO: have to have transaction genre be an enum of an enum (separate into rev/exp genres)
// TODO: implement transaction description
// TODO: have transaction be an interface with revenue and expense classes that implement it?
// TODO: I want to have two separate genre enum classes, one for rev and exp.

import model.enums.RevGenre;

public class Revenue extends Transaction {
    private RevGenre genre;

    public Revenue(double value, String desc, RevGenre genre) {
        super(value, desc);
        this.genre = genre;
    }

    public Revenue() {
        super();
        genre = null;
    }

    // Getters:
    public RevGenre getGenre() { return genre; }

    // Setters:
    public void setGenre(RevGenre genre) {
        this.genre = genre;
    }

    // EFFECTS: returns revenue object in string form "Revenue: $<value>"
    @Override
    public String toString() {
        return "Revenue: $" + value + " - " + description + " (" + genre + ")";
    }



}
