package ua.com.tracksee.dao.implementation;

import ua.com.tracksee.dao.ConfigDAO;
import ua.com.tracksee.entities.ConfigEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class ConfigDAOBean implements ConfigDAO {
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public ConfigEntity getConfig(String key) {
        return entityManager.find(ConfigEntity.class, key);
    }
}
