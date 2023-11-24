package com.petsociety.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petsociety.backend.entity.PetEntity;
import com.petsociety.backend.service.PetService;

@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "http://localhost:3000")
public class PetController {

    @Autowired
    PetService petService;

    // C - Create a Pet Record
    @PostMapping("/insertPet")
    public PetEntity insertPet(@RequestBody PetEntity pet) {
        return petService.insertPet(pet);
    }

    // R - Read all Pet Records
    @GetMapping("/getAllPets")
    public List<PetEntity> getAllPets() {
        return petService.getAllPets();
    }

    // U - Update a Pet Record
    @PutMapping("/updatePet")
    public PetEntity updatePet(@RequestParam int petID, @RequestBody PetEntity newPetDetails) {
        return petService.updatePet(petID, newPetDetails);
    }

    // D - Delete a Pet Record
    @PutMapping("/deletePet/{petID}")
    public String deletePet(@PathVariable int petID) {
        return petService.deletePet(petID);
    }
    
    
    @GetMapping("/info/{petID}")
    public PetEntity getpetbyID(@PathVariable int petID) {
        return petService.getPetID(petID);
    }

}
