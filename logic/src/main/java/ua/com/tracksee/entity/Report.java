package ua.com.tracksee.entity;

import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.entities.TaxiPriceEntity;

import javax.ejb.EJB;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sharaban Sasha
 */
public interface Report {
    public String getReportTitle();
    public ArrayList<String> getTitles();
    public ArrayList<ArrayList<String>> getData();
}
