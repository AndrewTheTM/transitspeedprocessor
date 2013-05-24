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
	
	NetworkData(int a, int b, int admclass, double speed, int areatype, int lanes){
		A=a;
		B=b;
		AdmClass=admclass;
		Speed=speed;
		AreaType=areatype;
		Lanes=lanes;
	}
}
