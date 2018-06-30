package edu.miami.cis324.hw4.alejandrosanchez;

/*
 * Base Interface for Doctor and Patient
 * 
 * Getters for all shared attributes between both sub-interfaces:
 * - PersonalData 	personalData
 * - Name			name
 */

/**
 * @author alejandrosanchez
 *
 */

public interface Person extends Comparable<Person> {
	Name 			getFullName();
	PersonalData 	getPersonalData();
	Boolean			getActiveStatus();
	void			setActiveStatus(Boolean newStat);
}
