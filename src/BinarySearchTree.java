import java.util.ArrayList;

public class BinarySearchTree {
	Node root;
	int minDepth, maxDepth;
	ArrayList<Node> nodes = new ArrayList<Node>();
	
	
	public void insertNode(Node node) {
		root = insertNodeHelper(root, node, 0);
		if(!checkUnbalance()) balanceTree();
		nodes.clear();
	}
	
	private void insertBalancedNode(Node node) {
		root = insertNodeHelper(root, node, 0);
	}
	
	private Node insertNodeHelper(Node root, Node node, int height) {
		
		int data  = node.data;
		
		if(root == null) {
			root = node;
			root.height = height;
			return root;
		} else if(data < root.data) {
			root.left = insertNodeHelper(root.left, node, height+1);
		} else {
			root.right = insertNodeHelper(root.right, node, height+1);
		}
		
		return root;
	}
	
	public boolean checkUnbalance() {
		
		minDepth = -1;
		maxDepth = 0;
		checkUnbalanceHelper(root,0);
		if(maxDepth - minDepth >= 2) return false;
		nodes.clear();		
		return true;
		
	}

	private void checkUnbalanceHelper(Node root, int height) {
		if(root == null && minDepth == -1) {
			minDepth = height-1;
			maxDepth = height-1;
			return;
		} else if(root == null) {
			if(height - 1 < minDepth) minDepth = height-1;
			else if(height - 1 > maxDepth) maxDepth = height-1;
			return;
		} else {
			checkUnbalanceHelper(root.left, height + 1);
			nodes.add(root);
			checkUnbalanceHelper(root.right, height + 1);
			
		}
		return;
	}
	
	public void balanceTree() {
		deleteTree();
		balanceTreeHelper(0, nodes.size()-1);
		nodes.clear();
		
	}
	
	private void balanceTreeHelper(int low, int high) {
		if(low > high) return;
		
		int middle = low + (high-low)/2;
		Node middleNode = nodes.get(middle);
		insertBalancedNode(middleNode);
		
		balanceTreeHelper(low, middle-1);
		balanceTreeHelper(middle+1, high);
		
		
	}
	
	public void displayTree() {
		displayTreeHelper(root);
	}
	
	private void displayTreeHelper(Node root) {
		if(root == null) return;
		
		System.out.println(root.data);
		displayTreeHelper(root.left);
		
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
	public void deleteTree() {
		root = deleteTreeHelper(root);
	}
	private Node deleteTreeHelper(Node root) {
		if(root == null) return null;
		
		root.left = deleteTreeHelper(root.left);
		root.right = deleteTreeHelper(root.right);
		
		return null;
	}
	
	
}
