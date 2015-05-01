package ua.com.tracksee.dao;

import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Vadym Akymov
 * @author Ruslan Gunavardana
 * @author Katia Stetsiuk
 */
@Local
public interface UserDAO {
    //13 drivers per query by default
    int DRIVERS_PAGE_SIZE = 12;

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
     * @return count of driver pages in system
     */
    int getDriverPagesCount();

    /**
     * @param serviceUserID user_id for deleting
     * @author Katia Stetsiuk
     */
    void deleteUser(int serviceUserID);

    void updateUser(ServiceUserEntity serviceUserEntity);

    ServiceUserEntity getUserByEmail(String email);

    void createUser(ServiceUserEntity serviceUserEntity);

    /**
     * @throws ua.com.tracksee.dao.postrgresql.exceptions.ServiceUserNotFoundException when there
     *                                                                                 is no service user with such id
     */
    ServiceUserEntity getDriverByID(int id);


    ServiceUserEntity getUserById(int id);


    List<String> getDriversEmails();

    boolean checkUserByEmail(String email);

    Integer getUserIdByEmail(String email);

    /**
     * @author Vadym Akymov
     */
    void assignCar(String carNumber, Integer driverID);

}
