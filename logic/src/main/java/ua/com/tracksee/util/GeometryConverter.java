package ua.com.tracksee.util;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.exception.ConversionRuntimeException;
import ua.com.tracksee.json.LocationDTO;

/**
 * @author Ruslan Gunavardana
 */
public class GeometryConverter {
    private static final WKTReader reader = new WKTReader();
    private static final Logger logger = LogManager.getLogger(GeometryConverter.class);

    public static Point toPoint(LocationDTO loc) {
        if (loc == null) {
            return null;
        }

        String string = "POINT(" + loc.getLat() + " " + loc.getLng() + ")";
        try {
            return (Point) reader.read(string);
        } catch (ParseException | ClassCastException e) {
            String msg = "Parsing POINT(1 1) format expected";
            logger.error(msg);
            throw new ConversionRuntimeException(msg);
        }
    }

    public static LocationDTO toLocationDTO(Point point) {
        if (point == null) {
            return null;
        }
        return new LocationDTO(point.getX(), point.getY());
    }
}
