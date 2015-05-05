package ua.com.tracksee.dao.postrgresql;

import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.entities.ProfitEntity;
import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.enumartion.CarCategory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ProfitEntity> serviceProfitByMonth(String year, String month){
        String yearMonth = year + "-" + month;
        String sql = "SELECT service, SUM(price) FROM taxi_order" +
                " WHERE ordered_date" +
                " BETWEEN '" + yearMonth + "-01'" +
                " AND '" + yearMonth + "-30'" +
                " GROUP BY service";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> list = query.getResultList();
        List<ProfitEntity> profitList = new ArrayList<>();
        ProfitEntity pe;
        for (Object[] objects : list) {
            String s = (String) objects[0];
            BigDecimal b = (BigDecimal) objects[1];
            pe = new ProfitEntity(s, b.doubleValue());
            profitList.add(pe);
        }
        return profitList;
    }
}
