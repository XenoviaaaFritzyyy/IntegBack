package com.petsociety.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petsociety.backend.entity.ApplicationEntity;
import com.petsociety.backend.repository.ApplicationRepository;

import jakarta.transaction.Transactional;

@Service
public class ApplicationService {
	@Autowired
	ApplicationRepository srepo;
	
	// C - CREATE OR INSERT STUDENT RECORD IN tblStudent
	@Transactional
	public ApplicationEntity insertApplication(ApplicationEntity application) {
		return srepo.save(application);
	}
	
	// R - READ ALL RECORDS IN tlbStudebt
	public List<ApplicationEntity> getAllApplication(){
		return srepo.findAll();
	}
	
	// D - DELETE A RECORD IN tlbStudent
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
