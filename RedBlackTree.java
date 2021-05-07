/*
 * Author: Patrick Jensen
 * Email: jensenpj@miamioh.edu
 * Filename: RedBlackTree.java
 * Description: Class that implements the Red-Black Tree algorithm fully
 */
class Node {
	int data;
	Node parent;
	Node left;
	Node right;
	int color;
}
public class RedBlackTree {
	private Node root;
	private Node empty;
	
	private void preOrderHelper(Node node) {
		if (node != empty) {
			System.out.print(node.data + " ");
			preOrderHelper(node.left);
			preOrderHelper(node.right);
		}
	}
	
	private void inOrderHelper(Node node) {
		if (node != empty) {
			inOrderHelper(node.left);
			System.out.print(node.data + " ");
			inOrderHelper(node.right);
		}
	}
	
	private void postOrderHelper(Node node) {
		if (node != empty) {
			postOrderHelper(node.left);
			postOrderHelper(node.right);
			System.out.print(node.data + " ");
		}
	}
	
	private Node searchTreeHelper(Node node, int key) {
		if (node == empty || key == node.data) { 
			return node;
		}
		
		if (key < node.data) {
			return searchTreeHelper(node.left, key);
		} else {
			return searchTreeHelper(node.right, key);
		}
	}

	public void preorder() {
		preOrderHelper(this.root);
	}
	
	public void inorder() {
		inOrderHelper(this.root);
	}
	
	public void postorder() {
		postOrderHelper(this.root);
	}
	
	public Node searchTree(int n) {
		return searchTreeHelper(this.root, n);
	}
	
	public Node getRoot() {
		return this.root;
	}
	
	public Node minimum(Node node) {
		while (node.left != empty) {
			node = node.left;
		}
		return node;
	}
	
	private void fixInsert(Node node) {
		Node temp;
		while (node.parent.color == 1) {
			if (node.parent == node.parent.parent.right) { 
				temp = node.parent.parent.left;
				if (temp.color == 1) {
					temp.color = 0;
					node.parent.color = 0;
					node.parent.parent.color = 1;
					node = node.parent.parent;
				} else  {
					if (node == node.parent.left) {
						node = node.parent;
						rightRotate(node);
					}
					node.parent.color = 0;
					node.parent.parent.color = 1;
					leftRotate(node.parent.parent);
				}
			} else {
				temp = node.parent.parent.right;
				if (temp.color == 1) {
					temp.color = 0;
					node.parent.color = 0;
					node.parent.parent.color = 1;
					node = node.parent.parent;
				} else {
					if (node == node.parent.right) { 
						node = node.parent;
						leftRotate(node);
					}
					node.parent.color = 0;
					node.parent.parent.color = 1;
					rightRotate(node.parent.parent);
				}
			}
			if (node == root) { break; }
		}
		root.color = 0;
	}
	
	public RedBlackTree() {
		empty = new Node();
		empty.right = null;
		empty.left = null;
		empty.color = 0;
		root = empty;
	}
	
	public Node maximum(Node node) {
		while (node.right != empty) {
			node = node.right;
		}
		return node;
	}
	
	public Node previous(Node node) {
		if (node.right != empty) {
			return minimum(node.right);
		}
		Node temp = node.parent;
		while (temp != empty && node == temp.right) { 
			node = temp;
			temp = temp.parent;
		}
		return temp;
	}
	
	public Node predecessor(Node node) {
		if (node.left != empty) {
			return maximum(node.left);
		}
		Node temp = node.parent;
		while (temp != empty && node == temp.left) {
			node = temp;
			temp = temp.parent;
		}
		return temp;
	}
	
	public void leftRotate(Node node) {
		Node temp = node.right;
		node.right = temp.left;
		if (temp.left != empty) {
			temp.left.parent = node;
		}
		temp.parent = node.parent;
		if (node.parent == null) {
			this.root = temp;
		} else if (node == node.parent.left) {
			node.parent.left = temp;
		} else {
			node.parent.right = temp;
		}
		temp.left = node;
		node.parent = temp;
	}
	
	public void rightRotate(Node node) {
		Node temp = node.left;
		node.left = temp.right;
		if (temp.right != empty) {
			temp.right.parent = node;
		}
		temp.parent = node.parent;
		if (node.parent == null) {
			this.root = temp;
		} else if (node == node.parent.right) {
			node.parent.right = temp;
		} else {
			node.parent.left = temp;
		}
		temp.right = node;
		node.parent = temp;
	}
	
	public void insert(int n) {
		Node node = new Node();
		node.parent = null;
		node.data = n;
		node.left = empty;
		node.right = empty;
		node.color = 1;
		
		Node x = this.root;
		Node y = null;
		while (x != empty) {
			y = x;
			if (node.data < x.data) { 
				x = x.left;
			} else {
				x = x.right;
			}
		}
		node.parent = y;
		if (y == null) {
			root = node;
		} else if (node.data > y.data) { 
			y.right = node;
		} else {
			y.left = node;
		}
		if (node.parent == null) {
			node.color = 0;
			return;
		}
		if (node.parent.parent == null) {
			return;
		}
		fixInsert(node);
	}

}
