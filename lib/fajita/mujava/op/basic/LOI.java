////////////////////////////////////////////////////////////////////////////
// Module : LOI.java
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
 * THIS MUTANT ACTUALLY INSERTS BITWISE ~ OPERATOR ONLY.
 * 
 */

/**
 * <p>Generate LOI (Logical Operator Insertion) mutants --
 *    insert bitwise logical operators (bitwise and-&, bitwise or-|,
 *    exclusive or-^)
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class LOI extends Arithmetic_OP
{
	
	private int mutationsLeft = -1;
	ParseTreeObject parent = null;
	
   public LOI(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
      super( file_env, comp_unit );
   }

   public void visit( Variable p) throws ParseTreeException
   {
      if (isArithmeticType(p))
      {
         outputToFile(p);
      }
   }

   public void visit( FieldAccess p ) throws ParseTreeException
   {
      if (isArithmeticType(p))
      {
         outputToFile(p);
      }
   }

   public void visit( AssignmentExpression p ) throws ParseTreeException
   {
      Expression lexpr = p.getLeft();

      if ((lexpr instanceof Variable) || (lexpr instanceof FieldAccess))
      {
    	 // do nothing
      }
      else
      {
	     lexpr.accept( this );
      }
      
      Expression rexp = p.getRight();
      rexp.accept( this );
   }

   /**
    * Output LOI mutants to files
    * @param original_field
    */
   public void outputToFile(FieldAccess original_field)
   {
      if (comp_unit == null) 
    	 return;
      
		parent = original_field.getMutationLimitParent();
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
    	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.LOI,
    			  original_field, null, "Field Access");
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
      f_name = getSourceName("LOI");
      String mutant_dir = getMuantID("LOI");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 LOI_Writer writer = new LOI_Writer(mutant_dir, out);
		 writer.setMutant(original_field);
         writer.setMethodSignature(currentMethodSignature);
		 comp_unit.accept( writer );
		 out.flush();  
		 out.close();
      } catch ( IOException e ) {
		 System.err.println( "fails to create " + f_name );
		 e.printStackTrace();
      } catch ( ParseTreeException e ) {
		 System.err.println( "errors during printing " + f_name );
		 e.printStackTrace();
      }
      
		if (mutationsLeft>=0) {
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

   }

   /**
    * Output LOI mutants to files
    * @param original_var
    */
   public void outputToFile(Variable original_var)
   {
      if (comp_unit == null) 
    	 return;

		parent = original_var.getMutationLimitParent();
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
    	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.LOI,
    			  original_var, null, "Variable");
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
      f_name = getSourceName("LOI");
      String mutant_dir = getMuantID("LOI");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 LOI_Writer writer = new LOI_Writer(mutant_dir, out);
		 writer.setMutant(original_var);
         writer.setMethodSignature(currentMethodSignature);
		 comp_unit.accept( writer );
		 out.flush();  
		 out.close();
      } catch ( IOException e ) {
		 System.err.println( "fails to create " + f_name );
		 e.printStackTrace();
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
