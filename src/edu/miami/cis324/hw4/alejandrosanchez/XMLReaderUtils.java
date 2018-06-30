package edu.miami.cis324.hw4.alejandrosanchez;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

/*
 * Class used for:
 * - Reading XML Events common to all XMLReader functions from an event reader, including
 *  - Dates
 *  - Characters
 */

/**
 * @author alejandrosanchez
 *
 */

public abstract class XMLReaderUtils {
	/*
	 * Methods:
	 * readCharacters(XMLEventReader, String): 		reads data from an Event in the EventReader passed
	 * 												adds characters of the event to a string variable chars
	 * 												returns the string variable value
	 * 
	 * readDate(XMLEventReader, String, String):	reads data from an Event in the EventReader passed
	 * 												tries to convert the read data into a Date
	 * 												if successful, it'll add the data to a Date variable and return the value
	 * 												
	 */
	
	/*
	 * readCharacters(XMLEventReader, String):
	 * - Gets the next event in the XMLEvent Reader object passed
	 * - Checks to see if it is a start element
	 * - Instantiates a String variable chars, which contains the characters found in the event
	 * - Returns the chars variable value
	 */
	
	public static String readCharacters(XMLEventReader eventReader, String elementName) throws XMLStreamException {
		/*XMLEvent firstEvent = eventReader.nextEvent();
		
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a " + elementName + 
											" but not a start element: found event of type " + firstEvent.getEventType());
		}
		
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(elementName)) {
			throw new IllegalStateException("Attempting to read a " + elementName + " at the wrong start element: found " + 
											firstEvent.asStartElement().getName());
		}
		*/
		String chars = " ";
		
		if (!eventReader.peek().isEndElement()){
			chars = eventReader.nextEvent().asCharacters().getData();
		}
		
		return chars;
	}
	
	/*
	 * readDate(XMLEventReader, String, String):
	 * - Gets the next event in the XMLEvent Reader object passed
	 * - Checks to see if it is a start element
	 * - Instantiates a String variable dateStr, which contains the data found in the event
	 * - Instantiates a SimpleDateFormat variable df with the passed dateFormat value
	 * - Tries to parse the dateStr value with the df variable
	 * - Instantiates a Date variable with the date value of df
	 * - Returns the date variable value
	 */
	
	public static Date readDate(XMLEventReader eventReader, String elementName, String dateFormat) throws XMLStreamException {
		/*XMLEvent firstEvent = eventReader.nextEvent();
		
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a " + elementName + 
											" but not a start element: found event of type " +  firstEvent.getEventType());
		}
		
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(elementName)) {
			throw new IllegalStateException("Attempting to read a " + elementName + " at the wrong start element: found " + 
											firstEvent.asStartElement().getName());
		}
		*/
		Date date = new Date();
		if (!eventReader.peek().isEndElement()){
			String dateStr = eventReader.nextEvent().asCharacters().getData();
			SimpleDateFormat df = new SimpleDateFormat(dateFormat);
			
			try {
				df.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			date = df.getCalendar().getTime();
		}
		
		return date;
	}
}
