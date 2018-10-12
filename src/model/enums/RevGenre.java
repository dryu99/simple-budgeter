package model.enums;

// TODO: make one enum where each case has a string form AND a boolean. true = revenue, false = expense??
public enum RevGenre {
    PAYCHEQUE("Paycheque"),
    REIMBURSEMENT("Reimbursement"),
    IOU("IOU");

    private final String lowerCaseForm;

    RevGenre(String lowerCaseForm) {
        this.lowerCaseForm = lowerCaseForm;
    }

    // EFFECTS: prints out vertical list of RevGenre cases
    public static void printUserInputList() {
        int i = 0;

        System.out.println("-----");
        for (RevGenre rg : RevGenre.values()) {
            System.out.println(i + ". " + rg.lowerCaseForm);
            i++;
        }
        System.out.println("-----");
    }

    // EFFECTS: returns case in lower case form
    public String toLowerString() {
        return lowerCaseForm;
    }

}
