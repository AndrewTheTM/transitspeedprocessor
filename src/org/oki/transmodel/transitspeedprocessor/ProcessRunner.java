package org.oki.transmodel.transitspeedprocessor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

public class ProcessRunner {

	/**
	 * @param args
	 */
	public static Properties config;
	public static void main(String[] args) throws IOException {
		//Read Control File
		config=new Properties();
		config.load(ProcessRunner.class.getClassLoader().getResourceAsStream("TransitSpeedProcessor.properties"));
		
		ArrayList<TransitGPSData> transitGPS=new ArrayList<TransitGPSData>();
		ArrayList<NodeData> nodeData=new ArrayList<NodeData>();
		ArrayList<NetworkData> NetData=new ArrayList<NetworkData>();
		
		try{
			//Read GPS Input Points
			String[] GPSPointInputFieldMap={"fileeid","pdatime","latit","longit","x","y","n","timesec"};
			ArrayList<Object[]> tempGPSData = new ArrayList<Object[]>();
			tempGPSData=readDBF(config.getProperty("TransitGPSTable"),GPSPointInputFieldMap);
			for(Object o[]:tempGPSData)
				transitGPS.add(new TransitGPSData(((Double)o[0]).intValue(),o[1],(Double)o[2],(Double)o[3],(Double)o[4],(Double)o[5],((Double)o[6]).intValue(),((Double)o[7]).intValue()));
			tempGPSData=null; //Cleanup
			GPSPointInputFieldMap=null; //Cleanup
			
			//Read Nodes
			String[] nodeInputFieldMap={"n","x","y"};
			ArrayList<Object[]> tempNodeData=new ArrayList<Object[]>();
			tempNodeData=readDBF(config.getProperty("NodeTable"),nodeInputFieldMap);
			for(Object o[]:tempNodeData)
				nodeData.add(new NodeData(((Double)o[0]).intValue(),(Float)o[1],(Float)o[2]));
			tempNodeData=null;
			nodeInputFieldMap=null;
			
			//Read Network
			String[] netInputFieldMap={"a","b","admclass","speed","areatype","lanes"};
			ArrayList<Object[]> tempNetData=new ArrayList<Object[]>();
			tempNetData=readDBF(config.getProperty("LinkTable"),netInputFieldMap);
			for(Object o[]:tempNetData)
				NetData.add(new NetworkData(((Double)o[0]).intValue(),((Double)o[1]).intValue(),((Float)o[2]).intValue(),(Float)o[3],((Float)o[4]).intValue(),((Float)o[5]).intValue()));
			tempNetData=null;
			netInputFieldMap=null;
		}catch (FileNotFoundException e){
			//TODO: Explain that the file was not found and to check paths, double-backslashes, etc.
			e.printStackTrace();
		}catch (DBFException e){
			//TODO: Explain that there was an unspecified DBF error and to check the DBF file.
			e.printStackTrace();
		}catch (Exception e){
			//TODO: Explain that there was an error.  We don't know what it is, but the stack trace may help.
			e.printStackTrace();
		}
		
		//TODO: Load assignments here
		try{
			String[] RideCountDataMap={"assignment","route","direction","tod"};
			ArrayList<Object[]> tempRideCountList=new ArrayList<Object[]>();
			tempRideCountList=readExcel(config.getProperty("AssignmentTable"),"assignments",RideCountDataMap);
			//TODO: fill into AL object and kill temp object.  Then...
			//FIXME: Load GPS File List
		}catch (InvalidFormatException e){
			//TODO: Tell the user that the format is illegal and the FBI is on their way to bust their ass!
			System.out.println("The format is illegal. The FBI is on the way.  Your ass is grass!");
			e.printStackTrace();
		}catch(FileNotFoundException e){
			//TODO: Tell the user that the file was not found
			System.out.println("File not found");
			e.printStackTrace();
		}catch(IOException e){
			//TODO: Something here
			System.out.println("IOException");
			e.printStackTrace();
		}
		
		int a=1;
		System.out.println(a);
	}
	
	
	/**
	 * Reads the selected contents of a DBF and stuffs them into an arraylist of arrays of objects
	 * @param DBFFileName The file path to the DBF to read
	 * @param inputFieldMap A String array of fields to read
	 * @return An Arraylist of Object arrays loaded with the contents
	 * @throws FileNotFoundException if the DBF file is not found
	 * @throws DBFException if there is a different problem with the DBF
	 */
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
			if(rowCount==1000) //FIXME: remove this when debugging is done
				break;
			outObj.add(newObject);
			
		}
		return outObj;
	}
	
	static ArrayList<Object[]> readExcel(String ExcelFileName, String SheetName, String[]inputFieldMap) throws InvalidFormatException, FileNotFoundException, IOException{
		//TODO: Documentation

		
		OPCPackage pkg=OPCPackage.open(new FileInputStream(ExcelFileName));
		XSSFWorkbook wb=new XSSFWorkbook(pkg);
		
		Sheet sheet=wb.getSheet(SheetName);
		//Header Row
		int[] cols=new int[inputFieldMap.length];
		int ifmCol=0;
		Row r=sheet.getRow(0);
		for(Cell c:r){
			for(int x=0;x<inputFieldMap.length;x++){
				if(c.toString().toLowerCase().equalsIgnoreCase(inputFieldMap[x])){
					cols[ifmCol]=c.getColumnIndex();
					ifmCol++;
					break; //should just break out of this loop
				}
			}
		}
		
		
		ArrayList<Object[]> outAL=new ArrayList<Object[]>();
		
		for(Row row:sheet){
			Object[] out=new Object[inputFieldMap.length];
			if(row.getRowNum()>0){
				for(int x=0;x<cols.length;x++){
					out[x]=row.getCell(cols[x]).toString(); //TODO: Convert from cell to values
				}
				outAL.add(out);
			}
			
		}
		return outAL;
	}
	
}
