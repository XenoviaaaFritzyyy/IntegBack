package com.petsociety.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petsociety.backend.entity.ForumEntity;
import com.petsociety.backend.service.ForumService;

@RestController
@RequestMapping("/gallery")
@CrossOrigin(origins = "http://localhost:3000")
public class ForumController {

    @Autowired
    ForumService forumService;

    // C - Create a Gallery Record
    @PostMapping("/insertForum")
    public ForumEntity insertForum(@RequestBody ForumEntity forum) {
        return forumService.insertForum(forum);
    }

    // R - Read all Gallery Records
    @GetMapping("/getAllForums")
    public List<ForumEntity> getAllForums() {
        return forumService.getAllForums();
    }

    // U - Update a Gallery Record
    @PutMapping("/updateForum")
    public ForumEntity updateForum(@RequestParam int forumID, @RequestBody ForumEntity newForumDetails) {
        return forumService.updateForum(forumID, newForumDetails);
    }

    // D - Delete a Gallery Record
    @PutMapping("/deleteForum/{forumID}")
    public String deleteForum(@PathVariable int forumID) {
        return forumService.deleteForum(forumID);
    }

    @GetMapping("/info/{forumID}")
    public ForumEntity getForumById(@PathVariable int forumID) {
        return forumService.getForumID(forumID);
    }
}
