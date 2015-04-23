package com.netcracker.bootcamp.tracksee.logic;


import com.netcracker.bootcamp.tracksee.entity.Address;
import com.netcracker.bootcamp.tracksee.entity.Role;
import com.netcracker.bootcamp.tracksee.entity.TaxiOrder;
import com.netcracker.bootcamp.tracksee.entity.User;
import com.netcracker.tracksee.dao.TaxiOrderDAO;
import com.netcracker.tracksee.entities.TaxiOrderEntity;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import java.sql.SQLException;

import static javax.ejb.LockType.WRITE;

//import com.netcracker.tracksee.entities.TaxiOrderEntity;

/**
 * @author Sasha Avlasov
 * Session Bean implementation class OrderRegistrator
 */
@Singleton
@Local
public class TaxiOrderBean {
    @EJB
    private TaxiOrderDAO taxiOrderDAO;

    /**
     * Default constructor.
     */
    public TaxiOrderBean() {
        // TODO Auto-generated constructor stub
    }
    @Lock(WRITE)
    public boolean makeOrder(long phone,String email,Address origin,Address destination) throws SQLException{
        System.out.println(email);
        System.out.println("Logic bean work");
        if(!checkPhone(phone)){
            User user = new User();
            TaxiOrder order = new TaxiOrder();
            user.setRole(Role.NOT_REGISTER_USER);
            user.setPhone(phone);
            //TODO order.setCustomer(user);
//            order.set(origin);
//            order.setDestination(destination);
//            if(checkBlackList(phone)){
//                order.incruasePrice();
//            }

            TaxiOrderEntity taxiOrderEntity=new TaxiOrderEntity();

            taxiOrderDAO.addTaxiOrder(taxiOrderEntity);

            return true;
        }
        else return false;
    }
    private boolean checkPhone(long phone){
        /**
         * check phone in DB if exist
         * return true;
         */
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
