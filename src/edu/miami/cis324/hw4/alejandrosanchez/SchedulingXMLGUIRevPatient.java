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

public class SchedulingXMLGUIRevPatient {
	private final static String PATIENT_GUI = "Patient";
	private JButton				patRevCfmBtn, patRevCclBtn,
								patRevCfmCfmBtn, patRevCfmCclBtn;
	private JTextField			patRevTxFName, patRevTxMName, patRevTxLName, patRevTxSSN;
	
	private JComboBox<String> 	patRevTxDateDay, patRevTxDateMonth, patRevTxDateYear;
	
	private JList<String> 		list;
	
	private JPanel 				pane;
	
	private JDialog dialog = new JDialog();
	
	public void editViewPatientGUI(ActionListener listener, WindowListener wlistener, Patient pat) {
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		Calendar cal = Calendar.getInstance();
		cal.setTime(pat.getPersonalData().getDOB());
		
		dialog.getContentPane().removeAll();
		dialog.addWindowListener(wlistener);
		dialog.setSize(500, 300);
		dialog.setVisible(true);
		
		patRevTxFName = new JTextField(pat.getFullName().getFirstName());
		patRevTxMName = new JTextField(pat.getFullName().getMidName());
		patRevTxLName = new JTextField(pat.getFullName().getLastName());
		patRevTxSSN = new JTextField(pat.getPersonalData().getSSN());
		patRevTxDateYear = SchedulingXMLGUIUtils.comboYearBox();
		patRevTxDateYear.setSelectedItem(String.valueOf(cal.get(Calendar.YEAR)));
		patRevTxDateMonth = SchedulingXMLGUIUtils.comboMonthBox();
		patRevTxDateMonth.setSelectedItem(String.valueOf(cal.get(Calendar.MONTH)));
		patRevTxDateDay = SchedulingXMLGUIUtils.comboDayBox();
		patRevTxDateDay.setSelectedItem(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Review " + PATIENT_GUI));
		panel.add(new JLabel("-----------------------------"));
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(PATIENT_GUI + " First Name:"));
		subPanel.add(patRevTxFName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(PATIENT_GUI + " Middle Name:"));
		subPanel.add(patRevTxMName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(PATIENT_GUI + " Last Name:"));
		subPanel.add(patRevTxLName);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(PATIENT_GUI + " SSN:"));
		subPanel.add(patRevTxSSN);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(PATIENT_GUI + " DOB:"));
		subPanel.add(patRevTxDateDay);
		subPanel.add(patRevTxDateMonth);
		subPanel.add(patRevTxDateYear);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		patRevCfmCclBtn = new JButton("Cancel");
		patRevCfmCfmBtn = new JButton("Confirm");
		patRevCfmCclBtn.addActionListener(listener);
		patRevCfmCfmBtn.addActionListener(listener);
		subPanel.add(patRevCfmCclBtn);
		subPanel.add(patRevCfmCfmBtn);
		panel.add(subPanel);
		
		dialog.add(panel);
		dialog.validate();
	}
	
	private JPanel revPatientGUI(SchedulerData data, ActionListener listener){
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		list = SchedulingXMLGUIUtils.listPatFullToJList(data);
		
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Review " + PATIENT_GUI));
		panel.add(new JLabel("-----------------------------"));
		panel.add(list);
		
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		patRevCclBtn = new JButton("Cancel");
		patRevCfmBtn = new JButton("Confirm");
		patRevCclBtn.addActionListener(listener);
		patRevCfmBtn.addActionListener(listener);
		subPanel.add(patRevCclBtn);
		subPanel.add(patRevCfmBtn);
		panel.add(subPanel);
		
		return panel;
	}
	
	SchedulingXMLGUIRevPatient(){}
	
	SchedulingXMLGUIRevPatient(SchedulerData data, String inputFile, ActionListener listener) 
			throws FileNotFoundException, IOException, XMLStreamException{
		data = SchedulingXMLGUIUtils.updateData(data, inputFile);
		pane = revPatientGUI(data, listener);
	}
	
	public void revPatient(SchedulerData data) {
		SchedulingXMLGUIUtils.getPatFromStringID(list.getSelectedValue(),data).getFullName().setFName(
				patRevTxFName.getText().toString());
		SchedulingXMLGUIUtils.getPatFromStringID(list.getSelectedValue(),data).getFullName().setMName(
				patRevTxMName.getText().toString());
		SchedulingXMLGUIUtils.getPatFromStringID(list.getSelectedValue(),data).getFullName().setLName(
				patRevTxLName.getText().toString());
		SchedulingXMLGUIUtils.getPatFromStringID(list.getSelectedValue(),data).getPersonalData().setSSN(
				patRevTxSSN.getText().toString());
		
		String dateString = patRevTxDateYear.getSelectedItem().toString() + "-" +
				patRevTxDateMonth.getSelectedItem().toString() + "-" +
				patRevTxDateDay.getSelectedItem().toString() + ", " + 
				"00" + ":" + "00";
		
		SchedulingXMLGUIUtils.getPatFromStringID(
				list.getSelectedValue(),data).getPersonalData().setDOB(DateUtil.getFromString(dateString));
	}
	
	public JPanel getPane() {
		return pane;
	}
	
	public JList<String> getList() {
		return list;
	}
	
	public JButton getCancelButton() {
		return patRevCclBtn;
	}
	
	public JButton getConfirmButton() {
		return patRevCfmBtn;
	}
	
	public JDialog getDialog() {
		return dialog;
	}
	
	public JButton getCancelCancelButton() {
		return patRevCfmCclBtn;
	}
	
	public JButton getConfirmConfirmButton() {
		return patRevCfmCfmBtn;
	}
}
