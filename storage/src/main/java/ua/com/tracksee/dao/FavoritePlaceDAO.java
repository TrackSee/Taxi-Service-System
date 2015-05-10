package ua.com.tracksee.dao;

import ua.com.tracksee.entities.FavoritePlaceEntity;
import ua.com.tracksee.entities.FavoritePlaceEntityPK;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
@Local
public interface FavoritePlaceDAO {
    FavoritePlaceEntity getAddress(FavoritePlaceEntityPK pk);
    List<FavoritePlaceEntity> getAddressesByUserId(int id);
    boolean addAddress(FavoritePlaceEntity address);
    boolean updateAddress(FavoritePlaceEntityPK pk, FavoritePlaceEntity newValue);
    boolean deleteAddress(FavoritePlaceEntityPK address);
}

