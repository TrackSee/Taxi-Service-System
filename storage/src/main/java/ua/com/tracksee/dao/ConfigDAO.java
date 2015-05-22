package ua.com.tracksee.dao;

import ua.com.tracksee.entities.ConfigEntity;

/**
 * @author Ruslan Gunavardana
 */
public interface ConfigDAO {
    ConfigEntity getConfig(String key);
    void saveConfig(ConfigEntity config);
}
