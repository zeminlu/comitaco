/***********************************************************************
Author: Juan Pablo Galeotti and Marcelo Frias, Relational Formal Methods 
Group, University of Buenos Aires and Buenos Aires Institute of Technology,
Buenos Aires, Argentina.
ROOPS class BinomialHeap with an auxiliary ROOPS class BinomialHeapNode. 
These classes are an implementation of Binomial Heaps. They come with a 
JFSL [1] contract that is annotated as a ROOPS comment.
This file is an adaptation from the BinomialHeap implementation used in
[2]. The implementation contains a subtle bug reported in [3] that
requires 13 BinomialHeapNode objects to be exhibited. The adaptation
includes a goal that requires an input structure with 13 BinomialHeapNode
objects to be covered, as a means to show wether the tool under use
can generate such a complex input.

[1] http://sdg.csail.mit.edu/forge/plugin.html

[2] Visser W., Pasareanu C.S., Pelanek R., Test Input Generation for Java
Containers using State Matching, in ISSTA 2006, pp.37-48, 2006.

[3] Galeotti J.P., Rosner N., Lopez Pombo C.G. and Frias M.F.
Analysis of Invariants for Efficient Bounded Verification, in 
ISSTA 2010, to appear.

***********************************************************************/


package roops.core.objects;


/**
 * @Invariant ( all n: BinomialHeapNode | ( n in this.Nodes.*(sibling @+ child) @- null => (
 *		            ( n.parent!=null  => n.key >=  n.parent.key )  &&   
 *		            ( n.child!=null   => n !in n.child.*(sibling @+ child) @- null ) && 
 *		            ( n.sibling!=null => n !in n.sibling.*(sibling @+ child) @- null ) && 
 *		            ( ( n.child !=null && n.sibling!=null ) => (no m: BinomialHeapNode | ( m in n.child.*(child @+ sibling) @- null && m in n.sibling.*(child @+ sibling) @- null )) ) && 
 *		            ( n.degree >= 0 ) && 
 *		            ( n.child=null => n.degree = 0 ) && 
 *		            ( n.child!=null =>n.degree=#(n.child.*sibling @- null) )  && 
 *		            ( #( ( n.child @+ n.child.child.*(child @+ sibling) ) @- null ) = #( ( n.child @+ n.child.sibling.*(child @+ sibling)) @- null )  ) && 
 *		            ( n.child!=null => ( all m: BinomialHeapNode | ( m in n.child.*sibling@-null =>  m.parent = n  ) ) ) && 
 *		            ( ( n.sibling!=null && n.parent!=null ) => ( n.degree > n.sibling.degree ) )
 * ))) && 
 * ( this.size = #(this.Nodes.*(sibling @+ child) @- null) ) &&
 * ( all n: BinomialHeapNode | n in this.Nodes.*sibling @- null => ( 
 *  ( n.sibling!=null => n.degree < n.sibling.degree ) && 
 *  ( n.parent=null ) 
 *  )) ;
 */
public class BinomialHeap {

	/**
	 * @Modifies_Everything;
	 * 
	 * @Ensures false;
	 */
	static public void extractMinTest(/*@ nullable @*/ BinomialHeap binomialHeap) {
		if (binomialHeap!=null) {
		  BinomialHeapNode ret_val = binomialHeap.extractMin();
		}
	}

	/**
	 * @Modifies_Everything;
	 * 
	 * @Ensures false;
	 */
	static public void findMinTest(/*@ nullable @*/ BinomialHeap binomialHeap) {
		if (binomialHeap!=null) {
		  int ret_val = binomialHeap.findMinimum();
		}
	}

	/**
	 * @Modifies_Everything;
	 * 
	 * @Ensures false;
	 */
	static public void decreaseKeyNodeTest(/*@ nullable @*/ BinomialHeap binomialHeap, /*@ nullable @*/ BinomialHeapNode node, int new_value) {
		if (binomialHeap!=null ) {
		  binomialHeap.decreaseKeyNode(node, new_value);
		}
	}

	/**
	 * @Modifies_Everything;
	 * 
	 * @Ensures false;
	 */
	static public void insertTest(/*@ nullable @*/ BinomialHeap binomialHeap, int value) {
		if (binomialHeap!=null) {
		  binomialHeap.insert(value);
		}
	}

	private /*@ nullable @*/ BinomialHeapNode Nodes;

	private int size;

	// helper procedure
	private void merge_extractMin(BinomialHeapNode binHeap) {

		BinomialHeapNode temp1 = Nodes, temp2 = binHeap;
		while ((temp1 != null) && (temp2 != null)) {
			if (temp1.degree == temp2.degree) {
				BinomialHeapNode tmp = temp2;
				temp2 = temp2.sibling;
				tmp.sibling = temp1.sibling;
				temp1.sibling = tmp;
				temp1 = tmp.sibling;
			} else {
				if (temp1.degree < temp2.degree) {
					if ((temp1.sibling == null)
							|| (temp1.sibling.degree > temp2.degree)) {
						BinomialHeapNode tmp = temp2;
						temp2 = temp2.sibling;
						tmp.sibling = temp1.sibling;
						temp1.sibling = tmp;
						temp1 = tmp.sibling;
					} else {
						temp1 = temp1.sibling;
					}
				} else {
					BinomialHeapNode tmp = temp1;
					temp1 = temp2;
					temp2 = temp2.sibling;
					temp1.sibling = tmp;
					if (tmp == Nodes) {
						Nodes = temp1;
					} 
				}
			}
		}

		if (temp1 == null) {
			temp1 = Nodes;
			while (temp1.sibling != null) {
				temp1 = temp1.sibling;
			}
			temp1.sibling = temp2;
		} 

	}

