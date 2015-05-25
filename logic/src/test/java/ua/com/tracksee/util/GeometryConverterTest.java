package ua.com.tracksee.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.tracksee.dto.Location;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ua.com.tracksee.util.GeometryConverter.decodeGooglePolylineToLocations;
import static ua.com.tracksee.util.GeometryConverter.encodeToGooglePolyLine;

/**
 * @author Ruslan Gunavardana
 */
public class GeometryConverterTest {

    private static List<Location> locations = new ArrayList<>();

    private static final Location loc1 = new Location(50, 30);
    private static final Location loc2 = new Location(33, 33);

    @Before
    public void setUp() throws Exception {
        locations.add(loc1.clone());
        locations.add(loc2.clone());
    }

    @After
    public void tearDown() throws Exception {
        locations.clear();
    }

    @Test
    public void testEncodeToGooglePolyline() throws Exception {
        String encoded = encodeToGooglePolyLine(locations);
        List<Location> decoded = decodeGooglePolylineToLocations(encoded);
        for (int i = 0; i < locations.size(); i++) {
            assertEquals(locations.get(i), decoded.get(i));
        }
    }
}