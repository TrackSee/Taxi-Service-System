package ua.com.tracksee.logic.reports;

import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.entities.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean provides prepare data
 * for ExcelReporterBean
 *
 * @author Sharaban Sasha
 */
@Stateless
public class PriceListReportBean  {
    @EJB private TaxiPriceDAO taxiPriceDAO;
    private static final int MIN_DISTANCE=5;
    private static final int DISTANCE_FOR_SHOW=10;
    private static final int MINUTES_IN_HOUR=60;

    public String getReportTitle() {
        return "Price list";
    }
    public int[] getReportNumberCells() {
        return new int[]{2,3,4,5,6};
    }

    public ArrayList<String> getTitles(){
        ArrayList<String> titles=new ArrayList<String>();
        titles.add("Car category");
        titles.add("Special time or day");
        titles.add("Minimal price (order lowest 5km)");
        titles.add("Price per km");
        titles.add("Price per 10km");
        titles.add("Price per min");
        titles.add("Price per hour");
        return titles;
    }
    public ArrayList<ArrayList<String>> getData(){
        ArrayList<ArrayList<String>> dataArray=new ArrayList<ArrayList<String>>();
        ArrayList<String> data;
        ArrayList<String> typeArray;

        List<TaxiPriceEntity> taxiPriceEntityList= taxiPriceDAO.getPricesOrderByCarCategory();
        for (int i = 0; i < taxiPriceEntityList.size() ; i++) {
            data=new ArrayList<String>();
            data.add(taxiPriceEntityList.get(i).getCarCategory().toString());
            if(taxiPriceEntityList.get(i).getNightTariff()){
                data.add("With night tariff");

            }else
            if(taxiPriceEntityList.get(i).getWeekend()){
                data.add("With weekend tariff");
            }else
            if(taxiPriceEntityList.get(i).getWeekend()&&taxiPriceEntityList.get(i).getNightTariff()){
                data.add("With weekend and night tariff");
            }else
            if(!taxiPriceEntityList.get(i).getWeekend()&&!taxiPriceEntityList.get(i).getNightTariff()){
                data.add("None");
            }
            BigDecimal pricePerKmDigDecimal=(BigDecimal)taxiPriceEntityList.get(i).getPricePerKm();
            long pricePerKm=pricePerKmDigDecimal.longValue();
            data.add(String.valueOf(pricePerKm*MIN_DISTANCE));
            data.add(String.valueOf(pricePerKm));
            data.add(String.valueOf(pricePerKm*DISTANCE_FOR_SHOW));
            BigDecimal pricePerMinBigDecimal=(BigDecimal)taxiPriceEntityList.get(i).getPricePerKm();
            long pricePerMin=pricePerMinBigDecimal.longValue();
            data.add(String.valueOf(pricePerMin));
            data.add(String.valueOf(pricePerMin*MINUTES_IN_HOUR));
            dataArray.add(data);
        }
        return dataArray;
    }
}
