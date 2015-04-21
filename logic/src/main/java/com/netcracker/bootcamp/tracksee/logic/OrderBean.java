package com.netcracker.bootcamp.tracksee.logic;


import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.mail.MessagingException;

//import com.netcracker.bootcamp.tracksee.entities.TaxiOrderEntity;
import com.netcracker.bootcamp.tracksee.entity.*;
import com.netcracker.bootcamp.tracksee.util.IdGenerator;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static javax.ejb.LockType.WRITE;

/**
 * @author Sasha Avlasov
 * Session Bean implementation class OrderRegistrator
 */
@Singleton
@Local
public class OrderBean {

    /**
     * Default constructor. 
     */
    public OrderBean() {
        // TODO Auto-generated constructor stub
    }

    @Lock(WRITE)
    public boolean makeOrder(long phone,String email,Address origin,Address destination) throws SQLException{
        System.out.println(email);
        if(!checkPhone(phone)){
            User user = new User();
            TaxiOrder order = new TaxiOrder();
            user.setRole(Role.NOT_REGISTER_USER);
            user.setPhone(phone);
            //TODO order.setCustomer(user);
            order.setOrigin(origin);
            order.setDestination(destination);
            if(checkBlackList(phone)){
                order.incruasePrice();
            }
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

}
