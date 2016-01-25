package linkCutTrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import algorithm.StreamingMST;
import dataTypes.Edge;

public class Algorithm implements StreamingMST {
	
	Set<Vertex> vertices = new HashSet<>();
	Map<Vertex, Map<Vertex, Integer>> edges = new HashMap<>();
	
	@Override
	public void newEdge(Edge edge) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public ArrayList<Edge> getEdges() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
