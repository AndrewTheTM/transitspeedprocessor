package org.oki.transmodel.transitspeedprocessor;

public class TransitAssignmentLinks {
	int fileId;
	String filename;
	int assignmentID;
	TransitAssignmentLinks(int FileID, String Filename){
		fileId=FileID;
		filename=Filename;
		String tmp=filename.substring(filename.indexOf("_"), filename.indexOf("_", filename.indexOf("_")+1));
		//RideDataPPC_00001060_P31131_20101115082022.zip
		System.out.println(tmp);
		assignmentID=Integer.parseInt(tmp);
	}
}
