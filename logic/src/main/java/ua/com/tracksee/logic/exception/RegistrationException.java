package ua.com.tracksee.logic.exception;

/**
 * @author Ruslan Gunavardana
 */
public class RegistrationException extends Exception {
    private String errorCode;

    public RegistrationException(String s, String errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
