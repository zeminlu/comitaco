////////////////////////////////////////////////////////////////////////////
// Module : COR.java
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
 * <p>Generate COR (Conditional Operator Replacement mutants --
 *    replace each logical operator by each of the other operators 
 *    (and-&&, or-||, and with no conditional evaluation-&, 
 *    or with no conditional evaluation-|, not equivalent-^)    
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class COR extends MethodLevelMutator
{

	private int mutationsLeft = -1;
	ParseTreeObject parent = null;

	public COR(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
      super( file_env, comp_unit );
   }

   /**
    * If the operator is one of logical operators, replace it with
    * each of the other logical operators 
    */
   public void visit( BinaryExpression p ) throws ParseTreeException 
   {
      Expression left = p.getLeft();
      left.accept(this);
      Expression right = p.getRight();
      right.accept(this);

      if ( (getType(p.getLeft()) == OJSystem.BOOLEAN) && 
     	  (getType(p.getRight()) == OJSystem.BOOLEAN))
      {
         int op_type = p.getOperator();
         if ( (op_type == BinaryExpression.LOGICAL_AND) ||
          	  (op_type == BinaryExpression.LOGICAL_OR) ||
              (op_type == BinaryExpression.BITAND) ||
              (op_type == BinaryExpression.BITOR) ||
              (op_type == BinaryExpression.XOR))
         {
            corMutantGen(p, op_type);
         }
      }
   }

   private void corMutantGen(BinaryExpression exp, int op)
   {
      BinaryExpression mutant;
      if ((op != BinaryExpression.LOGICAL_AND))
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.LOGICAL_AND);
         outputToFile(exp, mutant);
      }
      
      if ((op != BinaryExpression.LOGICAL_OR))
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.LOGICAL_OR);
         outputToFile(exp, mutant);
      }
      
      if (op != BinaryExpression.XOR)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.XOR);
         outputToFile(exp, mutant);
      }
      
      if ((op != BinaryExpression.BITAND))
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.BITAND);
         outputToFile(exp, mutant);
      }
      
      if ((op != BinaryExpression.BITOR))
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.BITOR);
         outputToFile(exp, mutant);
      }
   }

   /**
    * Output COR mutants to files
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
			catch (Exception e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;

      //********** MUJAVA++ MODIFICATION **********//
      //********** date: 7 Feb 2012     **********//
      if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
    	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COR,
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
      f_name = getSourceName("COR");
      String mutant_dir = getMuantID("COR");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 COR_Writer writer = new COR_Writer(mutant_dir, out);
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