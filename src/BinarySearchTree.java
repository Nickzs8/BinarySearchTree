
public class BinarySearchTree {
	Node root;
	
	public void insertNode(Node node) {
		root = insertHelper(root, node);
	}
	
	private Node insertHelper(Node root, Node node) {
		
		int data  = node.data;
		
		if(root == null) {
			root = node;
			return root;
		} else if(data < root.data) {
			root.left = insertHelper(root.left, node);
		} else {
			root.right = insertHelper(root.right, node);
		}
		
		return root;
	}
	
	public void displayTree() {
		displayTreeHelper(root);
	}
	
	private void displayTreeHelper(Node root) {
		if(root == null) return;
		
		
		displayTreeHelper(root.left);
		System.out.println(root.data);
		displayTreeHelper(root.right);
		
		return;
	}
	
	public boolean searchNode(int data) {
		return searchNodeHelper(root, data);
	}
	
	private boolean searchNodeHelper(Node root, int data) {
		if(root == null) return false;
		else if(root.data == data) return true;
		else if(root.data > data) return searchNodeHelper(root.left, data);
		else return searchNodeHelper(root.right, data);
	}
	
	public void removeNode(int data) {
		if(searchNode(data)) removeNodeHelper(root, data);
		else System.out.println("data could not be found");
	}	
	
	private Node removeNodeHelper(Node root, int data) {
		if(root == null) return root;
		else if(data < root.data)
			root.left = removeNodeHelper(root.left, data); 
		else if (data > root.data)
			root.right = removeNodeHelper(root.right, data);
		else {
			if(root.left == null && root.right == null) return null;
			else if(root.right != null) {
				root.data = sucessor(root);
				root.right = removeNodeHelper(root.right, root.data);
			}
			else {
				root.data = predecessor(root);
				root.left = removeNodeHelper(root.left, root.data);
			}
		}
		
		return root;
	}
	
	private int sucessor(Node root) {
		root = root.right;
		while(root.left != null) {
			root = root.left;
		}
		return root.data;
	}
	
	private int predecessor(Node root) {
		root = root.left;
		while(root.right != null) {
			root = root.right;
		}
		return root.data;
	}
	
	
}
