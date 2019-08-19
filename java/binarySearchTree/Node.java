package binarySearchTree;

public class Node {
	int data;
	Node leftChild, rightChild;
	
	public Node(int data) {
		this.data = data;
		leftChild = rightChild = null;
	}
	
	/**
	 * Method that returns a string representing this node's data.
	 * @return String format of this node object.
	 */
	public String toString() {
		return "The data value for this node is " + data;
	}
}