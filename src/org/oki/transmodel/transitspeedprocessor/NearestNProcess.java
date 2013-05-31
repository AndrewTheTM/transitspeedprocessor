package org.oki.transmodel.transitspeedprocessor;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class NearestNProcess implements Callable {
	public static ArrayList<TransitGPSData> transitGPS;
	public static TransitGPSData tgps;

	@Override
	public Object call() throws Exception {
		//System.out.println("NearestN Working on "+transitGPS.indexOf(tgps)+" of "+transitGPS.size());
		for(TransitGPSData tgi:transitGPS){
			if(tgps.N==tgi.N && tgps.FileID==tgi.FileID && tgps.TimeSeconds!=tgi.TimeSeconds){
				//Same N, Same FileID, not the same time
				if((tgps.TimeSeconds-tgi.TimeSeconds)<600){
					if(tgps.DistToN<tgi.DistToN){
						tgi.removeMe=true;
					}
				} 
			}
		}
		return null;
	}
	
	
}
