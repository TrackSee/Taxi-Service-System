package ua.com.tracksee.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana
 */
public class FavoritePlaceEntityPK implements Serializable {
    private String name;
    private Integer userId;

    public FavoritePlaceEntityPK() {
    }

    public FavoritePlaceEntityPK(String name, Integer userId) {
        this.name = name;
        this.userId = userId;
    }

    @Column(name = "name")
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "user_id")
    @Id
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritePlaceEntityPK that = (FavoritePlaceEntityPK) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userId);
    }
}
