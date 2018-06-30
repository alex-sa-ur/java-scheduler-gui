package edu.miami.cis324.hw4.alejandrosanchez;

import java.util.List;
import java.util.ArrayList;

/*
 * Class used for:
 * - Holding data
 * - Getting data being held
 */

/**
 * @author alejandrosanchez
 *
 */

public class SchedulerData {
	private List<Patient> 					listPat = new ArrayList<Patient>();
	private List<Doctor> 					listDoc = new ArrayList<Doctor>();
	private List<Visit<Integer,Integer>> 	listVis	= new ArrayList<Visit<Integer,Integer>>();
	
	//Constructor
	SchedulerData(){}
	
	/*
	 * Methods:
	 * getters for:
	 * - List of Patients:		listPat
	 * - List of Doctors:		listDoc
	 * - List of Visits:		listVis
	 * addPatient(Patient): 	adds a Patient to the List<Patient> listPat
	 * addDoctor(Doctor): 		adds a Doctor to the List<Doctor> listDoc
	 * addVisit(Visit):			adds a Visit to the List<Visit<Integer,Integer>> 
	 */
	
	//getters
	public List<Patient> getListPat(){
		return listPat;
	}
	
	public List<Doctor> getListDoc(){
		return listDoc;
	}
	
	public List<Visit<Integer,Integer>> getListVis(){
		return listVis;
	}
	
	//methods
	public void addPatient(Patient pat) {
		listPat.add(pat);
	}
	
	public void addDoctor(Doctor doc) {
		listDoc.add(doc);
	}
	
	public void addVisit(Visit<Integer,Integer> vis) {
		listVis.add(vis);
	}
}
