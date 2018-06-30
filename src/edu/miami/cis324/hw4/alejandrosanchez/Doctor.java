package edu.miami.cis324.hw4.alejandrosanchez;

/*
 * Interface for DoctorImpl
 * 
 * Getters for:
 * - Doctor ID:			docID
 * - Medical Specialty:	medSpec
 */

/**
 * @author alejandrosanchez
 *
 */

public interface Doctor extends Person {
	Integer getDocID();
	MedicalSpecialty getMedSpec();
	void setMedSpec(String newSpec);
}
