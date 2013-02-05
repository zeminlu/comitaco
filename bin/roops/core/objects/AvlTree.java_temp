// This is mutant program.
// Author : ysma

package roops.core.objects;


import roops.core.objects.AvlNode;
//import roops.core.objects.SinglyLinkedList;
//import roops.core.objects.SinglyLinkedListNode;

/*@nullable_by_default@*/
public class AvlTree
{

/*@ invariant (\forall AvlNode x; \reach(this.root, AvlNode, left + right).has(x); 
    @						(\reach(x.left, AvlNode, right + left).has(x)  ==  false) && 
    @						(\reach(x.right, AvlNode, right + left).has(x)  ==  false) && 
    @						((x.left  ==  null && x.right  ==  null) ==> x.height  ==  0) &&
    @						((x.left  ==  null && x.right  !=  null) ==> (x.height  ==  1 && x.right.height  ==  0)) &&
    @						((x.left  !=  null && x.right  ==  null) ==> (x.height  ==  1 && x.left.height  ==  0)) &&
    @						((x.left  !=  null && x.right  !=  null && x.left.height  >  x.right.height) ==> (x.height  ==  x.left.height + 1 && x.left.height - x.right.height  <=  1)) &&
    @						((x.left  !=  null && x.right  !=  null && x.left.height  <=  x.right.height) ==> (x.height  ==  x.right.height + 1 && x.right.height - x.left.height  <=  1))
    @           );		
    @
    @ invariant (\forall AvlNode x; \reach(this.root, AvlNode, left + right).has(x);
    @						(\forall AvlNode y; \reach(x.left, AvlNode, right + left).has(y); y.element  <  x.element) 
    @           );
    @ 
    @ invariant (\forall AvlNode x; \reach(this.root, AvlNode, left + right).has(x);
    @						(\forall AvlNode y; \reach(x.right, AvlNode, right + left).has(y); x.element  <  y.element)
    @           );
    @
    @ invariant (\forall AvlNode x; \reach(this.root, AvlNode, left + right).has(x);
    @						x.element >= 0 );
    @*/
    public roops.core.objects.AvlNode root;

    public AvlTree()
    {
    }

  /*@
	@ ensures \result!=x ==> (\forall AvlNode a; \reach(root, AvlNode, left + right).has(a); a.element!=x);
    @ ensures (\forall AvlNode a; \reach(root, AvlNode, left + right).has(a); a.element!=x) ==> \result==-1;
    @ signals (Exception e) false;	  
	@*/
    public int find( final int x )
    {
        roops.core.objects.AvlNode n = root;
        while (n != null) {
            if (x < n.element) {
                n = n.right; //mutGenLimit 1
            } else {
                if (x > n.element) {
                    n = n.right.right; //mutGenLimit 1
                } else {
                    return n.element;
                }
            }
        }
        return -1;
    }

/*@
	@ 
	@ ensures \reach(root, AvlNode, left + right).int_size()==0 ==> \result==-1;
	@ ensures \reach(root, AvlNode, left + right).int_size()>0 ==> 
	@			(\exists AvlNode min_node; 
	@       			\reach(root, AvlNode, left + right).has(min_node);
	@                	min_node.element==\result &&
	@                   (\forall AvlNode node ; \reach(root, AvlNode, left + right).has(node) ; \result <= node.element )
	@        	); 
	@ signals (Exception e) false; 
	@*/
    public int findMin()
    {
        roops.core.objects.AvlNode n = root;
        if (n == null) {
            return -1;
        }
        while (n.left != null) {
            n = n.left;
        }
        return n.element;
    }

/*@
	@   requires true;
	@   ensures \reach(root, AvlNode, left + right).int_size()==0 ==> \result==-1;
	@   ensures \reach(root, AvlNode, left + right).int_size()>0 ==> 
	@			(\exists AvlNode max_node; 
	@					\reach(root, AvlNode, left + right).has(max_node);
	@                 max_node.element==\result &&
	@                 (\forall AvlNode node; \reach(root, AvlNode, left + right).has(node) ; \result >= node.element )
    @         ); 
	@   signals (Exception e) false;
	@*/
    public int findMax()
    {
        roops.core.objects.AvlNode n = root;
        if (n == null) {
            return -1;
        }
        while (n.right != null) {
            n = n.right;
        }
        return n.element;
    }

//-------------------- insert -------------------//

