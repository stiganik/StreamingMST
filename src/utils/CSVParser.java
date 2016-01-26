package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import dataTypes.Edge;
import linkCutTrees.Vertex;

public class CSVParser {
	private ArrayList<Vertex> nodes;
	private ArrayList<Edge> edges;
	
	public CSVParser() {
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}
	
	public boolean parse(File file){
		BufferedReader br;
		try{
			br = new BufferedReader(new FileReader(file));
			String line;
			// First line is header
			line = br.readLine();
			String[] split = line.split(",");
			for(String label : split) {
				label = label.replaceAll("\\s+","");
				this.nodes.add(new Vertex(label, 0, 0));
			}
		    while ((line = br.readLine()) != null) {
		    	split = line.split(",");
		    	Edge e = createEdge(split);
		    	if(e != null){
		    		this.edges.add(e);
		    	}
		    }
		    br.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private Edge createEdge(String[] split) {
		if(split.length != 3){
			return null;
		}
		Vertex v1 = findNode(clean(split[0]));
		Vertex v2 = findNode(clean(split[1]));
		int weight = -1;
		try{
			weight = Integer.parseInt(clean(split[2]));
		} catch (Exception e) {}
		if(v1 == null || v2 == null || weight < 0) {
			return null;
		}
		Edge e = new Edge(v1, v2, weight);
		return e;
	}

	private Vertex findNode(String label) {
		for(Vertex v : nodes) {
			if(v.getLabel().equalsIgnoreCase(label)) {
				return v;
			}
		}
		return null;
	}

	private String clean(String label) {
		label = label.replaceAll("\\s+","");
		return label;
	}

	public ArrayList<Vertex> getNodes() {
		return nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	
}
