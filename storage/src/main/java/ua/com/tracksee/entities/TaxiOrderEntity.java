package ua.com.tracksee.entities;

import ua.com.tracksee.enumartion.CarCategory;
import ua.com.tracksee.enumartion.Sex;
import ua.com.tracksee.enumartion.WayOfPayment;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

/**
 * @author Ruslan Gunavardana
 */
@Entity
@Table(name = "taxi_order", schema = "public", catalog = "tracksee")
public class TaxiOrderEntity implements Serializable{
    private Integer trackingNumber;
    private String status;
    private BigDecimal price;
    private String service;
    private String description;
    private CarCategory carCategory;
    private WayOfPayment wayOfPayment;
    private Sex driverSex;
    private String musicStyle;
    private Boolean animalTransportation = false;
    private Boolean freeWifi = false;
    private Boolean smokingDriver = false;
    private Boolean airConditioner = false;
    private String comment;
    private Integer userId;
    private Timestamp carArriveTime;
    private Set<TaxiOrderItemEntity> taxiOrderItemEntitySet = new HashSet<TaxiOrderItemEntity>(0);


    @Id
    @GeneratedValue(generator = "orderSeq")
    @SequenceGenerator(name = "orderSeq", sequenceName = "taxi_order_tracking_number_seq",
            allocationSize = 1, initialValue= 1)
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
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
    @Column(name = "service")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Basic
    @Column(name = "animal_transportation", nullable = false)
    public Boolean getAnimalTransportation() {
        return animalTransportation;
    }

    public void setAnimalTransportation(Boolean animalTransportation) {
        this.animalTransportation = animalTransportation;
    }

    @Basic
    @Column(name = "free_wifi", nullable = false)
    public Boolean getFreeWifi() {
        return freeWifi;
    }

    public void setFreeWifi(Boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    @Basic
    @Column(name = "smoking_driver", nullable = false)
    public Boolean getSmokingDriver() {
        return smokingDriver;
    }

    public void setSmokingDriver(Boolean smokingDriver) {
        this.smokingDriver = smokingDriver;
    }

    @Basic
    @Column(name = "air_conditioner", nullable = false)
    public Boolean getAirConditioner() {
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

    @Basic
    @Column(name = "ordered_date")
    public Timestamp getCarArriveTime() {
        return carArriveTime;
    }

    public void setCarArriveTime(Timestamp carArriveTime) {
        this.carArriveTime = carArriveTime;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taxiOrder")
    public Set<TaxiOrderItemEntity> getTaxiOrderItemEntitySet() {
        return taxiOrderItemEntitySet;
    }

    public void setTaxiOrderItemEntitySet(Set<TaxiOrderItemEntity> taxiOrderItemEntitySet) {
        this.taxiOrderItemEntitySet = taxiOrderItemEntitySet;
    }


}
