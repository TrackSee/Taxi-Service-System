package ua.com.tracksee.rest;

import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.json.FavoritePlaceDTO;
import ua.com.tracksee.logic.facade.CustomerFacade;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static ua.com.tracksee.AttributeNames.USER_ID;

/**
 * @author Ruslan Gunavardana
 */
@Path("/places")
@Produces(APPLICATION_JSON)
@RequestScoped
public class FavoritePlacesService {
    private @EJB CustomerFacade customerFacade;

    @GET
    public Response getPlaces(@Context HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return Response.status(UNAUTHORIZED).build();
        }
        Integer userId = (Integer) session.getAttribute(USER_ID);
        return Response.ok(customerFacade.getFavoritePlacesFor(userId)).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response addPlace(@Context HttpServletRequest req, FavoritePlaceDTO place) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return Response.status(UNAUTHORIZED).build();
        }
        Integer userId = (Integer) session.getAttribute(USER_ID);
        customerFacade.addFavoritePlaceFor(userId, place);
        return Response.ok(customerFacade.getFavoritePlacesFor(userId)).build();
    }

    @DELETE
    @Consumes(APPLICATION_JSON)
    public Response removePlace(@Context HttpServletRequest req, FavoritePlaceDTO place) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return Response.status(UNAUTHORIZED).build();
        }
        Integer userId = (Integer) session.getAttribute(USER_ID);
        customerFacade.removeFavouritePlaceFor(userId, place.getName());
        return Response.ok(customerFacade.getFavoritePlacesFor(userId)).build();
    }
}
