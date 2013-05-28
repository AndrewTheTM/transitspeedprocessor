package org.oki.transmodel.transitspeedprocessor;

/**
 * @author arohne
 * Holds network data objects
 */
public class NetworkData {
	int A;
	int B;
	int AdmClass;
	double Speed;
	int AreaType;
	int Lanes;
	NetworkData(){
		A=0;
		B=0;
		AdmClass=0;
		Speed=0;
		AreaType=0;
		Lanes=0;
	}
	
	/**
	 * Network Data Quick-add method
	 * @param a The link's A-node value
	 * @param b The link's B-node value
	 * @param admclass The link's facility type code
	 * @param speed The link's speed
	 * @param areatype The link's area type
	 * @param lanes The link's number of lanes
	 */
	NetworkData(int a, int b, int admclass, double speed, int areatype, int lanes){
		A=a;
		B=b;
		AdmClass=admclass;
		Speed=speed;
		AreaType=areatype;
		Lanes=lanes;
	}
}
