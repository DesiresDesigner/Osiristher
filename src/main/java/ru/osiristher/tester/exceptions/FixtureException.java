package ru.osiristher.tester.exceptions;

/**
 * Created by DesiresDesigner on 19.02.15.
 */
public class FixtureException extends Exception{
    public FixtureException() {
        super();
    }

    public FixtureException(String message) {
        super(message);
    }

    public FixtureException(String message, Throwable cause) {
        super(message, cause);
    }

    public FixtureException(Throwable cause) {
        super(cause);
    }
}