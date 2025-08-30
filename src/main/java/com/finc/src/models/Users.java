package com.finc.src.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="username",nullable = false, unique = true)
    private String username;

    @Column (name="name",nullable = false)
    private String name;

    @Column (name="email",nullable = false, unique = true)
    private String email;

    @Column(name="password",nullable = false)
    private String password;

    //Getters & Setters
    
    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users [id=" + id + ", username=" + username + ", name=" + name + ", email=" + email + ", password="
                + password + "]";
    }    
    
}
