package ua.com.tracksee.logic;

import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.ServiceUserEntity;

import ua.com.tracksee.logic.EmailBean;
import ua.com.tracksee.logic.ValidationBean;
import ua.com.tracksee.logic.exception.RegistrationException;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.mail.MessagingException;

import static java.lang.Boolean.FALSE;
import static ua.com.tracksee.logic.exception.RegistrationExceptionType.*;

/**
 * Bean provides account registration and activation
 * functionality.
 *
 * @author Ruslan Gunavardana
*/
@Stateless
public class RegistrationBean {
    private static final int UNACTIVATED_USERS_MAX_DAYS = 30;

    private @EJB EmailBean emailBean;
    private @EJB ValidationBean validationBean;
    private @EJB UserDAO userDAO;

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

        // adding new user
        ServiceUserEntity user = new ServiceUserEntity();
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phoneNumber);
        Integer generatedId = userDAO.addUser(user);
        if (generatedId == null) {
            throw new RegistrationException("User already exists.", USER_EXISTS);
        }

        String userCode = generatedId.toString();
        try {
            emailBean.sendRegistrationEmail(user, userCode);
        } catch (MessagingException e) {
            throw new RegistrationException("Failed to send registration email.", EMAIL_SENDING_FAIL);
        }
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
        if (phoneNumber != null && !validationBean.isValidPhoneNumber(phoneNumber)) {
            throw new RegistrationException("Invalid phone number.", BAD_PHONE);
        }
    }

    @Schedule(dayOfMonth = "1", hour = "4")
    public void clearUnactivatedRegistrations() {
        userDAO.clearUnactivatedAccounts(UNACTIVATED_USERS_MAX_DAYS);
    }
}