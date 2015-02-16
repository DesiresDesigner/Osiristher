package osiristherNative.exceptions;

/**
 * Created by desiresdesigner on 16.02.15.
 */
public class UnknownLanguageException extends CompileException {
    public UnknownLanguageException() {
        super();
    }

    public UnknownLanguageException(String message) {
        super(message);
    }

    public UnknownLanguageException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownLanguageException(Throwable cause) {
        super(cause);
    }
}
