////////////////////////////////////////////////////////////////////////////
// Module : COI_Writer.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op.basic;

import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicBoolean;

import mujava.op.util.TraditionalMutantCodeWriter;
import openjava.ptree.AssignmentExpression;
import openjava.ptree.BinaryExpression;
import openjava.ptree.DoWhileStatement;
import openjava.ptree.FieldAccess;
import openjava.ptree.ForStatement;
import openjava.ptree.IfStatement;
import openjava.ptree.Literal;
import openjava.ptree.ParseTreeException;
import openjava.ptree.UnaryExpression;
import openjava.ptree.Variable;
import openjava.ptree.VariableDeclaration;
import openjava.ptree.WhileStatement;

/**
 * <p>Output and log COI mutants to files </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class COI_Writer extends TraditionalMutantCodeWriter
{
	
   BinaryExpression original_binary;
   AssignmentExpression original_assignment;
   VariableDeclaration original_variable_decl;
   UnaryExpression original_unary;
   
   DoWhileStatement original_doWhile;
   WhileStatement original_While;
   ForStatement original_for;
   IfStatement original_if;
   
   Variable original_var;
   FieldAccess original_field;
   Literal original_literal;
   boolean mutateLeft = false;
   boolean mutateRight = false;
   
   public static AtomicBoolean negateLiteral = new AtomicBoolean(false);
   
   public COI_Writer( String file_name, PrintWriter out ) 
   {
      super(file_name, out);
   }
   
	public void setMutant(DoWhileStatement originalDoWhile) {
		original_doWhile = originalDoWhile;
	}

	public void setMutant(Literal originalLiteral) {
		original_literal = originalLiteral;
	}

	
	public void setMutant(WhileStatement originalWhile) {
		original_While = originalWhile;
	}
	
	public void setMutant(ForStatement originalFor) {
		original_for = originalFor;
	}
	
	public void setMutant(IfStatement originalIf) {
		original_if = originalIf;
	}

	public void mutateLeft(){
	   this.mutateLeft = true;
	}
	
	public void mutateRight(){
		   this.mutateRight = true;
		}
   
   /**
    * Set original source code
    * @param p
    */
   public void setMutant(BinaryExpression p)
   {
      original_binary = p;
   }

   /**
    * Set original source code
    * @param p
    */
   public void setMutant(UnaryExpression p)
   {
      original_unary = p;
   }
   
   /**
    * Set original source code
    * @param p
    */
   public void setMutant(AssignmentExpression p)
   {
      original_assignment = p;
   }

   /**
    * Set original source code
    * @param p
    */
   public void setMutant(VariableDeclaration p)
   {
      original_variable_decl = p;
   }
   
   /**
    * Set original source code
    * @param p
    */
   public void setMutant(Variable p)
   {
      original_var = p;
   }

   /**
    * Set original source code
    * @param p
    */
   public void setMutant(FieldAccess p)
   {
      original_field = p;
   }

   /**
    * Log mutated line
    */
   public void visit( BinaryExpression p ) throws ParseTreeException
   {
      if (isSameObject(p, original_binary))
      {
    	  String log_str = null;
         if( mutateRight ){
        	 out.print(p.getLeft() + " " + p.operatorString() + " ");
        	 out.print("!" + p.getRight());
        	 log_str = p.toFlattenString()+ "  =>  " + p.getLeft() + " " + p.operatorString() + " !" + p.getRight();
        	 
         } else if( mutateLeft) {
        	 out.print("!" + p.getLeft() + " " + p.operatorString() + " " + p.getRight());
        	 log_str = p.toFlattenString()+ "  =>  !" + p.getLeft() + " " + p.operatorString() + " " + p.getRight();
         } else {
        	 out.print("!("+p.toString()+")");
        	 log_str = p.toFlattenString()+ "  =>  " +"!("+p.toString()+")";
         }
         // -----------------------------------------------------------
         mutated_line = line_num;
         
         writeLog(removeNewline(log_str));
         // -------------------------------------------------------------
      } 
      else
      {
         super.visit(p);
      }
   }
   
   /**
    * Log mutated line
    */
   public void visit( UnaryExpression p ) throws ParseTreeException
   {
      if (isSameObject(p, original_unary))
      {
    	  String log_str = null;
    	 out.print("!("+p.toString()+")");
    	 log_str = p.toFlattenString()+ "  =>  " +"!("+p.toString()+")";
         // -----------------------------------------------------------
         mutated_line = line_num;
         
         writeLog(removeNewline(log_str));
         // -------------------------------------------------------------
      } 
      else
      {
         super.visit(p);
      }
   }
   
   /**
    * Log mutated line
    */
   public void visit( AssignmentExpression p ) throws ParseTreeException
   {
      if (isSameObject(p, original_assignment))
      {
    	  String log_str = null;
    	 out.print(p.getLeft() + " = ");
    	 out.print("!" + p.getRight());
    	 log_str = p.toFlattenString()+ "  =>  " + p.getLeft() + " = !" + p.getRight();
         // -----------------------------------------------------------
         mutated_line = line_num;
         
         writeLog(removeNewline(log_str));
         // -------------------------------------------------------------
      } 
      else
      {
         super.visit(p);
      }
   }
   
   /**
    * Log mutated line
    */
   public void visit( VariableDeclaration p ) throws ParseTreeException
   {
      if (isSameObject(p, original_variable_decl))
      {
    	  out.print(p.getModifiers().toFlattenString());
		   out.print(" " + p.getTypeSpecifier().toFlattenString());
		   out.print(" " + p.getVariable());
		   out.print(" = ");
		   out.print("!" + p.getInitializer());
		   out.print(";\n");
         mutated_line = line_num;
         
      } 
      else
      {
         super.visit(p);
      }
   }
  
   
   public void visit(IfStatement p ) throws ParseTreeException{
	   if( isSameObject(p, original_if)){
		   negateLiteral.set(true);
	   }
	   super.visit(p);
	   negateLiteral.set(false);
   }
   
   public void visit(WhileStatement p ) throws ParseTreeException{
	   if( isSameObject(p, original_While)){
		   negateLiteral.set(true);
	   }
	   super.visit(p);
	   negateLiteral.set(false);
   }
   
   public void visit(DoWhileStatement p ) throws ParseTreeException{
	   if( isSameObject(p, original_doWhile)){
		   negateLiteral.set(true);
	   }
	   super.visit(p);
	   negateLiteral.set(false);
   }
   
   public void visit(ForStatement p ) throws ParseTreeException{
	   if( isSameObject(p, original_for)){
		   negateLiteral.set(true);
	   }
	   super.visit(p);
	   negateLiteral.set(false);
   }
   
   /**
    * Log mutated line
    */
   public void visit( Variable p ) throws ParseTreeException
   {
      if (isSameObject(p, original_var))
      {
         out.print("!"+p.toString());
         // -----------------------------------------------------------
         mutated_line = line_num;
         String log_str = p.toFlattenString()+ "  =>  " +"!"+p.toString();
         writeLog(removeNewline(log_str));
         // -------------------------------------------------------------
      }
      else
      {
         super.visit(p);
      }
   }

   
   /**
    * Log mutated line
    */
   public void visit( Literal p ) throws ParseTreeException
   {
      if (isSameObject(p, original_literal))
      {
         out.print("!"+p.toString());
         // -----------------------------------------------------------
         mutated_line = line_num;
         String log_str = p.toFlattenString()+ "  =>  " +"!"+p.toString();
         writeLog(removeNewline(log_str));
         // -------------------------------------------------------------
      }
      else
      {
         super.visit(p);
      }
   }   
   
   
   /**
    * Log mutated line
    */
   public void visit( FieldAccess p ) throws ParseTreeException
   {
      if (isSameObject(p, original_field))
      {
         out.print("!"+p.toString());
         // -----------------------------------------------------------
         mutated_line = line_num;
         String log_str = p.toFlattenString()+ "  =>  " +"!"+p.toString();
         writeLog(removeNewline(log_str));
         // -------------------------------------------------------------
      }
      else
      {
         super.visit(p);
      }
   }
   
}
