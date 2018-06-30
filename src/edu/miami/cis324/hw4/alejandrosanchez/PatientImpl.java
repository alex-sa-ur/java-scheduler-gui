package edu.miami.cis324.hw4.alejandrosanchez;

import java.util.Date;

/*
 * Class that implements the Patient interface
 */

/**
 * @author alejandrosanchez
 *
 */

public class PatientImpl extends PersonImpl implements Patient {
	private Integer 			patID = 0;
	private static Integer		maxPatID = 0;
	
	//Constructor
	PatientImpl(Date dob, String ssn, Name name, Boolean activeStatus, Integer patID){ 	//dob as a Date
		super(dob, ssn, name, activeStatus);
		this.patID = patID;
		if (maxPatID < patID) maxPatID = patID;
	}
	
	PatientImpl(String dob, String ssn, Name name, Boolean activeStatus, Integer patID){	//dob as a String
		super(dob, ssn, name, activeStatus);
		this.patID = patID;
		if (maxPatID < patID) maxPatID = patID;
	}
	
	PatientImpl(PersonalData data, Name name, Boolean activeStatus, Integer patID){	//dob as a String
		super(data, name, activeStatus);
		this.patID = patID;
		if (maxPatID < patID) maxPatID = patID;
	}
	
	PatientImpl(PersonalData data, Name name, Boolean activeStatus){	//dob as a String
		super(data, name, activeStatus);
		maxPatID = maxPatID + 1;
		this.patID = maxPatID;
	}
	
	/*
	 * Methods:
	 * getters for:
	 * - Patient ID: 	patID
	 * hashCode(): 		from Object [Based on patID, lastName, firstName, ssn, dob]
	 * equals(Object): 	from Object [Based on patID, lastName, firstName, ssn, dob]
	 */
	
	//getters
	@Override
	//from Patient
	public Integer getPatID() {
		return patID;
	}
	
	//methods
	@Override 
	//from Object
	public int hashCode() {
		int result = 0;
		
		result = 31 * result + (this.patID);
		result = 31 * result + (this.fullName.getLastName() 	!= null ? fullName.getLastName().hashCode() : 0);
		result = 31 * result + (this.fullName.getFirstName() 	!= null ? fullName.getFirstName().hashCode() : 0);
		result = 31 * result + (this.personalData.getSSN() 		!= null ? this.personalData.getSSN().hashCode() : 0);
		result = 31 * result + (this.personalData.getDOB() 		!= null ? this.personalData.getDOB().hashCode() : 0);
		
		return result;
	}
	
	@Override
	//from Object
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || this.getClass() != other.getClass()) return false;
		
		PatientImpl otherPat 					= (PatientImpl) other;
		
		return ((this.getPatID() 				== otherPat.getPatID()) &&
				(this.fullName.getLastName()	== otherPat.fullName.getLastName()) &&
				(this.fullName.getFirstName() 	== otherPat.fullName.getFirstName()) &&
				(this.personalData.getSSN() 	== otherPat.personalData.getSSN()) &&
				(this.personalData.getDOB() 	== otherPat.personalData.getDOB()));
	}
}
