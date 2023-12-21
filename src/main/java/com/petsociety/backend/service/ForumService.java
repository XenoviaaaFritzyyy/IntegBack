package com.petsociety.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petsociety.backend.entity.ForumEntity;
import com.petsociety.backend.entity.UserEntity;
import com.petsociety.backend.repository.ForumRepository;
import com.petsociety.backend.repository.UserRepository;


@Service
public class ForumService {
    @Autowired
    ForumRepository srepo;

	@Autowired
	UserRepository userRepository; 

	public ForumEntity insertEntry(ForumEntity entry, int userId) {
		UserEntity user = userRepository.findById(userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            entry.setUser(user);
            entry.setTimestamp(LocalDateTime.now());

			return srepo.save(entry);
		}
		

    // R - READ ALL RECORDS IN tlbStudebt
    public List<ForumEntity> getAllForums() {
        return srepo.findAll();
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
}
