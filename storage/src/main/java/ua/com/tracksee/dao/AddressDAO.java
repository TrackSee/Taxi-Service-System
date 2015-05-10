package ua.com.tracksee.dao;

import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.entities.AddressEntityPK;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
@Local
public interface AddressDAO {
    AddressEntity getAddress(AddressEntityPK pk);
    List<AddressEntity> getAddressesByUserId(int id);
    boolean addAddress(AddressEntity address);
    boolean updateAddress(AddressEntityPK pk, AddressEntity newValue);
    boolean deleteAddress(AddressEntityPK address);
}

