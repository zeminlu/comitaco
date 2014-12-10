////////////////////////////////////////////////////////////////////////////
// Module : TestResult.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.test;

import java.util.Vector;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class TestResult
{
  public Vector killed_mutants = new Vector();
  public Vector live_mutants = new Vector();
  public int mutant_score=0;
}
