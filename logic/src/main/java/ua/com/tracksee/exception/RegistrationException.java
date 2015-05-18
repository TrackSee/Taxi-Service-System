package ua.com.tracksee.exception;

/**
 * @author Ruslan Gunavardana
 */
public class RegistrationException extends Exception {
    private String errorType;

    public RegistrationException(String s, String errorType) {
        super(s);
        this.errorType = errorType;
    }

    public String getErrorType() {
        return errorType;
    }
}
