package ua.com.tracksee.dao;

import ua.com.tracksee.entities.AddressEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * @author  Katia Stetsiuk
 */
@Local
public interface AddressDAO {
    public void addAddress(AddressEntity address);
    public void deleteAddress(AddressEntity address);
    public void updateAddress(AddressEntity addressEntity);
    List<AddressEntity> getAddresses();

    /**
     * @author  Sharaban Sasha
     */
    AddressEntity getAddressByUserId(int id);

}

