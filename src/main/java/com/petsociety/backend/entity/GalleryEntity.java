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
    private String name;
    private String description;
    private String photoPath;
    private boolean isDeleted;

    public GalleryEntity() {
        super();
    }

    public GalleryEntity(int galID, String name, String description, String photoPath, boolean isDeleted) {
        this.galID = galID;
        this.name = name;
        this.description = description;
        this.isDeleted = isDeleted;
        this.photoPath = photoPath;
    }

    public int getGalID() {
        return galID;
    }

    public void setGalID(int dicID) {
        this.galID = dicID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
