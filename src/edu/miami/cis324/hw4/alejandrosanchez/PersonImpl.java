package edu.miami.cis324.hw4.alejandrosanchez;

import java.util.Date;

/*
 * Abstract class that implements the Person interface
 * Used as a base class for Patient and Doctor
 */

/**
 * @author alejandrosanchez
 *
 */

public abstract class PersonImpl implements Person {
	protected Name			fullName;
	protected PersonalData 	personalData;
	protected Boolean		activeStatus = true;

	//Constructor
	PersonImpl(Date dob, String ssn, Name name, Boolean activeStatus) { 		//dob as a Date
		this.fullName 		= name;
		this.personalData 	= new PersonalData(ssn, dob);
		this.activeStatus	= activeStatus;
	}
	
	PersonImpl(String dob, String ssn, Name name, Boolean activeStatus) { 	//dob as a String
		this.fullName 		= name;
		this.personalData 	= new PersonalData(ssn, DateUtil.getFromString(dob));
		this.activeStatus	= activeStatus;
	}
	
	PersonImpl(PersonalData data, Name name, Boolean activeStatus) { 
		this.fullName 		= name;
		this.personalData 	= data;
		this.activeStatus	= activeStatus;
	}
	
	/*
	 * Methods:
	 * compareTo(Person): 	from Comparable interface [Compares by lastName, firstName, dob, ssn]
	 * getters for:
	 * - Personal Data:		personalData
	 * - Full name: 		fullName
	 * - Active Status:		activeStatus
	 * hashCode(): 			from Object [Based on lastName, firstName, dob, ssn]
	 * equals(Object): 		from Object [Based on lastName, firstName, dob, ssn]
	 */
	
	//getters
	@Override
	//from Person
	public PersonalData getPersonalData() {
		return personalData;
	}

	@Override
	//from Person
	public Name getFullName() {
		return fullName;
	}
	
	@Override
	//from Person
	public Boolean getActiveStatus() {
		return activeStatus;
	}
	
	public void setActiveStatus(Boolean newStat) {
		this.activeStatus = newStat;
	}
	
	//methods
	@Override
	//from Comparable
	public int compareTo(Person other) {
		if (!this.fullName.getLastName().equalsIgnoreCase(other.getFullName().getLastName())) 
			return this.fullName.getLastName().compareToIgnoreCase(other.getFullName().getLastName());
		if (!this.fullName.getFirstName().equalsIgnoreCase(other.getFullName().getFirstName())) 
			return this.fullName.getFirstName().compareToIgnoreCase(other.getFullName().getFirstName());
		if (!this.personalData.getDOB().equals(other.getPersonalData().getDOB())) 						
			return this.personalData.getDOB().compareTo(other.getPersonalData().getDOB());
		else 														
			return this.personalData.getSSN().compareTo(other.getPersonalData().getSSN());
	}
	
	@Override 
	//from Object
	public int hashCode() {
		int result = 0;
		
		result = 31 * result + (this.fullName.getLastName() 	!= null ? fullName.getLastName().hashCode() : 0);
		result = 31 * result + (this.fullName.getFirstName() 	!= null ? fullName.getFirstName().hashCode() : 0);
		result = 31 * result + (this.personalData.getSSN() 		!= null ? personalData.getSSN().hashCode() : 0);
		result = 31 * result + (this.personalData.getDOB() 		!= null ? personalData.getDOB().hashCode() : 0);
		
		return result;
	}
	
	@Override
	//from Object
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || this.getClass() != other.getClass()) return false;
		
		Person otherPer 						= (PersonImpl) other;
		
		return ((this.fullName.getLastName() 	== otherPer.getFullName().getLastName()) &&
				(this.fullName.getFirstName()	== otherPer.getFullName().getFirstName()) &&
				(this.personalData.getSSN() 	== otherPer.getPersonalData().getSSN()) &&
				(this.personalData.getDOB() 	== otherPer.getPersonalData().getDOB()));
	}
}
