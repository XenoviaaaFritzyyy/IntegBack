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

    public GalleryEntity() {
        super();
    }

    public GalleryEntity(int galID, String name, String description) {
        this.galID = galID;
        this.name = name;
        this.description = description;
    }

    public int getDicID() {
        return galID;
    }

    public void setDicID(int dicID) {
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

}
