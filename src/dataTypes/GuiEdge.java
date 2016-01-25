package dataTypes;

public class GuiEdge {
	private Point p1;
	private Point p2;
	private int weight;
	
	public GuiEdge(){
		
	}
	
	public GuiEdge(Point p1, Point p2, int weight) {
		this.p1 = p1;
		this.p2 = p2;
		this.weight = weight;
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
