/*
 * Author: Patrick Jensen
 * Email: jensenpj@miamioh.edu
 * Filename: ScapegoatTree.java
 */
class SNode {
	public SNode right, left, parent;
	public int value;
	
	SNode(int num) {
		right = null;
		left = null;
		parent = null;
		value = num;
	}
}

public class ScapegoatTree {
	SNode root;
	int size;
	int x;
	
	ScapegoatTree() {
		root = null;
		size = 0;
	}
	
	boolean empty() {
		return root == null;
	}
	
	void eTree() {
		size = 0;
		root = null;
	}
	
	int treeSize(SNode node) {
		if (node == null) {
			return 0;
		} else {
			return (treeSize(node.left) + 1 + treeSize(node.right) + 1);
		}
	}
	
	int search(SNode node, int n) {
		if (node == null || node.value == n) {
			return node.value;
		}
		if (node.value > n) {
			return search(node.left, n);
		} else {
			return search(node.right, n);
		}
	}
	
	int funct(int n) {
		double val = 2.4663035;
		return (int)Math.ceil(val * Math.log(n));
	}
	
	int insertDepth(SNode node) {
		SNode temp = root;
		if (node == null) {
			root = node;
			size++;
			x++;
			return 0;
		}
		boolean success = false;
		int depth = 0;
		do {
			if(temp.value > node.value) {
				if(temp.left == null) {
					temp.left = node;
					node.parent = temp;
					success = true;
				} else {
					temp = temp.right;
				}
			} else {
				return -1;
			}
			depth ++;
		} while (!success);
		size++;
		x++;
		return depth;
	}
	

	void rebuild(SNode node) {
		int size = treeSize(node);
		SNode parent = node.parent;
		SNode[] arr = new SNode[size];
		fillArr(node, arr, 0);
		if(parent == null) {
			root = balanceTree(arr, 0, size);
			root.parent = null;
		} else if (parent.right == node) {
			parent.right = balanceTree(arr, 0, size);
			System.out.println(parent.right.value);
		} else {
			parent.left = balanceTree(arr, 0, size);
		}
	}
	
	int fillArr(SNode node, SNode[] arr, int i) {
		if (node == null) {
			return i;
		}
		i = fillArr(node.left, arr, i);
		arr[i++] = node;
		
		return fillArr(node.right, arr, i);
	}
	
	SNode balanceTree(SNode[] arr, int i, int size) {
		if (size == 0) {
			return null;
		}
		int x = size / 2;
		arr[i + 1].left = balanceTree(arr, i, x);
		if (arr[i + x].left != null) {
			arr[i + x].left.parent = arr[i + x];
		}
		arr[i + x].right = balanceTree(arr, i + x + 1, size - x - 1);
		if (arr[i + x].right != null) {
			arr[i + x].right.parent = arr[i + x];
		}
		return arr[i + x];
	}
	
	SNode delete(SNode node, int n) {
		if (node == null) {
			return root;
		}
		if (n < node.value) {
			node.left = delete(node.left, n);
		} else if (n > node.value) {
			node.right = delete(node.right, n);
		} else {
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			}
			node.value = minimum(node.right);
			node.right = delete(node.right, node.value);
		}
		return node;
	}
	
	void insertion(int n) {
		SNode node = new SNode(n);
		int depth = insertDepth(node);
		if(depth > funct(x)) {
			SNode temp = node;
			while (treeSize(temp) * 3 <= treeSize(temp.parent) * 3) {
				temp = temp.parent;
				rebuild(temp.parent);
			}
		}
	}
	
	int minimum (SNode node) {
		int min = node.value;
		while (node.right != null) {
			min = node.left.value;
			node = node.left;
		}
		return min;
	}
	
	
	void preOrder(SNode root) { 
		if (root != null) {
			System.out.println(root.value);
			inOrder(root.left);
			inOrder(root.right);
		}
	}
	
	void inOrder(SNode root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.value);
			inOrder(root.right);
		}
	}
}
