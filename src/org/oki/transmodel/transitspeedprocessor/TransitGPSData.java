package org.oki.transmodel.transitspeedprocessor;

import java.util.Date;

/**
 * @author arohne
 * Class to hold transit GPS Data
 */
public class TransitGPSData {
	int FileID;
	Date SurveyDate;
	double Latitude;
	double Longitude;
	double X;
	double Y;
	int N;
	int TimeSeconds;
	double DistToN;
	int Mode;
	String AgencyCode;
	boolean Crosstown;
	boolean removeMe;
	TransitGPSData(){
		FileID=0;
		//SurveyDate.set(2001,1,1);
		Latitude=0.0;
		Longitude=0.0;
		X=0.0;
		Y=0.0;
		N=0;
		TimeSeconds=0;
		DistToN=0.0;
		Mode=0;
		Crosstown=false;
		removeMe=false;
	}
	/**
	 * Quick-add method for transit GPS data
	 * @param fileid The file id of the data (used to link to the survey assignment data)
	 * @param surveydate The date the GPS data was collected.
	 * @param latitude The GPS latitude
	 * @param longitude The GPS longitude
	 * @param x The x-coordinate of the GPS point (likely in State Plane)
	 * @param y The y-coordinate of the GPS point (likely in State Plane)
	 * @param n The nearest node number
	 * @param timesec The number of seconds since midnight
	 */
	TransitGPSData(int fileid,Object surveydate, double latitude, double longitude, double x, double y, int n, int timesec){
		FileID=fileid;
		Latitude=latitude;
		Longitude=longitude;
		X=x;
		Y=y;
		N=n;
		TimeSeconds=timesec;
		SurveyDate=(Date) surveydate;
		removeMe=false;
	}
}
