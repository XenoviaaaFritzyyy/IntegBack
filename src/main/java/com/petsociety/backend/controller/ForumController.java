package com.petsociety.backend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.petsociety.backend.entity.ForumEntity;
import com.petsociety.backend.service.ForumService;

@RestController
@RequestMapping("/forum")
@CrossOrigin(origins = "http://localhost:3000")
public class ForumController {

    @Autowired
    ForumService forumService;

    // C - Create a Gallery Record
    @PostMapping("/insertForum")
    public ResponseEntity<ForumEntity> insertEntry(@RequestBody ForumEntity entry,
            @RequestParam("userId") int userId) {
        try {
            entry.setTimestamp(LocalDateTime.now());

            ForumEntity createdEntry = forumService.insertEntry(entry, userId);
            return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // R - Read all Gallery Records
    @GetMapping("/getAllForums")
    public List<ForumEntity> getAllForums() {
        return forumService.getAllForums();
    }

    // D - Delete a Gallery Record
    @PutMapping("/deleteForum/{forumID}")
    public String deleteForum(@PathVariable int forumID) {
        return forumService.deleteForum(forumID);
    }
}
