package ua.com.tracksee.logic.reports;

import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.entity.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
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
    private static final int MINUTES_IN_HOUR=60;

    public Report getData(){
        List<TaxiPriceEntity> taxiPriceEntityList= taxiPriceDAO.getPricesOrderByCarCategory();
        PriceReportImpl priceReport=new PriceReportImpl();

        priceReport.setReportTitle("Price list");

        priceReport.addColumnTitle("Car category");
        priceReport.addColumnTitle("Special time or day");
        priceReport.addColumnTitle("Minimal price (order lowest 5km)");
        priceReport.addColumnTitle("Price per km");
        priceReport.addColumnTitle("Price per min");
        priceReport.addColumnTitle("Price per hour");


        for (int i = 0; i < taxiPriceEntityList.size() ; i++) {
            DataObjectArray dataObjectArray=new DataObjectArrayImpl();
            dataObjectArray.add(taxiPriceEntityList.get(i).getCarCategory().toString());
            if(taxiPriceEntityList.get(i).getNightTariff()&&!taxiPriceEntityList.get(i).getWeekend()){
                dataObjectArray.add("With weekend tariff");
            }else
            if(taxiPriceEntityList.get(i).getWeekend()&&!taxiPriceEntityList.get(i).getNightTariff()){
                dataObjectArray.add("With weekend tariff");
            }else
            if(!taxiPriceEntityList.get(i).getWeekend()&&!taxiPriceEntityList.get(i).getNightTariff()){
                dataObjectArray.add("None");
            }else if(taxiPriceEntityList.get(i).getNightTariff()&&taxiPriceEntityList.get(i).getWeekend()){
                dataObjectArray.add("With weekend and night tariff");
            }
            BigDecimal pricePerKmDigDecimal=taxiPriceEntityList.get(i).getPricePerKm();
            long pricePerKm=pricePerKmDigDecimal.longValue();
            dataObjectArray.add(pricePerKm * MIN_DISTANCE);
            dataObjectArray.add(pricePerKm);
            BigDecimal pricePerMinBigDecimal=taxiPriceEntityList.get(i).getPricePerKm();
            long pricePerMin=pricePerMinBigDecimal.longValue();
            dataObjectArray.add(pricePerMin);
            dataObjectArray.add(pricePerMin * MINUTES_IN_HOUR);
            priceReport.addDataObjectArray(dataObjectArray);
        }
        return priceReport;
    }
}
