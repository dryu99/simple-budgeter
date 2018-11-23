package model.enums;

// TODO: how to lower coupling here, make just one enum?
public enum ExpGenre implements Genre {
    FOOD("Food"),
    DRINK("Drink"),
    SHOPPING("Shopping"),
    ACTIVITIES("Activities"),
    ENTERTAINMENT("Entertainment"),
    PERSONAL_CARE("Personal Care");

    private final String lowerCaseForm;

    ExpGenre(String lowerCaseForm) {
        this.lowerCaseForm = lowerCaseForm;
    }

//    // EFFECTS: returns vertical list of Expense genres
//    public static String stringList() {
//        int longestGenreLength = ExpGenre.greatestLengthGenre().lowerCaseForm.length();
//        int i = 1;
//
//        String str = BudgetStringer.lineString(longestGenreLength,"-") + "\n";
//
//        for (ExpGenre eg : ExpGenre.values()) {
//            str += i + ". " + eg.lowerCaseForm + "\n";
//            i++;
//        }
//        str += BudgetStringer.lineString(longestGenreLength,"-");
//
//        return str;
//    }


    // EFFECTS: returns case in lower case form
    @Override
    public String toLowerString() {
        return lowerCaseForm;
    }

    // TODO: should i put this in a diff class (inc cohesion)
    // EFFECTS: returns the RevGenre with the highest char count
    private static ExpGenre greatestLengthGenre() {
        ExpGenre genre = FOOD;

        for (ExpGenre eg : ExpGenre.values()) {
            if (eg.toLowerString().length() > genre.toLowerString().length()) {
                genre = eg;
            }
        }
        return genre;
    }

}
