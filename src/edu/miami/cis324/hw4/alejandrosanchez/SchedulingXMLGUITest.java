package edu.miami.cis324.hw4.alejandrosanchez;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import javax.xml.stream.XMLStreamException;

/**
 * @author alejandrosanchez
 *
 */

public class SchedulingXMLGUITest extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private final static String INPUT_FILE = "resources/SchedulerDataOutTest.xml";
	private final static String OUTPUT_FILE = "resources/SchedulerDataOutTest.xml";
	
	SchedulingXMLGUIAddPatient 	addPatientGUI	= new SchedulingXMLGUIAddPatient();
	SchedulingXMLGUIAddDoctor 	addDoctorGUI 	= new SchedulingXMLGUIAddDoctor();
	SchedulingXMLGUIAddVisit 	addVisitGUI		= new SchedulingXMLGUIAddVisit();
	SchedulingXMLGUIRevPatient 	revPatientGUI	= new SchedulingXMLGUIRevPatient();
	SchedulingXMLGUIRevDoctor 	revDoctorGUI	= new SchedulingXMLGUIRevDoctor();
	SchedulingXMLGUIRevVisit 	revVisitGUI		= new SchedulingXMLGUIRevVisit();
	SchedulingXMLGUIRemPatient 	remPatientGUI	= new SchedulingXMLGUIRemPatient();
	SchedulingXMLGUIRemDoctor 	remDoctorGUI	= new SchedulingXMLGUIRemDoctor();
	SchedulingXMLGUIRemVisit 	remVisitGUI		= new SchedulingXMLGUIRemVisit();
	SchedulingXMLGUIRecPatient 	recPatientGUI	= new SchedulingXMLGUIRecPatient();
	SchedulingXMLGUIRecDoctor 	recDoctorGUI	= new SchedulingXMLGUIRecDoctor();
	SchedulingXMLGUIMenuBar 	menuBarGUI		= new SchedulingXMLGUIMenuBar();
	
	private SchedulerData data;
	
	public void clearScreen() {
		setJMenuBar(menuBarGUI.getMenuBar());
		getContentPane().removeAll();
	}
	
	public void startScreen() {
		clearScreen();
		add(new JLabel("Select option from 'File'", JLabel.CENTER));
	} 
	
	SchedulingXMLGUITest() throws FileNotFoundException, IOException, XMLStreamException{
		data = SchedulingXMLGUIUtils.updateData(data, INPUT_FILE);
		addWindowListener(this);
		menuBarGUI = new SchedulingXMLGUIMenuBar(this);
		setSize(800, 800);
		startScreen();
		setVisible(true);
		validate();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		if(o.equals(menuBarGUI.getPatAdd())) {
			clearScreen();
			try {
				addPatientGUI = new SchedulingXMLGUIAddPatient(data, INPUT_FILE, this);
			} catch (IOException | XMLStreamException e) {}
			add(addPatientGUI.getPane());
			validate();
		}
		
		if(o.equals(menuBarGUI.getDocAdd())) {
			clearScreen();
			try {
				addDoctorGUI = new SchedulingXMLGUIAddDoctor(data, INPUT_FILE, this);
			} catch (IOException | XMLStreamException e) {}
			add(addDoctorGUI.getPane());
			validate();
		}
		
		if(o.equals(menuBarGUI.getVisAdd())) {
			clearScreen();
			try {
				addVisitGUI = new SchedulingXMLGUIAddVisit(data, INPUT_FILE, this);
			} catch (IOException | XMLStreamException e) {}
			add(addVisitGUI.getPane());
			validate();
		}
		
		if(o.equals(menuBarGUI.getPatRev())) {
			clearScreen();
			try {
				revPatientGUI = new SchedulingXMLGUIRevPatient(data, INPUT_FILE, this);
			} catch (IOException | XMLStreamException e) {}
			add(revPatientGUI.getPane());
			validate();
		}
		
		if(o.equals(menuBarGUI.getDocRev())) {
			clearScreen();
			try {
				revDoctorGUI = new SchedulingXMLGUIRevDoctor(data, INPUT_FILE, this);
			} catch (IOException | XMLStreamException e) {}
			add(revDoctorGUI.getPane());
			validate();
		}
		
		if(o.equals(menuBarGUI.getVisRev())) {
			clearScreen();
			try {
				revVisitGUI = new SchedulingXMLGUIRevVisit(data, INPUT_FILE, this);
			} catch (IOException | XMLStreamException e) {}
			add(revVisitGUI.getPane());
			validate();
		}
		
		if(o.equals(menuBarGUI.getPatRem())) {
			clearScreen();
			try {
				remPatientGUI = new SchedulingXMLGUIRemPatient(data, INPUT_FILE, this);
			} catch (IOException | XMLStreamException e) {}
			add(remPatientGUI.getPane());
			validate();
		}
		
		if(o.equals(menuBarGUI.getDocRem())) {
			clearScreen();
			try {
				remDoctorGUI = new SchedulingXMLGUIRemDoctor(data, INPUT_FILE, this);
			} catch (IOException | XMLStreamException e) {}
			add(remDoctorGUI.getPane());
			validate();
		}
		
		if(o.equals(menuBarGUI.getVisRem())) {
			clearScreen();
			try {
				remVisitGUI = new SchedulingXMLGUIRemVisit(data, INPUT_FILE, this);
			} catch (IOException | XMLStreamException e) {}
			add(remVisitGUI.getPane());
			validate();
		}
		
		if(o.equals(menuBarGUI.getPatRec())) {
			clearScreen();
			try {
				recPatientGUI = new SchedulingXMLGUIRecPatient(data, INPUT_FILE, this);
			} catch (IOException | XMLStreamException e) {}
			add(recPatientGUI.getPane());
			validate();
		}
		
		if(o.equals(menuBarGUI.getDocRec())) {
			clearScreen();
			try {
				recDoctorGUI = new SchedulingXMLGUIRecDoctor(data, INPUT_FILE, this);
			} catch (IOException | XMLStreamException e) {}
			add(recDoctorGUI.getPane());
			validate();
		}
		
		if(o.equals(menuBarGUI.getViewAll())) {
			clearScreen();
			add(SchedulingXMLGUIVisitList.viewAll(data, this));
			validate();
		}
		
		if(o.equals(menuBarGUI.getViewUp())) {
			clearScreen();
			add(SchedulingXMLGUIVisitList.viewUp(data, this));
			validate();
		}
		
		if(o.equals(menuBarGUI.getViewAllPat())) {
			clearScreen();
			add(SchedulingXMLGUIVisitList.viewAllPat(data, this));
			validate();
		}
		
		if(o.equals(menuBarGUI.getViewAllDoc())) {
			clearScreen();
			add(SchedulingXMLGUIVisitList.viewAllDoc(data, this));
			validate();
		}
		
		if(o.equals(menuBarGUI.getViewUpPat())) {
			clearScreen();
			add(SchedulingXMLGUIVisitList.viewUpPat(data, this));
			validate();
		}
		
		if(o.equals(menuBarGUI.getViewUpDoc())) {
			clearScreen();
			add(SchedulingXMLGUIVisitList.viewUpDoc(data,this));
			validate();
		}
		
		if(o.equals(menuBarGUI.getExit())) {
			dispose();
			System.exit(0);
		}
		
		if(o.equals(addPatientGUI.getConfirmButton())) {
			addPatientGUI.confirmAddPatientGUI(this, this);
		}
		
		if(o.equals(addPatientGUI.getConfirmConfirmButton())) {
			addPatientGUI.addPatient(data);
			try {
				SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
			} catch (XMLStreamException | IOException e) {}
			addPatientGUI.getDialog().dispose();
			startScreen();
			validate();
		}
		
		if(o.equals(addPatientGUI.getCancelCancelButton())) {
			addPatientGUI.getDialog().dispose();
		}
		
		if(o.equals(addPatientGUI.getCancelButton())) {
			startScreen();
			validate();
		}
		
		if(o.equals(addDoctorGUI.getConfirmButton())) {
			addDoctorGUI.confirmAddDoctorGUI(this, this);
		}
		
		if(o.equals(addDoctorGUI.getConfirmConfirmButton())) {
			addDoctorGUI.addDoctor(data);
			try {
				SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
			} catch (XMLStreamException | IOException e) {}
			addDoctorGUI.getDialog().dispose();
			startScreen();
			validate();
		}
		
		if(o.equals(addDoctorGUI.getCancelCancelButton())) {
			addDoctorGUI.getDialog().dispose();
		}
		
		if(o.equals(addDoctorGUI.getCancelButton())) {
			startScreen();
			validate();
		}
		
		if(o.equals(addVisitGUI.getConfirmButton())) {
			addVisitGUI.confirmAddVisitGUI(this, this);
		}
		
		if(o.equals(addVisitGUI.getConfirmConfirmButton())) {
			addVisitGUI.addVisit(data);
			try {
				SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
			} catch (XMLStreamException | IOException e) {}
			addVisitGUI.getDialog().dispose();
			startScreen();
			validate();
		}
		
		if(o.equals(addVisitGUI.getCancelCancelButton())) {
			addVisitGUI.getDialog().dispose();
		}
		
		if(o.equals(addVisitGUI.getCancelButton())) {
			startScreen();
			validate();
		}
		
		if(o.equals(revPatientGUI.getConfirmButton())) {
			revPatientGUI.editViewPatientGUI(this, this, 
					SchedulingXMLGUIUtils.getPatFromStringID(revPatientGUI.getList().getSelectedValue(),data));
		}
		
		if(o.equals(revPatientGUI.getConfirmConfirmButton())) {
			revPatientGUI.revPatient(data);
			try {
				SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
			} catch (XMLStreamException | IOException e) {}
			revPatientGUI.getDialog().dispose();
			startScreen();
			validate();
		}
		
		if(o.equals(revPatientGUI.getCancelCancelButton())) {
			revPatientGUI.getDialog().dispose();
		}
		
		if(o.equals(revPatientGUI.getCancelButton())) {
			startScreen();
			validate();
		}
		
		if(o.equals(revDoctorGUI.getConfirmButton())) {
			revDoctorGUI.editViewDoctorGUI(this, this, 
					SchedulingXMLGUIUtils.getDocFromStringID(revDoctorGUI.getList().getSelectedValue(),data));
		}
		
		if(o.equals(revDoctorGUI.getConfirmConfirmButton())) {
			revDoctorGUI.revDoctor(data);
			try {
				SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
			} catch (XMLStreamException | IOException e) {}
			revDoctorGUI.getDialog().dispose();
			startScreen();
			validate();
		}
		
		if(o.equals(revDoctorGUI.getCancelCancelButton())) {
			revDoctorGUI.getDialog().dispose();
		}
		
		if(o.equals(revDoctorGUI.getCancelButton())) {
			startScreen();
			validate();
		}
		
		if(o.equals(revVisitGUI.getConfirmButton())) {
			revVisitGUI.editViewVisitGUI(this, this, 
					data.getListVis().get(SchedulingXMLGUIUtils.findVisitIndex(revVisitGUI.getList().getSelectedValue(),data)),
					data);
		}
		
		if(o.equals(revVisitGUI.getConfirmConfirmButton())) {
			revVisitGUI.revVisit(data);
			try {
				SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
			} catch (XMLStreamException | IOException e) {}
			revVisitGUI.getDialog().dispose();
			startScreen();
			validate();
		}
		
		if(o.equals(revVisitGUI.getCancelCancelButton())) {
			revVisitGUI.getDialog().dispose();
		}
		
		if(o.equals(revVisitGUI.getCancelButton())) {
			startScreen();
			validate();
		}
		
		if(o.equals(remPatientGUI.getConfirmButton())) {
			remPatientGUI.remCfmPatientGUI(this, this, 
					SchedulingXMLGUIUtils.getPatFromStringID(remPatientGUI.getList().getSelectedValue(),data));
		}
		
		if(o.equals(remPatientGUI.getConfirmConfirmButton())) {
			data = 	remPatientGUI.remPatient(data, SchedulingXMLGUIUtils.getPatFromStringID(
					remPatientGUI.getList().getSelectedValue(),data));
			try {
				SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
			} catch (XMLStreamException | IOException e) {}
			remPatientGUI.getDialog().dispose();
			startScreen();
			validate();
		}
		
		if(o.equals(remPatientGUI.getCancelCancelButton())) {
			remPatientGUI.getDialog().dispose();
		}
		
		if(o.equals(remPatientGUI.getCancelButton())) {
			startScreen();
			validate();
		}
		
		if(o.equals(remDoctorGUI.getConfirmButton())) {
			remDoctorGUI.remCfmDoctorGUI(this, this, 
					SchedulingXMLGUIUtils.getDocFromStringID(remDoctorGUI.getList().getSelectedValue(),data));
		}
		
		if(o.equals(remDoctorGUI.getConfirmConfirmButton())) {
			data = 	remDoctorGUI.remDoctor(data, SchedulingXMLGUIUtils.getDocFromStringID(
					remDoctorGUI.getList().getSelectedValue(),data));
			try {
				SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
			} catch (XMLStreamException | IOException e) {}
			remDoctorGUI.getDialog().dispose();
			startScreen();
			validate();
		}
		
		if(o.equals(remDoctorGUI.getCancelCancelButton())) {
			remDoctorGUI.getDialog().dispose();
		}
		
		if(o.equals(remDoctorGUI.getCancelButton())) {
			startScreen();
			validate();
		}
		
		if(o.equals(remVisitGUI.getConfirmButton())) {
			remVisitGUI.remCfmVisitGUI(this, this, 
						data.getListVis().get(SchedulingXMLGUIUtils.findVisitIndex(remVisitGUI.getList().getSelectedValue(),data)),
						data);
		}
		
		if(o.equals(remVisitGUI.getConfirmConfirmButton())) {
			data = 	remVisitGUI.remVisit(data, 
					data.getListVis().get(SchedulingXMLGUIUtils.findVisitIndex(remVisitGUI.getList().getSelectedValue(),data)));
			try {
				SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
			} catch (XMLStreamException | IOException e) {}
			remVisitGUI.getDialog().dispose();
			startScreen();
			validate();
		}
		
		if(o.equals(remVisitGUI.getCancelCancelButton())) {
			remVisitGUI.getDialog().dispose();
		}
		
		if(o.equals(remVisitGUI.getCancelButton())) {
			startScreen();
			validate();
		}
		
		if(o.equals(recPatientGUI.getConfirmButton())) {
			recPatientGUI.recCfmPatientGUI(this, this, 
					SchedulingXMLGUIUtils.getPatFromStringID(recPatientGUI.getList().getSelectedValue(),data));
		}
		
		if(o.equals(recPatientGUI.getConfirmConfirmButton())) {
			data = 	recPatientGUI.recPatient(data, SchedulingXMLGUIUtils.getPatFromStringID(
					recPatientGUI.getList().getSelectedValue(),data));
			try {
				SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
			} catch (XMLStreamException | IOException e) {}
			recPatientGUI.getDialog().dispose();
			startScreen();
			validate();
		}
		
		if(o.equals(recPatientGUI.getCancelCancelButton())) {
			recPatientGUI.getDialog().dispose();
		}
		
		if(o.equals(recPatientGUI.getCancelButton())) {
			startScreen();
			validate();
		}
		
		if(o.equals(recDoctorGUI.getConfirmButton())) {
			recDoctorGUI.recCfmDoctorGUI(this, this, 
					SchedulingXMLGUIUtils.getDocFromStringID(remDoctorGUI.getList().getSelectedValue(),data));
		}
		
		if(o.equals(recDoctorGUI.getConfirmConfirmButton())) {
			data = 	recDoctorGUI.recDoctor(data, SchedulingXMLGUIUtils.getDocFromStringID(
					recDoctorGUI.getList().getSelectedValue(),data));
			try {
				SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
			} catch (XMLStreamException | IOException e) {}
			recDoctorGUI.getDialog().dispose();
			startScreen();
			validate();
		}
		
		if(o.equals(recDoctorGUI.getCancelCancelButton())) {
			recDoctorGUI.getDialog().dispose();
		}
		
		if(o.equals(recDoctorGUI.getCancelButton())) {
			startScreen();
			validate();
		}
		
		if(o.equals(SchedulingXMLGUIVisitList.getConfirmButton())) {
			clearScreen();
			add(SchedulingXMLGUIVisitList.updateView(data, this));
			validate();
			
		}
		
		if(o.equals(SchedulingXMLGUIVisitList.getCancelButton())) {
			startScreen();
			validate();
		}	
	}
	
	@Override
	public void windowClosing(WindowEvent arg0) {
		dispose();
		System.exit(0);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, XMLStreamException {
		new SchedulingXMLGUITest();
	}

	@Override
	public void windowClosed(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowActivated(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
}
