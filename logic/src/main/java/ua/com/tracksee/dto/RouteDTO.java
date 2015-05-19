package ua.com.tracksee.dto;

import com.vividsolutions.jts.geom.LineString;

import static ua.com.tracksee.util.GeometryConverter.decodeGooglePolylineToLineString;
import static ua.com.tracksee.util.GeometryConverter.decodeGooglePolylineToLocations;

/**
 * @author Ruslan Gunavardana
 */
public class RouteDTO {
    private String encodedRoute;
    private float distance;
    private int durationInMin;

    public RouteDTO() {

    }

    public Location[] getRouteLocations() {
        return decodeGooglePolylineToLocations(encodedRoute);
    }

    public LineString getRouteLineString() {
        return decodeGooglePolylineToLineString(encodedRoute);
    }

    public String getEncodedRoute() {
        return encodedRoute;
    }

    public void setEncodedRoute(String encodedRoute) {
        this.encodedRoute = encodedRoute;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getDurationInMin() {
        return durationInMin;
    }

    public void setDurationInMin(int durationInMin) {
        this.durationInMin = durationInMin;
    }
}
