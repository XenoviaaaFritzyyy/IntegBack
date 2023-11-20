package com.petsociety.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbluser")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name = "firstname")
    private String fname;

    @Column(name = "lastname")
    private String lname;

    private String password;
    private String email;
    private String gender;
    private String address;
    private String contact;
    private String role;

    public UserEntity() {
        super();
    }

    public UserEntity(int userID, String fname, String lname, String password, String email, String gender,
            String address, String contact, String role) {
        super();
        this.userID = userID;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.role = role;
    }

    private static boolean firstEntryPersisted = false;

    @PrePersist
    public void prePersist() {
        // Set the role to "admin" for the first entry
        if (!firstEntryPersisted) {
            this.role = "admin";
            firstEntryPersisted = true;
        } else {
            // Set the role to "user" for subsequent entries
            this.role = "user";
        }
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}