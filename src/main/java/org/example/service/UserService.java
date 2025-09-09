package org.example.service;

import org.example.dao.UserDAO;
import org.example.models.User;

import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        return userDAO.getAll();
    }

    public User getUserById(int userId){
        return userDAO.getT(userId);
    }

    public User createUser(String email, String name, int age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }

        User user = new User(email, name, age);
        userDAO.save(user);
        return user;
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void deleteUser(User user) {
        userDAO.delete(user);
    }


}
