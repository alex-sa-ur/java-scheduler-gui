package edu.miami.cis324.hw4.alejandrosanchez;

import java.util.Date;

/*
 * Class which extends the Doctor interface
 */

/**
 * @author alejandrosanchez
 *
 */

public class DoctorImpl extends PersonImpl implements Doctor{
	private Integer 			docID = 0;
	private MedicalSpecialty 	medSpec = MedicalSpecialty.GENERAL_MEDICINE;
	private static Integer		maxDocID = 0;
	
	//Constructor
	DoctorImpl (Date dob, String ssn, Name name, Boolean activeStatus, Integer docID, String medSpec){ 	//dob as a Date
		super(dob, ssn, name, activeStatus);
		this.docID 		= docID;
		if (maxDocID <= docID) maxDocID = docID;
		this.medSpec 	= MedicalSpecialty.getFromString(medSpec);
	}
	
	DoctorImpl (String dob, String ssn, Name name, Boolean activeStatus, Integer docID, String spec){	//dob as a String
		super(dob, ssn, name, activeStatus);
		this.docID	 	= docID;
		if (maxDocID <= docID) maxDocID = docID;
		this.medSpec 	= MedicalSpecialty.getFromString(spec);
	}
	
	DoctorImpl (PersonalData data, Name name, Boolean activeStatus, Integer docID, String spec){		//dob as a String
		super(data, name, activeStatus);
		this.docID	 	= docID;
		if (maxDocID <= docID) maxDocID = docID;
		this.medSpec 	= MedicalSpecialty.getFromString(spec);
	}
	
	DoctorImpl (PersonalData data, Name name, Boolean activeStatus, String spec){						//dob as a String
		super(data, name, activeStatus);
		this.docID	 	= ++maxDocID;
		this.medSpec 	= MedicalSpecialty.getFromString(spec);
	}
	
	/*
	 * Methods:
	 * getters for:
	 * - Doctor ID: 			docID
	 * - Medical Specialty: 	medSpec
	 * hashCode(): 				from Object [Based on docID, lastName, firstName, ssn, dob, medSpec]
	 * equals(Object): 			from Object [Based on docID, lastName, firstName, ssn, dob, medSpec]
	 */
	
	//getters
	@Override
	//from Doctor
	public Integer getDocID() {
		return docID;
	}

	@Override
	//from Doctor
	public MedicalSpecialty getMedSpec() {
		return medSpec;
	}
	
	public void setMedSpec(String newSpec) {
		this.medSpec 	= MedicalSpecialty.getFromString(newSpec);
	}
	
	//methods
	@Override 
	//from Object
	public int hashCode() {
		int result = 0;
		
		result = 31 * result + (this.docID);
		result = 31 * result + (this.getFullName().getLastName() 	!= null ? this.getFullName().getLastName().hashCode() : 0);
		result = 31 * result + (this.getFullName().getFirstName() 	!= null ? this.getFullName().getFirstName().hashCode() : 0);
		result = 31 * result + (this.getPersonalData().getSSN()		!= null ? this.getPersonalData().getSSN().hashCode() : 0);
		result = 31 * result + (this.getPersonalData().getDOB() 	!= null ? this.getPersonalData().getDOB().hashCode() : 0);
		result = 31 * result + (this.medSpec 						!= null ? medSpec.hashCode() : 0);
		
		return result;
	}
	
	@Override
	//from Object
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || this.getClass() != other.getClass()) return false;
		
		DoctorImpl otherDoc 					= (DoctorImpl) other;
		
		return ((this.docID 					== otherDoc.getDocID()) &&
				(this.fullName.getLastName() 	== otherDoc.getFullName().getLastName()) &&
				(this.fullName.getFirstName()	== otherDoc.getFullName().getFirstName()) &&
				(this.personalData.getSSN()		== otherDoc.getPersonalData().getSSN()) &&
				(this.personalData.getDOB() 	== otherDoc.getPersonalData().getDOB()) &&
				(this.medSpec 					== otherDoc.getMedSpec()));
	}
}
