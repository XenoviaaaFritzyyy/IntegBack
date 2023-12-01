package com.petsociety.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petsociety.backend.entity.NotificationEntity;
import com.petsociety.backend.entity.UserEntity;
import com.petsociety.backend.service.NotificationService;
import com.petsociety.backend.service.UserService;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
public ResponseEntity<String> createNotification(@RequestBody Map<String, Object> requestBody) {
    String userIdString = (String) requestBody.get("userId");
    int userId = Integer.parseInt(userIdString);

    String message = (String) requestBody.get("message");

    // Retrieve user details from the UserService
    UserEntity user = userService.findUserById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));


    LocalDateTime timestamp = LocalDateTime.now();
    
    // Create a notification
    notificationService.createNotification(user, message, timestamp);

    return ResponseEntity.ok("Notification created successfully");
}





    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationEntity>> getUserNotifications(@PathVariable int userId) {
        // Retrieve user details from the UserService
        UserEntity user = userService.findUserById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Get notifications for the user
        List<NotificationEntity> notifications = notificationService.getNotifications(user);
        return ResponseEntity.ok(notifications);
    }

    // Additional controller methods as needed
}
