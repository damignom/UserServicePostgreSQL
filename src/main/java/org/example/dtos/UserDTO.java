package org.example.dtos;

import java.time.LocalDateTime;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private int age;

    public UserDTO(long id, String name, String email, int age, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.name = name;
    }

    public Long getId() {
        return id;
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
