package ua.com.tracksee.logic;

import org.junit.Test;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.logic.exception.RegistrationException;

import javax.ejb.EJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Ruslan Gunavardana
 */
public class RegistrationBeanTest {

    private @EJB RegistrationBean registrationBean;
    private @EJB UserDAO userDAO;

    private static final String TEST_EMAIL = "rusan.rus@gmail.com";
    private static final String TEST_PASSWORD = "very@Secure";
    private static final String TEST_PHONE = "+380635005050";

    @Test(expected = RegistrationException.class)
    public void testRegisterBadCustomerUser() throws Exception {
        registrationBean.registerCustomerUser("badmail@", "nonsecurepassword", null);
    }

    @Test
    public void testRegisterGoodCustomerUser() throws Exception {
        clearUserIfExists(TEST_EMAIL);
        registrationBean.registerCustomerUser(TEST_EMAIL, TEST_PASSWORD, TEST_PHONE);
        ServiceUserEntity newUser = userDAO.getUserByEmail(TEST_EMAIL);
        assertFalse(newUser.getActivated());
        userDAO.deleteUser(newUser);
    }

    @Test
    public void testRegisterAndActivate() throws Exception {
        ServiceUserEntity unactivatedUser;
        ServiceUserEntity activatedUser;

        clearUserIfExists(TEST_EMAIL);
        registrationBean.registerCustomerUser(TEST_EMAIL, TEST_PASSWORD, TEST_PHONE);
        unactivatedUser = userDAO.getUserByEmail(TEST_EMAIL);
        assertFalse(unactivatedUser.getActivated());

        registrationBean.activateCustomerUserAccount(unactivatedUser.getUserId().toString());
        activatedUser = userDAO.getUserByEmail(TEST_EMAIL);
        assertTrue(activatedUser.getActivated());
    }

    private void clearUserIfExists(String email) {
        ServiceUserEntity oldUser = userDAO.getUserByEmail(email);
        if (oldUser != null) {
            userDAO.deleteUser(oldUser);
        }
    }
}
