package edu.miami.cis324.hw4.alejandrosanchez;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/*
 * Class used for:
 * - Reading a Scheduler XML file
 * - Populating a SchedulerData object with data read
 * - Getting populated SchedulerData object
 */

/**
 * @author alejandrosanchez
 *
 */

public abstract class SchedulerXMLReaderUtils extends SchedulerXMLReadWriteUtils {
	
	/*
	 * Methods:
	 * readName(XMLEventReader):		reads events of type name from an XML file
	 * 									adds data to a Name object
	 * 									returns resulting Name
	 * 
	 * readPersonalData(XMLEventReader):reads events within a personalData event in an XML file
	 * 									adds dob and ssn data to PersonalData object
	 * 									returns that PersonalData object
	 * 
	 * readPatient(XMLEventReader): 	reads events from an XML file
	 * 									adds data to a PatientImpl object
	 * 									returns resulting PatientImpl
	 * 
	 * readDoctor(XMLEventReader): 		reads events from an XML file
	 * 									adds data to a DoctorImpl object
	 * 									returns resulting DoctorImpl
	 * 
	 * readVisit(XMLEventReader):		reads events from an XML file
	 * 									adds data to a VisitImpl object
	 * 									returns resulting VisitImpl
	 * 
	 * readSchedulingXML(String): 		reads an XML file 'fileName'
	 * 									adds data to a SchedulerData object
	 * 									returns that SchedulerData object
	 */
	
	/*
	 * readName(XMLEventReader): 	
	 * Instantiates an XMLEvent with the next event in the Event reader, expecting a Name event
	 * Runs through looking for the start elements
	 * Reads the data in the events, looking for firstName and lastName values to assign to the variables of the same name
	 * Adds the variables to a Name object
	 * Returns the object
	 */
	
