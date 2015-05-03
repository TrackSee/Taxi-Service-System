package ua.com.tracksee.logic.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.postrgresql.CanselDAOBean;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.*;
import ua.com.tracksee.logic.OrderCancellationBean;
import ua.com.tracksee.logic.TaxiOrderBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ua.com.tracksee.enumartion.OrderStatus.QUEUED;

/**
 * Created by Sasha on 4/28/2015.
 */
@Singleton(name = "TestBeanEJB")
@Startup
public class TestBeanBean {
    private static final Logger logger = LogManager.getLogger();

    @PersistenceContext(unitName = "HibernatePU")
    EntityManager entityManager;
    private
    @EJB
    OrderCancellationBean cancellationBean;
    private
    @EJB
    TaxiOrderDAO taxiOrderDAO;
    private
    @EJB
    UserDAO userDAO;
    private
    @EJB
    TaxiOrderBean taxiOrderBean;
    @EJB
    TaxiOrderDAO orderDAO;
    @EJB
    CanselDAOBean canselDAO;

    public TestBeanBean() {
    }

    @PostConstruct
    public void test() {
        ServiceUserEntity user = createRandomUser();
        int startIgnoredTimes=user.getIgnoredTimes();
        TaxiOrderEntity order = createOrder(user);
        System.out.println("The user "+user.getEmail()+" ingnored times is "+startIgnoredTimes);
        long trackingNumber=order.getTrackingNumber();
        if (orderDAO.getOrder(trackingNumber) != null) {
            System.out.println("order was created"+trackingNumber);
            boolean rez = cancellationBean.cancelOrder(trackingNumber);
            System.out.println("The order wos refused?" + rez);
        } else {System.out.println("ERROR order DOES NOT added to DB");}
        int endIgnoredTimes=canselDAO.getUserRefusedTimes(order.getTrackingNumber());
        if(endIgnoredTimes==startIgnoredTimes+1) System.out.println("all normal user ignored times incruased");
        else {System.out.println("ERROR user ignored times false");
        System.out.println("The user ingnored times is "+user.getIgnoredTimes());
        }
//        boolean rez = cancellationBean.cancelOrder(1232164653);
//        if(rez)logger.error("THE NON EXISTED ORDER WAS REFUSED");
//        else System.out.println("yapy dont refuse non exisded order");
//        TaxiOrderEntity orderWhitoutUser=createOrder(new ServiceUserEntity());
//        rez = cancellationBean.cancelOrder(orderWhitoutUser.getTrackingNumber());
//        if(rez)logger.error("CANSEL ORDER WHITHOUT USER!!! FUUUUUCK");
//        else System.out.println("ha if no costumer no order mast beee");
    }

    /*
    @return new user;
     */
    private ServiceUserEntity createRandomUser() {
        ServiceUserEntity user = new ServiceUserEntity();
//        user.setEmail("test" + (int) (Math.random() * 25) + "@gmail.com");
        user.setEmail("test" + 20 + "@gmail.com");
        user.setPassword("none");
        user=checkUserPresent(user);
        System.out.println(user.getUserId() == userDAO.getUserIdByEmail(user.getEmail()));
        return user;
    }

    private ServiceUserEntity checkUserPresent(ServiceUserEntity serviceUserEntity) {
        if (userDAO.getUserIdByEmail(serviceUserEntity.getEmail()) != null) {
            System.out.println("User was found");
            serviceUserEntity=userDAO.getUserByEmail(serviceUserEntity.getEmail());
        } else {
            logger.info("User was not found");
            serviceUserEntity.setActivated(false);
            serviceUserEntity.setPassword("");
            serviceUserEntity.setUserId(userDAO.addUser(serviceUserEntity));
        }
        return serviceUserEntity;
    }

    /*
    @return new Taxi order
    @param user for order
     */
    private TaxiOrderEntity createOrder(ServiceUserEntity user) {
        TaxiOrderEntity order = new TaxiOrderEntity(QUEUED);
        order.setDescription("");
        order.setUserId(user.getUserId());
        order.setPrice(new BigDecimal(123));
        order.setService(Service.SIMPLE_TAXI);
        order.setCarCategory(CarCategory.USER_CAR);
        order.setWayOfPayment(WayOfPayment.CASH);
        order.setDriverSex(Sex.M);
        order.setMusicStyle(MusicStyle.CLASSICAL_MUSIC);
        order.setAnimalTransportation(false);
        order.setFreeWifi(true);
        order.setNonSmokingDriver(true);
        order.setAirConditioner(false);
        Timestamp timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parsedDate = dateFormat.parse("1994-12-12 21:32");
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setArriveDate(timestamp);
        order.setEndDate(timestamp);
        Long trackingNumber = taxiOrderDAO.addOrder(order);
        order.setTrackingNumber(trackingNumber);
        return order;
    }
}
