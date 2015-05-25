package ua.com.tracksee.rest.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dto.TaxiOrdersDTO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.facade.DriverFacade;
import ua.com.tracksee.logic.facade.OrderFacade;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import java.math.BigInteger;
import java.util.List;

import static java.lang.Math.ceil;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.FORBIDDEN;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static ua.com.tracksee.dao.TaxiOrderDAO.ORDERS_PAGE_SIZE;
import static ua.com.tracksee.servlets.AttributeNames.USER_ID;

/**
 * @author Ruslan Gunavardana
 */
@Path("/driver/orders")
public class DriverOrdersService {
    private static final Logger logger = LogManager.getLogger(DriverOrdersService.class);

    private @EJB OrderFacade orderFacade;
    private @EJB DriverFacade driverFacade;
    private @Context HttpServletRequest request;

    private UserEntity driver;

    private Integer getDriverId() {
        HttpSession session = request.getSession(false);
        return session != null ? (Integer) session.getAttribute(USER_ID) : null;
    }

    /**
     *
     * @return null if user may access data, else refusion report
     */
    private Response tryToRefuseRequest(int pageNumber) {
        Integer userId = getDriverId();
        logger.debug("User {} asked available orders page #{}", userId, pageNumber);
        if (userId == null) {
            logger.warn("Unauthorised access to available orders");
            return Response.status(UNAUTHORIZED).build();
        }
        driver = driverFacade.getDriverByID(getDriverId());
        if (!driver.getDriver()) {
            logger.warn("NonDriver access to available orders.");
            return Response.status(FORBIDDEN).build();
        }
        return null;
    }

    @GET
    @Path("/available")
    @Produces(APPLICATION_JSON)
    public Response getAvailableOrders(@QueryParam("page") int pageNumber) {
        Response failResponse = tryToRefuseRequest(pageNumber);
        if (failResponse != null) {
            return failResponse;
        }
        BigInteger totalCount = orderFacade.getOrdersPagesCountQueued(driver);
        logger.debug("For driver #{} {} available orders is found", driver.getUserId(), totalCount);
        if (pageNumber < 1 || pageNumber > ceil(totalCount.intValue() / (double) ORDERS_PAGE_SIZE)) {
            return Response.status(BAD_REQUEST).build();
        }
        List<TaxiOrderEntity> orders = orderFacade.getAvailableOrders(driver, pageNumber);
        return Response.ok(new TaxiOrdersDTO(totalCount, orders)).build();
    }

    @GET
    @Path("/assigned")
    @Produces(APPLICATION_JSON)
    public Response getAssignedOrders(@QueryParam("page") int pageNumber) {
        Response failResponse = tryToRefuseRequest(pageNumber);
        if (failResponse != null) {
            return failResponse;
        }

        BigInteger totalCount = orderFacade.getOrdersPagesCountQueued(driver);
        logger.debug("For driver #{} {} assigned orders is found", driver.getUserId(), totalCount);
        if (pageNumber < 1 || pageNumber > ceil(totalCount.intValue() / (double) ORDERS_PAGE_SIZE)) {
            return Response.status(BAD_REQUEST).build();
        }
        List<TaxiOrderEntity> orders = orderFacade.getAssignedOrders(driver.getUserId(), pageNumber);
        return Response.ok(new TaxiOrdersDTO(totalCount, orders)).build();
    }

    @GET
    @Path("/history")
    @Produces(APPLICATION_JSON)
    public Response getOrderHistory(@QueryParam("page") int pageNumber) {
        Response failResponse = tryToRefuseRequest(pageNumber);
        if (failResponse != null) {
            return failResponse;
        }

        BigInteger totalCount = orderFacade.getOrdersPagesCountCompleted(driver.getUserId());
        logger.debug("For driver #{} {} history of orders is found", driver.getUserId(), totalCount);
        if (pageNumber < 1 || pageNumber > ceil(totalCount.intValue() / (double) ORDERS_PAGE_SIZE)) {
            return Response.status(BAD_REQUEST).build();
        }
        List<TaxiOrderEntity> orders = orderFacade.getHistoryOfOrders(driver.getUserId(), pageNumber);
        return Response.ok(new TaxiOrdersDTO(totalCount, orders)).build();
    }
}
