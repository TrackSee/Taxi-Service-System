package ua.com.tracksee.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana.
 */
@Entity
@Table(name = "taxi_order", schema = "public", catalog = "tracksee")
public class TaxiOrderEntity {
    private Integer trackingNumber;
    private String status;
    private Double price;
    private String service;
    private String carCategory;
    private String wayOfPayment;
    private String driverSex;
    private String musicStyle;
    private Boolean animalTransportation;
    private Boolean freeWifi;
    private Boolean smokingDriver;
    private Boolean airConditioner;
    private String comment;
    private Integer userId;

    @Id
    @Column(name = "tracking_number")
    public Integer getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Integer trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    /* дописано з бд, бо було відсутнє */
    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "service")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Basic
    @Column(name = "car_category")
    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    @Basic
    @Column(name = "way_of_payment")
    public String getWayOfPayment() {
        return wayOfPayment;
    }

    public void setWayOfPayment(String wayOfPayment) {
        this.wayOfPayment = wayOfPayment;
    }

    @Basic
    @Column(name = "driver_sex")
    public String getDriverSex() {
        return driverSex;
    }

    public void setDriverSex(String driverSex) {
        this.driverSex = driverSex;
    }

    @Basic
    @Column(name = "music_style")
    public String getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }

    public Boolean isAnimalTransportation() {
        return animalTransportation;
    }

    public void setAnimalTransportation(Boolean animalTransportation) {
        this.animalTransportation = animalTransportation;
    }

    public Boolean isFreeWifi() {
        return freeWifi;
    }

    public void setFreeWifi(Boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    public Boolean isSmokingDriver() {
        return smokingDriver;
    }

    public void setSmokingDriver(Boolean smokingDriver) {
        this.smokingDriver = smokingDriver;
    }

    public Boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(Boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiOrderEntity that = (TaxiOrderEntity) o;
        return Objects.equals(trackingNumber, that.trackingNumber) &&
                Objects.equals(status, that.status) &&
                Objects.equals(price, that.price) &&
                Objects.equals(service, that.service) &&
                Objects.equals(carCategory, that.carCategory) &&
                Objects.equals(wayOfPayment, that.wayOfPayment) &&
                Objects.equals(driverSex, that.driverSex) &&
                Objects.equals(musicStyle, that.musicStyle) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackingNumber, status, price, service, carCategory, wayOfPayment, driverSex, musicStyle, comment);
    }
}
