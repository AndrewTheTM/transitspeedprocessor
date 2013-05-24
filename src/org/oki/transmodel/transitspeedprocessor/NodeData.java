package org.oki.transmodel.transitspeedprocessor;

/**
 * @author arohne
 * Holds node data
 */
public class NodeData {
	int N;
	double X;
	double Y;
	NodeData(){
		N=0;
		X=0.0;
		Y=0.0;
	}
	NodeData(int n, double x, double y){
		N=n;
		X=x;
		Y=y;
	}
}
