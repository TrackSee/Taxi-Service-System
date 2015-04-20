package ua.com.tracksee.dao;

import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Ruslan Gunavardana.
 */
@Local
public interface UserDAO {
    /**
     * @param partNumber - number of data part
     * @return list with part of drivers(default size of list if 10)
     * @author Vadym Akymov
     */
    List<ServiceUserEntity> getDrivers(int partNumber);

    /**
     * @param driver - entity of driver for addition by Administrator
     * @author Katia Stetsiuk
     */
    void addDriver(ServiceUserEntity driver);

    /**
     * @param driverId - driver's id for deleting by Administrator
     * @author Katia Stetsiuk
     */
    void removeDriver(int driverId);

    /**
     * @param driver - - entity of driver to editing by Administrator
     * @author Katia Stetsiuk
     */

    void editDriver(ServiceUserEntity driver);

    /**
     * @param taxiOrder - entity of taxiOrder for editing by User
     * @author Katia Stetsiuk
     */
    void editOrder(TaxiOrderEntity taxiOrder);

    /**
     * @param taxiOrderId - taxiOrder's id for refusal Order by User
     * @author Katia Stetsiuk
     */

    void cancelOrder(int taxiOrderId);
}
