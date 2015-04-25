package ua.com.tracksee.logic.admin;



import com.netcracker.tracksee.dao.UserDAO;
import com.netcracker.tracksee.entities.ServiceUserEntity;

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
}
