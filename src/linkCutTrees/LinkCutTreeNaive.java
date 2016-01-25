package linkCutTrees;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LinkCutTreeNaive implements LinkCutTree {
	
	Set<Vertex> vertices = new HashSet<>();
	Map<Vertex, Vertex> parents = new HashMap<>();

	@Override
	public void makeTree(Vertex v) {
		vertices.add(v);
		parents.put(v, null);
	}

	@Override
	public void link(Vertex v1, Vertex v2) {
		parents.put(v1, v2);
	}

	@Override
	public void cut(Vertex v) {
		parents.put(v, null);
	}

	@Override
	public Vertex findRoot(Vertex v) {
		if(parents.get(v) == null)
			return v;
		else
			return findRoot(parents.get(v));
	}

	@Override
	public List<Vertex> findPath(Vertex v) {
		List<Vertex> path = new ArrayList<>();
		path.add(v);
		
		while(parents.get(v) != null) {
			path.add(parents.get(v));
			v = parents.get(v);
		}
		return path;
	}
	
	@Override
	public String toString() {
		return parents.toString();
	}
	
}
