package ru.osiristher.tester.exceptions;

/**
 * Created by desiresdesigner on 16.02.15.
 */
public class UnknownLanguageException extends CompileException {
    private String extension;

    public UnknownLanguageException() {
        super();
    }

    public UnknownLanguageException(String message) {
        super(message);
        extension = message;
    }

    public UnknownLanguageException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownLanguageException(Throwable cause) {
        super(cause);
    }

    public void setExtension(String extension){
        this.extension = extension;
    }

    public String getExtension(){
        return extension;
    }
}
