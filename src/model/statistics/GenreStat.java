package model.statistics;

import model.enums.Genre;

public class GenreStat {
    private Genre genre;
    private int num;
    private double totalAmount;

    public GenreStat(Genre genre) {
        this.genre = genre;
        num = 0;
        totalAmount = 0;
    }


}
