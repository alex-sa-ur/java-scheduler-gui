package edu.miami.cis324.hw4.alejandrosanchez;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.event.*;
import javax.xml.stream.XMLStreamException;

/**
 * @author alejandrosanchez
 *
 */

public class SchedulingXMLGUIAddPatient {
	private final static String PATIENT_GUI = "Patient";
	private JButton				patAddCfmBtn, patAddCclBtn,
								patAddAddCfmBtn, patAddAddCclBtn;
	private JTextField			patAddTxFName, patAddTxMName, patAddTxLName, patAddTxSSN;
	
	private JComboBox<String> 	patAddTxDateDay, patAddTxDateMonth, patAddTxDateYear;
	
	private JSplitPane pane;
	
	private JDialog dialog = new JDialog();
	
	public void confirmAddPatientGUI(ActionListener listener, WindowListener wlistener) {
		dialog.getContentPane().removeAll();
		dialog.addWindowListener(wlistener);
		dialog.setSize(500, 300);
		dialog.setVisible(true);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Confirming creation of " + PATIENT_GUI + ":", JLabel.CENTER);
		panel.add(label);
		label = new JLabel("First Name: " + patAddTxFName.getText(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Middle Name: " + patAddTxMName.getText(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Last Name: " + patAddTxLName.getText(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("SSN: " + patAddTxSSN.getText(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("DOB: " + 	patAddTxDateDay.getSelectedItem().toString() + "-" + 
										patAddTxDateMonth.getSelectedItem().toString() + "-" +
										patAddTxDateYear.getSelectedItem().toString(), JLabel.CENTER);
		panel.add(label);
		patAddAddCfmBtn = new JButton("Confirm");
		patAddAddCfmBtn.addActionListener(listener);
		panel.add(patAddAddCfmBtn, JPanel.CENTER_ALIGNMENT);
		patAddAddCclBtn = new JButton("Cancel");
		patAddAddCclBtn.addActionListener(listener);
		panel.add(patAddAddCclBtn, JPanel.CENTER_ALIGNMENT);
		dialog.add(panel);
		dialog.validate();
	}
	
	private JPanel addPatientGUI(ActionListener listener){
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		patAddTxFName = new JTextField();
		patAddTxMName = new JTextField();
		patAddTxLName = new JTextField();
		patAddTxSSN = new JTextField();
		patAddTxDateYear = SchedulingXMLGUIUtils.comboYearBox();
		patAddTxDateMonth = SchedulingXMLGUIUtils.comboMonthBox();
		patAddTxDateDay = SchedulingXMLGUIUtils.comboDayBox();
		
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Add " + PATIENT_GUI));
		panel.add(new JLabel("-----------------------------"));
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(PATIENT_GUI + " First Name:"));
		subPanel.add(patAddTxFName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(PATIENT_GUI + " Middle Name:"));
		subPanel.add(patAddTxMName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(PATIENT_GUI + " Last Name:"));
		subPanel.add(patAddTxLName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(PATIENT_GUI + " SSN:"));
		subPanel.add(patAddTxSSN);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(PATIENT_GUI + " DOB:"));
		subPanel.add(patAddTxDateDay);
		subPanel.add(patAddTxDateMonth);
		subPanel.add(patAddTxDateYear);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		patAddCclBtn = new JButton("Cancel");
		patAddCfmBtn = new JButton("Confirm");
		patAddCclBtn.addActionListener(listener);
		patAddCfmBtn.addActionListener(listener);
		subPanel.add(patAddCclBtn);
		subPanel.add(patAddCfmBtn);
		panel.add(subPanel);
		
		return panel;
	}
	
	SchedulingXMLGUIAddPatient(){}
	
	SchedulingXMLGUIAddPatient(SchedulerData data, String inputFile, ActionListener listener) 
			throws FileNotFoundException, IOException, XMLStreamException{
		data = SchedulingXMLGUIUtils.updateData(data, inputFile);
		pane = SchedulingXMLGUIUtils.splitPaneHorizontalList(SchedulingXMLGUIUtils.listPatToJList(data), addPatientGUI(listener));
	}
	
	public JSplitPane getPane() {
		return pane;
	}
	
	public JButton getCancelButton() {
		return patAddCclBtn;
	}
	
	public JButton getConfirmButton() {
		return patAddCfmBtn;
	}
	
	public JDialog getDialog() {
		return dialog;
	}
	
	public JButton getCancelCancelButton() {
		return patAddAddCclBtn;
	}
	
	public JButton getConfirmConfirmButton() {
		return patAddAddCfmBtn;
	}
	
	public void addPatient(SchedulerData data) {
		Name name = new Name(patAddTxFName.getText(), patAddTxLName.getText(), patAddTxMName.getText());
		String dateString = patAddTxDateYear.getSelectedItem().toString() + "-" +
							patAddTxDateMonth.getSelectedItem().toString() + "-" +
							patAddTxDateDay.getSelectedItem().toString() + ", " + 
							"00" + ":" + "00";
		PersonalData personalData = new PersonalData(patAddTxSSN.getText(), DateUtil.getFromString(dateString));
		Patient p = new PatientImpl(personalData, name, true);
		data.addPatient(p);
	} 
}
