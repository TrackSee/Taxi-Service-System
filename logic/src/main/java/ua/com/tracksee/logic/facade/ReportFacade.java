package ua.com.tracksee.logic.facade;

import ua.com.tracksee.dao.ReportDAO;
import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.entities.MostPopularOption;
import ua.com.tracksee.entities.ServiceProfitable;
import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.enumartion.CarCategory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by kstes_000 on 17-May-15.
 */
@Stateless
public class ReportFacade {
    @EJB
    private ReportDAO reportDAO;
    @EJB
    private TaxiPriceDAO taxiPriceDAO;
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
     * Returns tariffs.
     *
     * @author Sharaban Sasha
     * @return list objects with tariffs
     */
    public List<TaxiPriceEntity> getPricesByCarCategory(CarCategory carCategory){
        return taxiPriceDAO.getPricesByCarCategory(carCategory);
    }

}
