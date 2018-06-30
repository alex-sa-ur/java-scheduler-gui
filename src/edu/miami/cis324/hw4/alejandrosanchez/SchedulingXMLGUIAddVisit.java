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

public class SchedulingXMLGUIAddVisit {
	private final static String 				VISIT_GUI = "Visit";
	private final static String 				DOCTOR_GUI = "Doctor";
	private final static String 				PATIENT_GUI = "Patient";
	private JButton								visAddCfmBtn, visAddCclBtn, 
												visAddAddCfmBtn, visAddAddCclBtn;;
	private JComboBox<String>					visAddTxDoc;
	private JComboBox<String> 					visAddTxPat;
	private JComboBox<String> 					visAddTxDateDay, visAddTxDateMonth, visAddTxDateYear,
												visAddTxDateHour, visAddTxDateMinute;
	
	private JSplitPane pane;
	
	private JDialog dialog = new JDialog();
	
	public void confirmAddVisitGUI(ActionListener listener, WindowListener wlistener) {
		dialog.getContentPane().removeAll();
		dialog.addWindowListener(wlistener);
		dialog.setSize(500, 300);
		dialog.setVisible(true);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Confirming creation of " + VISIT_GUI + ":", JLabel.CENTER);
		panel.add(label);
		label = new JLabel(VISIT_GUI + " Date: " + visAddTxDateDay.getSelectedItem().toString() + "-" + 
											visAddTxDateMonth.getSelectedItem().toString() + "-" +
											visAddTxDateYear.getSelectedItem().toString() + ", " +
											visAddTxDateHour.getSelectedItem().toString() + ":" +
											visAddTxDateMinute.getSelectedItem().toString(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel(DOCTOR_GUI + ": " + (visAddTxDoc.getSelectedItem()), JLabel.CENTER);
		panel.add(label);
		label = new JLabel(PATIENT_GUI + ": " + (visAddTxPat.getSelectedItem()), JLabel.CENTER);
		panel.add(label);
		visAddAddCfmBtn = new JButton("Confirm");
		visAddAddCfmBtn.addActionListener(listener);
		panel.add(visAddAddCfmBtn, JPanel.CENTER_ALIGNMENT);
		visAddAddCclBtn = new JButton("Cancel");
		visAddAddCclBtn.addActionListener(listener);
		panel.add(visAddAddCclBtn, JPanel.CENTER_ALIGNMENT);
		dialog.add(panel);
		dialog.validate();
	}
	
	private JPanel addVisitGUI(ActionListener listener, SchedulerData data){
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		visAddTxDoc = SchedulingXMLGUIUtils.comboDoctorBox(data);
		visAddTxDateYear = SchedulingXMLGUIUtils.comboNextYearBox();
		visAddTxDateMonth = SchedulingXMLGUIUtils.comboMonthBox();
		visAddTxDateDay = SchedulingXMLGUIUtils.comboDayBox();
		visAddTxDateHour = SchedulingXMLGUIUtils.comboHourBox();
		visAddTxDateMinute = SchedulingXMLGUIUtils.comboMinuteBox();
		visAddTxPat = SchedulingXMLGUIUtils.comboPatientBox(data);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Add " + VISIT_GUI));
		panel.add(new JLabel("-----------------------------"));
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(VISIT_GUI + " Date:"));
		subPanel.add(visAddTxDateDay);
		subPanel.add(visAddTxDateMonth);
		subPanel.add(visAddTxDateYear);
		subPanel.add(visAddTxDateHour);
		subPanel.add(visAddTxDateMinute);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(VISIT_GUI + " " + DOCTOR_GUI +":"));
		subPanel.add(visAddTxDoc);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(VISIT_GUI + " " + PATIENT_GUI +":"));
		subPanel.add(visAddTxPat);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		visAddCclBtn = new JButton("Cancel");
		visAddCfmBtn = new JButton("Confirm");
		visAddCclBtn.addActionListener(listener);
		visAddCfmBtn.addActionListener(listener);
		subPanel.add(visAddCclBtn);
		subPanel.add(visAddCfmBtn);
		panel.add(subPanel);
		
		return panel;
	}
	
	SchedulingXMLGUIAddVisit(){}
	
	SchedulingXMLGUIAddVisit(SchedulerData data, String inputFile, ActionListener listener) 
			throws FileNotFoundException, IOException, XMLStreamException{
		data = SchedulingXMLGUIUtils.updateData(data, inputFile);
		pane = SchedulingXMLGUIUtils.splitPaneHorizontalList(SchedulingXMLGUIUtils.listVisToJList(data), 
				addVisitGUI(listener, data));
	}
	
	public JSplitPane getPane() {
		return pane;
	}
	
	public JButton getCancelButton() {
		return visAddCclBtn;
	}
	
	public JButton getConfirmButton() {
		return visAddCfmBtn;
	}
	
	public JDialog getDialog() {
		return dialog;
	}
	
	public JButton getCancelCancelButton() {
		return visAddAddCclBtn;
	}
	
	public JButton getConfirmConfirmButton() {
		return visAddAddCfmBtn;
	}
	
	public void addVisit(SchedulerData data) {
		String dateString = visAddTxDateYear.getSelectedItem().toString() + "-" +
							visAddTxDateMonth.getSelectedItem().toString() + "-" +
							visAddTxDateDay.getSelectedItem().toString() + ", " + 
							visAddTxDateHour.getSelectedItem().toString() + ":" +
							visAddTxDateMinute.getSelectedItem().toString();
		
		Visit<Integer,Integer> v = new VisitImpl<Integer,Integer>(	
				SchedulingXMLGUIUtils.getPatIDFromStringID(visAddTxPat.getSelectedItem().toString(), data),
				SchedulingXMLGUIUtils.getDocIDFromStringID(visAddTxDoc.getSelectedItem().toString(), data),
				DateUtil.getFromString(dateString));
		data.addVisit(v);
	}
}
