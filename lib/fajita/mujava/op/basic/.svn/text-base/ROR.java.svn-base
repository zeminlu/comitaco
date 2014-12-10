////////////////////////////////////////////////////////////////////////////
// Module : ROR.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op.basic;

import java.io.IOException;
import java.io.PrintWriter;

import mujava.api.Api;
import mujava.api.Mutant;
import mujava.api.MutantsInformationHolder;
import openjava.mop.FileEnvironment;
import openjava.ptree.BinaryExpression;
import openjava.ptree.ClassDeclaration;
import openjava.ptree.CompilationUnit;
import openjava.ptree.Expression;
import openjava.ptree.NonLeaf;
import openjava.ptree.ParseTreeException;
import openjava.ptree.ParseTreeObject;

/**
 * <p>Generate ROR (Rational Operator Replacement) mutants --
 *    replace each occurrence of one of the relational operators 
 *    (<, <=, >, >=, =, <>) by each of the other operators 
 *    and by <i>falseOp</i> and <i>trueOp</i> where 
 *    <i>falseOp</i> always returns <i>false</i> and 
 *    <i>trueOp</i> always returns <i>true</i> 
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class ROR extends Arithmetic_OP
{

	private int mutationsLeft = -1;
	ParseTreeObject parent = null;

   public ROR(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
      super( file_env, comp_unit );
   }

   public void visit( BinaryExpression p ) throws ParseTreeException 
   {
      Expression left = p.getLeft(); 
      left.accept(this);
      Expression right = p.getRight();
      right.accept(this);

      int op_type = p.getOperator();
      if (isArithmeticType(p.getLeft()) && isArithmeticType(p.getRight()))
      {
         if ((op_type == BinaryExpression.GREATER) || (op_type == BinaryExpression.GREATEREQUAL) ||
        		 //********** MUJAVA++ MODIFICATION **********//
        		 //********** date: 6 Dic 2011     **********//
        		 (op_type == BinaryExpression.LESS) ||
        		 //*******************************************//
        		 (op_type == BinaryExpression.LESSEQUAL) || (op_type == BinaryExpression.EQUAL) ||
             (op_type == BinaryExpression.NOTEQUAL) )
         {
            primitiveRORMutantGen(p, op_type);
         }
      }
      else if ( (op_type == BinaryExpression.EQUAL) || (op_type == BinaryExpression.NOTEQUAL) )
      {
         objectRORMutantGen(p, op_type);
      }
   }

   private void primitiveRORMutantGen(BinaryExpression exp, int op)
   {
      BinaryExpression mutant;
      if (op != BinaryExpression.GREATER)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.GREATER);
         outputToFile(exp, mutant);
      }
      
      if (op != BinaryExpression.GREATEREQUAL)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.GREATEREQUAL);
         outputToFile(exp, mutant);
      }
     
      if (op != BinaryExpression.LESS)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.LESS);
         outputToFile(exp, mutant);
      }
      
      if (op != BinaryExpression.LESSEQUAL)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.LESSEQUAL);
         outputToFile(exp, mutant);
      }
 
      if (op != BinaryExpression.EQUAL)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.EQUAL);
         outputToFile(exp, mutant);
      }
       
      if (op != BinaryExpression.NOTEQUAL)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.NOTEQUAL);
         outputToFile(exp, mutant);
      }
   }

   private void objectRORMutantGen(BinaryExpression exp, int op)
   {
      BinaryExpression mutant;
      if (op != BinaryExpression.EQUAL)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.EQUAL);
         outputToFile(exp, mutant);
      }
      
      if (op != BinaryExpression.NOTEQUAL)
      {
         mutant = (BinaryExpression)(exp.makeRecursiveCopy());
         mutant.setOperator(BinaryExpression.NOTEQUAL);
         outputToFile(exp, mutant);
      }
   }

   /**
    * Output ROR mutants to files
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
      //********** date: 6 Dic 2011     **********//
      if (Api.usingApi()) {
  		if (parent==null) return; //do not mutate if no mutation generation limit provided!
    	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.ROR,
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
      f_name = getSourceName("ROR");
      String mutant_dir = getMuantID("ROR");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 ROR_Writer writer = new ROR_Writer(mutant_dir, out);
		 writer.setMutant(original, mutant);
         writer.setMethodSignature(currentMethodSignature);
		 comp_unit.accept( writer );
		 out.flush();  
		 out.close();
      } catch ( IOException e ) 
      {
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
