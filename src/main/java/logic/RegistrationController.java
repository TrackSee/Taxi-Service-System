package logic;

import entity.Role;
import entity.Sex;
import entity.User;
import util.IdGenerator;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Ruslan Gunavardana.
 */
public class RegistrationController {
    EmailController emailController = new EmailController();
    Map<String, User> unactivatedUsers;

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

    public boolean registerUser(String email, String password, Role role, Sex sex)
            throws SQLException, RegistrationException
    {
            //TODO check if user registered
//        if (dao.get(email) != null) {
//            return false;
//        }

        // adding new user
        User user = new User();
        user.setRole(Role.CUSTOMER_USER);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setSex(sex);
        try {
            String userCode = IdGenerator.generateId();
            emailController.sendRegistrationEmail(user, userCode);
            unactivatedUsers.put(userCode, user);
        } catch (MessagingException e) {
            throw new RegistrationException("Failed to send registration email.");
        }
        return true;
    }
}
