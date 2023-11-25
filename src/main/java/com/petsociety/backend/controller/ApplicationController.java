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
import org.springframework.web.bind.annotation.RestController;

import com.petsociety.backend.entity.ApplicationEntity;
import com.petsociety.backend.service.ApplicationService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    ApplicationService sserv;
    
    // C - Create a Student Record
    @PostMapping("/insertApplication")
    public ApplicationEntity insertApplication(@RequestBody ApplicationEntity application) {
        return sserv.insertApplication(application);
    }

    // R - Read a Student Record
    @GetMapping("/getAllApplication")
    public List<ApplicationEntity> getAllApplication() {
        return sserv.getAllApplication();
    }

    @PutMapping("/deleteApplication/{applicationID}")  // Corrected the path variable name
    public String deleteApplication(@PathVariable int applicationID) {
        return sserv.deleteApplication(applicationID);
    }
}
