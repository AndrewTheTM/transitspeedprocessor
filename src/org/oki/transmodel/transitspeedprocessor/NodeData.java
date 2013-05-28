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
	
	/**
	 * NodeData Quick-add method
	 * @param n The node number
	 * @param x The node's x coordinate
	 * @param y The node's y coordinate
	 */
	NodeData(int n, double x, double y){
		N=n;
		X=x;
		Y=y;
	}
}
