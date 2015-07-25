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
package icse.binomialheap.set3;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerBinomialHeapFindMinimum6Bug1Dx2Dx4Dx5Dx6Ix11DTest extends CollectionTestBase {

    @Override
    protected String getClassToCheck() {
        return "icse.binomialheap.set3.BinomialHeapFindMinimum6Bug1Dx2Dx4Dx5Dx6Ix11D";
    }
    
    public void test_insertTest() throws VizException {
        setConfigKeyRelevantClasses("icse.binomialheap.set3.BinomialHeapFindMinimum6Bug1Dx2Dx4Dx5Dx6Ix11D,icse.binomialheap.BinomialHeapNode");
        setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(0);
		setConfigKeyIntBithwidth(4);
        setConfigKeyLoopUnroll(4);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(false);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(true);
		setConfigKeyMaxStrykerMethodsPerFile(1);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
        setConfigKeyTypeScopes("icse.binomialheap.set3.BinomialHeapFindMinimum6Bug1Dx2Dx4Dx5Dx6Ix11D:1,icse.binomialheap.BinomialHeapNode:7");
        check(GENERIC_PROPERTIES, "findMinimum_0", true);
    }


}
