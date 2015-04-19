package com.netcracker.bootcamp.tracksee.entities;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ruslan Gunavardana.
 */
public class EntityManagerTest {

    @Test
    public void AddressEntityTest() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePU");
        EntityManager entityManager = factory.createEntityManager();
        ServiceUserEntity entity = entityManager.find(ServiceUserEntity.class, 1);
        System.out.println(entity.getEmail());
    }
}
