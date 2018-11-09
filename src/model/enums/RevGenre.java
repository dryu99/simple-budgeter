package model.enums;

import model.BudgeterStringer;

// TODO have to remove UI funtionality
public enum RevGenre implements Genre{
    PAYCHEQUE("Paycheque"),
    REIMBURSEMENT("Reimbursement"),
    IOU("IOU");

    private final String lowerCaseForm;

    RevGenre(String lowerCaseForm) {
        this.lowerCaseForm = lowerCaseForm;
    }

    // EFFECTS: returns vertical list of Revenue genres
    public static String stringList() {
        int longestGenreLength = RevGenre.greatestLengthGenre().lowerCaseForm.length();
        int i = 1;

        String str = BudgeterStringer.lineString(longestGenreLength,"-") + "\n";

        for (RevGenre rg : RevGenre.values()) {
            str += i + ". " + rg.lowerCaseForm + "\n";
            i++;
        }
        str += BudgeterStringer.lineString(longestGenreLength,"-");

        return str;
    }

    // EFFECTS: returns case in lower case form
    public String toLowerString() {
        return lowerCaseForm;
    }

    // EFFECTS: returns the RevGenre with the highest char count
    private static RevGenre greatestLengthGenre() {
        RevGenre genre = PAYCHEQUE;

        for (RevGenre rg : RevGenre.values()) {
            if (rg.toLowerString().length() > genre.toLowerString().length()) {
                genre = rg;
            }
        }
        return genre;
    }

}
