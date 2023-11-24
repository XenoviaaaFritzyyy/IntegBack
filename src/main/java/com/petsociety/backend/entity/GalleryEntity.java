package com.petsociety.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblgallery")
public class GalleryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int galID;

    // private String entry;
    // private String description;
    // private boolean isDeleted;

    public GalleryEntity() {
        super();
    }

    public GalleryEntity(int galID/* , String entry, String description, boolean isDeleted */) {
        super();
        this.galID = galID;
        // this.entry = entry;
        // this.description = description;
    }

    public int getDicID() {
        return galID;
    }

    public void setDicID(int dicID) {
        this.galID = dicID;
    }

    // public String getEntry() {
    // return entry;
    // }

    // public void setEntry(String entry) {
    // this.entry = entry;
    // }

    // public String getDescription() {
    // return description;
    // }

    // public void setDescription(String description) {
    // this.description = description;
    // }

    // public boolean getIsDeleted() {
    // return isDeleted;
    // }

    // public void setIsDeleted(boolean isDeleted) {
    // this.isDeleted = isDeleted;
    // }

}
