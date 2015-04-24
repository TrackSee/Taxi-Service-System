package com.netcracker.bootcamp.tracksee.logic;




import com.netcracker.tracksee.dao.AddressDAO;
import com.netcracker.tracksee.dao.TaxiOrderDAO;
import com.netcracker.tracksee.dao.UserDAO;
import com.netcracker.tracksee.dao.postrgresql.UserDAOBean;
import com.netcracker.tracksee.entities.Address;
import com.netcracker.tracksee.entities.TaxiOrder;
import com.netcracker.tracksee.entities.User;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import java.sql.SQLException;

import static javax.ejb.LockType.WRITE;


/**
 * @author Sasha Avlasov
 * Session Bean implementation class OrderRegistrator
 */
@Singleton
@Local
public class TaxiOrderBean {
    @EJB
    private TaxiOrderDAO taxiOrderDAO;
    @EJB
    private UserDAO userDAO;
    @EJB
    private AddressDAO addressDAO;

    /**
     * Default constructor.
     */
    public TaxiOrderBean() {
        // TODO Auto-generated constructor stub
    }
    @Lock(WRITE)
    public void makeOrder(TaxiOrder taxiOrder,User user,Address addressFrom,Address addressTo) throws SQLException{
           taxiOrderDAO.addTaxiOrder(taxiOrder);
           System.out.println("Check user :"+user.getEmail());
           if(!userDAO.checkEmail(user.getEmail())){
               System.out.println("User dont exist");
           }
        else System.out.println("User exist");
//        userDAO.addUser(user);
//            addressDAO.addAddress(addressFrom);
//            addressDAO.addAddress(addressTo);

    }
    private boolean checkPhone(long phone){

        return false;
    }
    private boolean checkBlackList(long phone){
        /**
         * Check phone in black list
         * if find return true;
         *
         */
        return false;
    }
    /**
     * @param origin - address
     * @param destination - number of data part (from 1 to driver_count/DRIVERS_LIMIT)
     * @return list with part of drivers(default size of list if 10)
     *
     * @author Sharaban Sasha
     */

    @Lock(WRITE)
    public long calculatePrice(Address origin,Address destination) throws SQLException{
        long distance=0;
        long price=0;
        if(!origin.getAddress().equals(destination.getAddress()))  {
            distance=getDistance(origin,destination);
            //TODO Get price for 1km from database and calculate final price
        }
        return price;
    }
    private long getDistance(Address origin,Address destination){
        /**
         * calculate distance
         * return distance;
         */
        return 0;
    }

}
