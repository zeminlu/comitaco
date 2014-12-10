////////////////////////////////////////////////////////////////////////////
// Module : AORU.java
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

// not use ??? (11/15/2009) 

/**
 * <p>Generate AORU (Arithmetic Operator Replacement (Unary)) mutants --
 *    replace each occurrence of one of the arithmetic operators + and - 
 *    by each of the other operators 
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class AORU extends MethodLevelMutator
{
	
	private int mutationsLeft = -1;
	ParseTreeObject parent = null;

   public AORU(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
      super( file_env, comp_unit );
   }

   /**
    * If a given unary expression contains an arithmetic operator + or -,
    * generate an AORU mutant
    */
   public void visit( UnaryExpression p ) throws ParseTreeException
   {
      int op = p.getOperator();
      if ( (op == UnaryExpression.MINUS) || (op == UnaryExpression.PLUS) ) 
      {
         genBasicUnaryMutants(p,op);
      }
   }

   void genBasicUnaryMutants(UnaryExpression p, int op)
   {
      UnaryExpression mutant;
      if ( op == UnaryExpression.PLUS )
      {
         mutant = (UnaryExpression)(p.makeRecursiveCopy());
         mutant.setOperator(UnaryExpression.MINUS);
         outputToFile(p, mutant);
      }
      else if ( op == UnaryExpression.MINUS )
      {
         mutant = (UnaryExpression)(p.makeRecursiveCopy());
         mutant.setOperator(UnaryExpression.PLUS);
         outputToFile(p, mutant);
      }
   }

   /**
    * Output AORU mutants to files
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
    	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.AORU,
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
      f_name = getSourceName("AORU");
      String mutant_dir = getMuantID("AORU");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 AORU_Writer writer = new AORU_Writer(mutant_dir, out);
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
