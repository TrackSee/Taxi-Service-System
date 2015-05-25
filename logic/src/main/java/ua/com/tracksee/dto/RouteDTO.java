package ua.com.tracksee.dto;

import com.vividsolutions.jts.geom.LineString;

import java.math.BigDecimal;
import java.util.List;

import static ua.com.tracksee.util.GeometryConverter.decodeGooglePolylineToLineString;
import static ua.com.tracksee.util.GeometryConverter.decodeGooglePolylineToLocations;
import static ua.com.tracksee.util.GeometryConverter.encodeToGooglePolyLine;

/**
 * @author Ruslan Gunavardana
 */
public class RouteDTO {
    private String encodedRoute;
    private BigDecimal distance;
    private int durationInMin;

    public RouteDTO() {
    }

    public RouteDTO(LineString lineString, BigDecimal distance) {
        this.encodedRoute = encodeToGooglePolyLine(lineString);
        this.distance = distance;
    }

    public List<Location> getRouteLocations() {
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

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public int getDurationInMin() {
        return durationInMin;
    }

    public void setDurationInMin(int durationInMin) {
        this.durationInMin = durationInMin;
    }
}
