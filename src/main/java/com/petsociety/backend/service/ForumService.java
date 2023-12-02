package com.petsociety.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petsociety.backend.entity.ForumEntity;
import com.petsociety.backend.repository.ForumRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ForumService {
    @Autowired
    ForumRepository srepo;

    // C - CREATE OR INSERT STUDENT RECORD IN tblStudent
    public ForumEntity insertForum(ForumEntity entry) {
        return srepo.save(entry);
    }

    // R - READ ALL RECORDS IN tlbStudebt
    public List<ForumEntity> getAllForums() {
        return srepo.findAll();
    }

    // Read a Single Entry by DictionaryID
    public ForumEntity getForumById(int forumID) {
        return srepo.findById(forumID)
                .orElseThrow(() -> new NoSuchElementException("Entry " + forumID + " does not exist!"));
    }

    // U - UPDATE A RECORD IN tlbStudent
    @SuppressWarnings("finally")
    public ForumEntity updateForum(int forumID, ForumEntity newEntryDetails) {
        ForumEntity entry = new ForumEntity();
        try {
            // Search the id number of the student will be updated
            entry = srepo.findById(forumID).get();
            entry.setPost(newEntryDetails.getPost());
            entry.setReply(newEntryDetails.getReply());

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Entry " + forumID + "does not exist!");
        } finally {
            return srepo.save(entry);
        }
    }

    // D - DELETE A RECORD IN tlbStudent
    public String deleteForum(int forumID) {
        String msg = "";

        Optional<ForumEntity> optionalForum = srepo.findById(forumID);

        if (optionalForum.isPresent()) {
            ForumEntity entry = optionalForum.get();
            entry.setIsDeleted(true); // Update the isDeleted field
            srepo.save(entry); // Save the updated entity back to the database
            msg = "Entry " + forumID + " is successfully 'deleted'!";
        } else {
            msg = "Entry " + forumID + " does not exist!";
        }
        return msg;
    }

    // Get Gallery ID
    public ForumEntity getForumID(int forumID) {
        return srepo.findById(forumID)
                .orElseThrow(() -> new EntityNotFoundException("Forum not found"));
    }
}
