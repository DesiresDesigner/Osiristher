package ru.osiristher.tester.exceptions;

/**
 * Created by DesiresDesigner on 24.02.15.
 */
public class ConfigException extends Exception{
    public ConfigException() {
        super();
    }

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigException(Throwable cause) {
        super(cause);
    }
}