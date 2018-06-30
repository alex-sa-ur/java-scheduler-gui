package edu.miami.cis324.hw4.alejandrosanchez;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/*
 * Class used for:
 * - Check for upcoming visits, doctors, and patients
 * - Checks if a patient is matches for the visit
 * - Prints upcoming Visits by date
 */

/**
 * @author alejandrosanchez
 *
 */

public abstract class VisitUtils {
	private static SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
	
	/*
	 * Methods:
	 * hstHasUpcoming(List<Visit<Patient,Doctor>>, Doctor):								returns true if a doctor has at least one
	 * 																					upcoming visit
	 * 
	 * vtrHasUpcoming(List<Visit<Patient,Doctor>>, Patient):							returns true if a patient has at least one
	 * 																					upcoming visit
	 * 
	 * hstHasUpcoming(Map<Integer,List<Visit<Patient,Doctor>>>, Doctor):				identifies the doctor within a map then
	 * 																					returns the outcome of hstHasUpcoming()
	 * 																					with the list linked to that doctor
	 * 
	 * vtrHasUpcoming(Map<Integer,List<Visit<Patient,Doctor>>>, Patient):				identifies the patient within a map then
	 * 																					returns the outcome of vtrHasUpcoming()
	 * 																					with the list linked to that patient
	 * 
	 * isUpcoming(Visit<V,T>):															returns true if the visit is upcoming
	 * 
	 * isUpcomingHst(Visit<V,T>, T):													returns true if the visit is upcoming
	 * 																					and the doctor matches the visit doctor
	 * 
	 * isUpcomingVtr(Visit<V,T>, V):													returns true if the visit is upcoming
	 * 																					and the patient matches the visit patient
	 * 
	 * matchToDocID(Visit<Integer,Integer>, Doctor):									returns a doctor with an ID value matching
	 * 																					the host value of the visit
	 * 
	 * matchToPatID(Visit<Integer,Integer>, Patient):									returns a patient with an ID value matching
	 * 																					the visitor value of the visit
	 * 
	 * sortVisListDate(List<Visit<Integer,Integer>>):									sorts a list of visits by date
	 * 
	 * printUpcomingByDate(List<Visit<Integer,Integer>>, List<Patient>, List<Doctor>):	prints all upcoming visits by date
	 * 
	 */
	
	/*
	 * hstHasUpcoming(List<Visit<Patient,Doctor>>, Doctor):
	 * - for every visit in the list, it checks:
	 * 	- if the doc value is equal to the host value
	 *  - if the date of the visit is past today
	 * - if both conditions are true, it returns true
	 */
	
	public static Boolean hstHasUpcoming(List <Visit<Patient,Doctor>> listVis, Doctor doc) {
		for (Visit<Patient,Doctor> vis:listVis) {
			if ((vis.getHost().equals(doc) && (DateUtil.dayDifference(vis.getDate()) > 0))) return true;
		}
		return false;
	}
	
	/*
	 * vtrHasUpcoming(List<Visit<Patient,Doctor>>, Patient):
	 * - for every visit in the list, it checks:
	 * 	- if the pat value is equal to the visitor value
	 *  - if the date of the visit is past today
	 * - if both conditions are true, it returns true
	 */
	
	public static Boolean vtrHasUpcoming(List <Visit<Patient,Doctor>> listVis, Patient pat) {
		for (Visit<Patient,Doctor> vis:listVis) {
			if ((vis.getVisitor().equals(pat) && (DateUtil.dayDifference(vis.getDate()) > 0))) return true;
		}
		return false;
	}
	
	/*
	 * hstHasUpcoming(Map<Integer,List<Visit<Patient,Doctor>>>, Doctor):
	 * - identifies if the map contains the doc ID value as a key
	 * - if it does, it runs the hstHasUpcoming() method with the list linked to the docID
	 * - if it doesn't it automatically returns false
	 */
	
	public static Boolean hstHasUpcoming(Map <Integer, List <Visit<Patient,Doctor>>> mapVis, Doctor doc) {
		if (mapVis.containsKey(doc.getDocID())) {
			List <Visit<Patient,Doctor>> listVis = mapVis.get(doc.getDocID());
			return hstHasUpcoming(listVis, doc);
		}
		return false;
	}
	
	/*
	 * vtrHasUpcoming(Map<Integer,List<Visit<Patient,Doctor>>>, Patient):
	 * - identifies if the map contains the pat ID value as a key
	 * - if it does, it runs the hstHasUpcoming() method with the list linked to the patID
	 * - if it doesn't it automatically returns false
	 */
	
	public static Boolean vtrHasUpcoming(Map <Integer, List <Visit<Patient,Doctor>>> mapVis, Patient pat) {
		if (mapVis.containsKey(pat.getPatID())) {
			List <Visit<Patient,Doctor>> listVis = mapVis.get(pat.getPatID());
			return vtrHasUpcoming(listVis, pat);
		}
		return false;
	}
	
