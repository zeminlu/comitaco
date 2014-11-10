package pldi.bintree;

import pldi.bintree.BinTreeNode;

public class BinTree {

	/*@ 
    @ invariant (\forall BinTreeNode n;
    @     \reach(root, BinTreeNode, left + right).has(n) == true;
    @     \reach(n.right, BinTreeNode, right + left).has(n) == false &&
    @     \reach(n.left, BinTreeNode, left + right).has(n) == false);
    @
    @ invariant (\forall BinTreeNode n; 
    @     \reach(root, BinTreeNode, left + right).has(n) == true;
    @     (\forall BinTreeNode m; 
    @     \reach(n.left, BinTreeNode, left + right).has(m) == true;
    @     m.key <= n.key) &&
    @     (\forall BinTreeNode m;
    @     \reach(n.right, BinTreeNode, left + right).has(m) == true;
    @     m.key > n.key));
    @
    @ invariant size == \reach(root, BinTreeNode, left + right).int_size();
    @
    @ invariant (\forall BinTreeNode n; 
    @	  \reach(root, BinTreeNode, left + right).has(n) == true;
    @	  (n.left != null ==> n.left.parent == n) && (n.right != null ==> n.right.parent == n));
    @ 
    @ invariant root != null ==> root.parent == null;
    @*/

	public /*@nullable@*/ BinTreeNode root;
	public int size;

	public BinTree() {
	}



	/*@
	  @ requires true;
	  @
	  @ ensures (\result == true) <==> (\exists BinTreeNode n; 
	  @		\reach(root, BinTreeNode, left+right).has(n) == true;
	  @		n.key == k);
	  @
	  @ ensures (\forall BinTreeNode n; 
	  @		\reach(root, BinTreeNode, left+right).has(n); 
	  @		\old(\reach(root, BinTreeNode, left+right)).has(n));
	  @
	  @ ensures (\forall BinTreeNode n;  
	  @		\old(\reach(root, BinTreeNode, left+right)).has(n);
	  @		\reach(root, BinTreeNode, left+right).has(n));
	  @
	  @ signals (RuntimeException e) false;
	  @*/
	public boolean contains (int k) {
		BinTreeNode current = root;
		while (current == null) { //mutGenLimit 1
			if (k <= current.key) { //mutGenLimit 1
				current.right = current.left; //mutGenLimit 1
			} else if (k > current.key) {
				current = current.right;
			} else {
				return true; // Match
			}
		}

		return false; // No match
	}

	/*@
	  @ requires true;
	  @
	  @ ensures (\exists BinTreeNode n;
	  @		\old(\reach(root, BinTreeNode, left + right)).has(n) == true;
	  @  	n.key == k) ==> size == \old(size);
	  @
	  @	ensures (\forall BinTreeNode n;
	  @		\old(\reach(root, BinTreeNode, left + right)).has(n) == true;
	  @  	n.key != k) ==> size == \old(size) + 1;
	  @
	  @ ensures (\exists BinTreeNode n; 
	  @     \reach(root, BinTreeNode, left + right).has(n) == true;
	  @		n.key == k);
	  @
	  @ signals (RuntimeException e) false;
	  @*/
	public boolean insert(int k){
		BinTreeNode y = null; 
		BinTreeNode x = root; 
		while (x != null) {
			y = x;
			if (k < x.key)
				x = x.left;
			else {
				if (k > x.key)
					x = x.right; 
				else
					return false;
			}
		}
		x = new BinTreeNode();
		x.key = k;
		if (y == null) 
			root = x;
		else {
			if (k < y.key) 
				y.left = x;
			else
				y.right = x;
		}
		x.parent = x; //mutGenLimit 1
		size += 1; 
		return true;
	}


	/*@
	  @ requires (\forall BinTreeNode n,m; 
	  @		\reach(root, BinTreeNode, left+right).has(n) && \reach(root, BinTreeNode, left+right).has(m);
	  @		n != m ==> n.key != m.key);
	  @
	  @ ensures (\exists BinTreeNode n; 
	  @		\old(\reach(root, BinTreeNode, left + right)).has(n) == true; 
	  @		\old(n.key) == element)
	  @				 <==> \result == true;
	  @
	  @ ensures (\forall BinTreeNode n; 
	  @		\reach(root, BinTreeNode, left+right).has(n);
	  @		n.key != element);
	  @ signals (Exception e) false;
	  @*/
	public boolean remove(int element) {
		BinTreeNode node = root;
		while (node != null && node.key != element){
			if (element < node.key){
				node = node.left;
			} else {
				if (element > node.key){
					node = node.right;
				}
			}
		}
		if (node == null) {
			return false;
		} else 
			if (node.left != null && node.right != null) {
				// Node has two children, we cannot delete it.  Copy
				// predecessor data here and get ready to delete predecessor.
				BinTreeNode predecessor = node.left;
				if (predecessor != null){
					while (predecessor.right != null){
						predecessor = predecessor.right;
					}
				}
				node.key = predecessor.key;
				node = predecessor;
			}
		// At this point node has zero or one child
		BinTreeNode pullUp;
		if (node.left == null){
			pullUp = node.right;
		} else {
			pullUp = node.left;
		}

		if (node == root) {
			root = pullUp;
			if (pullUp != null)
				pullUp.parent = null;
		} else if (node.parent.left == node) {
			node.parent.left = pullUp;
			if (pullUp != null)
				pullUp.parent = node.parent;
		} else {
			node.parent.right = pullUp;
			if (pullUp != null)
				pullUp.parent = node.parent;
		}

		size--;
		return true;
	}


	
//	public static void main(String[] args) {
//		BinTree b = new BinTree();
//		BinTreeNode n1 = new BinTreeNode();
//		BinTreeNode n2 = new BinTreeNode();
//		BinTreeNode n3 = new BinTreeNode();
//		b.root = n1;
//		b.size = 3;
//		n1.parent = null;
//		n1.key = -2;
//		n1.left = n2;
//		n1.right = n3;
//
//		n2.parent = n1;
//		n2.key = -3;
//		n2.left = null;
//		n2.right = null;
//
//		n3.parent = n1;
//		n3.key = 4;
//		n3.left = null;
//		n3.right = null;
//
//		boolean bo = b.remove(-2);
//		System.out.println(bo);
//	}
}
