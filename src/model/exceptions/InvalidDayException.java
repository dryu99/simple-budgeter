package model.exceptions;

public class InvalidDayException extends InvalidDateException {

    public InvalidDayException(int day) {
        super(day, " is not a valid day.");
    }
}
