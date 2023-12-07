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

import com.petsociety.backend.entity.GalleryEntity;
import com.petsociety.backend.service.GalleryService;

@RestController
@RequestMapping("/gallery")
@CrossOrigin(origins = "http://localhost:3000")
public class GalleryController {

    @Autowired
    GalleryService galleryService;

    // C - Create RECORD
    @PostMapping("/insertGallery")
    public ResponseEntity<GalleryEntity> insertGallery(@RequestBody GalleryEntity gallery,
            @RequestParam("userId") int userId) {
        try {
            GalleryEntity createdGallery = galleryService.insertGallery(gallery, userId);

            return new ResponseEntity<>(createdGallery, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // R - Read all Gallery Records
    @GetMapping("/getAllGallery")
    public List<GalleryEntity> getAllGallerys() {
        return galleryService.getAllGallerys();
    }

    // U - Update a Gallery Record
    @PutMapping("/updateGallery")
    public GalleryEntity updateGallery(@RequestParam int galID, @RequestBody GalleryEntity newGalleryDetails) {
        return galleryService.updateGallery(galID, newGalleryDetails);
    }

    // D - Delete a Gallery Record
    @PutMapping("/deleteGallery/{galID}")
    public String deleteGallery(@PathVariable int galID) {
        return galleryService.deleteGallery(galID);
    }

    @GetMapping("/info/{galID}")
    public GalleryEntity getGalleryById(@PathVariable int galID) {
        return galleryService.getGalleryID(galID);
    }

}
