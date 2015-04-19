package com.netcracker.bootcamp.tracksee.logic;

import com.netcracker.bootcamp.tracksee.entity.Role;
import com.netcracker.bootcamp.tracksee.entity.Sex;
import com.netcracker.bootcamp.tracksee.entity.UnactivatedUser;
import com.netcracker.bootcamp.tracksee.util.IdGenerator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.mail.MessagingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static javax.ejb.LockType.WRITE;

/**
 * @author Ruslan Gunavardana.
 */
@Singleton
@Local
public class RegistrationBean {
    @EJB
    private EmailBean emailBean;
    private Map<String, UnactivatedUser> unactivatedUsers;

    @PostConstruct
    private void postConstruct() {
        unactivatedUsers = new HashMap<>();
    }

    @Lock(WRITE)
    public boolean activateUser(String userCode) throws SQLException {
        /*
        int userId;
        try {
            userId = parseInt(userCode);
        } catch (NumberFormatException e) {
            return false; // not a valid userCode
        }
        UserDAO dao = SpringContext.getInstance().getBean(DAOFactory.class).getUserDAO();
        User user = dao.get(userId);
        if (!user.isActivated()) {
            user.setActivated(true);
            return dao.update(user);
        }
        */
        //TODO add to db
        unactivatedUsers.remove(userCode);

        return false;
    }

    @Lock(WRITE)
    public boolean registerUser(String email, String password, Role role, Sex sex)
            throws SQLException, RegistrationException
    {
    //TODO check if user registered
//        if (dao.get(email) != null) {
//            return false;
//        }

        // adding new user
        UnactivatedUser user = new UnactivatedUser();
        user.setRole(Role.CUSTOMER_USER);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setSex(sex);
        user.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
        try {
            String userCode = IdGenerator.generateId();
            emailBean.sendRegistrationEmail(user, userCode);
            unactivatedUsers.put(userCode, user);
        } catch (MessagingException e) {
            throw new RegistrationException("Failed to send registration email.");
        }
        return true;
    }
}