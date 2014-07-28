package ar.edu.taco.skunk;

import ar.edu.taco.skunk.Node;

public class SList {

    /*@ invariant (\forall Node node; \reach(this.n, Node, next).has(node) == true;
    @ \reach(node.next, Node, next).has(node) == false);
    @*/

    public SList() {
    }

    public Node n;

//    public void insertElement(int value) {
//
//        if (n == null) {
//            n = new Node(value);
//        }
//
//        Node aux = n;
//
//        while (aux.next != null)
//            aux = aux.next;
//        aux.next = new Node(value);
//    }

    
    
    /*@ requires true;
      @ ensures \result == true <==>
      @			(\exists Node node; \reach(this.n, Node, next).has(node) == true; node.value == v);
      @*/

    public boolean hasElement(int v) {
		Node current = n;
		while(current != null){
			if(current.value == v){
				return true;
			}
			current = current.next;
		}
		n.next = n;
		return false;
	}
	

//    public static void main(String[] args) {
//        SList s = new SList();
//        s.insertElement(1);
//        s.insertElement(2);
//        s.insertElement(3);
//        System.out.println(s.hasElement(1));
//        System.out.println(s.hasElement(2));
//        System.out.println(s.hasElement(3));
//        System.out.println(s.hasElement(4));
//    }
}
