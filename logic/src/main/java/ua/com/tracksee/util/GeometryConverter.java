package ua.com.tracksee.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import ua.com.tracksee.dto.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
public class GeometryConverter {
    private static final GeometryFactory geometryFactory = new GeometryFactory();

    public static Point locationToPoint(Location loc) {
        if (loc == null) {
            return null;
        }

        Coordinate coordinate = new Coordinate(loc.getLat(), loc.getLng());
        return geometryFactory.createPoint(coordinate);
    }

    public static Location pointToLocation(Point point) {
        if (point == null) {
            return null;
        }
        return new Location(point.getX(), point.getY());
    }

    public static LineString locationsToLineString(Location[] path) {
        if (path == null) {
            return null;
        }

        Coordinate[] coordinates = new Coordinate[path.length];
        for (int i = 0; i < path.length; i++) {
            coordinates[i] = new Coordinate(path[i].getLat(), path[i].getLng());
        }

        return geometryFactory.createLineString(coordinates);
    }

    public static Location[] lineStringToLocations(LineString lineString) {
        if (lineString == null) {
            return null;
        }

        Coordinate[] coordinates = lineString.getCoordinates();
        Location[] locations = new Location[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            locations[i] = new Location(coordinates[i].x, coordinates[i].y);
        }

        return locations;
    }

    public static Location[] decodeGooglePolylineToLocations(String encoded) {

        List<Location> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            Location p = new Location((int) (((double) lat / 1E5) * 1E6),
                    (int) (((double) lng / 1E5) * 1E6));
            System.out.println(p.getLat() + " " + p.getLng());
            poly.add(p);
        }

        return poly.toArray(new Location[poly.size()]);
    }

    public static LineString decodeGooglePolylineToLineString(String encoded) {
        return locationsToLineString(decodeGooglePolylineToLocations(encoded));
    }
}
