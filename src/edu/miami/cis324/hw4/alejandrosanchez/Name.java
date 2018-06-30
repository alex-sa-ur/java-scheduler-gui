package edu.miami.cis324.hw4.alejandrosanchez;

/*
 * Base class for Name objects
 * - Separated into First Name and Last Name
 * - Includes getters for first name and last name
 * - Overrides toString to return full name
 */

/**
 * @author alejandrosanchez
 *
 */

public class Name {
	private String firstName = " ";
	private String lastName = " ";
	private String midName = " ";
	
	//Constructor
	Name (String firstName, String lastName, String midName) {
		this.firstName	= firstName;
		this.lastName 	= lastName;
		this.midName	= midName;
	}
	
	//Methods
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getMidName() {
		return this.midName;
	}
	
	public String getFLName() {
		return this.firstName + " " + this.lastName;
	}
	
	public void setFName(String name) {
		this.firstName = name;
	}
	
	public void setMName(String name) {
		this.midName = name;
	}
	
	public void setLName(String name) {
		this.lastName = name;
	}
	
	@Override
	public String toString() {
		return firstName + " "+ midName + " " + lastName;
	}
}
