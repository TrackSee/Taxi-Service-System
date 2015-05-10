package ua.com.tracksee.logic.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.json.FavoritePlaceDTO;
import ua.com.tracksee.logic.customer.FavoritePlacesBean;
import ua.com.tracksee.logic.customer.RegistrationBean;
import ua.com.tracksee.exception.RegistrationException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Session Facade Bean for customer user subsystem.
 * Used to access any taxi service system functionality
 * for customer users.
 *
 * @author Ruslan Gunavardana
 * @author Vadym Akymov
 */
@Stateless
public class CustomerFacade {
    private static final Logger logger = LogManager.getLogger();

    private @EJB TaxiOrderDAO taxiOrderDAO;
    private @EJB RegistrationBean registrationBean;
    private @EJB FavoritePlacesBean favoritePlacesBean;

    public List<TaxiOrderEntity> getOrdersPerPage(OrderStatusBO orderStatus, int userID, int pageNumber){
        switch (orderStatus){
            case ACTIVE: return taxiOrderDAO.getCustomerActiveOrdersPerPage(userID, pageNumber);
            case COMPLETED: return taxiOrderDAO.getCustomerOldOrdersPerPage(userID, pageNumber);
            default:
                logger.warn("wrong order status param");
                throw new IllegalArgumentException("wrong order status param");
        }
    }
    /** Registers new account with the specified credentials. */
    public void registerUser(String email, String password, String phoneNumber)
            throws RegistrationException
    {
        registrationBean.registerCustomerUser(email, password, phoneNumber);
    }

    /** Activates user account with the specified code. */
    public void activateUser(String userCode) throws RegistrationException {
        registrationBean.activateCustomerUserAccount(userCode);
    }

    /** Returns a list of customer user's favourite addresses. */
    public List<FavoritePlaceDTO> getFavoritePlacesFor(int userId) {
        return favoritePlacesBean.getFavoritePlacesFor(userId);
    }

    /**
     * Saves favorite place for customer user.
     *
     * @param userId customer user id
     * @param favoritePlaceDto favorite place to save
     * @return true if successfully saved, false if not
     */
    public boolean addFavoritePlaceFor(Integer userId, FavoritePlaceDTO favoritePlaceDto) {
        return favoritePlacesBean.addFavoritePlaceFor(userId, favoritePlaceDto);
    }

    /**
     * Removes favourite place for customer user.
     *
     * @param userId customer user id
     * @param name place name
     * @return true if successfully removed, false if not
     */
    public boolean removeFavoritePlaceFor(Integer userId, String name) {
        return favoritePlacesBean.removeFavoritePlaceFor(userId, name);
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
        return favoritePlacesBean.updateFavoritePlaceFor(userId, oldName, newData);
    }
}