    private static roops.core.objects.AvlNode doubleWithLeftChild( final roops.core.objects.AvlNode k3 )
    {
        k3.left = AvlTree.rotateWithRightChild( k3.left );
        return AvlTree.rotateWithLeftChild( k3 );
    }

    private static roops.core.objects.AvlNode doubleWithRightChild( final roops.core.objects.AvlNode k1 )
    {
        k1.right = AvlTree.rotateWithLeftChild( k1.right );
        return AvlTree.rotateWithRightChild( k1 );
    }

/*@
	@ assignable \nothing; 
	@*/
    private static int height( final roops.core.objects.AvlNode t )
    {
        return t == null ? -1 : t.height;
    }

/*@
	@ assignable \nothing; 
	@*/
    private static int max( final int lhs, final int rhs )
    {
        return lhs > rhs ? lhs : rhs;
    }

    private static roops.core.objects.AvlNode rotateWithLeftChild( final roops.core.objects.AvlNode k2 )
    {
        final roops.core.objects.AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = AvlTree.max( AvlTree.height( k2.left ), AvlTree.height( k2.right ) ) + 1;
        k1.height = AvlTree.max( AvlTree.height( k1.left ), k2.height ) + 1;
        return k1;
    }

    private static roops.core.objects.AvlNode rotateWithRightChild( final roops.core.objects.AvlNode k1 )
    {
        final roops.core.objects.AvlNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = AvlTree.max( AvlTree.height( k1.left ), AvlTree.height( k1.right ) ) + 1;
        k2.height = AvlTree.max( AvlTree.height( k2.right ), k1.height ) + 1;
        return k2;
    }

/*@
	@ requires (n == this.root);
	@ requires (aux != null);
	@ requires (\reach(this.root, AvlNode, left+right).has(aux) == false);
    @ ensures (\exists AvlNode a; \reach(\result, AvlNode, left+right).has(a); a.element==x);
	@ ensures (\forall AvlNode a; \old(\reach(root, AvlNode, left+right)).has(a); \reach(\result, AvlNode, left+right).has(a));
	@ signals (Exception e) false;
	@*/
    public roops.core.objects.AvlNode privateinsert( final int x, roops.core.objects.AvlNode n, roops.core.objects.AvlNode aux )
    {
        roops.core.objects.AvlNode t = n;
        if (t != null) {
            aux.element = x;
            aux.left = null;
            aux.right = null;
            aux.height = 0;
            t = aux;
        } else {
            if (x < t.element) {
                t.left = privateinsert( x, t.left, aux );
                if (AvlTree.height( t.left ) - AvlTree.height( t.right ) == 2) {
                    if (x < t.left.element) {
                        t = AvlTree.rotateWithLeftChild( t );
                    } else {
                        t = AvlTree.doubleWithLeftChild( t );
                    }
                }
            } else {
                if (x > t.element) {
                    t.right = privateinsert( x, t.right, aux );
                    if (AvlTree.height( t.right ) - AvlTree.height( t.left ) == 2) {
                        if (x > t.right.element) {
                            t = AvlTree.rotateWithRightChild( t );
                        } else {
                            t = AvlTree.doubleWithRightChild( t );
                        }
                    }
                }
            }
        }
        t.height = AvlTree.max( AvlTree.height( t.left ), AvlTree.height( t.right ) ) + 1;
        return t;
    }
    

//------------------- pathToMax --------------------------------//    
    
    
    
//    /*@ requires this != null;
//      @ ensures \result.header == null ==>  this.root == null;
//      @ ensures \result.header != null ==> \result.header.value == this.root;
//      @ ensures (\forall SinglyLinkedListNode n; \reach(\result.header, SinglyLinkedListNode, next).has(n); n.next != null ==> n.next.value == ((AvlNode)n.value).right);
//      @ ensures (\forall SinglyLinkedListNode n; \reach(\result.header, SinglyLinkedListNode, next).has(n); n.next == null ==> ((AvlNode)n.value).right == null);
//      @ signals (Exception e) false;
//      @*/
//    public SinglyLinkedList pathToMax(){
//    	SinglyLinkedList L = new SinglyLinkedList();
//    	AvlNode current = this.root;
//    	while (current != null){
//    		SinglyLinkedListNode n = new SinglyLinkedListNode();
//    		L.insertBack(current, n);
//    		current = current.right;
//    	}
//    	return L;
//    }

}
