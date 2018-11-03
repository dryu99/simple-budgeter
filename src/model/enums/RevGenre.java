package model.enums;

// TODO have to remove UI funtionality
public enum RevGenre implements Genre{
    PAYCHEQUE("Paycheque"),
    REIMBURSEMENT("Reimbursement"),
    IOU("IOU");

    private final String lowerCaseForm;

    RevGenre(String lowerCaseForm) {
        this.lowerCaseForm = lowerCaseForm;
    }

    // TODO: get rid of UI stuff here
    // EFFECTS: prints out vertical list of RevGenre cases
    public static void printUserInputList() {
        int i = 1;

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
