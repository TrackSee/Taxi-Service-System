package ua.com.tracksee.rest.customer;

import ua.com.tracksee.dto.FavoritePlaceDTO;
import ua.com.tracksee.logic.facade.CustomerFacade;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;
import static ua.com.tracksee.servlets.AttributeNames.USER_ID;

/**
 * @author Ruslan Gunavardana
 */
@Path("/customer/places")
@RequestScoped
public class FavoritePlacesService {

    private @EJB CustomerFacade customerFacade;
    private @Context HttpServletRequest request;

    private Integer getUserId() {
        HttpSession session = request.getSession(false);
        return session != null ? (Integer) session.getAttribute(USER_ID) : null;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getPlaces() {
        Integer userId = getUserId();
        if (userId == null) {
            return Response.status(UNAUTHORIZED).build();
        }
        return Response.ok(customerFacade.getFavoritePlacesFor(userId)).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response addPlace(FavoritePlaceDTO place) {
        Integer userId = getUserId();
        if (userId == null) {
            return Response.status(UNAUTHORIZED).build();
        }
        String name = place.getName();
        return customerFacade.addFavoritePlaceFor(userId, place) ? Response.status(CREATED).entity(name).build()
                                                                 : Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Path("{name}")
    public Response updatePlace(@PathParam("name") String name, FavoritePlaceDTO newValue) {
        Integer userId = getUserId();
        if (userId == null) {
            return Response.status(UNAUTHORIZED).build();
        }
        return customerFacade.removeFavoritePlaceFor(userId, name)
                ? Response.status(NO_CONTENT).build()
                : Response.status(BAD_REQUEST).build();
    }

    @DELETE
    @Path("{name}")
    public Response removePlace(@PathParam("name") String name) {
        Integer userId = getUserId();
        if (userId == null) {
            return Response.status(UNAUTHORIZED).build();
        }
        return customerFacade.removeFavoritePlaceFor(userId, name)
                ? Response.status(NO_CONTENT).build()
                : Response.status(BAD_REQUEST).build();
    }
}
