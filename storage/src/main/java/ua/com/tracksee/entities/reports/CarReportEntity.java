package ua.com.tracksee.entities.reports;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by Vadym Akymov on 18.05.15.
 */
@Entity
@Table(name="Car_Report", schema = "public", catalog = "tracksee")
public class CarReportEntity {
    private BigInteger id;
    private String carCategory;
    private Integer orderedCount;

    public CarReportEntity(){}

    @Id
    @JsonIgnore
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Basic
    @JsonProperty("car_category")
    @Column(name = "car_category")
    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    @Basic
    @JsonProperty("ordered_count")
    @Column(name = "ordered_count")
    public Integer getOrderedCount() {
        return orderedCount;
    }

    public void setOrderedCount(Integer orderedCount) {
        this.orderedCount = orderedCount;
    }
}
