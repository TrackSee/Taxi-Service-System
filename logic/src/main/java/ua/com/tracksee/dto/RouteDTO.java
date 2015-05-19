package ua.com.tracksee.dto;

import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
public class RouteDTO {
    private Location[] points;
    private float distance;
    private float duration;

    public RouteDTO() {
    }

    public RouteDTO(Location[] points) {
        this.points = points;
    }

    public RouteDTO(List<Location> points) {
        this.points = points.toArray(new Location[points.size()]);
    }

    public Location[] getPoints() {
        return points;
    }

    public void setPoints(Location[] points) {
        this.points = points;
    }
}
