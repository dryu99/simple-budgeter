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
        // TODO: I can't get the size of values? I want to make a for loop not for-each
        for (ExpGenre eg : ExpGenre.values()) {
            System.out.println(eg.lowerCaseForm);
            i++;
        }
        System.out.println("-----");
    }
}
