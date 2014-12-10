package mujava.api;

import java.io.PrintWriter;

import mujava.op.PRV_Writer;
import mujava.op.basic.AODS_Writer;
import mujava.op.basic.AODU_Writer;
import mujava.op.basic.AOIS_Writer;
import mujava.op.basic.AOIU_Writer;
import mujava.op.basic.AORB_Writer;
import mujava.op.basic.AORS_Writer;
import mujava.op.basic.AORU_Writer;
import mujava.op.basic.ASRS_Writer;
import mujava.op.basic.COD_Writer;
import mujava.op.basic.COI_Writer;
import mujava.op.basic.COR_Writer;
import mujava.op.basic.LOD_Writer;
import mujava.op.basic.LOI_Writer;
import mujava.op.basic.LOR_Writer;
import mujava.op.basic.ROR_Writer;
import mujava.op.basic.SOR_Writer;
import openjava.ptree.AssignmentExpression;
import openjava.ptree.BinaryExpression;
import openjava.ptree.CompilationUnit;
import openjava.ptree.DoWhileStatement;
import openjava.ptree.FieldAccess;
import openjava.ptree.ForStatement;
import openjava.ptree.IfStatement;
import openjava.ptree.ParseTreeException;
import openjava.ptree.UnaryExpression;
import openjava.ptree.Variable;
import openjava.ptree.VariableDeclaration;
import openjava.ptree.VariableDeclarator;
import openjava.ptree.WhileStatement;
import openjava.ptree.LabeledStatement;
import openjava.ptree.ExpressionStatement;
import openjava.ptree.ParseTreeObject;
import openjava.ptree.NonLeaf;
import openjava.ptree.ReturnStatement;
import openjava.ptree.Literal;

/**
 * Used for converting mutant identifiers into java files
 */
public class MutantIdentifierWriter {

	private CompilationUnit source;
	private PrintWriter output;

	public MutantIdentifierWriter(CompilationUnit source, PrintWriter output) {
		this.source = source;
		this.output = output;
	}

