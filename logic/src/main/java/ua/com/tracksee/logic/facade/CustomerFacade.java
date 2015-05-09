package ua.com.tracksee.logic.facade;

import org.postgresql.geometric.PGpoint;
import ua.com.tracksee.dao.AddressDAO;
import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.json.FavoritePlaceDTO;
import ua.com.tracksee.json.LocationDTO;
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

    public void activateUser(String userCode) throws RegistrationException {
        registrationBean.activateCustomerUserAccount(userCode);
    }

    /**
     * Returns a list of customer user's favourite addresses.
     */
    public List<AddressEntity> getFavoritePlacesFor(int userId) {
        return addressDAO.getAddressesByUserId(userId);
    }

    /**
     * Saves favorite place for customer user.
     *
     * @param userId customer user id
     * @param favoritePlaceDto favorite place to save
     * @return true if successfully saved, false if not
     */
    public boolean addFavoritePlaceFor(Integer userId, FavoritePlaceDTO favoritePlaceDto) {
        String name = favoritePlaceDto.getName();
        LocationDTO locationDTO = favoritePlaceDto.getLocation();
        PGpoint location = new PGpoint(locationDTO.getLat(), locationDTO.getLng());
        return addressDAO.addAddress(new AddressEntity(name, userId, location));
    }

    /**
     * Removes favourite place for customer user.
     *
     * @param userId customer user id
     * @param name
     * @return
     */
    public boolean removeFavouritePlaceFor(Integer userId, String name) {
        return addressDAO.deleteAddress(new AddressEntity(name, userId));
    }
}
