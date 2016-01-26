package linkCutTrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import algorithm.StreamingMST;
import dataTypes.Edge;

public class Algorithm implements StreamingMST {
	
	Set<Vertex> vertices = new HashSet<>();
	Map<Vertex, Map<Vertex, Integer>> edges = new HashMap<>();
	LinkCutTree tree;
	
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
	
	@Override
	public void newEdge(Edge edge) {	
		Vertex v1 = edge.getVertex1();
		Vertex v2 = edge.getVertex2();
		int weight = edge.getWeight();
		
		if(!vertices.contains(v1) && !vertices.contains(v2)) {
			handleNewEdges(v1, v2, weight);			
		}
		else if(!vertices.contains(v2)) {
			handleNewEdge(v1, v2, weight);
		}
		else if(!vertices.contains(v1)) {
			handleNewEdge(v2, v1, weight);
		}
		else {
			handleOldEdges(v1, v2, weight);
		}
	}
	
	private void handleOldEdges(Vertex v1, Vertex v2, int weight) {
		if(tree.findRoot(v1) != tree.findRoot(v2)) {
			mergeTrees(v1, v2, weight);
		}
		else {
			breakCycle(v1, v2, weight);
		}
	}
	
	private void breakCycle(Vertex v1, Vertex v2, int weight) {
		List<Vertex> path1 = tree.findPath(v1);
		List<Vertex> path2 = tree.findPath(v2);
		unifyPaths(path1, path2);
		
		int currentMaxWeight = weight;
		Vertex currentMaxVert1 = v1;
		Vertex currentMaxVert2 = v2;
		
		for(int i = 0; i < path1.size()-1; i++) {
			Vertex measuredVert1 = path1.get(i);
			Vertex measuredVert2 = path1.get(i+1);
			
			int measuredWeight = measureEdge(measuredVert1, measuredVert2);
			
			if(measuredWeight > currentMaxWeight) {
				currentMaxWeight = measuredWeight;
				currentMaxVert1 = measuredVert1;
				currentMaxVert2 = measuredVert2;
			}
		}
		
		for(int i = 0; i < path2.size()-1; i++) {
			Vertex measuredVert1 = path2.get(i);
			Vertex measuredVert2 = path2.get(i+1);
			
			int measuredWeight = measureEdge(measuredVert1, measuredVert2);
			
			if(measuredWeight > currentMaxWeight) {
				currentMaxWeight = measuredWeight;
				currentMaxVert1 = measuredVert1;
				currentMaxVert2 = measuredVert2;
			}
		}
		
		if(currentMaxWeight == weight) {
			return;
		}
		else {
			rememberEdge(v1, v2, weight);
			forgetEdge(currentMaxVert1, currentMaxVert2);
			
			tree.cut(currentMaxVert1);
			rotate(v1);
			rotate(v2);
			tree.link(v1, v2);
		}
		
	}

	private void unifyPaths(List<Vertex> path1, List<Vertex> path2) {
		Vertex previous = null;
		while(path1.get(path1.size()-1) == path2.get(path2.size()-1)) {
			previous = path1.get(path1.size()-1);
			path1.remove(path1.size()-1);
			path2.remove(path2.size()-1);
			if(path1.size() == 0  || path2.size() == 0) {
				break;
			}
		}
		path1.add(previous);
		path2.add(previous);
	}

	private void mergeTrees(Vertex v1, Vertex v2, int weight) {
		rememberEdge(v1, v2, weight);
		
		rotate(v1);
		rotate(v2);
		tree.link(v1, v2);
	}

	private void rotate(Vertex v) {
		List<Vertex> path = tree.findPath(v);
		
		for(int i = path.size() - 1; i > 0; i--) {
			Vertex parent = path.get(i);
			Vertex child = path.get(i-1);
			
			tree.cut(child);
			tree.link(parent, child);
		}
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
	
	private int measureEdge(Vertex v1, Vertex v2) {
		if(v1.toString().compareTo(v2.toString()) < 0) {
			return edges.get(v1).get(v2);
		}
		else {
			return edges.get(v2).get(v1);
		}
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

	public Algorithm() {
		this.tree = new LinkCutTreeSplay();
	}	

}
