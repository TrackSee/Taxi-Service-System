package ua.com.tracksee.logic;

import org.apache.commons.validator.routines.EmailValidator;

import javax.ejb.Stateless;

import static java.lang.Character.isDigit;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class ValidationBean {

    /**
     * (?=.*[0-9])         - a digit must occur at least once
     * (?=.*[a-z])         - a lower case letter must occur at least once
     * (?=.*[A-Z])         - an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])    - a special character must occur at least once
     * (?=\\S+$)           - no whitespace allowed in the entire string
     * [a-zA-Z0-9@#$%^&+=] - allowed characters
     * {6,28}              - at least 6-28 characters
     */
    private String PASSWORD_REGEXP = "^(?=.*[0-9@#$%^&+=_])(?=.*[a-z])(?=.*[A-Z])[\\w@#$%^&+=]{6,28}$";

    private String PHONE_NUMBER_REGEXP = "^[0-9 +()-]{5,28}$";

    /**
     * Returns true if email address is valid, false in other case.
     * @param email validated email address
     */
    public boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEXP);
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        boolean isValidSymbols = phoneNumber.matches(PHONE_NUMBER_REGEXP);
        int digitCount = 0;
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if (isDigit(c)) {
                ++digitCount;
            }
        }
        return digitCount > 4 & digitCount < 16 & isValidSymbols;
    }
}
