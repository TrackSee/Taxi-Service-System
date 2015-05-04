package ua.com.tracksee.servlets.customer;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.OrderStatus;
import ua.com.tracksee.enumartion.Sex;
import ua.com.tracksee.logic.OrderBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by Igor on 03.05.2015.
 */
@WebServlet("UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {

    @EJB
    private OrderBean orderBean;

    private static final Logger logger = LogManager.getLogger();

    private static final String STATUS = "status";
    private static final String DRIVER_SEX = "driverSex";
    private static final String MUSIC_STYLE = "musicStyle";
    private static final String ANIMAL_TRANSPORTATION = "animalTransportation";
    private static final String WIFI = "wifi";
    private static final String SMOKING_DRIVER = "smokingDriver";
    private static final String CONDITIONER = "airConditioner";
    private static final String TRACKING_NUMBER = "trackingNumber";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackNumberStr = req.getParameter(TRACKING_NUMBER);
        String status = req.getParameter(STATUS);
        String driverSex = req.getParameter(DRIVER_SEX);
        String musicStyle = req.getParameter(MUSIC_STYLE);
        String animalTransportation = req.getParameter(ANIMAL_TRANSPORTATION);
        String wifiStr = req.getParameter(WIFI);
        String smokingDriverStr = req.getParameter(SMOKING_DRIVER);
        String conditionerStr = req.getParameter(CONDITIONER);

        Integer trackingNumber = null;
        OrderStatus orderStatus = null;
        Sex sex = null;
        Boolean animalTranport = Boolean.parseBoolean(animalTransportation);
        Boolean wifi = Boolean.parseBoolean(wifiStr);
        Boolean smokingDriver = Boolean.parseBoolean(smokingDriverStr);
        Boolean conditioner = Boolean.parseBoolean(conditionerStr);
        try {
            trackingNumber = Integer.parseInt(trackNumberStr);
        } catch (NumberFormatException e) {
            logger.error("Impossible convert String to Integer! " + e.getMessage());
        }
        try {
            orderStatus = OrderStatus.fromString(status);
            sex = Sex.fromString(driverSex);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }

        TaxiOrderEntity entity = new TaxiOrderEntity();
        if ((trackingNumber != null) && (orderStatus != null) && (sex != null))  {
            entity.setTrackingNumber(trackingNumber);
            entity.setStatus(orderStatus);
            entity.setDriverSex(sex);
            entity.setMusicStyle(musicStyle);
            entity.setAnimalTransportation(animalTranport);
            entity.setFreeWifi(wifi);
            entity.setSmokingDriver(smokingDriver);
            entity.setAirConditioner(conditioner);

            orderBean.updateOrder(entity);
        }
    }
}