package ua.com.tracksee.logic.exception;

/**
 * @author Ruslan Gunavardana
 */
public class RegistrationException extends Exception {
    private RegistrationExceptionType errorType;

    public RegistrationException(String s, RegistrationExceptionType errorType) {
        super(s);
        this.errorType = errorType;
    }

    public String getErrorType() {
        return errorType.getCode();
    }
}
