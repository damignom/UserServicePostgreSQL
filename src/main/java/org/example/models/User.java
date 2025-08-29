package org.example.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;

    private String email;

    private int age;

    private LocalDateTime created_at = LocalDateTime.now();

    public User(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.created_at = LocalDateTime.now();
    }

    public User() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String toString(){
        return getId() + " " + getName() + " " + getAge() + " " + getEmail() + " " + getCreated_at();
    }
}
