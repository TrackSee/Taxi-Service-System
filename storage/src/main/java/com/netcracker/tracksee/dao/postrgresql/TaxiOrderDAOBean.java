package com.netcracker.tracksee.dao.postrgresql;

import com.netcracker.tracksee.entities.ServiceUserEntity;
import com.netcracker.tracksee.entities.TaxiOrder;
import com.netcracker.tracksee.entities.TaxiOrderEntity;
import com.netcracker.tracksee.dao.TaxiOrderDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

/**
* @author Sharaban Sasha
 * @author Sasha Avlasov
*/
@Stateless
public class TaxiOrderDAOBean implements TaxiOrderDAO {
    private static final Logger logger = LogManager.getLogger();
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public void addTaxiOrder(TaxiOrder taxiOrder) {
        try{
      String sql="INSERT INTO taxi_order (status,price,service,car_category,way_of_payment,driver_sex," +
              "music_style,animal_transportation,free_wifi,smoking_driver,air_conditioner,comment) " +
              "VALUES(" + "'" +taxiOrder.getStatus()+"',"+taxiOrder.getPrice()+",'"+taxiOrder.getService()+"','"
              + taxiOrder.getCarCategory()+"','"+taxiOrder.getWayOfPayment()+"','"+
              taxiOrder.getDriverSex()+"','"+taxiOrder.getMusicStyle()+"','"+taxiOrder.getAnimalTransportation()+"','"+
              taxiOrder.getFreeWifi()+"','"+taxiOrder.getSmokingDriver()+"','"+taxiOrder.getAirConditioner()+"','"+
              taxiOrder.getComment()+"')";

            //"VALUES(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,'"+taxiOrder.getComment()+"')";
            System.out.println(taxiOrder.getStatus());
            System.out.println(taxiOrder.getPrice());
            System.out.println(taxiOrder.getService());
            System.out.println(taxiOrder.getCarCategory());
            System.out.println(taxiOrder.getWayOfPayment());
            System.out.println(taxiOrder.getDriverSex());
            System.out.println(taxiOrder.getMusicStyle());
            System.out.println(taxiOrder.getAnimalTransportation());
            System.out.println(taxiOrder.getFreeWifi());
            System.out.println(taxiOrder.getSmokingDriver());
            System.out.println(taxiOrder.getAirConditioner());
            System.out.println(taxiOrder.getComment());
            Query query = entityManager.createNativeQuery(sql);
//            query.setParameter(1, taxiOrder.getTrackingNumber());// TODO autoincrement
//            query.setParameter(1,taxiOrder.getStatus());
//            query.setParameter(2, taxiOrder.getPrice());
//            query.setParameter(3, taxiOrder.getService());
//            query.setParameter(4, taxiOrder.getCarCategory());
//            query.setParameter(5, taxiOrder.getWayOfPayment());
//            query.setParameter(6, taxiOrder.getDriverSex());
//            query.setParameter(7, taxiOrder.getMusicStyle());
//            query.setParameter(8, taxiOrder.getAnimalTransportation());
//            query.setParameter(9, taxiOrder.getFreeWifi());
//            query.setParameter(10, taxiOrder.getSmokingDriver());
//            query.setParameter(11, taxiOrder.getAirConditioner());
//            query.setParameter(12, taxiOrder.getComment());
            query.executeUpdate();
        }catch(Exception ex){
           ex.printStackTrace();
            System.out.println("TaxiOrderDAO");
        }
    }
}
