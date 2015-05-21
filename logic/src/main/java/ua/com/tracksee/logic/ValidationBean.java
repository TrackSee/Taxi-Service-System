package ua.com.tracksee.logic;

import org.apache.commons.validator.routines.EmailValidator;

import javax.ejb.Stateless;
import java.sql.Timestamp;

import static java.lang.Character.isDigit;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class ValidationBean {

    private static final int MINIMAL_PASSWORD_LENGTH = 6;
    private static final int MAXIMAL_PASSWORD_LENGTH = 50;

//    /**
//     * (?=.*[0-9])         - a digit must occur at least once
//     * (?=.*[a-z])         - a lower case letter must occur at least once
//     * (?=.*[A-Z])         - an upper case letter must occur at least once
//     * (?=.*[@#$%^&+=])    - a special character must occur at least once
//     * (?=\\S+$)           - no whitespace allowed in the entire string
//     * [a-zA-Z0-9@#$%^&+=] - allowed characters
//     * {6,28}              - at least 6-28 characters
//     */
//    private String PASSWORD_REGEXP = "^(?=.*[0-9@#$%^&+=_])(?=.*[a-z])(?=.*[A-Z])[\\w@#$%^&+=]{6,28}$";

    /**
     * Allows + as the first symbol, and a lot of digits, parentesis.
     *
     * +38063-529(08)(07) is valid
     * 38063-529+(08)(07) is not
     */
    private static String PHONE_NUMBER_REGEXP = "^\\+?[0-9 ()-]{5,27}$";

    /**
     * Returns true if email address is valid, false in other case.
     * @param email validated email address
     */
    public boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public boolean isValidPassword(String password) {
        return password.length() > MINIMAL_PASSWORD_LENGTH && password.length() < MAXIMAL_PASSWORD_LENGTH;
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        int digitCount = countDigits(phoneNumber);

        return digitCount > 4 & digitCount < 16 & phoneNumber.matches(PHONE_NUMBER_REGEXP);
    }

    private int countDigits(String string) {
        int digitCount = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (isDigit(c)) {
                ++digitCount;
            }
        }
        return digitCount;
    }

    /**
     * Returns date converted from
     * Timestamp to string with small
     * changes that needed for showing
     * on web page.
     *
     * @author Sharaban Sasha
     * @param timestamp - date in Timestamp format
     * @return date in string format ready for showing on web page
     */
    public String convertDateForShow(Timestamp timestamp){
        if(timestamp!=null){
            String date= timestamp.toString();
            String[] dateParts=date.split(":00");
        return dateParts[0];
        }
        return "";
    }
}
