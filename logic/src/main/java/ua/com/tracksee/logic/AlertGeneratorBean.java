package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.FavoritePlaceDAO;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.MostPopularOption;
import ua.com.tracksee.entities.ServiceProfitable;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.enumartion.*;
import ua.com.tracksee.exception.OrderException;
import ua.com.tracksee.json.TaxiOrderDTO;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static ua.com.tracksee.enumartion.OrderStatus.QUEUED;

/**
 * @author Sharaban Sasha
 * @see ua.com.tracksee.logic.AlertGenerator
 */
@Stateless
public class AlertGeneratorBean  {
    private static final Logger logger = LogManager.getLogger();
    /**
     * Parts of popup alert and
     * different alert types.
     */
    private static final String ALERT_TYPE_SUCCESS = "<div class=\"alert alert-success\"";
    private static final String ALERT_TYPE_INFO = "<div class=\"alert alert-info\"";
    private static final String ALERT_TYPE_WARNING = "<div class=\"alert alert-warning\"";
    private static final String ALERT_TYPE_DANGER = "<div class=\"alert alert-danger\"";
    private static final String ALERT_BODY = " role=\"alert\">\n" +
            "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
            "                    <span aria-hidden=\"true\">&times;</span></button>";
    private static final String ALERT_END = "</div>";
    private static final String FONT_BEGIN="<h3>";
    private static final String FONT_END="</h3>";

    /**
     * Returns complete success alert popup
     * that contain input text and ready to
     * be displayed on web page.
     *
     * @author Sharaban Sasha
     * @param inputText text that must be displayed in alert
     * @return complete alert popup with text ready for show
     */
   public String getSuccessAlert(String inputText){
       return ALERT_TYPE_SUCCESS+ALERT_BODY+FONT_BEGIN+inputText+FONT_END+ALERT_END;
   }

    /**
     * Returns complete info alert popup
     * that contain input text and ready to
     * be displayed on web page.
     *
     * @author Sharaban Sasha
     * @param inputText text that must be displayed in alert
     * @return complete alert popup with text ready for show
     */
    public String getInfoAlert(String inputText){
        return ALERT_TYPE_INFO+ALERT_BODY+FONT_BEGIN+inputText+FONT_END+ALERT_END;
    }

    /**
     * Returns complete warning alert popup
     * that contain input text and ready to
     * be displayed on web page.
     *
     * @author Sharaban Sasha
     * @param inputText text that must be displayed in alert
     * @return complete alert popup with text ready for show
     */
    public String getWarningAlert(String inputText){

        return ALERT_TYPE_WARNING+ALERT_BODY+FONT_BEGIN+inputText+FONT_END+ALERT_END;
    }

    /**
     * Returns complete danger alert popup
     * that contain input text and ready to
     * be displayed on web page.
     *
     * @author Sharaban Sasha
     * @param inputText text that must be displayed in alert
     * @return complete alert popup with text ready for show
     */
    public String getDangerAlert(String inputText){
        return ALERT_TYPE_DANGER+ALERT_BODY+FONT_BEGIN+inputText+FONT_END+ALERT_END;
    }

}