	/*
	 * isUpcoming(Visit<V,T>):
	 * - returns true if the vis date is past today
	 */
	
	public static <V,T> Boolean isUpcoming(Visit<V,T> vis) {
		if (DateUtil.dayDifference(vis.getDate()) > 0) return true;
		return false;
	}
	
	/*
	 * isUpcomingHst(Visit<V,T>, T):
	 * - returns true if the vis date is past today and the passed hst value matched the visit host
	 */
	
	public static <V,T> Boolean isUpcomingHst(Visit<V,T> vis, T hst) {
		return ((vis.getHost().equals(hst)) && (DateUtil.dayDifference(vis.getDate()) > 0));
	}
	
	/*
	 * isUpcomingVtr(Visit<V,T>, V):
	 * - returns true if the vis date is past today and the passed vtr value matched the visit visitor
	 */
	
	public static <V,T> Boolean isUpcomingVtr(Visit<V,T> vis, V vtr) {
		return ((vis.getVisitor().equals(vtr)) && (DateUtil.dayDifference(vis.getDate()) > 0));
	}
	
	/*
	 * matchToDocID(Visit<Integer,Integer>, Doctor):
	 * - takes the host value from the visit and returns a doctor with the matching docID value
	 */
	
	public static Doctor matchToDocID(Visit<Integer,Integer> vis, List<Doctor> listDoc) {
		for(Doctor doc:listDoc) {
			if(vis.getHost().equals(doc.getDocID())) return doc;
		}
		return null;
	}
	
	/*
	 * matchToPatID(Visit<Integer,Integer>, Patient):
	 * - takes the visitor value from the visit and returns a patient with the matching patID value
	 */
	
	public static Patient matchToPatID(Visit<Integer,Integer> vis, List<Patient> listPat) {
		for(Patient pat:listPat) {
			if(vis.getVisitor().equals(pat.getPatID())) return pat;
		}
		return null;
	}
	
	/*
	 * sortVisListDate(List<Visit<Integer,Integer>>):
	 * - runs through each visit in the list
	 * - for every visit it checks the following visits in the list
	 * - if any visit after the first visit being checked has an earlier date, they swap positions in the list
	 */
	
	public static void sortVisListDate(List<Visit<Integer,Integer>> listVis) {
		for(int i = 0; i < listVis.size() - 1; i++) {
			for(int j = i + 1; j < listVis.size(); j++) {
				if(listVis.get(i).compare(listVis.get(j)) > 0) Collections.swap(listVis, i, j);
			}
		}
	}
	
	public static Boolean isActiveVisit(Visit<Integer,Integer> vis, List<Patient> listPat, List<Doctor> listDoc) {
		return (matchToPatID(vis, listPat).getActiveStatus() && matchToDocID(vis, listDoc).getActiveStatus());
	}
	
	/*
	 * printUpcomingByDate(List<Visit<Integer,Integer>>, List<Patient>, List<Doctor>):
	 * - sorts the visits by date
	 * - runs through each visit, checking the following:
	 *  - if the visit is upcoming and if the visit or any component hasn't been removed from the system
	 *  - if both values of the host and visitor IDs are able to retrieve doctor and patient values from the corresponding lists
	 * - if the conditions are true, it returns the desired information in the given format
	 */
	
	public static void printUpcomingByDate(List <Visit<Integer,Integer>> listVis, List<Patient> listPat, List <Doctor> listDoc) {
		sortVisListDate(listVis);
		for(Visit<Integer,Integer> vis:listVis) {
			if (isUpcoming(vis) && (matchToDocID(vis, listDoc) != null) && 
					(matchToPatID(vis, listPat) != null) &&
					(isActiveVisit(vis, listPat, listDoc))) {
				System.out.printf(
					"Visit Date:			" 	+ DateUtil.dateToString(vis.getDate(), format) 				+ System.lineSeparator() +
					"Doctor:				" 	+ matchToDocID(vis, listDoc).getFullName().getFLName() 		+ System.lineSeparator() +
					"Specialty: 			" 	+ matchToDocID(vis, listDoc).getMedSpec() 					+ System.lineSeparator() +
					"Days Until Visit:		" 	+ DateUtil.dayDifference(vis.getDate()) 					+ System.lineSeparator() +
					"Patient:" 																				+ System.lineSeparator() +
					"	First Name:		" 		+ matchToPatID(vis, listPat).getFullName().getFirstName() 	+ System.lineSeparator() +
					"	Last Name:		" 		+ matchToPatID(vis, listPat).getFullName().getLastName() 	+ System.lineSeparator() +
					"	SSN:			" 		+ matchToPatID(vis, listPat).getPersonalData().getSSN() 	+ System.lineSeparator() +
					"	Age:			" 		+ matchToPatID(vis, listPat).getPersonalData().getAge()		+ System.lineSeparator()
				);
			}

			System.out.println("------------------------------------------");
		}
	}
}
