package org.example.dao;

import org.example.models.User;
import org.example.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoTest {

    private UserDAO userDAO;

    private static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17.0")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @BeforeAll
    void setupTestContainer()
    {
        postgres.start();

        System.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        System.setProperty("hibernate.connection.username", postgres.getUsername());
        System.setProperty("hibernate.connection.password", postgres.getPassword());

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        userDAO = new UserDAO();
    }

    @AfterAll
    void teardownAll() {
        postgres.stop();
    }

    @Test
    void testAddAndSaveUser()
    {
        User user = new User("Alesya@gmail.com", "Ales", 27);
        userDAO.save(user);

        User fetchedUser = userDAO.getT(user.getId());
        assertNotNull(fetchedUser);
        assertEquals("Ales", fetchedUser.getName());

    }

    @Test
    void testGetAllUsers(){

        userDAO.save(new User("roma@gmail.com", "roma", 26));
        userDAO.save(new User("nnaaasstt@gmauil.com", "nastya", 24));

        List<User> users = userDAO.getAll();

        assertTrue(users.size() >= 2);
    }

    @Test
    void testGetAndUpdateUser()
    {
        User user = new User("artem@gmail.com", "artem", 29);
        userDAO.save(user);

        User userForUpdate = userDAO.getT(user.getId());

        assertNotNull(userForUpdate);
        assertEquals("artem", userForUpdate.getName());


        userForUpdate.setName("Dasha");

        userDAO.update(userForUpdate);

        User userUpdated = userDAO.getT(userForUpdate.getId());

        assertNotNull(userUpdated);
        assertEquals("Dasha", userUpdated.getName());

    }

    @Test
    void testRemoveUsers(){
        userDAO.save(new  User("kira@gmail.com", "kira", 27));
        userDAO.save(new User("alesya@gmail.com", "ales", 27));

        for (User user : userDAO.getAll()){
            userDAO.delete(user);
        }

        List<User> users  = userDAO.getAll();

        assertEquals(0, users.size());

    }






}