	public int write(MutantIdentifier mutant) throws ParseTreeException {
		int ret = -1;
		//TODO: Add all the mutant operators
		if (mutant.getMutOp().equals(Mutant.AODS)) {
			/* ---- get the mutation limit comment and decrement it ---- */
			UnaryExpression orig = (UnaryExpression) mutant.getOriginal();
			ParseTreeObject parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null && ((NonLeaf) parent).getComment()!=null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */
			AODS_Writer writer = new AODS_Writer(null, output);
			writer.setMutant((UnaryExpression) mutant.getOriginal());
			source.accept(writer);
			ret = writer.getMutatedLine();

			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */
		} else if (mutant.getMutOp().equals(Mutant.AODU)) {
			/* ---- get the mutation limit comment and decrement it ---- */
			UnaryExpression orig = (UnaryExpression) mutant.getOriginal();
			ParseTreeObject parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null && ((NonLeaf) parent).getComment()!=null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */
			AODU_Writer writer = new AODU_Writer(null, output);
			writer.setMutant((UnaryExpression) mutant.getOriginal());
			source.accept(writer);
			ret = writer.getMutatedLine();

			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */

		} else if (mutant.getMutOp().equals(Mutant.AOIS)) {
			/* ---- get the mutation limit comment and decrement it ---- */
			ParseTreeObject parent;
			if (mutant.getAdditionalInfo().equals("Variable")) {
				Variable orig = (Variable) mutant.getOriginal();
				parent = orig.getMutationLimitParent();
			}
			else {
				FieldAccess orig = (FieldAccess) mutant.getOriginal();
				parent = orig.getMutationLimitParent();				
			}
			int mutationsLeft = -1;
			if (parent != null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */

			AOIS_Writer writer = new AOIS_Writer(null, output);
			if (mutant.getAdditionalInfo().equals("Variable")) {
				writer.setMutant((Variable) mutant.getOriginal(), (String) mutant.getMutant());
			} else {
				writer.setMutant((FieldAccess) mutant.getOriginal(), (String) mutant.getMutant());
			}
			source.accept(writer);
			ret = writer.getMutatedLine();

			/* ---- restore the mutation limit comment for other mutants ---- */						
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			/* ---- ---- */

		} else if (mutant.getMutOp().equals(Mutant.AOIU)) {
			/* ---- get the mutation limit comment and decrement it ---- */
			ParseTreeObject parent;
			if (mutant.getAdditionalInfo().equals("Variable")) {
				Variable orig = (Variable) mutant.getOriginal();
				parent = orig.getMutationLimitParent();
			}
			else {
				FieldAccess orig = (FieldAccess) mutant.getOriginal();
				parent = orig.getMutationLimitParent();				
			}
			int mutationsLeft = -1;
			if (parent != null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */
			
			AOIU_Writer writer = new AOIU_Writer(null, output);
			if (mutant.getAdditionalInfo().equals("Variable")) {
				writer.setMutant((Variable) mutant.getOriginal());
			} else {
				writer.setMutant((FieldAccess) mutant.getOriginal());
			}
			source.accept(writer);
			ret = writer.getMutatedLine();
			/* ---- restore the mutation limit comment for other mutants ---- */						
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			/* ---- ---- */
		} else if (mutant.getMutOp().equals(Mutant.AORB)) {
			/* ---- get the mutation limit comment and decrement it ---- */
			BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
			ParseTreeObject parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null && ((NonLeaf) parent).getComment()!=null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			
			AORB_Writer writer = new AORB_Writer(null, output);
			writer.setMutant((BinaryExpression) mutant.getOriginal(),
					(BinaryExpression) mutant.getMutant());
			source.accept(writer);
			ret = writer.getMutatedLine();
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */
		} else if (mutant.getMutOp().equals(Mutant.AORS)) {
			/* ---- get the mutation limit comment and decrement it ---- */
			UnaryExpression orig = (UnaryExpression) mutant.getOriginal();
			ParseTreeObject parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null && ((NonLeaf) parent).getComment()!=null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			
			AORS_Writer writer = new AORS_Writer(null, output);
			writer.setMutant((UnaryExpression) mutant.getOriginal(),
					(UnaryExpression) mutant.getMutant());
			source.accept(writer);
			ret = writer.getMutatedLine();
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */
		} else if (mutant.getMutOp().equals(Mutant.AORU)) {
			/* ---- get the mutation limit comment and decrement it ---- */
			UnaryExpression orig = (UnaryExpression) mutant.getOriginal();
			ParseTreeObject parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null && ((NonLeaf) parent).getComment()!=null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */
			AORU_Writer writer = new AORU_Writer(null, output);
			writer.setMutant((UnaryExpression) mutant.getOriginal(),
					(UnaryExpression) mutant.getMutant());
			source.accept(writer);
			ret = writer.getMutatedLine();
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			
		} else if (mutant.getMutOp().equals(Mutant.ROR)) {
			/* ---- get the mutation limit comment and decrement it ---- */
			BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
			ParseTreeObject parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null && ((NonLeaf) parent).getComment()!=null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			
			ROR_Writer writer = new ROR_Writer(null, output);
			writer.setMutant((BinaryExpression) mutant.getOriginal(), (BinaryExpression) mutant.getMutant());
			source.accept( writer );
			ret = writer.getMutatedLine();
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			
		} else if (mutant.getMutOp().equals(Mutant.LOD)){
			/* ---- get the mutation limit comment and decrement it ---- */
			UnaryExpression orig = (UnaryExpression) mutant.getOriginal();
			ParseTreeObject parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null && ((NonLeaf) parent).getComment()!=null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */

			LOD_Writer writer = new LOD_Writer(null, output);
			writer.setMutant((UnaryExpression)mutant.getOriginal());
			source.accept(writer);
			ret = writer.getMutatedLine();
			
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

		} else if (mutant.getMutOp().equals(Mutant.LOI)){

			/* ---- get the mutation limit comment and decrement it ---- */
			ParseTreeObject parent;
			if (mutant.getAdditionalInfo().equals("Variable")) {
				Variable orig = (Variable) mutant.getOriginal();
				parent = orig.getMutationLimitParent();
			}
			else {
				FieldAccess orig = (FieldAccess) mutant.getOriginal();
				parent = orig.getMutationLimitParent();				
			}
			int mutationsLeft = -1;
			if (parent != null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			
			
			LOI_Writer writer = new LOI_Writer(null, output);
			if ( "Variable".equals(mutant.getAdditionalInfo()) ){
				writer.setMutant((Variable)mutant.getOriginal());
			} else if ( "Field Access".equals(mutant.getAdditionalInfo()) ){
				writer.setMutant((FieldAccess)mutant.getOriginal());
			} else {
				throw new IllegalStateException();
			}
			source.accept(writer);
			ret = writer.getMutatedLine();
			
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

		} else if (mutant.getMutOp().equals(Mutant.LOR)){
			/* ---- get the mutation limit comment and decrement it ---- */
			BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
			ParseTreeObject parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null && ((NonLeaf) parent).getComment()!=null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			
			
			LOR_Writer writer = new LOR_Writer(null, output);
			writer.setMutant((BinaryExpression)mutant.getOriginal(), (BinaryExpression)mutant.getMutant());
			source.accept(writer);
			ret = writer.getMutatedLine();
			
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

		} else if (mutant.getMutOp().equals(Mutant.SOR)){
			/* ---- get the mutation limit comment and decrement it ---- */
			BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
			ParseTreeObject parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null && ((NonLeaf) parent).getComment()!=null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */		
			
			SOR_Writer writer = new SOR_Writer(null, output);
			writer.setMutant((BinaryExpression)mutant.getOriginal(), (BinaryExpression)mutant.getMutant());
			source.accept(writer);
			ret = writer.getMutatedLine();
			
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			
			
		} else if (mutant.getMutOp().equals(Mutant.PRV)){
			/* ---- get the mutation limit comment and decrement it ---- */
			ParseTreeObject parent = null;
			if ("Assignment Expression Right".equals(mutant.getAdditionalInfo())) {
				AssignmentExpression orig = (AssignmentExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();
			}
			else if ( "Assignment Expression Left".equals(mutant.getAdditionalInfo())) {
				AssignmentExpression orig = (AssignmentExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();				
			}
			else if ("Binary Expression Left".equals(mutant.getAdditionalInfo())) {
				BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();								
			}
			else if ( "Binary Expression Right".equals(mutant.getAdditionalInfo())) {
				BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();												
			}
			else if( "Variable Declaration Right".equals(mutant.getAdditionalInfo())) {
				VariableDeclaration orig = (VariableDeclaration) mutant.getOriginal();
				parent = orig.getMutationLimitParent();																
			}
			int mutationsLeft = -1;
			if (parent != null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			
			
			PRV_Writer writer = new PRV_Writer(null, output);
			if( "Assignment Expression Right".equals(mutant.getAdditionalInfo())){
				writer.setMutant((AssignmentExpression)mutant.getOriginal(), (String)mutant.getMutant());
			} else if( "Assignment Expression Left".equals(mutant.getAdditionalInfo())){
				writer.setMutant((AssignmentExpression)mutant.getOriginal(), (String)mutant.getMutant());
				writer.setLeftPRV();
			} else if("Binary Expression Left".equals(mutant.getAdditionalInfo())){
				writer.setMutant((BinaryExpression)mutant.getOriginal(), (String)mutant.getMutant());
				writer.setLeftPRV();
			} else if( "Binary Expression Right".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((BinaryExpression)mutant.getOriginal(), (String)mutant.getMutant());
//			} else if( "Variable Declaration Right".equals(mutant.getAdditionalInfo())) {
//				writer.setMutant((VariableDeclaration)mutant.getOriginal(), (String)mutant.getMutant());
			} else {
				throw new IllegalStateException();
			}

			source.accept(writer);
			ret = writer.getMutatedLine();
			
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

		} else if (mutant.getMutOp().equals(Mutant.PRVO)){
			/* ---- get the mutation limit comment and decrement it ---- */
			ParseTreeObject parent = null;
			if ("Assignment Expression Right".equals(mutant.getAdditionalInfo())) {
				AssignmentExpression orig = (AssignmentExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();
			}
			else if( "Assignment Expression Right in Labelled Statement".equals(mutant.getAdditionalInfo())){
				LabeledStatement orig = (LabeledStatement) mutant.getOriginal();
				parent = orig.getMutationLimitParent();
			}
			else if ( "Assignment Expression Left".equals(mutant.getAdditionalInfo())) {
				AssignmentExpression orig = (AssignmentExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();				
			}
			else if ("Binary Expression Left".equals(mutant.getAdditionalInfo())) {
				BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();								
			}
			else if ( "Binary Expression Right".equals(mutant.getAdditionalInfo())) {
				BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();												
			}
//			else if( "Variable Declaration Right".equals(mutant.getAdditionalInfo())) {
//				VariableDeclaration orig = (VariableDeclaration) mutant.getOriginal();
//				parent = orig.getMutationLimitParent();																
//			}
			else if( "Variable Declarator Right".equals(mutant.getAdditionalInfo())) {
				VariableDeclarator orig = (VariableDeclarator) mutant.getOriginal();
				parent = orig.getMutationLimitParent();																
			}
			int mutationsLeft = -1;
			if (parent != null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			
			
			PRV_Writer writer = new PRV_Writer(null, output);
			if( "Assignment Expression Right".equals(mutant.getAdditionalInfo())){
				writer.setMutant((AssignmentExpression)mutant.getOriginal(), (String)mutant.getMutant());
			} 
			else if( "Assignment Expression Right in Labelled Statement".equals(mutant.getAdditionalInfo())){
				writer.setMutant((LabeledStatement)mutant.getOriginal(), (String)mutant.getMutant());
			}
			else if( "Assignment Expression Left".equals(mutant.getAdditionalInfo())){
				writer.setMutant((AssignmentExpression)mutant.getOriginal(), (String)mutant.getMutant());
				writer.setLeftPRV();
			} else if("Binary Expression Left".equals(mutant.getAdditionalInfo())){
				writer.setMutant((BinaryExpression)mutant.getOriginal(), (String)mutant.getMutant());
				writer.setLeftPRV();
			} else if( "Binary Expression Right".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((BinaryExpression)mutant.getOriginal(), (String)mutant.getMutant());
//			} else if( "Variable Declaration Right".equals(mutant.getAdditionalInfo())) {
//				writer.setMutant((VariableDeclaration)mutant.getOriginal(), (String)mutant.getMutant());
			} else if( "Variable Declarator Right".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((VariableDeclarator)mutant.getOriginal(), (String)mutant.getMutant());
			} else if( "Return Statement Right".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((ReturnStatement)mutant.getOriginal(), (String)mutant.getMutant());
			} else {
				throw new IllegalStateException();
			}

			source.accept(writer);
			ret = writer.getMutatedLine();
			
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

		} else if (mutant.getMutOp().equals(Mutant.PRVV)){
			/* ---- get the mutation limit comment and decrement it ---- */
			ParseTreeObject parent = null;
			if ("Assignment Expression Right".equals(mutant.getAdditionalInfo())) {
				AssignmentExpression orig = (AssignmentExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();
			}
			else if ( "Assignment Expression Left".equals(mutant.getAdditionalInfo())) {
				AssignmentExpression orig = (AssignmentExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();				
			}
			else if ("Binary Expression Left".equals(mutant.getAdditionalInfo())) {
				BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();								
			}
			else if ( "Binary Expression Right".equals(mutant.getAdditionalInfo())) {
				BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();												
			}
			else if( "Variable Declaration Right".equals(mutant.getAdditionalInfo())) {
				VariableDeclaration orig = (VariableDeclaration) mutant.getOriginal();
				parent = orig.getMutationLimitParent();																
			}
			else if( "Return Statement Right".equals(mutant.getAdditionalInfo())) {
				ReturnStatement orig = (ReturnStatement) mutant.getOriginal();
				parent = orig.getMutationLimitParent();																
			}
			int mutationsLeft = -1;
			if (parent != null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

			PRV_Writer writer = new PRV_Writer(null, output);
			if( "Assignment Expression Right".equals(mutant.getAdditionalInfo())){
				writer.setMutant((AssignmentExpression)mutant.getOriginal(), (String)mutant.getMutant());
			} else if( "Assignment Expression Left".equals(mutant.getAdditionalInfo())){
				writer.setMutant((AssignmentExpression)mutant.getOriginal(), (String)mutant.getMutant());
				writer.setLeftPRV();
			} else if("Binary Expression Left".equals(mutant.getAdditionalInfo())){
				writer.setMutant((BinaryExpression)mutant.getOriginal(), (String)mutant.getMutant());
				writer.setLeftPRV();
			} else if( "Binary Expression Right".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((BinaryExpression)mutant.getOriginal(), (String)mutant.getMutant());
//			} else if( "Variable Declaration Right".equals(mutant.getAdditionalInfo())) {
//				writer.setMutant((VariableDeclaration)mutant.getOriginal(), (String)mutant.getMutant());
			} else if( "Return Statement Right".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((ReturnStatement)mutant.getOriginal(), (String)mutant.getMutant());
			} else {
				throw new IllegalStateException();
			}

			source.accept(writer);
			ret = writer.getMutatedLine();

			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

		} else if (mutant.getMutOp().equals(Mutant.ASRS)) {

			/* ---- get the mutation limit comment and decrement it ---- */
			ParseTreeObject parent = null;
			AssignmentExpression orig = (AssignmentExpression) mutant.getOriginal();
			parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

			
			ASRS_Writer writer = new ASRS_Writer(null, output);
			writer.setMutant((AssignmentExpression)mutant.getOriginal(), (AssignmentExpression)mutant.getMutant());
			source.accept(writer);
			ret = writer.getMutatedLine();
			
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

		} else if (mutant.getMutOp().equals(Mutant.COD)) {

			/* ---- get the mutation limit comment and decrement it ---- */
			ParseTreeObject parent = null;
			UnaryExpression orig = (UnaryExpression) mutant.getOriginal();
			parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

			COD_Writer writer = new COD_Writer(null, output);
			writer.setMutant((UnaryExpression)mutant.getOriginal());
			source.accept(writer);
			ret = writer.getMutatedLine();
			
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

		} else if (mutant.getMutOp().equals(Mutant.COI)) {
			/* ---- get the mutation limit comment and decrement it ---- */
			ParseTreeObject parent = null;
			if ("Variable".equals(mutant.getAdditionalInfo())) {
				Variable orig = (Variable) mutant.getOriginal();
				parent = orig.getMutationLimitParent();
			}
			else if ( "FieldAccess".equals(mutant.getAdditionalInfo())) {
				FieldAccess orig = (FieldAccess) mutant.getOriginal();
				parent = orig.getMutationLimitParent();				
			}
			else if ("Binary Expression".equals(mutant.getAdditionalInfo())) {
				BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();								
			}
			else if ( "Assignment Expression".equals(mutant.getAdditionalInfo())) {
				AssignmentExpression orig = (AssignmentExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();												
			}
			else if( "Variable Declaration".equals(mutant.getAdditionalInfo())) {
				VariableDeclaration orig = (VariableDeclaration) mutant.getOriginal();
				parent = orig.getMutationLimitParent();																
			} else if( "If Statement".equals(mutant.getAdditionalInfo())) {
				IfStatement orig = (IfStatement) mutant.getOriginal();
				parent = orig.getMutationLimitParent();																
			} else if( "DoWhile Statement".equals(mutant.getAdditionalInfo())) {
				DoWhileStatement orig = (DoWhileStatement) mutant.getOriginal();
				parent = orig.getMutationLimitParent();																
			} else if( "For Statement".equals(mutant.getAdditionalInfo())) {
				ForStatement orig = (ForStatement) mutant.getOriginal();
				parent = orig.getMutationLimitParent();																
			} else if( "While Statement".equals(mutant.getAdditionalInfo())) {
				WhileStatement orig = (WhileStatement) mutant.getOriginal();
				parent = orig.getMutationLimitParent();																
			} else if( "Unary Expression".equals(mutant.getAdditionalInfo())) {
				UnaryExpression orig = (UnaryExpression) mutant.getOriginal();
				parent = orig.getMutationLimitParent();		
			} else if( "Literal".equals(mutant.getAdditionalInfo())) {
				Literal orig = (Literal) mutant.getOriginal();
				parent = orig.getMutationLimitParent();																
			} else {
				throw new IllegalStateException();
			}
			int mutationsLeft = -1;
			if (parent != null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

			COI_Writer writer = new COI_Writer(null, output);
			if ( "Variable".equals(mutant.getAdditionalInfo()) ){
				writer.setMutant((Variable)mutant.getOriginal());
			} else if ( "Field Access".equals(mutant.getAdditionalInfo()) ){
				writer.setMutant((FieldAccess)mutant.getOriginal());
			} else if( "Binary Expression".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((BinaryExpression)mutant.getOriginal());
				if( mutant.getMutant() != null ){
					String whichSide = (String)mutant.getMutant();
					if( "Left".equals(whichSide) ){
						writer.mutateLeft();
					} else if( "Right".equals(whichSide) ){
						writer.mutateRight();
					} else {
						throw new IllegalStateException();
					}
				}
			} else if( "Assignment Expression".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((AssignmentExpression)mutant.getOriginal());
			} else if( "Variable Declaration".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((VariableDeclaration)mutant.getOriginal());
			} else if( "If Statement".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((IfStatement)mutant.getOriginal());
			} else if( "DoWhile Statement".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((DoWhileStatement)mutant.getOriginal());
			} else if( "For Statement".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((ForStatement)mutant.getOriginal());
			} else if( "While Statement".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((WhileStatement)mutant.getOriginal());
			} else if( "Unary Expression".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((UnaryExpression)mutant.getOriginal());
			} else if( "Literal".equals(mutant.getAdditionalInfo())) {
				writer.setMutant((Literal)mutant.getOriginal());
			} else {
				throw new IllegalStateException();
			}
			source.accept(writer);
			ret = writer.getMutatedLine();
			
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

		} else if (mutant.getMutOp().equals(Mutant.COR)) {

			/* ---- get the mutation limit comment and decrement it ---- */
			ParseTreeObject parent = null;
			BinaryExpression orig = (BinaryExpression) mutant.getOriginal();
			parent = orig.getMutationLimitParent();
			int mutationsLeft = -1;
			if (parent != null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
			if (mutationsLeft>0) {
				mutationsLeft--;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

			COR_Writer writer = new COR_Writer(null, output);
			writer.setMutant((BinaryExpression)mutant.getOriginal(), (BinaryExpression)mutant.getMutant());
			source.accept(writer);
			ret = writer.getMutatedLine();
			
			/* ---- restore the mutation limit comment for other mutants ---- */			
			if (mutationsLeft>=0) {
				mutationsLeft++;
				((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
			}
			/* ---- ---- */			

		} else {
			throw new IllegalStateException("The mutant " + mutant.getMutOp() + " isn't writable");
		}

		output.flush();  
		output.close();
		return ret;
	}
}
