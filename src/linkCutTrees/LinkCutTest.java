package linkCutTrees;

public class LinkCutTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkCutTree c = new LinkCutTreeNaive();
		
		Vertex v1 = new Vertex("A", 1, 1);
		Vertex v2 = new Vertex("B", 1, 1);
		
		c.makeTree(v1);
		c.makeTree(v2);
		c.link(v1, v2);
		c.cut(v1);
		c.link(v2, v1);
		System.out.println(c.findRoot(v2));
		System.out.println(c.findPath(v2));
		System.out.println(c.findPath(v1));
		System.out.println(c);
	}

}
