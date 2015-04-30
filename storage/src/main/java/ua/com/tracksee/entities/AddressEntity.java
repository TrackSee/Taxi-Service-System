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
    private ServiceUserEntity userId;
    private String stringRepresentation;
    private PGpoint location;

    public AddressEntity(String name, ServiceUserEntity userId, String stringRepresentation, PGpoint location) {
        this.name = name;
        this.userId = userId;
        this.stringRepresentation = stringRepresentation;
        this.location = location;
    }

    //TODO delete this constructor by Kozin O.
    public AddressEntity(String name, ServiceUserEntity userId, String stringRepresentation) {
        this.name = name;
        this.userId = userId;
        this.stringRepresentation = stringRepresentation;
    }

    public AddressEntity() {
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
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public ServiceUserEntity getUserId() {
        return userId;
    }

    public void setUserId(ServiceUserEntity userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "string_representation")
    public String getStringRepresentation() {
        return stringRepresentation;
    }

    public void setStringRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
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
                Objects.equals(stringRepresentation, that.stringRepresentation) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userId, stringRepresentation, location);
    }
}
