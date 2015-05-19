package ua.com.tracksee.entities;

import com.vividsolutions.jts.geom.LineString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana
 */
@Entity
@Table(name = "taxi_order_item", schema = "public", catalog = "tracksee")
public class TaxiOrderItemEntity {
    private Long taxiItemId;
    private LineString path;
    private BigDecimal orderedQuantity;
    private UserEntity driver;
    private TaxiOrderEntity taxiOrder;

    public TaxiOrderItemEntity() {
    }

    public TaxiOrderItemEntity(LineString path, BigDecimal orderedQuantity, TaxiOrderEntity taxiOrder) {
        this.path = path;
        this.orderedQuantity = orderedQuantity;
        this.taxiOrder = taxiOrder;
    }

    @Id
    @GeneratedValue(generator = "orderItemSeq")
    @SequenceGenerator(name = "orderItemSeq", sequenceName = "taxi_order_item_taxi_item_id_seq",
            allocationSize = 1, initialValue= 1)
    @Column(name = "taxi_item_id" , columnDefinition = "int8")
    public Long getTaxiItemId() {
        return taxiItemId;
    }

    public void setTaxiItemId(Long taxiItemId) {
        this.taxiItemId = taxiItemId;
    }

    @Basic
    @Type(type="org.hibernate.spatial.GeometryType")
    @Column(name = "path", columnDefinition = "geometry")
    public LineString getPath() {
        return path;
    }

    public void setPath(LineString path) {
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

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "user_id")
    public UserEntity getDriver() {
        return driver;
    }

    public void setDriver(UserEntity serviceUserByDriverId) {
        this.driver = serviceUserByDriverId;
    }

    @ManyToOne
    @JoinColumn(name = "tracking_numer", referencedColumnName = "tracking_number",
            nullable = false, columnDefinition = "int8")
    public TaxiOrderEntity getTaxiOrder() {
        return taxiOrder;
    }

    public void setTaxiOrder(TaxiOrderEntity taxiOrderByTrackingNumer) {
        this.taxiOrder = taxiOrderByTrackingNumer;
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
}
