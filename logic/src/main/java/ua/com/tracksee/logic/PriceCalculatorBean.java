package ua.com.tracksee.logic;

import org.joda.time.DateTime;
import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;
import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.enumartion.Service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;
import static ua.com.tracksee.enumartion.Service.CELEBRATION_TAXI;
import static ua.com.tracksee.enumartion.Service.TAXI_FOR_LONG_TERM;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class PriceCalculatorBean {
    public static final int NIGHT_TARIFF_FROM_HOUR = 22;
    public static final int NIGHT_TARIFF_TILL_HOUR = 6;
    public static final int[] WEEKEND_DAYS = {SATURDAY, SUNDAY};

    private @EJB TaxiPriceDAO taxiPriceDAO;

    public BigDecimal calculatePrice(TaxiOrderEntity order) {
        BigDecimal servicePrice = getServicePrice(order);

        TaxiOrderItemEntity[] orderItems = {};
        BigDecimal summ = BigDecimal.ZERO;
        for (TaxiOrderItemEntity item : orderItems) {
            summ = summ.add(servicePrice.multiply(item.getOrderedQuantity()));
        }

        return summ;
    }

    public double simpleCalculatePrice(int distance){
        double price=0;

        //TODO getting taxiPrice from database
        int taxiPricePerKm=1;

        price=distance*taxiPricePerKm;
        return price;
    }
    private BigDecimal getServicePrice(TaxiOrderEntity order) {
        TaxiPriceEntity priceList = getTariff(order);
        Service service = order.getService();

        if (service.equals(CELEBRATION_TAXI) || service.equals(TAXI_FOR_LONG_TERM)) {
            return priceList.getPricePerMin();
        } else {
            return priceList.getPricePerKm();
        }
    }

    private TaxiPriceEntity getTariff(TaxiOrderEntity order) {
        Timestamp timestamp = order.getOrderedDate();
        DateTime time = timestamp != null? new DateTime(timestamp.getTime()) : DateTime.now();
        int hour = time.getHourOfDay();
        int dayOfWeek = time.getDayOfWeek();

        boolean isNightTariff = hour <= NIGHT_TARIFF_TILL_HOUR & hour <= NIGHT_TARIFF_FROM_HOUR
                | hour >= NIGHT_TARIFF_FROM_HOUR & hour >= NIGHT_TARIFF_TILL_HOUR;
        boolean isWeekend = false;
        for (int i : WEEKEND_DAYS) {
            isWeekend |= i == dayOfWeek;
        }

        //TODO keep this map in @Singleton EJB
        return taxiPriceDAO.getPriceFor(order.getCarCategory(), isNightTariff, isWeekend);
    }
}
