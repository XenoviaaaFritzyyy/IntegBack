package com.petsociety.backend.controller;

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
import com.petsociety.backend.entity.ApplicationEntity;
import com.petsociety.backend.service.ApplicationService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    ApplicationService sserv;
    
    // C - Create RECORD
    @PostMapping("/insertApplication")
    public ResponseEntity<ApplicationEntity> insertApplication(@RequestBody ApplicationEntity application,
                                                              @RequestParam("petId") int petId,
                                                              @RequestParam("userId") int userId) {
        try {
            ApplicationEntity createdApplication = sserv.insertApplication(application, petId, userId);
            return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    // R - Read RECORD
    @GetMapping("/getAllApplication")
    public List<ApplicationEntity> getAllApplication() {
        return sserv.getAllApplication();
    }


	// U - Update RECORD
	 @PutMapping("/updateApplicationStatus/{applicationID}")
	 public ApplicationEntity updateEntry(@PathVariable int applicationID, @RequestBody ApplicationEntity newEntryDetails) {
	     return sserv.updateEntry(applicationID, newEntryDetails);
	 }
}
