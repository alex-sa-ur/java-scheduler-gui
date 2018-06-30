package edu.miami.cis324.hw4.alejandrosanchez;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

/*
 * Main class for reading from Scheduler XML files
 * - Reads data from an XML file
 * - Puts data in a SchedulerData object
 * - Prints all upcoming visits by date
 */

/**
 * @author alejandrosanchez
 *
 */

public class SchedulerXMLReadTest {
	
	private final static String INPUT_FILE = "resources/SchedulerData.xml";

	public static void main(String[] args) throws XMLStreamException, IOException {
		run();
	}
	
	private static void run() throws FileNotFoundException, XMLStreamException, IOException {
		
		SchedulerData data = SchedulerXMLReaderUtils.readSchedulingXML(INPUT_FILE);
		
		VisitUtils.printUpcomingByDate(data.getListVis(), data.getListPat(), data.getListDoc());
	}

}
