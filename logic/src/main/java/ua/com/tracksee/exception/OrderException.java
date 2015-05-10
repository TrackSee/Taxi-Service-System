package ua.com.tracksee.exception;

/**
 * @author Sharaban Sasha
 */
public class OrderException extends Exception {
    private String errorCode;

    public OrderException(String s, String errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
