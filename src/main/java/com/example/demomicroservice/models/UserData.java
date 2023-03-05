package com.example.demomicroservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserData {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;
    @Id
    @Column(updatable = false, nullable = false)
    private String username;
    private String emailid;
    private String password;

    public UserData() {

    }

    public UserData(String username, String email, String password) {
        this.username = username;
        this.emailid = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}
