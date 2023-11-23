package com.petsociety.backend.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
            @RequestParam("image") MultipartFile image) throws IOException 
        {
            imageService.uploadImage(image);
            PetEntity petEntity = petService.getPetID(petID);
            petEntity.setPhotoPath(image.getOriginalFilename());
            return petService.insertPet(petEntity);
        }   

    @GetMapping("/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable String fileName) {
        try {
            byte[] image = imageService.downloadImage(fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<?> deleteImage(@PathVariable String fileName) {
        try {
            imageService.deleteFile(fileName);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }         
}

