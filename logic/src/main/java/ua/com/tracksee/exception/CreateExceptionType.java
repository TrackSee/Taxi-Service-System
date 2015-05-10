package ua.com.tracksee.exception;

/**
 * Created by kstes_000 on 09-May-15.
 */
public enum CreateExceptionType {
    BAD_PASSWORD("bad-password"),
    BAD_PHONE("bad-phone"),
    BAD_EMAIL("bad-email"),
    USER_EXISTS("user-exists"),
    USER_IS_ACTIVE("is-active"),
    BAD_LINK("bad-link");

    private String code;

    CreateExceptionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}