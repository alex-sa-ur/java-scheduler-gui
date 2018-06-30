package edu.miami.cis324.hw4.alejandrosanchez;

import java.util.Date;

/*
 * Class that contains String ssn and Date dob values,
 * and getters to obtain them
 */

/**
 * @author alejandrosanchez
 *
 */

public class PersonalData {
	private String ssn = " ";
	private Date dob = new Date();
	
	//Constructor
	PersonalData(String ssn, Date dob){
		this.ssn = ssn;
		this.dob = dob;
	}
	
	//Methods
	public String getSSN() {
		return this.ssn;
	}
	
	public Date getDOB() {
		return this.dob;
	}
	
	public Integer getAge() {
		return -DateUtil.yearDifference(this.dob);
	}
	
	public void setDOB(Date newDOB) {
		this.dob = newDOB;
	}
	
	public void setSSN(String newSSN) {
		this.ssn = newSSN;
	}
}
