package org.oki.transmodel.transitspeedprocessor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

public class ProcessRunner {

	/**
	 * @param args
	 */
	public static Properties config;
	public static void main(String[] args) throws IOException {
		//TODO: Subs to read control file
		//TODO: Subs to read files into object collections
		//TODO: Subs to do all the steps
		config=new Properties();
		config.load(ProcessRunner.class.getClassLoader().getResourceAsStream("TransitSpeedProcessor.properties"));
		String[] GPSPointInputFieldMap={"fileeid","pdatime","latit","longit","x","y","n","timesec"};
		
		ArrayList<Object[]> tempGPSData = new ArrayList<Object[]>();
		
		tempGPSData=readDBF(config.getProperty("TransitGPSTable"),GPSPointInputFieldMap);
		//TODO: tGPSData can be read into an arraylist of its own
		ArrayList<TransitGPSData> transitGPS=new ArrayList<TransitGPSData>();
		for(Object o[]:tempGPSData){
			transitGPS.add(new TransitGPSData(((Double)o[0]).intValue(),o[1],(Double)o[2],(Double)o[3],(Double)o[4],(Double)o[5],((Double)o[6]).intValue(),((Double)o[7]).intValue()));
		}
		
		
		
		int a=1;
		System.out.println(a);
	}
	
	
	static ArrayList<Object[]> readDBF(String DBFFileName, String[] inputFieldMap) throws FileNotFoundException, DBFException{
		InputStream iStream = new FileInputStream(DBFFileName);
		DBFReader reader=new DBFReader(iStream);
		int numberOfFields=reader.getFieldCount();
		
		
		
		ArrayList<Object[]> outObj=new ArrayList<Object[]>();
		
		Object[] row;
		int rowCount=0;
		while((row=reader.nextRecord())!=null){
			rowCount++;
			if((rowCount % 100)==0 || rowCount==1)
				System.out.println("Working on row "+rowCount);
			Object[] newObject = new Object[inputFieldMap.length];
			for(int f=0;f<numberOfFields;f++){
				DBFField field=reader.getField(f);
				for(int ff=0;ff<inputFieldMap.length;ff++)
					if(field.getName().toLowerCase().equals(inputFieldMap[ff].toLowerCase())){
						newObject[ff]=row[f];
					}
			}
			if(rowCount==1000)
				break;
			outObj.add(newObject);
			
		}
		
		return outObj;
	}
	
	
	
}
