package ua.com.tracksee.dao;

import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.UserEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Vadym Akymov
 * @author Ruslan Gunavardana
 * @author Katia Stetsiuk
 * @author Sharaban Sasha
 */
@Local
public interface UserDAO {
    //13 drivers per query by default
    int DRIVERS_PAGE_SIZE = 12;

    /**
     * @param partNumber - number of data part
     * @return list, containing the part of drivers(default size of list is 10)
     */
    List<UserEntity> getDrivers(int partNumber);
    List<UserEntity> getUsers();

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
     * @return TRUE if the user is activated, FALSE if user is not activated,
     * and null if user not exists
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
    Integer addUser(UserEntity user);

    /**
     * This method adds record in
     * database about user that
     * make order but which is
     * not registered.
     *
     * @author Sharaban Sasha
     * @param user - entity that contain information about user that made order
     * @return id the added record
     */
    Integer addUnregisteredUser(UserEntity user);

    /**
     * @return count of driver pages in system
     */
    int getDriverPagesCount();

    /**
     * @param serviceUserID user_id for deleting
     * @author Katia Stetsiuk
     */
    void deleteUser(int serviceUserID);

    void updateUser(UserEntity userEntity);
    /**
     * @author Sharaban Sasha
     */
    public List<UserEntity> getUnregisteredUsers();
    /**
     * Returns user with the specified email address.
     *
     * @param email user's email
     * @return user with the specified email address or null if user not exists
     */
    UserEntity getUserByEmail(String email);

    void createUser(UserEntity userEntity);

    /**
     * @throws ua.com.tracksee.dao.implementation.exceptions.ServiceUserNotFoundException when there
     *                                                                                 is no service user with such id
     */
    UserEntity getDriverByID(int id);


    UserEntity getUserById(int id);

    List<String> getDriversEmails();



    Integer getUserIdByEmail(String email);

    /**
     * @author Vadym Akymov
     */
    void assignCar(String carNumber, Integer driverID);
    /**
     * @author Sharaban Sasha
     */
    boolean checkBlackListUserByEmail(String email);


    /**
     * @author Katia Stetsiuk
     * @param email to get customers with such email
     * @return
     */
    List<UserEntity> getCustomersByEmail(String email);

    /**
     * @author Katia Stetsiuk
     * @param email to get drivers with such email
     * @return
     */
    List<UserEntity> getDriversByEmail(String email);

    public CarEntity getDriversCar(UserEntity driver);

}
