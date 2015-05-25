package ua.com.tracksee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vividsolutions.jts.geom.LineString;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Taxi order data transfer object class.
 * Objects of this class are used for converting to and from JSON.
 *
 * @author Ruslan Gunavardana
 */
public class TaxiOrderDTO {
    private long trackingNumber;
    private RouteDTO[] routes;
    private String service;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:00 MM/dd/yyyy")
    private Date orderDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:00 MM/dd/yyyy")
    private Date arrivalDate;
    private BigDecimal price;
    private String status;

    /* contact info */
    private String email;
    private String phoneNumber;
    private String description;

    /* additional info */
    private String carCategory;
    private String wayOfPayment;
    private String driverSex;
    private String musicStyle;
    private Boolean animalTransportation;
    private Boolean freeWiFi;
    private Boolean nonSmokingDriver;
    private Boolean airConditioner;

    public TaxiOrderDTO() {
    }

    public TaxiOrderDTO(List<TaxiOrderItemEntity> items) {
        if (items != null) {
            routes = new RouteDTO[items.size()];
            for (int i = 0; i < items.size(); i++) {
                routes[i] = new RouteDTO(items.get(i).getPath(), items.get(i).getOrderedQuantity());
            }
        }
    }

    public TaxiOrderDTO(TaxiOrderEntity orderEntity) {
        this(orderEntity.getItemList());
        this.trackingNumber = orderEntity.getTrackingNumber();
        this.service = orderEntity.getService().getName();
        this.orderDate = orderEntity.getOrderedDate();
        this.arrivalDate = orderEntity.getArriveDate();
        this.price = orderEntity.getPrice();
        this.status = orderEntity.getStatus().getName();

        this.description = orderEntity.getDescription();

        this.carCategory = orderEntity.getCarCategory().toString();
        this.wayOfPayment = orderEntity.getWayOfPayment().toString();
        this.driverSex = orderEntity.getDriverSex().toString();
        this.musicStyle = orderEntity.getMusicStyle().toString();
        this.animalTransportation = orderEntity.getAnimalTransportation();
        this.freeWiFi = orderEntity.getFreeWifi();
        this.nonSmokingDriver = orderEntity.getNonSmokingDriver();
        this.airConditioner = orderEntity.getAirConditioner();
    }



    public long getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(long trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public RouteDTO[] getRoutes() {
        return routes;
    }

    public void setRoutes(RouteDTO[] routes) {
        this.routes = routes;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Boolean getNonSmokingDriver() {
        return nonSmokingDriver;
    }

    public void setNonSmokingDriver(Boolean smokingDriver) {
        this.nonSmokingDriver = smokingDriver;
    }

    public Boolean getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(Boolean airConditioner) {
        this.airConditioner = airConditioner;
    }
}
