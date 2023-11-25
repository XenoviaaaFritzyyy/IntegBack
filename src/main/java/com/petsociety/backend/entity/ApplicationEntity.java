package com.petsociety.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tblapplication")
public class ApplicationEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int applicationID;
	
	private String fname;
	private String lname;
	
	private String address;
	private String city;
	private String state;
	private int noAdults;
	private int noChildren;
	private String desHousehold;
	private String typeResidence;
	private String rentHome;
	private String landlordContact;

    private Boolean isDeleted;

    private int fk_petID;

    private int fk_userID;

	public ApplicationEntity() {
		super();
	}

	public ApplicationEntity(int applicationID, String fname, String lname, String address, String city, String state,
			int noAdults, int noChildren, String desHousehold, String typeResidence, String rentHome,
			String landlordContact, Boolean isDeleted, int fk_petID, int fk_userID) {
		super();
		this.applicationID = applicationID;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.noAdults = noAdults;
		this.noChildren = noChildren;
		this.desHousehold = desHousehold;
		this.typeResidence = typeResidence;
		this.rentHome = rentHome;
		this.landlordContact = landlordContact;
		this.isDeleted = isDeleted;
		this.fk_petID = fk_petID;
		this.fk_userID = fk_userID;
	}

	public int getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getNoAdults() {
		return noAdults;
	}

	public void setNoAdults(int noAdults) {
		this.noAdults = noAdults;
	}

	public int getNoChildren() {
		return noChildren;
	}

	public void setNoChildren(int noChildren) {
		this.noChildren = noChildren;
	}

	public String getDesHousehold() {
		return desHousehold;
	}

	public void setDesHousehold(String desHousehold) {
		this.desHousehold = desHousehold;
	}

	public String getTypeResidence() {
		return typeResidence;
	}

	public void setTypeResidence(String typeResidence) {
		this.typeResidence = typeResidence;
	}

	public String getRentHome() {
		return rentHome;
	}

	public void setRentHome(String rentHome) {
		this.rentHome = rentHome;
	}

	public String getLandlordContact() {
		return landlordContact;
	}

	public void setLandlordContact(String landlordContact) {
		this.landlordContact = landlordContact;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getFk_petID() {
		return fk_petID;
	}

	public void setFk_petID(int fk_petID) {
		this.fk_petID = fk_petID;
	}

	public int getFk_userID() {
		return fk_userID;
	}

	public void setFk_userID(int fk_userID) {
		this.fk_userID = fk_userID;
	}
}