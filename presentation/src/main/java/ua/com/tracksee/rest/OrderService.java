package ua.com.tracksee.rest;

import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.json.TaxiOrderDTO;
import ua.com.tracksee.logic.facade.OrderFacade;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static ua.com.tracksee.AttributeNames.USER_ID;

/**
 * @author Ruslan Gunavardana
 */
@Path("/order")
public class OrderService {
    private @EJB OrderFacade orderFacade;

    @GET
    @Produces(APPLICATION_JSON)
    public Response getUserOrders(@Context HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return Response.status(UNAUTHORIZED).build();
        }
        Integer userId = (Integer) session.getAttribute(USER_ID);
        return Response.ok(orderFacade.getOrdersFor(userId)).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response addOrder(@Context HttpServletRequest req, TaxiOrderDTO order) {
//        HttpSession session = req.getSession(false);
//
//        // unathorised order
//        if (session == null) {
//            orderFacade.placeOrder();
//        }
        return Response.status(CREATED).build();
    }
}
