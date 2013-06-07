package org.oki.transmodel.transitspeedprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NetTimes implements Runnable{
	private ArrayList<NetworkData> links;
	private ArrayList<TransitGPSData> tGPS;
	String timePeriod;

	NetTimes(ArrayList<NetworkData> links,ArrayList<TransitGPSData> tGPS, String t){
		this.links=links;
		this.tGPS=tGPS;
		this.timePeriod=t;
	}
	
	@Override
	public void run(){
		List<Future> futuresList = new ArrayList<Future>();
		
		int nrOfProcessors=Runtime.getRuntime().availableProcessors()-1; //No, I'm not going to totally drill the computer so much so an MP3 can't play and you can't go screw around on Twitter and Reddit!
		ExecutorService eservice = Executors.newFixedThreadPool(nrOfProcessors);
		
		for(NetworkData link:links){
			futuresList.add(eservice.submit(new NetTimesProcess(link,tGPS,timePeriod)));
		}

		Object taskResult;
		for(Future future:futuresList){
			try{
				taskResult=future.get();
			}catch(InterruptedException e){
				e.printStackTrace();
			}catch(ExecutionException e){
				e.printStackTrace();
			}finally{
				eservice.shutdown();
			}
		}
	}
}
