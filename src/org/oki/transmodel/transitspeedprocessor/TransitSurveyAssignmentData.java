package org.oki.transmodel.transitspeedprocessor;

//TODO Document
/**
 * @author arohne
 * Class to hold assignment data
 */
public class TransitSurveyAssignmentData {
	//TODO: Write transit survey assignment data structure
	int assignmentID;
	int tripOrder;
	String route;
	String direction;
	String timeOfDay;
	/**
	 * Quick-Add method
	 * @param AssignmentID Assignment ID
	 * @param TripOrder Trip order in assignment
	 * @param Route Route covered
	 * @param Direction Direction of Route (in text, so "Inbound", "Outbound", etc.)
	 * @param TimeOfDay Time of day of route (in text, so "AM Peak", "Mid-Day", etc.)
	 */
	TransitSurveyAssignmentData(int AssignmentID, int TripOrder, String Route, String Direction, String TimeOfDay){
		assignmentID=AssignmentID;
		tripOrder=TripOrder;
		route=Route;
		direction=Direction;
		timeOfDay=TimeOfDay;
	}
}
