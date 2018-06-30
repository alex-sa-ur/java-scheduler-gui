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

public class SchedulingXMLGUIRecPatient {
	private final static String PATIENT_GUI = "Patient";
	private JButton				patRecCfmBtn, patRecCclBtn,
								patRecCfmCfmBtn, patRecCfmCclBtn;
	
	private JList<String> 		list;
	
	private JPanel 				pane;
	
	private JDialog dialog = new JDialog();
	
	public void recCfmPatientGUI(ActionListener listener, WindowListener wlistener, Patient pat) {
		dialog.getContentPane().removeAll();
		dialog.addWindowListener(wlistener);
		dialog.setSize(500, 300);
		dialog.setVisible(true);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Confirming recovery of " + PATIENT_GUI + ":", JLabel.CENTER);
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
		patRecCfmCfmBtn = new JButton("Confirm");
		patRecCfmCfmBtn.addActionListener(listener);
		panel.add(patRecCfmCfmBtn, JPanel.CENTER_ALIGNMENT);
		patRecCfmCclBtn = new JButton("Cancel");
		patRecCfmCclBtn.addActionListener(listener);
		panel.add(patRecCfmCclBtn, JPanel.CENTER_ALIGNMENT);
		dialog.add(panel);
		dialog.validate();
	}
	
	private JPanel remPatientGUI(SchedulerData data, ActionListener listener){
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		list = SchedulingXMLGUIUtils.listRemPatFullToJList(data);
		
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Recover " + PATIENT_GUI));
		panel.add(new JLabel("-----------------------------"));
		panel.add(list);
		
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		patRecCclBtn = new JButton("Cancel");
		patRecCfmBtn = new JButton("Confirm");
		patRecCclBtn.addActionListener(listener);
		patRecCfmBtn.addActionListener(listener);
		subPanel.add(patRecCclBtn);
		subPanel.add(patRecCfmBtn);
		panel.add(subPanel);
		
		return panel;
	}
	
	SchedulingXMLGUIRecPatient(){}
	
	SchedulingXMLGUIRecPatient(SchedulerData data, String inputFile, ActionListener listener) 
			throws FileNotFoundException, IOException, XMLStreamException{
		data = SchedulingXMLGUIUtils.updateData(data, inputFile);
		pane = remPatientGUI(data, listener);
	}
	
	public SchedulerData recPatient(SchedulerData data, Patient pat) {
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
				p.setActiveStatus(true);
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
		return patRecCclBtn;
	}
	
	public JButton getConfirmButton() {
		return patRecCfmBtn;
	}
	
	public JDialog getDialog() {
		return dialog;
	}
	
	public JButton getCancelCancelButton() {
		return patRecCfmCclBtn;
	}
	
	public JButton getConfirmConfirmButton() {
		return patRecCfmCfmBtn;
	}
}
