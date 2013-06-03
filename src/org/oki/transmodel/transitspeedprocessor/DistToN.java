package org.oki.transmodel.transitspeedprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DistToN implements Runnable{
	private ArrayList<TransitGPSData> transitGPS;
	private ArrayList<NodeData> nodeData;
	
	DistToN(ArrayList<TransitGPSData> t, ArrayList<NodeData> n){
		this.transitGPS=t;
		this.nodeData=n;
	}
	
	@Override
	public void run() {
		List<Future> futuresList = new ArrayList<Future>();
		
		int nrOfProcessors=Runtime.getRuntime().availableProcessors()-1; //No, I'm not going to totally drill the computer so much so an MP3 can't play and you can't go screw around on Twitter and Reddit!
		ExecutorService eservice = Executors.newFixedThreadPool(nrOfProcessors);
		//ExecutorService eservice=Executors.newCachedThreadPool();
		
		for(TransitGPSData tgps:transitGPS){
			futuresList.add(eservice.submit(new DistToNProcess(tgps,nodeData)));
		}

		Object taskResult;
		for(Future future:futuresList){
			try{
				//System.out.println("NN Working on "+currentTask+" of "+tasksToDo);
				//currentTask++;
				/*
				 * The two comments above indicate issues with how things work in the new threading world.  The numbers seemed to
				 * jump around quite a bit. 
				 */
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
	
	public void setTransitGPS(ArrayList<TransitGPSData> o){
		transitGPS=o;
	}
	
	public void setNodeData(ArrayList<NodeData> o){
		nodeData=o;
	}
}
