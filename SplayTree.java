/*
 * Author: Patrick Jensen
 * Email: jensenpj@miamioh.edu
 */
class SplayNode {
	SplayNode left, right;
	int key;
}

public class SplayTree {
	SplayNode makeNode(int n) {
		SplayNode node = new SplayNode();
		node.key = n;
		node.left = null;
		node.right = null;
		return node;
	}

	SplayNode splay(SplayNode root, int n) {
		if (root == null || root.key == n) {
			return root;
		}
		if (root.key > n) {
			if (root.left == null) {
				return root;
			}
			if (root.left.key > n) {
				root.left.left = splay(root.left.left, n);
				root = rotateRight(root);
			} else if (root.left.key < n) {
				root.left.right = splay(root.left.right, n);
				if (root.left.right != null) {
					root.left = rotateLeft(root.left);
				}
			}
			return root.left == null ? root : rotateRight(root);
		} else {
			if (root.right == null) {
				return root;
			}
			if (root.right.key > n) {
				root.right.left = splay(root.right.left, n);
				if (root.right.left != null) {
					root.right = rotateRight(root.right);
				}
			} else if (root.right.key < n) {
				root.right.right = splay(root.right.right, n);
				root = rotateLeft(root);
			}
			return (root.right == null) ? root : rotateLeft(root);
		}
	}
	
	SplayNode rotateRight(SplayNode node) {
		SplayNode temp = node.left;
		node.left = temp.right;
		temp.right = node;
		return temp;
	}

	SplayNode rotateLeft(SplayNode node) {
		SplayNode temp = node.right;
		node.right = temp.left;
		temp.left = node;
		return temp;
	}
	
	SplayNode seach(SplayNode root, int n) {
		return splay(root, n);
	}
}
