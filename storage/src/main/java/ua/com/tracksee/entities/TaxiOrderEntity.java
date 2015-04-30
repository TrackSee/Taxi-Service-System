package ua.com.tracksee.entities;

import org.postgresql.util.PGmoney;
import ua.com.tracksee.enumartion.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;

/**
 * @author Ruslan Gunavardana
 */
@Entity
@Table(name = "taxi_order", schema = "public", catalog = "tracksee")
public class TaxiOrderEntity {
    private Long trackingNumber;
    private OrderStatus status;
    private BigDecimal price;
    private Service service;
    private String description;
    private CarCategory carCategory;
    private WayOfPayment wayOfPayment;
    private Sex driverSex;
    private MusicStyle musicStyle;
    private Boolean animalTransportation;
    private Boolean freeWifi;
    private Boolean nonSmokingDriver;
    private Boolean airConditioner;
    private String comment;
    private Integer userId;
    private Timestamp orderedDate = new Timestamp(System.currentTimeMillis());
    private Timestamp arriveDate;
    private Timestamp endDate;

    public Timestamp getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Timestamp arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public TaxiOrderEntity() {

    }

    public TaxiOrderEntity(OrderStatus status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(generator = "orderSeq")
    @SequenceGenerator(name = "orderSeq", sequenceName = "taxi_order_tracking_number_seq",
            allocationSize = 1, initialValue= 1)
    @Column(name = "tracking_number")
    public Long getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Long trackingNumber) {
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
    @Column(name = "status", nullable = false)
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
    @Enumerated(STRING)
    @Column(name = "music_style")
    public MusicStyle getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(MusicStyle musicStyle) {
        this.musicStyle = musicStyle;
    }

    @Basic
    @Column(name = "animal_transportation")
    public Boolean getAnimalTransportation() {
        return animalTransportation;
    }

    public void setAnimalTransportation(Boolean animalTransportation) {
        this.animalTransportation = animalTransportation;
    }

    @Basic
    @Column(name = "free_wifi")
    public Boolean getFreeWifi() {
        return freeWifi;
    }

    public void setFreeWifi(Boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    @Basic
    @Column(name = "smoking_driver")
    public Boolean getNonSmokingDriver() {
        return nonSmokingDriver;
    }

    public void setNonSmokingDriver(Boolean smokingDriver) {
        this.nonSmokingDriver = smokingDriver;
    }

    @Basic
    @Column(name = "air_conditioner")
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
    @Column(name="ordered_date")
    public Timestamp getOrderedDate(){
        return orderedDate;
    }
    public void setOrderedDate(Timestamp orderedDate){
        this.orderedDate = orderedDate;
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
                Objects.equals(comment, that.comment) &&
                Objects.equals(orderedDate, that.orderedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackingNumber, status, price, service, carCategory, wayOfPayment, driverSex, musicStyle, comment, orderedDate);
    }
}
