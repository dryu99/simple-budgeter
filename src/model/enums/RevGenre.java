package model.enums;

// TODO: make one enum where each case has a string form AND a boolean. true = revenue, false = expense
public enum RevGenre {
    WORK("Work"),
    IOU("IOU");

    private final String lowerCaseForm;

    RevGenre(String lowerCaseForm) {
        this.lowerCaseForm = lowerCaseForm;
    }

    // TODO: make it print a list without numbers
    public static void printUserInputList() {
        int i = 0;
        System.out.println("-----");
        // TODO: I can't get the size of values? I want to make a for loop not for-each
        for (RevGenre rg : RevGenre.values()) {
            System.out.println(rg.lowerCaseForm);
            i++;
        }
        System.out.println("-----");
    }

}
