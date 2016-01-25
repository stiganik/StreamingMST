package algorithm;

import java.util.ArrayList;

import dataTypes.Edge;

public interface StreamingMST {
	/*
	 * Can be revised, doesn't have to stay like this
	 * I just wrote the methods blindly without knowing
	 * how the algorithm works
	 * 
	 * The idea is that the GUI will send and ask things from the algorithm
	 * and the algorithm will answer.
	 */
	
	// Add new edge to MST data structure
	// return True, if edge is accepted in MST
	// return False, if the edge was ignored
	public void newEdge(Edge edge);
	
	// Return all edges currently in the MST data structure
	public ArrayList<Edge> getEdges();
	
}
