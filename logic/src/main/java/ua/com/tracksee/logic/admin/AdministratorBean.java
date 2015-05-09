package ua.com.tracksee.logic.admin;

import ua.com.tracksee.dao.CarDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.postrgresql.ServiceUserDaoBeen;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.error.PersistError;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collection;
import java.util.List;

/**
 * Created by Vadym_Akymov on 22.04.15.
 */
@Stateless
public class AdministratorBean {

    @EJB
    private UserDAO userDAO;

    @EJB
    private CarDAO carDAO;

    @EJB
    private ServiceUserDaoBeen serviceUserDaoBeen;

    /**
     * @author Katia Stetsiuk
     * @return
     */
    public List<ServiceUserEntity> getUsers(){return userDAO.getUsers();}
    /**
     * @author Vadym_Akymov
     * @param partNumber - number of part which is neeeded
     * This method return part of drivers
     */

    public List<ServiceUserEntity> getDrivers(int partNumber){
        return userDAO.getDrivers(partNumber);
    }

    public Integer addUser(ServiceUserEntity user) {return  userDAO.addUser(user);}

    /**
     * @author Katia Stetsiuk
     * @param user entity for creating and updating
     */
    public void  updateUser(ServiceUserEntity user) { userDAO.updateUser(user);}

    public void createUser(ServiceUserEntity user) {userDAO.createUser(user);}

    public void getDriverById(int driverId) {
        userDAO.getDriverByID(driverId);
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
    public ServiceUserEntity getDriverByID(int id){
        return userDAO.getDriverByID(id);
    }
    /**
     * @author Vadym Akymov
     */
    public void deleteUser(int id){
        userDAO.deleteUser(id);
    }
    public List<ServiceUserEntity> getDriversByEmail(String email){return userDAO.getDriversByEmail(email);};
    public List<ServiceUserEntity> getCustomersByEmail(String email){return userDAO.getCustomersByEmail(email);};

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

    public List<ServiceUserEntity> getAllUsersById(List<Integer> integers){
        return serviceUserDaoBeen.getAllById(integers);
    }


    public void blockAllById(List<Integer> ids, int blockValue) throws PersistError {
        serviceUserDaoBeen.blockAllById(ids, blockValue);
    }

    public long getUsersCount() {
        return serviceUserDaoBeen.getUsersCount();
    }

    public Collection<? extends ServiceUserEntity> getAll(int start, int countPerPage) {
        return serviceUserDaoBeen.getAll(start, countPerPage);
    }


}
