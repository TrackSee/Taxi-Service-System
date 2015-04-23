package com.netcracker.tracksee.dao.postrgresql;

import com.netcracker.tracksee.entities.ServiceUserEntity;
import com.netcracker.tracksee.entities.TaxiOrderEntity;
import com.netcracker.tracksee.dao.TaxiOrderDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
* @author Sharaban Sasha
*/
@Stateless
public class TaxiOrderDAOBean implements TaxiOrderDAO {
    private static final Logger logger = LogManager.getLogger();
    //10 drivers per query by default
    public static final int DRIVERS_LIMIT = 10;
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;


//    /**
//     * @param partNumber - number of data part (from 1 to driver_count/DRIVERS_LIMIT)
//     * @return list with part of drivers(default size of list if 10)
//     */

//    public void getDrivers(int partNumber) {
//        if(partNumber <= 0) {
//            logger.error("partNumber can't be <= 0");
//            throw new IllegalArgumentException("partNumber can't be <= 0");
//        }
////        Query query = entityManager.createNativeQuery("SELECT * FROM service_user " +
////                "WHERE driver = TRUE LIMIT ?1 OFFSET ?2", ServiceUserEntity.class);
////        query.setParameter(1, DRIVERS_LIMIT);
////        query.setParameter(2, (partNumber-1)*DRIVERS_LIMIT);
////        return query.getResultList();
//    }

    @Override
    public void addTaxiOrder(TaxiOrderEntity taxiOrderEntity) {
//        String sql="INSERT INTO taxi_order (tracking_number,status,price,way_of_payment) VALUES()";

        if(entityManager==null){
            System.out.println("IT'S DISASTER!!!!!!  ");
        }
//
        try {
//            Query query = entityManager.createNativeQuery("SELECT * FROM service_user", ServiceUserEntity.class);


            String sql = "SELECT* FROM taxi_order ";
            Query query2 = entityManager.createNativeQuery(sql);
            System.out.println(query2.getParameter(0));
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("DAO bean work");
    }

//    @Override
//    public Boolean accountIsActivated(Integer userId) {
//        String sql = "SELECT  FROM service_user WHERE user_id = " + userId;
//        Query query = entityManager.createNativeQuery(sql);
//        return (Boolean) query.getSingleResult();
//    }
//
//    @Override
//    public void activateAccount(Integer userId) {
//        String sql = "";
//        Query query = entityManager.createNamedQuery(sql);
//        query.executeUpdate();
//    }
}
