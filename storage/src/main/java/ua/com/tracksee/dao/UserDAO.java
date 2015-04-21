package ua.com.tracksee.dao;

import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Vadym Akymov
 * @author Ruslan Gunavardana
 */
@Local
public interface UserDAO {
    /**
     * @param partNumber - number of data part
     * @return list, containing the part of drivers(default size of list is 10)
     */
    List<ServiceUserEntity> getDrivers(int partNumber);

    /**
     * Method clears any unactivated users that exist more than
     * unactivatedDays.
     *
     * @param unactivatedDays days of unactivated account existence
     */
    void clearUnactivatedAccounts(int unactivatedDays);

    /**
     * Checks if the user with specified userId is activated.
     *
     * @param userId specified user's id
     * @return if the user is activated.
     */
    Boolean accountIsActivated(Integer userId);

    /**
     * Activates the account of user with specified userId
     *
     * @param userId id of activated user
     */
    void activateAccount(Integer userId);
}
