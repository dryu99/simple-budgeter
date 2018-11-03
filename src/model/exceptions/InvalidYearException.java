package model.exceptions;

public class InvalidYearException extends InvalidDateException {

    public InvalidYearException(int year) {
        super(year, " is not a valid year.");
    }
}
