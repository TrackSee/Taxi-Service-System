package ua.com.tracksee.dao;

import ua.com.tracksee.entities.MostPopularOption;
import ua.com.tracksee.entities.ServiceProfitable;
import ua.com.tracksee.entities.reports.CarReportEntity;
import ua.com.tracksee.entities.reports.DriverReportEntity;
import ua.com.tracksee.entities.reports.MusicReportEntity;

import javax.ejb.Local;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by kstes_000 on 09-May-15.
 */
@Local
public interface ReportDAO {

    /**
     * @author Vadym Akymov
     * @return list of entities which contains info about sex of driver and ordered count
     */
    List<DriverReportEntity> getDriverSexReport();

    /**
     * @author Vadym Akymov
     * @return list of entities which contains info about car category and ordered count
     */
    List<CarReportEntity> getCarCategoryReport();

        /**
     * @author Katia Stetisuk
     * @param startDate start date to get the most profitable service
     * @param endDate end date to get the most profitable service
     * @return list object with statistic data
     */

    List<ServiceProfitable> getProfitByService(String startDate, String endDate);

    /**
     * @author Katia Stetsiuk
     * @param userId user id for whom getting statistic about popular options
     * @return list object with statistic data
     */
    List<MostPopularOption> getMostPopularOptionsForUser(Integer userId);

    BigInteger getCountOptionalChar(String option, Integer userId);


    BigInteger getCountOptionalBool(String option, Integer userId);

    /**
     * @author Oleksandr Kozin
     * @param startDate start date of the period
     * @param endDate end date of the period
     * @return number of orders per period
     */
    int getOrdersByPeriod(String startDate, String endDate);

    /**
     * @author Oleksandr Kozin
     * @param startDate month for profit report
     * @return profit by month
     */
    double serviceProfitByMonth(String startDate);

    /**
     * @author Oleksandr Kozin
     * @return list of entities which contains info about music style and ordered count overall
     */
    List<MusicReportEntity> getMusicReportOverall();

    /**
     * @author Oleksandr Kozin
     * @return list of entities which contains info about music style and ordered count for current customer
     */
    List<MusicReportEntity> getMusicReportByUser(Integer userId);

    /**
     * @author Oleksandr Kozin
     * @return map with most popular additional car options overall
     */
    Map<String, Integer> mostPopularAdditionalCarOptOverall();

    /**
     * @author Oleksandr Kozin
     * @return map with most popular additional car options for current customer
     */
    Map<String, Integer> mostPopularAdditionalCarOptByUser(Integer userId);

}
