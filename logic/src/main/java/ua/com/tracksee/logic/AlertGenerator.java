package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Local;

/**
 * The AlertGenerator interface provides
 * generating any popup alert to further
 * display on web page.
 *
 * @author Sharaban Sasha
 */

public interface AlertGenerator {
    //TODO Decide about using this interface Sharaban Sasha
    /**
     * Parts of popup alert and
     * different alert types.
     */
    static final String ALERT_TYPE_SUCCESS = "<div class=\"alert alert-success\"";
    static final String ALERT_TYPE_INFO = "<div class=\"alert alert-info\"";
    static final String ALERT_TYPE_WARNING = "<div class=\"alert alert-warning\"";
    static final String ALERT_TYPE_DANGER = "<div class=\"alert alert-danger\"";
    static final String ALERT_BODY = " role=\"alert\">\n" +
            "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
            "                    <span aria-hidden=\"true\">&times;</span></button><h3>";
    static final String ALERT_END = "</div>";


    /**
     * Returns complete success alert popup
     * that contain input text and ready to
     * be displayed on web page.
     *
     * @author Sharaban Sasha
     * @param inputText text that must be displayed in alert
     * @return complete alert popup with text ready for show
     */
    public String getSuccessAlert(String inputText);

    /**
     * Returns complete info alert popup
     * that contain input text and ready to
     * be displayed on web page.
     *
     * @author Sharaban Sasha
     * @param inputText text that must be displayed in alert
     * @return complete alert popup with text ready for show
     */
    public String getInfoAlert(String inputText);

    /**
     * Returns complete warning alert popup
     * that contain input text and ready to
     * be displayed on web page.
     *
     * @author Sharaban Sasha
     * @param inputText text that must be displayed in alert
     * @return complete alert popup with text ready for show
     */
    public String getWarningAlert(String inputText);

    /**
     * Returns complete danger alert popup
     * that contain input text and ready to
     * be displayed on web page.
     *
     * @author Sharaban Sasha
     * @param inputText text that must be displayed in alert
     * @return complete alert popup with text ready for show
     */
    public String getDangerAlert(String inputText);

}

