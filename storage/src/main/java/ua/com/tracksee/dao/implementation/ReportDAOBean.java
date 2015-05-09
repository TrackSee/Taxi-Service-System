package ua.com.tracksee.dao.implementation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.ReportDAO;
import ua.com.tracksee.entities.MostPopularOption;
import ua.com.tracksee.entities.ServiceProfitable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kstes_000 on 09-May-15.
 */
@Stateless
public class ReportDAOBean implements ReportDAO{
    private static final Logger logger = LogManager.getLogger();
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;
    /**
     * @author Katia Stetisuk
     * @param startDate start date to get the most profitable service
     * @param endDate end date to get the most profitable service
     * @return list object with statistic data
     */

    public List<ServiceProfitable> getProfitByService(String startDate, String endDate) {
        String sql = "SELECT service, SUM(price)\n" +
                "FROM taxi_order\n " +
                "WHERE ORDERED_DATE BETWEEN '" + startDate + "'" +
                " AND '" + endDate + "'" +
                "GROUP BY service";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> list = query.getResultList();
        List<ServiceProfitable> profitList = new ArrayList<>();
        ServiceProfitable sp;
        for (Object[] objects : list) {
            String s = (String) objects[0];
            BigDecimal b = (BigDecimal) objects[1];
            sp = new ServiceProfitable(s, b.doubleValue());
            profitList.add(sp);
        }
        return profitList;
    }

    /**
     * @author Katia Stetsiuk
     * @param userId user id for whom getting statistic about popular options
     * @return list object with statistic data
     */
    public List<MostPopularOption> getMostPopularOptionsForUser(Integer userId) {
        String options[] = {"Animal Transportation", "Music Style", "Free Wifi", "Non Smoking Driver",
                "Air Conditioner", "Driver Sex", "Way Of Payment", "Car Category"};
        BigInteger counts[] = {
                getCountOptionalBool("animal_transportation", userId),
                getCountOptionalChar("music_style", userId),
                getCountOptionalBool("free_wifi", userId),
                getCountOptionalBool("non_smoking_driver", userId),
                getCountOptionalBool("air_conditioner", userId),
                getCountOptionalChar("driver_sex", userId),
                getCountOptionalChar("way_of_payment", userId),
                getCountOptionalChar("car_category", userId)
        };
        List<MostPopularOption> listOptions = new ArrayList<>();
        for (int i = 0; i < options.length; i++) {
            listOptions.add(new MostPopularOption(options[i], counts[i]));
        }
        return listOptions;
    }

    /**
     * @author Katia Stetsiuk
     * @param option additional option (bool type)
     * @param userId user id to get his additional option
     * @return count of option for user
     */
    public BigInteger getCountOptionalBool(String option, Integer userId) {
        String sql = "select count(*) from taxi_order where " + option + " = true " +
                "and user_id = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, userId);
        return (BigInteger) query.getSingleResult();
    }

    /**
     * @author Katia Stetsiuk
     * @param option additional option (char type)
     * @param userId user id to get his additional option
     * @return count of option for user
     */
    public BigInteger getCountOptionalChar(String option, Integer userId) {
        String sql = "select count(*) from taxi_order where " + option + " is not null " +
                "and user_id = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, userId);
        return (BigInteger) query.getSingleResult();
    }

}
