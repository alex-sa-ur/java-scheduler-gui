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

public class SchedulingXMLGUIAddDoctor {
	private final static String 		DOCTOR_GUI = "Doctor";
	private JButton						docAddCfmBtn, docAddCclBtn,
										docAddAddCfmBtn, docAddAddCclBtn;
	private JTextField					docAddTxFName,docAddTxMName, docAddTxLName, docAddTxSSN;

	private JComboBox<String> 			docAddTxDateDay, docAddTxDateMonth, docAddTxDateYear;
	
	private JComboBox<MedicalSpecialty>	docAddTxSpec;
	
	private JSplitPane pane;
	
	private JDialog dialog = new JDialog();
	
	public void confirmAddDoctorGUI(ActionListener listener, WindowListener wlistener) {
		dialog.getContentPane().removeAll();
		dialog.addWindowListener(wlistener);
		dialog.setSize(500, 300);
		dialog.setVisible(true);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Confirming creation of " + DOCTOR_GUI + ":", JLabel.CENTER);
		panel.add(label);
		label = new JLabel("First Name: " + docAddTxFName.getText(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Middle Name: " + docAddTxMName.getText(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Last Name: " + docAddTxLName.getText(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Medical Specialty: " + docAddTxSpec.getSelectedItem(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("SSN: " + docAddTxSSN.getText(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("DOB: " + 	docAddTxDateDay.getSelectedItem().toString() + "-" + 
										docAddTxDateMonth.getSelectedItem().toString() + "-" +
										docAddTxDateYear.getSelectedItem().toString(), JLabel.CENTER);
		panel.add(label);
		docAddAddCfmBtn = new JButton("Confirm");
		docAddAddCfmBtn.addActionListener(listener);
		panel.add(docAddAddCfmBtn, JPanel.CENTER_ALIGNMENT);
		docAddAddCclBtn = new JButton("Cancel");
		docAddAddCclBtn.addActionListener(listener);
		panel.add(docAddAddCclBtn, JPanel.CENTER_ALIGNMENT);
		dialog.add(panel);
		dialog.validate();
	}
	
	private JPanel addDoctorGUI(ActionListener listener){
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		docAddTxFName = new JTextField();
		docAddTxMName = new JTextField();
		docAddTxLName = new JTextField();
		docAddTxSpec = SchedulingXMLGUIUtils.comboSpecialtyBox();
		docAddTxSSN = new JTextField();
		docAddTxDateYear = SchedulingXMLGUIUtils.comboYearBox();
		docAddTxDateMonth = SchedulingXMLGUIUtils.comboMonthBox();
		docAddTxDateDay = SchedulingXMLGUIUtils.comboDayBox();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Add " + DOCTOR_GUI));
		panel.add(new JLabel("-----------------------------"));
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " First Name:"));
		subPanel.add(docAddTxFName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " Middle Name:"));
		subPanel.add(docAddTxMName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " Last Name:"));
		subPanel.add(docAddTxLName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " Medical Specialty:"));
		subPanel.add(docAddTxSpec);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " SSN:"));
		subPanel.add(docAddTxSSN);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " DOB:"));
		subPanel.add(docAddTxDateDay);
		subPanel.add(docAddTxDateMonth);
		subPanel.add(docAddTxDateYear);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		docAddCclBtn = new JButton("Cancel");
		docAddCfmBtn = new JButton("Confirm");
		docAddCclBtn.addActionListener(listener);
		docAddCfmBtn.addActionListener(listener);
		subPanel.add(docAddCclBtn);
		subPanel.add(docAddCfmBtn);
		panel.add(subPanel);
		
		return panel;
	}
	
	SchedulingXMLGUIAddDoctor(){}
	
	SchedulingXMLGUIAddDoctor(SchedulerData data, String inputFile, ActionListener listener) 
			throws FileNotFoundException, IOException, XMLStreamException{
		data = SchedulingXMLGUIUtils.updateData(data, inputFile);
		pane = SchedulingXMLGUIUtils.splitPaneHorizontalList(SchedulingXMLGUIUtils.listDocToJList(data), addDoctorGUI(listener));
	}
	
	public JSplitPane getPane() {
		return pane;
	}
	
	public JButton getCancelButton() {
		return docAddCclBtn;
	}
	
	public JButton getConfirmButton() {
		return docAddCfmBtn;
	}
	
	public JDialog getDialog() {
		return dialog;
	}
	
	public JButton getCancelCancelButton() {
		return docAddAddCclBtn;
	}
	
	public JButton getConfirmConfirmButton() {
		return docAddAddCfmBtn;
	}
	
	public void addDoctor(SchedulerData data) {
		Name name = new Name(docAddTxFName.getText(), docAddTxLName.getText(), docAddTxMName.getText());
		String dateString = docAddTxDateYear.getSelectedItem().toString() + "-" +
							docAddTxDateMonth.getSelectedItem().toString() + "-" +
							docAddTxDateDay.getSelectedItem().toString() + ", " + 
							"00" + ":" + "00";
		PersonalData personalData = new PersonalData(docAddTxSSN.getText(), DateUtil.getFromString(dateString));
		Doctor d = new DoctorImpl(personalData, name, true, docAddTxSpec.getSelectedItem().toString());
		data.addDoctor(d);
	}
	
}
