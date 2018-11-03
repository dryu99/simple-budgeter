package model;

import model.enums.ExpGenre;
import model.enums.RevGenre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// CLASS: stores maps with genres to list of transactions to record genre-transaction stats
// TODO: want to have a transaction storer that is Map<RevGenre, List<Revenue>>, that will give all the revenues that map to that genre! that way i will be able to calculate stats easier
// TODO
public class TransactionGenreManager {
    private Map<RevGenre, List<Revenue>> genreRevenuesMap = new HashMap<>();
    private Map<ExpGenre, List<Expense>> genreExpensesMap = new HashMap<>();

//  TODO: are enumeration values unique? (are they static)
    public TransactionGenreManager() {
        for (RevGenre rg : RevGenre.values()) {
            genreRevenuesMap.put(rg, new ArrayList<>());
        }

        for (ExpGenre eg : ExpGenre.values()) {
            genreExpensesMap.put(eg, new ArrayList<>());
        }
    }

}
