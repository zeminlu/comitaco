package ar.edu.taco.stryker.api.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.WildcardType;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

import ar.edu.taco.stryker.api.impl.input.DarwinistInput;
import ar.edu.taco.utils.FileUtils;

import com.google.common.collect.Lists;

public class VariablizationData {

    private Map<Integer, MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>>> expressions;
    private int lastVariablizedIndex = -1;
    private int lastVarNumber = 0;
    private CompilationUnit unit;
    private MethodDeclaration method;
    private ASTRewrite rewrite;
    private String source;
    private int lastVariablizedMutID;
    private boolean lastVariablizedMutIDRight;
    private boolean stillFatherable;

    public VariablizationData(String source, CompilationUnit unit, MethodDeclaration method, Map<Integer, MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>>> expressions) {
        super();
        this.source = source;
        this.unit = unit;
        this.method = method;
        this.expressions = expressions;
        this.rewrite = ASTRewrite.create(unit.getAST());
    }

    public Map<Integer, MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>>> getExpressions() {
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

    public boolean isLastVariablizedMutIDRight() {
        return lastVariablizedMutIDRight;
    }

    public void setLastVariablizedMutIDRight(boolean lastVariablizedMutIDRight) {
        this.lastVariablizedMutIDRight = lastVariablizedMutIDRight;
    }

    public int getLastVariablizedMutID() {
        return lastVariablizedMutID;
    }

    public void setLastVariablizedMutID(int lastVariablizedMutID) {
        this.lastVariablizedMutID = lastVariablizedMutID;
    }

    public boolean isStillFatherable() {
        return stillFatherable;
    }

    public void setStillFatherable(boolean stillFatherable) {
        this.stillFatherable = stillFatherable;
    }

    public static VariablizationData preprocessVariabilization2(DarwinistInput darwinistInput) {
        String variablizedFilename = darwinistInput.getSeqVariablizedFilename();
        if (variablizedFilename == null) {
            variablizedFilename = darwinistInput.getSeqFilesPrefix();
            darwinistInput.setSeqVariablizedFilename(variablizedFilename);
        }
        String source = "";

        try {
            source = FileUtils.readFile(variablizedFilename);
        } catch (final IOException e1) {
            // TODO: Define what to do!
        }

        final IDocument document = new Document(source);

        final org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);

        parser.setEnvironment(new String[] {
                System.getProperty("user.dir")+OpenJMLController.FILE_SEP+"bin", 
                "/Library/Java/JavaVirtualMachines/jdk1.7.0_21.jdk/Contents/Home/jre/lib/rt.jar"
        }, 
        null, null, false);
        parser.setUnitName(variablizedFilename);
        parser.setSource(document.get().toCharArray());
        // Parse the source code and generate an AST.
        final CompilationUnit unit = (CompilationUnit) parser.createAST(null);
        // to iterate through methods
        StrykerVariablizerVisitor visitor = new StrykerVariablizerVisitor(darwinistInput, unit, source, unit.getAST());

        // to iterate through methods
        @SuppressWarnings("unchecked")
        final List<AbstractTypeDeclaration> types = unit.types();
        for (final AbstractTypeDeclaration type : types) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                // Class def found
                @SuppressWarnings("unchecked")
                final List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (final BodyDeclaration body : bodies) {
                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        final MethodDeclaration method = (MethodDeclaration)body;
                        if (method.getName().toString().contains(darwinistInput.getMethod())) {
                            //First, we want to add some instructions as first lines of the method to create the output
                            //file for this method, where the sequential code is going to be outputted.
                            //Then, the visitor has to inspect every line of code and insert an output instruction to the
                            //previously created file, containing the exact line that just run, to obtain
                            //the secuential code branch. If it is a guard, replace it and brackets with an assert.

                            //To do this, we will implement an ASTVisitor that does everything we want, and we will
                            //give it the AST Tree to visit starting at this method.
                            visitor.setMethodName(method.getName().toString());
                            method.accept(visitor);
                        }
                    }
                }
            }
        }

        return visitor.buildVariablizationData();

    }
    //    public static VariablizationData preprocessVariabilization(DarwinistInput darwinistInput) {
    //        String variablizedFilename = darwinistInput.getSeqVariablizedFilename();
    //        if (variablizedFilename == null) {
    //            variablizedFilename = darwinistInput.getSeqFilesPrefix();
    //            darwinistInput.setSeqVariablizedFilename(variablizedFilename);
    //        }
    //        String source = "";
    //
    //        try {
    //            source = FileUtils.readFile(variablizedFilename);
    //        } catch (final IOException e1) {
    //            // TODO: Define what to do!
    //        }
    //
    //        final IDocument document = new Document(source);
    //
    //        final org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
    //        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
    //        parser.setResolveBindings(true);
    //
    //        parser.setEnvironment(new String[] {
    //                System.getProperty("user.dir")+OpenJMLController.FILE_SEP+"bin", 
    //                "/Library/Java/JavaVirtualMachines/jdk1.7.0_45.jdk/Contents/Home/jre/lib/rt.jar"
    //        }, 
    //        null, null, false);
    //        parser.setUnitName(variablizedFilename);
    //        parser.setSource(document.get().toCharArray());
    //        // Parse the source code and generate an AST.
    //        final CompilationUnit unit = (CompilationUnit) parser.createAST(null);
    //
    //        Integer mutIDNumber = null;
    //        Integer curMutableLine = 0;
    //        // to iterate through methods
    //        StrykerASTVisitor visitor = new StrykerASTVisitor(null, unit, source, unit.getAST(), variablizedFilename, null);
    //
    //        boolean stillFatherable = true;
    //
    //        Map<Integer, MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>>> rhsExpressions = Maps.newTreeMap();
    //        MethodDeclaration method = null;
    //        @SuppressWarnings("unchecked")
    //        final List<AbstractTypeDeclaration> types = unit.types();
    //        for (final AbstractTypeDeclaration type : types) {
    //            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
    //                // Class def found
    //                @SuppressWarnings("unchecked")
    //                final List<BodyDeclaration> bodies = type.bodyDeclarations();
    //                for (final BodyDeclaration body : bodies) {
    //                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
    //                        method = (MethodDeclaration)body;
    //                        //Veo si es uno de los que tengo que variabilizar
    //                        if (darwinistInput.getMethod().contains(method.getName().toString())) {
    //                            @SuppressWarnings("unchecked")
    //                            List<Statement> statements = method.getBody().statements();
    //                            //Itero por los statements de abajo hacia arriba
    //                            //Como lo estoy haciendo sobre el codigo con input fixed, es un ifstatement
    //                            for (int i = statements.size() - 1 ; i >= 0 ; --i) {
    //                                Statement statement = statements.get(i);
    //                                if (statement instanceof ExpressionStatement 
    //                                        && unit.lastTrailingCommentIndex(statement) >= 0
    //                                        && unit.firstLeadingCommentIndex(statement) >= 0) {
    //                                    //Es expression statement y tiene comentario
    //                                    Expression expression = ((ExpressionStatement) statement).getExpression();
    //                                    if (expression instanceof Assignment) {
    //                                        //Es una asignacion
    //
    //                                        //Tomar el id de mutante
    //                                        int commentIndex = unit.firstLeadingCommentIndex(statement);
    //                                        LineComment mutIDCommentNode;
    //                                        String mutID = null;
    //                                        int lastCommentIndex = unit.lastTrailingCommentIndex(statement);
    //                                        while (commentIndex <= lastCommentIndex) {
    //                                            mutIDCommentNode = ((LineComment) unit.getCommentList().get(commentIndex));
    //                                            mutID = source.substring(mutIDCommentNode.getStartPosition(), mutIDCommentNode.getStartPosition() + mutIDCommentNode.getLength());
    //                                            if (!mutID.contains("mutID")) {
    //                                                ++commentIndex;
    //                                            } else {
    //                                                mutIDNumber = Integer.valueOf(mutID.substring(8));
    //                                                break;
    //                                            }
    //                                        }
    //                                        if (commentIndex > lastCommentIndex) {
    //                                            ++curMutableLine;
    //                                            continue;
    //                                        }
    //
    //                                        Assignment assignment = (Assignment) expression;
    //
    //                                        ///LHS de la asignacion
    //                                        Expression lhs = assignment.getLeftHandSide();
    //                                        if (lhs instanceof FieldAccess /*&& !visitor.getLineComment(unit.lastTrailingCommentIndex(statement)).contains("mutGenLimit 1")*/) {
    //                                            //Es un FieldAccess, se variabiliza para PRVOL
    //                                            if (rhsExpressions.containsKey(mutIDNumber) 
    //                                                    && rhsExpressions.get(mutIDNumber).getRight() != null 
    //                                                    && rhsExpressions.get(mutIDNumber).getRight().getLeft() != null) {
    //                                                rhsExpressions.get(mutIDNumber).getRight().getLeft().add(lhs);
    //                                            } else {
    //                                                String mutGenLimit = visitor.getLineComment(unit.lastTrailingCommentIndex(statement));
    //                                                if (mutGenLimit.contains("mutGenLimit 0")) {
    //                                                    stillFatherable = false;
    //                                                }
    //                                                
    //                                                ITypeBinding binding = assignment.resolveTypeBinding();
    //                                                MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>> outerPair = 
    //                                                        rhsExpressions.containsKey(mutIDNumber) ? rhsExpressions.get(mutIDNumber) : 
    //                                                            new MutablePair<MutablePair<ITypeBinding,Boolean>, MutablePair<List<Expression>,List<Expression>>>(
    //                                                                    new MutablePair<ITypeBinding, Boolean>(), new MutablePair<List<Expression>, List<Expression>>());
    //                                                MutablePair<List<Expression>, List<Expression>> expressionsPair = outerPair.getRight() == null ? 
    //                                                        new MutablePair<List<Expression>, List<Expression>>() : outerPair.getRight();
    //                                                MutablePair<ITypeBinding, Boolean> bindingPair = outerPair.getLeft() == null ? new MutablePair<ITypeBinding, Boolean>() : outerPair.getLeft();
    //                                                outerPair.setLeft(bindingPair);
    //                                                outerPair.setRight(expressionsPair);
    //                                                List<Expression> expressions = expressionsPair.getLeft();
    //                                                if (expressions == null) { 
    //                                                    expressions = Lists.newArrayList();
    //                                                    expressionsPair.setLeft(expressions);
    //                                                }
    //                                                expressions.add(lhs);
    //                                                bindingPair.setLeft(binding);
    //                                                bindingPair.setRight(stillFatherable);
    //                                                rhsExpressions.put(curMutableLine, outerPair);
    //                                                curMutableLine++;
    //                                            }
    //                                        }
    //
    //                                        ///RHS de la asignacion
    //                                        Expression rhs = assignment.getRightHandSide();
    //
    //                                        if (rhsExpressions.containsKey(mutIDNumber) 
    //                                                && rhsExpressions.get(mutIDNumber).getRight() != null 
    //                                                && rhsExpressions.get(mutIDNumber).getRight().getRight() != null) {
    //                                            rhsExpressions.get(mutIDNumber).getRight().getRight().add(rhs);
    //                                        } else {
    //                                            String mutGenLimit = visitor.getLineComment(unit.lastTrailingCommentIndex(statement));
    //                                            if (mutGenLimit.contains("mutGenLimit 0")) {
    //                                                stillFatherable = false;
    //                                            }
    //                                            
    //                                            ITypeBinding binding = assignment.resolveTypeBinding();
    //                                            MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>> outerPair = 
    //                                                    rhsExpressions.containsKey(mutIDNumber) ? rhsExpressions.get(mutIDNumber) : 
    //                                                        new MutablePair<MutablePair<ITypeBinding,Boolean>, MutablePair<List<Expression>,List<Expression>>>(
    //                                                                new MutablePair<ITypeBinding, Boolean>(), new MutablePair<List<Expression>, List<Expression>>());
    //                                            MutablePair<List<Expression>, List<Expression>> expressionsPair = outerPair.getRight() == null ? 
    //                                                    new MutablePair<List<Expression>, List<Expression>>() : outerPair.getRight();
    //                                            MutablePair<ITypeBinding, Boolean> bindingPair = outerPair.getLeft() == null ? new MutablePair<ITypeBinding, Boolean>() : outerPair.getLeft();
    //                                            outerPair.setLeft(bindingPair);
    //                                            outerPair.setRight(expressionsPair);
    //                                            List<Expression> expressions = expressionsPair.getRight();
    //                                            if (expressions == null) { 
    //                                                expressions = Lists.newArrayList();
    //                                                expressionsPair.setRight(expressions);
    //                                            }
    //                                            expressions.add(rhs);
    //                                            bindingPair.setLeft(binding);
    //                                            bindingPair.setRight(stillFatherable);
    //                                            rhsExpressions.put(curMutableLine, outerPair);
    //                                            curMutableLine++;
    //                                        }
    //
    //                                    } else if (expression instanceof PostfixExpression) {
    //                                        //Tomar el id de mutante
    //                                        int commentIndex = unit.firstLeadingCommentIndex(statement);
    //                                        LineComment mutIDCommentNode;
    //                                        String mutID = null;
    //                                        int lastCommentIndex = unit.lastTrailingCommentIndex(statement);
    //                                        while (commentIndex <= lastCommentIndex) {
    //                                            mutIDCommentNode = ((LineComment) unit.getCommentList().get(commentIndex));
    //                                            mutID = source.substring(mutIDCommentNode.getStartPosition(), mutIDCommentNode.getStartPosition() + mutIDCommentNode.getLength());
    //                                            if (!mutID.contains("mutID")) {
    //                                                ++commentIndex;
    //                                            } else {
    //                                                mutIDNumber = Integer.valueOf(mutID.substring(8));
    //                                                break;
    //                                            }
    //                                        }
    //                                        if (commentIndex > lastCommentIndex) {
    //                                            curMutableLine++;
    //                                            continue;
    //                                        }
    //
    //                                        if (rhsExpressions.containsKey(mutIDNumber) 
    //                                                && rhsExpressions.get(mutIDNumber).getRight() != null 
    //                                                && rhsExpressions.get(mutIDNumber).getRight().getRight() != null) {
    //                                            rhsExpressions.get(mutIDNumber).getRight().getRight().add(expression);
    //                                        } else {
    //                                            String mutGenLimit = visitor.getLineComment(unit.lastTrailingCommentIndex(statement));
    //                                            if (mutGenLimit.contains("mutGenLimit 0")) {
    //                                                stillFatherable = false;
    //                                            }
    //                                            
    //                                            ITypeBinding binding = expression.resolveTypeBinding();
    //                                            MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>> outerPair = 
    //                                                    rhsExpressions.containsKey(mutIDNumber) ? rhsExpressions.get(mutIDNumber) : 
    //                                                        new MutablePair<MutablePair<ITypeBinding,Boolean>, MutablePair<List<Expression>,List<Expression>>>(
    //                                                                new MutablePair<ITypeBinding, Boolean>(), new MutablePair<List<Expression>, List<Expression>>());
    //                                            MutablePair<List<Expression>, List<Expression>> expressionsPair = outerPair.getRight() == null ? 
    //                                                    new MutablePair<List<Expression>, List<Expression>>() : outerPair.getRight();
    //                                            MutablePair<ITypeBinding, Boolean> bindingPair = outerPair.getLeft() == null ? new MutablePair<ITypeBinding, Boolean>() : outerPair.getLeft();
    //                                            outerPair.setLeft(bindingPair);
    //                                            outerPair.setRight(expressionsPair);
    //                                            List<Expression> expressions = expressionsPair.getRight();
    //                                            if (expressions == null) { 
    //                                                expressions = Lists.newArrayList();
    //                                                expressionsPair.setRight(expressions);
    //                                            }
    //                                            expressions.add(expression);
    //                                            bindingPair.setLeft(binding);
    //                                            bindingPair.setRight(stillFatherable);
    //                                            rhsExpressions.put(curMutableLine, outerPair);
    //                                            curMutableLine++;
    //                                        }
    //                                    } else if (expression instanceof PrefixExpression) {
    //                                        //Tomar el id de mutante
    //                                        int commentIndex = unit.firstLeadingCommentIndex(statement);
    //                                        LineComment mutIDCommentNode;
    //                                        String mutID = null;
    //                                        int lastCommentIndex = unit.lastTrailingCommentIndex(statement);
    //                                        while (commentIndex <= lastCommentIndex) {
    //                                            mutIDCommentNode = ((LineComment) unit.getCommentList().get(commentIndex));
    //                                            mutID = source.substring(mutIDCommentNode.getStartPosition(), mutIDCommentNode.getStartPosition() + mutIDCommentNode.getLength());
    //                                            if (!mutID.contains("mutID")) {
    //                                                ++commentIndex;
    //                                            } else {
    //                                                mutIDNumber = Integer.valueOf(mutID.substring(8));
    //                                                break;
    //                                            }
    //                                        }
    //                                        if (commentIndex > lastCommentIndex) {
    //                                            curMutableLine++;
    //                                            continue;
    //                                        }
    //
    //                                        if (rhsExpressions.containsKey(mutIDNumber) 
    //                                                && rhsExpressions.get(mutIDNumber).getRight() != null 
    //                                                && rhsExpressions.get(mutIDNumber).getRight().getRight() != null) {
    //                                            rhsExpressions.get(mutIDNumber).getRight().getRight().add(expression);
    //                                        } else {
    //                                            String mutGenLimit = visitor.getLineComment(unit.lastTrailingCommentIndex(statement));
    //                                            if (mutGenLimit.contains("mutGenLimit 0")) {
    //                                                stillFatherable = false;
    //                                            }
    //                                            
    //                                            ITypeBinding binding = expression.resolveTypeBinding();
    //                                            MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>> outerPair = 
    //                                                    rhsExpressions.containsKey(mutIDNumber) ? rhsExpressions.get(mutIDNumber) : 
    //                                                        new MutablePair<MutablePair<ITypeBinding,Boolean>, MutablePair<List<Expression>,List<Expression>>>(
    //                                                                new MutablePair<ITypeBinding, Boolean>(), new MutablePair<List<Expression>, List<Expression>>());
    //                                            MutablePair<List<Expression>, List<Expression>> expressionsPair = outerPair.getRight() == null ? 
    //                                                    new MutablePair<List<Expression>, List<Expression>>() : outerPair.getRight();
    //                                            MutablePair<ITypeBinding, Boolean> bindingPair = outerPair.getLeft() == null ? new MutablePair<ITypeBinding, Boolean>() : outerPair.getLeft();
    //                                            outerPair.setLeft(bindingPair);
    //                                            outerPair.setRight(expressionsPair);
    //                                            List<Expression> expressions = expressionsPair.getRight();
    //                                            if (expressions == null) { 
    //                                                expressions = Lists.newArrayList();
    //                                                expressionsPair.setRight(expressions);
    //                                            }
    //                                            expressions.add(expression);
    //                                            bindingPair.setLeft(binding);
    //                                            bindingPair.setRight(stillFatherable);
    //                                            rhsExpressions.put(curMutableLine, outerPair);
    //                                            curMutableLine++;
    //                                        }
    //                                    }
    //                                } else if (statement instanceof ReturnStatement 
    //                                        && unit.lastTrailingCommentIndex(statement) >= 0
    //                                        && unit.firstLeadingCommentIndex(statement) >= 0) {
    //                                    //return !result; //mutgenlimit 1
    //
    //                                    //Es una asignacion
    //
    //                                    //Tomar el id de mutante
    //                                    int commentIndex = unit.firstLeadingCommentIndex(statement);
    //                                    LineComment mutIDCommentNode;
    //                                    String mutID = null;
    //                                    int lastCommentIndex = unit.lastTrailingCommentIndex(statement);
    //                                    while (commentIndex <= lastCommentIndex) {
    //                                        mutIDCommentNode = ((LineComment) unit.getCommentList().get(commentIndex));
    //                                        mutID = source.substring(mutIDCommentNode.getStartPosition(), mutIDCommentNode.getStartPosition() + mutIDCommentNode.getLength());
    //                                        if (!mutID.contains("mutID")) {
    //                                            ++commentIndex;
    //                                        } else {
    //                                            mutIDNumber = Integer.valueOf(mutID.substring(8));
    //                                            break;
    //                                        }
    //                                    }
    //                                    if (commentIndex > lastCommentIndex) {
    //                                        curMutableLine++;
    //                                        continue;
    //                                    }
    //
    //                                    Expression expression = ((ReturnStatement) statement).getExpression();
    //                                    if (rhsExpressions.containsKey(mutIDNumber) 
    //                                            && rhsExpressions.get(mutIDNumber).getRight() != null 
    //                                            && rhsExpressions.get(mutIDNumber).getRight().getRight() != null) {
    //                                        rhsExpressions.get(mutIDNumber).getRight().getRight().add(expression);
    //                                    } else {
    //                                        String mutGenLimit = visitor.getLineComment(unit.lastTrailingCommentIndex(statement));
    //                                        if (mutGenLimit.contains("mutGenLimit 0")) {
    //                                            stillFatherable = false;
    //                                        }
    //                                        
    //                                        ITypeBinding binding = expression.resolveTypeBinding();
    //                                        MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>> outerPair = 
    //                                                rhsExpressions.containsKey(mutIDNumber) ? rhsExpressions.get(mutIDNumber) : 
    //                                                    new MutablePair<MutablePair<ITypeBinding,Boolean>, MutablePair<List<Expression>,List<Expression>>>(
    //                                                            new MutablePair<ITypeBinding, Boolean>(), new MutablePair<List<Expression>, List<Expression>>());
    //                                        MutablePair<List<Expression>, List<Expression>> expressionsPair = outerPair.getRight() == null ? 
    //                                                new MutablePair<List<Expression>, List<Expression>>() : outerPair.getRight();
    //                                        MutablePair<ITypeBinding, Boolean> bindingPair = outerPair.getLeft() == null ? new MutablePair<ITypeBinding, Boolean>() : outerPair.getLeft();
    //                                        outerPair.setLeft(bindingPair);
    //                                        outerPair.setRight(expressionsPair);
    //                                        List<Expression> expressions = expressionsPair.getRight();
    //                                        if (expressions == null) { 
    //                                            expressions = Lists.newArrayList();
    //                                            expressionsPair.setRight(expressions);
    //                                        }
    //                                        expressions.add(expression);
    //                                        bindingPair.setLeft(binding);
    //                                        bindingPair.setRight(stillFatherable);
    //                                        rhsExpressions.put(curMutableLine, outerPair);
    //                                        curMutableLine++;
    //                                    }
    //                                }
    //                            }
    //                            break;
    //                        }
    //                    }
    //                }
    //            }
    //        }
    //
    //        return new VariablizationData(source, unit, method, rhsExpressions);       
    //    }

    public boolean variablizeNext(final DarwinistInput darwinistInput) {

        String variablizedFilename = darwinistInput.getSeqVariablizedFilename();
        if (variablizedFilename == null) {
            variablizedFilename = darwinistInput.getSeqFilesPrefix();
            darwinistInput.setSeqVariablizedFilename(variablizedFilename);
        }

        Integer previousVar = getLastVarNumber();

        final IDocument document = new Document(getSource());

        String varPrefix = "customvar_";
        ASTRewrite rewrite = getRewrite();

        Map<Integer, MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>>> expressions = getExpressions();
        List<Integer> mutIDs = Lists.newArrayList(expressions.keySet());

        int curVariablizationIndex;
        boolean right = true;

        if ((!isLastVariablizedMutIDRight() || expressions.get(mutIDs.get(expressions.size() - 1 - getLastVariablizedIndex())).getRight().getLeft() == null)) {
            if (getLastVariablizedIndex() + 1 == getExpressions().size()) {
                return false;
            } else {
                curVariablizationIndex = getLastVariablizedIndex() + 1;
            }
        } else {
            curVariablizationIndex = getLastVariablizedIndex();
            right = false;
        }

        AST ast = getUnit().getAST();
        MethodDeclaration method = getMethod();

        MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>> curMut = expressions.get(mutIDs.get(expressions.size() - 1 - curVariablizationIndex));
        ITypeBinding binding = curMut.getLeft().getLeft();
        List<Expression> expressionsToVariablize = right ? curMut.getRight().getRight() : curMut.getRight().getLeft();

        for (Expression expression : expressionsToVariablize) {
            //Generamos un nuevo nombre de variable en funcion de los ya asignados
            String variableName = varPrefix + previousVar;
            //Debo reemplazar la RHS por una variable del mismo tipo
            //Dicha variable hay que agregarla como argumento al metodo
            //Nueva variable:
            SingleVariableDeclaration variableDeclaration = ast.newSingleVariableDeclaration();
            //Tipo de la asignacion
            Type assignmentType = typeFromBinding(ast, binding);
            //Seteamos el tipo
            variableDeclaration.setType(assignmentType);
            //Seteamos el nombre de la variable
            SimpleName variableSimpleName = ast.newSimpleName(variableName);
            variableDeclaration.setName(variableSimpleName);
            //Agregamos la nueva variable a los parametros del metodo
            ListRewrite lr = rewrite.getListRewrite(method, MethodDeclaration.PARAMETERS_PROPERTY);
            lr.insertLast(variableDeclaration, null);
            //Reemplazamos la parte derecha de la asignacion por la nueva variable
            rewrite.replace(expression, variableSimpleName, null);
            previousVar++;
        }

        setLastVarNumber(previousVar);

        setLastVariablizedIndex(curVariablizationIndex);

        setLastVariablizedMutIDRight(right);

        //Reescribimos el archivo con los metodos secuenciales que fallaron pero variabilizados
        final TextEdit edits = rewrite.rewriteAST(document, null);
        try {
            edits.apply(document);
        } catch (MalformedTreeException | BadLocationException e) {
            // TODO: Define what to do!
        }
        try {
            FileUtils.writeToFile(variablizedFilename, document.get());
            System.out.println("Current variabilization filename is: " + variablizedFilename);
        } catch (final IOException e) {
            // TODO: Define what to do!
        }

        setStillFatherable(curMut.getLeft().getRight());
        setLastVariablizedMutID(mutIDs.get(expressions.size() - 1 - curVariablizationIndex));

        return true;
    }

    public Type typeFromBinding(AST ast, ITypeBinding typeBinding) {
        if( ast == null ) 
            throw new NullPointerException("ast is null");
        if( typeBinding == null )
            throw new NullPointerException("typeBinding is null");

        if( typeBinding.isPrimitive() ) {
            return ast.newPrimitiveType(
                    PrimitiveType.toCode(typeBinding.getName()));
        }

        if( typeBinding.isCapture() ) {
            ITypeBinding wildCard = typeBinding.getWildcard();
            WildcardType capType = ast.newWildcardType();
            capType.setBound(typeFromBinding(ast, wildCard.getBound()),
                    wildCard.isUpperbound());
            return capType;
        }

        if( typeBinding.isArray() ) {
            Type elType = typeFromBinding(ast, typeBinding.getElementType());
            return ast.newArrayType(elType, typeBinding.getDimensions());
        }

        if( typeBinding.isParameterizedType() ) {
            ParameterizedType type = ast.newParameterizedType(
                    typeFromBinding(ast, typeBinding.getErasure()));

            @SuppressWarnings("unchecked")
            List<Type> newTypeArgs = type.typeArguments();
            for( ITypeBinding typeArg : typeBinding.getTypeArguments() ) {
                newTypeArgs.add(typeFromBinding(ast, typeArg));
            }

            return type;
        }

        // simple or raw type
        String qualName = typeBinding.getQualifiedName();
        if( "".equals(qualName) ) {
            throw new IllegalArgumentException("No name for type binding.");
        }

        SimpleType ret = null;
        try {
            ret = ast.newSimpleType(ast.newName(qualName));
        } catch (Exception e) {
            System.out.println(e);
        }
        return ret; 
    }
}