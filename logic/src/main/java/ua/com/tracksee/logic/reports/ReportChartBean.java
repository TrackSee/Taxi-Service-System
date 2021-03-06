package ua.com.tracksee.logic.reports;

import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.dao.ReportDAO;
import ua.com.tracksee.entities.MostPopularOption;
import ua.com.tracksee.entities.ServiceProfitable;
import ua.com.tracksee.entities.reports.CarReportEntity;
import ua.com.tracksee.entities.reports.DriverReportEntity;
import ua.com.tracksee.entities.reports.MusicReportEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.List;

/**
 * Created by kstes_000 on 09-May-15.
 */
@Stateless
public class ReportChartBean {
    @EJB
    private ReportDAO reportDAO;


    /**
     * @author Vadym Akymov
     * @return json which contains info about driver's sex and ordered count
     */
    public String getDriverSexReport() throws IOException {
        List<DriverReportEntity> driverReport = reportDAO.getDriverSexReport();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(driverReport);
    }

    /**
     * @author Vadym Akymov
     * @return json which contains info about car categories and ordered count
     * @throws IOException
     */
    public String getCarCategoryReport() throws IOException {
        List<CarReportEntity> carReport = reportDAO.getCarCategoryReport();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(carReport);
    }
    /**
        * @author Katia Stetsiuk
      * @param startDate start date to get the most profitable service
       * @param endDate end date to get the most profitable service
      * @return list object with statistic data
        */
    public List<ServiceProfitable> getProfitByService(String startDate, String endDate){return reportDAO.getProfitByService(startDate, endDate);}

    /**
     * @author Katia Stetsiuk
     * @param userId user id for whom getting statistic about popular options
     * @return list object with statistic data
     */
    public List<MostPopularOption> getMostPopularOptionsForUser(Integer userId){return reportDAO.getMostPopularOptionsForUser(userId);}

    /**
     * @author Oleksandr Kozin
     * @return json which contains info about additional options and ordered count overall
     */
    public String mostPopularAdditionalCarOptOverall() throws IOException {
        List<MostPopularOption> optionsReport = reportDAO.mostPopularAdditionalCarOptOverall();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(optionsReport);
    }

    /**
     * @author Oleksandr Kozin
     * @return json which contains info about additional options and ordered count for current customer
     */
    public String mostPopularAdditionalCarOptByUser(Integer userId) throws IOException {
        List<MostPopularOption> optionsReport = reportDAO.mostPopularAdditionalCarOptByUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(optionsReport);
    }

    /**
     * @author Oleksandr Kozin
     * @return json which contains info about music style and ordered count overall
     */
    public String getMusicStyleReportOverall() throws IOException {
        List<MusicReportEntity> musicReport = reportDAO.getMusicReportOverall();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(musicReport);
    }

    /**
     * @author Oleksandr Kozin
     * @return json which contains info about music style and ordered count for current customer
     */
    public String getMusicStyleReportByUser(Integer userId) throws IOException {
        List<MusicReportEntity> musicReport = reportDAO.getMusicReportByUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(musicReport);
    }

    /**
     * @author Oleksandr Kozin
     * @param startDate start date of the period
     * @param endDate end date of the period
     * @return number of orders per period
     */
    public String getOrdersByPeriod(String startDate, String endDate) throws IOException {
        Integer result = reportDAO.getOrdersByPeriod(startDate, endDate);
        return String.valueOf(result);
//        List<MusicReportEntity> musicReport = reportDAO.getMusicReportOverall();
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(musicReport);
    }

    /**
     * @author Oleksandr Kozin
     * @param startDate month for profit report
     * @return profit by month
     */
    public String serviceProfitByMonth(String startDate) throws IOException {
        Double result = reportDAO.serviceProfitByMonth(startDate);
        return String.valueOf(result);
//        List<MusicReportEntity> musicReport = reportDAO.getMusicReportOverall();
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(musicReport);
    }
}
