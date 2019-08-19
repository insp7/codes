package binarySearchTree;

class BST {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree(8);

		bst.addNode(3);
		bst.addNode(10);
		bst.addNode(1);
		bst.addNode(6);
		bst.addNode(4);
		bst.addNode(7);
		bst.addNode(13);
		bst.addNode(14);

		bst.inOrder(bst.root);
	}
}
