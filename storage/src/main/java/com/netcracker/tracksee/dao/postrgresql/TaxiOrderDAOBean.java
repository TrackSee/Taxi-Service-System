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
 *    @author Sharaban Sasha
 *   @author Sasha Avlasov
*/
@Stateless
public class TaxiOrderDAOBean implements TaxiOrderDAO {
    private static final Logger logger = LogManager.getLogger();
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public int addTaxiOrder(TaxiOrderEntity taxiOrderEntity) {
        try{
      String sql="INSERT INTO taxi_order (status,price,service,car_category,way_of_payment,driver_sex," +
              "music_style,animal_transportation,free_wifi,smoking_driver,air_conditioner) " +
              "VALUES(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11)";

//            String sql="INSERT INTO taxi_order (status,price) VALUES(?1,?2);";

//              "VALUES(" + "'" +taxiOrder.getStatus()+"',"+taxiOrder.getPrice()+",'"+taxiOrder.getService()+"','"
//              + taxiOrder.getCarCategory()+"','"+taxiOrder.getWayOfPayment()+"','"+
//              taxiOrder.getDriverSex()+"','"+taxiOrder.getMusicStyle()+"','"+taxiOrder.getAnimalTransportation()+"','"+
//              taxiOrder.getFreeWifi()+"','"+taxiOrder.getSmokingDriver()+"','"+taxiOrder.getAirConditioner()+"','"+
//              taxiOrder.getComment()+"')";


            System.out.println(taxiOrderEntity.getStatus());
            System.out.println(taxiOrderEntity.getPrice());
            System.out.println(taxiOrderEntity.getService());
            System.out.println(taxiOrderEntity.getCarCategory());
            System.out.println(taxiOrderEntity.getWayOfPayment());
            System.out.println(taxiOrderEntity.getDriverSex());
            System.out.println(taxiOrderEntity.getMusicStyle());
            System.out.println(taxiOrderEntity.isAnimalTransportation());
            System.out.println(taxiOrderEntity.isFreeWifi());
            System.out.println(taxiOrderEntity.isSmokingDriver());
            System.out.println(taxiOrderEntity.isAirConditioner());


            Query query = entityManager.createNativeQuery(sql);
//            query.setParameter(1,"'QUEUED'");
//            query.setParameter(2, 1.0);
//            query.executeUpdate();
            query.setParameter(1, taxiOrderEntity.getStatus());
            query.setParameter(2, taxiOrderEntity.getPrice());
            query.setParameter(3, taxiOrderEntity.getService());
            query.setParameter(4, taxiOrderEntity.getCarCategory());
            query.setParameter(5, taxiOrderEntity.getWayOfPayment());
            query.setParameter(6, taxiOrderEntity.getDriverSex());
            query.setParameter(7, taxiOrderEntity.getMusicStyle());
            query.setParameter(8, taxiOrderEntity.isAnimalTransportation());
            query.setParameter(9, taxiOrderEntity.isFreeWifi());
            query.setParameter(10, taxiOrderEntity.isSmokingDriver());
            query.setParameter(11, taxiOrderEntity.isAirConditioner());
            query.executeUpdate();

        }catch(Exception ex){
           ex.printStackTrace();
            System.out.println("TaxiOrderDAO");
        }
        return 0;
    }


    private String commaConvertString(String string){
        return "'"+string+"'";
    }
}
