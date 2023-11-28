package com.petsociety.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblpet")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "petID")
    private int petID;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "age")
    private int age;

    @Column(name = "temperament")
    private String temperament;

    @Column(name = "color")
    private String color;

    @Column(name = "gender")
    private String gender;

    @Column(name = "size")
    private String size;

    @Column(name = "vaccinated")
    private String vaccinated;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    public PetEntity() {
        super();
    }

    public PetEntity(int petID, String name, String description, int age, String temperament, String color,
            String gender, String size, String vaccinated, String photoPath, boolean isDeleted) {
        this.petID = petID;
        this.name = name;
        this.description = description;
        this.age = age;
        this.temperament = temperament;
        this.color = color;
        this.gender = gender;
        this.size = size;
        this.vaccinated = vaccinated;
        this.photoPath = photoPath;
        this.isDeleted = isDeleted;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(String vaccinated) {
        this.vaccinated = vaccinated;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
