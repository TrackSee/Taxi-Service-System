package ua.com.tracksee.dao;

import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Vadym Akymov
 * @author Ruslan Gunavardana
 */
@Local
public interface UserDAO {
    /**
     * @param partNumber - number of data part
     * @return list with part of drivers(default size of list if 10)
     */
    List<ServiceUserEntity> getDrivers(int partNumber);
}
