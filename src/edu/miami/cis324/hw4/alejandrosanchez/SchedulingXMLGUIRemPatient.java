package edu.miami.cis324.hw4.alejandrosanchez;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.xml.stream.XMLStreamException;

/**
 * @author alejandrosanchez
 *
 */

public class SchedulingXMLGUIRemPatient {
	private final static String PATIENT_GUI = "Patient";
	private JButton				patRemCfmBtn, patRemCclBtn,
								patRemCfmCfmBtn, patRemCfmCclBtn;
	
	private JList<String> 		list;
	
	private JPanel 				pane;
	
	private JDialog dialog = new JDialog();
	
	public void remCfmPatientGUI(ActionListener listener, WindowListener wlistener, Patient pat) {
		dialog.getContentPane().removeAll();
		dialog.addWindowListener(wlistener);
		dialog.setSize(500, 300);
		dialog.setVisible(true);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Confirming deletion of " + PATIENT_GUI + ":", JLabel.CENTER);
		panel.add(label);
		label = new JLabel("First Name: " + pat.getFullName().getFirstName(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Middle Name: " + pat.getFullName().getMidName(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Last Name: " + pat.getFullName().getLastName(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("SSN: " + pat.getPersonalData().getSSN(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("DOB: " + 	DateUtil.dateToString(pat.getPersonalData().getDOB(),new SimpleDateFormat("dd-MM-yyyy")),
				JLabel.CENTER);
		panel.add(label);
		patRemCfmCfmBtn = new JButton("Confirm");
		patRemCfmCfmBtn.addActionListener(listener);
		panel.add(patRemCfmCfmBtn, JPanel.CENTER_ALIGNMENT);
		patRemCfmCclBtn = new JButton("Cancel");
		patRemCfmCclBtn.addActionListener(listener);
		panel.add(patRemCfmCclBtn, JPanel.CENTER_ALIGNMENT);
		dialog.add(panel);
		dialog.validate();
	}
	
	private JPanel remPatientGUI(SchedulerData data, ActionListener listener){
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		list = SchedulingXMLGUIUtils.listPatFullToJList(data);
		
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Remove " + PATIENT_GUI));
		panel.add(new JLabel("-----------------------------"));
		panel.add(list);
		
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		patRemCclBtn = new JButton("Cancel");
		patRemCfmBtn = new JButton("Confirm");
		patRemCclBtn.addActionListener(listener);
		patRemCfmBtn.addActionListener(listener);
		subPanel.add(patRemCclBtn);
		subPanel.add(patRemCfmBtn);
		panel.add(subPanel);
		
		return panel;
	}
	
	SchedulingXMLGUIRemPatient(){}
	
	SchedulingXMLGUIRemPatient(SchedulerData data, String inputFile, ActionListener listener) 
			throws FileNotFoundException, IOException, XMLStreamException{
		data = SchedulingXMLGUIUtils.updateData(data, inputFile);
		pane = remPatientGUI(data, listener);
	}
	
	public SchedulerData remPatient(SchedulerData data, Patient pat) {
		SchedulerData dataNew = new SchedulerData();
		
		for (Doctor d: data.getListDoc()) {
			dataNew.addDoctor(d);
		}
		
		for (Visit<Integer,Integer> v: data.getListVis()) {
			dataNew.addVisit(v);
		}
		
		for (Patient p: data.getListPat()) {
			if (!p.equals(pat)) {
				dataNew.addPatient(p);
			} else {
				p.setActiveStatus(false);
				dataNew.addPatient(p);
			}
		}
		
		return dataNew;
	}
	
	public JPanel getPane() {
		return pane;
	}
	
	public JList<String> getList() {
		return list;
	}
	
	public JButton getCancelButton() {
		return patRemCclBtn;
	}
	
	public JButton getConfirmButton() {
		return patRemCfmBtn;
	}
	
	public JDialog getDialog() {
		return dialog;
	}
	
	public JButton getCancelCancelButton() {
		return patRemCfmCclBtn;
	}
	
	public JButton getConfirmConfirmButton() {
		return patRemCfmCfmBtn;
	}
}
