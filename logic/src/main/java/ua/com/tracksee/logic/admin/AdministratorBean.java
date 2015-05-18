package ua.com.tracksee.logic.admin;

import ua.com.tracksee.dao.CarDAO;
import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.implementation.ServiceUserDaoBeen;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.enumartion.CarCategory;
import ua.com.tracksee.error.PersistError;
import ua.com.tracksee.exception.CreateException;
import ua.com.tracksee.logic.ValidationBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static ua.com.tracksee.exception.CreateExceptionType.BAD_EMAIL;
import static ua.com.tracksee.exception.CreateExceptionType.BAD_PHONE;

/**
 * Created by Vadym_Akymov on 22.04.15.
 */
@Stateless
public class AdministratorBean {

    private @EJB ValidationBean validationBean;
    private @EJB UserDAO userDAO;
    private @EJB CarDAO carDAO;
    private @EJB ServiceUserDaoBeen serviceUserDaoBeen;
    private @EJB TaxiPriceDAO taxiPriceDAO;

    private void validateRegistrationData(UserEntity user)
            throws CreateException

    {
        System.out.println("begin");
        if (!validationBean.isValidEmail(user.getEmail())) {
            throw new CreateException("Invalid email.", BAD_EMAIL);
        }
//        if (!validationBean.isValidPassword(user.getPassword())) {
//            throw new CreateException("Invalid password.", BAD_PASSWORD);
//        }
        if (user.getPhone() != null && !user.getPhone().equals("") && !validationBean.isValidPhoneNumber(user.getPhone())) {
            throw new CreateException("Invalid phone number.", BAD_PHONE);
        }
    }


    /**
     * @author Katia Stetsiuk
     * @return list of users
     */
    public List<UserEntity> getUsers(){return userDAO.getUsers();}
    /**
     * @author Vadym_Akymov
     * @param partNumber - number of part which is neeeded
     * This method return part of drivers
     */

    public List<UserEntity> getDrivers(int partNumber){
        return userDAO.getDrivers(partNumber);
    }

    public Integer addUser(UserEntity user) {return  userDAO.addUser(user);}

    /**
     * @author Katia Stetsiuk
     * @param user entity for updating
     */
    public void  updateUser(UserEntity user) { userDAO.updateUser(user);}
    /**
     * @author Katia Stetsiuk
     * @param user entity for creating
     */
    public void createUser(UserEntity user) throws CreateException {
        validateRegistrationData(user);
        userDAO.createUser(user);
    }

    public void getDriverById(int driverId) {
        userDAO.getDriverByID(driverId);
    }
    public UserEntity getUserById(int userId) {
        return userDAO.getUserById(userId);
    }
    public List<CarEntity> getCars() {
        return carDAO.getCars();
    }

    /**
     * @author Vadym Akymov, Katia Stetsiuk
     */
    public List<CarEntity> getAllFreeCars(){
        return carDAO.getAllFreeCars();
    }

    /**
     * @author Vadym Akymov, Katia Stetsiuk
     */
    public CarEntity getCarByNumber(String carNumber){
        return carDAO.getCarByNumber(carNumber);
    }
    /**
     * @author Vadym Akymov
     */
    public UserEntity getDriverByID(int id){
        return userDAO.getDriverByID(id);
    }
    /**
     * @author Vadym Akymov
     */
    public void deleteUser(int id){
        userDAO.deleteUser(id);
    }
    public List<UserEntity> getDriversByEmail(String email){return userDAO.getDriversByEmail(email);};
    public List<UserEntity> getCustomersByEmail(String email){return userDAO.getCustomersByEmail(email);};

    /**
     * @author Vadym Akymov
     */
    public void assignCar(String carNumber, Integer driverID){
        userDAO.assignCar(carNumber, driverID);
    }

    /**
     * @author Katia Stetsiuk
     * @param carEntity entity  creating
     */
    public void createCar(CarEntity carEntity){carDAO.createCar(carEntity);}
    /**
     * @author Katia Stetsiuk
     * @param carEntity entity for updating
     */
    public void updateCar(CarEntity carEntity){carDAO.updateCar(carEntity);}
    /**
     * @author Katia Stetsiuk
     * @param carNumber car number for deleting
     */
    public void deleteCar(String carNumber) {carDAO.deleteCar(carNumber);}

    /**
     * @author Katia Stetsiuk
     * @param partNumber number of part to review
     * @return
     */
    public List<CarEntity> getCarsPart(int partNumber) {return carDAO.getCarsPart(partNumber);}

    /**
     *
     * @return number of pages for review driver list
     */
    public int getCarPagesCount(){return carDAO.getCarPagesCount();}


    /**
     *@author Vadym Akymov
     * @return count of drivers per page
     */
    public int getDriverPagesCount(){
        return userDAO.getDriverPagesCount();
    }

    public List<UserEntity> getAllUsersById(List<Integer> integers){
        return serviceUserDaoBeen.getAllById(integers);
    }


    public void blockAllById(List<Integer> ids, int blockValue) throws PersistError {
        serviceUserDaoBeen.blockAllById(ids, blockValue);
    }

    public long getUsersCount() {
        return serviceUserDaoBeen.getUsersCount();
    }

    public Collection<? extends UserEntity> getAll(int start, int countPerPage) {
        return serviceUserDaoBeen.getAll(start, countPerPage);
    }
    /**
     * @author Vitalii Diravka
     * @return present tazi_price table from DB
     */
    public List<TaxiPriceEntity> getAllPrices(){taxiPriceDAO.getAllPrices();
        return taxiPriceDAO.getAllPrices();
    }
    /**
     * @author Vitalii Diravka
     */
    public void updateTariff(BigDecimal newPrice, CarCategory enumCarCategory, Boolean weekend, Boolean nightTariff, String priceType) {
        TaxiPriceEntity newPriceEntity = new TaxiPriceEntity();
        if (priceType.equals("perKm")) {
            System.out.println(priceType);
            newPriceEntity.setPricePerKm(newPrice);
            newPriceEntity.setPricePerMin(null);
            newPriceEntity.setCarCategory(enumCarCategory);
            newPriceEntity.setWeekend(weekend);
            newPriceEntity.setNightTariff(nightTariff);
            taxiPriceDAO.updatePricePerKm(newPriceEntity);
        }
        if (priceType.equals("perMin")){
            System.out.println(priceType);
            newPriceEntity.setPricePerKm(null);
            newPriceEntity.setPricePerMin(newPrice);
            newPriceEntity.setCarCategory(enumCarCategory);
            newPriceEntity.setWeekend(weekend);
            newPriceEntity.setNightTariff(nightTariff);
            taxiPriceDAO.updatePricePerMin(newPriceEntity);
        }
    }
}
