package ua.com.tracksee.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import ua.com.tracksee.json.LocationDTO;
import ua.com.tracksee.json.PathDTO;

/**
 * @author Ruslan Gunavardana
 */
public class GeometryConverter {
    private static final GeometryFactory geometryFactory = new GeometryFactory();

    public static Point toPoint(LocationDTO loc) {
        if (loc == null) {
            return null;
        }

        Coordinate coordinate = new Coordinate(loc.getLat(), loc.getLng());
        return geometryFactory.createPoint(coordinate);
    }

    public static LocationDTO toLocationDTO(Point point) {
        if (point == null) {
            return null;
        }
        return new LocationDTO(point.getX(), point.getY());
    }

    public static LineString toLineString(PathDTO path) {
        if (path == null) {
            return null;
        }

        LocationDTO[] points = path.getPoints();
        Coordinate[] coordinates = new Coordinate[points.length];
        for (int i = 0; i < points.length; i++) {
            coordinates[i] = new Coordinate(points[i].getLat(), points[i].getLng());
        }

        return geometryFactory.createLineString(coordinates);
    }

    public static PathDTO toPathDTO(LineString lineString) {
        if (lineString == null) {
            return null;
        }

        Coordinate[] coordinates = lineString.getCoordinates();
        LocationDTO[] locations = new LocationDTO[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            locations[i] = new LocationDTO(coordinates[i].x, coordinates[i].y);
        }

        return new PathDTO(locations);
    }
}
