////////////////////////////////////////////////////////////////////////////
// Module : LOD.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op.basic;

import mujava.api.Api;
import mujava.api.Mutant;
import mujava.api.MutantsInformationHolder;
import openjava.mop.*;
import openjava.ptree.*;

import java.io.*;

/*
 * MuJava++ NOTICE: THIS COMMENT BELOW IS INCORRECT. 
 * THIS MUTANT ACTUALLY DELETES BITWISE ~ OPERATOR ONLY.
 * 
 */

/**
 * <p>Generate LOD (Logical Operator Deletion) mutants --
 *    delete each occurrence of bitwise logical operators 
 *    (bitwise and-&, bitwise or-|, exclusive or-^)
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class LOD extends MethodLevelMutator
{
	private int mutationsLeft = -1;
	ParseTreeObject parent = null;

   public LOD(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
      super( file_env, comp_unit );
   }

   public void visit( UnaryExpression p ) throws ParseTreeException
   {
      int op = p.getOperator();
      if ( op == UnaryExpression.BIT_NOT)
      {
         outputToFile(p);
      }
   }

   /**
    * Output LOD mutants to files
    * @param original
    */
   public void outputToFile(UnaryExpression original)
   {
      if (comp_unit == null) 
    	 return;

		parent = original.getMutationLimitParent();
		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
			try {
				String mutations = ((NonLeaf) parent).getComment().substring(14);
				mutationsLeft = Integer.parseInt(mutations);
			}
			catch (NumberFormatException e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;

      
      //********** MUJAVA++ MODIFICATION **********//
      //********** date: 7 Feb 2012     **********//
      if (Api.usingApi()) {
    		if (parent==null) return; //do not mutate if no mutation generation limit provided!
    	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.LOD,
    			  original);
    	  return;
      }
      //*******************************************//

		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

      String f_name;
      num++;
      f_name = getSourceName("LOD");
      String mutant_dir = getMuantID("LOD");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 LOD_Writer writer = new LOD_Writer(mutant_dir,out);
		 writer.setMutant(original);
         writer.setMethodSignature(currentMethodSignature);
		 comp_unit.accept( writer );
		 out.flush();  out.close();
      } catch ( IOException e ) {
		 System.err.println( "fails to create " + f_name );
      } catch ( ParseTreeException e ) {
		 System.err.println( "errors during printing " + f_name );
		 e.printStackTrace();
      }
      
		if (mutationsLeft>=0) {
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

   }
}
