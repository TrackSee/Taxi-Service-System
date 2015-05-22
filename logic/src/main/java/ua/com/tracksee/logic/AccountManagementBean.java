package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.exception.RegistrationException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
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
    private static final Logger logger = LogManager.getLogger(AccountManagementBean.class);

    //TODO redirect these to configs
    private static final int UNACTIVATED_USERS_MAX_DAYS = 30;
    private static final int SALT_SIZE = 8;

    @Inject
    private ValidatorFactory validatorFactory;

    @Inject
    private Validator validator;

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
        logger.debug("Activation attempt using code: {}", userCode);

        Integer userId;
        try {
            userId = Integer.parseInt(userCode);
        } catch (NumberFormatException e) {
            throw new RegistrationException("Invalid link.", BAD_LINK.getCode());
        }
        if (userDAO.accountIsActivated(userId) != FALSE) {
            throw new RegistrationException("User is already activated.", USER_IS_ACTIVE.getCode());
        }

        userDAO.activateAccount(userId);
    }

    /**
     * Customer user registration method.
     * Registration:
     * <table>
     *     <thead>
     *         <tr>
     *             <th>For</th>
     *             <th>Result</th>
     *             <th>Sends email</th>
     *         </tr>
     *     </thead>
     *     <tbody>
     *         <tr>
     *             <td>not created user</td>
     *             <td>PASSES, returns true</td>
     *             <td>+</td>
     *         </tr>
     *         <tr>
     *             <td>unactivated user</td>
     *             <td>PASSES, returns false</td>
     *             <td>+</td>
     *         </tr>
     *         <tr>
     *             <td>blocked user</td>
     *             <td>FAILS, throws exception</td>
     *             <td>-</td>
     *         </tr>
     *         <tr>
     *             <td>registered user</td>
     *             <td>FAILS, throws exception</td>
     *             <td>-</td>
     *         </tr>
     *         <tr>
     *             <td>invalid data</td>
     *             <td>FAILS, throws exception</td>
     *             <td>-</td>
     *         </tr>
     *     </tbody>
     * </table>
     *
     * @param user user to register
     * @throws RegistrationException if invalid data passed or
     * user has already been activated or blocked in the system
     * @return false if user was previously registered (but not activated),
     * true if the user is new
     */
    public boolean registerCustomerUser(UserEntity user) throws RegistrationException {
        validateRegistrationData(user);

        // hashing the password
        String salt = generateSalt();
        String hashedPassword = getHashedPassword(user.getPassword(), salt);

        // adding new user
        user.setPassword(hashedPassword);
        user.setSalt(salt);

        UserEntity persistedUser = userDAO.getUserByEmail(user.getEmail());
        if (persistedUser == null) {

            // simply adding new user
            userDAO.addUser(user);
        } else {
            user.setUserId(persistedUser.getUserId());
            user.setActivated(persistedUser.getActivated());

            if (!user.getActivated().equals(FALSE)) {
                throw new RegistrationException("User already exists.", USER_EXISTS.getCode());
            }

            // if we are here, user is not activated, so just update him
            userDAO.updateUser(user);
        }

        String userCode = user.getUserId().toString();
        logger.debug("Generated userCode: {}", userCode);
        emailBean.sendRegistrationEmail(user.getEmail(), userCode);

        // returning if the new user was generated in db
        return persistedUser != null;
    }

    private void validateRegistrationData(UserEntity user) throws RegistrationException {
        StringBuilder builder = new StringBuilder();
        boolean invalid = false;

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        if (violations.size() > 0) {
            invalid = true;
            for (ConstraintViolation<UserEntity> v : violations) {
                builder.append(v.getMessage()).append(' ');
            }
        }
        if (!validationBean.isValidEmail(user.getEmail())) {
            invalid = true;
            builder.append(BAD_EMAIL.getCode()).append(' ');
        }
        if (!validationBean.isValidPassword(user.getPassword())) {
            invalid = true;
            builder.append(BAD_PASSWORD.getCode()).append(' ');
        }
        String phone = user.getPhone();
        if (phone != null && !phone.equals("") && !validationBean.isValidPhoneNumber(phone)) {
            invalid = true;
            builder.append(BAD_PHONE.getCode()).append(' ');
        }

        if (invalid) {
            String msg = builder.toString();
            throw new RegistrationException("Registration field validation failed: " + msg, msg);
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