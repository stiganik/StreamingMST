package linkCutTrees;

import splayTrees.SplayTree;

public class AuxiliaryTree extends SplayTree {
	private Vertex parent;
	
	public AuxiliaryTree() {
		super();
		parent = null;
	}
	
	public Vertex getParent() {
		return parent;
	}
}
