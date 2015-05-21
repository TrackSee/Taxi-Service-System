package ua.com.tracksee.logic;

import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 * Stateless bean used for searching users
 * with different parameters.
 *
 * @author Sharaban Sasha
 */
@Stateless
public class UserCheckerBean {
    private @EJB
    UserDAO userDAO;
    /**
     * Checks user authorized or not
     * and in second case search for activated
     * user with such email.
     *
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.UserDAO
     */
    public boolean checkActivatedUserByEmail(String email,Integer userId){
     boolean state=false;
        boolean activatedCustomer=userDAO.getActivatedCustomerByEmail(email);
        if(userId==null&&!activatedCustomer){
            state=true;
        }else if(userId!=null&&activatedCustomer){
            state=true;
        }
        return state;
    }
    /**
     * Returns object that contain
     * information about user with
     * such user id.
     *
     * @author Sharaban Sasha
     * @param id id number of user record
     * @return object that contain all information about user
     */
    public UserEntity getUserInfo(int id) {
        return userDAO.getUserById(id);
    }


}
