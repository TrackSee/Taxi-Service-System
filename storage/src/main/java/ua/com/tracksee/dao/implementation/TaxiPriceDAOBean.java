package ua.com.tracksee.dao.implementation;

import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.enumartion.CarCategory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ruslan Gunavardana
 * @author Katia Stetsiuk
 * @author Vitalii Diravka
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
    @Override
    public Map<String, Double> serviceProfitByMonth(String year, String month){
        String yearMonth = year + "-" + month;
        String sql = "SELECT service, SUM(price) FROM taxi_order" +
                " WHERE ordered_date" +
                " BETWEEN '" + yearMonth + "-01'" +
                " AND '" + yearMonth + "-30'" +
                " GROUP BY service";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> list = query.getResultList();
        Map<String, Double> map = new HashMap<>();
        for (Object[] objects : list) {
            String service = (String) objects[0];
            BigDecimal b = (BigDecimal) objects[1];
            map.put(service, b.doubleValue());
        }
        return map;
    }
    @Override
    public void updatePricePerKm(TaxiPriceEntity newPriceEntity) {
        String sql = "UPDATE taxi_price " +
                "SET price_per_km =  ?1 " +
                "where car_category = ?3 " +
                "and weekend = ?4 " +
                "and night_tariff = ?5";
        Query query = entityManager.createNativeQuery(sql, TaxiPriceEntity.class);
        query.setParameter(1, newPriceEntity.getPricePerKm());
        query.setParameter(3, newPriceEntity.getCarCategory().toString());
        query.setParameter(4, newPriceEntity.getWeekend());
        query.setParameter(5, newPriceEntity.getNightTariff());
        query.executeUpdate();
    }

    @Override
    public void updatePricePerMin(TaxiPriceEntity newPriceEntity) {
        String sql = "UPDATE taxi_price " +
                "SET price_per_min = ?2 " +
                "where car_category = ?3 " +
                "and weekend = ?4 " +
                "and night_tariff = ?5";
        Query query = entityManager.createNativeQuery(sql, TaxiPriceEntity.class);
        query.setParameter(2, newPriceEntity.getPricePerMin());
        query.setParameter(3, newPriceEntity.getCarCategory().toString());
        query.setParameter(4, newPriceEntity.getWeekend());
        query.setParameter(5, newPriceEntity.getNightTariff());
        query.executeUpdate();
    }


    @Override
    public List<TaxiPriceEntity> getAllPrices() {
        String sql = "SELECT price_per_km, price_per_min, car_category, weekend, night_tariff " +
                "FROM taxi_price " +
                "ORDER BY car_category, weekend, night_tariff";
        Query query = entityManager.createNativeQuery(sql, TaxiPriceEntity.class);
        return query.getResultList();
    }
}
