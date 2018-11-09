package model;


import model.enums.RevGenre;

public class Revenue extends Transaction {

    public Revenue(double value, String desc, RevGenre genre) {
        super(value, desc, genre);
    }

}
