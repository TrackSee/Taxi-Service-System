package ua.com.tracksee.dao;

import ua.com.tracksee.entities.AddressEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * @author  Katia Stetsiuk
 */
@Local
public interface AddressDAO {
    void addAddress(AddressEntity address);
    void deleteAddress(AddressEntity address);
    void updateAddress(AddressEntity address);
    List getAllAddressesByUserId(AddressEntity address);
    List<AddressEntity> getAddresses();

    AddressEntity getAddressByUserId(Integer userId, String name);
    List getAllAddressesByUserId(Integer userId);

}

