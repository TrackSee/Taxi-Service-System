package ua.com.tracksee.entities;

import ua.com.tracksee.enumartion.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;

/**
 * @author Ruslan Gunavardana
 */
@Entity
@Table(name = "taxi_order", schema = "public", catalog = "tracksee")
public class TaxiOrderEntity {
    private Integer trackingNumber;
    private OrderStatus status;
    private BigDecimal price;
    private Service service;
    private CarCategory carCategory;
    private WayOfPayment wayOfPayment;
    private Sex driverSex;
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

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Enumerated(STRING)
    @Column(name = "status")
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Enumerated(STRING)
    @Column(name = "service")
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Basic
    @Enumerated(STRING)
    @Column(name = "car_category")
    public CarCategory getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(CarCategory carCategory) {
        this.carCategory = carCategory;
    }

    @Basic
    @Enumerated(STRING)
    @Column(name = "way_of_payment")
    public WayOfPayment getWayOfPayment() {
        return wayOfPayment;
    }

    public void setWayOfPayment(WayOfPayment wayOfPayment) {
        this.wayOfPayment = wayOfPayment;
    }

    @Basic
    @Enumerated(STRING)
    @Column(name = "driver_sex")
    public Sex getDriverSex() {
        return driverSex;
    }

    public void setDriverSex(Sex driverSex) {
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
