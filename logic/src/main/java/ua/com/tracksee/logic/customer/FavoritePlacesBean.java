package ua.com.tracksee.logic.customer;

import org.postgresql.geometric.PGpoint;
import ua.com.tracksee.dao.AddressDAO;
import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.entities.AddressEntityPK;
import ua.com.tracksee.json.FavoritePlaceDTO;
import ua.com.tracksee.json.LocationDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

import static ua.com.tracksee.util.GeometryConverter.toLocationDTO;
import static ua.com.tracksee.util.GeometryConverter.toPoint;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class FavoritePlacesBean {
    private @EJB AddressDAO addressDAO;

    /**
     * Returns a list of customer user's favourite addresses.
     */
    public List<FavoritePlaceDTO> getFavoritePlacesFor(int userId) {
        List<AddressEntity> entities = addressDAO.getAddressesByUserId(userId);
        List<FavoritePlaceDTO> dtoList = new ArrayList<>(entities.size());

        // filling dtoList
        for (AddressEntity entity : entities) {
            LocationDTO locationDTO = toLocationDTO(entity.getLocation());
            FavoritePlaceDTO placeDTO = new FavoritePlaceDTO(entity.getName(), locationDTO);
            dtoList.add(placeDTO);
        }

        return dtoList;
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
        LocationDTO loc = favoritePlaceDto.getLocation();
        return addressDAO.addAddress(new AddressEntity(name, userId, toPoint(loc)));
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
        LocationDTO loc = newData.getLocation();
        AddressEntity newEntity = new AddressEntity(newData.getName(), userId, toPoint(loc));
        return addressDAO.updateAddress(new AddressEntityPK(oldName, userId), newEntity);
    }
}
