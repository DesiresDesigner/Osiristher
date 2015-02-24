package ru.osiristher.tester.exceptions;

/**
 * Created by desiresdesigner on 16.02.15.
 */
public class CompileException extends Exception {
    public CompileException() {
        super();
    }

    public CompileException(String message) {
        super(message);
    }

    public CompileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompileException(Throwable cause) {
        super(cause);
    }
}
