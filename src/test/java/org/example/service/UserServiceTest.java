package org.example.service;

import org.example.dao.UserDAO;
import org.example.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private  UserService userService;
    private UserDAO userDAOMock;

    @BeforeEach
    void setupMock(){
        userDAOMock = Mockito.mock(UserDAO.class);
        userService = new UserService(userDAOMock);
    }

    @Test
    void testCreateUser(){
        User user = userService.createUser("roma@gmail.com", "roma", 26);

        assertEquals("roma", user.getName());
        assertEquals("roma@gmail.com", user.getEmail());
        verify(userDAOMock, times(1)).save(user);
    }

    @Test
    void testGetAllUsers(){
        when(userDAOMock.getAll()).thenReturn(List.of(new User("roma@gmail.com", "roma", 26),
                                                      new User("nnasstt@gmail.com", "nastya", 24)));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertEquals("roma", users.get(0).getName());
    }

    @Test
    void testCreateUserWithNegativeAge(){

        Exception exception = assertThrows(Exception.class, () -> userService.createUser("roma@", "roma", -26));
        assertEquals("Возраст не может быть отрицательным", exception.getMessage());
    }
}
