package ua.com.tracksee.dao.implementation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.ReportDAO;
import ua.com.tracksee.entities.MostPopularOption;
import ua.com.tracksee.entities.ServiceProfitable;
import ua.com.tracksee.entities.reports.CarReportEntity;
import ua.com.tracksee.entities.reports.DriverReportEntity;
import ua.com.tracksee.entities.reports.MusicReportEntity;

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

    @Override
    public List<DriverReportEntity> getDriverSexReport() {
        Query q = entityManager.createNativeQuery("SELECT * FROM driver_report", DriverReportEntity.class);
        return q.getResultList();
    }

    @Override
    public List<CarReportEntity> getCarCategoryReport() {
        Query q = entityManager.createNativeQuery("SELECT * FROM car_report", CarReportEntity.class);
        return q.getResultList();
    }

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

    @Override
    public int getOrdersByPeriod(String startDate, String endDate) {
        String sql = "SELECT COUNT(*) FROM taxi_order" +
                " WHERE ordered_date" +
                " BETWEEN '" + startDate + "'" +
                " AND '" + endDate + "'";
        Query query = entityManager.createNativeQuery(sql);
        BigInteger bigInteger = (BigInteger) query.getSingleResult();
        return bigInteger.intValue();
//        return getInteger(sql);
    }

    @Override
    public double serviceProfitByMonth(String startDate) {
        startDate = startDate + "-01";
        String endDate = null;
        String[] date = startDate.split("-");
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        for (int i = 0; i < months.length; i++) {
            if (date[1].equals(months[i]) && i < months.length){
                endDate = date[0] + "-" + months[i + 1] + "-" + date[2];
            } else {
                endDate = date[0] + "-" + months[0] + "-" + date[2];
            }
        }
        String sql = "SELECT SUM(price)" +
                " FROM taxi_order" +
                " WHERE ordered_date >= '" + startDate + "'" +
                " AND ordered_date < '" + endDate + "'";
        Query query = entityManager.createNativeQuery(sql);

        BigDecimal bigDecimal = (BigDecimal) query.getSingleResult();
        return bigDecimal.doubleValue();
    }

    @Override
    public List<MusicReportEntity> getMusicReportOverall() {
        Query query = entityManager.createNativeQuery("SELECT * FROM music_report", MusicReportEntity.class);
        return query.getResultList();
    }

    @Override
    public List<MusicReportEntity> getMusicReportByUser(Integer userId) {
        String sql = "SELECT music_style, COUNT(music_style)" +
                " FROM taxi_order" +
                " WHERE music_style NOTNULL" +
                " AND user_id = ?" +
                " GROUP BY music_style;";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, userId);
        return query.getResultList();
    }

    @Override
    public List<MostPopularOption> mostPopularAdditionalCarOptOverall() {
        List<MostPopularOption> list = new ArrayList<>();
        String sql = "SELECT COUNT(way_of_payment)" +
                " FROM taxi_order" +
                " WHERE way_of_payment = 'VISA_CARD'";
        BigInteger integer = getBigInteger(sql);
        list.add(new MostPopularOption("Credit card", integer));
        String[] options = {"free_wifi", "animal_transportation", "non_smoking_driver", "air_conditioner"};
        String[] optionsRight = {"Free Wi-Fi", "Animal transportation", "Non smoking driver", "Air conditioner"};
        for (int i = 0; i < options.length; i++) {
            BigInteger amount = getAmountOverall(options[i]);
            list.add(new MostPopularOption(optionsRight[i], amount));
        }
        return list;
    }

    @Override
    public List<MostPopularOption> mostPopularAdditionalCarOptByUser(Integer userId) {
        List<MostPopularOption> list = new ArrayList<>();
        String sql = "SELECT COUNT(way_of_payment)" +
                " FROM taxi_order" +
                " WHERE way_of_payment = 'VISA_CARD'" +
                " AND user_id = " + userId;
        BigInteger integer = getBigInteger(sql);
        list.add(new MostPopularOption("Credit card", integer));
        String[] options = {"free_wifi", "animal_transportation", "non_smoking_driver", "air_conditioner"};
        String[] optionsRight = {"Free Wi-Fi", "Animal transportation", "Non smoking driver", "Air conditioner"};
        for (int i = 0; i < options.length; i++) {
            BigInteger amount = getAmountByUser(options[i], userId);
            list.add(new MostPopularOption(optionsRight[i], amount));
        }
        return list;
    }

    private BigInteger getBigInteger(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        BigInteger bigInteger = (BigInteger) query.getSingleResult();
        return bigInteger;
    }

    /**
     * It counts the number of orders from this additional option overall
     *
     * @param option the name of an additional option
     * @return number of orders with this additional option
     * @author Oleksandr Kozin
     */
    private BigInteger getAmountOverall(String option) {
        String sql = "SELECT COUNT(*) FROM taxi_order" +
                " WHERE " + option + " = TRUE";
        return getBigInteger(sql);
    }

    /**
     * It counts the number of orders from this additional option for current customer
     *
     * @param option the name of an additional option
     * @return number of orders with this additional option
     * @author Oleksandr Kozin
     */
    private BigInteger getAmountByUser(String option, Integer userId) {
        String sql = "SELECT COUNT(*) FROM taxi_order" +
                " WHERE " + option + " = TRUE" +
                " AND user_id = " + userId;
        return getBigInteger(sql);
    }


}
