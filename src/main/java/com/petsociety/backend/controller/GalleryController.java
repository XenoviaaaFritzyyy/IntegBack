package com.petsociety.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.petsociety.backend.entity.GalleryEntity;
import com.petsociety.backend.service.GalleryService;

@RestController
@RequestMapping("/gallery")
@CrossOrigin(origins = "http://localhost:3000")
public class GalleryController {

    @Autowired
    GalleryService galleryService;

    // C - Create a Gallery Record
    @PostMapping("/insertGallery")
    public GalleryEntity insertGallery(@RequestBody GalleryEntity gallery) {
        return galleryService.insertGallery(gallery);
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
    @DeleteMapping("/deleteGallery/{galID}")
    public String deleteGallery(@PathVariable int galID) {
        return galleryService.deleteGallery(galID);
    }

    @GetMapping("/info/{galleryID}")
    public GalleryEntity getGalleryById(@PathVariable int galID) {
        return galleryService.getGalleryById(galID);
    }

}
