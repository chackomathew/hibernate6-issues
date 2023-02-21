package com.chackomathew.enumfqn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class UserTest {

    private static EntityManager entityManager = Persistence
            .createEntityManagerFactory("TestPU")
            .createEntityManager();


    @Test
    public void testQueryEnum() {
        User user = new User("firstName", "lastName", Status.ON_HOLD, false, false);

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        Query userQuery = entityManager.createQuery("select u from User u where u.status = ON_HOLD");
        User userFound = (User) userQuery.getSingleResult();

        assertEquals(user.getId(), userFound.getId());
    }
}