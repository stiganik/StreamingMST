package linkcuttrees;
import java.util.List;

public interface LinkCutTree {
	
	void makeTree(Vertex v);
	
	void link(Vertex v1, Vertex v2);
	
	void cut(Vertex v);
	
	Vertex findRoot(Vertex v);
	
	List<Vertex> findPath(Vertex v);

}