package com.netcracker.bootcamp.tracksee.util.config;

import entity.UserEntity;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by byte on 4/18/15.
 *
 */
@Singleton(name = "ConfigManagerEJB")
@Startup
public class ConfigManagerBean {

    @PersistenceContext(unitName = "DataSourceTS")
    EntityManager em;

    private ResourceBundle resourceBundle;

    public ConfigManagerBean() {
        resourceBundle = ResourceBundle.getBundle("config");
    }

    /**
     * method which return config from rescorces/config.properties
     * @param key search key
     * @return some config
     */
    @Lock(LockType.READ)
    public String getString(String key){
        return resourceBundle.getString(key);
    }

    @PostConstruct
    public void testMethod(){
       UserEntity userEntity = em.find(UserEntity.class, "byte@gmail.com");
        System.out.println("mail = " + userEntity.getEmail() + " password = " + userEntity.getPassword() + " admin_role = " + userEntity.getAdmin());



        List<UserEntity> result = em.createNamedQuery("SELECT OBJECT (u) FROM User u", UserEntity.class).getResultList();

        for (UserEntity entity:result){
            System.out.println(entity.getEmail() + " " + entity.getPassword() + " " + entity.getPhone());
        }
    }
}
