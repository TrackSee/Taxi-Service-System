package ua.com.tracksee.logic.exception;

/**
 * @author Ruslan Gunavardana
 */
public enum RegistrationExceptionType {
    BAD_PASSWORD("bad-password"),
    BAD_PHONE("bad-phone"),
    BAD_EMAIL("bad-email"),
    USER_EXISTS("user-exists"),
    EMAIL_SENDING_FAIL("send-fail"),
    USER_IS_ACTIVE("is-active"),
    BAD_LINK("bad-link");

    private String code;

    RegistrationExceptionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
