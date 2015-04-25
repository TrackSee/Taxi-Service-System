package ua.com.tracksee.logic;


import com.netcracker.tracksee.dao.UserDAO;
import com.netcracker.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.logic.exception.RegistrationException;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static java.lang.Boolean.FALSE;

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

//        if (userDAO.getUserByEmail(email) != null) {
//            return false;
//        }

        // adding new user
        ServiceUserEntity user = new ServiceUserEntity();
        user.setEmail(email);
        user.setPassword(password);
        user.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
        user.setActivated(FALSE);
        user.setPhone(phoneNumber);
        Integer generatedId = userDAO.addUser(user);

        String userCode = generatedId.toString();
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