package linkcuttrees;

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
	
	LinkCutTree tree = new LinkCutTreeNaive();
	
	@Override
	public void newEdge(Edge edge) {
		Vertex v1 = edge.getVertex1();
		Vertex v2 = edge.getVertex2();
		int weight = edge.getWeight();
		
		if(!vertices.contains(v1) && ! vertices.contains(v2)) {
			handleNewEdges(v1, v2, weight);			
		}
		else if(vertices.contains(v1)) {
			handleNewEdge(v1, v2, weight);
		}
		else if(vertices.contains(v2)) {
			handleNewEdge(v2, v1, weight);
		}
		else {
			handleOldEdges(v1, v2, weight);
		}
		
	}
	
	private void handleOldEdges(Vertex v1, Vertex v2, int weight) {
		// TODO Auto-generated method stub
		
	}

	private void handleNewEdge(Vertex old, Vertex newVertex, int weight) {
		vertices.add(newVertex);
		
		edges.put(newVertex, new HashMap<>());
		rememberEdge(old, newVertex, weight);
		
		tree.makeTree(newVertex);
		tree.link(newVertex, old);
	}
	
	private void handleNewEdges(Vertex v1, Vertex v2, int weight) {
		vertices.add(v1);
		vertices.add(v2);
		
		edges.put(v1, new HashMap<>());
		edges.put(v2, new HashMap<>());
		rememberEdge(v1, v2, weight);
		
		tree.makeTree(v1);
		tree.makeTree(v2);
		tree.link(v1, v2);
	}
	
	@Override
	public ArrayList<Edge> getEdges() {
		ArrayList<Edge> edgeList = new ArrayList<>();
		for(Vertex v: edges.keySet()) {
			Map<Vertex, Integer> dest = edges.get(v);
			for(Vertex v2: dest.keySet()) {
				edgeList.add(new Edge(v, v2, dest.get(v2)));
			}
		}
		return edgeList;
	}
	
	private void forgetEdge(Vertex v1, Vertex v2) {
		if(v1.toString().compareTo(v2.toString()) < 0) {
			edges.get(v1).remove(v2);
		}
		else {
			edges.get(v2).remove(v1);
		}
	}
	
	private void rememberEdge(Vertex v1, Vertex v2, int weight) {
		if(v1.toString().compareTo(v2.toString()) < 0) {
			edges.get(v1).put(v2, weight);
		}
		else {
			edges.get(v2).put(v1, weight);
		}
	}

}
