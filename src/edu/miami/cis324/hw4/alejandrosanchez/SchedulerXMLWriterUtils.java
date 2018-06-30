package edu.miami.cis324.hw4.alejandrosanchez;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

/*
 * Class used for:
 * - Getting data from a SchedulerData object
 * - Writing data obtained into an XML file
 */

/**
 * @author alejandrosanchez
 *
 */

public abstract class SchedulerXMLWriterUtils extends SchedulerXMLReadWriteUtils {
	private final static String NAMESPACE = "http://www.miami.edu/cis324/xml/scheduling";
	private final static String SCHEMA_INSTANCE_PREFIX = "xsi";
	private final static String SCHEMA_INSTANCE_NS = "http://www.w3.org/2001/XMLSchema-instance";
	private final static String SCHEMA_LOCATION_ATTRNAME = "schemaLocation";
	private final static String SCHEMA_FILE_NAME = "scheduling.xsd";
	
	/*
	 * Methods:
	 * writeName(XMLEventFactory,XMLEventWriter,Name,int):					writes events of type NAME to an XML file
	 * 
	 * writePersonalData(XMLEventFactory,XMLEventWriter,PersonalData,int):	writes events of type PERSONAL_DATA to an XML file
	 * 
	 * writeSpecialty(XMLEventFactory,XMLEventWriter,MedicalSpecialty,int):	writes events of type MEDICAL_SPECIALTY to an XML file
	 * 
	 * writePatient(XMLEventFactory,XMLEventWriter,Patient,int): 			writes events of type PATIENT to an XML file
	 * 
	 * writeDoctor(XMLEventFactory,XMLEventWriter,Visit,int): 				writes events of type DOCTOR to an XML file
	 * 
	 * writeVisit(XMLEventFactory,XMLEventWriter,Visit,int):				writes events of type VISIT to an XML file
	 * 
	 * writeSchedulingXML(String,SchedulerData):							writes the base events to an XML file
	 * 																		and calls adequate methods to add data
	 * 																		from a SchedulerData object
	 */
	
	/*
	 * writeName(XMLEventFactory,XMLEventWriter,Name,int):
	 * - Reaches the correct level of indentation using the level parameter
	 * - Adds a start element of type NAME and ignorable space
	 * - Writes a Node for FIRST_NAME and LAST_NAME at a sublevel of NAME
	 * - Adds an end element matching the NAME type and ignorable space
	 */
	
	public static void writeName(XMLEventFactory eventFactory, XMLEventWriter eventWriter, Name name, int level) 
			throws XMLStreamException{
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
		eventWriter.add(eventFactory.createStartElement("", "", NAME));
		eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		XMLWriterUtils.writeNode(eventFactory, eventWriter, FIRST_NAME, name.getFirstName(), level+1);
		XMLWriterUtils.writeNode(eventFactory, eventWriter, MID_NAME, name.getMidName(), level+1);
		XMLWriterUtils.writeNode(eventFactory, eventWriter, LAST_NAME, name.getLastName(), level+1);
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
		eventWriter.add(eventFactory.createEndElement("", "", NAME));
		eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	}
	
	/*
	 * writePersonalData(XMLEventFactory,XMLEventWriter,PersonalData,int):
	 * - Reaches the correct level of indentation using the level parameter
	 * - Adds a start element of type PERSONAL_DATA and ignorable space
	 * - Writes a Node for DOB and SSN at a sublevel of PERSONAL_DATA
	 * - Adds an end element matching the PERSONAL_DATA type and ignorable space
	 */
	
	public static void writePersonalData(XMLEventFactory eventFactory, XMLEventWriter eventWriter, PersonalData data, int level) 
			throws XMLStreamException{
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
		eventWriter.add(eventFactory.createStartElement("", "", PERSONAL_DATA));
		eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		XMLWriterUtils.writeDate(eventFactory, eventWriter, DOB, data.getDOB(), level+1);
		XMLWriterUtils.writeNode(eventFactory, eventWriter, SSN, data.getSSN(), level+1);
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
		eventWriter.add(eventFactory.createEndElement("", "", PERSONAL_DATA));
		eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	}
	
	/*
	 * writeSpecialty(XMLEventFactory,XMLEventWriter,MedicalSpecialty,int):
	 * - Reaches the correct level of indentation using the level parameter
	 * - Writes a Node for MEDICAL_SPECIALTY
	 */
	
	public static void writeSpecialty(XMLEventFactory eventFactory, XMLEventWriter eventWriter, MedicalSpecialty spec, int level) 
			throws XMLStreamException{
		XMLWriterUtils.writeNode(eventFactory, eventWriter, MEDICAL_SPECIALTY, spec.toString(), level);
	}
	
	/*
	 * writePatient(XMLEventFactory,XMLEventWriter,Patient,int):
	 * - Reaches the correct level of indentation using the level parameter
	 * - Adds a start element of type PATIENT and its PATIENT_ID and ACTIVE_STATUS attributes
	 * - Writes a Node for NAME and PERSONAL_DATA at a sublevel of PATIENT
	 * - Adds an end element matching the PATIENT type and ignorable space
	 */
	
