package com.netcracker.bootcamp.tracksee.logic;

import com.netcracker.bootcamp.tracksee.entity.Role;
import com.netcracker.bootcamp.tracksee.entity.Sex;
import com.netcracker.bootcamp.tracksee.entity.UnactivatedUser;
import com.netcracker.bootcamp.tracksee.logic.exception.RegistrationException;
import com.netcracker.bootcamp.tracksee.util.IdGenerator;

import javax.ejb.*;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.sql.Timestamp;

import static javax.ejb.LockType.WRITE;

/**
 * @author Ruslan Gunavardana.
 */
@Stateless
public class RegistrationBean {
    @EJB private EmailBean emailBean;
    @EJB private ValidatorBean validatorBean;

    public boolean activateUser(String userCode) throws SQLException {
        //TODO add to db

        return false;
    }

    public boolean registerCustomerUser(String email, String password, Sex sex)
            throws SQLException, RegistrationException
    {
        if (!validatorBean.isValidEmail(email)) {
            throw new RegistrationException("Invalid email.", "inv-email");
        }
        if (!validatorBean.isValidPassword(password)) {
            throw new RegistrationException("Invalid password.", "inv-password");
        }

    //TODO check if user registered
//        if (dao.getUserByEmail(email) != null) {
//            return false;
//        }

        // adding new user
        UnactivatedUser user = new UnactivatedUser();
        user.setRole(Role.CUSTOMER_USER);
        user.setEmail(email);
        user.setPassword(password);
        user.setSex(sex);
        user.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
        String userCode = IdGenerator.generateId();

        try {
            emailBean.sendRegistrationEmail(user, userCode);
        } catch (MessagingException e) {
            throw new RegistrationException("Failed to send registration email.", "send-fail");
        }
        return true;
    }

    @Schedule(dayOfWeek = "Sun", hour = "4")
    public void clearUnactivatedRegistrations() {
        //TODO redirect code to storage module
        EntityManager em = null;
        String sql = "DELETE FROM service_user " +
                "WHERE activated = FALSE " +
                "AND CURRENT_TIMESTAMP - registration_date  > '1 month' :: INTERVAL";
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }
}