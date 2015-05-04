package ua.com.tracksee.entities;

import org.postgresql.util.PGobject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ruslan Gunavardana
 */
@Entity
@Table(name = "taxi_order_item", schema = "public", catalog = "tracksee")
//@IdClass(TaxiOrderItemEntityPK.class)
public class TaxiOrderItemEntity extends PGobject implements Serializable{
    private Integer taxiItemId;
//    private PGpath path;
    //private Integer trackingNumber;
//    private Integer driver_id;
    private Integer orderedQuantity;
    private ServiceUserEntity driver;
    private TaxiOrderEntity taxiOrder;

    @Id
   // @GeneratedValue
    @Column(name = "taxi_item_id")
    public Integer getTaxiItemId() {
        return taxiItemId;
    }

    public void setTaxiItemId(Integer taxiItemId) {
        this.taxiItemId = taxiItemId;
    }

//    @Basic
//    @Column(name = "path")
//    @Type(type = "ua.com.tracksee.usertype.PathUserType")
//    public PGpath getPath() {
//        return path;
//    }
//
//    public void setPath(PGpath path) {
//        this.path = path;
//    }

//    @Basic
//    @Column(name = "driver_id")
//    public Integer getDriver_id() {
//        return driver_id;
//    }
//
//    public void setDriver_id(Integer driver_id) {
//        this.driver_id = driver_id;
//    }

//    @Id
//    @Column(name = "tracking_numer")
//    public Integer getTrackingNumber() {
//        return trackingNumber;
//    }
//
//    public void setTrackingNumber(Integer tracking_number) {
//        this.trackingNumber = tracking_number;
//    }

    @Basic
    @Column(name = "ordered_quantity")
    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TaxiOrderItemEntity that = (TaxiOrderItemEntity) o;
//        return Objects.equals(taxiItemId, that.taxiItemId) &&
//                Objects.equals(path, that.path);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(taxiItemId, path);
//    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "user_id")
    public ServiceUserEntity getDriver() {
        return driver;
    }

    public void setDriver(ServiceUserEntity serviceUserByDriverId) {
        this.driver = serviceUserByDriverId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tracking_numer", referencedColumnName = "tracking_number")
    public TaxiOrderEntity getTaxiOrder() {
        return taxiOrder;
    }

    public void setTaxiOrder(TaxiOrderEntity taxiOrderByTrackingNumer) {
        this.taxiOrder = taxiOrderByTrackingNumer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxiOrderItemEntity)) return false;

        TaxiOrderItemEntity that = (TaxiOrderItemEntity) o;

        if (driver != null ? !driver.equals(that.driver) : that.driver != null) return false;
        if (orderedQuantity != null ? !orderedQuantity.equals(that.orderedQuantity) : that.orderedQuantity != null)
            return false;
        if (taxiItemId != null ? !taxiItemId.equals(that.taxiItemId) : that.taxiItemId != null) return false;
        if (taxiOrder != null ? !taxiOrder.equals(that.taxiOrder) : that.taxiOrder != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taxiItemId != null ? taxiItemId.hashCode() : 0;
        result = 31 * result + (orderedQuantity != null ? orderedQuantity.hashCode() : 0);
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (taxiOrder != null ? taxiOrder.hashCode() : 0);
        return result;
    }
}
