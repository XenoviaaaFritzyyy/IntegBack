package com.petsociety.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petsociety.backend.entity.UserEntity;
import com.petsociety.backend.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService sserv;

    // C - Create a Student Record
    @PostMapping("/insertUser")
    public UserEntity insertUser(@RequestBody UserEntity user) {
        return sserv.insertUser(user);
    }

    // R - Read a Student Record
    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return sserv.getAllUsers();
    }

    // U - Update a Student Record
    @PutMapping("/updateUser")
    public UserEntity updateUser(@RequestParam int userID, @RequestBody UserEntity newUserDetails) {
        return sserv.updateUser(userID, newUserDetails);
    }

    // D - Delete a Student Record
    @DeleteMapping("/deleteUser/{userID}")
    public String deleteStudent(@PathVariable int userID) {
        return sserv.deleteUser(userID);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity user) {
        String email = user.getEmail();
        String password = user.getPassword();

        if (sserv.authenticateUser(email, password)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
