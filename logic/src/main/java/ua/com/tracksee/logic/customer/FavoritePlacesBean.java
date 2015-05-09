package ua.com.tracksee.logic.customer;

import org.postgresql.geometric.PGpoint;
import ua.com.tracksee.dao.AddressDAO;
import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.entities.AddressEntityPK;
import ua.com.tracksee.json.FavoritePlaceDTO;
import ua.com.tracksee.json.LocationDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class FavoritePlacesBean {
    private @EJB AddressDAO addressDAO;

    public FavoritePlacesBean() {
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
     * @param name place name
     * @return true if successfully removed, false if not
     */
    public boolean removeFavoritePlaceFor(Integer userId, String name) {
        return addressDAO.deleteAddress(new AddressEntityPK(name, userId));
    }

    /**
     * Updates favorite place for customer user.
     *
     * @param userId customer user id
     * @param oldName old place name
     * @param newData new place data transfer object
     * @return true if successfully updated, false if not
     */
    public boolean updateFavoritePlaceFor(Integer userId, String oldName, FavoritePlaceDTO newData) {
        LocationDTO locationDTO = newData.getLocation();
        PGpoint location = new PGpoint(locationDTO.getLat(), locationDTO.getLng());
        AddressEntity newEntity = new AddressEntity(newData.getName(), userId, location);
        return addressDAO.updateAddress(new AddressEntityPK(oldName, userId), newEntity);
    }
}
