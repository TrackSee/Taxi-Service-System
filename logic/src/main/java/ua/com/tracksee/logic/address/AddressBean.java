package ua.com.tracksee.logic.address;

import ua.com.tracksee.dao.AddressDAO;
import ua.com.tracksee.entities.AddressEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created on 28.04.2015
 * @author Oleksandr Kozin
 */
@Stateless
public class AddressBean {

    @EJB
    private AddressDAO addressDAO;

    public AddressBean() {
    }

    /**
     *
     * @param userId -
     * @param name -
     * @return -
     */
    public AddressEntity getAddressByUserId(Integer userId, String name) {
//        return addressDAO.getAddressByUserId(userId, name);
        return null;
    }

    /**
     *
     * @param userId -
     * @return p-
     */
    public List<AddressEntity> getAllAddressesByUserId(Integer userId) {
//        return addressDAO.getAllAddressesByUserId(userId);
        return null;
    }

    /**
     *
     * @param address -
     * @return -
     */
    public List getAllAddressesByUserId(AddressEntity address){
        return addressDAO.getAllAddressesByUserId(address);
    }

    /**
     *
     * @param address -
     */
    public void addAddress(AddressEntity address){
        addressDAO.addAddress(address);
    }

    /**
     *
     * @param address -
     */
    public void deleteAddress(AddressEntity address){
        addressDAO.deleteAddress(address);
    }

    /**
     *
     * @param address -
     */
    public void updateAddress(AddressEntity address){
        addressDAO.updateAddress(address);
    }
}
