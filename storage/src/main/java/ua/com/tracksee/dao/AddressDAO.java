package ua.com.tracksee.dao;

import ua.com.tracksee.entities.AddressEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by kstes_000 on 23-Apr-15.
 */
@Local
public interface AddressDAO {
    void addAddress(AddressEntity address);
    void deleteAddress(AddressEntity address);
    void updateAddress(AddressEntity address);
    List getAllAddressesByUserId(AddressEntity address);
    List<AddressEntity> getAddresses(int partNumber);

}

