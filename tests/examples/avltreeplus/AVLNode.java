package examples.avltreeplus;

public class AVLNode {

	int element; // The data in the node

	/*@ nullable @*/AVLNode left; 

	/*@ nullable @*/AVLNode right;
	
	/*@ nullable @*/AVLNode parent;

	int height; 
	
	int balance;


	public AVLNode () {}
	
}
