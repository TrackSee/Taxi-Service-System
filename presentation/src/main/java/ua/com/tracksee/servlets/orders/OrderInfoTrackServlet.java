package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.*;
import ua.com.tracksee.logic.TaxiOrderBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sharaban Sasha
 */
@WebServlet("/orderTracking")
public class OrderInfoTrackServlet extends HttpServlet {
    private
    @EJB
    TaxiOrderBean taxiOrderBean;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String service=null;
        String musicStyle=null;
        String driverSex=null;
        String carCategory=null;
        String wayOfPayment=null;
        try {
            int trackingNumber=Integer.parseInt(req.getParameter("orderTrackingNumber"));

            TaxiOrderEntity taxiOrderEntity = taxiOrderBean.getOrderInfo(trackingNumber);
            ServiceUserEntity serviceUserEntity=taxiOrderBean.getUserInfo(taxiOrderEntity.getUserId());
            AddressEntity addressEntity=taxiOrderBean.getAddressInfo(taxiOrderEntity.getUserId());

            req.setAttribute("trackingNumber", trackingNumber);
            req.setAttribute("phoneNumber", serviceUserEntity.getPhone());
            req.setAttribute("email",serviceUserEntity.getEmail());

            String[] addresses = addressEntity.getStringRepresentation().split("-");
            req.setAttribute("addressOrigin",addresses[0]);
            req.setAttribute("addressDestination", addresses[1]);

            req.setAttribute("arriveDate", taxiOrderEntity.getArriveDate());
            req.setAttribute("endDate", taxiOrderEntity.getEndDate());

            req.setAttribute("description", taxiOrderEntity.getDescription());
            if(taxiOrderEntity.getComment()!=null) {
                req.setAttribute("comments", taxiOrderEntity.getComment());
                req.setAttribute("commentsState","disabled=\"disabled\"");
                req.setAttribute("buttonCommentsHide","hidden=\"hidden\"");

            }
            if(taxiOrderEntity.getWayOfPayment()== WayOfPayment.CASH){
                wayOfPayment="cash";
            }
            if(taxiOrderEntity.getWayOfPayment()== WayOfPayment.VISA_CARD){
                wayOfPayment="visaCard";
            }
            req.setAttribute(wayOfPayment,"selected=\"selected\"");


            if(taxiOrderEntity.getService()== Service.CARGO_TAXI){
                service="cargoTaxi";
            }
            if(taxiOrderEntity.getService()==Service.CELEBRATION_TAXI){
                service="celebrationTaxi";
            }
            if(taxiOrderEntity.getService()==Service.CONVEY_CORPORATION_EMPLOYEES){
                service="conveyCorporationEmployees";
            }
            if(taxiOrderEntity.getService()==Service.FOODSTUFF_DELIVERY){
                service="foodStuffDelivery";
            }
            if(taxiOrderEntity.getService()==Service.GUEST_DELIVERY){
                service="guestDelivery";
            }
            if(taxiOrderEntity.getService()==Service.MEET_MY_GUEST){
                service="meetMyGuest";
            }
            if(taxiOrderEntity.getService()==Service.SIMPLE_TAXI){
                service="simpleTaxi";
            }
            if(taxiOrderEntity.getService()==Service.SOBER_DRIVER){
                service="soberDriver";
            }
            if(taxiOrderEntity.getService()==Service.TAXI_FOR_LONG_TERM){
                service="taxiForLongTerm";
            }
            req.setAttribute(service,"selected=\"selected\"");

            if(taxiOrderEntity.getMusicStyle()== MusicStyle.BLUES){
                musicStyle="blues";
            }
            if(taxiOrderEntity.getMusicStyle()== MusicStyle.CLASSICAL_MUSIC){
                musicStyle="classicalMusic";
            }
            if(taxiOrderEntity.getMusicStyle()== MusicStyle.DANCE_MUSIC){
                musicStyle="danceMusic";
            }
            if(taxiOrderEntity.getMusicStyle()== MusicStyle.DEFAULT){
                musicStyle="default";
            }
            if(taxiOrderEntity.getMusicStyle()== MusicStyle.ELECTRONIC_MUSIC){
                musicStyle="electronicMusic";
            }
            if(taxiOrderEntity.getMusicStyle()== MusicStyle.HIP_HOP){
                musicStyle="hiHop";
            }
            if(taxiOrderEntity.getMusicStyle()== MusicStyle.JAZZ){
                musicStyle="jazz";
            }
            if(taxiOrderEntity.getMusicStyle()== MusicStyle.ROCK){
                musicStyle="rock";
            }
            req.setAttribute(musicStyle,"selected=\"selected\"");


            if(taxiOrderEntity.getDriverSex()== Sex.A){
                driverSex="anyone";
            }
            if(taxiOrderEntity.getDriverSex()== Sex.M){
                driverSex="male";
            }
            if(taxiOrderEntity.getDriverSex()== Sex.F){
                driverSex="female";
            }

            req.setAttribute(driverSex,"selected=\"selected\"");

            if(taxiOrderEntity.getCarCategory()== CarCategory.BUSINESS_CLASS){
                carCategory="businessClass";
            }
            if(taxiOrderEntity.getCarCategory()== CarCategory.ECONOMY_CLASS){
                carCategory="economyClass";
            }
            if(taxiOrderEntity.getCarCategory()== CarCategory.VAN){
                carCategory="van";
            }
            req.setAttribute(carCategory,"selected=\"selected\"");

            if(taxiOrderEntity.getAnimalTransportation()){
            req.setAttribute("animalTransportation", "checked=\"checked\"");
            }
            if(taxiOrderEntity.getFreeWifi()){
            req.setAttribute("freeWifi","checked=\"checked\"");
            }
            if(taxiOrderEntity.getNonSmokingDriver()) {
                req.setAttribute("smokingDriver","checked=\"checked\"");
            }
            if(taxiOrderEntity.getAirConditioner()) {
                req.setAttribute("airConditioner","checked=\"checked\"");
            }


            if(taxiOrderEntity.getStatus()==OrderStatus.REFUSED||taxiOrderEntity.getStatus()== OrderStatus.COMPLETED){

            req.getRequestDispatcher("/WEB-INF/customer/orderTrackComplete.jsp").forward(req, resp);
            }else{
                req.getRequestDispatcher("/WEB-INF/customer/orderTrack.jsp").forward(req, resp);
            }
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
        }

    }
    }
