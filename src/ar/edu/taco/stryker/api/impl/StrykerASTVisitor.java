package ar.edu.taco.stryker.api.impl;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;

import com.google.common.collect.Sets;

public final class StrykerASTVisitor extends ASTVisitor {

    private final OpenJMLInputWrapper wrapper;
    private final AST ast;
    private final String seqFileName;
    private final ASTRewrite rewrite;
    private final Set<ASTNode> customNodes = Sets.newHashSet();

    public StrykerASTVisitor(final OpenJMLInputWrapper wrapper, final AST ast, String seqFileName) {
        super();
        this.wrapper = wrapper;
        this.ast = ast;
        this.seqFileName = seqFileName;
        this.rewrite = ASTRewrite.create(ast);
    }

    //    @Override
    //    public void endVisit(BreakStatement node) {
    //        System.out.println("---------------------------------------------------------------------");
    //        System.out.println("Este es un BreakStatement:");
    //        System.out.println(node);
    //        System.out.println("---------------------------------------------------------------------");
    //        super.endVisit(node);
    //    }
    //    
    //    @Override
    //    public void endVisit(ContinueStatement node) {
    //        System.out.println("---------------------------------------------------------------------");
    //        System.out.println("Este es un ContinueStatement:");
    //        System.out.println(node);
    //        System.out.println("---------------------------------------------------------------------");
    //        super.endVisit(node);
    //    }
    //    
    //    @Override
    //    public void endVisit(DoStatement node) {
    //        System.out.println("---------------------------------------------------------------------");
    //        System.out.println("Este es un DoStatement:");
    //        System.out.println(node);
    //        System.out.println("---------------------------------------------------------------------");
    //        super.endVisit(node);
    //    }
    //    
    //    @Override
    //    public void endVisit(LabeledStatement node) {
    //        System.out.println("---------------------------------------------------------------------");
    //        System.out.println("Este es un LabeledStatement:");
    //        System.out.println(node);
    //        System.out.println("---------------------------------------------------------------------");
    //        super.endVisit(node);
    //    }
    //    
    //    @Override
    //    public void endVisit(SwitchCase node) {
    //        System.out.println("---------------------------------------------------------------------");
    //        System.out.println("Este es un SwitchCase:");
    //        System.out.println(node);
    //        System.out.println("---------------------------------------------------------------------");
    //        super.endVisit(node);
    //    }
    //    
    //    @Override
    //    public void endVisit(SwitchStatement node) {
    //        System.out.println("---------------------------------------------------------------------");
    //        System.out.println("Este es un SwitchStatement:");
    //        System.out.println(node);
    //        System.out.println("---------------------------------------------------------------------");
    //        super.endVisit(node);
    //    }
    //    
    //    @Override
    //    public void endVisit(SynchronizedStatement node) {
    //        System.out.println("---------------------------------------------------------------------");
    //        System.out.println("Este es un SynchronizedStatement:");
    //        System.out.println(node);
    //        System.out.println("---------------------------------------------------------------------");
    //        super.endVisit(node);
    //    }
    //    
    //    @Override
    //    public void endVisit(ThrowStatement node) {
    //        System.out.println("---------------------------------------------------------------------");
    //        System.out.println("Este es un ThrowStatement:");
    //        System.out.println(node);
    //        System.out.println("---------------------------------------------------------------------");
    //        super.endVisit(node);
    //    }
    //    
    //    @Override
    //    public void endVisit(TryStatement node) {
    //        System.out.println("---------------------------------------------------------------------");
    //        System.out.println("Este es un TryStatement:");
    //        System.out.println(node);
    //        System.out.println("---------------------------------------------------------------------");
    //        super.endVisit(node);
    //    }
    //    
    //    // Las siguientes no extienden de statement pero cuidado!
    //    
    //    @Override
    //    public void endVisit(AnonymousClassDeclaration node) {
    //        // TODO: Auto-generated method stub
    //        super.endVisit(node);
    //    }
    //    
    //    @Override
    //    public void endVisit(ConditionalExpression node) {
    //        // TODO: Auto-generated method stub
    //        super.endVisit(node);
    //    }

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
            ASTNode newNode = ASTNode.copySubtree(ast, node);

            ASTNode nodes[] = {getWriteToFileExpressionStatement(newNode.toString()), newNode};

