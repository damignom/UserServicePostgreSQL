package org.example.dtos;

import java.time.LocalDateTime;

public class UserDTO {

    private String name;
    private String email;
    private int age;

    public UserDTO(int id, String name, String email, int age, LocalDateTime createdAt) {
        this.email = email;
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
