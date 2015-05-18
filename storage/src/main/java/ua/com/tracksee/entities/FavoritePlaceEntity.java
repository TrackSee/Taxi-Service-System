package ua.com.tracksee.entities;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana
 */
@Entity
@Table(name = "Favorite_Place", schema = "public", catalog = "tracksee")
@IdClass(FavoritePlaceEntityPK.class)
public class FavoritePlaceEntity {
    private String name;
    private Integer userId;
    private Point location;

    public FavoritePlaceEntity() {
    }

    public FavoritePlaceEntity(String name, Integer userId) {
        this.name = name;
        this.userId = userId;
    }

    public FavoritePlaceEntity(String name, Integer userId, Point location) {
        this.name = name;
        this.userId = userId;
        this.location = location;
    }

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Type(type="org.hibernate.spatial.GeometryType")
    @Column(name = "location", columnDefinition = "geometry")
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritePlaceEntity that = (FavoritePlaceEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userId, location);
    }
}
