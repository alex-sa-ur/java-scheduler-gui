package edu.miami.cis324.hw4.alejandrosanchez;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

/**
 * @author alejandrosanchez
 *
 */

public class SchedulingXMLGUIVisitList {
	private static JComboBox<String> 	filterBox;
	private static JList<String> 		jlist;
	private static JButton				updateButton, cancelButton;
	private static JPanel				panel;
	private static JPanel 				subPanel;
	private static String				selection;
	private static String				menuType;
	
	public static JPanel viewAll(SchedulerData data, ActionListener listener) {
		panel = new JPanel();
		subPanel = new JPanel();
		cancelButton = new JButton("Cancel");
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel,BoxLayout.X_AXIS));
		
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
		
		jlist = new JList<String>(model);
		
		cancelButton.addActionListener(listener);
		subPanel.add(cancelButton);
		
		panel.add(subPanel);
		panel.add(jlist);
		return panel;
	}
	
	public static JPanel viewUp(SchedulerData data, ActionListener listener) {
		panel = new JPanel();
		subPanel = new JPanel();
		cancelButton = new JButton("Cancel");
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel,BoxLayout.X_AXIS));
		
		List<String> list = new ArrayList<String>();
		
		VisitUtils.sortVisListDate(data.getListVis());
		
		for (Visit<Integer,Integer> v: data.getListVis()) {
			if(VisitUtils.matchToDocID(v,data.getListDoc()).getActiveStatus() && 
					VisitUtils.matchToPatID(v,data.getListPat()).getActiveStatus() && 
					v.getDate().after(new Date())) {
			list.add(	DateUtil.dateToString(v.getDate(), new SimpleDateFormat("dd/MM/yyyy | HH:mm")) + " | Dr. " + 
						VisitUtils.matchToDocID(v,data.getListDoc()).getFullName().getLastName() + " | " +
						VisitUtils.matchToPatID(v,data.getListPat()).getFullName().getLastName());
			}
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		jlist = new JList<String>(model);
		
		cancelButton.addActionListener(listener);
		subPanel.add(cancelButton);
		
		panel.add(subPanel);
		panel.add(jlist);
		return panel;
	}
	
	public static JPanel viewAllPat(SchedulerData data, ActionListener listener) {
		menuType = "ViewAllPat";
		panel = new JPanel();
		subPanel = new JPanel();
		filterBox = SchedulingXMLGUIUtils.comboPatientBox(data);
		selection = (selection != null? selection: filterBox.getSelectedItem().toString());
		cancelButton = new JButton("Cancel");
		updateButton = new JButton("Update");
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel,BoxLayout.X_AXIS));
		
		List<String> list = new ArrayList<String>();
		
		VisitUtils.sortVisListDate(data.getListVis());
		
		for (Visit<Integer,Integer> v: data.getListVis()) {
			if(VisitUtils.matchToDocID(v,data.getListDoc()).getActiveStatus() && 
					VisitUtils.matchToPatID(v,data.getListPat()).getActiveStatus() &&
					v.getVisitor().equals((selection!=null? 
							SchedulingXMLGUIUtils.getPatFromStringID(selection, data).getPatID():
							SchedulingXMLGUIUtils.getPatFromStringID(filterBox.getSelectedItem().toString(), data).getPatID()))) {
			list.add(	DateUtil.dateToString(v.getDate(), new SimpleDateFormat("dd/MM/yyyy | HH:mm")) + " | Dr. " + 
						VisitUtils.matchToDocID(v,data.getListDoc()).getFullName().getLastName() + " | " +
						VisitUtils.matchToPatID(v,data.getListPat()).getFullName().getLastName());
			}
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		jlist = new JList<String>(model);
		
		updateButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		subPanel.add(filterBox);
		subPanel.add(updateButton);
		subPanel.add(cancelButton);
		
		panel.add(subPanel);
		panel.add(jlist);
		return panel;
	}
	
	public static JPanel viewAllDoc(SchedulerData data, ActionListener listener) {
		menuType = "ViewAllDoc";
		panel = new JPanel();
		subPanel = new JPanel();
		filterBox = SchedulingXMLGUIUtils.comboDoctorBox(data);
		selection = (selection != null? selection: filterBox.getSelectedItem().toString());
		cancelButton = new JButton("Cancel");
		updateButton = new JButton("Update");
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel,BoxLayout.X_AXIS));
		
		List<String> list = new ArrayList<String>();
		
		VisitUtils.sortVisListDate(data.getListVis());
		
		for (Visit<Integer,Integer> v: data.getListVis()) {
			if(VisitUtils.matchToDocID(v,data.getListDoc()).getActiveStatus() && 
					VisitUtils.matchToPatID(v,data.getListPat()).getActiveStatus() &&
					v.getVisitor().equals((selection!=null? 
							SchedulingXMLGUIUtils.getDocFromStringID(selection, data).getDocID():
							SchedulingXMLGUIUtils.getDocFromStringID(filterBox.getSelectedItem().toString(), data).getDocID()))) {
			list.add(	DateUtil.dateToString(v.getDate(), new SimpleDateFormat("dd/MM/yyyy | HH:mm")) + " | Dr. " + 
						VisitUtils.matchToDocID(v,data.getListDoc()).getFullName().getLastName() + " | " +
						VisitUtils.matchToPatID(v,data.getListPat()).getFullName().getLastName());
			}
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		jlist = new JList<String>(model);
		
		updateButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		subPanel.add(filterBox);
		subPanel.add(updateButton);
		subPanel.add(cancelButton);
		
		panel.add(subPanel);
		panel.add(jlist);
		return panel;
	}
	
	public static JPanel viewUpPat(SchedulerData data, ActionListener listener) {
		menuType = "ViewUpPat";
		panel = new JPanel();
		subPanel = new JPanel();
		filterBox = SchedulingXMLGUIUtils.comboPatientBox(data);
		selection = (selection != null? selection: filterBox.getSelectedItem().toString());
		cancelButton = new JButton("Cancel");
		updateButton = new JButton("Update");
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel,BoxLayout.X_AXIS));
		
		List<String> list = new ArrayList<String>();
		
		VisitUtils.sortVisListDate(data.getListVis());
		
		for (Visit<Integer,Integer> v: data.getListVis()) {
			if(VisitUtils.matchToDocID(v,data.getListDoc()).getActiveStatus() && 
					VisitUtils.matchToPatID(v,data.getListPat()).getActiveStatus() && 
					v.getDate().after(new Date()) &&
					v.getVisitor().equals((selection!=null? 
							SchedulingXMLGUIUtils.getPatFromStringID(selection, data).getPatID():
							SchedulingXMLGUIUtils.getPatFromStringID(filterBox.getSelectedItem().toString(), data).getPatID()))) {
			list.add(	DateUtil.dateToString(v.getDate(), new SimpleDateFormat("dd/MM/yyyy | HH:mm")) + " | Dr. " + 
						VisitUtils.matchToDocID(v,data.getListDoc()).getFullName().getLastName() + " | " +
						VisitUtils.matchToPatID(v,data.getListPat()).getFullName().getLastName());
			}
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		jlist = new JList<String>(model);
		
		updateButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		subPanel.add(filterBox);
		subPanel.add(updateButton);
		subPanel.add(cancelButton);
		
		panel.add(subPanel);
		panel.add(jlist);
		return panel;
	}
	
	public static JPanel viewUpDoc(SchedulerData data, ActionListener listener) {
		menuType = "ViewUpDoc";
		panel = new JPanel();
		subPanel = new JPanel();
		filterBox = SchedulingXMLGUIUtils.comboDoctorBox(data);
		selection = (selection != null? selection: filterBox.getSelectedItem().toString());
		cancelButton = new JButton("Cancel");
		updateButton = new JButton("Update");
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel,BoxLayout.X_AXIS));
		
		List<String> list = new ArrayList<String>();
		
		VisitUtils.sortVisListDate(data.getListVis());
		
		for (Visit<Integer,Integer> v: data.getListVis()) {
			if(VisitUtils.matchToDocID(v,data.getListDoc()).getActiveStatus() && 
					VisitUtils.matchToPatID(v,data.getListPat()).getActiveStatus() && 
					v.getDate().after(new Date()) &&
					v.getVisitor().equals((selection!=null? 
							SchedulingXMLGUIUtils.getDocFromStringID(selection, data).getDocID():
							SchedulingXMLGUIUtils.getDocFromStringID(filterBox.getSelectedItem().toString(), data).getDocID()))) {
			list.add(	DateUtil.dateToString(v.getDate(), new SimpleDateFormat("dd/MM/yyyy | HH:mm")) + " | Dr. " + 
						VisitUtils.matchToDocID(v,data.getListDoc()).getFullName().getLastName() + " | " +
						VisitUtils.matchToPatID(v,data.getListPat()).getFullName().getLastName());
			}
		}
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < list.size(); i++) {
			model.addElement(list.get(i));
		}
		
		jlist = new JList<String>(model);
		
		updateButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		subPanel.add(filterBox);
		subPanel.add(updateButton);
		subPanel.add(cancelButton);
		
		panel.add(subPanel);
		panel.add(jlist);
		return panel;
	}
	
	public static JButton getCancelButton() {
		return cancelButton;
	}
	
	public static JButton getConfirmButton() {
		return updateButton;
	}
	
	public static JPanel updateView(SchedulerData data, ActionListener listener) {
		selection = filterBox.getSelectedItem().toString();
		
		if (menuType.equals("ViewAllPat")) {
			return viewAllPat(data, listener);
		}
		
		else if (menuType.equals("ViewAllDoc")) {
			return viewAllDoc(data, listener);
		}
		
		else if (menuType.equals("ViewUpPat")) {
			return viewUpPat(data, listener);
		}
		
		else if (menuType.equals("ViewUpDoc")) {
			return viewUpDoc(data, listener);
		}
		
		return null;
	}
}
