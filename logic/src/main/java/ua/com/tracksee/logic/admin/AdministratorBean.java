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
    public void  updateUser(ServiceUserEntity user) { userDAO.updateUser(user);}
    public void createUser(ServiceUserEntity user) {userDAO.createUser(user);}
    public List<CarEntity> getCars() {return carDAO.getCars();}

    /**
     * @author Vadym_Akymov, Katia Stetsiuk
     */
    public List<CarEntity> getAllFreeCars(){
        return carDAO.getAllFreeCars();
    }

    /**
     * @author Vadym_Akymov
     */
    public ServiceUserEntity getDriverByID(int id){
        return userDAO.getDriverByID(id);
    }
    /**
     * @author Vadym_Akymov
     */
    public void deleteUser(int id){
        userDAO.deleteUser(id);
    }
}
