package ua.com.tracksee.exception;

/**
 * Created by kstes_000 on 09-May-15.
 */
public class CreateException extends Exception {
    private CreateExceptionType errorType;

    public CreateException(String s, CreateExceptionType errorType) {
        super(s);
        this.errorType = errorType;
    }

    public String getErrorType() {
        return errorType.getCode();
    }
}
