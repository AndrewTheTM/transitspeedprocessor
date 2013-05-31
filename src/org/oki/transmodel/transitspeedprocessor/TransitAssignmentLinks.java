package org.oki.transmodel.transitspeedprocessor;

/**
 * Class to hold links between GPS data and assignment data
 * @author arohne
 */
public class TransitAssignmentLinks {
	int fileId;
	String filename;
	int assignmentID;
	
	/**
	 * Quick-add method
	 * @param FileID The fileid
	 * @param Filename The Filename (part of this is used for linking)
	 */
	TransitAssignmentLinks(int FileID, String Filename){
		fileId=FileID;
		filename=Filename;
		String tmp=filename.substring(filename.indexOf("_")+1, filename.indexOf("_", filename.indexOf("_")+1));
		assignmentID=Integer.parseInt(tmp);
	}
}
