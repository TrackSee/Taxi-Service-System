package com.netcracker.bootcamp.tracksee.entities;

import org.postgresql.geometric.PGpath;

import javax.persistence.*;

/**
 * Created by Vadym_Akymov on 19.04.15.
 */
@Entity
@Table(name = "address", schema = "public", catalog = "taxi")
@IdClass(AddressEntityPK.class)
public class AddressEntity {
    private String name;
    private int userId;
    private String stringRepresentation;
    private PGpath location;
    private ServiceUserEntity serviceUserByUserId;

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
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
    public PGpath getLocation() {
        return location;
    }

    public void setLocation(PGpath location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

        if (userId != that.userId) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (stringRepresentation != null ? !stringRepresentation.equals(that.stringRepresentation) : that.stringRepresentation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + userId;
        result = 31 * result + (stringRepresentation != null ? stringRepresentation.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    public ServiceUserEntity getServiceUserByUserId() {
        return serviceUserByUserId;
    }

    public void setServiceUserByUserId(ServiceUserEntity serviceUserByUserId) {
        this.serviceUserByUserId = serviceUserByUserId;
    }
}
