
public class Main {

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		
		tree.insertNode(new Node(3));
		tree.insertNode(new Node(4));
		tree.insertNode(new Node(8));
		tree.insertNode(new Node(9));
		tree.insertNode(new Node(0));
		tree.insertNode(new Node(2));
		tree.insertNode(new Node(10));
		
		tree.displayTree();
		
	}

}
