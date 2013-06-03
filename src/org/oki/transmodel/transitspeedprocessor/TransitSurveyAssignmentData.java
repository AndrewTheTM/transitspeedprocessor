package org.oki.transmodel.transitspeedprocessor;

/**
 * Class for transit survey assignment data (assignment ID, trip taken)
 * @author arohne
 */
public class TransitSurveyAssignmentData {
	int assignmentID;
	int tripOrder;
	String route;
	String direction;
	String timeOfDay;
	int Mode;
	boolean Crosstown;
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
		String rNum = null;

		try{
			if(Route.lastIndexOf(".")>0)
				rNum=Route.substring(Route.lastIndexOf(".")+1);
			else
				rNum=Route.substring(Route.lastIndexOf("-")+1);
			
			if(rNum.equalsIgnoreCase("31")||rNum.equalsIgnoreCase("41")||rNum.equalsIgnoreCase("51"))
				Crosstown=true;
			
			if(rNum.length()==1)
				Mode=4; //These are the single-digit routes, can't have an X or JC and everything breaks
			else if(rNum.substring(rNum.length()-1, rNum.length()).equalsIgnoreCase("X") || rNum.substring(rNum.length()-2, rNum.length()-1).equalsIgnoreCase("X") || rNum.substring(rNum.length()-2, rNum.length()).equalsIgnoreCase("JC") || rNum.equalsIgnoreCase("71XFE"))
				Mode=5;
			else
				Mode=4;
		}catch(Exception ex){
			System.out.println("ERROR. rNum="+rNum+".  Route="+Route);
			ex.printStackTrace();
		}
				
	}
}
