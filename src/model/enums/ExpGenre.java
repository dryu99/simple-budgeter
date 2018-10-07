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
        int i = 0;

        System.out.println("-----");
        // TODO: Are there no get methods for ExpGenre.values()... I want to retrieve sth in the array
        for (ExpGenre eg : ExpGenre.values()) {
            System.out.println(eg.lowerCaseForm);
            i++;
        }
        System.out.println("-----");
    }

}
