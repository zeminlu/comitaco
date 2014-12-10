////////////////////////////////////////////////////////////////////////////
// Module : AORS.java
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
import openjava.ptree.AssignmentExpression;
import openjava.ptree.BinaryExpression;
import openjava.ptree.ClassDeclaration;
import openjava.ptree.CompilationUnit;
import openjava.ptree.Expression;
import openjava.ptree.NonLeaf;
import openjava.ptree.ParseTreeException;
import openjava.ptree.ParseTreeObject;
import openjava.ptree.UnaryExpression;

/**
 * <p>Generate AORS (Arithmetic Operator Replacement (Short-cut)) mutants --
 *    replace each occurrence of the increment and decrement operators 
 *    by each of the other operators
 * </p>
 * <p><i>Example</i>:
 *       for (int i=0; i<length; i++) is mutated to for (int i=0; i<length; i--)
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class AORS extends MethodLevelMutator
{

	private int mutationsLeft = -1;
	ParseTreeObject parent = null;

	boolean isPrePostEQ = true;

   public AORS(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
      super( file_env, comp_unit );
   }

   public void visit( UnaryExpression p ) throws ParseTreeException
   {
      int op = p.getOperator();
      if ( (op == UnaryExpression.POST_DECREMENT) || (op == UnaryExpression.POST_INCREMENT) ||
           (op == UnaryExpression.PRE_DECREMENT) || (op == UnaryExpression.PRE_INCREMENT) )
      {
         genShortCutUnaryMutants(p, op);
      }
   }

   public void visit( BinaryExpression p ) throws ParseTreeException 
   {
      isPrePostEQ = false;
      super.visit(p);
      isPrePostEQ = true;
   }

   public void visit(AssignmentExpression p) throws ParseTreeException
   {
      //Expression lexpr = p.getLeft();
      // lexpr.accept( this );

      isPrePostEQ =  false;
      Expression rexp = p.getRight();
      rexp.accept( this );
      isPrePostEQ =  true;
   }

   void genShortCutUnaryMutants(UnaryExpression p, int op)
   {
      UnaryExpression mutant;
      if (isPrePostEQ)
      {  // Inside FOR stmts
         if ( (!(op == UnaryExpression.PRE_INCREMENT)) && (!(op == UnaryExpression.POST_INCREMENT)))
         {
            mutant = (UnaryExpression)(p.makeRecursiveCopy());
            mutant.setOperator(UnaryExpression.POST_INCREMENT);
            outputToFile(p, mutant);
         }
        
         if ( (!(op == UnaryExpression.PRE_DECREMENT)) && (!(op == UnaryExpression.POST_DECREMENT)))
         {
            mutant = (UnaryExpression)(p.makeRecursiveCopy());
            mutant.setOperator(UnaryExpression.POST_DECREMENT);
            outputToFile(p,mutant);
         }
      }  
      else
      {
         if (!(op == UnaryExpression.POST_DECREMENT))
         {
            mutant = (UnaryExpression)(p.makeRecursiveCopy());
            mutant.setOperator(UnaryExpression.POST_DECREMENT);
            outputToFile(p, mutant);
         }
         
         if (!(op == UnaryExpression.POST_INCREMENT))
         {
            mutant = (UnaryExpression)(p.makeRecursiveCopy());
            mutant.setOperator(UnaryExpression.POST_INCREMENT);
            outputToFile(p, mutant);
         }
         
         if (!(op == UnaryExpression.PRE_DECREMENT))
         {
            mutant = (UnaryExpression)(p.makeRecursiveCopy());
            mutant.setOperator(UnaryExpression.PRE_DECREMENT);
            outputToFile(p, mutant);
         }
      
         if (!(op == UnaryExpression.PRE_INCREMENT))
         {
            mutant = (UnaryExpression)(p.makeRecursiveCopy());
            mutant.setOperator(UnaryExpression.PRE_INCREMENT);
            outputToFile(p, mutant);
         }
      }
   }

   /**
    * Output AORS mutants to files
    * @param original
    * @param mutant
    */
   public void outputToFile(UnaryExpression original, UnaryExpression mutant)
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
    	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.AORS,
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
      f_name = getSourceName("AORS");
      String mutant_dir = getMuantID("AORS");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 AORS_Writer writer = new AORS_Writer(mutant_dir, out);
		 writer.setMutant(original, mutant);
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
