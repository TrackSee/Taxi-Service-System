package ua.com.tracksee.logic.exception;

/**
 * @author Sharaban Sasha
 */
public class EnumException extends Exception {
    private String errorCode;

    public EnumException(String s, String errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
