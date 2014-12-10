////////////////////////////////////////////////////////////////////////////
// Module : AOIU.java
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
import openjava.ptree.ExpressionStatement;
import openjava.ptree.FieldAccess;
import openjava.ptree.NonLeaf;
import openjava.ptree.ParseTreeException;
import openjava.ptree.ParseTreeObject;
import openjava.ptree.UnaryExpression;
import openjava.ptree.Variable;

/**
 * <p>Generate AOIU (Arithmetic Operator Insertion (Unary)) mutants --
 *    insert a unary operator (arithmetic -) before each variable or 
 *    expression      
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class AOIU extends Arithmetic_OP
{

	private int mutationsLeft = -1;
	ParseTreeObject parent = null;

	boolean aor_flag = false;

   public AOIU(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
      super( file_env, comp_unit );
   }

   /**
    * Set an AOR flag 
    * @param b
    */
   public void setAORflag(boolean b)
   {
      aor_flag = b;
   }

   public void visit( UnaryExpression p ) throws ParseTreeException
   {
     // NO OPERATION
   }

   /**
    * Generate AOIU mutant
    */
   public void visit( Variable p) throws ParseTreeException
   {	   
      if (isArithmeticType(p))
      {
         outputToFile(p);
      }
   }

   /**
    * Generate AOIU mutant
    */
   public void visit( FieldAccess p ) throws ParseTreeException
   {
	   if (isArithmeticType(p))
      {
         outputToFile(p);
      }
   }

   /**
    * Generate AOIU mutant
    */
   public void visit( BinaryExpression p) throws ParseTreeException
   {
      if (aor_flag && isArithmeticType(p)) 
      {
         if ( (p.getOperator() == BinaryExpression.MINUS) || 
        	  (p.getOperator() == BinaryExpression.PLUS)  ||
              (p.getOperator() == BinaryExpression.MOD))
         {
//            Expression e1 = p.getLeft();
//            super.visit(e1);
//            // Ignore right expression because it produce equivalent mutants;
//            Expression e2 = p.getRight();
//            super.visit(e2);
        	 super.visit(p);
         } 
         else if ( (p.getOperator() == BinaryExpression.DIVIDE) || 
        		   (p.getOperator() == BinaryExpression.TIMES) )
         {
            Expression e1 = p.getLeft();
            Expression e2 = p.getRight();
            if ( ((e1 instanceof Variable) || (e1 instanceof FieldAccess)) &&
                 ((e2 instanceof Variable) || (e2 instanceof FieldAccess)) )
            {
               // Consider only one expression because it produces equivalent mutants;
               super.visit(e1);
            }
            else
            {
               super.visit(p);
            }
         }
      }
   }

   /**
    * Generate AOIU mutant
    */
   public void visit( AssignmentExpression p ) throws ParseTreeException
   {
      // [ Example ]
      // int a=0;int b=2;int c=4;
      // Right Expression : a = b = -c;
      // Wrong Expression : a = -b = c;
      // Ignore left expression
	   Expression rexp = p.getRight();
      rexp.accept( this );
   }

   /***
    * Write AOIU mutants to files
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
      //********** date: 6 Dic 2011     **********//
      if (Api.usingApi()) {
		  if (parent==null) return; //do not mutate if no mutation generation limit provided!
    	  MutantsInformationHolder.mainHolder()
    	  	.addMutantIdentifier(Mutant.AOIU, original_field, null, "FieldAccess");
    	  return;
      }
      //*******************************************//

		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}
      
      String f_name;
      num++;
      f_name = getSourceName("AOIU");
      String mutant_dir = getMuantID("AOIU");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 AOIU_Writer writer = new AOIU_Writer(mutant_dir, out);
		 writer.setMutant(original_field);
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

   /**
    * Write AOIU mutants to files
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
      //********** date: 6 Dic 2011     **********//
      if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
    	  MutantsInformationHolder.mainHolder()
    	  	.addMutantIdentifier(Mutant.AOIU, original_var, null, "Variable");
    	  return;
      }
      //*******************************************//

		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

      String f_name;
      num++;
      f_name = getSourceName("AOIU");
      String mutant_dir = getMuantID("AOIU");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 AOIU_Writer writer = new AOIU_Writer(mutant_dir, out);
		 writer.setMutant(original_var);
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
