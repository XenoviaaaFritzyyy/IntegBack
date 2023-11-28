package com.petsociety.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    // C - Create a User Record
    @PostMapping("/insertUser")
    public UserEntity insertUser(@RequestBody UserEntity user) {
        return sserv.insertUser(user);
    }

    // R - Read a User Record
    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return sserv.getAllUsers();
    }

    // U - Update a User Record
    @PutMapping("/updateUser")
    public UserEntity updateUser(@RequestParam int userID, @RequestBody UserEntity newUserDetails) {
        return sserv.updateUser(userID, newUserDetails);
    }

    // D - Delete a User Record
    @DeleteMapping("/deleteUser/{userID}")
    public String deleteStudent(@PathVariable int userID) {
        return sserv.deleteUser(userID);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity user) {
        String email = user.getEmail();
        String password = user.getPassword();

        UserEntity authenticatedUser = sserv.authenticateUser(email, password);

        if (authenticatedUser != null) {
            // Assuming UserEntity has a getUserId() method to get the userID
            int userID = authenticatedUser.getUserID();
            // You can include additional user information in the response if needed
            Map<String, Object> response = new HashMap<>();
            response.put("userId", userID);
            response.put("message", "Authentication successful");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        Optional<UserEntity> userOptional = sserv.findUserById(userId);
    
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found with ID: " + userId, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/info/{userID}")
    public UserEntity getuserbyID(@PathVariable int userID) {
        return sserv.getUserID(userID);
    }
}
