package ua.com.tracksee.logic.driver;

import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by Maria Komar on 28.04.2015.
 */
@Stateless
public class DriverBean {
    @EJB
    private UserDAO userDAO;

    public CarEntity getDriversCar(UserEntity driver){
        return userDAO.getDriversCar(driver);
    }

    public UserEntity getUserById(int id){
        return userDAO.getUserById(id);
    }
}
