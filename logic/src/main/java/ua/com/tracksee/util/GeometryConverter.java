package ua.com.tracksee.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import ua.com.tracksee.dto.Location;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

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

    public static LineString locationsToLineString(List<Location> path) {
        if (path == null) {
            return null;
        }

        if (path.size() == 1) {
            Location startAndFinish = path.get(0);
            path = asList(startAndFinish, startAndFinish);
        }

        Coordinate[] coordinates = new Coordinate[path.size()];
        for (int i = 0; i < path.size(); i++) {
            coordinates[i] = new Coordinate(path.get(i).getLat(), path.get(i).getLng());
        }

        return geometryFactory.createLineString(coordinates);
    }

    public static List<Location> lineStringToLocations(LineString lineString) {
        if (lineString == null) {
            return null;
        }

        Coordinate[] coordinates = lineString.getCoordinates();
        Location[] locations = new Location[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            locations[i] = new Location(coordinates[i].x, coordinates[i].y);
        }

        return asList(locations);
    }

    public static List<Location> decodeGooglePolylineToLocations(String encoded) {
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
            Location p = new Location((((double) lat / 1E5)), (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }

    public static LineString decodeGooglePolylineToLineString(String encoded) {
        return locationsToLineString(decodeGooglePolylineToLocations(encoded));
    }

    public static String encodeToGooglePolyLine(LineString polyline) {
        return encodeToGooglePolyLine(lineStringToLocations(polyline));
    }

    public static String encodeToGooglePolyLine(List<Location> polyline) {
        StringBuilder encodedPoints = new StringBuilder();
        int prev_lat = 0, prev_lng = 0;
        for (Location trackpoint : polyline) {
            int lat = (int) (trackpoint.getLat() * 1E5);
            int lng = (int) (trackpoint.getLng() * 1E5);
            encodedPoints.append(encodeSignedNumber(lat - prev_lat));
            encodedPoints.append(encodeSignedNumber(lng - prev_lng));
            prev_lat = lat;
            prev_lng = lng;
        }
        return encodedPoints.toString();
    }

    private static StringBuffer encodeSignedNumber(int num) {
        int sgn_num = num << 1;
        if (num < 0) {
            sgn_num = ~(sgn_num);
        }
        return(encodeNumber(sgn_num));
    }

    private static StringBuffer encodeNumber(int num) {
        StringBuffer encodeString = new StringBuffer();
        while (num >= 0x20) {
            int nextValue = (0x20 | (num & 0x1f)) + 63;
            encodeString.append((char)(nextValue));
            num >>= 5;
        }
        num += 63;
        encodeString.append((char)(num));
        return encodeString;
    }
}
