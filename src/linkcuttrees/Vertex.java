package linkcuttrees;

public class Vertex {
	
	String label;
	int x, y;
	
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
