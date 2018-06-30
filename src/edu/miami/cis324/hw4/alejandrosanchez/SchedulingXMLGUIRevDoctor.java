package edu.miami.cis324.hw4.alejandrosanchez;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.*;
import javax.xml.stream.XMLStreamException;

/**
 * @author alejandrosanchez
 *
 */

public class SchedulingXMLGUIRevDoctor {
	private final static String DOCTOR_GUI = "Doctor";
	private JButton				docRevCfmBtn, docRevCclBtn,
								docRevCfmCfmBtn, docRevCfmCclBtn;
	private JTextField			docRevTxFName, docRevTxMName, docRevTxLName, docRevTxSSN;
	
	private JComboBox<String> 	docRevTxDateDay, docRevTxDateMonth, docRevTxDateYear;
	
	private JComboBox<MedicalSpecialty>	docRevTxSpec;
	
	private JList<String> 		list;
	
	private JPanel 				pane;
	
	private JDialog dialog = new JDialog();
	
	public void editViewDoctorGUI(ActionListener listener, WindowListener wlistener, Doctor doc) {
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		Calendar cal = Calendar.getInstance();
		cal.setTime(doc.getPersonalData().getDOB());
		
		dialog.getContentPane().removeAll();
		dialog.addWindowListener(wlistener);
		dialog.setSize(500, 300);
		dialog.setVisible(true);
		
		docRevTxFName = new JTextField(doc.getFullName().getFirstName());
		docRevTxMName = new JTextField(doc.getFullName().getMidName());
		docRevTxLName = new JTextField(doc.getFullName().getLastName());
		docRevTxSpec = SchedulingXMLGUIUtils.comboSpecialtyBox();
		docRevTxSpec.setSelectedItem(doc.getMedSpec());
		docRevTxSSN = new JTextField(doc.getPersonalData().getSSN());
		docRevTxDateYear = SchedulingXMLGUIUtils.comboYearBox();
		docRevTxDateYear.setSelectedItem(String.valueOf(cal.get(Calendar.YEAR)));
		docRevTxDateMonth = SchedulingXMLGUIUtils.comboMonthBox();
		docRevTxDateMonth.setSelectedItem(String.valueOf(cal.get(Calendar.MONTH)));
		docRevTxDateDay = SchedulingXMLGUIUtils.comboDayBox();
		docRevTxDateDay.setSelectedItem(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Review " + DOCTOR_GUI));
		panel.add(new JLabel("-----------------------------"));
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " First Name:"));
		subPanel.add(docRevTxFName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " Middle Name:"));
		subPanel.add(docRevTxMName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " Last Name:"));
		subPanel.add(docRevTxLName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " Medical Specialty:"));
		subPanel.add(docRevTxSpec);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " SSN:"));
		subPanel.add(docRevTxSSN);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(DOCTOR_GUI + " DOB:"));
		subPanel.add(docRevTxDateDay);
		subPanel.add(docRevTxDateMonth);
		subPanel.add(docRevTxDateYear);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		docRevCfmCclBtn = new JButton("Cancel");
		docRevCfmCfmBtn = new JButton("Confirm");
		docRevCfmCclBtn.addActionListener(listener);
		docRevCfmCfmBtn.addActionListener(listener);
		subPanel.add(docRevCfmCclBtn);
		subPanel.add(docRevCfmCfmBtn);
		panel.add(subPanel);
		
		dialog.add(panel);
		dialog.validate();
	}
	
	private JPanel revDoctorGUI(SchedulerData data, ActionListener listener){
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		list = SchedulingXMLGUIUtils.listDocFullToJList(data);
		
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Review " + DOCTOR_GUI));
		panel.add(new JLabel("-----------------------------"));
		panel.add(list);
		
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		docRevCclBtn = new JButton("Cancel");
		docRevCfmBtn = new JButton("Confirm");
		docRevCclBtn.addActionListener(listener);
		docRevCfmBtn.addActionListener(listener);
		subPanel.add(docRevCclBtn);
		subPanel.add(docRevCfmBtn);
		panel.add(subPanel);
		
		return panel;
	}
	
	SchedulingXMLGUIRevDoctor(){}
	
	SchedulingXMLGUIRevDoctor(SchedulerData data, String inputFile, ActionListener listener) 
			throws FileNotFoundException, IOException, XMLStreamException{
		data = SchedulingXMLGUIUtils.updateData(data, inputFile);
		pane = revDoctorGUI(data, listener);
	}
	
	public void revDoctor(SchedulerData data) {
		SchedulingXMLGUIUtils.getDocFromStringID(list.getSelectedValue(),data).getFullName().setFName(
				docRevTxFName.getText().toString());
		SchedulingXMLGUIUtils.getDocFromStringID(list.getSelectedValue(),data).getFullName().setMName(
				docRevTxMName.getText().toString());
		SchedulingXMLGUIUtils.getDocFromStringID(list.getSelectedValue(),data).getFullName().setLName(
				docRevTxLName.getText().toString());
		SchedulingXMLGUIUtils.getDocFromStringID(list.getSelectedValue(),data).getPersonalData().setSSN(
				docRevTxSSN.getText().toString());
		
		String dateString = docRevTxDateYear.getSelectedItem().toString() + "-" +
				docRevTxDateMonth.getSelectedItem().toString() + "-" +
				docRevTxDateDay.getSelectedItem().toString() + ", " + 
				"00" + ":" + "00";
		
		SchedulingXMLGUIUtils.getDocFromStringID(
				list.getSelectedValue(),data).getPersonalData().setDOB(DateUtil.getFromString(dateString));
		
		SchedulingXMLGUIUtils.getDocFromStringID(
				list.getSelectedValue(),data).setMedSpec(docRevTxSpec.getSelectedItem().toString());
	}
	
	public JPanel getPane() {
		return pane;
	}
	
	public JList<String> getList() {
		return list;
	}
	
	public JButton getCancelButton() {
		return docRevCclBtn;
	}
	
	public JButton getConfirmButton() {
		return docRevCfmBtn;
	}
	
	public JDialog getDialog() {
		return dialog;
	}
	
	public JButton getCancelCancelButton() {
		return docRevCfmCclBtn;
	}
	
	public JButton getConfirmConfirmButton() {
		return docRevCfmCfmBtn;
	}
}
