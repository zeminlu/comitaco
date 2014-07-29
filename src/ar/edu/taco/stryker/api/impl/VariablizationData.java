package ar.edu.taco.stryker.api.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jface.text.IDocument;

public class VariablizationData {
	
	private Map<Integer, Pair<ITypeBinding, List<Expression>>> expressions;
	private int lastVariablizedIndex = -1;
	private int lastVarNumber = 0;
	private CompilationUnit unit;
	private MethodDeclaration method;
	private ASTRewrite rewrite;
	private String source;
	
	public VariablizationData(String source, CompilationUnit unit, MethodDeclaration method, Map<Integer, Pair<ITypeBinding, List<Expression>>> expressions) {
		super();
		this.source = source;
		this.unit = unit;
		this.method = method;
		this.expressions = expressions;
		this.rewrite = ASTRewrite.create(unit.getAST());
	}
	
	public Map<Integer, Pair<ITypeBinding, List<Expression>>> getExpressions() {
		return expressions;
	}
	
	public CompilationUnit getUnit() {
		return unit;
	}
	
	public int getLastVariablizedIndex() {
		return lastVariablizedIndex;
	}
	
	public int getLastVarNumber() {
		return lastVarNumber;
	}
	
	public ASTRewrite getRewrite() {
		return rewrite;
	}
	
	public void setLastVariablizedIndex(int lastVariablizedIndex) {
		this.lastVariablizedIndex = lastVariablizedIndex;
	}

	public void setLastVarNumber(int lastVarNumber) {
		this.lastVarNumber = lastVarNumber;
	}

	public String getSource() {
        return source;
    }
	
	public MethodDeclaration getMethod() {
		return method;
	}
}
