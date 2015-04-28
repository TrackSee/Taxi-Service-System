package ua.com.tracksee.logic.admin;

import ua.com.tracksee.dao.CarDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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

    /**
     * @author Vadym Akymov
     */
    public void assignCar(String carNumber, Integer driverID){
        userDAO.assignCar(carNumber, driverID);
    }

    /**
     * @author Katia Stetsiuk
     * @param carEntity entity for updating, creating, deleting
     */
    public void createCar(CarEntity carEntity){carDAO.createCar(carEntity);}
    public void updateCar(CarEntity carEntity){carDAO.updateCar(carEntity);}
    public void deleteCar(String carNumber) {carDAO.deleteCar(carNumber);}


}
