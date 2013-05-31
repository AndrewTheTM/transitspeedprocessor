package org.oki.transmodel.transitspeedprocessor;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class DistToNProcess implements Callable {
	public TransitGPSData tgps;
	public ArrayList<NodeData> nodeData;
	
	@Override
	public Object call() throws Exception {
		for(NodeData nd:nodeData){
			if(nd.N==tgps.N && !nd.equals(tgps)){
			tgps.DistToN=Math.sqrt(Math.pow((nd.X-tgps.X),2)+Math.pow((nd.Y-tgps.Y),2));
			}
		}
		return null;
	}
	
	public void setTGPS(TransitGPSData t){
		tgps=t;
	}
}
