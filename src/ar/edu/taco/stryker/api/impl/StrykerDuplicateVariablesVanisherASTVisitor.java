package ar.edu.taco.stryker.api.impl;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;

import com.google.common.collect.Sets;

public class StrykerDuplicateVariablesVanisherASTVisitor extends ASTVisitor {

    private final OpenJMLInputWrapper wrapper;
    private final CompilationUnit unit;
    private final String source;
    private final AST ast;
    private final String seqFileName;
    private String methodName;
    private final ASTRewrite rewrite;
    private final Set<ASTNode> customNodes = Sets.newHashSet();
    private Set<String> variables;
    public StrykerDuplicateVariablesVanisherASTVisitor(final OpenJMLInputWrapper wrapper, CompilationUnit unit, String source, final AST ast, String seqFileName) {
        super();
        this.wrapper = wrapper;
        this.unit = unit;
        this.source = source;
        this.ast = ast;
        this.seqFileName = seqFileName;
        this.rewrite = ASTRewrite.create(ast);
    }
    
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setVariables(Set<String> variables) {
        this.variables = variables;
    }
    
    @Override
    public boolean visit(VariableDeclarationStatement node) {
        List<VariableDeclarationFragment> fragments = node.fragments();
        int size = fragments.size();
        for (VariableDeclarationFragment fragment : fragments) {
            if (variables.contains(fragment.getName().toString())) {
                rewrite.remove(fragment, null);
                size -= 1;
            } else {
                variables.add(fragment.getName().toString());
            }
        }
        if (size == 0) {
            rewrite.remove(node, null);
        }

        return false;
    }
    
    public ASTRewrite getRewrite() {
        return rewrite;
    }
}
