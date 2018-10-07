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

    public static void printUserInputList() {
        int i = 0;
        System.out.println("-----");
        // TODO: Are there no get methods for RevGenre.values()... I want to retrieve sth in the array
        for (RevGenre rg : RevGenre.values()) {
            System.out.println(rg.lowerCaseForm);
            i++;
        }
        System.out.println("-----");
    }

}
