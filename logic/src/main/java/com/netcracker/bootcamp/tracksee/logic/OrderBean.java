package com.netcracker.bootcamp.tracksee.logic;


import com.netcracker.bootcamp.tracksee.entities.ServiceUserEntity;
import com.netcracker.bootcamp.tracksee.entity.Address;
import com.netcracker.bootcamp.tracksee.entity.Role;
import com.netcracker.bootcamp.tracksee.entity.TaxiOrder;
import com.netcracker.bootcamp.tracksee.entity.User;

import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

import static javax.ejb.LockType.WRITE;

//import com.netcracker.bootcamp.tracksee.entities.TaxiOrderEntity;

/**
 * @author Sasha Avlasov
 * Session Bean implementation class OrderRegistrator
 */
@Singleton
@Local
public class OrderBean {

    @PersistenceContext(unitName = "hibernatePU")
    EntityManager manager;
    /**
     * Default constructor. 
     */
    public OrderBean() {
        // TODO Auto-generated constructor stub
    }

    @Lock(WRITE)
    public boolean makeOrder(long phone,String email,Address origin,Address destination) throws SQLException{
        if(manager!=null)System.out.println("You do it mothefucker !!!!!!!!!!!!!1111");
        Query query = manager.createNativeQuery("SELECT * FROM service_user", ServiceUserEntity.class);
        List<ServiceUserEntity> list= (List<ServiceUserEntity>)query.getResultList();
       System.out.println("User emails");
       for(ServiceUserEntity user:list){
            System.out.println(user.getEmail());
       }
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
