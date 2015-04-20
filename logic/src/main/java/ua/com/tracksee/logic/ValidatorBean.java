package ua.com.tracksee.logic;

import org.apache.commons.validator.routines.EmailValidator;

import javax.ejb.Stateless;

/**
 * @author Ruslan Gunavardana.
 */
@Stateless
public class ValidatorBean {

    /**
     * Returns true if email address is valid, false in other case.
     * @param email validated email address
     */
    public boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    /**
     * (?=.*[0-9]) a digit must occur at least once
     * (?=.*[a-z]) a lower case letter must occur at least once
     * (?=.*[A-Z]) an upper case letter must occur at least once
     * (?=.*[@#$%^&+=]) a special character must occur at least once
     * (?=\\S+$) no whitespace allowed in the entire string
     * {6,28} at least 6-28 characters
     */
    private String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,28}$";

    public boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEXP);
    }
}
