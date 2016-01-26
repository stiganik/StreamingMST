package linkCutTrees;

public class Vertex {
	
	String label;
	int x, y;
	
	// The left and right children of the node, and the parent of the node, in an auxiliary tree.
	Vertex left, right, parent;
	
	// The path-parent pointer in the link/cut tree.
	Vertex pathParent;
	
	public Vertex(String label, int x, int y) {
		super();
		
		this.label = label;
		this.x = x;
		this.y = y;
		
		left = null;
		right = null;
		parent = null;
		pathParent = null;
	}
	
	@Override
	public String toString() {
		return label;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
}
