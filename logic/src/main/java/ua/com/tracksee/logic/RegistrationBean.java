package ua.com.tracksee.logic;

import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entity.Role;
import ua.com.tracksee.entity.Sex;
import ua.com.tracksee.entity.UnactivatedUser;
import ua.com.tracksee.logic.exception.RegistrationException;
import ua.com.tracksee.util.IdGenerator;

import javax.ejb.*;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class RegistrationBean {
    private static final int UNACTIVATED_USERS_MAX_DAYS = 30;

    @EJB
    private EmailBean emailBean;
    @EJB
    private ValidatorBean validatorBean;
    @EJB
    private UserDAO userDAO;

    public void activateCustomerUserAccount(String userCode) throws RegistrationException {
        Integer userId;
        try {
            userId = Integer.parseInt(userCode);
        } catch (NumberFormatException e) {
            throw new RegistrationException("Invalid link.", "bad-link");
        }

        if (userDAO.accountIsActivated(userId)) {
            throw new RegistrationException("User is already activated.", "is-active");
        }

        userDAO.activateAccount(userId);
    }

    public void registerCustomerUser(String email, String password, String phoneNumber)
            throws SQLException, RegistrationException
    {
        if (!validatorBean.isValidEmail(email)) {
            throw new RegistrationException("Invalid email.", "bad-email");
        }
        if (!validatorBean.isValidPassword(password)) {
            throw new RegistrationException("Invalid password.", "bad-password");
        }
        if (phoneNumber != null && !validatorBean.isValidPhoneNumber(phoneNumber)) {
            throw new RegistrationException("Invalid phone number.", "bad-phone");
        }

    //TODO check if user registered
//        if (userDAO.getUserByEmail(email) != null) {
//            return false;
//        }

        // adding new user
        UnactivatedUser user = new UnactivatedUser();
        user.setRole(Role.CUSTOMER_USER);
        user.setEmail(email);
        user.setPassword(password);
        user.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
        String userCode = IdGenerator.generateId();
        //TODO userDAO.addUnactivatedUser(user);

        try {
            emailBean.sendRegistrationEmail(user, userCode);
        } catch (MessagingException e) {
            throw new RegistrationException("Failed to send registration email.", "send-fail");
        }
    }

    @Schedule(dayOfMonth = "1", hour = "4")
    public void clearUnactivatedRegistrations() {
        userDAO.clearUnactivatedAccounts(UNACTIVATED_USERS_MAX_DAYS);
    }
}