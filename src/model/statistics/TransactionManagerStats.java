package model.statistics;

import model.TransactionManager;
import model.enums.Genre;

import java.util.HashMap;
import java.util.Map;

// CLASS: keeps track of transaction manager related stats
public class TransactionManagerStats {
    private int numOfTransactions;
    private double netValue;
    private Map<Genre, GenreStat> genreStats;

    public TransactionManagerStats(TransactionManager tm) {
        numOfTransactions = tm.size();
        netValue = tm.totalValue();
        genreStats = new HashMap<>();
    }

}
