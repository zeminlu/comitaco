////////////////////////////////////////////////////////////////////////////
// Module : AOIS.java
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
import openjava.ptree.ParseTreeException;
import openjava.ptree.ParseTreeObject;
import openjava.ptree.UnaryExpression;
import openjava.ptree.Variable;
import openjava.ptree.NonLeaf;

/**
 * <p>Generate AOIS (Arithmetic Operator Insertion (Short-cut)) mutants --
 *    insert unary operators (increment ++, decrement --) before and after
 *    each variable of an arithmetic type  
 * </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
 */

public class AOIS extends Arithmetic_OP
{

	private int mutationsLeft = -1;
	ParseTreeObject parent = null;

	boolean isPrePostEQ = true;

	public AOIS(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
	{
		super( file_env, comp_unit );
	}

	public void visit( UnaryExpression p ) throws ParseTreeException
	{
		// NO OPERATION
	}

	public void visit(Variable p) throws ParseTreeException
	{

		if (isArithmeticType(p))
		{
			if (isPrePostEQ)
			{
				outputToFile(p, p.toString() + "++" );
				outputToFile(p, p.toString() + "--" );
			}
			else
			{
				outputToFile(p, "++" + p.toString() );
				outputToFile(p, "--" + p.toString() );
				outputToFile(p, p.toString() + "++" );
				outputToFile(p, p.toString()+"--" );
			}
		}
	}

	public void visit( FieldAccess p ) throws ParseTreeException
	{

		if (isArithmeticType(p))
		{
			if (isPrePostEQ)
			{
				outputToFile(p, p.toString() + "++" );
				outputToFile(p, p.toString() + "--" );
			}
			else
			{
				outputToFile(p, "++" + p.toString() );
				outputToFile(p, "--" + p.toString() );
				outputToFile(p, p.toString() + "++" );
				outputToFile(p, p.toString() + "--" );
			}
		}
	}

	public void visit( BinaryExpression p ) throws ParseTreeException 
	{

		isPrePostEQ = false;
		super.visit(p);
		isPrePostEQ = true;
	}

	public void visit( AssignmentExpression p ) throws ParseTreeException
	{
		isPrePostEQ = false;
		Expression rexp = p.getRight();
		rexp.accept( this );
		isPrePostEQ = true;
	}

	/**
	 * Write AOIS mutants to files
	 * @param original_field
	 * @param mutant
	 */
	public void outputToFile(FieldAccess original_field, String mutant)
	{
		if (comp_unit == null) 
			return;

		parent = original_field.getMutationLimitParent();
		if (parent != null) {
			try {
				String mutations = ((NonLeaf) parent).getComment().substring(14);
				mutationsLeft = Integer.parseInt(mutations);
			}
			catch (Exception e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;

		//********** MUJAVA++ MODIFICATION **********//
		//********** date: 6 Dic 2011     **********//
		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.AOIS,
					original_field, mutant, "FieldAccess");
			return;
		}
		//*******************************************//

		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

		String f_name;
		num++;
		f_name = getSourceName("AOIS");
		String mutant_dir = getMuantID("AOIS");

		try 
		{
			PrintWriter out = getPrintWriter(f_name);
			AOIS_Writer writer = new AOIS_Writer(mutant_dir, out);
			writer.setMutant(original_field, mutant);
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

	/**
	 * Write AOIS mutants to files
	 * @param original_var
	 * @param mutant
	 */
	public void outputToFile(Variable original_var, String mutant)
	{

		if (comp_unit == null) 
			return;

		parent = original_var.getMutationLimitParent();
		if (parent != null) {
			try {
				String mutations = ((NonLeaf) parent).getComment().substring(14);
				mutationsLeft = Integer.parseInt(mutations);
			}
			catch (Exception e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;

		//********** MUJAVA++ MODIFICATION **********//
		//********** date: 6 Dic 2011     **********//
		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(Mutant.AOIS,
					original_var, mutant, "Variable");
			return;
		}
		//*******************************************//

		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

		String f_name;
		num++;
		f_name = getSourceName("AOIS");
		String mutant_dir = getMuantID("AOIS");

		try 
		{
			PrintWriter out = getPrintWriter(f_name);
			AOIS_Writer writer = new AOIS_Writer(mutant_dir, out);
			writer.setMutant(original_var, mutant);
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
