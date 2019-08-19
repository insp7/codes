package binarySearchTree;

/**
 * A class for performing all basic operations on a Binary Search Tree.
 */
public class BinarySearchTree {

	public Node root;
	public int leafCount, nodeCount;

	// Creates an emoty binary search tree with root set to null.
	public BinarySearchTree() {
		root = null;
		leafCount = nodeCount = 1;
	}

	// Creates a binary search tree with a root node.
	public BinarySearchTree(int data) {
		root = new Node(data);
		leafCount = nodeCount = 1;
	}

	/**
	 * Method to insert a node in the binary search tree.
	 * @param data Specifies the integer data to be inserted.
	 */
	public void addNode(int data) {
		Node current = root, follow = null;
		Node newNode = new Node(data);
		
		if(root == null) {
			root = newNode;
			return;
		}

		while(current != null) {
			follow = current;

			if(data <= current.data) 
				current = current.leftChild;
			else
				current = current.rightChild;
		}
		
		// Actual insertion
		if(data <= follow.data)
			follow.leftChild = newNode;
		else
			follow.rightChild = newNode;
	}

	/**
	 * Given a binary tree, this method deletes the a node which matches with the given data.
	 * This is a recursive implementation for node deletion.
	 * When a node is deleted, there are three possibilities:
	 * 
	 * Node to be deleted is leaf:
	 * Simply remove from the tree.
	 * 
	 * Node to be deleted has only one child;
	 * Copy the child to the 'node to be deleted' and delete the child.
	 * 
	 * Node to be deleted has two children: 
	 * Find inorder successor/predecessor of the node. 
	 * Copy contents of the inorder successor/predecessor to the node and delete the inorder successor/predecessor.
	 * Here we use inorder successor. 
	 * 
	 * @param  root Root node for a given binary search tree.
	 * @param  data Specifies the data of the node to be deleted.
	 * @return      Returns the new root for a binary search tree.
	 */
	public Node deleteNode(Node root, int data) {
		if(root == null) return root; // Base case

		// Below are the recursive calls for traversing the tree until the root.data == data.
		if(data < root.data) {
			root.leftChild = deleteNode(root.leftChild, data);
			return root;
		} else if(data > root.data) {
			root.rightChild = deleteNode(root.rightChild, data);
			return root;
		}

		// The below code is executed when root.data == data
		// i.e. when root is the node to be deleted.
		
		// If one of the children is empty
		if(root.leftChild == null) 
			return root.rightChild; // rightChild may or may not be null.
		else if(root.rightChild == null) 
			return root.leftChild; // leftChild may or may not be null.
		else { // If both children exist
			Node successor, successorParent;
			successor = successorParent = root.rightChild;
			
			// Get the inorder successor of the node to be deleted.
			while(successor.leftChild != null) {
				successorParent = successor;
				successor = successor.leftChild;
			}

			// Since successor is always the leftChild of its successorParent, 
			// we can safely make successor's rightChild as left of its successorParent.
			// This statement implicitly removes the successorParent's reference on successor.
			// Hence, successor is an unused object now, which will be deleted by the garbage collector.
			successorParent.leftChild = successor.rightChild; // rightChild may or may not be null.

			// Copy successor's data to root
			root.data = successor.data;

			return root;
		}
	}

	// Recursive method to traverse and print elements in preorder.
	public void preOrder(Node root) {
		if(root != null) {
			System.out.println(root.data + " ");
			preOrder(root.leftChild);
			preOrder(root.rightChild);
		}
	}

	// Recursive method to traverse and print elements in inorder.
	public void inOrder(Node root) {
		if(root != null) {
			inOrder(root.leftChild);
			System.out.print(root.data + " ");
			inOrder(root.rightChild);
		}
	}

	// Recursive method to traverse and print elements in postorder.
	public void postOrder(Node root) {
		if(root != null) {
			postOrder(root.leftChild);
			postOrder(root.rightChild);
			System.out.println(root.data + " ");
		}
	}

	public Node search(Node root, int search) {
		Node current = root;

		while(current != null && current.data != search) {
			if(search <= current.data)
				current = current.leftChild;
			else
				current = current.rightChild;
		}

		if(current == null)
			return null;
		else
			return current;
	}

	/*
	public int leafCount(Node root) {
		if(root != null) {
			leafCount(root.leftChild);
			if(root.leftChild == null && root.rightChild == null) leafCount++;
			leafCount(root.rightChild);
		}
	}	

	public void getNodeCount(Node root) {
		if(root != null) {
			getNodeCount(root.leftChild);
			this.nodeCount++;
			getNodeCount(root.rightChild);
		}
	} */

	public int getMinValue(Node root) {
		Node current = root;

		while(current.leftChild != null) 
			current = current.leftChild;
		return current.data;
	}

	public int getMaxValue(Node root) {
		Node current = root;

		while(current.rightChild != null) 
			current = current.rightChild;
		return current.data;
	}
}