            rewrite.replace(node, rewrite.createGroupNode(nodes), null);
            return false;
        } else if (node instanceof IfStatement){

            //Ojo con los replace, no se si al reemplazar un padre, y luego reemplazar el hijo, si el hijo sera reemplazado o no, 
            // porque en el rewrite ya se cambio ese hijo por una copia, entonces quizáz no lo encuentra.

            Expression ifExpression = ((IfStatement) node).getExpression();

            Statement thenStatement = ((IfStatement) node).getThenStatement();

            Statement elseStatement = ((IfStatement) node).getElseStatement();

            ASTNode newThenNode;

            if (thenStatement instanceof Block) {
                Statement thenFirstStatement = (Statement)((Block)thenStatement).statements().get(0);
                if (!(thenFirstStatement instanceof IfStatement)
                        && !(thenFirstStatement instanceof WhileStatement)
                        && !(thenFirstStatement instanceof ForStatement)
                        && !(thenFirstStatement instanceof EnhancedForStatement)) {
                    newThenNode = ASTNode.copySubtree(ast, thenFirstStatement);
                    customNodes.add(thenFirstStatement);
                    ASTNode thenNodes[] = {
                            getWriteToFileExpressionStatement("assert(" + ifExpression.toString() + ");"), 
                            getWriteToFileExpressionStatement(newThenNode.toString()), newThenNode};

                    rewrite.replace(thenFirstStatement, rewrite.createGroupNode(thenNodes), null);
                } else {
                    rewrite.getListRewrite(thenStatement, Block.STATEMENTS_PROPERTY).insertFirst(
                            getWriteToFileExpressionStatement("assert(" + ifExpression.toString() + ");"), null);
                    //return true;
                }
            } else if (!(thenStatement instanceof IfStatement)
                    && !(thenStatement instanceof WhileStatement)
                    && !(thenStatement instanceof ForStatement)
                    && !(thenStatement instanceof EnhancedForStatement)) {
                newThenNode = ASTNode.copySubtree(ast, thenStatement);
                customNodes.add(thenStatement);
                
                ASTNode thenNodes[] = {
                        getWriteToFileExpressionStatement("assert(" + ifExpression.toString() + ");"),
                        getWriteToFileExpressionStatement(newThenNode.toString()), newThenNode};
                rewrite.replace(thenStatement, rewrite.createGroupNode(thenNodes), null);
            } else {
                newThenNode = ASTNode.copySubtree(ast, thenStatement);
                customNodes.add(thenStatement);
                
                ASTNode thenNodes[] = {
                        getWriteToFileExpressionStatement("assert(" + ifExpression.toString() + ");"), newThenNode};
                rewrite.replace(thenStatement, rewrite.createGroupNode(thenNodes), null);
                //return true;
            }

            if (elseStatement != null) {
                ASTNode newElseNode;
                if (elseStatement instanceof Block) {
                    Statement elseFirstStatement = (Statement)((Block)elseStatement).statements().get(0);

                    if (!(elseFirstStatement instanceof IfStatement)
                            && !(elseFirstStatement instanceof WhileStatement)
                            && !(elseFirstStatement instanceof ForStatement)
                            && !(elseFirstStatement instanceof EnhancedForStatement)) {
                        newElseNode = ASTNode.copySubtree(ast, elseFirstStatement);
                        customNodes.add(elseFirstStatement);
                        ASTNode elseNodes[] = {getWriteToFileExpressionStatement("assert(!(" + ifExpression.toString() + "));"), 
                                getWriteToFileExpressionStatement(newElseNode.toString()), newElseNode};

                        rewrite.replace(elseFirstStatement, rewrite.createGroupNode(elseNodes), null);
                    } else {
                        rewrite.getListRewrite(elseStatement, Block.STATEMENTS_PROPERTY).insertFirst(
                                getWriteToFileExpressionStatement("assert(!(" + ifExpression.toString() + "));"), null);
                        //return true;
                    }
                } else if (!(elseStatement instanceof IfStatement)
                        && !(elseStatement instanceof WhileStatement)
                        && !(elseStatement instanceof ForStatement)
                        && !(elseStatement instanceof EnhancedForStatement)) {
                    newElseNode = ASTNode.copySubtree(ast, elseStatement);
                    customNodes.add(elseStatement);
                    
                    ASTNode elseNodes[] = {
                            getWriteToFileExpressionStatement("assert(!(" + ifExpression.toString() + "));"),
                            getWriteToFileExpressionStatement(newElseNode.toString()), newElseNode};
                    rewrite.replace(elseStatement, rewrite.createGroupNode(elseNodes), null);
                } else {
                    newElseNode = ASTNode.copySubtree(ast, elseStatement);
                    customNodes.add(elseStatement);
                    
                    ASTNode elseNodes[] = {
                            getWriteToFileExpressionStatement("assert(!(" + ifExpression.toString() + "));"), newElseNode};
                    rewrite.replace(elseStatement, rewrite.createGroupNode(elseNodes), null);
                    //return true;
                }
            }
            
            return true;
        } else if (node instanceof WhileStatement) {
            Expression whileExpression = ((WhileStatement) node).getExpression();

            Statement whileBody = ((WhileStatement) node).getBody();

            ASTNode newWhileBodyFirstStatementNode;
            
            if (whileBody instanceof Block) {
                Statement whileBodyFirstStatement = (Statement)((Block)whileBody).statements().get(0);
                if (!(whileBodyFirstStatement instanceof IfStatement)
                        && !(whileBodyFirstStatement instanceof WhileStatement)
                        && !(whileBodyFirstStatement instanceof ForStatement)
                        && !(whileBodyFirstStatement instanceof EnhancedForStatement)) {
                    newWhileBodyFirstStatementNode = ASTNode.copySubtree(ast, whileBodyFirstStatement);
                    customNodes.add(whileBodyFirstStatement);
                    ASTNode whileBodyFirstNodes[] = {
                            getWriteToFileExpressionStatement("assert(" + whileExpression.toString() + ");"), 
                            getWriteToFileExpressionStatement(newWhileBodyFirstStatementNode.toString()), 
                            newWhileBodyFirstStatementNode};

                    rewrite.replace(whileBodyFirstStatement, rewrite.createGroupNode(whileBodyFirstNodes), null);
                } else {
                    rewrite.getListRewrite(whileBody, Block.STATEMENTS_PROPERTY).insertFirst(
                            getWriteToFileExpressionStatement("assert(" + whileExpression.toString() + ");"), null);
                    //return true;
                }
            } else if (!(whileBody instanceof IfStatement)
                    && !(whileBody instanceof WhileStatement)
                    && !(whileBody instanceof ForStatement)
                    && !(whileBody instanceof EnhancedForStatement)) {
                newWhileBodyFirstStatementNode = ASTNode.copySubtree(ast, whileBody);
                customNodes.add(whileBody);
                
                ASTNode whileBodyNodes[] = {
                        getWriteToFileExpressionStatement("assert(" + whileExpression.toString() + ");"),
                        getWriteToFileExpressionStatement(newWhileBodyFirstStatementNode.toString()), newWhileBodyFirstStatementNode};
                rewrite.replace(whileExpression, rewrite.createGroupNode(whileBodyNodes), null);
            } else {
                newWhileBodyFirstStatementNode = ASTNode.copySubtree(ast, whileBody);
                customNodes.add(whileBody);
                
                ASTNode whileBodyNodes[] = {
                        getWriteToFileExpressionStatement("assert(" + whileExpression.toString() + ");"), newWhileBodyFirstStatementNode};
                rewrite.replace(whileBody, rewrite.createGroupNode(whileBodyNodes), null);
                //return true;
            }
            
            ASTNode assertNode = getWriteToFileExpressionStatement("assert(!(" + whileExpression.toString() + "));");
            rewrite.getListRewrite(node.getParent(), (ChildListPropertyDescriptor) node.getLocationInParent())
            .insertAfter(assertNode, node, null);
            
            return true;
        } else if (node instanceof ForStatement) {
            Expression forExpression = ((ForStatement) node).getExpression();

            Statement forBody = ((ForStatement) node).getBody();

            ASTNode newForBodyFirstStatementNode;
            
            if (forBody instanceof Block) {
                Statement forBodyFirstStatement = (Statement)((Block)forBody).statements().get(0);
                if (!(forBodyFirstStatement instanceof IfStatement)
                        && !(forBodyFirstStatement instanceof WhileStatement)
                        && !(forBodyFirstStatement instanceof ForStatement)
                        && !(forBodyFirstStatement instanceof EnhancedForStatement)) {
                    newForBodyFirstStatementNode = ASTNode.copySubtree(ast, forBodyFirstStatement);
                    customNodes.add(forBodyFirstStatement);
                    ASTNode forBodyFirstNodes[] = {
                            getWriteToFileExpressionStatement("assert(" + forExpression.toString() + ");"), 
                            getWriteToFileExpressionStatement(newForBodyFirstStatementNode.toString()), 
                            newForBodyFirstStatementNode};

                    rewrite.replace(forBodyFirstStatement, rewrite.createGroupNode(forBodyFirstNodes), null);
                } else {
                    rewrite.getListRewrite(forBody, Block.STATEMENTS_PROPERTY).insertFirst(
                            getWriteToFileExpressionStatement("assert(" + forExpression.toString() + ");"), null);
                    //return true;
                }
            } else if (!(forBody instanceof IfStatement)
                    && !(forBody instanceof WhileStatement)
                    && !(forBody instanceof ForStatement)
                    && !(forBody instanceof EnhancedForStatement)) {
                newForBodyFirstStatementNode = ASTNode.copySubtree(ast, forBody);
                customNodes.add(forBody);
                
                ASTNode forBodyNodes[] = {
                        getWriteToFileExpressionStatement("assert(" + forExpression.toString() + ");"),
                        getWriteToFileExpressionStatement(newForBodyFirstStatementNode.toString()), newForBodyFirstStatementNode};
                rewrite.replace(forExpression, rewrite.createGroupNode(forBodyNodes), null);
            } else {
                newForBodyFirstStatementNode = ASTNode.copySubtree(ast, forBody);
                customNodes.add(forBody);
                
                ASTNode forBodyNodes[] = {
                        getWriteToFileExpressionStatement("assert(" + forExpression.toString() + ");"), newForBodyFirstStatementNode};
                rewrite.replace(forBody, rewrite.createGroupNode(forBodyNodes), null);
                //return true;
            }
            
            ASTNode assertNode = getWriteToFileExpressionStatement("assert(!(" + forExpression.toString() + "));");
            rewrite.getListRewrite(node.getParent(), (ChildListPropertyDescriptor) node.getLocationInParent())
            .insertAfter(assertNode, node, null);
            
            return true;
        } else if (node instanceof EnhancedForStatement) {
            Expression forExpression = ((EnhancedForStatement) node).getExpression();

            Statement forBody = ((EnhancedForStatement) node).getBody();

            ASTNode newForBodyFirstStatementNode;
            
            if (forBody instanceof Block) {
                Statement forBodyFirstStatement = (Statement)((Block)forBody).statements().get(0);
                if (!(forBodyFirstStatement instanceof IfStatement)
                        && !(forBodyFirstStatement instanceof WhileStatement)
                        && !(forBodyFirstStatement instanceof ForStatement)
                        && !(forBodyFirstStatement instanceof EnhancedForStatement)) {
                    newForBodyFirstStatementNode = ASTNode.copySubtree(ast, forBodyFirstStatement);
                    customNodes.add(forBodyFirstStatement);
                    ASTNode forBodyFirstNodes[] = {
                            getWriteToFileExpressionStatement("assert(" + forExpression.toString() + ");"), 
                            getWriteToFileExpressionStatement(newForBodyFirstStatementNode.toString()), 
                            newForBodyFirstStatementNode};

                    rewrite.replace(forBodyFirstStatement, rewrite.createGroupNode(forBodyFirstNodes), null);
                } else {
                    rewrite.getListRewrite(forBody, Block.STATEMENTS_PROPERTY).insertFirst(
                            getWriteToFileExpressionStatement("assert(" + forExpression.toString() + ");"), null);
                    //return true;
                }
            } else if (!(forBody instanceof IfStatement)
                    && !(forBody instanceof WhileStatement)
                    && !(forBody instanceof ForStatement)
                    && !(forBody instanceof EnhancedForStatement)) {
                newForBodyFirstStatementNode = ASTNode.copySubtree(ast, forBody);
                customNodes.add(forBody);
                
                ASTNode forBodyNodes[] = {
                        getWriteToFileExpressionStatement("assert(" + forExpression.toString() + ");"),
                        getWriteToFileExpressionStatement(newForBodyFirstStatementNode.toString()), newForBodyFirstStatementNode};
                rewrite.replace(forExpression, rewrite.createGroupNode(forBodyNodes), null);
            } else {
                newForBodyFirstStatementNode = ASTNode.copySubtree(ast, forBody);
                customNodes.add(forBody);
                
                ASTNode forBodyNodes[] = {
                        getWriteToFileExpressionStatement("assert(" + forExpression.toString() + ");"), newForBodyFirstStatementNode};
                rewrite.replace(forBody, rewrite.createGroupNode(forBodyNodes), null);
                //return true;
            }
            
            ASTNode assertNode = getWriteToFileExpressionStatement("assert(!(" + forExpression.toString() + "));");
            rewrite.getListRewrite(node.getParent(), (ChildListPropertyDescriptor) node.getLocationInParent())
            .insertAfter(assertNode, node, null);
 
            return true;
        } else if (node instanceof ReturnStatement) {
            ASTNode newNode = ASTNode.copySubtree(ast, node);

            ASTNode nodes[] = {getWriteToFileExpressionStatement(newNode.toString()), 
                    getWriteToFileExpressionStatement("}"), newNode};

            rewrite.replace(node, rewrite.createGroupNode(nodes), null);
            
            return true;

        } else if (node instanceof MethodDeclaration) {
            MethodDeclaration newNode = (MethodDeclaration) node;

            if (newNode.getName().getFullyQualifiedName().contains(wrapper.getMethod())) {
                @SuppressWarnings("rawtypes")
                List statements = newNode.getBody().statements();

                Statement firstStatement = (Statement) statements.get(0);

                ASTNode newFirstStatement = ASTNode.copySubtree(ast, firstStatement);

                TryStatement es, es3;

                ASTNode nodes[] = {
                        es = getWriteToFileExpressionStatement(newNode.toString().substring(0, newNode.toString().indexOf('\n'))), 
                                es3 = getWriteToFileExpressionStatement(newFirstStatement.toString()), newFirstStatement};

                customNodes.add(es);
                customNodes.add(es3);
                customNodes.add(firstStatement);

                rewrite.replace(firstStatement, rewrite.createGroupNode(nodes), null);

                Statement lastStatement = (Statement) statements.get(statements.size() - 1);

                ASTNode newLastStatement = ASTNode.copySubtree(ast, lastStatement);

                TryStatement es2, es4;

                ASTNode lastNodes[] = {es4 = getWriteToFileExpressionStatement(newLastStatement.toString()), 
                        es2 = getWriteToFileExpressionStatement("}"), newLastStatement};

                customNodes.add(es2);
                customNodes.add(es4);
                customNodes.add(lastStatement);

                rewrite.replace(lastStatement, rewrite.createGroupNode(lastNodes), null);
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

    @SuppressWarnings("unchecked")
    public TryStatement getWriteToFileExpressionStatement(String value) {
        
        StringLiteral fileNameLiteral1 = ast.newStringLiteral();
        fileNameLiteral1.setLiteralValue(seqFileName);
        StringLiteral fileNameLiteral2 = ast.newStringLiteral();
        fileNameLiteral2.setLiteralValue(seqFileName);

        MethodInvocation wtfMI = ast.newMethodInvocation();
        StringLiteral methodLiteral = ast.newStringLiteral();
        methodLiteral.setLiteralValue(value);

        wtfMI.arguments().add(0, methodLiteral);
        wtfMI.arguments().add(0, fileNameLiteral1);

        wtfMI.setExpression(ast.newSimpleName("FileUtils"));
        wtfMI.setName(ast.newSimpleName("appendToFile"));

        ExpressionStatement es = ast.newExpressionStatement(wtfMI);
        
        TryStatement tryState = ast.newTryStatement();
        Block block = ast.newBlock();
        block.statements().add(es);
        tryState.setBody(block);
        
        CatchClause catchClause = ast.newCatchClause();
        SingleVariableDeclaration exception = ast.newSingleVariableDeclaration();
        exception.setType(ast.newSimpleType(ast.newSimpleName("IOException")));
        exception.setName(ast.newSimpleName("ioexception"));
        catchClause.setException(exception);
        tryState.catchClauses().add(catchClause);

        return tryState;
    }
    
    public String getSeqFileName() {
        return seqFileName;
    }
    
}
