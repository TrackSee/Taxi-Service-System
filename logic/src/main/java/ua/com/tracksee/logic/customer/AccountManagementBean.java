package ua.com.tracksee.logic.customer;

import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.EmailBean;
import ua.com.tracksee.logic.ValidationBean;
import ua.com.tracksee.exception.RegistrationException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import static java.lang.Boolean.FALSE;
import static ua.com.tracksee.exception.RegistrationExceptionType.*;
import static ua.com.tracksee.logic.encryption.HashGenerator.getHash;
import static ua.com.tracksee.logic.encryption.PasswordUtils.generatePassword;

/**
 * Bean provides account registration and activation
 * functionality.
 *
 * @author Ruslan Gunavardana
*/
@Stateless
public class AccountManagementBean {

    //TODO redirect these to configs
    private static final int UNACTIVATED_USERS_MAX_DAYS = 30;
    private static final int SALT_SIZE = 8;

    private @EJB EmailBean emailBean;
    private @EJB ValidationBean validationBean;
    private @EJB UserDAO userDAO;

    public UserEntity getUserByLoginCredentials(String email, String loginPassword) {
        UserEntity user = userDAO.getUserByEmail(email);

        if (user != null) {
            String hashedLoginPassword = getHashedPassword(loginPassword, user.getSalt());

            // hash code of login and database passwords must be equal
            if (!hashedLoginPassword.equals(user.getPassword())) {
                user = null;
            }
        }

        return user;
    }

    /**
     * Activates registered user's account.
     *
     * @param userCode the code, that is used to identify unregistered user's account
     * @throws RegistrationException if the userCode is bad, or user is already active
     */
    public void activateCustomerUserAccount(String userCode) throws RegistrationException {
        Integer userId;
        try {
            userId = Integer.parseInt(userCode);
        } catch (NumberFormatException e) {
            throw new RegistrationException("Invalid link.", BAD_LINK);
        }
        if (userDAO.accountIsActivated(userId) != FALSE) {
            throw new RegistrationException("User is already activated.", USER_IS_ACTIVE);
        }

        userDAO.activateAccount(userId);
    }

    /**
     * Customer user registration method.
     *
     * @param email user's email
     * @param password user's password
     * @param phoneNumber user's phone number
     * @throws RegistrationException if invalid data passed
     */
    public void registerCustomerUser(String email, String password, String phoneNumber)
            throws RegistrationException
    {
        validateRegistrationData(email, password, phoneNumber);

        // hashing the password
        String salt = generateSalt();
        String hashedPassword = getHashedPassword(password, salt);


        // adding new user
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        user.setPhone(phoneNumber);
        Integer generatedId = userDAO.addUser(user);
        if (generatedId == null) {
            throw new RegistrationException("User already exists.", USER_EXISTS);
        }

        String userCode = generatedId.toString();
        emailBean.sendRegistrationEmail(email, userCode);
    }

    private void validateRegistrationData(String email, String password, String phoneNumber)
            throws RegistrationException
    {
        if (!validationBean.isValidEmail(email)) {
            throw new RegistrationException("Invalid email.", BAD_EMAIL);
        }
        if (!validationBean.isValidPassword(password)) {
            throw new RegistrationException("Invalid password.", BAD_PASSWORD);
        }
        if (phoneNumber != null && !phoneNumber.equals("") && !validationBean.isValidPhoneNumber(phoneNumber)) {
            throw new RegistrationException("Invalid phone number.", BAD_PHONE);
        }
    }

    private String generateSalt() {
        return generatePassword(SALT_SIZE, true, true, true, true);
    }

    private String getHashedPassword(String password, String salt) {
        return getHash(salt + password + salt);
    }

    public void clearUnactivatedRegistrations() {
        userDAO.clearUnactivatedAccounts(UNACTIVATED_USERS_MAX_DAYS);
    }
}