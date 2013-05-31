package org.oki.transmodel.transitspeedprocessor;

import java.util.ArrayList;

public class TransitGPSDataCollection extends ArrayList<TransitGPSData> {
	private static final long serialVersionUID = 636011501358548627L;
	//private TransitGPSDataCollection instance;
	
	public ArrayList<TransitGPSData> getPointsByNandFID(int N, int FileID){
		ArrayList<TransitGPSData> output=new ArrayList<TransitGPSData>();
		for(TransitGPSData t:this){
			if(t.N==N && t.FileID==FileID)
				output.add(t);
		}
		return output;
	}
	
	public ArrayList<TransitGPSData> getPointsByN(int N){
		ArrayList<TransitGPSData> output=new ArrayList<TransitGPSData>();
		for(TransitGPSData t:this){
			if(t.N==N)
				output.add(t);
		}
		return output;
	}
	
	public ArrayList<Integer> getUniqueFID(){
		ArrayList<Integer> output=new ArrayList<Integer>();
		boolean hasFID=false;
		for(TransitGPSData t:this){
			for(Integer i:output){
				if(t.FileID==i){
					hasFID=true;
					break;
				}
			}
			if(!hasFID)
				output.add(t.FileID);
		}
		return output;
	}
}
