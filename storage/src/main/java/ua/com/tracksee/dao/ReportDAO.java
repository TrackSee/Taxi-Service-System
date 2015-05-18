package ua.com.tracksee.dao;

import ua.com.tracksee.entities.MostPopularOption;
import ua.com.tracksee.entities.ServiceProfitable;
import ua.com.tracksee.entities.reports.CarReportEntity;
import ua.com.tracksee.entities.reports.DriverReportEntity;

import javax.ejb.Local;
import java.math.BigInteger;
import java.util.List;

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

}
