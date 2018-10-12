package model.enums;

// TODO: maybe make one FOOD_AND_DRINK case, but have to figure out the toString form of it
public enum ExpGenre {
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

    public static void printUserInputList() {
        int i = 1;

        System.out.println("-----");
        for (ExpGenre eg : ExpGenre.values()) {
            System.out.println(i + ". " + eg.lowerCaseForm);
            i++;
        }
        System.out.println("-----");
    }

    // EFFECTS: returns case in lower case form
    public String toLowerString() {
        return lowerCaseForm;
    }

}
