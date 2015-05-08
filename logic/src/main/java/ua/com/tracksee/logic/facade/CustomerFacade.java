package ua.com.tracksee.logic.facade;

import ua.com.tracksee.dao.AddressDAO;
import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.logic.RegistrationBean;
import ua.com.tracksee.logic.exception.RegistrationException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class CustomerFacade {
    private @EJB AddressDAO addressDAO;
    private @EJB RegistrationBean registrationBean;

    public void registerUser(String email, String password, String phoneNumber)
            throws RegistrationException
    {
        registrationBean.registerCustomerUser(email, password, phoneNumber);
    }

    /**
     * Returns a list of user's favourite addresses.
     */
    public List<AddressEntity> getFavouritePlacesFor(int userId) {
        return addressDAO.getAddressesByUserId(userId);
    }
}
