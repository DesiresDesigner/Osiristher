package ru.osiristher.tester.exceptions;

/**
 * Created by desiresdesigner on 16.02.15.
 */
public class LackOfExpansionException extends CompileException {
    public LackOfExpansionException() {
        super();
    }

    public LackOfExpansionException(String message) {
        super(message);
    }

    public LackOfExpansionException(String message, Throwable cause) {
        super(message, cause);
    }

    public LackOfExpansionException(Throwable cause) {
        super(cause);
    }
}
