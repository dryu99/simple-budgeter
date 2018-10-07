package model;


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
    public void setGenre(RevGenre genre) { this.genre = genre; }

    // EFFECTS: returns revenue object in string form "Revenue: $<value>"
    @Override
    public String toString() {
        return "Revenue: $" + value + " - " + description + " (" + genre + ")";
    }



}
