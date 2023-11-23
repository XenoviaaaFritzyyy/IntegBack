package com.petsociety.backend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petsociety.backend.entity.PetEntity;
import com.petsociety.backend.service.ImageService;
import com.petsociety.backend.service.PetService;

@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {
    @Autowired
    ImageService imageService;
    @Autowired
    PetService petService;

    @PostMapping("/insertPet/{petID}")
    public PetEntity addImage(
            @PathVariable int petID,
            @RequestParam("image") MultipartFile image) throws IOException {

            // Handle the file upload
            imageService.uploadImage(image);

            // Retrieve the existing PetEntity from the database
            PetEntity petEntity = petService.getPetID(petID);

            // Update the petEntity with the JSON data
            petEntity.setPhotoPath(image.getOriginalFilename());
            // Add more fields as needed

            // Save the updated PetEntity
            return petService.insertPet(petEntity);
            
     } 
            
}

