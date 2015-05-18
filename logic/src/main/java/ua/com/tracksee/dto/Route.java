package ua.com.tracksee.dto;

import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
public class Route {
    private Location[] points;

    public Route() {
    }

    public Route(Location[] points) {
        this.points = points;
    }

    public Route(List<Location> points) {
        this.points = points.toArray(new Location[points.size()]);
    }

    public Location[] getPoints() {
        return points;
    }

    public void setPoints(Location[] points) {
        this.points = points;
    }
}
