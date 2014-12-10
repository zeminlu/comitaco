////////////////////////////////////////////////////////////////////////////
// Module : PRV_Writer.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op;

import java.io.*;
import openjava.ptree.*;
import mujava.op.util.MutantCodeWriter;

/**
 * <p>Output and log PRV mutants to files</p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class PRV_Writer extends MutantCodeWriter
{
   AssignmentExpression original = null;
   String mutant = null;
   boolean leftPRV = false;
   BinaryExpression original_binary = null;
   //VariableDeclaration original_variable = null;
   LabeledStatement original_labeled = null;
   VariableDeclarator original_var_decl = null;
   ReturnStatement original_return = null;

  /** 
   * Set original source code and mutated code
   * @param original
   * @param mutant
   */
   public void setMutant(AssignmentExpression original, String mutant)
   {
      this.original = original;
      this.mutant = mutant;
   }

   public void setMutant(ReturnStatement original, String mutant)
   {
      this.original_return = original;
      this.mutant = mutant;
   }

   
   /**
    * setMutant defined for LabeledStatement. So far, labels must only be used in
    * assignments, to indicate the number of times PRV has mutated that specific statement.
    * In order to "write" a labeled statement, the label is output, and then the corresponding
    * assignment is output.
    * @param original
    * @param mutant
    */
   public void setMutant(LabeledStatement original, String mutant)
   {
      this.original_labeled = original;
      this.original = (AssignmentExpression) ((ExpressionStatement) original.getStatement()).getExpression();
      this.mutant = mutant;
   }

   
   public void setLeftPRV(){
	   this.leftPRV = true;
   }
   
   public void setMutant(BinaryExpression original, String mutant){
	   this.original_binary = original;
	   this.mutant = mutant;
   }

//   public void setMutant(VariableDeclaration original, String mutant){
//	   this.original_variable = original;
//	   this.mutant = mutant;
//   }

   public void setMutant(VariableDeclarator original, String mutant){
	   this.original_var_decl = original;
	   this.mutant = mutant;
   }
  
   public PRV_Writer( String file_name, PrintWriter out ) 
   {
      super(file_name, out);
   }
   
//   public void visit(VariableDeclaration p) throws ParseTreeException {
//	   
//	   if( isSameObject(p, original_variable) ){
//		   
//		   
//		   writeTab();
//
//	        ModifierList modifs = p.getModifiers();
//	        modifs.accept( this );
//	        if (! modifs.isEmptyAsRegular())  out.print( " " );
//
//	        TypeName typespec = p.getTypeSpecifier();
//	        typespec.accept( this );
//	        out.print(" ");
//	        VariableDeclarator decl = p.getVariableDeclarator();
//	        if (decl != null) decl.accept( this );
//	        
//	        out.print( " " );
//
//	        out.print(" " + p.getVariable());
//	        out.print(" = ");
//	        out.print(mutant);
//	        
//	   } else {
//		   super.visit(p);
//	   }
//   }

   public void visit(VariableDeclarator p) throws ParseTreeException {
	   
	   if( isSameObject(p, original_var_decl) ){
		   
	        out.print(p.getVariable());
	        out.print(" = ");
	        out.print(mutant);
	        
	   } else {
		   super.visit(p);
	   }
   }   

   public void visit(ReturnStatement p) throws ParseTreeException {
	   
	   if( isSameObject(p, original_return) ){
		   writeTab();
		   out.print("return ");
		   out.print(mutant);
		   out.print(";");
	       try {
	        	String mutations = p.getComment().substring(14);
	        	int numberOfMutations = Integer.parseInt(mutations);
	            out.print(" //mutGenLimit " + numberOfMutations);
	            out.println();
	        }
	        catch (Exception e) {
	        	// comment is not a mutation generation limit. Do not output it.
	        }

	   } else {
		   super.visit(p);
	   }
   }   

   
   public void visit(BinaryExpression p) throws ParseTreeException{
	   if( isSameObject(p, original_binary) && leftPRV ){
		   out.write(mutant);
		   out.write(" " + p.operatorString() + " ");
		   out.write(p.getRight().toString());
	   } else if( isSameObject(p, original_binary) && !leftPRV ){
		   out.write(p.getLeft().toString());
		   out.write(" " + p.operatorString() + " ");
		   out.write(mutant);
	   } else {
		   super.visit(p);
	   }
   }

   public void visit( AssignmentExpression p ) throws ParseTreeException
   {
      if (isSameObject(p, original) && !leftPRV )
      {
         Expression lexpr = p.getLeft();

         if (lexpr instanceof AssignmentExpression) 
         {
		 	writeParenthesis( lexpr );
         } 
         else 
         {
		    lexpr.accept( this );
         }

		 String operator = p.operatorString();
         out.print( " " + operator + " " );

         // -------------------------------------------------------------
	     mutated_line = line_num;
		 out.print(mutant);
		 writeLog(removeNewline(original.toString()+";  =>  "+ lexpr.toString() + " = " + mutant+";"));
		 // -------------------------------------------------------------

      } else if (isSameObject(p, original) && leftPRV ){
    	  
    	  mutated_line = line_num;
    	  
    	  out.print(mutant);
    	  
    	  out.print(" " + p.operatorString() + " ");
    	  
    	  Expression rexpr = p.getRight();
    	  
    	  if( rexpr instanceof AssignmentExpression ){
    		  writeParenthesis(rexpr);
    	  } else {
    		  rexpr.accept(this);
    	  }
    	  
    	  writeLog(removeNewline(original.toString()+";  =>  "+ mutant + " = " + p.getRight()+";"));
      } else {
         Expression lexpr = p.getLeft();

         if (lexpr instanceof AssignmentExpression) 
         {
	        writeParenthesis( lexpr );
         } 
         else 
         {
	        lexpr.accept( this );
         }

	     String operator = p.operatorString();
         out.print( " " + operator + " " );

         Expression rexp = p.getRight();
         rexp.accept( this );
         
      }
   }
   
}
