package ua.com.tracksee.json;

import org.joda.time.LocalDateTime;

/**
 * @author Ruslan Gunavardana
 */
public class TaxiOrderDTO {
    private LocationDTO[] path;
    private String service;
    private LocalDateTime orderDate;

    /* contact info */
    private String email;
    private String phoneNumber;

    /* additional info */
    private String carCategory;
    private String wayOfPayment;
    private String driverSex;
    private String musicStyle;
    private Boolean animalTransportation;
    private Boolean freeWiFi;
    private Boolean smokingDriver;
    private Boolean airConditioner;

    public LocationDTO[] getPath() {
        return path;
    }

    public void setPath(LocationDTO[] path) {
        this.path = path;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public String getWayOfPayment() {
        return wayOfPayment;
    }

    public void setWayOfPayment(String wayOfPayment) {
        this.wayOfPayment = wayOfPayment;
    }

    public String getDriverSex() {
        return driverSex;
    }

    public void setDriverSex(String driverSex) {
        this.driverSex = driverSex;
    }

    public String getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }

    public Boolean getAnimalTransportation() {
        return animalTransportation;
    }

    public void setAnimalTransportation(Boolean animalTransportation) {
        this.animalTransportation = animalTransportation;
    }

    public Boolean getFreeWiFi() {
        return freeWiFi;
    }

    public void setFreeWiFi(Boolean freeWiFi) {
        this.freeWiFi = freeWiFi;
    }

    public Boolean getSmokingDriver() {
        return smokingDriver;
    }

    public void setSmokingDriver(Boolean smokingDriver) {
        this.smokingDriver = smokingDriver;
    }

    public Boolean getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(Boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
