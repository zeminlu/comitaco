////////////////////////////////////////////////////////////////////////////
// Module : AODU.java
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
import openjava.ptree.ParseTreeException;
import openjava.ptree.UnaryExpression;
import openjava.ptree.ParseTreeObject;
import openjava.ptree.NonLeaf;

/**
 * <p>Generate AODU (Arithmetic Operator Deletion (Unary)) mutants --
 *    delete a unary operator (arithmetic -) before each variable or 
 *    expression
 * </p> 
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
 */

public class AODU extends Arithmetic_OP
{
	boolean aor_flag = false;

	private int mutationsLeft = -1;
	ParseTreeObject parent = null;


	public AODU(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
	{
		super( file_env, comp_unit );
	}

	/**
	 * Set AOR flag 
	 * @param b
	 */
	public void setAORflag(boolean b)
	{
		aor_flag = b;
	}

	public void visit( BinaryExpression p) throws ParseTreeException
	{

		// Examine equivalent
		if (aor_flag && isArithmeticType(p)) 
		{
			if ( (p.getOperator() == BinaryExpression.MINUS) || 
					(p.getOperator() == BinaryExpression.PLUS)  ||
					(p.getOperator() == BinaryExpression.MOD))
			{
				Expression e1 = p.getLeft();
				super.visit(e1);
			}
		}
	}

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

	public void visit( UnaryExpression p ) throws ParseTreeException
	{

		if (isArithmeticType(p))
		{
			int op = p.getOperator();
			if ( (op == UnaryExpression.MINUS) || (op == UnaryExpression.PLUS) )
			{
				outputToFile(p);
			} 
		}
	}

	/**
	 * Write AODU mutants to files
	 * @param original
	 */
	public void outputToFile(UnaryExpression original)
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
			MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.AODU, original);
			return;
		}
		//*******************************************//

		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

		String f_name;
		num++;
		f_name = getSourceName("AODU");
		String mutant_dir = getMuantID("AODU");

		try 
		{
			PrintWriter out = getPrintWriter(f_name);
			AODU_Writer writer = new AODU_Writer(mutant_dir, out);
			writer.setMutant(original);
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
