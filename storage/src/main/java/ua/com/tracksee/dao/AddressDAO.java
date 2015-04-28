package ua.com.tracksee.dao;

import ua.com.tracksee.entities.AddressEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by kstes_000 on 23-Apr-15.
 */
@Local
public interface AddressDAO {
    public void addAddress(AddressEntity address);
    public void deleteAddress(AddressEntity address);
    public void updateAddress(AddressEntity addressEntity);
    List<AddressEntity> getAddresses();

}

