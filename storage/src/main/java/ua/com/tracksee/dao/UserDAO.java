package ua.com.tracksee.dao;

import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Vadym Akymov
 * @author Ruslan Gunavardana
 * @author KatiaStetsiuk
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
    boolean activateAccount(Integer userId);

    /**
     * Returns false if user is activated.
     *
     * @param user
     */
    Integer addUser(ServiceUserEntity user);

    /**
     * @author Vadym Akymov
     * @return count of drivers in system
     */
    int getDriversCount();
    void deleteUser(ServiceUserEntity serviceUserEntity);
    void updateUser(ServiceUserEntity serviceUserEntity);
    void createUser(ServiceUserEntity serviceUserEntity);
}
