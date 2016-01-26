package linkCutTrees;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinkCutTreeSplay implements LinkCutTree {
	
	private Set<Vertex> vertices = new HashSet<Vertex>();

	@Override
	// Creates a new tree into the forest, with v as its only vertex.
	public void makeTree(Vertex v) {
		
		// Make the tree by adding the vertex
		vertices.add(v);
	
	}

	@Override
	// If v1 is a tree root and v2 is a vertex in another tree, links the trees containing v1 and v2 by adding the edge (v1, v2), making v2 the parent of v1.
	public void link(Vertex v1, Vertex v2) {
		
		// Access v1. As v1 is the root, accessing it will mean that it will have no left child in the auxiliary tree.
		access(v1);
		
		// Access v2
		access(v2);
		
		// Make v2 the left child of v1, making it the parent of v1 in the represented tree.
		v1.left = v2;
		v2.parent = v1; // TODO: Optimise
		v2.pathParent = null;
	
	}

	@Override
	// Cut the represented tree at node v.
	public void cut(Vertex v) {
		
		// First, access v, which puts all the elements lower than v in the represented tree as the right child of v in the auxiliary tree.
		access(v);
		
		// All nodes in the left subtree of v are nodes higher than v in the represented tree. We can, therefore, disconnect the left child of v, making v the root of a represented tree.
		if (v.left != null) {
			v.left.parent = null;
			v.left.pathParent = null;
			v.left = null;
		}
		
	}

	@Override
	public Vertex findRoot(Vertex v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vertex> findPath(Vertex v) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void access(Vertex v) {
		// TODO
	}

}