	// another helper procedure
	private void unionNodes_extractMin(BinomialHeapNode binHeap) {
		merge_extractMin(binHeap);

		BinomialHeapNode prevTemp = null, temp = Nodes , nextTemp = Nodes.sibling;
		
		while (nextTemp != null) {
			if ((temp.degree != nextTemp.degree)
					|| ((nextTemp.sibling != null) && (nextTemp.sibling.degree == temp.degree))) {
				prevTemp = temp;
				temp = nextTemp;
			} else {
				if (temp.key <= nextTemp.key) {
					temp.sibling = nextTemp.sibling;
					nextTemp.parent = temp;
					nextTemp.sibling = temp.child;
					temp.child = nextTemp;
					temp.degree++;
				} else {
					if (prevTemp == null) {
						Nodes = nextTemp;
					} else {
						prevTemp.sibling = nextTemp;
					}
					temp.parent = nextTemp;
					temp.sibling = nextTemp.child;
					nextTemp.child = temp;
					nextTemp.degree++;
					temp = nextTemp;
				}
			}

			nextTemp = temp.sibling;
		}
	}

	public BinomialHeapNode extractMin() {

		if (Nodes == null) 
			return null;

        int old_size = size;

		BinomialHeapNode temp = Nodes, prevTemp = null;
		BinomialHeapNode minNode = findMinNode_extractMin(Nodes);
		while (temp.key != minNode.key) {
			/*{roops.util.Goals.reached(0);}*/
			prevTemp = temp;
			temp = temp.sibling;
		}

		if (prevTemp == null) {
			/*{roops.util.Goals.reached(1);}*/
			Nodes = temp.sibling;
		} else {
			/*{roops.util.Goals.reached(2);}*/
			prevTemp.sibling = temp.sibling;
		}
		temp = temp.child;
		BinomialHeapNode fakeNode = temp;
		while (temp != null) {
			/*{roops.util.Goals.reached(3);}*/
			temp.parent = null;
			temp = temp.sibling;
		}

		if ((Nodes == null) && (fakeNode == null)) {
			/*{roops.util.Goals.reached(4);}*/
			size = 0;
		} else {
			if ((Nodes == null) && (fakeNode != null)) {
				/*{roops.util.Goals.reached(5);}*/
				Nodes = fakeNode.reverse(null);
				size--;
			} else {
				/*{roops.util.Goals.reached(6);}*/
				if ((Nodes != null) && (fakeNode == null)) {
					/*{roops.util.Goals.reached(7);}*/
					size--;
				} else {
					/*{roops.util.Goals.reached(8);}*/
					unionNodes_extractMin(fakeNode.reverse(null));
					size--;
				}
			}
		}
 
		if (this.size==12) {
			/*{roops.util.Goals.reached(9);}*/
		}
		return minNode;
	}

	
	public int findMinimum() {
		return findMinNode(Nodes).key;
	}
	
	private static BinomialHeapNode findMinNode_extractMin(BinomialHeapNode arg) {
		BinomialHeapNode x = arg, y = arg;
		int min = x.key;

		while (x != null) {
			/*{roops.util.Goals.reached(0);}*/
			
			if (x.key < min) {
				/*{roops.util.Goals.reached(1);}*/
				y = x;
				min = x.key;
			}
			x = x.sibling;
		}

		/*{roops.util.Goals.reached(2);}*/
		return y;
	}

	private static BinomialHeapNode findMinNode(BinomialHeapNode arg) {
		BinomialHeapNode x = arg, y = arg;
		int min = x.key;

		while (x != null) {
			/*{roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);}*/
			
			if (x.key < min) {
				/*{roops.util.Goals.reached(1, roops.util.Verdict.REACHABLE);}*/
				y = x;
				min = x.key;
			}
			x = x.sibling;
		}

		/*{roops.util.Goals.reached(2, roops.util.Verdict.REACHABLE);}*/
		return y;
	}


	public void decreaseKeyNode(BinomialHeapNode node, int new_value) {
		if (node == null) {
			/*{roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);}*/
			return;
		}
		
		node.key = new_value;
		BinomialHeapNode tempParent = node.parent;

		while ((tempParent != null) && (node.key < tempParent.key)) {
			/*{roops.util.Goals.reached(1, roops.util.Verdict.REACHABLE);}*/
			
			int z = node.key;
			node.key = tempParent.key;
			tempParent.key = z;

			node = tempParent;
			tempParent = tempParent.parent;
		}
		/*{roops.util.Goals.reached(2, roops.util.Verdict.REACHABLE);}*/
	}

	

