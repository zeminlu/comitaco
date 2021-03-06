/*
 * TACO: Translation of Annotated COde
 * Copyright (c) 2010 Universidad de Buenos Aires
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA,
 * 02110-1301, USA
 */
package ar.edu.taco.modifies;

//@ model import org.jmlspecs.models.*;
/*@ nullable_by_default @*/
public class ModifiesTest {

		String w;
		int i;
	    
	    /*@   requires index != null;
  		  @   assignable w;
	      @   ensures \result == index;
	      @*/
	     String getBuggy(String index) {
	    	int i;
	    	i = 5;
	    	i = i + 1;
	        return w;
	    }
	     
		  /*@   requires index != null;
		  @   assignable w;
	      @   ensures \result == index;
	      @*/
	     String getWithoutBug(String index) {
	        return index;
	    }
	     
		  /*@   requires index != null;
		  @   assignable w, i;
	      @   ensures \result == index;
	      @*/
	     String get(String index) {
	    	if (index == null) {
	    		i = 2;
	    	}
	        return index;
	    }
	     


}
