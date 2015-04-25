package ua.com.tracksee.logic.admin;

import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.postrgresql.ServiceUserDaoBeen;
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
    private ServiceUserDaoBeen serviceUserDaoBeen;

    /**
     * @author Vadym_Akymov
     * @param partNumber - number of part which is neeeded
     * This method return part of drivers
     */
    public List<ServiceUserEntity> getDrivers(int partNumber){
        return userDAO.getDrivers(partNumber);
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
