package model.statistics;

import model.TransactionManager;
import model.enums.ExpGenre;
import model.enums.Genre;
import model.enums.RevGenre;

import java.util.HashMap;
import java.util.Map;

// TODO: want the hashmap to be some kind of data frame (records are each genre, variables are num and value), with this class just being a summary of that data frame
// CLASS: keeps track of transaction manager related stats
public class TransactionManagerStats {
    private TransactionManager homeTransManager;
    private int numOfTransactions;
    private double netValue;
    private Map<Genre, GenreStat> genreStats;

    public TransactionManagerStats(TransactionManager tm) {
        homeTransManager = tm;
        numOfTransactions = tm.size();
        netValue = tm.totalValue();
        genreStats = new HashMap<>();
        initializeGenreStats();
    }

    // Getters:
    public TransactionManager getHomeTransManager() { return homeTransManager; }
    public int getNumOfTransactions() { return numOfTransactions; }
    public double getNetValue() { return netValue; }


    private void initializeGenreStats() {
        if (homeTransManager.getClass() == RevenueManager.class) {
            for (RevGenre rg : RevGenre.values()) {
                genreStats.put(rg, new GenreStat(rg));
            }
        } else {
            for (ExpGenre eg : ExpGenre.values()) {
                genreStats.put(eg, new GenreStat(eg));
            }
        }
    }

}
