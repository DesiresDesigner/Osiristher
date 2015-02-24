package ru.osiristher.tester.exceptions;

/**
 * Created by DesiresDesigner on 16.02.15.
 */
public class GCCException extends CompileException {
    public GCCException() {
        super();
    }

    public GCCException(String message) {
        super(message);
    }

    public GCCException(String message, Throwable cause) {
        super(message, cause);
    }

    public GCCException(Throwable cause) {
        super(cause);
    }
}
