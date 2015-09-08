package  ar.edu.taco.skunk;

import java.util.HashSet;
import java.util.Set;

/**
 * @SpecField blackHeight : int from this.left, this.right |
 * (
 *		( this.left=null && this.right=null => this.blackHeight=1 ) && 
 *
 *		( this.left!=null && this.right=null => ( 
 *		        ( ( this in this.left.*(left@+right)@-null ) => this.blackHeight=0 ) && 
 *		        ( ( this !in this.left.*(left@+right)@-null ) => ( 
 *		                ( this.left.color=true  => this.blackHeight=this.left.blackHeight +1 ) && 
 *		                ( this.left.color=false => this.blackHeight=this.left.blackHeight )  
 *		         ))
 *		                                        )) && 
 *		( this.left=null && this.right!=null => ( 
 *		        ( ( this in this.right.*(left@+right)@-null ) => this.blackHeight=0 ) && 
 *		        ( ( this !in this.right.*(left@+right)@-null ) => ( 
 *		                ( this.right.color=true  => this.blackHeight=this.right.blackHeight +1 ) && 
 *		                ( this.right.color=false => this.blackHeight=this.right.blackHeight )  
 *		         ))
 *		                                        )) &&
 * 
 *		( this.left!=null && this.right!=null => ( 
 *		        ( ( this in this.^(left@+right)@-null ) => this.blackHeight=0 ) && 
 *		        ( ( this !in this.^(left@+right)@-null ) => ( 
 *		                ( this.left.color=true  => this.blackHeight=this.left.blackHeight +1 ) && 
 *		                ( this.left.color=false => this.blackHeight=this.left.blackHeight )  
 *		                                        ))
 *		         ))                                  
 *
 * ) ;
 */
public class TreeSetEntry {
	
	
	public /*@ nullable @*/TreeSetEntry left = null;
	public /*@ nullable @*/TreeSetEntry right = null;
	public /*@ nullable @*/TreeSetEntry parent;
	public int key;
	public int color;

	

	public TreeSetEntry() {}



}

	/* end roops.core.objects */

