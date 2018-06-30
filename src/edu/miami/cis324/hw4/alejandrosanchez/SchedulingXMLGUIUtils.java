package edu.miami.cis324.hw4.alejandrosanchez;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.xml.stream.XMLStreamException;

/**
 * @author alejandrosanchez
 *
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SchedulingXMLGUIUtils {
	
	public static JComboBox<String> comboPatientBox(SchedulerData data) {
		List<Patient> plist = data.getListPat();
		
		List<String> slist = new ArrayList();
		
		for (Patient p: plist) {
			if (p.getActiveStatus()) {
				slist.add(p.getPatID().toString() + ": " + p.getFullName().getFLName().toString());
			}
		}
		
		JComboBox<String> box = new JComboBox(slist.toArray());
		return box;
	}
	
	public static JComboBox<String> comboDoctorBox(SchedulerData data) {
		List<Doctor> dlist = data.getListDoc();
		
		List<String> slist = new ArrayList();
		
		for (Doctor d: dlist) {
			if (d.getActiveStatus()) {
				slist.add(d.getDocID().toString() + ": " + d.getFullName().getFLName().toString());
			}
		}
		
		JComboBox<String> box = new JComboBox(slist.toArray());
		return box;
	}
	
	public static JComboBox<String> comboYearBox() {
		List<String> years = new ArrayList<String>();
		for (Integer i = 1900; i < 2100;i++) {
			years.add(i.toString());
		}
		
		JComboBox<String> box = new JComboBox(years.toArray());
		return box;
	}
	
	public static JComboBox<String> comboNextYearBox() {
		List<String> years = new ArrayList<String>();
		for (Integer i = 2018; i < 2100;i++) {
			years.add(i.toString());
		}
		
		JComboBox<String> box = new JComboBox(years.toArray());
		return box;
	}
	
	public static JComboBox<String> comboMonthBox() {
		List<String> months = new ArrayList<String>();
		for (Integer i = 1; i < 13;i++) {
			months.add(i.toString());
		}
		
		JComboBox<String> box = new JComboBox(months.toArray());
		return box;
	}
	
	public static JComboBox<String> comboDayBox() {
		List<String> days = new ArrayList<String>();
		for (Integer i = 1; i < 32;i++) {
			days.add(i.toString());
		}
		
		JComboBox<String> box = new JComboBox(days.toArray());
		return box;
	}
	
	public static JComboBox<String> comboHourBox() {
		List<String> hours = new ArrayList<String>();
		for (Integer i = 0; i < 24;i++) {
			hours.add(i.toString());
		}
		
		JComboBox<String> box = new JComboBox(hours.toArray());
		return box;
	}
	
	public static JComboBox<String> comboMinuteBox() {
		List<String> minutes = new ArrayList<String>();
		for (Integer i = 0; i < 60;i++) {
			minutes.add(i.toString());
		}
		
		JComboBox<String> box = new JComboBox(minutes.toArray());
		return box;
	}
	
	public static JComboBox<MedicalSpecialty> comboSpecialtyBox() {
		JComboBox<MedicalSpecialty> box = new JComboBox(MedicalSpecialty.values());
		return box;
	}
	
	public static JComboBox<Visit<Integer,Integer>> comboVisitBox(SchedulerData data) {
		JComboBox<Visit<Integer,Integer>> box = new JComboBox<Visit<Integer,Integer>>
			((Visit<Integer,Integer>[]) data.getListVis().toArray());
		return box;
	}
	
	public static JList<String> listVisToJList(SchedulerData data) {
		data.getListPat().toArray();
		List<String> list = new ArrayList<String>();
		
		VisitUtils.sortVisListDate(data.getListVis());
		
		for (Visit<Integer,Integer> v: data.getListVis()) {
			if(VisitUtils.matchToDocID(v,data.getListDoc()).getActiveStatus() && 
					VisitUtils.matchToPatID(v,data.getListPat()).getActiveStatus()) {
			list.add(	DateUtil.dateToString(v.getDate(), new SimpleDateFormat("dd/MM/yyyy | HH:mm")) + " | Dr. " + 
						VisitUtils.matchToDocID(v,data.getListDoc()).getFullName().getLastName() + " | " +
						VisitUtils.matchToPatID(v,data.getListPat()).getFullName().getLastName());
			}
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		JList<String> jlist = new JList<String>(model);		
		return jlist;
	}
	
	public static JList<String> listPatToJList(SchedulerData data) {
		data.getListPat().toArray();
		List<String> list = new ArrayList<String>();
		
		for (Patient p: data.getListPat()) {
			if(p.getActiveStatus())
				list.add(p.getPatID().toString() + " : " + p.getFullName().getFLName());
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		JList<String> jlist = new JList<String>(model);		
		return jlist;
	}
	
	public static JList<String> listPatFullToJList(SchedulerData data) {
		data.getListPat().toArray();
		List<String> list = new ArrayList<String>();
		
		for (Patient p: data.getListPat()) {
			if(p.getActiveStatus())
				list.add(p.getPatID().toString() + ": " + p.getFullName().getFLName() + " : " 
						+ DateUtil.dateToString(p.getPersonalData().getDOB(),new SimpleDateFormat("dd-MM-yyyy")));
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		JList<String> jlist = new JList<String>(model);		
		return jlist;
	}
	
	public static JList<String> listDocToJList(SchedulerData data) {
		data.getListPat().toArray();
		List<String> list = new ArrayList<String>();
		
		for (Doctor d: data.getListDoc()) {
			if(d.getActiveStatus())
				list.add(d.getDocID().toString() + " : " + d.getFullName().getFLName());
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		JList<String> jlist = new JList<String>(model);		
		return jlist;
	}
	
	public static JList<String> listDocFullToJList(SchedulerData data) {
		data.getListDoc().toArray();
		List<String> list = new ArrayList<String>();
		
		for (Doctor d: data.getListDoc()) {
			if(d.getActiveStatus())
				list.add(d.getDocID().toString() + ": " + d.getFullName().getFLName() + " : " 
						+ DateUtil.dateToString(d.getPersonalData().getDOB(),new SimpleDateFormat("dd-MM-yyyy")));
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		JList<String> jlist = new JList<String>(model);		
		return jlist;
	}
	
	public static JSplitPane splitPaneHorizontalList(JList<String> list, JPanel panel) {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.add(list, JSplitPane.LEFT);
		splitPane.add(panel, JSplitPane.RIGHT);
		return splitPane;
	}
	
	public static SchedulerData updateData(SchedulerData data, String inputFile) 
			throws FileNotFoundException, IOException, XMLStreamException {
		data = SchedulerXMLReaderUtils.readSchedulingXML(inputFile);
		VisitUtils.sortVisListDate(data.getListVis());
		return data;
	}
	
	public static Integer getDocIDFromStringID(String strID, SchedulerData data) {
		Integer id = Integer.parseInt((strID.substring(0, strID.indexOf(":"))));
		for (Doctor d: data.getListDoc()) {
			if (d.getDocID() == id) return d.getDocID();
		}
		return null;
	}
	
	public static Integer getPatIDFromStringID(String strID, SchedulerData data) {
		Integer id = Integer.parseInt((strID.substring(0, strID.indexOf(":"))));
		for (Patient p: data.getListPat()) {
			if (p.getPatID() == id) return p.getPatID();
		}
		return null;
	}
	
	public static Doctor getDocFromStringID(String strID, SchedulerData data) {
		Integer id = Integer.parseInt((strID.substring(0, strID.indexOf(":"))));
		for (Doctor d: data.getListDoc()) {
			if (d.getDocID() == id) return d;
		}
		return null;
	}
	
	public static Patient getPatFromStringID(String strID, SchedulerData data) {
		Integer id = Integer.parseInt((strID.substring(0, strID.indexOf(":"))));
		for (Patient p: data.getListPat()) {
			if (p.getPatID() == id) return p;
		}
		return null;
	}
	
	public static Integer findVisitIndex(Visit<Integer,Integer> vis, SchedulerData data) {
		for (Visit<Integer,Integer> v: data.getListVis()) {
			if (v.equals(vis)) return data.getListVis().indexOf(v);
		}
		return null;
	}
	
	public static Integer findVisitIndex(String listForm, SchedulerData data) {
		VisitUtils.sortVisListDate(data.getListVis());
		
		for (Visit<Integer,Integer> v: data.getListVis()) {
			if(VisitUtils.matchToDocID(v,data.getListDoc()).getActiveStatus() && 
					VisitUtils.matchToPatID(v,data.getListPat()).getActiveStatus()) {
			String s =	DateUtil.dateToString(v.getDate(), new SimpleDateFormat("dd/MM/yyyy | HH:mm")) + " | Dr. " + 
						VisitUtils.matchToDocID(v,data.getListDoc()).getFullName().getLastName() + " | " +
						VisitUtils.matchToPatID(v,data.getListPat()).getFullName().getLastName();
			if (s.equals(listForm)) {
				return data.getListVis().indexOf(v);
			}
			}
		}
		return null;
	}

	public static JList<String> listRemDocFullToJList(SchedulerData data) {
		data.getListDoc().toArray();
		List<String> list = new ArrayList<String>();
		
		for (Doctor d: data.getListDoc()) {
			if(!d.getActiveStatus())
				list.add(d.getDocID().toString() + ": " + d.getFullName().getFLName() + " : " 
						+ DateUtil.dateToString(d.getPersonalData().getDOB(),new SimpleDateFormat("dd-MM-yyyy")));
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		JList<String> jlist = new JList<String>(model);		
		return jlist;
	}

	public static JList<String> listRemPatFullToJList(SchedulerData data) {
		data.getListPat().toArray();
		List<String> list = new ArrayList<String>();
		
		for (Patient p: data.getListPat()) {
			if(!p.getActiveStatus())
				list.add(p.getPatID().toString() + ": " + p.getFullName().getFLName() + " : " 
						+ DateUtil.dateToString(p.getPersonalData().getDOB(),new SimpleDateFormat("dd-MM-yyyy")));
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		JList<String> jlist = new JList<String>(model);		
		return jlist;
	}
}
