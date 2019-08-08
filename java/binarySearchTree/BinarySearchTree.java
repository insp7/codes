package binarySearchTree;

class BinarySearchTree {
	Node root;
	int leafCount, nodeCount;

	BinarySearchTree() {
		root = null;
		leafCount = 0;
		nodeCount = 0;
	}

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
		if(current.data <= data)
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

	public void preOrder(Node root) {
		if(root != null) {
			System.out.println(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public void inOrder(Node root) {
		if(root != null) {
			inOrder(root.left);
			System.out.println(root.data + " ");
			inOrder(root.right);
		}
	}

	public void postOrder(Node root) {
		if(root != null) {
			postOrder(root.left);
			postorder(root.right);
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

	public int countLeafs(Node root) {
		if(root != null) {
			countLeafst(root->left);
			if(root->left == NULL && root->right == NULL) leafCount++;
			countLeafs(root->right);
		}
	}	

	public void countNodes(Node root) {
		if(root != null) {
			countNodes(root.left);
			this.nodeCount++;
			countNodes(root.right);
		}
	}

}