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

public class SchedulingXMLGUIRemVisit {
	private final static String VISIT_GUI = "Visit";
	private JButton				visRemCfmBtn, visRemCclBtn,
								visRemCfmCfmBtn, visRemCfmCclBtn;
	
	private JList<String> 		list;
	
	private JPanel 				pane;
	
	private JDialog dialog = new JDialog();
	
	public void remCfmVisitGUI(ActionListener listener, WindowListener wlistener, 
			Visit<Integer,Integer> vis, SchedulerData data) {
		dialog.getContentPane().removeAll();
		dialog.addWindowListener(wlistener);
		dialog.setSize(500, 300);
		dialog.setVisible(true);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Confirming deletion of " + VISIT_GUI + ":", JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Doctor: " + VisitUtils.matchToDocID(vis, data.getListDoc()).getFullName().getFLName(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Patient: " + VisitUtils.matchToPatID(vis, data.getListPat()).getFullName().getFLName(), JLabel.CENTER);
		panel.add(label);
		label = new JLabel("Date: " + DateUtil.dateToString(vis.getDate(),new SimpleDateFormat("dd-MM-yyyy")), JLabel.CENTER);
		panel.add(label);
		visRemCfmCfmBtn = new JButton("Confirm");
		visRemCfmCfmBtn.addActionListener(listener);
		panel.add(visRemCfmCfmBtn, JPanel.CENTER_ALIGNMENT);
		visRemCfmCclBtn = new JButton("Cancel");
		visRemCfmCclBtn.addActionListener(listener);
		panel.add(visRemCfmCclBtn, JPanel.CENTER_ALIGNMENT);
		dialog.add(panel);
		dialog.validate();
	}
	
	private JPanel remVisitGUI(SchedulerData data, ActionListener listener){
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		list = SchedulingXMLGUIUtils.listVisToJList(data);
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Remove " + VISIT_GUI));
		panel.add(new JLabel("-----------------------------"));
		panel.add(list);
		
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		visRemCclBtn = new JButton("Cancel");
		visRemCfmBtn = new JButton("Confirm");
		visRemCclBtn.addActionListener(listener);
		visRemCfmBtn.addActionListener(listener);
		subPanel.add(visRemCclBtn);
		subPanel.add(visRemCfmBtn);
		panel.add(subPanel);
		
		return panel;
	}
	
	SchedulingXMLGUIRemVisit(){}
	
	SchedulingXMLGUIRemVisit(SchedulerData data, String inputFile, ActionListener listener) 
			throws FileNotFoundException, IOException, XMLStreamException{
		data = SchedulingXMLGUIUtils.updateData(data, inputFile);
		pane = remVisitGUI(data, listener);
	}
	
	public SchedulerData remVisit(SchedulerData data, Visit<Integer,Integer> vis) {
		SchedulerData dataNew = new SchedulerData();
		
		for (Patient p: data.getListPat()) {
			dataNew.addPatient(p);
		}
		
		for (Doctor d: data.getListDoc()) {
			dataNew.addDoctor(d);
		}
		
		for (Visit<Integer,Integer> v: data.getListVis()) {
			if (!v.equals(vis)) {
				dataNew.addVisit(v);
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
		return visRemCclBtn;
	}
	
	public JButton getConfirmButton() {
		return visRemCfmBtn;
	}
	
	public JDialog getDialog() {
		return dialog;
	}
	
	public JButton getCancelCancelButton() {
		return visRemCfmCclBtn;
	}
	
	public JButton getConfirmConfirmButton() {
		return visRemCfmCfmBtn;
	}
}
