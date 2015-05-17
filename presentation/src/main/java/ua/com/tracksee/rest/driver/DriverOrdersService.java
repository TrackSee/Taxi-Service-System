package ua.com.tracksee.rest.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.facade.DriverFacade;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static ua.com.tracksee.servlets.AttributeNames.USER_ID;

/**
 * @author Ruslan Gunavardana
 */
@Path("/driver/orders")
public class DriverOrdersService {
    private static final Logger logger = LogManager.getLogger(DriverOrdersService.class);

    private @EJB DriverFacade driverFacade;
    private @Context HttpServletRequest request;

    private Integer getDriverId() {
        HttpSession session = request.getSession(false);
        return session != null ? (Integer) session.getAttribute(USER_ID) : null;
    }

    @GET
    @Path("/available")
    @Produces(APPLICATION_JSON)
    public Response getAvailableOrders() {
        //TODO
        return null;
    }
}
