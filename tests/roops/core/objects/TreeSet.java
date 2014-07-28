/*
 * @(#)TreeMap.java	1.56 03/01/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package roops.core.objects;


import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import roops.core.objects.PairObjectInt;




public class TreeSet {

	

	public /*@ nullable @*/ TreeSetEntry root = null;

	/**
	 * The number of entries in the tree
	 */
	public int size = 0;

	/**
	 * The number of structural modifications to the tree.
	 */
	int modCount = 0;

	final boolean RED = false;
	final boolean BLACK = true;


//	  /*@  
//		@ requires true;
//		@ ensures false;
//		@*/
//		public boolean repOk() {
//			List workList = new ArrayList();
//			workList.add(root);
//			Set visited = new HashSet();
//			visited.add(root);
//			return true;
//		}	
		

	
	  /*@  
		@ requires true;
		@ requires \reach(this.root, TreeSetEntry, left + right).int_size() == 5;
		@ ensures \result == false;
		@ signals (Exception e) false;
		@*/
		public boolean repOk() {
			if (root == null)
				return size == 0;

			if (root.parent != null)
				return false;

			HashSet visited = new HashSet();
			visited.add(root);
			ArrayList workList = new ArrayList();
			workList.add(root);
		
			while (workList.size() > 0) {
				
				TreeSetEntry current = (TreeSetEntry) workList.get(0);
				workList.remove(0);

				TreeSetEntry cl = current.left;
				if (cl != null) {
					if (!visited.add(cl))
						return false;
					if (cl.parent != current)
						return false;
					workList.add(cl);
				}
				
				TreeSetEntry cr = current.right;
				if (cr != null) {
					if (!visited.add(cr))
						return false;
					if (cr.parent != current)
						return false;
					workList.add(cr);
				}
			}

			if (visited.size() != size)
				return false;

			if (!repOK_Colors())
				return false;

			return repOK_KeysAndValues();
		}


		
	      /*@ requires true;
			@ ensures true;
			@*/
			public boolean repOK_Colors() {

				ArrayList workList = new ArrayList();
				workList.add(root);
				while (workList.size() > 0) {
					TreeSetEntry current = (TreeSetEntry) workList.get(0);
					workList.remove(0);
					TreeSetEntry cl = current.left;
					TreeSetEntry cr = current.right;
					if (current.color == RED) {
						if (cl != null && cl.color == RED)
							return false;
						if (cr != null && cr.color == RED)
							return false;
					}
					if (cl != null)
						workList.add(cl);
					if (cr != null)
						workList.add(cr);
				}

				if (root == null)
					return true;
				
				int numberOfBlack = -1;
				ArrayList workList2 = new ArrayList();
				
				workList2.add(new PairObjectInt(root, 0));
				
				while (workList2.size() > 0) {
					PairObjectInt p = (PairObjectInt) workList2.get(0);
					workList2.remove(0);
					TreeSetEntry e = (TreeSetEntry) p.fst;
					int n = p.snd;
					if (e.color == BLACK)
						n++;
					if (e.left==null || e.right==null) {
						if (numberOfBlack == -1)
							numberOfBlack = n+1;
						else if (numberOfBlack != n+1)
							return false;
						TreeSetEntry tse;
						if (e.left != null)
							workList2.add(new PairObjectInt(e.left, n));
						if (e.right != null)
							workList2.add(new PairObjectInt(e.right, n));
					} else {
						workList2.add(new PairObjectInt(e.left, n));
						workList2.add(new PairObjectInt(e.right, n));
					}
				}
				return true;
			}

			
			
			public boolean repOK_KeysAndValues() {
				int min = repOK_findMin(root);
				int max = repOK_findMax(root);
				if (!repOK_orderedKeys(root, min-1, max+1))
					return false;

				// touch values
				ArrayList workList = new ArrayList();
				workList.add(root);
				while (workList.size() > 0) {
					TreeSetEntry current = (TreeSetEntry) workList.get(0);
					workList.remove(0);

					if (current.left != null)
						workList.add(current.left);
					if (current.right != null)
						workList.add(current.right);
				}
				return true;
			}

			private int repOK_findMin(TreeSetEntry root) {
				TreeSetEntry curr = root;
				while (curr.left!=null) {
					curr = curr.left;
				}
				return curr.key;
			}

			private int repOK_findMax(TreeSetEntry root) {
				TreeSetEntry curr = root;
				while (curr.right!=null) {
					curr = curr.right;
				}
				return curr.key;
			}

			public boolean repOK_orderedKeys(TreeSetEntry e, int min, int max) {

				if ((e.key <= min) || (e.key >= max))
					return false;
				if (e.left != null)
					if (!repOK_orderedKeys(e.left, min, e.key))
						return false;

				if (e.right != null)
					if (!repOK_orderedKeys(e.right, e.key, max))
						return false;
				return true;
			}
	
			
//      /*@ requires true;
//		@ ensures true;
//		@*/
//		public boolean repOK_Colors() {
//
//			List workList = new ArrayList();
//			workList.add(root);
//			while (workList.size() > 0) {
//				TreeSetEntry current = (TreeSetEntry) workList.get(0);
//				workList.remove(0);
//				TreeSetEntry cl = current.left;
//				TreeSetEntry cr = current.right;
//				if (current.color == RED) {
//					if (cl != null && cl.color == RED)
//						return false;
//					if (cr != null && cr.color == RED)
//						return false;
//				}
//				if (cl != null)
//					workList.add(cl);
//				if (cr != null)
//					workList.add(cr);
//			}
//
//			int numberOfBlack = -1;
//			List workList2 = new ArrayList();
//			
//			workList2.add(new PairObjectInt(root, 0));
//			
//			while (workList2.size() > 0) {
//				PairObjectInt p = (PairObjectInt) workList2.get(0);
//				workList2.remove(0);
//				TreeSetEntry e = (TreeSetEntry) p.fst;
//				int n = p.snd;
//				if (e != null && e.color == BLACK)
//					n++;
//				if (e == null) {
//					if (numberOfBlack == -1)
//						numberOfBlack = n;
//					else if (numberOfBlack != n)
//						return false;
//				} else {
//					workList2.add(new PairObjectInt(e.left, n));
//					workList2.add(new PairObjectInt(e.right, n));
//				}
//			}
//			return true;
//		}


	
}