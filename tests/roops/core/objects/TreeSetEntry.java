package roops.core.objects;



public class TreeSetEntry {
	public int key;
	public /*@ nullable @*/TreeSetEntry parent;
	public boolean color = false;
	public /*@ nullable @*/TreeSetEntry left = null;
	public /*@ nullable @*/TreeSetEntry right = null;

	/*@ public model int blackHeight;
	  @ represents blackHeight \such_that 
	  @				( ( left == null && right == null ) ==> ( blackHeight == 1 ) ) &&
	 @				( left != null && right == null ==> ( 
	 @				      ( left.color == false ) && 
	 @				      ( left.blackHeight == 1 ) && 
	 @				      ( blackHeight == 1 )  
	 @				)) &&
	 @				( left == null && right != null ==>  ( 
	 @				      ( right.color == false ) && 
	 @				      ( right.blackHeight == 1 ) && 
	 @				      ( blackHeight == 1 ) 
	 @				 )) && 
	 @				( left != null && right != null && left.color == false && right.color == false ==> ( 
	 @				        ( left.blackHeight == right.blackHeight ) && 
	 @				        ( blackHeight == left.blackHeight ) 
	 @				)) && 
	 @				( left != null && right != null && left.color == true && right.color == true ==> ( 
	 @				        ( left.blackHeight == right.blackHeight ) && 
	 @				        ( blackHeight == left.blackHeight + 1 )  
	 @				)) && 
	 @				( left != null && right != null && left.color == false && right.color == true ==> ( 
	 @				        ( left.blackHeight == right.blackHeight + 1 ) && 
	 @				        ( blackHeight == left.blackHeight  )  
	 @				)) && 
	 @				( left != null && right != null && left.color == true && right.color == false ==> ( 
	 @				        ( right.blackHeight == left.blackHeight + 1 ) && 
	 @				        ( blackHeight == right.blackHeight  )  
	 @			    ));
	  @*/

	
	public TreeSetEntry () {}
}