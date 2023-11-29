package com.petsociety.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.petsociety.backend.entity.UserEntity;
import com.petsociety.backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    UserRepository srepo;

    // C - CREATE OR INSERT STUDENT RECORD IN tblStudent
    public UserEntity insertUser(UserEntity user) {
        return srepo.save(user);
    }

    // R - READ ALL RECORDS IN tlbStudebt
    public List<UserEntity> getAllUsers() {
        return srepo.findAll();
    }

    // U - UPDATE A RECORD IN tlbStudent
    @SuppressWarnings("finally")
    public UserEntity updateUser(int userID, UserEntity newUserDetails) {
        UserEntity user = new UserEntity();
        try {
            // Search the id number of the student will be updated
            user = srepo.findById(userID).get();

            user.setFname(newUserDetails.getFname());
            user.setLname(newUserDetails.getLname());
            user.setEmail(newUserDetails.getEmail());
            user.setGender(newUserDetails.getGender());
            user.setAddress(newUserDetails.getAddress());
            user.setContact(newUserDetails.getContact());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Student " + userID + "does not exist!");
        } finally {
            return srepo.save(user);
        }
    }

    // D - DELETE A RECORD IN tlbStudent
    public String deleteUser(int userID) {
        String msg = "";

        if (srepo.findById(userID) != null) {
            srepo.deleteById(userID);
            msg = "User " + userID + " is succesfully deleted!";
        } else
            msg = "User " + userID + " does not exist!";
        return msg;
    }

    public UserEntity authenticateUser(String email, String password) {
        return srepo.findByEmailAndPassword(email, password);
    }

    // Find a user by ID
    public Optional<UserEntity> findUserById(int userID) {
        return srepo.findById(userID);
    }

    //get specific
     public UserEntity getUserID(int userID) {
        return srepo.findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}