package ua.com.tracksee.json;

import java.util.Objects;

/**
 * @author Ruslan Gunavardana
 */
public class FavoritePlaceDTO {
    private String name;
    private LocationDTO location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritePlaceDTO that = (FavoritePlaceDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    @Override
    public String toString() {
        return "FavouritePlaceDTO{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}
