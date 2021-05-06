/*
 * Author: Patrick Jensen
 * Email: jensenpj@miamioh.edu
 */
class AVLNode {
	int key;
	int height;
	AVLNode left;
	AVLNode right;
}

public class AVL {
	private AVLNode root;

	void updateHeight(AVLNode node) {
		node.height = Math.max(height(node.left), height(node.right)) + 1;
	}

	int height(AVLNode node) {
		return node == null ? -1 : node.height;
	}

	int getBalance(AVLNode node) {
		return (node == null) ? 0 : height(node.right) - height(node.left);
	}

	AVLNode rotateRight(AVLNode node) {
		AVLNode temp = node.left;
		AVLNode temp1 = temp.right;
		node.left = temp1;
		temp.right = node;
		updateHeight(node);
		updateHeight(temp);

		return temp;
	}

	AVLNode rotateLeft(AVLNode node) {
		AVLNode temp = node.right;
		AVLNode temp1 = temp.left;
		node.right = temp1;
		temp.left = node;
		updateHeight(node);
		updateHeight(temp);
		return temp;
	}

	AVLNode rebalance(AVLNode node) {
		updateHeight(node);
		int balance = getBalance(node);
		if (balance > 1) {
			if (height(node.right.left) < height(node.right.right)) {
				node = rotateLeft(node);
			} else {
				node.right = rotateRight(node.right);
				node = rotateLeft(node);
			}
		} else if (balance < -1) {
			if (height(node.left.right) < height(node.left.left)) {
				node = rotateRight(node);
			} else {
				node.left = rotateLeft(node.left);
				node = rotateRight(node);
			}
		}
		return node;
	}

	AVLNode insert(AVLNode node, int n) throws Exception {
		if (node == null) {
			return node;
		} else if (node.key > n) {
			node.left = insert(node.left, n);
		} else if (node.key < n) {
			node.right = insert(node.right, n);
		} else {
			throw new Exception("No Duplicates!");
		}
		return rebalance(node);
	}

	AVLNode delete(AVLNode node, int n) {
		if (node == null) {
			return node;
		} else if (n < node.key) {
			node.left = delete(node.left, n);
		} else if (node.key < n) {
			node.right = delete(node.right, n);
		} else {
			if (node.right == null || node.left == null) {
				node = (node.left == null) ? node.right : node.left;
			} else {
				AVLNode temp = node.right.left;
				node.key = temp.key;
				node.right = delete(node.right, node.key);
			}
		}
		if (node != null) {
			node = rebalance(node);
		}
		return node;
	}

	AVLNode find(int n) {
		AVLNode current = root;
		while (current != null) {
			if (current.key == n) {
				break;
			}
			current = current.key < n ? current.right : current.left;
		}
		return current;
	}
}
