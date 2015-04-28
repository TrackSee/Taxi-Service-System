package ua.com.tracksee.logic.customer;

import ua.com.tracksee.dao.AddressDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.AddressEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created on 27.04.2015.
 * @author Oleksandr Kozin
 */
@Stateless
public class CustomerBean {

    @EJB
    private UserDAO userDAO;
    @EJB
    private AddressDAO addressDAO;

    /**
     *
     * @param userId -
     * @param name -
     * @return -
     */
    public AddressEntity getAddressByUserId(long userId, String name) {
        return addressDAO.getAddressByUserId(userId, name);
    }

    /**
     *
     * @param userId -
     * @return p-
     */
    public List getAllAddressesByUserId(long userId) {
        return addressDAO.getAllAddressesByUserId(userId);
    }

    /**
     *
     * @param address number of part which is needed
     * @return part of addresses
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
