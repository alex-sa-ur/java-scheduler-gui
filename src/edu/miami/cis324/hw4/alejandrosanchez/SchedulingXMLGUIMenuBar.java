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

public class SchedulingXMLGUIMenuBar {
	private final static String FILE_GUI = "File";
	private final static String ADD_GUI = "New";
	private final static String PATIENT_GUI = "Patient";
	private final static String DOCTOR_GUI = "Doctor";
	private final static String VISIT_GUI = "Visit";
	private final static String REVIEW_GUI = "Review";
	private final static String REMOVE_GUI = "Remove";
	private final static String RESTORE_GUI = "Restore";
	private final static String VIEW_GUI = "View Visits";
	private final static String VIEW_ALL_GUI = "View All";
	private final static String VIEW_UP_GUI = "View All Upcoming";
	private final static String VIEW_BY_PAT_GUI = "By Patient";
	private final static String VIEW_BY_DOC_GUI = "By Doctor";
	private final static String EXIT_GUI = "Exit";

	private JMenu		mnFile, mnAdd, mnRev, mnRem, mnRec, mnView, mnViewAll, mnViewUp;
	
	private JMenuItem 	patAddItm, patRevItm, patRemItm, patRecItm,
						docAddItm, docRevItm, docRemItm, docRecItm,
						visAddItm, visRevItm, visRemItm,
						viewAllItm, viewUpcmgItm, viewAllForPatItm, viewUpcmgForPatItm, viewAllForDocItm, viewUpcmgForDocItm,
						exitItm;
	
	private JMenuBar	menuBar;
	
	public JMenuBar createSchedulingMenuBar(ActionListener listener) {
		JMenuBar menuBar = new JMenuBar();
		
		mnFile 	= new JMenu(FILE_GUI);
		mnAdd	= new JMenu(ADD_GUI);
		mnRev	= new JMenu(REVIEW_GUI);
		mnRem	= new JMenu(REMOVE_GUI);
		mnRec	= new JMenu(RESTORE_GUI);
		mnView = new JMenu(VIEW_GUI);
		mnViewAll = new JMenu(VIEW_ALL_GUI);
		mnViewUp = new JMenu(VIEW_UP_GUI);
		
		patAddItm 			= new JMenuItem(PATIENT_GUI);		patAddItm.addActionListener(listener);
		patRevItm 			= new JMenuItem(PATIENT_GUI);		patRevItm.addActionListener(listener);
		patRemItm 			= new JMenuItem(PATIENT_GUI);		patRemItm.addActionListener(listener);
		patRecItm 			= new JMenuItem(PATIENT_GUI);		patRecItm.addActionListener(listener);
		docAddItm  			= new JMenuItem(DOCTOR_GUI);		docAddItm.addActionListener(listener);
		docRevItm 			= new JMenuItem(DOCTOR_GUI);		docRevItm.addActionListener(listener);
		docRemItm 			= new JMenuItem(DOCTOR_GUI);		docRemItm.addActionListener(listener);
		docRecItm  			= new JMenuItem(DOCTOR_GUI);		docRecItm.addActionListener(listener);
		visAddItm 			= new JMenuItem(VISIT_GUI);			visAddItm.addActionListener(listener);
		visRevItm 			= new JMenuItem(VISIT_GUI);			visRevItm.addActionListener(listener);
		visRemItm 			= new JMenuItem(VISIT_GUI);			visRemItm.addActionListener(listener);
		viewAllItm			= new JMenuItem(VIEW_ALL_GUI);		viewAllItm.addActionListener(listener);
		viewUpcmgItm		= new JMenuItem(VIEW_UP_GUI);		viewUpcmgItm.addActionListener(listener);
		viewAllForPatItm 	= new JMenuItem(VIEW_BY_PAT_GUI);	viewAllForPatItm.addActionListener(listener);
		viewUpcmgForPatItm	= new JMenuItem(VIEW_BY_PAT_GUI);	viewUpcmgForPatItm.addActionListener(listener);
		viewAllForDocItm 	= new JMenuItem(VIEW_BY_DOC_GUI);	viewAllForDocItm.addActionListener(listener);
		viewUpcmgForDocItm 	= new JMenuItem(VIEW_BY_DOC_GUI);	viewUpcmgForDocItm.addActionListener(listener);
		exitItm 				= new JMenuItem(EXIT_GUI);		exitItm.addActionListener(listener);
		
		
		mnAdd.add(patAddItm);
		mnRev.add(patRevItm);
		mnRem.add(patRemItm);
		mnRec.add(patRecItm);
		mnAdd.add(docAddItm);
		mnRev.add(docRevItm);
		mnRem.add(docRemItm);
		mnRec.add(docRecItm);
		mnAdd.add(visAddItm);
		mnRev.add(visRevItm);
		mnRem.add(visRemItm);
		
		mnViewAll.add(viewAllItm);
		mnViewAll.add(viewAllForPatItm);
		mnViewAll.add(viewAllForDocItm);
		
		mnViewUp.add(viewUpcmgItm);
		mnViewUp.add(viewUpcmgForPatItm);
		mnViewUp.add(viewUpcmgForDocItm);
		
		mnView.add(mnViewAll);
		mnView.add(mnViewUp);
		
		mnFile.add(mnAdd);
		mnFile.add(mnRev);
		mnFile.add(mnRem);
		mnFile.add(mnRec);
		mnFile.add(mnView);
		mnFile.addSeparator();
		mnFile.add(exitItm);
		
		menuBar.add(mnFile);
		
		return menuBar;
	}
	
	SchedulingXMLGUIMenuBar(){}
	
	SchedulingXMLGUIMenuBar(ActionListener listener) 
			throws FileNotFoundException, IOException, XMLStreamException{
		menuBar = createSchedulingMenuBar(listener);
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	
	public JMenuItem getPatAdd() {
		return patAddItm;
	}
	
	public JMenuItem getDocAdd() {
		return docAddItm;
	}
	
	public JMenuItem getVisAdd() {
		return visAddItm;
	}
	
	public JMenuItem getPatRev() {
		return patRevItm;
	}
	
	public JMenuItem getDocRev() {
		return docRevItm;
	}
	
	public JMenuItem getVisRev() {
		return visRevItm;
	}
	
	public JMenuItem getPatRem() {
		return patRemItm;
	}
	
	public JMenuItem getDocRem() {
		return docRemItm;
	}
	
	public JMenuItem getVisRem() {
		return visRemItm;
	}
	
	public JMenuItem getPatRec() {
		return patRecItm;
	}
	
	public JMenuItem getDocRec() {
		return docRecItm;
	}

	public JMenuItem getViewAll() {
		return viewAllItm;
	}
	
	public JMenuItem getViewUp() {
		return viewUpcmgItm;
	}
	
	public JMenuItem getViewAllPat() {
		return viewAllForPatItm;
	}
	
	public JMenuItem getViewAllDoc() {
		return viewAllForDocItm;
	}
	
	public JMenuItem getViewUpPat() {
		return viewUpcmgForPatItm;
	}
	
	public JMenuItem getViewUpDoc() {
		return viewUpcmgForDocItm;
	}
	
	public JMenuItem getExit() {
		return exitItm;
	}
}
