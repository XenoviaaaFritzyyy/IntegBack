package com.petsociety.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petsociety.backend.entity.ApplicationEntity;
import com.petsociety.backend.entity.PetEntity;
import com.petsociety.backend.entity.UserEntity;
import com.petsociety.backend.repository.ApplicationRepository;
import com.petsociety.backend.repository.PetRepository;
import com.petsociety.backend.repository.UserRepository;


@Service
public class ApplicationService {
	@Autowired
	ApplicationRepository srepo;
	
    @Autowired
    PetRepository petRepository; 	

    @Autowired
    UserRepository userRepository; 

    public ApplicationEntity insertApplication(ApplicationEntity application, int petId, int userId) {
		// Retrieve PetEntity and UserEntity from repositories
		PetEntity pet = petRepository.findById(petId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));

		UserEntity user = userRepository.findById(userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

		// Set the relationships
		application.setPet(pet);
		application.setUser(user);

		return srepo.save(application);
    }
	
	// R - READ RECORD
	public List<ApplicationEntity> getAllApplication(){
		return srepo.findAll();
	}
	
	// D - DELETE RECORD
	public String deleteApplication(int applicationID) {
	    String msg = "";
	    
	    Optional<ApplicationEntity> optionalEntry = srepo.findById(applicationID);
	    
	    if (optionalEntry.isPresent()) {
	        ApplicationEntity entry = optionalEntry.get();
	        entry.setIsDeleted(true); // Update the isDeleted field
	        srepo.save(entry); // Save the updated entity back to the database
	        msg = "Application " + applicationID + " is successfully 'deleted'!";
	    } else {
	        msg = "Application " + applicationID + " does not exist!";
	    }
	    
	    return msg;
	}
}
