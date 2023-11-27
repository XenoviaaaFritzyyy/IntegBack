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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petsociety.backend.entity.UserEntity;
import com.petsociety.backend.service.ImageService;
import com.petsociety.backend.service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class PImageController {
    @Autowired
    ImageService imageService;
    @Autowired
    UserService userService;

    @PostMapping("/insertUser/{userID}")
    public UserEntity addImage(
            @PathVariable int userID,
            @RequestParam("image") MultipartFile image) throws IOException {
        imageService.uploadImage(image);
        UserEntity userEntity = userService.getUserID(userID);
        userEntity.setPhotoPath(image.getOriginalFilename());
        return userService.insertUser(userEntity);
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
