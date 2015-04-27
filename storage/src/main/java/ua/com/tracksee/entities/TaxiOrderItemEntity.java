package ua.com.tracksee.entities;

import org.postgresql.geometric.PGpath;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana
 */
@Entity
@Table(name = "taxi_order_item", schema = "public", catalog = "tracksee")
public class TaxiOrderItemEntity {
    private Integer taxiItemId;
    private PGpath path;
    private BigDecimal orderedQuantity;
    private ServiceUserEntity driver;
    private TaxiOrderEntity taxiOrder;

    @Id
    @GeneratedValue
    @Column(name = "taxi_item_id")
    public Integer getTaxiItemId() {
        return taxiItemId;
    }

    public void setTaxiItemId(Integer taxiItemId) {
        this.taxiItemId = taxiItemId;
    }

    @Basic
    @Column(name = "path")
    public PGpath getPath() {
        return path;
    }

    public void setPath(PGpath path) {
        this.path = path;
    }

    @Basic
    @Column(name = "ordered_quantity")
    public BigDecimal getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(BigDecimal orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiOrderItemEntity that = (TaxiOrderItemEntity) o;
        return Objects.equals(taxiItemId, that.taxiItemId) &&
                Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxiItemId, path);
    }

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "user_id")
    public ServiceUserEntity getDriver() {
        return driver;
    }

    public void setDriver(ServiceUserEntity serviceUserByDriverId) {
        this.driver = serviceUserByDriverId;
    }

    @ManyToOne
    @JoinColumn(name = "tracking_numer", referencedColumnName = "tracking_number")
    public TaxiOrderEntity getTaxiOrder() {
        return taxiOrder;
    }

    public void setTaxiOrder(TaxiOrderEntity taxiOrderByTrackingNumer) {
        this.taxiOrder = taxiOrderByTrackingNumer;
    }
}
