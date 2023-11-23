package com.petsociety.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbldictionary")
public class DictionaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dicID;
	
	private String entry;
	private String description;
	private boolean isDeleted;
	
	public DictionaryEntity() {
		super();
	}

	public DictionaryEntity(int dicID, String entry, String description, boolean isDeleted) {
		super();
		this.dicID = dicID;
		this.entry = entry;
		this.description = description;
	}

	public int getDicID() {
		return dicID;
	}

	public void setDicID(int dicID) {
		this.dicID = dicID;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}

