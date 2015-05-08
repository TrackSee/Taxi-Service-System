package ua.com.tracksee.dao.implementation;

import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.enumartion.CarCategory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Ruslan Gunavardana
 * @author Katia Stetsiuk
 */
@Stateless
public class TaxiPriceDAOBean implements TaxiPriceDAO {
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public TaxiPriceEntity getPriceFor(CarCategory category, boolean weekend, boolean nightTariff) {
        String sql = "SELECT * FROM taxi_price" +
                " WHERE car_category = " + category +
                " AND weekend = " + weekend +
                " AND night_tariff = " + nightTariff;
        Query query = entityManager.createNativeQuery(sql);
        return (TaxiPriceEntity) query.getSingleResult();
    }

    @Override
    public void updateTariff(TaxiPriceEntity priceEntity) {
        entityManager.refresh(priceEntity);
    }
}
