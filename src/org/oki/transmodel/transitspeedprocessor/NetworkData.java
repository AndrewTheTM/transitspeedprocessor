package org.oki.transmodel.transitspeedprocessor;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author arohne
 * Holds network data objects
 */
public class NetworkData {
	int A;
	int B;
	int AdmClass;
	double Speed;
	double Time;
	int AreaType;
	int Lanes;
	ArrayList<Double> LocalTransitTimes;
	ArrayList<Double> ExpressTransitTimes;
	NetworkData(){
		A=0;
		B=0;
		AdmClass=0;
		Speed=0;
		Time=0;
		AreaType=0;
		Lanes=0;
		LocalTransitTimes=new ArrayList<Double>();
		ExpressTransitTimes=new ArrayList<Double>();
	}
	
	/**
	 * Network Data Quick-add method
	 * @param a The link's A-node value
	 * @param b The link's B-node value
	 * @param admclass The link's facility type code
	 * @param speed The link's speed
	 * @param areatype The link's area type
	 * @param lanes The link's number of lanes
	 */
	NetworkData(int a, int b, int admclass, double speed, double time, int areatype, int lanes){
		A=a;
		B=b;
		AdmClass=admclass;
		Speed=speed;
		Time=time;
		AreaType=areatype;
		Lanes=lanes;
		LocalTransitTimes=new ArrayList<Double>();
		ExpressTransitTimes=new ArrayList<Double>();
	}
	
	/**
	 * Gets the average local bus time
	 * @return the average time in minutes
	 */
	public double getAverageLocalTime(){
		int cnt = 0;
		double sum = 0;
		for(double d:LocalTransitTimes){
			cnt++;
			sum+=d;
		}
		if(cnt>0)
			return (sum/cnt)/60;
		else
			return 0;
	}
	
	/**
	 * Gets the minimum observed local bus time
	 * @return the minimum observed local bus time in minutes
	 */
	public double getMinLocalTime(){
		double minTime=9999999;
		for(double d:LocalTransitTimes)
			if(d<minTime)
				minTime=d;
		if(minTime==9999999)
			minTime=0;
		return minTime/60;
	}
	
	/**
	 * Gets the maximum observed local bus time
	 * @return the maximum observed local bus time in minutes
	 */
	public double getMaxLocalTime(){
		double maxTime=0;
		for(double d:LocalTransitTimes)
			if(d>maxTime)
				maxTime=d;
		return maxTime/60;
	}
	
	/**
	 * Gets the median local bus time
	 * @return the median local bus time in minutes
	 */
	public double getMedianLocalTime(){
		int cnt=LocalTransitTimes.size();
		ArrayList<Double> tempTimes=LocalTransitTimes; //Not sure if I want to screw with the index order here
		Collections.sort(tempTimes);
		if(cnt==0)
			return 0;
		else if(cnt==1)
			return tempTimes.get(0)/60;
		else if(cnt%2==0){
			return ((tempTimes.get(((Double)((double)cnt/2)).intValue())+tempTimes.get(((Double)((double)cnt/2)).intValue()-1))/2)/60;
		}else{
			return (tempTimes.get(((Double)((double)cnt/2)).intValue()))/60;
		}
	}
	
	/**
	 * Gets the average express bus time
	 * @return the average express in minutes
	 */
	public double getAverageExpressTime(){
		int cnt = 0;
		double sum = 0;
		for(double d:ExpressTransitTimes){
			cnt++;
			sum+=d;
		}
		if(cnt>0)
			return (sum/cnt)/60;
		else
			return 0;
	}
	
	/**
	 * Gets the minimum observed express bus time
	 * @return the minimum observed express bus time in minutes
	 */
	public double getMinExpressTime(){
		double minTime=9999999;
		for(double d:ExpressTransitTimes)
			if(d<minTime)
				minTime=d;
		if(minTime==9999999)
			minTime=0;
		return minTime/60;
	}
	
	/**
	 * Gets the maximum observed express bus time
	 * @return the maximum observed express bus time in minutes
	 */
	public double getMaxExpressTime(){
		double maxTime=0;
		for(double d:ExpressTransitTimes)
			if(d>maxTime)
				maxTime=d;
		return maxTime/60;
	}
	
	/**
	 * Gets the median express bus time
	 * @return the median express bus time in minutes
	 */
	public double getMedianExpressTime(){
		int cnt=ExpressTransitTimes.size();
		ArrayList<Double> tempTimes=ExpressTransitTimes; //Not sure if I want to screw with the index order here
		Collections.sort(tempTimes);
		if(cnt==0)
			return 0;
		else if(cnt==1)
			return tempTimes.get(0)/60;
		else if(cnt%2==0){
			return ((tempTimes.get(((Double)((double)cnt/2)).intValue())+tempTimes.get(((Double)((double)cnt/2)).intValue()-1))/2)/60;
		}else{
			return (tempTimes.get(((Double)((double)cnt/2)).intValue()))/60;
		}
	}
}
