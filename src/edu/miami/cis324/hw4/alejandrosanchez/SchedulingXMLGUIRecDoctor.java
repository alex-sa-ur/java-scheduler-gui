package edu.miami.cis324.hw4.alejandrosanchez;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.xml.stream.XMLStreamException;

/**
 * @author alejandrosanchez
 *
 */

public class SchedulingXMLGUIRecDoctor {
	private final static String DOCTOR_GUI = "Doctor";
	private JButton				docRecCfmBtn, docRecCclBtn,
								docRecCfmCfmBtn, docRecCfmCclBtn;
	
	private JList<String> 		list;
	
	private JPanel 				pane;
	
	private JDialog dialog = new JDialog();
	
	public void recCfmDoctorGUI(ActionListener listener, WindowListener wlistener, Doctor doc) {
		dialog.getContentPane().removeAll();
		dialog.addWindowListener(wlistener);
		dialog.setSize(500, 300);
		dialog.setVisible(true);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Confirming recovery of " + DOCTOR_GUI + ":", JLabel.CENTER);
		panel.add(label);
		label = new JLabel("First Name: " + doc.getFullName().getFirstName(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Middle Name: " + doc.getFullName().getMidName(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Last Name: " + doc.getFullName().getLastName(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("SSN: " + doc.getPersonalData().getSSN(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("DOB: " + 	DateUtil.dateToString(doc.getPersonalData().getDOB(),new SimpleDateFormat("dd-MM-yyyy")),
				JLabel.CENTER);
		panel.add(label);
		docRecCfmCfmBtn = new JButton("Confirm");
		docRecCfmCfmBtn.addActionListener(listener);
		panel.add(docRecCfmCfmBtn, JPanel.CENTER_ALIGNMENT);
		docRecCfmCclBtn = new JButton("Cancel");
		docRecCfmCclBtn.addActionListener(listener);
		panel.add(docRecCfmCclBtn, JPanel.CENTER_ALIGNMENT);
		dialog.add(panel);
		dialog.validate();
	}
	
	private JPanel revDoctorGUI(SchedulerData data, ActionListener listener){
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		list = SchedulingXMLGUIUtils.listRemDocFullToJList(data);
		
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Recover " + DOCTOR_GUI));
		panel.add(new JLabel("-----------------------------"));
		panel.add(list);
		
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		docRecCclBtn = new JButton("Cancel");
		docRecCfmBtn = new JButton("Confirm");
		docRecCclBtn.addActionListener(listener);
		docRecCfmBtn.addActionListener(listener);
		subPanel.add(docRecCclBtn);
		subPanel.add(docRecCfmBtn);
		panel.add(subPanel);
		
		return panel;
	}
	
	SchedulingXMLGUIRecDoctor(){}
	
	SchedulingXMLGUIRecDoctor(SchedulerData data, String inputFile, ActionListener listener) 
			throws FileNotFoundException, IOException, XMLStreamException{
		data = SchedulingXMLGUIUtils.updateData(data, inputFile);
		pane = revDoctorGUI(data, listener);
	}
	
	public SchedulerData recDoctor(SchedulerData data, Doctor doc) {
		SchedulerData dataNew = new SchedulerData();
		
		for (Patient p: data.getListPat()) {
			dataNew.addPatient(p);
		}
		
		for (Visit<Integer,Integer> v: data.getListVis()) {
			dataNew.addVisit(v);
		}
		
		for (Doctor d: data.getListDoc()) {
			if (!d.equals(doc)) {
				dataNew.addDoctor(d);
			} else {
				d.setActiveStatus(true);
				dataNew.addDoctor(d);
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
		return docRecCclBtn;
	}
	
	public JButton getConfirmButton() {
		return docRecCfmBtn;
	}
	
	public JDialog getDialog() {
		return dialog;
	}
	
	public JButton getCancelCancelButton() {
		return docRecCfmCclBtn;
	}
	
	public JButton getConfirmConfirmButton() {
		return docRecCfmCfmBtn;
	}
}
