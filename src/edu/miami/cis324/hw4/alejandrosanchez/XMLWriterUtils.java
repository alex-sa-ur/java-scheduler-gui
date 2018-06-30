package edu.miami.cis324.hw4.alejandrosanchez;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;

/*
 * Class used for:
 * - Writing XML Events common to all XMLWriter functions to an XML file, including
 *  - Nodes
 *  - Dates
 */

/**
 * @author alejandrosanchez
 *
 */

public abstract class XMLWriterUtils {
	/*
	 * Methods:
	 * getIndentation(XMLEventFactory, int):							returns a Characters object containing the needed number
	 * 																	of tabs to indent to a desired value defined by the int
	 * 
	 * writeNode(XMLEventFactory, XMLEventWriter, String, String, int):	creates start node, content, end node, and line feed for
	 * 																	a new Node with the desired information and at the desired
	 * 																	level
	 * 																	
	 * 
	 * writeDate(XMLEventFactory, XMLEventWriter, String, Date, int):	takes a Date and formats it into the required String 
	 * 																	format, then calls writeNode to add it to the file
	 * 
	 */
	
	/*
	 * getIndentation(XMLEventFactory, int):
	 * - Creates an array of the desired size passed in the int parameter
	 * - Fills the array with tab ('\t') values
	 * - Returns a Characters object containing ignorable space equivalent to the array of tabs
	 */
	
	public static Characters getIndentation(XMLEventFactory eventFactory, int level){
		char[] tabs = new char[level];
		
		Arrays.fill(tabs, '\t');
		
		return eventFactory.createIgnorableSpace(String.valueOf(tabs));
	}
	
	/*
	 * writeNode(XMLEventFactory, XMLEventWriter, String, String, int):
	 * - Creates a start element with the desired name
	 * - Creates a Characters object containing the desired content
	 * - Creates an end element that matches the start element name
	 * - Calls getIndentaion to get to the correct level
	 * - Adds the start element to the writer
	 * - Adds the content Characters object to the writer
	 * - Adds the end element to the writer
	 * - Adds ignorable space ('\n') to the writer
	 */
	
	public static void writeNode(XMLEventFactory eventFactory, XMLEventWriter eventWriter, String name, String value, int level) 
			throws XMLStreamException {
		StartElement startElement = eventFactory.createStartElement("", "", name);
		Characters charValue = eventFactory.createCharacters(value);
		EndElement endElement = eventFactory.createEndElement("", "", name);
		
		eventWriter.add(getIndentation(eventFactory, level));
		eventWriter.add(startElement);
		eventWriter.add(charValue);
		eventWriter.add(endElement);
		eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	}
	
	/*
	 * writeDate(XMLEventFactory, XMLEventWriter, String, Date, int):
	 * - Creates a SimpleDateFormat using the xmlStandardDateFormat variable
	 * - Formats the Date variable in accordance with the SimpleDateFormat created
	 * - Calls writeNode() to create a node for the formatted string version of the Date
	 */
	
	public static String xmlStandardDateFormat = "yyyy-MM-dd";

	public static void writeDate(XMLEventFactory eventFactory, XMLEventWriter eventWriter, String name, Date date, int level) 
			throws XMLStreamException {
		DateFormat df = new SimpleDateFormat(xmlStandardDateFormat);
		String dateStr = df.format(date.getTime()).toString();
		
		writeNode(eventFactory, eventWriter, name, dateStr, level);
	}
}
