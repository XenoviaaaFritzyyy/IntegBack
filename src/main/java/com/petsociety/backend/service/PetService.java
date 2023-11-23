package com.petsociety.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petsociety.backend.entity.PetEntity;
import com.petsociety.backend.repository.PetRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PetService {

    @Autowired
    PetRepository petRepo;

    // C - CREATE OR INSERT PET RECORD IN tblPet
    public PetEntity insertPet(PetEntity pet) {
        return petRepo.save(pet);
    }

    // R - READ ALL RECORDS IN tblPet
    public List<PetEntity> getAllPets() {
        return petRepo.findAll();
    }

    // U - UPDATE A RECORD IN tblPet
    @SuppressWarnings("finally")
    public PetEntity updatePet(int petID, PetEntity newPetDetails) {
        PetEntity pet = new PetEntity();
        try {
            // Search the id number of the pet that will be updated
            pet = petRepo.findById(petID).get();

            pet.setName(newPetDetails.getName());
            pet.setDescription(newPetDetails.getDescription());
            pet.setAge(newPetDetails.getAge());
            pet.setTemperament(newPetDetails.getTemperament());
            pet.setColor(newPetDetails.getColor());
            pet.setGender(newPetDetails.getGender());
            pet.setSize(newPetDetails.getSize());
            pet.setVaccinated(newPetDetails.isVaccinated());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Pet " + petID + " does not exist!");
        } finally {
            return petRepo.save(pet);
        }
    }

    // D - DELETE A RECORD IN tblPet
    public String deletePet(int petID) {
        String msg = "";

        if (petRepo.findById(petID).isPresent()) {
            petRepo.deleteById(petID);
            msg = "Pet " + petID + " is successfully deleted!";
        } else
            msg = "Pet " + petID + " does not exist!";
        return msg;
    }

    // Get Pet ID
    public PetEntity getPetID(int petID){
        return petRepo.findById(petID)
        .orElseThrow(()-> new EntityNotFoundException("Pet not found"));
    }
}
