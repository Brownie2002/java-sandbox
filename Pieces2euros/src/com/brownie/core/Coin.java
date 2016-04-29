package com.brownie.core;

public class Coin {

	private int refId ;
	private String country ;
	private String picture ;
	private int year ;
	private String reference ;
	private String comment ;

public Coin(int id, String country, String reference, int year) {
	setRefId(id);
	setCountry(country);
	setReference(reference);
	setYear(year);
	}

	// Getters and Setters
public int getRefId() {
	return refId;
}

public void setRefId(int refId) {
	this.refId = refId;
}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	// Methods
	@Override
	public String toString() {
		String comment = "This coin is from " + getCountry() + ", produced in " + getYear() + "["+getReference()+"]";
		return comment;
	}


	
}
