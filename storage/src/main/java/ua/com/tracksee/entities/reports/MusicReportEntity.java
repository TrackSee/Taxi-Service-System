package ua.com.tracksee.entities.reports;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created on 19.05.2015
 *
 * @author Oleksandr Kozin
 */
@Entity
@Table(name="Music_Report", schema = "public", catalog = "tracksee")
public class MusicReportEntity {
    private BigInteger id;
    String style;
    private Integer orderCount;

    public MusicReportEntity() {
    }

    @Id
    @JsonIgnore
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Basic
    @JsonProperty("style")
    @Column(name = "music_style")
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Basic
    @JsonProperty("order_count")
    @Column(name = "music_count")
    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }
}
