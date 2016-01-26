package linkCutTrees;

import java.util.List;

public class Vertex {
	
	String label;
	int x, y;
	Vertex pathParent;
	Vertex left, right, parent;
	
	public Vertex(String label, int x, int y) {
		super();
		this.label = label;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return label;
	}
	
}
