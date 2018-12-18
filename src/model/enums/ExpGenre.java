package model.enums;

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

    // EFFECTS: returns case in lower case form
    @Override
    public String toLowerString() {
        return lowerCaseForm;
    }

}
