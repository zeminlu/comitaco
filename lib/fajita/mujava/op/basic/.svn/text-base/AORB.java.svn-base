////////////////////////////////////////////////////////////////////////////
// Module : AORB.java
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
 * <p>Generate AORB (Arithmetic Operator Replacement (Binary)) mutants -- 
 *    replace an arithmetic operator by each of the other operators  
 *    (*, /, %, +, -)
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class AORB extends Arithmetic_OP
{

	private int mutationsLeft = -1;
	ParseTreeObject parent = null;
	
   public AORB(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
      super( file_env, comp_unit );
   }
 
   /**
    * Mutate the arithmetic operator to TIMES, DIVIDE,
    * MOD, PLUS, MINUS (excluding itself) 
    */
   public void visit( BinaryExpression p ) throws ParseTreeException 
   {

      Expression left = p.getLeft();
      left.accept(this);

      Expression right = p.getRight();
      right.accept(this);

      if (isArithmeticType(p))
      {
         int op_type = p.getOperator();
         switch (op_type)
         {
            // for AOR mutation operator
            // 5 Arithmetic Operators : TIMES, DIVIDE, MOD, PLUS, MINUS
            case BinaryExpression.TIMES :
                          aorMutantGen(p, BinaryExpression.TIMES);
                          break;

            case BinaryExpression.DIVIDE :
                          aorMutantGen(p, BinaryExpression.DIVIDE);
                          break;

            case BinaryExpression.MOD :
                          aorMutantGen(p, BinaryExpression.MOD);
                          break;

            case BinaryExpression.PLUS :
                          aorMutantGen(p, BinaryExpression.PLUS);
                          break;

            case BinaryExpression.MINUS :
                          aorMutantGen(p, BinaryExpression.MINUS);
                          break;
         }
      }
   }

   private void aorMutantGen(BinaryExpression exp, int op)
   {
      BinaryExpression mutant;
      if (op != BinaryExpression.TIMES)
      {
         mutant = (BinaryExpression)exp.makeRecursiveCopy();
         mutant.setOperator(BinaryExpression.TIMES);
         aor_outputToFile(exp, mutant);
      }
      if (op != BinaryExpression.DIVIDE)
      {
         mutant = (BinaryExpression)exp.makeRecursiveCopy();
         mutant.setOperator(BinaryExpression.DIVIDE);
         aor_outputToFile(exp, mutant);
      }
      if(op != BinaryExpression.MOD)
      {
         mutant = (BinaryExpression)exp.makeRecursiveCopy();
         mutant.setOperator(BinaryExpression.MOD);
         aor_outputToFile(exp, mutant);
      }
      if (op != BinaryExpression.PLUS)
      {
         mutant = (BinaryExpression)exp.makeRecursiveCopy();
         mutant.setOperator(BinaryExpression.PLUS);
         aor_outputToFile(exp, mutant);
      }
      if (op != BinaryExpression.MINUS)
      {
         mutant = (BinaryExpression)exp.makeRecursiveCopy();
         mutant.setOperator(BinaryExpression.MINUS);
         aor_outputToFile(exp, mutant);
      }
   }

   /**
    * Output AORB mutants to file
    * @param original
    * @param mutant
    */
   public void aor_outputToFile(BinaryExpression original, BinaryExpression mutant)
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
    	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.AORB,
    			  original, mutant);
    	  //TODO: check if this is correct
    	  return;
      }
      //*******************************************//

		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

      String f_name;
      num++;
      f_name = getSourceName("AORB");
      String mutant_dir = getMuantID("AORB");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 AORB_Writer writer = new AORB_Writer(mutant_dir, out);
		 writer.setMutant(original, mutant);
         writer.setMethodSignature(currentMethodSignature);
		 comp_unit.accept( writer );
		 out.flush();  out.close();
      } catch ( IOException e ) 
      {
		 System.err.println( "fails to create " + f_name );
      } catch ( ParseTreeException e ) 
      {
		 System.err.println( "errors during printing " + f_name );
		 e.printStackTrace();
      }
      
		if (mutationsLeft>=0) {
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

   }
   

}
