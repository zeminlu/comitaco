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
package ar.edu.taco.regresion.amelie;

import ar.edu.taco.regresion.RegresionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class CList2fTest extends RegresionTestBase {
	private static final String CONFIG_AMELIA_DLIST_PROPERTIES = "config/amelia/clist2f.properties";

	//removeOk
	public void testCheckRemoveOk() throws VizException {
	    runAndCheck(CONFIG_AMELIA_DLIST_PROPERTIES,"removeOk_0",false);		
	}
	
	//removeBuggy
	
	public void testCheckRemoveBuggy() throws VizException {
		setConfigKeyObjectScope(4);
		setConfigKeyTypeScopes("java.lang.Object:8");
		setConfigKeyIntBithwidth(4);				
		runAndCheck(CONFIG_AMELIA_DLIST_PROPERTIES,"removeBuggy_0",true);
	}

	//ShowInstance
	public void testCheckShowInstance() throws VizException {
		setConfigKeyObjectScope(4);
		setConfigKeyTypeScopes("java.lang.Object:6");
		setConfigKeyIntBithwidth(4);		
		runAndCheck(CONFIG_AMELIA_DLIST_PROPERTIES,"showInstance_0",true);		
	}

	
}
