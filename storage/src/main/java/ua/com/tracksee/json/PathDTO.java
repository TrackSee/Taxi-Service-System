package ua.com.tracksee.json;

/**
 * @author Ruslan Gunavardana
 */
public class PathDTO {
    private LocationDTO[] points;

    public PathDTO() {
    }

    public PathDTO(LocationDTO[] points) {
        this.points = points;
    }

    public LocationDTO[] getPoints() {
        return points;
    }

    public void setPoints(LocationDTO[] points) {
        this.points = points;
    }
}
