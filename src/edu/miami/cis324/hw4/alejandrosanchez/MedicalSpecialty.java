package edu.miami.cis324.hw4.alejandrosanchez;

/*
 * Contains allowed values for Doctor specialties
 * 
 * Methods:
 * - getFromString(): turns a string into a respective MedicalSpecialty value 
 */

/**
 * @author alejandrosanchez
 *
 */

public enum MedicalSpecialty {
	GENERAL_MEDICINE,
	PEDIATRICS,
	ONCOLOGY;
	
	public static MedicalSpecialty getFromString(String ms) {
		return valueOf(ms.toUpperCase());
	}
}
