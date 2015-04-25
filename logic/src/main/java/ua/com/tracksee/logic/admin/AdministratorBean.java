package ua.com.tracksee.logic.admin;

import ua.com.tracksee.dao.UserDAO;
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
}
