////////////////////////////////////////////////////////////////////////////
// Module : SOR.java
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

/**
 * <p>Generate SOR (Shift Operator Replacement) mutants --
 *    replace each occurrence of one of the shift operators <<, >>, and >>>
 *    by each of the other operators
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class SOR extends MethodLevelMutator
{
	
	private int mutationsLeft = -1;
	ParseTreeObject parent = null;

   public SOR(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
      super( file_env, comp_unit );
   }

   public void visit( BinaryExpression p ) throws ParseTreeException 
   {
      Expression left = p.getLeft();
      left.accept( this );

      int op_type = p.getOperator();
      if ( (op_type == BinaryExpression.SHIFT_L) || (op_type == BinaryExpression.SHIFT_R)
         ||(op_type == BinaryExpression.SHIFT_RR) )
      {
         sorMutantGen(p, op_type);
      }

      Expression right = p.getRight();
      right.accept( this );
   }

   private void sorMutantGen(BinaryExpression exp, int op)
   {
      BinaryExpression mutant;

      if (op != BinaryExpression.SHIFT_L)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.SHIFT_L);
         outputToFile(exp, mutant);
      }
      
      if (op != BinaryExpression.SHIFT_R)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.SHIFT_R);
         outputToFile(exp, mutant);
      }
      
      if (op != BinaryExpression.SHIFT_RR)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.SHIFT_RR);
         outputToFile(exp, mutant);
      }
   }

   /**
    * Output SOR mutants to files
    * @param original
    * @param mutant
    */
   public void outputToFile(BinaryExpression original, BinaryExpression mutant)
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
    	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.SOR,
    			  original, mutant);
    	  return;
      }
      //*******************************************//

		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

      String f_name;
      num++;
      f_name = getSourceName("SOR");
      String mutant_dir = getMuantID("SOR");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 SOR_Writer writer = new SOR_Writer(mutant_dir, out);
		 writer.setMutant(original, mutant);
         writer.setMethodSignature(currentMethodSignature);
		 comp_unit.accept( writer );
		 out.flush();  
		 out.close();
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
