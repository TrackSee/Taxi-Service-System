package ua.com.tracksee.entities;

import org.postgresql.geometric.PGpoint;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana
 */
@Entity
@Table(name = "address", schema = "public", catalog = "tracksee")
@IdClass(AddressEntityPK.class)
public class AddressEntity {
    private String name;
    private Integer userId;
    private PGpoint location;

    public AddressEntity() {
    }

    public AddressEntity(String name, Integer userId) {
        this.name = name;
        this.userId = userId;
    }

    public AddressEntity(String name, Integer userId, PGpoint location) {
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
    @Column(name = "location")
    public PGpoint getLocation() {
        return location;
    }

    public void setLocation(PGpoint location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userId, location);
    }
}