	public static Name readName(XMLEventReader eventReader) throws XMLStreamException{
		XMLEvent firstEvent = eventReader.nextEvent();
		
		if(!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a Name but not a start element: found event of type " + 
											firstEvent.getEventType());
		}
		
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(NAME)){
			throw new IllegalStateException("Attempting to read a Name at the wrong start element: found event of type " +
											firstEvent.asStartElement().getName());
		}
		
		Name name = null;
		String firstName = null, lastName = null, midName = null;
		boolean finished = false;
		
		while(!finished) {
			XMLEvent event = eventReader.nextEvent();
			
			if(event.isStartElement()) { 
				StartElement startElement = event.asStartElement();
				
				if(startElement.getName().getLocalPart().equals(FIRST_NAME)) {
					event 		= eventReader.nextEvent();
					if (!event.isEndElement()){
						firstName 	= event.asCharacters().getData();
					}
				}
				
				else if (startElement.getName().getLocalPart().equals(LAST_NAME)) {
					event 		= eventReader.nextEvent();
					if (!event.isEndElement()){
						lastName 	= event.asCharacters().getData();
					}
				}
				
				else if (startElement.getName().getLocalPart().equals(MID_NAME)) {
					event 		= eventReader.nextEvent();
					if (!event.isEndElement()){
						midName 	= event.asCharacters().getData();
					}
				}
				
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
				}
			}
			
			else if (event.isEndElement()){
				EndElement endElement = event.asEndElement();
				
				if(endElement.getName().getLocalPart().equals(NAME)) {
					name 		= new Name(firstName, lastName, midName);
					finished 	= true;
				}
			}
		}
		
		return name;
	}
	
	/*
	 * readPersonalData(XMLEventReader):
	 * Instantiates an XMLEvent with the next event in the Event reader, expecting a PersonalData event
	 * Runs through looking for the start elements
	 * Reads the data in the events, looking for ssn and dob values to assign to the variables of the same name
	 * Adds the variables to a PersonalData object
	 * Returns the object
	 */
	
	private final static String DOB_FORMAT = "yyyy-MM-dd";
	
	public static PersonalData readPersonalData(XMLEventReader eventReader) throws XMLStreamException{
		XMLEvent firstEvent = eventReader.nextEvent();
		
		if(!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read Personal Data but not a start element: found event of type " + 
											firstEvent.getEventType());
		}
		
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(PERSONAL_DATA)){
			throw new IllegalStateException("Attempting to read Personal Data at the wrong start element: found event of type " +
											firstEvent.asStartElement().getName());
		}
		
		PersonalData data = null;
		Date dob = null;
		String ssn = null;
		boolean finished = false;
		
		while(!finished) {
			XMLEvent event = eventReader.nextEvent();
			
			if(event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				
				if(startElement.getName().getLocalPart().equals(DOB)) {
					dob 		= XMLReaderUtils.readDate(eventReader, DOB, DOB_FORMAT);
				}
				
				else if (startElement.getName().getLocalPart().equals(SSN)) {
					ssn 		= XMLReaderUtils.readCharacters(eventReader, SSN);
				}
				
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
				}
			}
			
			else if (event.isEndElement()){
				EndElement endElement = event.asEndElement();
				
				if(endElement.getName().getLocalPart().equals(PERSONAL_DATA)) {
					data 		= new PersonalData(ssn, dob);
					finished 	= true;
				}
			}
		}
		
		return data;
	}
	
	/*
	 * readPatient(XMLEventReader):
	 * - Instantiates an XMLEvent with the next Event in the Event Reader
	 * - Checks to see if the event is a Start Element and an Element of a Patient
	 * - Reads the Patient, first through attributes then through events until end Event is found
	 * 	- For Attributes:
	 * 		- Assigns patientID attributes to id
	 *  	- If the attribute is of another type, it sends an error and skips it
	 * 	- For Events:
	 * 		- Checks for start and end events
	 *  	- If start event:
	 *   		- Assigns name and data events to name and data respectively
	 *   		- If the events are of another type, it sends an error and skips
	 *  	- If end event:
	 *   		- Retrieves the event
	 *   		- Checks for valid existence
	 *   		- Creates the return object of type Patient
	 *  	- If other type of event:
	 *   		- Skips the event
	 *  	- Sets finished condition to true
	 * - Returns a PatientImpl with read elements
	 */
	
	public static PatientImpl readPatient(XMLEventReader eventReader) throws XMLStreamException{
		XMLEvent firstEvent = eventReader.nextEvent();
		
		if(!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a Patient but not a start element: found event of type " + 
											firstEvent.getEventType());
		}
		
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(PATIENT)){
			throw new IllegalStateException("Attempting to read a Patient at the wrong start element: found event of type " +
											firstEvent.asStartElement().getName());
		}
		
		int 				id 			= 0;
		Boolean			active		= false;
		Iterator<Attribute> attributes 	= firstEvent.asStartElement().getAttributes();
		
		while(attributes.hasNext()) {
			Attribute attribute = attributes.next();
			
			if (attribute.getName().getLocalPart().equals(PATIENT_ID)) {
				id = Integer.valueOf(attribute.getValue());
			}
			
			else if(attribute.getName().getLocalPart().equals(ACTIVE_STATUS)) {
				active = Boolean.valueOf(attribute.getValue());
			}
			
			else {
				System.err.println("Found unknown attribute, ignoring; found: " + attribute.getName());
			}
		}
		
		PatientImpl 	patient 	= null;
		Name 			name 		= null;
		PersonalData 	data		= null;
		boolean 		finished 	= false;
		
		while(!finished) {
			XMLEvent event 	= eventReader.peek();
			
			if(event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				
				if(startElement.getName().getLocalPart().equals(NAME)) {
					name = readName(eventReader);
				}
				
				else if(startElement.getName().getLocalPart().equals(PERSONAL_DATA)) {
					data = readPersonalData(eventReader);
				}
				
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
					event = eventReader.nextEvent();
				}
				
			}
			
			else if (event.isEndElement()) {
				event 					= eventReader.nextEvent();
				EndElement endElement 	= event.asEndElement();
				
				if(endElement.getName().getLocalPart().equals(PATIENT)) {
					patient 	= new PatientImpl(data, name, active, id);
					finished 	= true;
				}
			}
			
			else {
				event = eventReader.nextEvent();
			}
		}
		
		return patient;
	}
	
	/*
	 * readDoctor(XMLEventReader):
	 * - Instantiates an XMLEvent with the next Event in the Event Reader
	 * - Checks to see if the event is a Start Element and an Element of a Doctor
	 * - Reads the Doctor, first through attributes then through events until end Event is found
	 * 	- For Attributes:
	 * 		- Assigns doctorID attributes to id
	 *  	- If the attribute is of another type, it sends an error and skips it
	 * 	- For Events:
	 * 		- Checks for start and end events
	 *  	- If start event:
	 *   		- Assigns name and data events to name and data respectively
	 *   		- If the events are of another type, it sends an error and skips
	 *  	- If end event:
	 *   		- Retrieves the event
	 *   		- Checks for valid existence
	 *   		- Creates the return object of type Patient
	 *  	- If other type of event:
	 *   		- Skips the event
	 *  	- Sets finished condition to true
	 * - Returns a Patient with read elements
	 */
	
	public static DoctorImpl readDoctor(XMLEventReader eventReader) throws XMLStreamException{
		XMLEvent firstEvent = eventReader.nextEvent();
		
		if(!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a Doctor but not a start element: found event of type " + 
											firstEvent.getEventType());
		}
		
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(DOCTOR)){
			throw new IllegalStateException("Attempting to read a Doctor at the wrong start element: found event of type " +
											firstEvent.asStartElement().getName());
		}
		
		int 				id 			= 0;
		Boolean				active		= false;
		Iterator<Attribute> attributes 	= firstEvent.asStartElement().getAttributes();
		
		while(attributes.hasNext()) {
			Attribute attribute = attributes.next();
			
			if (attribute.getName().getLocalPart().equals(DOCTOR_ID)) {
				id = Integer.valueOf(attribute.getValue());
			}
			
			else if(attribute.getName().getLocalPart().equals(ACTIVE_STATUS)) {
				active = Boolean.valueOf(attribute.getValue());
			}
			
			else {
				System.err.println("Found unknown attribute, ignoring; found: " + attribute.getName());
			}
		}
		 
		DoctorImpl 			doctor	 	= null;
		Name 				name 		= null;
		PersonalData 		data		= null;
		String				spec		= null;
		boolean 			finished 	= false;
		
		while(!finished) {
			XMLEvent event 	= eventReader.peek();
			
			if(event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				
				if(startElement.getName().getLocalPart().equals(NAME)) {
					name = readName(eventReader);
				}
				
				else if(startElement.getName().getLocalPart().equals(PERSONAL_DATA)) {
					data = readPersonalData(eventReader);
				}
				
				else if(startElement.getName().getLocalPart().equals(MEDICAL_SPECIALTY)) {
					event = eventReader.nextEvent(); // to my understanding, this first one puts the 'specialty' element in the reader
					event = eventReader.nextEvent(); //so this extra step is taken to put the value of the element in the reader
					spec = event.asCharacters().getData();
				}
				
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
					event = eventReader.nextEvent();
				}
				
			}
			
			else if (event.isEndElement()) {
				event 					= eventReader.nextEvent();
				EndElement endElement 	= event.asEndElement();
				
				if(endElement.getName().getLocalPart().equals(DOCTOR)) {
					doctor	 	= new DoctorImpl(data, name, active, id, spec);
					finished 	= true;
				}
			}
			
			else {
				event = eventReader.nextEvent();
			}
		}
		
		return doctor;
	}
	
	/*
	 * readVisit(XMLEvent):
	 * - Instantiates an XMLEvent with the next Event in the Event Reader
	 * - Checks to see if the event is a Start Element and an Element of a Visit
	 * - Instantiates an empty VisitImpl<Integer,Integer> object
	 * - Reads the Visit through its attributes, adding attribute values to variables in the VisitImpl obj
	 */
	
	private final static SimpleDateFormat VISIT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
	
	public static VisitImpl<Integer, Integer> readVisit(XMLEventReader eventReader) throws XMLStreamException {
		XMLEvent firstEvent = eventReader.nextEvent();
		
		if(!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a Visit but not a start element: found event of type " + 
											firstEvent.getEventType());
		}
		
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(VISIT)){
			throw new IllegalStateException("Attempting to read a Visit at the wrong start element: found event of type " +
											firstEvent.asStartElement().getName());
		}
		
		VisitImpl<Integer,Integer> 	visit 	= null;
		Integer 					docID 	= null, patID = null;
		Date 						date	= null;
		Iterator<Attribute> attributes 		= firstEvent.asStartElement().getAttributes();
		
		while(attributes.hasNext()) {
			Attribute attribute = attributes.next();
			
			if (attribute.getName().getLocalPart().equals(DOCTOR_ID)) {
				docID = Integer.valueOf(attribute.getValue());
			}
			
			else if (attribute.getName().getLocalPart().equals(PATIENT_ID)) {
				patID = Integer.valueOf(attribute.getValue());
			}
			
			else if (attribute.getName().getLocalPart().equals(VISIT_DATE)) {
				try {
					date = VISIT_DATE_FORMAT.parse(attribute.getValue().toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			else {
				System.err.println("Found unknown attribute, ignoring; found: " + attribute.getName());
			}
		}
		
		visit = new VisitImpl<Integer,Integer>(patID, docID, date);
		
		return visit;
	}
	
	/*
	 * readSchedulingXML(String):
	 * - Instantiates SchedulerData object 'data'
	 * - Creates XMLInputFactory, Path, BufferedReader, and EventReader
	 * - Reads XML file:
	 *  - Checks if there is an event next
	 *  - Peaks next event
	 *  - Checks if next event is a Start Element
	 *  	- If it is a Start Element, 
	 *   		- Checks to see if it is of type Name, PersonalData, MedicalSpecialty, Doctor, Patient, Visit, or Root
	 *   		- If it is of type Patient, Doctor, or Visit 
	 *   			- Adds the data to an object of that type and adds the object to the SchedulerData object
	 *      	- If it is of another identifiable type, it skips to the next element
	 *      	- If it is of an type not mentioned above, it prints an error and skips to the next element
	 *   	- If it is not a Start Element, it moves to the next event 
	 * - Returns 'data'
	 */
	
	public static SchedulerData readSchedulingXML(String xmlFileName) 
			throws FileNotFoundException, IOException, XMLStreamException {
		SchedulerData 	data 		= new SchedulerData();
		XMLInputFactory inFactory 	= XMLInputFactory.newInstance();
		Path 			xmlFilePath = Paths.get(xmlFileName);
		Reader 			in 			= Files.newBufferedReader(xmlFilePath, StandardCharsets.UTF_8);
		XMLEventReader 	eventReader = inFactory.createXMLEventReader(in);
		
		while(eventReader.hasNext()) {
			XMLEvent event = eventReader.peek();
			
			if(event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				
				if( startElement.getName().getLocalPart() == (NAME) 			|| 
					startElement.getName().getLocalPart() == (PERSONAL_DATA)	||
					startElement.getName().getLocalPart() == (MEDICAL_SPECIALTY)||
					startElement.getName().getLocalPart() == (ROOT)
					) {
					event = eventReader.nextEvent();
				} 
				
				else if(startElement.getName().getLocalPart() == (PATIENT)){
					Patient pat = readPatient(eventReader);
					data.addPatient(pat);
				} 
				
				else if(startElement.getName().getLocalPart() == (DOCTOR)){
					Doctor doc = readDoctor(eventReader);
					data.addDoctor(doc);
				}
				
				else if(startElement.getName().getLocalPart() == (VISIT)){
					Visit<Integer,Integer> vis = readVisit(eventReader);
					data.addVisit(vis);
				}
				
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
					event = eventReader.nextEvent();
				}
				
			} else {
				event = eventReader.nextEvent();
			}
		}
		
		eventReader.close();
		
		return data;
	}
}