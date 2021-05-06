/*
 * Author: Patrick Jensen
 * Email: jensenpj@miamioh.edu
 */
public class BTree {
	public int x;
	public BNode root;

	BTree (int t) {
		this.root = null;
		this.x = t;
	}
	public BNode search(int n) throws Exception {
		if (this.root == null) {
			throw new Exception ("Root is null");
		} else {
			return this.root.search(n);
		}
	}
	public void traverse() {
		if (this.root != null) {
			this.root.traverse();
		}
		System.out.println();
	}
}

class BNode {
	int[] keys;
	int x;
	BNode[] pointers;
	int n;
	boolean leaf;
	
	BNode(int x, boolean leaf) {
		this.x = x;
		this.leaf = leaf;
		this.keys = new int[2 * x - 1];
		this.pointers = new BNode[x * 2];
		this.n = 0;
	}
	
	BNode search(int key) {
		int i = 0;
		while (i < n && key > keys[i]) {
			i++;
		}
		if (keys[i] == key) {
			return this;
		}
		if (leaf) {
			return null;
		}
		return pointers[i].search(key);
	}
	
	public void traverse() {
		int i = 0;
		for (i = 0; i < this.n; i++) {
			if (!this.leaf) {
				pointers[i].traverse();
			}
			System.out.print(keys[i] + " ");
		}
		if (!leaf) {
			pointers[i].traverse();
		}
	}

}
