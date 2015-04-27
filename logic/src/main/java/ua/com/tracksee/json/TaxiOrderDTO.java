package ua.com.tracksee.json;

import org.joda.time.LocalDateTime;

/**
 * Taxi order data transfer object class.
 * Objects of this class are used for converting to and from JSON.
 *
 * @author Ruslan Gunavardana
 */
public class TaxiOrderDTO {
    private LocationDTO[][] routes;
    private String service;
    private LocalDateTime orderDate;

    /* contact info */
    private String email;
    private String phoneNumber;

    private String description;
    private String serviceInfo;

    /* additional info */
    private String carCategory;
    private String wayOfPayment;
    private String driverSex;
    private String musicStyle;
    private Boolean animalTransportation;
    private Boolean freeWiFi;
    private Boolean smokingDriver;
    private Boolean airConditioner;

    /**
     * An array of different routes.
     *
     * @return
     */
    public LocationDTO[][] getRoutes() {
        return routes;
    }

    public void setRoutes(LocationDTO[][] routes) {
        this.routes = routes;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
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
}
