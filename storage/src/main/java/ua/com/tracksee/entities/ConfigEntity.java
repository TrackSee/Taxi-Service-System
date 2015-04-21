package ua.com.tracksee.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana
 */
@Entity
@Table(name = "config", schema = "public", catalog = "tracksee")
public class ConfigEntity {
    private Integer configId;
    private Float punishmentPersent;

    @Id
    @Column(name = "config_id")
    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    @Basic
    @Column(name = "punishment_persent")
    public Float getPunishmentPersent() {
        return punishmentPersent;
    }

    public void setPunishmentPersent(Float punishmentPersent) {
        this.punishmentPersent = punishmentPersent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigEntity that = (ConfigEntity) o;
        return Objects.equals(configId, that.configId) &&
                Objects.equals(punishmentPersent, that.punishmentPersent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configId, punishmentPersent);
    }
}
