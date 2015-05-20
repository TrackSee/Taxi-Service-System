package ua.com.tracksee.logic;

import org.joda.time.DateTime;
import ua.com.tracksee.config.ConfigName;
import ua.com.tracksee.dao.ConfigDAO;
import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.enumartion.CarCategory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static javax.ejb.LockType.READ;
import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;
import static ua.com.tracksee.config.ConfigName.MINIMAL_ORDER_DISTANCE;

/**
 * @author Ruslan Gunavardana
 */
@Singleton
public class PriceListBean {
    public static final int NIGHT_TARIFF_FROM_HOUR = 22;
    public static final int NIGHT_TARIFF_TILL_HOUR = 6;
    public static final int[] WEEKEND_DAYS = {SATURDAY, SUNDAY};

    private @EJB ConfigDAO configDAO;
    private @EJB TaxiPriceDAO taxiPriceDAO;
    private TaxiPriceEntity[] prices;

    public PriceListBean() {
    }

//    @PostConstruct
//    private void postConstruct() {
//        List<TaxiPriceEntity> lists = taxiPriceDAO.getAllPrices();
//        prices = lists.toArray(new TaxiPriceEntity[lists.size()]);
//    }

    public List<TaxiPriceEntity> getPrices() {
//        List<TaxiPriceEntity> priceList = new ArrayList<>();
//        for (TaxiPriceEntity price : this.prices) {
//            priceList.add(price.clone());
//        }
//
//        return priceList;
        return taxiPriceDAO.getAllPrices();
    }

    public TaxiPriceEntity getPrice(TaxiOrderEntity order) {
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

        //TODO keep this map in memory
        return taxiPriceDAO.getPriceFor(order.getCarCategory(), isNightTariff, isWeekend);
    }

    public BigDecimal getMinimalOrderDistance() {
        return new BigDecimal(configDAO.getConfig(MINIMAL_ORDER_DISTANCE).getValue());
    }

    @Lock(READ)
    private TaxiPriceEntity getPriceInternal(CarCategory carCategory, boolean isNightTariff, boolean isWeekend) {
        throw new UnsupportedOperationException(); //TODO
    }
}
