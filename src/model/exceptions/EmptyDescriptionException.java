package model.exceptions;

public class EmptyDescriptionException extends Exception {

    public EmptyDescriptionException() {
        super("Given description is an empty string");
    }

}
