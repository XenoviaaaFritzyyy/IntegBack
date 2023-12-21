package com.petsociety.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petsociety.backend.entity.GalleryEntity;
import com.petsociety.backend.entity.UserEntity;
import com.petsociety.backend.repository.GalleryRepository;
import com.petsociety.backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GalleryService {
    @Autowired
    GalleryRepository srepo;

    @Autowired
    UserRepository userRepository;

    public GalleryEntity insertGallery(GalleryEntity entry, int userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        entry.setUser(user);

        return srepo.save(entry);
    }

    // R - READ ALL RECORDS IN tlbStudebt
    public List<GalleryEntity> getAllGallerys() {
        return srepo.findAll();
    }

    // Read a Single Entry by DictionaryID
    public GalleryEntity getGalleryById(int galID) {
        return srepo.findById(galID)
                .orElseThrow(() -> new NoSuchElementException("Entry " + galID + " does not exist!"));
    }

    // U - UPDATE A RECORD IN tlbStudent
    @SuppressWarnings("finally")
    public GalleryEntity updateGallery(int galID, GalleryEntity newEntryDetails) {
        GalleryEntity entry = new GalleryEntity();
        try {
            // Search the id number of the student will be updated
            entry = srepo.findById(galID).get();
            entry.setName(newEntryDetails.getName());
            entry.setDescription(newEntryDetails.getDescription());

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Entry " + galID + "does not exist!");
        } finally {
            return srepo.save(entry);
        }
    }

    // D - DELETE A RECORD IN tlbStudent
    public String deleteGallery(int galID) {
        String msg = "";

        Optional<GalleryEntity> optionalGallery = srepo.findById(galID);

        if (optionalGallery.isPresent()) {
            GalleryEntity entry = optionalGallery.get();
            entry.setIsDeleted(true); // Update the isDeleted field
            srepo.save(entry); // Save the updated entity back to the database
            msg = "Entry " + galID + " is successfully 'deleted'!";
        } else {
            msg = "Entry " + galID + " does not exist!";
        }
        return msg;
    }

    // Get Gallery ID
    public GalleryEntity getGalleryID(int galleryID) {
        return srepo.findById(galleryID)
                .orElseThrow(() -> new EntityNotFoundException("Gallery not found"));
    }
}