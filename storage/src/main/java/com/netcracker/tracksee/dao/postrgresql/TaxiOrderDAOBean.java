package com.netcracker.tracksee.dao.postrgresql;

import com.netcracker.tracksee.entities.ServiceUserEntity;
import com.netcracker.tracksee.entities.TaxiOrderEntity;
import com.netcracker.tracksee.dao.TaxiOrderDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.*;

/**
 * @author Sharaban O.V
*/
@Stateless
public class TaxiOrderDAOBean implements TaxiOrderDAO {
    private static final Logger logger = LogManager.getLogger();
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;


    @Override
    public void addTaxiOrder(TaxiOrderEntity taxiOrderEntity) {
      String sql="INSERT INTO taxi_order (status,price,service,car_category,way_of_payment,driver_sex," +
              "music_style,animal_transportation,free_wifi,smoking_driver,air_conditioner,description) " +
              "VALUES(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12)";

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, taxiOrderEntity.getStatus().toString());
            query.setParameter(2, taxiOrderEntity.getPrice());
            query.setParameter(3, taxiOrderEntity.getService().toString());
            query.setParameter(4, taxiOrderEntity.getCarCategory().toString());
            query.setParameter(5, taxiOrderEntity.getWayOfPayment().toString());
            query.setParameter(6, taxiOrderEntity.getDriverSex().toString());
            query.setParameter(7, taxiOrderEntity.getMusicStyle().toString());
            query.setParameter(8, taxiOrderEntity.isAnimalTransportation());
            query.setParameter(9, taxiOrderEntity.isFreeWifi());
            query.setParameter(10, taxiOrderEntity.isSmokingDriver());
            query.setParameter(11, taxiOrderEntity.isAirConditioner());
            query.setParameter(12, taxiOrderEntity.getDescription());
            query.executeUpdate();
    }

}
