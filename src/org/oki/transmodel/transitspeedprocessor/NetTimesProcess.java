package org.oki.transmodel.transitspeedprocessor;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class NetTimesProcess implements Callable{
	private NetworkData link;
	private ArrayList<TransitGPSData> tGPS;
	private String timePeriod;

	NetTimesProcess(NetworkData l, ArrayList<TransitGPSData> tGPS, String t){
		this.link=l;
		this.tGPS=tGPS;
		this.timePeriod=t;
		
	}
	
	@Override
	public Object call() throws Exception{
		for(TransitGPSData n:tGPS){
			if((timePeriod.equalsIgnoreCase("AM") && n.TimeSeconds>23400 && n.TimeSeconds<34200) ||
					(timePeriod.equalsIgnoreCase("MD") && n.TimeSeconds>34200 && n.TimeSeconds<66600)){
				if(n.N==link.A){
					for(TransitGPSData bn:tGPS){
						if(bn.N==link.B){
							double transitTime=(bn.TimeSeconds-n.TimeSeconds)/60;
							if(transitTime>0 && transitTime<(10*link.Time) && n.Mode==bn.Mode && transitTime<600){
								if(n.Mode==4)
									link.LocalTransitTimes.add((double) (bn.TimeSeconds-n.TimeSeconds));
								else
									link.ExpressTransitTimes.add((double) (bn.TimeSeconds-n.TimeSeconds));
							}
						}
					}
				}
			}
		}
		return null;
	}
}
