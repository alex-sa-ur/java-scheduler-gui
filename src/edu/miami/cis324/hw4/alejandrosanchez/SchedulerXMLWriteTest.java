package edu.miami.cis324.hw4.alejandrosanchez;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

/*
 * Main class for writing to a Scheduler XML file
 * - Reads data from an XML file
 * - Puts data in a SchedulerData object
 * - Writes data from the SchedulerData object into an XML file
 * - Tests file by:
 * 	- Reading from new output file
 *  - Printing data read from new file
 */

/**
 * @author alejandrosanchez
 *
 */

public class SchedulerXMLWriteTest {

	private final static String INPUT_FILE = "resources/SchedulerDataOutTest.xml";
	private final static String OUTPUT_FILE = "resources/SchedulerDataOutTest2.xml";
	
	public static void main(String[] args) throws XMLStreamException, IOException {
		run();
	}
	
	private static void run() throws FileNotFoundException, IOException, XMLStreamException {
		SchedulerData data = SchedulerXMLReaderUtils.readSchedulingXML(INPUT_FILE);
		
		SchedulerXMLWriterUtils.writeSchedulingXML(OUTPUT_FILE, data);
		
		data = SchedulerXMLReaderUtils.readSchedulingXML(OUTPUT_FILE);
		
		VisitUtils.printUpcomingByDate(data.getListVis(), data.getListPat(), data.getListDoc());
	}
}
