package ua.com.tracksee.json;

/**
 * Taxi order data transfer object class.
 * Objects of this class are used for converting to and from JSON.
 *
 * @author Ruslan Gunavardana
 */
public class LocationDTO {
    private double lat;
    private double lng;

    public LocationDTO() {
    }

    public LocationDTO(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
