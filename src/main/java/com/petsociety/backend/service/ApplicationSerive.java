package com.petsociety.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petsociety.backend.entity.ApplicationEntity;
import com.petsociety.backend.repository.ApplicationRepository;

@Service
public class ApplicationSerive {
	@Autowired
	ApplicationRepository srepo;
	
	// C - CREATE OR INSERT STUDENT RECORD IN tblStudent
	public ApplicationEntity insertApplication(ApplicationEntity application) {
		return srepo.save(application);
	}
	
	// R - READ ALL RECORDS IN tlbStudebt
	public List<ApplicationEntity> getAllApplication(){
		return srepo.findAll();
	}
	
// U - UPDATE A RECORD IN tlbStudent
@SuppressWarnings("finally")
public ApplicationEntity updateApplication(int applicationID, ApplicationEntity newEntryDetails) {
    ApplicationEntity application = new ApplicationEntity();
    try {
        // Search the id number of the application that will be updated
        application = srepo.findById(applicationID).orElseThrow(() -> new NoSuchElementException("Application " + applicationID + " does not exist!"));

        application.setFname(newEntryDetails.getFname());
        application.setLname(newEntryDetails.getLname());
        application.setAddress(newEntryDetails.getAddress());
        application.setCity(newEntryDetails.getCity());
        application.setState(newEntryDetails.getState());
        application.setNoAdults(newEntryDetails.getNoAdults());
        application.setDesHousehold(newEntryDetails.getDesHousehold());
        application.setTypeResidence(newEntryDetails.getTypeResidence());
        application.setRentHome(newEntryDetails.getRentHome());
        application.setLandlordContact(newEntryDetails.getLandlordContact());
        application.setIsDeleted(newEntryDetails.getIsDeleted());
        application.setRequestStatus(newEntryDetails.getRequestStatus());
        application.setRentHome(newEntryDetails.getRentHome());

    } catch (NoSuchElementException ex) {
        throw new NoSuchElementException("Application " + applicationID + " does not exist!");
    } finally {
        return srepo.save(application);
    }
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
