package ua.com.tracksee.logic.facade;

import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.driver.DriverBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Ruslan Gunavardana
 * @author Maria Komar
 */
@Stateless
public class DriverFacade {

    @EJB
    private DriverBean driverBean;

    public CarEntity getDriversCar(UserEntity driver){
        return driverBean.getDriversCar(driver);
    }

    public UserEntity getUserById(int id){
        return  driverBean.getUserById(id);
    }
}
