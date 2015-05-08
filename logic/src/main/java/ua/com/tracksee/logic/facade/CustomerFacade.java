package ua.com.tracksee.logic.facade;

import ua.com.tracksee.dao.AddressDAO;
import ua.com.tracksee.entities.AddressEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class CustomerFacade {
    private @EJB AddressDAO addressDAO;

    /**
     * Returns a list of user's favourite addresses.
     */
    public List<AddressEntity> getFavouritePlacesFor(int userId) {
        return addressDAO.getAddressesByUserId(userId);
    }
}
