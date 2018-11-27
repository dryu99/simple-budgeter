package model.exceptions;

public class InvalidDateException extends Exception {
    private int dateValue;

    public InvalidDateException(int dateValue, String message) {
        super(dateValue + message);
        this.dateValue = dateValue;
    }

    public InvalidDateException() {
        super("Invalid date was given");
    }


}
