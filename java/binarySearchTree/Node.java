package binarySearchTree;

class Node {
	int data;
	Node leftChild, rightChild;
	
	Node(int data) {
		this.data = data;
		leftChild = rightChild = null;
	}
	
	/**
	 * [toString description]
	 * @return [description]
	 */
	public String toString() {
		return "The data value for this node is " + data;
	}
}