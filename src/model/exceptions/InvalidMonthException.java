package model.exceptions;

public class InvalidMonthException extends InvalidDateException {

    public InvalidMonthException(int month) {
        super(month, " is not a valid month.");
    }
}