	public void insert(int value) {
		if (value > 0) {
			/*{roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);}*/
			
			BinomialHeapNode temp = new BinomialHeapNode();
			temp.key = value;
			
			if (Nodes == null) {
				/*{roops.util.Goals.reached(1, roops.util.Verdict.REACHABLE);}*/
				
				Nodes = temp;
				size = 1;
			} else {
				/*{roops.util.Goals.reached(2, roops.util.Verdict.REACHABLE);}*/
				
				unionNodes_insert(temp);
				size++;
			}
		} else {
			/*{roops.util.Goals.reached(21, roops.util.Verdict.REACHABLE);}*/
		}
	}

	// another helper procedure
	private void unionNodes_insert(BinomialHeapNode binHeap) {
		merge_insert(binHeap);
	
		BinomialHeapNode prevTemp = null, temp = Nodes , nextTemp = Nodes.sibling;
		
		while (nextTemp != null) {
			if ((temp.degree != nextTemp.degree)
					|| ((nextTemp.sibling != null) && (nextTemp.sibling.degree == temp.degree))) {
				
				/*{roops.util.Goals.reached(14);}
				
				prevTemp = temp;
				temp = nextTemp;
			} else {
				
				/*{roops.util.Goals.reached(15, roops.util.Verdict.REACHABLE);}*/
				
				if (temp.key <= nextTemp.key) {
					
					/*{roops.util.Goals.reached(16, roops.util.Verdict.REACHABLE);}*/
					
					temp.sibling = nextTemp.sibling;
					nextTemp.parent = temp;
					nextTemp.sibling = temp.child;
					temp.child = nextTemp;
					temp.degree++;
				} else {
					
					/*{roops.util.Goals.reached(17, roops.util.Verdict.REACHABLE);}*/
					
					if (prevTemp == null) {
						
						/*{roops.util.Goals.reached(18, roops.util.Verdict.REACHABLE);}*/
						
						Nodes = nextTemp;
					} else {
						
						/*{roops.util.Goals.reached(19, roops.util.Verdict.REACHABLE);}*/
						prevTemp.sibling = nextTemp;
					}
					temp.parent = nextTemp;
					temp.sibling = nextTemp.child;
					nextTemp.child = temp;
					nextTemp.degree++;
					temp = nextTemp;
				}
			}
	
			/*{roops.util.Goals.reached(20, roops.util.Verdict.REACHABLE);}*/
			nextTemp = temp.sibling;
		}
	}

	// helper procedure
	private void merge_insert(BinomialHeapNode binHeap) {
	
		BinomialHeapNode temp1 = Nodes, temp2 = binHeap;
		while ((temp1 != null) && (temp2 != null)) {
			
			/*{roops.util.Goals.reached(3, roops.util.Verdict.REACHABLE);}*/
			
			if (temp1.degree == temp2.degree) {
				
				/*{roops.util.Goals.reached(4, roops.util.Verdict.REACHABLE);}*/
				
				BinomialHeapNode tmp = temp2;
				temp2 = temp2.sibling;
				tmp.sibling = temp1.sibling;
				temp1.sibling = tmp;
				temp1 = tmp.sibling;
			} else {
				
				/*{roops.util.Goals.reached(5, roops.util.Verdict.REACHABLE);}*/
				
				if (temp1.degree < temp2.degree) {
					
					/*{roops.util.Goals.reached(6, roops.util.Verdict.REACHABLE);}*/
					
					if ((temp1.sibling == null)
							|| (temp1.sibling.degree > temp2.degree)) {
						
						/*{roops.util.Goals.reached(7, roops.util.Verdict.REACHABLE);}*/
						
						BinomialHeapNode tmp = temp2;
						temp2 = temp2.sibling;
						tmp.sibling = temp1.sibling;
						temp1.sibling = tmp;
						temp1 = tmp.sibling;
					} else {
						
						/*{roops.util.Goals.reached(8, roops.util.Verdict.REACHABLE);}*/
						
						temp1 = temp1.sibling;
					}
				} else {
					/*{roops.util.Goals.reached(9, roops.util.Verdict.REACHABLE);}*/
					
					BinomialHeapNode tmp = temp1;
					temp1 = temp2;
					temp2 = temp2.sibling;
					temp1.sibling = tmp;
					if (tmp == Nodes) {
						/*{roops.util.Goals.reached(10, roops.util.Verdict.REACHABLE);}*/
						Nodes = temp1;
					} 
				}
			}
		}
	
		if (temp1 == null) {

			/*{roops.util.Goals.reached(11, roops.util.Verdict.REACHABLE);}*/
			temp1 = Nodes;
			while (temp1.sibling != null) {

				/*{roops.util.Goals.reached(12, roops.util.Verdict.REACHABLE);}*/
				temp1 = temp1.sibling;
			}
			
			/*{roops.util.Goals.reached(13, roops.util.Verdict.REACHABLE);}*/
			temp1.sibling = temp2;
		} 
	
	}

	
}
/* end roops.core.objects */
