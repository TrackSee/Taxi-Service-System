package ua.com.tracksee.entities.reports;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by root on 18.05.15.
 */

@Entity
@Table(name="Driver_Report", schema = "public", catalog = "tracksee")
public class DriverReportEntity {
    private BigInteger id;
    private String sex;
    private Integer orderCount;

    public DriverReportEntity(){}

    @Basic
    @JsonProperty("sex")
    @Column(name = "driver_sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @JsonProperty("order_count")
    @Column(name = "order_count")
    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    //Max of tracking numbers is id
    @Id
    @JsonIgnore
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}