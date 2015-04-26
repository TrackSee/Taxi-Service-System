package ua.com.tracksee.logic;

import org.apache.commons.validator.routines.EmailValidator;

import javax.ejb.Stateless;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class ValidatorBean {

    /**
     * Returns true if email address is valid, false in other case.
     *
     * @param email validated email address
     */
    public boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    /**
     * (?=.*[0-9])         - a digit must occur at least once
     * (?=.*[a-z])         - a lower case letter must occur at least once
     * (?=.*[A-Z])         - an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])    - a special character must occur at least once
     * (?=\\S+$)           - no whitespace allowed in the entire string
     * [a-zA-Z0-9@#$%^&+=] - allowed characters
     * {6,28}              - at least 6-28 characters
     */
    private String PASSWORD_REGEXP = "^(?=.*[0-9@#$%^&+=])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9@#$%^&+=]{6,28}$";

    public boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEXP);
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        int digitCount = 0;
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if (isDigit(c)) {
                ++digitCount;
            }
        }
        return digitCount > 4 & digitCount < 16;
    }

    /**
     * This method validate address
     *
     * @param address - address that must be validate
     * @return address validation state
     * @author Sharaban Sasha
     */
    public boolean isValidAddress(String address) {
        /**
         * check address
         * return is valid address
         */
        return true;
    }

    /**
     * This method validate phone number
     *
     * @param phoneNumber - phone number that must be validate
     * @return phone number validation state
     * @author Sharaban Sasha
     */
    public boolean isValidOrderPhoneNumber(String phoneNumber) {
        String regex = "^ *\\+?([0-9] ?){6,14}[0-9]( *)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
