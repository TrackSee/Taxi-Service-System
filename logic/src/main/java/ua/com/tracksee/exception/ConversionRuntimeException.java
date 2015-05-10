package ua.com.tracksee.exception;

/**
 * Thrown to indicate developer's mistake in writing
 * converter.
 *
 * @author Ruslan Gunavardana
 */
public class ConversionRuntimeException extends RuntimeException {

    public ConversionRuntimeException() {
        super();
    }

    public ConversionRuntimeException(String message) {
        super(message);
    }

    public ConversionRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConversionRuntimeException(Throwable cause) {
        super(cause);
    }
}
