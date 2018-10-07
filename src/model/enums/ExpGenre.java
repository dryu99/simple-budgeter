package model.enums;

public enum ExpGenre {
    FOOD("Food"),
    CLOTHES("Clothes"),
    GAMES("Games");

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
