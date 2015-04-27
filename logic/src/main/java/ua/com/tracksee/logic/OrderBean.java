package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.CarCategory;
import ua.com.tracksee.enumartion.Sex;
import ua.com.tracksee.enumartion.WayOfPayment;
import ua.com.tracksee.json.TaxiOrderDTO;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import static ua.com.tracksee.enumartion.OrderStatus.QUEUED;

/**
 * Bean used for any order processing business logic.
 *
 * @author Ruslan Gunavardana
 */
@Stateless
@DeclareRoles({"customer", "unregistered"})
public class OrderBean {
    private static final Logger logger = LogManager.getLogger();
    private @EJB TaxiOrderDAO taxiOrderDAO;

    /**
     * Creates taxi order of authorised user.
     *
     * @param userId id of authorised customer user
     * @param orderDTO basic information about the order
     */
    @RolesAllowed("customer")
    public Long createAuthorisedOrder(Integer userId, TaxiOrderDTO orderDTO) {
        TaxiOrderEntity order = new TaxiOrderEntity();
        order.setUserId(userId);
        order.setStatus(QUEUED);
        //TODO service + price calculation at server order.setPrice();

        // collecting data from DTO
        try {
            order.setCarCategory(CarCategory.valueOf(orderDTO.getCarCategory()));
            order.setWayOfPayment(WayOfPayment.valueOf(orderDTO.getWayOfPayment()));
            order.setDriverSex(Sex.valueOf(orderDTO.getDriverSex()));
        } catch (IllegalArgumentException e) {
            logger.warn("Could not parse enum during taxi order creation.");
            return null;
        }

        order.setDescription(orderDTO.getDescription());
        order.setMusicStyle(orderDTO.getMusicStyle());
        order.setAnimalTransportation(orderDTO.getAnimalTransportation());
        order.setFreeWifi(orderDTO.getFreeWiFi());
        order.setSmokingDriver(orderDTO.getSmokingDriver());
        order.setAirConditioner(orderDTO.getAirConditioner());
        return taxiOrderDAO.addOrder(order);
    }
}
