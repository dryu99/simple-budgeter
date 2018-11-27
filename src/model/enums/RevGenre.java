package model.enums;

public enum RevGenre implements Genre {
    PAYCHEQUE("Paycheque"),
    REIMBURSEMENT("Reimbursement"),
    IOU("IOU");

    private final String lowerCaseForm;

    RevGenre(String lowerCaseForm) {
        this.lowerCaseForm = lowerCaseForm;
    }

    // EFFECTS: returns case in lower case form
    @Override
    public String toLowerString() {
        return lowerCaseForm;
    }

}
