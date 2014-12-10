////////////////////////////////////////////////////////////////////////////
// Module : COI.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op.basic;

import mujava.api.Mutant;
import mujava.api.MutantsInformationHolder;
import openjava.mop.FileEnvironment;
import openjava.mop.OJClass;
import openjava.mop.OJClassNotFoundException;
import openjava.mop.OJSystem;
import openjava.ptree.AssignmentExpression;
import openjava.ptree.BinaryExpression;
import openjava.ptree.ClassDeclaration;
import openjava.ptree.CompilationUnit;
import openjava.ptree.DoWhileStatement;
import openjava.ptree.Expression;
import openjava.ptree.FieldAccess;
import openjava.ptree.ForStatement;
import openjava.ptree.IfStatement;
import openjava.ptree.Literal;
import openjava.ptree.NonLeaf;
import openjava.ptree.ParseTreeException;
import openjava.ptree.ParseTreeObject;
import openjava.ptree.UnaryExpression;
import openjava.ptree.Variable;
import openjava.ptree.VariableDeclaration;
import openjava.ptree.WhileStatement;

/**
 * <p>Generate COI (Conditional Operator Insertion) mutants --
 *    insert logical operators (and-&&, or-||, 
 *    and with no conditional evaluation-&, 
 *    or with no conditional evaluation-|, not equivalent-^)
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class COI extends MethodLevelMutator
{

	private int mutationsLeft = -1;
	ParseTreeObject parent = null;

   public COI(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
      super( file_env, comp_unit );
   }

   public void visit(UnaryExpression p) throws ParseTreeException
   {
	   if (getType(p) == OJSystem.BOOLEAN)
	   {
		   outputToFile(p);
	   }
   }
   
   
   public void visit( Variable p ) throws ParseTreeException
   {
      if (getType(p) == OJSystem.BOOLEAN)
      {
    	  Class<? extends ParseTreeObject> pclazz = p.getParent().getClass();
    	  if( pclazz.equals(AssignmentExpression.class) ){
    		  AssignmentExpression pp = (AssignmentExpression)p.getParent();
    		  if(pp.getLeft().equals(p)){
    			  return;
    		  }
    	  } 
    	  
         outputToFile(p);
      }
   }

   public void visit(Literal p) throws ParseTreeException
   {
      if (getType(p) == OJSystem.BOOLEAN)
      {
    	  Class<? extends ParseTreeObject> pclazz = p.getParent().getClass();
    	  if( pclazz.equals(AssignmentExpression.class) ){
    		  AssignmentExpression pp = (AssignmentExpression)p.getParent();
    		  if(pp.getLeft().equals(p)){
    			  return;
    		  }
    	  } 
    	  
         outputToFile(p);
      }
   }
   
   public void visit( FieldAccess p ) throws ParseTreeException
   {
      if (getType(p) == OJSystem.BOOLEAN)
      {
         outputToFile(p);
      }
   }

   public void visit( BinaryExpression p ) throws ParseTreeException
   {
      Expression left = p.getLeft();
      left.accept(this);
      Expression right = p.getRight();
      right.accept(this);

      if (getType(p) == OJSystem.BOOLEAN)
      {
         outputToFile(p);
         if( right.getClass().equals(Literal.class) && ((Literal)right).getLiteralType() == Literal.BOOLEAN){
        	 outputToFileRight(p);
         }
         if( left.getClass().equals(Literal.class) && ((Literal)left).getLiteralType() == Literal.BOOLEAN){
        	 outputToFileLeft(p);
         }
      }
   }
   
   public void visit( AssignmentExpression p ) throws ParseTreeException{
	   Expression left = p.getLeft();
	   left.accept(this);
	   Expression right = p.getRight();
	   right.accept(this);
	   
	   if( getType(p) == OJSystem.BOOLEAN && right.getClass().equals(Literal.class)
			   && ((Literal)right).getLiteralType() == Literal.BOOLEAN){
		   outputToFile(p);
	   }
   }
   
   public void visit( VariableDeclaration p ) throws ParseTreeException{
	   
	   //TODO: Check if this is correct
	   
	   try {
		getEnvironment().bindVariable(p.getVariable(), OJClass.forName(p.getTypeSpecifier().toString()));
	   } catch (OJClassNotFoundException e) {
		System.err.println("Couldn't bind variable " + p.getVariable());
	   }
	   
	   /* Only mutating boolean variable declarations with initialization */
	   if( "boolean".equals(p.getTypeSpecifier().getName()) && p.getInitializer() != null){
		   outputToFile(p);
	   } else {
		   super.visit(p);
	   }
   }
   
   public void visit(IfStatement p ) throws ParseTreeException{
	   if( Literal.class.equals(p.getExpression().getClass()) ){
		   outputToFile(p);
	   }
	   super.visit(p);
   }
   
   public void visit(WhileStatement p ) throws ParseTreeException{
	   if( Literal.class.equals(p.getExpression().getClass()) ){
		   outputToFile(p);
	   }
	   super.visit(p);
   }
   
   public void visit(DoWhileStatement p ) throws ParseTreeException{
	   if( Literal.class.equals(p.getExpression().getClass()) ){
		   outputToFile(p);
	   }
	   super.visit(p);
   }
   
   public void visit(ForStatement p ) throws ParseTreeException{
	   if( Literal.class.equals(p.getCondition().getClass()) ){
		   outputToFile(p);
	   }
	   super.visit(p);
   }
   
   
   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFile(UnaryExpression original)
   {
      if (comp_unit == null) {
    	  return;
      }

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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!

	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, null, "Unary Expression");
   }
   
   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFile(BinaryExpression original)
   {
      if (comp_unit == null) {
    	  return;
      }
      
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!
      
	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, null, "Binary Expression");
   }
   
   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFileLeft(BinaryExpression original)
   {
      if (comp_unit == null) {
    	  return;
      }
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!
      
	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, "Left", "Binary Expression");
   }
   
   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFileRight(BinaryExpression original)
   {
      if (comp_unit == null) {
    	  return;
      }
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!

	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, "Right", "Binary Expression");
   }
   
   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFile(AssignmentExpression original)
   {
      if (comp_unit == null) {
    	  return;
      }
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!

	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, null, "Assignment Expression");
   }
   
   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFile(VariableDeclaration original)
   {
      if (comp_unit == null) {
    	  return;
      }
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!

	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, null, "Variable Declaration");
   }

   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFile(IfStatement original)
   {
      if (comp_unit == null) {
    	  return;
      }
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!

	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, null, "If Statement");
   }
   
   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFile(DoWhileStatement original)
   {
      if (comp_unit == null) {
    	  return;
      }
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!

	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, null, "DoWhile Statement");
   }
   
   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFile(ForStatement original)
   {
      if (comp_unit == null) {
    	  return;
      }
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!

	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, null, "For Statement");
   }
   
   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFile(WhileStatement original)
   {
      if (comp_unit == null) {
    	  return;
      }
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!

	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, null, "While Statement");
   }
   
   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFile(Variable original)
   {
      if (comp_unit==null){
    	  return;
      }
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!

	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, null, "Variable");
      
   }

   public void outputToFile(Literal original)
   {
      if (comp_unit==null){
    	  return;
      }
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!

	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
			  original, null, "Literal");
      
   }

   
   
   /**
    * Output COI mutants to files
    * @param original
    */
   public void outputToFile(FieldAccess original)
   {
      if (comp_unit == null){
    	  return;
      }
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

		if (parent==null) return; //do not mutate if no mutation generation limit provided!

	  MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.COI,
    			  original, null, "Field Access");
   }
}