	public static void writePatient(XMLEventFactory eventFactory, XMLEventWriter eventWriter, Patient pat, int level) 
			throws XMLStreamException{
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
	    eventWriter.add(eventFactory.createStartElement("", "", PATIENT));
	    eventWriter.add(eventFactory.createAttribute(PATIENT_ID, Integer.toString(pat.getPatID())));
	    eventWriter.add(eventFactory.createAttribute(ACTIVE_STATUS, Boolean.toString(pat.getActiveStatus())));
	    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		writeName(eventFactory, eventWriter, pat.getFullName(), level+1);
		writePersonalData(eventFactory, eventWriter, pat.getPersonalData(), level+1);
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
		eventWriter.add(eventFactory.createEndElement("", "", PATIENT));
		eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	}
	
	/*
	 * writeDoctor(XMLEventFactory,XMLEventWriter,Visit,int):
	 * - Reaches the correct level of indentation using the level parameter
	 * - Adds a start element of type DOCTOR and its DOCTOR_ID and ACTIVE_STATUS attributes
	 * - Writes a Node for NAME, PERSONAL_DATA, and MEDICAL_SPECIALTY at a sublevel of DOCTOR
	 * - Adds an end element matching the DOCTOR type and ignorable space
	 */
	
	public static void writeDoctor(XMLEventFactory eventFactory, XMLEventWriter eventWriter, Doctor doc, int level) 
			throws XMLStreamException{
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
	    eventWriter.add(eventFactory.createStartElement("", "", DOCTOR));
	    eventWriter.add(eventFactory.createAttribute(DOCTOR_ID, Integer.toString(doc.getDocID())));
	    eventWriter.add(eventFactory.createAttribute(ACTIVE_STATUS, Boolean.toString(doc.getActiveStatus())));
	    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		writeName(eventFactory, eventWriter, doc.getFullName(), level+1);
		writePersonalData(eventFactory, eventWriter, doc.getPersonalData(), level+1);
		writeSpecialty(eventFactory, eventWriter, doc.getMedSpec(), level+1);
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
		eventWriter.add(eventFactory.createEndElement("", "", DOCTOR));
		eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	}
	
	/*
	 * writeVisit(XMLEventFactory,XMLEventWriter,Visit,int):
	 * - Reaches the correct level of indentation using the level parameter
	 * - Adds a start element of type VISIT and its DOCTOR_ID, PATIENT_ID, and VISIT_DATE attributes
	 */
	
	public static void writeVisit(XMLEventFactory eventFactory, XMLEventWriter eventWriter, Visit<Integer,Integer> vis, int level) 
			throws XMLStreamException{
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
	    eventWriter.add(eventFactory.createStartElement("", "", VISIT));
	    eventWriter.add(eventFactory.createAttribute(DOCTOR_ID, Integer.toString(vis.getHost())));
	    eventWriter.add(eventFactory.createAttribute(PATIENT_ID, Integer.toString(vis.getVisitor())));
	    eventWriter.add(eventFactory.createAttribute(VISIT_DATE, DateUtil.dateToString(vis.getDate(), 
	    		new SimpleDateFormat(xmlStandardDateFormat))));
	}
	
	/*
	 * writeSchedulingXML(String,SchedulerData):
	 * - Creates an XMLOutputFactory, XMLEventWriter, and XMLEventFactory
	 * - Writes a start tag and ignorable space
	 * - Writes the root element and adds namespaces
	 * - Adds schema location attributes and ignorable space
	 * - Runs through all Patients in the SchedulerData object, adding them to the file
	 * - Runs through all Doctors in the SchedulerData object, adding them to the file
	 * - Runs through all Visits in the SchedulerData object, adding them to the file
	 * - Adds endDocument to the Writer, and then closes it
	 */
	
	public static void writeSchedulingXML(String outFile, SchedulerData data) 
			throws XMLStreamException, IOException{
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		Path outputFilePath = Paths.get(outFile);
	    Writer outputFile = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8);
	    XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(outputFile);
	    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
	    
	    eventWriter.add(eventFactory.createStartDocument("UTF-8", "1.0"));
	    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	    eventWriter.add(eventFactory.createStartElement("", "", ROOT));
	    eventWriter.setDefaultNamespace(SchedulerXMLWriterUtils.NAMESPACE);
		eventWriter.add(eventFactory.createNamespace(NAMESPACE));
	    eventWriter.add(eventFactory.createNamespace(SCHEMA_INSTANCE_PREFIX, SCHEMA_INSTANCE_NS));
	    
	    String schemaLocationArg = NAMESPACE + " " + SCHEMA_FILE_NAME;
	    
	    eventWriter.add(eventFactory.createAttribute(SCHEMA_INSTANCE_PREFIX, SCHEMA_INSTANCE_NS, SCHEMA_LOCATION_ATTRNAME, 
	    		schemaLocationArg));
	    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	    
	    for (Doctor doc : data.getListDoc()) {
			writeDoctor(eventFactory, eventWriter, doc, 1);
		    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		}
	    
		for (Patient pat : data.getListPat()) {
			writePatient(eventFactory, eventWriter, pat, 1);
		    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		}
		
		for (Visit<Integer,Integer> vis : data.getListVis()) {
			writeVisit(eventFactory, eventWriter, vis, 1);
		    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		}
		
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}
}
