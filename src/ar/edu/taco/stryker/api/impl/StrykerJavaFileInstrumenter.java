package ar.edu.taco.stryker.api.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.WildcardType;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
import ar.edu.taco.utils.FileUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class StrykerJavaFileInstrumenter {

    private static List<String> ioImports = Lists.newArrayList("java.io.IOException", "ar.edu.taco.utils.FileUtils");

    @SuppressWarnings("unchecked")
    public static OpenJMLInputWrapper instrumentForSequentialOutput(final OpenJMLInputWrapper wrapper) {

        final String oldFilename = wrapper.getFilename();

        final String newFilename = oldFilename.replace(
                oldFilename.split("/")[oldFilename.split("/").length - 1], "instrumented/" +
                        oldFilename.split("/")[oldFilename.split("/").length - 1]);
        final String seqFileName = oldFilename.replaceFirst(
                oldFilename.split("/")[oldFilename.split("/").length - 1], "sequential/" +
                        oldFilename.split("/")[oldFilename.split("/").length - 1]);
        String source = "";

        try {
            source = FileUtils.readFile(oldFilename);
        } catch (final IOException e1) {
            // TODO: Define what to do!
        }

        final IDocument document = new Document(source);

        final org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);
        parser.setSource(document.get().toCharArray());

        // Parse the source code and generate an AST.
        final CompilationUnit unit = (CompilationUnit) parser.createAST(null);
        
        //Record modifications to the AST
        unit.recordModifications();

        //Add imports to enable file output of the instrumented code
        final AST ast = unit.getAST();

        StrykerASTVisitor visitor = new StrykerASTVisitor(wrapper, unit, source, ast, seqFileName);
        // to iterate through methods
        final List<AbstractTypeDeclaration> types = unit.types();
        for (final AbstractTypeDeclaration type : types) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                // Class def found
                final List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (final BodyDeclaration body : bodies) {
                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        final MethodDeclaration method = (MethodDeclaration)body;
                        if (method.getName().getFullyQualifiedName().contains(wrapper.getMethod())) {
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

        //Agregamos los imports para el FileUtils.appendToFile()
        //visitor.getRewrite().get(unit, CompilationUnit.TYPES_PROPERTY);
        ListRewrite lrw = visitor.getRewrite().getListRewrite(unit, CompilationUnit.IMPORTS_PROPERTY);
        for (final String importString : ioImports) {
            final ImportDeclaration id = ast.newImportDeclaration();
            final String classToImport = importString;
            id.setName(ast.newName(classToImport.split("\\.")));
            lrw.insertLast(id, null);
        }

        //Reescribimos el archivo fuente con su instrumentacion
        final TextEdit edits = visitor.getRewrite().rewriteAST(document, null);
        try {
            edits.apply(document);
        } catch (MalformedTreeException | BadLocationException e) {
            // TODO: Define what to do!
        }
        try {
            //            System.out.println(document.get());
            FileUtils.writeToFile(newFilename, document.get());

        } catch (final IOException e) {
            // TODO: Define what to do!
        }

        OpenJMLInputWrapper newWrapper =  new OpenJMLInputWrapper(
                newFilename,
                wrapper.getJunitInputs(),
                wrapper.getConfigurationFile(),
                wrapper.getOverridingProperties(),
                wrapper.getMethod(),
                wrapper.getMap());

        newWrapper.setSeqFilesPrefix(seqFileName);
        newWrapper.setOldFilename(oldFilename);

        return newWrapper;
    }

    @SuppressWarnings("unchecked")
    public static OpenJMLInputWrapper replaceMethodBodies(final OpenJMLInputWrapper wrapper, Set<String> methodNames) {

        final String oldFilename = wrapper.getOldFilename();
        final String seqFilesPrefix = wrapper.getSeqFilesPrefix();

        String source = "";

        try {
            source = FileUtils.readFile(oldFilename);
        } catch (final IOException e1) {
            // TODO: Define what to do!
        }

        final IDocument document = new Document(source);

        final org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);
        parser.setSource(document.get().toCharArray());

        // Parse the source code and generate an AST.
        final CompilationUnit unit = (CompilationUnit) parser.createAST(null);

        //Record modifications to the AST
        unit.recordModifications();

        final AST ast = unit.getAST();

        StrykerASTVisitor visitor = new StrykerASTVisitor(wrapper, unit, source, ast, seqFilesPrefix);
        // to iterate through methods
        final List<AbstractTypeDeclaration> types = unit.types();
        for (final AbstractTypeDeclaration type : types) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                // Class def found
                final List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (final BodyDeclaration body : bodies) {
                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        final MethodDeclaration method = (MethodDeclaration)body;
                        for (String name : methodNames) {
                            if (methodNames.contains(method.getName().toString())) {
                                
                                String seqSource = "";
                                
                                try {
                                    seqSource = FileUtils.readFile(seqFilesPrefix + "_" + method.getName());
                                } catch (final IOException e1) {
                                    // TODO: Define what to do!
                                }
                                
                                final IDocument seqDocument = new Document(seqSource);
                                
                                final org.eclipse.jdt.core.dom.ASTParser seqParser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
                                seqParser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_STATEMENTS);
                                seqParser.setResolveBindings(true);
                                seqParser.setSource(seqDocument.get().toCharArray());
                                
                                final Block seqBlock = (Block) seqParser.createAST(null);
                                
                                visitor.getRewrite().replace(method.getBody(), seqBlock, null);
                            }
                        }
                    }
                }
            }
        }

        //Reescribimos el archivo fuente con su instrumentacion
        final TextEdit edits = visitor.getRewrite().rewriteAST(document, null);
        try {
            edits.apply(document);
        } catch (MalformedTreeException | BadLocationException e) {
            // TODO: Define what to do!
        }
        try {
            //            System.out.println(document.get());
            FileUtils.writeToFile(seqFilesPrefix, document.get());

        } catch (final IOException e) {
            // TODO: Define what to do!
        }

        return wrapper;
    }
    
    @SuppressWarnings("unchecked")
    public static OpenJMLInputWrapper variablizeMethods(final OpenJMLInputWrapper wrapper, Set<String> methodNames) {

        final String variablizedFilename = 
                wrapper.getVariablizedFilename() == null ? wrapper.getSeqFilesPrefix() : wrapper.getVariablizedFilename();
        
        
        String source = "";
        
        Map<String, Integer> variableNames = Maps.newHashMap();

        try {
            source = FileUtils.readFile(variablizedFilename);
        } catch (final IOException e1) {
            // TODO: Define what to do!
        }

        final IDocument document = new Document(source);

        final org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);
        parser.setSource(document.get().toCharArray());

        // Parse the source code and generate an AST.
        final CompilationUnit unit = (CompilationUnit) parser.createAST(null);

        //Record modifications to the AST
        unit.recordModifications();

        final AST ast = unit.getAST();

        StrykerASTVisitor visitor = new StrykerASTVisitor(wrapper, unit, source, ast, variablizedFilename);
        // to iterate through methods
        final List<AbstractTypeDeclaration> types = unit.types();
        for (final AbstractTypeDeclaration type : types) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                // Class def found
                final List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (final BodyDeclaration body : bodies) {
                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        final MethodDeclaration method = (MethodDeclaration)body;
                        for (String name : methodNames) {
                            //Veo si es uno de los que tengo que variabilizar
                            if (methodNames.contains(method.getName().toString())) {
                                List<Statement> statements = method.getBody().statements();
                                //Itero por los statements de abajo hacia arriba
                                for (int i = statements.size() - 1 ; i >= 0 ; --i) {
                                    Statement statement = statements.get(i);
                                    if (statement.getClass().equals(ExpressionStatement.class) 
                                            && unit.lastTrailingCommentIndex(statement) >= 0) {
                                        //Es expression statement y tiene comentario
                                        Expression expression = ((ExpressionStatement) statement).getExpression();
                                        if (expression.getClass().equals(Assignment.class)) {
                                            //Es una asignacion
                                            Assignment assignment = (Assignment) expression;
                                            ///RHS de la asignacion
                                            Expression rhs = assignment.getRightHandSide();
                                            //Veo si es una variable que ya inserte yo
                                            if (rhs.getClass().equals(SimpleName.class) 
                                                    && ((SimpleName)rhs).toString().contains("VAR_")) {
                                                    continue;
                                            }
                                            //Tipo de la asignacion
                                            Type assignmentType = 
                                                    typeFromBinding(ast, rhs.resolveTypeBinding().getTypeDeclaration());
                                            //Debo reemplazar la RHS por una variable del mismo tipo
                                            //Dicha variable hay que agregarla como argumento al método
                                            //Nueva variable:
                                            SingleVariableDeclaration variableDeclaration = ast.newSingleVariableDeclaration();
                                            //Seteamos el tipo
                                            variableDeclaration.setType(assignmentType);
                                            //Generamos un nuevo nombre de variable en funcion de los ya asignados
                                            Integer previousVarNumber = variableNames.get(name);
                                            previousVarNumber = previousVarNumber == null ? 0 : ++previousVarNumber;
                                            variableNames.put(name, previousVarNumber);
                                            String variableName = "var_" + previousVarNumber;
                                            //Seteamos el nombre de la variable
                                            variableDeclaration.setName(ast.newSimpleName(variableName));
                                            //Agregamos la nueva variable a los parametros del metodo
                                            method.parameters().add(variableDeclaration);
                                            //Reemplazamos la parte derecha de la asignacion por la nueva variable
                                            visitor.getRewrite().replace(rhs, ast.newSimpleName(variableName), null);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //Reescribimos el archivo fuente con su instrumentacion
        final TextEdit edits = visitor.getRewrite().rewriteAST(document, null);
        try {
            edits.apply(document);
        } catch (MalformedTreeException | BadLocationException e) {
            // TODO: Define what to do!
        }
        try {
            //            System.out.println(document.get());
            FileUtils.writeToFile(variablizedFilename, document.get());
        } catch (final IOException e) {
            // TODO: Define what to do!
        }

        return wrapper;
    }
    
    public static Type typeFromBinding(AST ast, ITypeBinding typeBinding) {
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
        return ast.newSimpleType(ast.newName(qualName));
    }
}
