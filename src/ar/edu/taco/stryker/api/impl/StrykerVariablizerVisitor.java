package ar.edu.taco.stryker.api.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.MutablePair;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class StrykerVariablizerVisitor extends ASTVisitor {

    private final CompilationUnit unit;
    private final String source;
    private final AST ast;
    private final String seqFileName;
    private String methodName;
    private final ASTRewrite rewrite;
    private final Set<ASTNode> customNodes = Sets.newHashSet();
    private int nextMutID;
    private List<Integer> lastMutatedLines;
    private static final String mutIDCommentPrefix = "mutID ";
    
    private Integer curMutableLine = 0;
    private boolean stillFatherable = true;
    private MethodDeclaration method = null;
    private Map<Integer, MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>>> rhsExpressions = Maps.newTreeMap();
    
    public StrykerVariablizerVisitor(final OpenJMLInputWrapper wrapper, CompilationUnit unit, String source, final AST ast, String seqFileName, List<Integer> lastMutatedLines) {
        super();
        this.unit = unit;
        this.source = source;
        this.ast = ast;
        this.seqFileName = seqFileName;
        this.rewrite = ASTRewrite.create(ast);
        this.lastMutatedLines = lastMutatedLines;
    }

    public void setNextMutID(int nextMutID) {
        this.nextMutID = nextMutID;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public static int getLineNumber(CompilationUnit compilationUnit, ASTNode node) {
        return compilationUnit.getLineNumber(node.getStartPosition()) - 1;
    }

    public String getLineComment(int commentIndex) {
        LineComment lineCommentNode;
        //Tiene comentario el statement, potencialmente sea el de mutacion que necesito
        String lineComment = "";
        boolean flag = true;
        while (flag) {
            lineCommentNode = ((LineComment) unit.getCommentList().get(commentIndex));
            lineComment = source.substring(lineCommentNode.getStartPosition(), 
                    lineCommentNode.getStartPosition() + lineCommentNode.getLength());
            if (!lineComment.contains("mutGenLimit")) {
                --commentIndex;
            } else {
                break;
            }
        }
        lineComment += '\n';
        return lineComment;
    }
    
    public VariablizationData buildVariablizationData() {
        return new VariablizationData(source, unit, method, rhsExpressions); 
    }
    
    public void processReturnNode(ReturnStatement statement) {

//        String mutIDComment = getLineComment(unit.lastTrailingCommentIndex(statement));
//        
//        int mutIDIndex = mutIDComment.indexOf("mutID") + 6;
//        String mutIDString = mutIDComment.substring(mutIDIndex, mutIDComment.length() - 1);
//        
//        Integer mutIDNumber = Integer.valueOf(mutIDString);
//
//        Expression expression = statement.getExpression();
//        if (rhsExpressions.containsKey(mutIDNumber) 
//                && rhsExpressions.get(mutIDNumber).getRight() != null 
//                && rhsExpressions.get(mutIDNumber).getRight().getRight() != null) {
//            rhsExpressions.get(mutIDNumber).getRight().getRight().add(expression);
//        } else {
//            String mutGenLimit = getLineComment(unit.lastTrailingCommentIndex(statement));
//            if (mutGenLimit.contains("mutGenLimit 0")) {
//                stillFatherable = false;
//            }
//
//            ITypeBinding binding = expression.resolveTypeBinding();
//            MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>> outerPair = 
//                    rhsExpressions.containsKey(mutIDNumber) ? rhsExpressions.get(mutIDNumber) : 
//                        new MutablePair<MutablePair<ITypeBinding,Boolean>, MutablePair<List<Expression>,List<Expression>>>(
//                                new MutablePair<ITypeBinding, Boolean>(), new MutablePair<List<Expression>, List<Expression>>());
//                    MutablePair<List<Expression>, List<Expression>> expressionsPair = outerPair.getRight() == null ? 
//                            new MutablePair<List<Expression>, List<Expression>>() : outerPair.getRight();
//                            MutablePair<ITypeBinding, Boolean> bindingPair = outerPair.getLeft() == null ? new MutablePair<ITypeBinding, Boolean>() : outerPair.getLeft();
//                            outerPair.setLeft(bindingPair);
//                            outerPair.setRight(expressionsPair);
//                            List<Expression> expressions = expressionsPair.getRight();
//                            if (expressions == null) { 
//                                expressions = Lists.newArrayList();
//                                expressionsPair.setRight(expressions);
//                            }
//                            expressions.add(expression);
//                            bindingPair.setLeft(binding);
//                            bindingPair.setRight(stillFatherable);
//                            rhsExpressions.put(curMutableLine, outerPair);
//                            curMutableLine++;
//        }
    }
    
    public void processNode(Statement statement) {
        String mutIDComment = getLineComment(unit.lastTrailingCommentIndex(statement));
        
        int mutIDIndex = mutIDComment.indexOf("mutID") + 6;
        String mutIDString = mutIDComment.substring(mutIDIndex, mutIDComment.length() - 1);
        
        Integer mutIDNumber = Integer.valueOf(mutIDString);

        // to iterate through methods
        //Es expression statement y tiene comentario
        Expression expression = ((ExpressionStatement) statement).getExpression();
        if (expression instanceof Assignment) {
            //Es una asignacion
            //Tomar el id de mutante

            Assignment assignment = (Assignment) expression;

            ///LHS de la asignacion
            Expression lhs = assignment.getLeftHandSide();
            if (lhs instanceof FieldAccess /*&& !visitor.getLineComment(unit.lastTrailingCommentIndex(statement)).contains("mutGenLimit 1")*/) {
                //Es un FieldAccess, se variabiliza para PRVOL
                if (rhsExpressions.containsKey(mutIDNumber) 
                        && rhsExpressions.get(mutIDNumber).getRight() != null 
                        && rhsExpressions.get(mutIDNumber).getRight().getLeft() != null) {
                    rhsExpressions.get(mutIDNumber).getRight().getLeft().add(lhs);
                } else {
                    String mutGenLimit = getLineComment(unit.lastTrailingCommentIndex(statement));
                    if (mutGenLimit.contains("mutGenLimit 0")) {
                        stillFatherable = false;
                    }

                    ITypeBinding binding = assignment.resolveTypeBinding();
                    MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>> outerPair = 
                            rhsExpressions.containsKey(mutIDNumber) ? rhsExpressions.get(mutIDNumber) : 
                                new MutablePair<MutablePair<ITypeBinding,Boolean>, MutablePair<List<Expression>,List<Expression>>>(
                                        new MutablePair<ITypeBinding, Boolean>(), new MutablePair<List<Expression>, List<Expression>>());
                            MutablePair<List<Expression>, List<Expression>> expressionsPair = outerPair.getRight() == null ? 
                                    new MutablePair<List<Expression>, List<Expression>>() : outerPair.getRight();
                                    MutablePair<ITypeBinding, Boolean> bindingPair = outerPair.getLeft() == null ? new MutablePair<ITypeBinding, Boolean>() : outerPair.getLeft();
                                    outerPair.setLeft(bindingPair);
                                    outerPair.setRight(expressionsPair);
                                    List<Expression> expressions = expressionsPair.getLeft();
                                    if (expressions == null) { 
                                        expressions = Lists.newArrayList();
                                        expressionsPair.setLeft(expressions);
                                    }
                                    expressions.add(lhs);
                                    bindingPair.setLeft(binding);
                                    bindingPair.setRight(stillFatherable);
                                    rhsExpressions.put(curMutableLine, outerPair);
                                    curMutableLine++;
                }
            }

            ///RHS de la asignacion
            Expression rhs = assignment.getRightHandSide();

            if (rhsExpressions.containsKey(mutIDNumber) 
                    && rhsExpressions.get(mutIDNumber).getRight() != null 
                    && rhsExpressions.get(mutIDNumber).getRight().getRight() != null) {
                rhsExpressions.get(mutIDNumber).getRight().getRight().add(rhs);
            } else {
                String mutGenLimit = getLineComment(unit.lastTrailingCommentIndex(statement));
                if (mutGenLimit.contains("mutGenLimit 0")) {
                    stillFatherable = false;
                }

                ITypeBinding binding = assignment.resolveTypeBinding();
                MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>> outerPair = 
                        rhsExpressions.containsKey(mutIDNumber) ? rhsExpressions.get(mutIDNumber) : 
                            new MutablePair<MutablePair<ITypeBinding,Boolean>, MutablePair<List<Expression>,List<Expression>>>(
                                    new MutablePair<ITypeBinding, Boolean>(), new MutablePair<List<Expression>, List<Expression>>());
                        MutablePair<List<Expression>, List<Expression>> expressionsPair = outerPair.getRight() == null ? 
                                new MutablePair<List<Expression>, List<Expression>>() : outerPair.getRight();
                                MutablePair<ITypeBinding, Boolean> bindingPair = outerPair.getLeft() == null ? new MutablePair<ITypeBinding, Boolean>() : outerPair.getLeft();
                                outerPair.setLeft(bindingPair);
                                outerPair.setRight(expressionsPair);
                                List<Expression> expressions = expressionsPair.getRight();
                                if (expressions == null) { 
                                    expressions = Lists.newArrayList();
                                    expressionsPair.setRight(expressions);
                                }
                                expressions.add(rhs);
                                bindingPair.setLeft(binding);
                                bindingPair.setRight(stillFatherable);
                                rhsExpressions.put(curMutableLine, outerPair);
                                curMutableLine++;
            }

        } else if (expression instanceof PostfixExpression) {
            //Tomar el id de mutante

            if (rhsExpressions.containsKey(mutIDNumber) 
                    && rhsExpressions.get(mutIDNumber).getRight() != null 
                    && rhsExpressions.get(mutIDNumber).getRight().getRight() != null) {
                rhsExpressions.get(mutIDNumber).getRight().getRight().add(expression);
            } else {
                String mutGenLimit = getLineComment(unit.lastTrailingCommentIndex(statement));
                if (mutGenLimit.contains("mutGenLimit 0")) {
                    stillFatherable = false;
                }

                ITypeBinding binding = expression.resolveTypeBinding();
                MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>> outerPair = 
                        rhsExpressions.containsKey(mutIDNumber) ? rhsExpressions.get(mutIDNumber) : 
                            new MutablePair<MutablePair<ITypeBinding,Boolean>, MutablePair<List<Expression>,List<Expression>>>(
                                    new MutablePair<ITypeBinding, Boolean>(), new MutablePair<List<Expression>, List<Expression>>());
                        MutablePair<List<Expression>, List<Expression>> expressionsPair = outerPair.getRight() == null ? 
                                new MutablePair<List<Expression>, List<Expression>>() : outerPair.getRight();
                                MutablePair<ITypeBinding, Boolean> bindingPair = outerPair.getLeft() == null ? new MutablePair<ITypeBinding, Boolean>() : outerPair.getLeft();
                                outerPair.setLeft(bindingPair);
                                outerPair.setRight(expressionsPair);
                                List<Expression> expressions = expressionsPair.getRight();
                                if (expressions == null) { 
                                    expressions = Lists.newArrayList();
                                    expressionsPair.setRight(expressions);
                                }
                                expressions.add(expression);
                                bindingPair.setLeft(binding);
                                bindingPair.setRight(stillFatherable);
                                rhsExpressions.put(curMutableLine, outerPair);
                                curMutableLine++;
            }
        } else if (expression instanceof PrefixExpression) {
            //Tomar el id de mutante

            if (rhsExpressions.containsKey(mutIDNumber) 
                    && rhsExpressions.get(mutIDNumber).getRight() != null 
                    && rhsExpressions.get(mutIDNumber).getRight().getRight() != null) {
                rhsExpressions.get(mutIDNumber).getRight().getRight().add(expression);
            } else {
                String mutGenLimit = getLineComment(unit.lastTrailingCommentIndex(statement));
                if (mutGenLimit.contains("mutGenLimit 0")) {
                    stillFatherable = false;
                }

                ITypeBinding binding = expression.resolveTypeBinding();
                MutablePair<MutablePair<ITypeBinding, Boolean>, MutablePair<List<Expression>, List<Expression>>> outerPair = 
                        rhsExpressions.containsKey(mutIDNumber) ? rhsExpressions.get(mutIDNumber) : 
                            new MutablePair<MutablePair<ITypeBinding,Boolean>, MutablePair<List<Expression>,List<Expression>>>(
                                    new MutablePair<ITypeBinding, Boolean>(), new MutablePair<List<Expression>, List<Expression>>());
                        MutablePair<List<Expression>, List<Expression>> expressionsPair = outerPair.getRight() == null ? 
                                new MutablePair<List<Expression>, List<Expression>>() : outerPair.getRight();
                                MutablePair<ITypeBinding, Boolean> bindingPair = outerPair.getLeft() == null ? new MutablePair<ITypeBinding, Boolean>() : outerPair.getLeft();
                                outerPair.setLeft(bindingPair);
                                outerPair.setRight(expressionsPair);
                                List<Expression> expressions = expressionsPair.getRight();
                                if (expressions == null) { 
                                    expressions = Lists.newArrayList();
                                    expressionsPair.setRight(expressions);
                                }
                                expressions.add(expression);
                                bindingPair.setLeft(binding);
                                bindingPair.setRight(stillFatherable);
                                rhsExpressions.put(curMutableLine, outerPair);
                                curMutableLine++;
            }
        }
    }

    @Override
    public boolean preVisit2(ASTNode node) {
        if (node instanceof Statement 
                && !(node instanceof Block) 
                && !(node instanceof IfStatement) 
                && !(node instanceof WhileStatement)
                && !(node instanceof ForStatement)
                && !(node instanceof EnhancedForStatement)
                && !(node instanceof ReturnStatement)
                && !customNodes.contains(node)) {
            int commentIndex = unit.lastTrailingCommentIndex(node);
            if (commentIndex >= 0) {
                String mutGenLimitComment = getLineComment(commentIndex);
                if (!mutGenLimitComment.contains("mutID")) {
                    if (mutGenLimitComment.contains("//mutGenLimit")) {
                        ++curMutableLine;
                    }
                } else {
                    processNode((Statement)node);
                }
            }

            return false;
        } else if (node instanceof IfStatement){

            //Ojo con los replace, no se si al reemplazar un padre, y luego reemplazar el hijo, si el hijo sera reemplazado o no, 
            // porque en el rewrite ya se cambio ese hijo por una copia, entonces quizáz no lo encuentra.

            Statement thenStatement = ((IfStatement) node).getThenStatement();

            Statement elseStatement = ((IfStatement) node).getElseStatement();

            if (thenStatement instanceof Block) {
                Statement thenFirstStatement = (Statement)((Block)thenStatement).statements().get(0);
                if (!(thenFirstStatement instanceof IfStatement)
                        && !(thenFirstStatement instanceof WhileStatement)
                        && !(thenFirstStatement instanceof ForStatement)
                        && !(thenFirstStatement instanceof EnhancedForStatement)) {
                    customNodes.add(thenFirstStatement);
                    int commentIndex = unit.lastTrailingCommentIndex(thenFirstStatement);
                    if (commentIndex >= 0) {
                        String mutGenLimitComment = getLineComment(commentIndex);
                        if (!mutGenLimitComment.contains("mutID")) {
                            if (mutGenLimitComment.contains("//mutGenLimit")) {
                                ++curMutableLine;
                            }
                        } else {
                            processNode(thenFirstStatement);
                        }
                    }
                }
            } else if (!(thenStatement instanceof IfStatement)
                    && !(thenStatement instanceof WhileStatement)
                    && !(thenStatement instanceof ForStatement)
                    && !(thenStatement instanceof EnhancedForStatement)) {
                customNodes.add(thenStatement);
                int commentIndex = unit.lastTrailingCommentIndex(thenStatement);
                if (commentIndex >= 0) {
                    String mutGenLimitComment = getLineComment(commentIndex);
                    if (!mutGenLimitComment.contains("mutID")) {
                        if (mutGenLimitComment.contains("//mutGenLimit")) {
                            ++curMutableLine;
                        }
                    } else {
                        processNode(thenStatement);
                    }
                }
            } else {
                customNodes.add(thenStatement);
            }

            if (elseStatement != null) {
                if (elseStatement instanceof Block) {
                    Statement elseFirstStatement = (Statement)((Block)elseStatement).statements().get(0);

                    if (!(elseFirstStatement instanceof IfStatement)
                            && !(elseFirstStatement instanceof WhileStatement)
                            && !(elseFirstStatement instanceof ForStatement)
                            && !(elseFirstStatement instanceof EnhancedForStatement)) {
                        customNodes.add(elseFirstStatement);
                        int commentIndex = unit.lastTrailingCommentIndex(elseFirstStatement);
                        if (commentIndex >= 0) {
                            String mutGenLimitComment = getLineComment(commentIndex);
                            if (!mutGenLimitComment.contains("mutID")) {
                                if (mutGenLimitComment.contains("//mutGenLimit")) {
                                    ++curMutableLine;
                                }
                            } else {
                                processNode(elseFirstStatement);
                            }
                        }
                    }
                } else if (!(elseStatement instanceof IfStatement)
                        && !(elseStatement instanceof WhileStatement)
                        && !(elseStatement instanceof ForStatement)
                        && !(elseStatement instanceof EnhancedForStatement)) {
                    customNodes.add(elseStatement);
                    int commentIndex = unit.lastTrailingCommentIndex(elseStatement);
                    if (commentIndex >= 0) {
                        String mutGenLimitComment = getLineComment(commentIndex);
                        if (!mutGenLimitComment.contains("mutID")) {
                            if (mutGenLimitComment.contains("//mutGenLimit")) {
                                ++curMutableLine;
                            }
                        } else {
                            processNode(elseStatement);
                        }
                    }
                } else {
                    customNodes.add(elseStatement);
                }
            }

            return true;
        } else if (node instanceof WhileStatement) {

            Statement whileBody = ((WhileStatement) node).getBody();

            if (whileBody instanceof Block) {
                Statement whileBodyFirstStatement = (Statement)((Block)whileBody).statements().get(0);
                if (!(whileBodyFirstStatement instanceof IfStatement)
                        && !(whileBodyFirstStatement instanceof WhileStatement)
                        && !(whileBodyFirstStatement instanceof ForStatement)
                        && !(whileBodyFirstStatement instanceof EnhancedForStatement)) {
                    customNodes.add(whileBodyFirstStatement);
                    int commentIndex = unit.lastTrailingCommentIndex(whileBodyFirstStatement);
                    if (commentIndex >= 0) {
                        String mutGenLimitComment = getLineComment(commentIndex);
                        if (!mutGenLimitComment.contains("mutID")) {
                            if (mutGenLimitComment.contains("//mutGenLimit")) {
                                ++curMutableLine;
                            }
                        } else {
                            processNode(whileBodyFirstStatement);
                        }
                    }
                }
            } else if (!(whileBody instanceof IfStatement)
                    && !(whileBody instanceof WhileStatement)
                    && !(whileBody instanceof ForStatement)
                    && !(whileBody instanceof EnhancedForStatement)) {
                customNodes.add(whileBody);
                int commentIndex = unit.lastTrailingCommentIndex(whileBody);
                if (commentIndex >= 0) {
                    String mutGenLimitComment = getLineComment(commentIndex);
                    if (!mutGenLimitComment.contains("mutID")) {
                        if (mutGenLimitComment.contains("//mutGenLimit")) {
                            ++curMutableLine;
                        }
                    } else {
                        processNode(whileBody);
                    }                }
            } else {
                customNodes.add(whileBody);
            }

            return true;
        } else if (node instanceof ForStatement) {
            Statement forBody = ((ForStatement) node).getBody();

            if (forBody instanceof Block) {
                Statement forBodyFirstStatement = (Statement)((Block)forBody).statements().get(0);
                if (!(forBodyFirstStatement instanceof IfStatement)
                        && !(forBodyFirstStatement instanceof WhileStatement)
                        && !(forBodyFirstStatement instanceof ForStatement)
                        && !(forBodyFirstStatement instanceof EnhancedForStatement)) {
                    customNodes.add(forBodyFirstStatement);
                    int commentIndex = unit.lastTrailingCommentIndex(forBodyFirstStatement);
                    if (commentIndex >= 0) {
                        String mutGenLimitComment = getLineComment(commentIndex);
                        if (!mutGenLimitComment.contains("mutID")) {
                            if (mutGenLimitComment.contains("//mutGenLimit")) {
                                ++curMutableLine;
                            }
                        } else {
                            processNode(forBodyFirstStatement);
                        }
                    }
                }
            } else if (!(forBody instanceof IfStatement)
                    && !(forBody instanceof WhileStatement)
                    && !(forBody instanceof ForStatement)
                    && !(forBody instanceof EnhancedForStatement)) {
                customNodes.add(forBody);
                int commentIndex = unit.lastTrailingCommentIndex(forBody);
                if (commentIndex >= 0) {
                    String mutGenLimitComment = getLineComment(commentIndex);
                    if (!mutGenLimitComment.contains("mutID")) {
                        if (mutGenLimitComment.contains("//mutGenLimit")) {
                            ++curMutableLine;
                        }
                    } else {
                        processNode(forBody);
                    }                }
            } else {
                customNodes.add(forBody);
            }

            return true;
        } else if (node instanceof EnhancedForStatement) {
            Statement forBody = ((EnhancedForStatement) node).getBody();

            if (forBody instanceof Block) {
                Statement forBodyFirstStatement = (Statement)((Block)forBody).statements().get(0);
                if (!(forBodyFirstStatement instanceof IfStatement)
                        && !(forBodyFirstStatement instanceof WhileStatement)
                        && !(forBodyFirstStatement instanceof ForStatement)
                        && !(forBodyFirstStatement instanceof EnhancedForStatement)) {
                    customNodes.add(forBodyFirstStatement);
                    int commentIndex = unit.lastTrailingCommentIndex(forBodyFirstStatement);
                    if (commentIndex >= 0) {
                        String mutGenLimitComment = getLineComment(commentIndex);
                        if (!mutGenLimitComment.contains("mutID")) {
                            if (mutGenLimitComment.contains("//mutGenLimit")) {
                                ++curMutableLine;
                            }
                        } else {
                            processNode(forBodyFirstStatement);
                        }                    }
                }
            } else if (!(forBody instanceof IfStatement)
                    && !(forBody instanceof WhileStatement)
                    && !(forBody instanceof ForStatement)
                    && !(forBody instanceof EnhancedForStatement)) {
                customNodes.add(forBody);
                int commentIndex = unit.lastTrailingCommentIndex(forBody);
                if (commentIndex >= 0) {
                    String mutGenLimitComment = getLineComment(commentIndex);
                    if (!mutGenLimitComment.contains("mutID")) {
                        if (mutGenLimitComment.contains("//mutGenLimit")) {
                            ++curMutableLine;
                        }
                    } else {
                        processNode(forBody);
                    }
                }
            } else {
                customNodes.add(forBody);
            }

            return true;
        } else if (node instanceof ReturnStatement) {
            int commentIndex = unit.lastTrailingCommentIndex(node);
            if (commentIndex >= 0) {
                String mutGenLimitComment = getLineComment(commentIndex);
                if (!mutGenLimitComment.contains("mutID")) {
                    if (mutGenLimitComment.contains("//mutGenLimit")) {
                        ++curMutableLine;
                    }
                } else {
                    processReturnNode((ReturnStatement)node);
                }
            }        

            return true;
        } else if (node instanceof MethodDeclaration) {
            MethodDeclaration newNode = (MethodDeclaration) node;

            if (newNode.getName().getFullyQualifiedName().contains(methodName)) {
                @SuppressWarnings("rawtypes")
                List statements = newNode.getBody().statements();

                Statement firstStatement = (Statement) statements.get(0);

                customNodes.add(firstStatement);

                Statement lastStatement = (Statement) statements.get(statements.size() - 1);

                customNodes.add(lastStatement);
                
                this.method = newNode;

                return true;
            } else {
                return super.preVisit2(node);
            }
        }

        return super.preVisit2(node);
    }

    public ASTRewrite getRewrite() {
        return rewrite;
    }

}
