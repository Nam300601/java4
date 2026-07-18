package com.lab1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    
    @Id
    @Column(name = "Id")
    private String id;
    
    @Column(name = "Password")
    private String password;
    
    @Column(name = "Fullname")
    private String fullname;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "Admin")
    private Boolean admin = false;

    // Constructors
    public User() {}

    public User(String id, String password, String fullname, String email, Boolean admin) {
        this.id = id;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.admin = admin;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Boolean getAdmin() { return admin; }
    public void setAdmin(Boolean admin) { this.admin = admin; }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", fullname=" + fullname + ", email=" + email + ", admin=" + admin + "]";
    }
}