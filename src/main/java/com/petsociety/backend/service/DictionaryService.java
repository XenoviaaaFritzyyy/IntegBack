package com.petsociety.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petsociety.backend.entity.DictionaryEntity;
import com.petsociety.backend.repository.DictionaryRepository;

@Service
public class DictionaryService {
	@Autowired
	DictionaryRepository srepo;
	
	// C - CREATE OR INSERT STUDENT RECORD IN tblStudent
	public DictionaryEntity insertEntry(DictionaryEntity entry) {
		return srepo.save(entry);
	}
	
	// R - READ ALL RECORDS IN tlbStudebt
	public List<DictionaryEntity> getAllEntry(){
		return srepo.findAll();
	}
	
    // Read a Single Entry by DictionaryID
    public DictionaryEntity getEntryById(int dicID) {
        return srepo.findById(dicID).orElseThrow(() -> new NoSuchElementException("Entry " + dicID + " does not exist!"));
    }
	
	// U - UPDATE A RECORD IN tlbStudent
	@SuppressWarnings("finally")
	public DictionaryEntity updateEntry(int dicID, DictionaryEntity newEntryDetails) {
		DictionaryEntity entry = new DictionaryEntity();
		try {
			// Search the id number of the student will be updated
			entry = srepo.findById(dicID).get();
			
			entry.setEntry(newEntryDetails.getEntry());
			entry.setDescription(newEntryDetails.getDescription());
			entry.setIsDeleted(newEntryDetails.getIsDeleted());


		}catch(NoSuchElementException ex) {
			throw new NoSuchElementException("Entry " + dicID + "does not exist!");
		}finally {
			return srepo.save(entry);
		}
	}
	
	// D - DELETE A RECORD IN tlbStudent
	public String deleteEntry(int dicID) {
	    String msg = "";
	    
	    Optional<DictionaryEntity> optionalEntry = srepo.findById(dicID);
	    
	    if (optionalEntry.isPresent()) {
	        DictionaryEntity entry = optionalEntry.get();
	        entry.setIsDeleted(true); // Update the isDeleted field
	        srepo.save(entry); // Save the updated entity back to the database
	        msg = "Entry " + dicID + " is successfully 'deleted'!";
	    } else {
	        msg = "Entry " + dicID + " does not exist!";
	    }
	    
	    return msg;
	}
}
