package binarySearchTree;

class BinarySearchTree {
	Node root;
	int leafCount, nodeCount;

	BinarySearchTree() {
		root = null;
		leafCount = 0;
		nodeCount = 0;
	}

	/**
	 * [addNode description]
	 * @param data [description]
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
		if(current.data <= data)
			follow.leftChild = newNode;
		else
			follow.rightChild = newNode;
	}

	public boolean deleteNode(Node node) {

	}

	/**
	 * [preOrder description]
	 * @param root [description]
	 */
	public void preOrder(Node root) {
		if(root != null) {
			System.out.println(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	/**
	 * [inOrder description]
	 * @param root [description]
	 */
	public void inOrder(Node root) {
		if(root != null) {
			inOrder(root.left);
			System.out.println(root.data + " ");
			inOrder(root.right);
		}
	}

	/**
	 * [postOrder description]
	 * @param root [description]
	 */
	public void postOrder(Node root) {
		if(root != null) {
			postOrder(root.left);
			postorder(root.right);
			System.out.println(root.data + " ");
		}
	}

	/**
	 * [search description]
	 * @param  root   [description]
	 * @param  search [description]
	 * @return        [description]
	 */
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

	/**
	 * [countLeafs description]
	 * @param  root [description]
	 * @return      [description]
	 */
	public int countLeafs(Node root) {
		if(root != null) {
			countLeafst(root->left);
			if(root->left == NULL && root->right == NULL) leafCount++;
			countLeafs(root->right);
		}
	}	

	/**
	 * [countNodes description]
	 * @param root [description]
	 */
	public void countNodes(Node root) {
		if(root != null) {
			countNodes(root.left);
			this.nodeCount++;
			countNodes(root.right);
		}
	}

}