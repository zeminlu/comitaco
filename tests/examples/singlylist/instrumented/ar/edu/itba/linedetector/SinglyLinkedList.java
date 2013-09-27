package .Users.santi.Documents.Doctorado.comitaco.tests.examples.singlylist.instrumented.ar.edu.itba.linedetector;

import java.io.IOException;
import ar.edu.taco.utils.FileUtils;

/**
 * @Invariant all n: SinglyLinkedListNode | ( ( n in this.header.*next @- null ) => ( n !in n.next.*next @- null ) ) ;
 */
/**
 * @SpecField myseq: seq SinglyLinkedListNode from this.header, SinglyLinkedListNode.next | (
 *		( #(this.header.*next @- null) = #(this.myseq) ) && 
 *		( this.header=null => #(this.myseq)=0 ) && 
 *		( this.header!= null => this.header=this.myseq[0] ) && 
 *		( all i: int | ( ( i >= 0 && i < ( #(this.myseq) - 1) ) => ( this.myseq[i + 1] = this.myseq[i].next ) ) )
 *		) ;
 */
public class SinglyLinkedList 
{
    protected static ar.edu.taco.stryker.api.impl.ReachSet reach(Object o, Class<?> clazz, String str) {
        String fieldsToMoveThroughAsStrings[] = str.replaceAll(" ", "").split("\\+");
        ar.edu.taco.stryker.api.impl.ReachSet objectSet = new ar.edu.taco.stryker.api.impl.ReachSet();
        java.util.IdentityHashMap<Object,Object> visitedObjects = new java.util.IdentityHashMap<Object,Object>();
        java.util.Queue<Object> queue = new java.util.LinkedList<Object>();
        
		 if(o == null) {
        	return objectSet;
		 }
        visitedObjects.put(o, o);
        queue.add(o);
		 objectSet.add(o);
        while(!queue.isEmpty()) {
            Object currentObject = queue.poll();
            
            java.util.List<java.lang.reflect.Field> fieldsToMoveThrough = new java.util.ArrayList<java.lang.reflect.Field>();
            for(int i = 0; i < fieldsToMoveThroughAsStrings.length; i++) {
                    String fieldAsString = fieldsToMoveThroughAsStrings[i];
                try {
                    java.lang.reflect.Field field = currentObject.getClass().getDeclaredField(fieldAsString);
                    fieldsToMoveThrough.add(field);
                } catch (NoSuchFieldException e) {
                e.printStackTrace();
                } catch (SecurityException e) {
                     e.printStackTrace();
                }
            }
            
            for(java.lang.reflect.Field field : fieldsToMoveThrough) {
                field.setAccessible(true);
                try {
                    Object accessedObject = field.get(currentObject);
                    if(accessedObject != null && visitedObjects.containsKey(accessedObject) == false) {
                        visitedObjects.put(accessedObject, accessedObject);
                        queue.add(accessedObject);
                        if(clazz.isInstance(accessedObject)) {
                            objectSet.add(accessedObject);
                        }
                    }
                } catch (IllegalArgumentException e) {
                e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return objectSet;
    }



	/*@ nullable @*/SinglyLinkedListNode header;

	/**
	 * @Modifies_Everything
	 * 
	 * @Ensures (some i: int | ( ( i>=0 && i < #(this.myseq) ) && (this.myseq[i] in SinglyLinkedListNode) && (this.myseq[i].value=value_param)) ) <=> return=true ;
	 */
	public boolean contains(/*@ nullable @*/Object value_param) {
		SinglyLinkedListNode current;
		boolean result;

		current = this.header;
		result = false;
		while (result == false && current != null) {
			boolean equalVal;

			// begin equalVal = this.jml_isEqualValue(current.nodeValue,
			// value_param);
			if (value_param == null && current.value == null)
				equalVal = true;
			else if (value_param != null)

				if (value_param == current.value)
					equalVal = true;
				else
					equalVal = false;
			else
				equalVal = false;
			// end equalVal = this.jml_isEqualValue(current.nodeValue,
			// value_param);

			if (equalVal == true) {
				result = true;
			}
			current = current.next.next;/*current.next*/
		}
		return result;
	}

	/**
	 * @Requires ( index>=0 && index<#(this.myseq) ) ;
	 * 
	 * @Modifies_Everything
	 *
	 * @Ensures ( #(this.myseq) = #(@old(this.myseq))-1 ) && 
	 *		( all i: int | ( (i>=0 && i<index ) => (this.myseq[i] = @old(this.myseq[i]) ) )) && 
	 *		( all j: int | ( (j>=index && j<#(this.myseq)) => this.myseq[j]=@old(this.myseq[j+1]) ) ) ;
	 */
	public void remove(int index) {
		SinglyLinkedListNode current;
		current = this.header;
		SinglyLinkedListNode previous;
		previous = null;
		int current_index;
		current_index = 0;
		
		boolean found = false;
		
		while (found==false && current != null) {
			if (index == current_index) {
				found = true;
			} else {
				current_index = current_index + 1;
				previous = current;
				current = current.next;
			}
		}
		
		if (previous == null)
			this.header = current.next;
		else
			previous.next = current.next;
		
	}

	/**
	 * @Modifies_Everything
	 * 
	 * @Ensures ( #(this.myseq)=#(@old(this.myseq))+1 ) && 
	 *          ( this.myseq[#(this.myseq)-1].value=arg ) && 
	 *		    ( all i: int | ( ( i>=0 && i<#(@old(this.myseq)) ) => ( this.myseq[i]=@old(this.myseq[i]) )) )
	 */
	public void insertBack(/*@ nullable @*/Object arg) {
		SinglyLinkedListNode freshNode = new SinglyLinkedListNode();
		freshNode.value = arg;
		freshNode.next = null;

		if (this.header == null)
			this.header = freshNode;
		else {
			SinglyLinkedListNode current;
			current = this.header;
			while (current.next != null) {
				current = current.next;
			}
			current.next = freshNode;
		}
	}

	
//	public static void main(String[] args) {
//		SinglyLinkedList list = new SinglyLinkedList();
//		list.insertBack(new Object());
//		list.remove(0);
//		
//	}
	
}
