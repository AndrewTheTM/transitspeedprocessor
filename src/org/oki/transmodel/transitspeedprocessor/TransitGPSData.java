package org.oki.transmodel.transitspeedprocessor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author arohne
 * Class to hold transit GPS Data
 */
public class TransitGPSData {
	int FileID;
	Calendar SurveyDate;
	double Latitude;
	double Longitude;
	double X;
	double Y;
	int N;
	int TimeSeconds;
	double DistToN;
	int Mode;
	boolean Crosstown;
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
	}
	TransitGPSData(int fileid,Object surveydate, double latitude, double longitude, double x, double y, int n, int timesec){
		FileID=fileid;
		Latitude=latitude;
		Longitude=longitude;
		X=x;
		Y=y;
		N=n;
		TimeSeconds=timesec;
		DateFormat df=new SimpleDateFormat("mm/dd/yyyy"); //FIXME: The survey date is not working
		try {
			SurveyDate.setTime(df.parse(surveydate.toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//TODO: Quick-add method
}
