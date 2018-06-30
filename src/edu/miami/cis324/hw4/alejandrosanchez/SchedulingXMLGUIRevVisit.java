package edu.miami.cis324.hw4.alejandrosanchez;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.xml.stream.XMLStreamException;

/**
 * @author alejandrosanchez
 *
 */

public class SchedulingXMLGUIRevVisit {
	private final static String VISIT_GUI = "Visit";
	private final static String PATIENT_GUI = "Patient";
	private final static String DOCTOR_GUI = "Doctor";
	private JButton				visRevCfmBtn, visRevCclBtn,
								visRevCfmCfmBtn, visRevCfmCclBtn;
	
	private JComboBox<String> 	visRevTxDoc, visRevTxPat,
								visRevTxDateDay, visRevTxDateMonth, visRevTxDateYear, visRevTxDateHour, visRevTxDateMinute;
	
	private JList<String> 		list;
	
	private JPanel 				pane;
	
	private JDialog dialog = new JDialog();
	
	public void editViewVisitGUI(ActionListener listener, WindowListener wlistener, 
			Visit<Integer,Integer> vis, SchedulerData data) {
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		Calendar cal = Calendar.getInstance();
		cal.setTime(vis.getDate());
		
		String dateString = DateUtil.dateToString(vis.getDate(), new SimpleDateFormat("yyyy/MM/dd-HH:mm"));
		
		dialog.getContentPane().removeAll();
		dialog.addWindowListener(wlistener);
		dialog.setSize(500, 300);
		dialog.setVisible(true);
		
		visRevTxDoc = SchedulingXMLGUIUtils.comboDoctorBox(data);
		visRevTxDoc.setSelectedItem(vis.getHost()+": "+VisitUtils.matchToDocID(vis, data.getListDoc()).getFullName().getFLName());
		visRevTxPat = SchedulingXMLGUIUtils.comboPatientBox(data);
		visRevTxPat.setSelectedItem(vis.getVisitor()+": "+VisitUtils.matchToPatID(vis, data.getListPat()).getFullName().getFLName());
		
		visRevTxDateYear = SchedulingXMLGUIUtils.comboYearBox();
		visRevTxDateYear.setSelectedItem((String.valueOf(Integer.parseInt(dateString.substring(0, 4).toString()))));
		visRevTxDateMonth = SchedulingXMLGUIUtils.comboMonthBox();
		visRevTxDateMonth.setSelectedItem((String.valueOf(Integer.parseInt(dateString.substring(5, 7).toString()))));
		visRevTxDateDay = SchedulingXMLGUIUtils.comboDayBox();
		visRevTxDateDay.setSelectedItem((String.valueOf(Integer.parseInt(dateString.substring(8, 10).toString()))));
		visRevTxDateHour = SchedulingXMLGUIUtils.comboHourBox();
		visRevTxDateHour.setSelectedItem((String.valueOf(Integer.parseInt(dateString.substring(11, 13).toString()))));
		visRevTxDateMinute = SchedulingXMLGUIUtils.comboMinuteBox();
		visRevTxDateMinute.setSelectedItem((String.valueOf(Integer.parseInt(dateString.substring(14).toString()))));
		
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Review " + VISIT_GUI));
		panel.add(new JLabel("-----------------------------"));
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(VISIT_GUI + " " + DOCTOR_GUI + ":"));
		subPanel.add(visRevTxDoc);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(VISIT_GUI + " " + PATIENT_GUI + ":"));
		subPanel.add(visRevTxPat);
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.add(new JLabel(VISIT_GUI + " Date:"));
		subPanel.add(visRevTxDateDay);
		subPanel.add(visRevTxDateMonth);
		subPanel.add(visRevTxDateYear);
		subPanel.add(visRevTxDateHour);
		subPanel.add(visRevTxDateMinute);
		
		panel.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		visRevCfmCclBtn = new JButton("Cancel");
		visRevCfmCfmBtn = new JButton("Confirm");
		visRevCfmCclBtn.addActionListener(listener);
		visRevCfmCfmBtn.addActionListener(listener);
		subPanel.add(visRevCfmCclBtn);
		subPanel.add(visRevCfmCfmBtn);
		panel.add(subPanel);
		
		dialog.add(panel);
		dialog.validate();
	}
	
	private JPanel revVisitGUI(SchedulerData data, ActionListener listener){
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		list = SchedulingXMLGUIUtils.listVisToJList(data);
		
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		
		panel.add(new JLabel("Review " + VISIT_GUI));
		panel.add(new JLabel("-----------------------------"));
		panel.add(list);
		
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		visRevCclBtn = new JButton("Cancel");
		visRevCfmBtn = new JButton("Confirm");
		visRevCclBtn.addActionListener(listener);
		visRevCfmBtn.addActionListener(listener);
		subPanel.add(visRevCclBtn);
		subPanel.add(visRevCfmBtn);
		panel.add(subPanel);
		
		return panel;
	}
	
	SchedulingXMLGUIRevVisit(){}
	
	SchedulingXMLGUIRevVisit(SchedulerData data, String inputFile, ActionListener listener) 
			throws FileNotFoundException, IOException, XMLStreamException{
		data = SchedulingXMLGUIUtils.updateData(data, inputFile);
		pane = revVisitGUI(data, listener);
	}
	
	public void revVisit(SchedulerData data) {
		data.getListVis().get(list.getSelectedIndex()).setHost(
				SchedulingXMLGUIUtils.getDocIDFromStringID(visRevTxDoc.getSelectedItem().toString(),data));
		
		data.getListVis().get(list.getSelectedIndex()).setVisitor(
				SchedulingXMLGUIUtils.getPatIDFromStringID(visRevTxPat.getSelectedItem().toString(),data));
		
		String dateString = visRevTxDateYear.getSelectedItem().toString() + "-" +
							visRevTxDateMonth.getSelectedItem().toString() + "-" +
							visRevTxDateDay.getSelectedItem().toString() + ", " + 
							visRevTxDateHour.getSelectedItem().toString() + ":" + 
							visRevTxDateMinute.getSelectedItem().toString();
		
		data.getListVis().get(list.getSelectedIndex()).setDate(DateUtil.getFromString(dateString));
	}
	
	public JPanel getPane() {
		return pane;
	}
	
	public JList<String> getList() {
		return list;
	}
	
	public JButton getCancelButton() {
		return visRevCclBtn;
	}
	
	public JButton getConfirmButton() {
		return visRevCfmBtn;
	}
	
	public JDialog getDialog() {
		return dialog;
	}
	
	public JButton getCancelCancelButton() {
		return visRevCfmCclBtn;
	}
	
	public JButton getConfirmConfirmButton() {
		return visRevCfmCfmBtn;
	}
}
