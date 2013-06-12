package ar.edu.taco.stryker.api.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Assignment.Operator;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WildcardType;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
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
import com.google.common.collect.Sets;

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
        //        final String seqFileName = "/Users/zeminlu/ITBA/Ph.D./comitaco/tests/roops/core/objects/seq/SinglyLinkedList.java";
        String source = "";

        try {
            source = FileUtils.readFile(oldFilename);
        } catch (final IOException e1) {
            // TODO: Define what to do!
        }

        final IDocument document = new Document(source);

        final org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
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
                        if (method.getName().toString().contains(wrapper.getMethod())) {
                            //First, we want to add some instructions as first lines of the method to create the output
                            //file for this method, where the sequential code is going to be outputted.
                            //Then, the visitor has to inspect every line of code and insert an output instruction to the
                            //previously created file, containing the exact line that just run, to obtain
                            //the secuential code branch. If it is a guard, replace it and brackets with an assert.

                            //To do this, we will implement an ASTVisitor that does everything we want, and we will
                            //give it the AST Tree to visit starting at this method.
                            visitor.setMethodName(method.getName().toString());
                            visitor.setNextMutID(0);
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

        IDocument document = new Document(source);

        org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
        parser.setSource(document.get().toCharArray());

        // Parse the source code and generate an AST.
        CompilationUnit unit = (CompilationUnit) parser.createAST(null);

        //Record modifications to the AST
        unit.recordModifications();

        AST ast = unit.getAST();

        Map<String, String> replaceMap = Maps.newHashMap();

        StrykerASTVisitor visitor = new StrykerASTVisitor(wrapper, unit, source, ast, seqFilesPrefix);
        // to iterate through methods
        List<AbstractTypeDeclaration> types = unit.types();
        for (final AbstractTypeDeclaration type : types) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                // Class def found
                final List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (final BodyDeclaration body : bodies) {
                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        final MethodDeclaration method = (MethodDeclaration)body;
                        for (String name : methodNames) {
                            if (name.contains(method.getName().toString()) && method.getName().toString().contains(name)) {
                                String seqSource = "";
                                try {
                                    seqSource = FileUtils.readFile(seqFilesPrefix + "_" + method.getName());
                                } catch (final IOException e1) {
                                    // TODO: Define what to do!
                                }

                                String previousBody = source.substring(method.getBody().getStartPosition(), 
                                        method.getBody().getStartPosition() + method.getBody().getLength());

                                replaceMap.put(previousBody, "{" + seqSource + "}");
                            }
                        }
                    }
                }
            }
        }
        for (Entry<String, String> entry : replaceMap.entrySet()) {
            source = source.replace(entry.getKey(), entry.getValue());
        }
        source = source.replace("package roops.core.objects", "package roops.core.objects.seq");

        ////////////////////////////////////// Duplicate Variables Vanisher /////////////////////////////////////////

        document = new Document(source);

        parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
        parser.setSource(document.get().toCharArray());

        // Parse the source code and generate an AST.
        unit = (CompilationUnit) parser.createAST(null);

        //Record modifications to the AST
        unit.recordModifications();

        ast = unit.getAST();

        StrykerDuplicateVariablesVanisherASTVisitor vanisherVisitor = 
                new StrykerDuplicateVariablesVanisherASTVisitor(wrapper, unit, source, ast, seqFilesPrefix);

        // to iterate through methods
        types = unit.types();
        for (final AbstractTypeDeclaration type : types) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                // Class def found
                final List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (final BodyDeclaration body : bodies) {
                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        final MethodDeclaration method = (MethodDeclaration)body;
                        for (String name : methodNames) {
                            if (name.contains(method.getName().toString()) && method.getName().toString().contains(name)) {
                                Set<String> variables = Sets.newHashSet();
                                vanisherVisitor.setVariables(variables);
                                method.accept(vanisherVisitor);
                            }
                        }
                    }
                }
            }
        }

        //Reescribimos el archivo original por una version donde a los metodos especificados se los reemplazo por su
        //version secuencial
        final TextEdit edits = vanisherVisitor.getRewrite().rewriteAST(document, null);
        try {
            edits.apply(document);
        } catch (MalformedTreeException | BadLocationException e) {
            // TODO: Define what to do!
        }
        try {
            FileUtils.writeToFile(seqFilesPrefix, document.get());

        } catch (final IOException e) {
            // TODO: Define what to do!
        }

        return wrapper;
    }

    @SuppressWarnings("unchecked")
    public static OpenJMLInputWrapper variablizeMethods(final OpenJMLInputWrapper wrapper, Set<String> methodNames) {

        String variablizedFilename = wrapper.getVariablizedFilename();
        if (variablizedFilename == null) {
            wrapper.setVariablizedFilename(variablizedFilename);
            variablizedFilename = wrapper.getSeqFilesPrefix();
        }
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
        parser.setEnvironment(new String[] {"/Users/zeminlu/ITBA/Ph.D./comitaco/bin"}, null, null, false);
        parser.setUnitName(variablizedFilename);
        parser.setSource(document.get().toCharArray());
        // Parse the source code and generate an AST.
        final CompilationUnit unit = (CompilationUnit) parser.createAST(null);

        //Record modifications to the AST
        unit.recordModifications();

        final AST ast = unit.getAST();

        String varPrefix = "var_";
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
                            if (name.contains(method.getName().toString()) && method.getName().toString().contains(name)) {
                                List<Statement> statements = method.getBody().statements();
                                //Itero por los statements de abajo hacia arriba
                                for (int i = statements.size() - 1 ; i >= 0 ; --i) {
                                    Statement statement = statements.get(i);
                                    if (statement instanceof ExpressionStatement 
                                            && unit.lastTrailingCommentIndex(statement) >= 0) {
                                        //Es expression statement y tiene comentario
                                        Expression expression = ((ExpressionStatement) statement).getExpression();
                                        if (expression instanceof Assignment) {
                                            //Es una asignacion
                                            Assignment assignment = (Assignment) expression;
                                            ///RHS de la asignacion
                                            Expression rhs = assignment.getRightHandSide();
                                            //Veo si es una variable que ya inserte yo
                                            if (rhs instanceof SimpleName 
                                                    && ((SimpleName)rhs).toString().toLowerCase().contains(varPrefix)) {
                                                Integer previousVarNumber = variableNames.get(name);
                                                previousVarNumber = previousVarNumber == null ? 0 : ++previousVarNumber;
                                                variableNames.put(name, previousVarNumber);
                                                continue;
                                            }
                                            //Tomar el id de mutante
                                            int commentIndex = unit.firstLeadingCommentIndex(statement);
                                            boolean flag = true;
                                            LineComment mutIDCommentNode;
                                            String mutID = null;
                                            while (flag) {
                                                mutIDCommentNode = ((LineComment) unit.getCommentList().get(commentIndex));
                                                mutID = source.substring(mutIDCommentNode.getStartPosition(), mutIDCommentNode.getStartPosition() + mutIDCommentNode.getLength());
                                                if (!mutID.contains("mutID")) {
                                                    ++commentIndex;
                                                } else {
                                                    break;
                                                }
                                            }
                                            String otherMutID = null;
                                            for (int j = i ; j >= 0 ; --j) {
                                                statement = statements.get(j);
                                                if (statement instanceof ExpressionStatement 
                                                        && unit.lastTrailingCommentIndex(statement) >= 0) {
                                                    //Es expression statement y tiene comentario
                                                    expression = ((ExpressionStatement) statement).getExpression();
                                                    if (expression instanceof Assignment) {
                                                        //Es una asignacion
                                                        assignment = (Assignment) expression;
                                                        ///RHS de la asignacion
                                                        rhs = assignment.getRightHandSide();
                                                        flag = true;
                                                        commentIndex = unit.firstLeadingCommentIndex(statement);
                                                        while (flag) {
                                                            mutIDCommentNode = ((LineComment) unit.getCommentList().get(commentIndex));
                                                            otherMutID = source.substring(mutIDCommentNode.getStartPosition(), mutIDCommentNode.getStartPosition() + mutIDCommentNode.getLength());
                                                            if (!otherMutID.contains("mutID")) {
                                                                ++commentIndex;
                                                            } else {
                                                                break;
                                                            }
                                                        }
                                                        if (!otherMutID.contains(mutID)) {
                                                            continue;
                                                        }
                                                        ITypeBinding binding = assignment.resolveTypeBinding();
                                                        //Tipo de la asignacion
                                                        Type assignmentType = typeFromBinding(ast, binding);
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
                                                        String variableName = varPrefix + previousVarNumber;
                                                        //Seteamos el nombre de la variable
                                                        SimpleName variableSimpleName = ast.newSimpleName(variableName);
                                                        variableDeclaration.setName(variableSimpleName);
                                                        //Agregamos la nueva variable a los parametros del metodo
                                                        ListRewrite lr = visitor.getRewrite().getListRewrite(method, MethodDeclaration.PARAMETERS_PROPERTY);
                                                        lr.insertLast(variableDeclaration, null);
                                                        //Reemplazamos la parte derecha de la asignacion por la nueva variable
                                                        visitor.getRewrite().replace(rhs, variableSimpleName, null);
                                                    }
                                                }
                                            }
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

        //Reescribimos el archivo con los metodos secuenciales que fallaron pero variabilizados
        final TextEdit edits = visitor.getRewrite().rewriteAST(document, null);
        try {
            edits.apply(document);
        } catch (MalformedTreeException | BadLocationException e) {
            // TODO: Define what to do!
        }
        try {
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

    //Hay que englobar cada formula (1 por cada ensures)
    //Luego, conjuncion de ellas con un and
    //Por ultimo, englobo toda esa conjuncion y le clavo el ! adelante.
    //Cabe destacar que por ahora tenemos solo un ensures por linea, en 1 linea.
    //A futuro, soportar de ser posible mediante parseo, que la formula de c/ensures sea multilinea.
    @SuppressWarnings("unchecked")
    public static OpenJMLInputWrapper negatePostconditions(final OpenJMLInputWrapper wrapper, Set<String> methodNames) {

        final String seqFilesPrefix = wrapper.getSeqFilesPrefix();

        String source = "";

        try {
            source = FileUtils.readFile(seqFilesPrefix);
        } catch (final IOException e1) {
            // TODO: Define what to do!
        }

        final IDocument document = new Document(source);

        final org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
        parser.setSource(document.get().toCharArray());

        // Parse the source code and generate an AST.
        final CompilationUnit unit = (CompilationUnit) parser.createAST(null);

        // to iterate through methods
        Map<String, String> replacements = Maps.newHashMap();
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
                            if (name.contains(method.getName().toString()) && method.getName().toString().contains(name)) {
                                int commentIndex = unit.firstLeadingCommentIndex(method);
                                if (commentIndex >= 0) {
                                    BlockComment blockCommentNode = (BlockComment) unit.getCommentList().get(commentIndex);
                                    //Tiene comentario precedente al metodo, potencialmente sea el del contrato
                                    String blockComment = source.substring(blockCommentNode.getStartPosition(), 
                                            blockCommentNode.getStartPosition() + blockCommentNode.getLength());
                                    String blockCommentBackup = new String(blockComment);
                                    //Empezamos el parseo de la postcondicion, para reemplazar por la negada luego
                                    String blockCommentLines[] = blockComment.split("\n");
                                    List<String> formulas = Lists.newLinkedList();
                                    for (int i = 0; i < blockCommentLines.length; ++i) {
                                        String line = blockCommentLines[i];
                                        if (line.contains("ensures")) {
                                            formulas.add(line.replace("    @ ensures ", ""));
                                            blockComment = blockComment.replace(line + "\n", "");
                                        }
                                    }
                                    String postcondition = "";

                                    for (String formula : formulas) {
                                        postcondition = postcondition.concat(" and (" + formula + ");");
                                    }

                                    postcondition = postcondition.replaceFirst(" and ", "");

                                    String negPostcondition = "    @ ensures !(" + postcondition + ");\n";

                                    //Tengo la postcondicion negada
                                    //Tengo que reemplazar todas las lineas de ensures, por esta.
                                    //Como ya saque cada ensures que encontré, basta con poner la negada al comienzo
                                    blockComment = blockComment.replaceFirst("\n", "\n" + negPostcondition);

                                    //Y finalmente, record de reemplazar la postcondicion
                                    if (!replacements.containsKey(blockCommentBackup)) {
                                        replacements.put(blockCommentBackup, blockComment);
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }

        //Reescribimos el archivo con sus postcondiciones negadas
        for (Entry<String, String> entry : replacements.entrySet()) {
            source = source.replace(entry.getKey(), entry.getValue());
        }
        try {
            FileUtils.writeToFile(seqFilesPrefix, source);
        } catch (final IOException e) {
            // TODO: Define what to do!
        }

        return wrapper;
    }

    public static OpenJMLInputWrapper fixInput(final OpenJMLInputWrapper wrapper, Map<String, String> methods) {
        /*
         * Parsear en el ast todas las variable declarations y guardarlas
         * Parsear y convertir los updateValue a sentencias de asignacion
         * Parsear el invoke para saber qué variables son parametros y cuales son para la instancia
         * Insertar todo en orden al comienzo de los metodos secuenciales
         */

        final String seqFilesPrefix = wrapper.getSeqFilesPrefix();

        String source = "";

        try {
            source = FileUtils.readFile(seqFilesPrefix);
        } catch (final IOException e1) {
            // TODO: Define what to do!
        }

        final IDocument document = new Document(source);

        final org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
        parser.setSource(document.get().toCharArray());

        // Parse the source code and generate an AST.
        final CompilationUnit unit = (CompilationUnit) parser.createAST(null);

        //Record modifications to the AST
        unit.recordModifications();

        final AST ast = unit.getAST();
        final ASTRewrite rewrite = ASTRewrite.create(ast);

        // to iterate through methods
        Map<String, String> replacements = Maps.newHashMap();
        final List<AbstractTypeDeclaration> types = unit.types();
        for (final AbstractTypeDeclaration type : types) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                // Class def found
                final List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (final BodyDeclaration body : bodies) {
                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        final MethodDeclaration method = (MethodDeclaration)body;
                        for (String name : methods.keySet()) {
                            //Veo si es uno de los que tengo que cablear
                            if (name.contains(method.getName().toString()) && method.getName().toString().contains(name)) {
                                //Usar el value correspondiente al key para obtener el archivo de input
                                String inputFilename = methods.get(name);
                                //Parsear en el ast todas las variable declarations y guardarlas
                                //Parsear y convertir los updateValue a sentencias de asignacion
                                //Parsear el invoke para saber qué variables son parametros y cuales son para la instancia
                                String inputSource = "";

                                try {
                                    inputSource = FileUtils.readFile(inputFilename);
                                } catch (final IOException e1) {
                                    // TODO: Define what to do!
                                }

                                final IDocument inputDocument = new Document(inputSource);

                                final org.eclipse.jdt.core.dom.ASTParser inputParser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
                                inputParser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
                                inputParser.setSource(inputDocument.get().toCharArray());

                                final List<VariableDeclarationStatement> vbstatements = Lists.newArrayList();
                                final List<SimpleName> methodArguments = Lists.newArrayList();
                                final List<MethodInvocation> variableAssignments = Lists.newArrayList();

                                // Parse the source code and generate an AST.
                                final CompilationUnit inputUnit = (CompilationUnit) inputParser.createAST(null);
                                final List<AbstractTypeDeclaration> inputTypes = inputUnit.types();
                                for (final AbstractTypeDeclaration inputType : inputTypes) {
                                    if (inputType.getNodeType() == ASTNode.TYPE_DECLARATION) {
                                        // Class def found
                                        final List<BodyDeclaration> inputBodies = inputType.bodyDeclarations();
                                        for (final BodyDeclaration inputBody : inputBodies) {
                                            if (inputBody.getNodeType() == ASTNode.METHOD_DECLARATION) {
                                                final MethodDeclaration inputMethod = (MethodDeclaration)inputBody;
                                                if (inputMethod.getName().toString().contains("test")) {
                                                    Block inputBlock = inputMethod.getBody();

                                                    List<Statement> statements = inputBlock.statements();
                                                    for (Statement statement : statements) {
                                                        if (statement instanceof VariableDeclarationStatement) {
                                                            VariableDeclarationStatement vds = (VariableDeclarationStatement) statement;
                                                            List<VariableDeclarationFragment> fragments = vds.fragments();
                                                            //Ojo aca que al else van los que tengan N fragmentos
                                                            if (fragments.size() == 1 && fragments.get(0).getName().getIdentifier().equalsIgnoreCase("method")) {
                                                                continue;
                                                            } else {
                                                                vbstatements.add(vds);
                                                            }

                                                        } else if (statement instanceof ExpressionStatement) {
                                                            ExpressionStatement es = (ExpressionStatement) statement;
                                                            if (es.getExpression() instanceof MethodInvocation) {
                                                                MethodInvocation esInvocation = (MethodInvocation) es.getExpression();
                                                                if (esInvocation.getName().getIdentifier().equalsIgnoreCase("updateValue")) {
                                                                    List<Expression> arguments = esInvocation.arguments();
                                                                    if (arguments.size() == 3) {
//                                                                        for (Expression expression : arguments) {
//                                                                            if (expression instanceof MethodInvocation && 
//                                                                                    ((MethodInvocation)expression).getName().getIdentifier().equalsIgnoreCase("updatevalue")) {
                                                                                variableAssignments.add(esInvocation);
//                                                                            } else {
                                                                                //TODO
//                                                                            } 
//                                                                        }
                                                                    }else {
                                                                        //TODO
                                                                    }
                                                                } else {
                                                                    //TODO
                                                                }
                                                            } else {
                                                                //TODO
                                                            }

                                                        } else if (statement instanceof TryStatement) {
                                                            TryStatement trys = (TryStatement) statement;
                                                            Block tryBlock = trys.getBody();
                                                            List<Statement> tryStatements = tryBlock.statements();
                                                            if (tryStatements.size() != 1) {
                                                                //TODO
                                                            } else {
                                                                Statement tryStatement = tryStatements.get(0);
                                                                if (tryStatement instanceof ExpressionStatement) {
                                                                    ExpressionStatement tryExpStatement = (ExpressionStatement) tryStatement;
                                                                    Expression tryExpression = tryExpStatement.getExpression();
                                                                    if (tryExpression instanceof MethodInvocation) {
                                                                        MethodInvocation tryInvocation = (MethodInvocation) tryExpression;
                                                                        if (tryInvocation.getName().getIdentifier().equalsIgnoreCase("invoke")) {
                                                                            List<Expression> arguments = tryInvocation.arguments();
                                                                            if (arguments.size() == 2) {
                                                                                Expression args = arguments.get(1);
                                                                                if (args instanceof ArrayCreation) {
                                                                                    ArrayCreation argsArray = (ArrayCreation) args;
                                                                                    ArrayInitializer argsInit = argsArray.getInitializer();
                                                                                    methodArguments.addAll(argsInit.expressions());
                                                                                } else {
                                                                                    //TODO
                                                                                }
                                                                            } else {
                                                                                //TODO
                                                                            }
                                                                        } else {
                                                                            //TODO
                                                                        }
                                                                    } else {
                                                                        //TODO
                                                                    }
                                                                } else {
                                                                    //TODO
                                                                }
                                                            }
                                                        } else {
                                                            //TODO
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                //Tengo las cosas parseadas en las 3 colecciones antes del for
                                //Insertar todo en orden al comienzo de los metodos secuenciales
                                Statement firstStatement = (Statement)method.getBody().statements().get(0);
                                //Saco las declaraciones de las variables que no necesito
                                VariableDeclarationStatement instanceStatement = null;
                                for (VariableDeclarationStatement oldvds : vbstatements) {
                                    if (((VariableDeclarationFragment)oldvds.fragments().get(0)).getName().getIdentifier().equalsIgnoreCase("instance")) {
                                        instanceStatement = oldvds;
                                        break;
                                    }
                                }
                                vbstatements.remove(instanceStatement);

                                List<VariableDeclarationStatement> newvds = ASTNode.copySubtrees(ast, vbstatements);
                                //Tengo que remover los N primeros argumentos del metodo y ponerles ese nombre a las vds que corresponda
                                int argsToRemove = methodArguments.size();

                                ListRewrite lr = rewrite.getListRewrite(method, MethodDeclaration.PARAMETERS_PROPERTY);
                                List<SingleVariableDeclaration> parameters = method.parameters();
                                Map<String, SimpleName> namesMap = Maps.newHashMap();
                                for (int i = 0 ; i < methodArguments.size() ; ++ i) {
                                    SimpleName arg = methodArguments.get(i);
                                    String newName = "";
                                    SingleVariableDeclaration svd = parameters.get(i);
                                    lr.remove(svd, null);
                                    newName = svd.getName().getIdentifier();
                                    for (VariableDeclarationStatement vds : newvds) {
                                        VariableDeclarationFragment vdf = ((VariableDeclarationFragment)vds.fragments().get(0));
                                        if (vdf.getName().getIdentifier().equalsIgnoreCase(arg.getIdentifier())) {
                                            SimpleName newSimpleName = ast.newSimpleName(newName);
                                            rewrite.replace(vdf.getName(), newSimpleName, null);
                                            namesMap.put(vdf.getName().getIdentifier(), newSimpleName);
                                            break;
                                        }
                                    }
                                }

                                //Inserto las declaraciones de las variables
                                for (VariableDeclarationStatement vds : newvds) {
                                    rewrite.getListRewrite(method.getBody(), Block.STATEMENTS_PROPERTY).insertBefore(vds, firstStatement, null);
                                }

                                //Pasar updateValues de los variableAssignments a assignments e insertarlos debajo de todo lo anterior
                                for (MethodInvocation updateValue : variableAssignments) {
                                    List<Expression> updateArgs = updateValue.arguments();
                                    if (updateArgs.size() != 3) {
                                        //TODO
                                    }

                                    Assignment assignment = ast.newAssignment();

                                    Expression firstArg = updateArgs.get(0);
                                    FieldAccess fieldAccess = ast.newFieldAccess();
                                    fieldAccess.setName(ast.newSimpleName(((StringLiteral)updateArgs.get(1)).getLiteralValue()));
                                    if (firstArg instanceof SimpleName && ((SimpleName)firstArg).getIdentifier().equalsIgnoreCase("instance")) {
                                        fieldAccess.setExpression(ast.newThisExpression());
                                    } else {
                                        fieldAccess.setExpression((Expression)ASTNode.copySubtree(ast, firstArg));
                                    }
                                    assignment.setLeftHandSide(fieldAccess);
                                    assignment.setOperator(Operator.ASSIGN);
                                    Expression rhsExpression = (Expression)ASTNode.copySubtree(ast, updateArgs.get(2));
                                    if (rhsExpression instanceof SimpleName && namesMap.containsKey(((SimpleName)rhsExpression).getIdentifier())) {
                                        assignment.setRightHandSide(namesMap.get(((SimpleName)rhsExpression).getIdentifier()));
                                    } else {
                                        assignment.setRightHandSide(rhsExpression);
                                    }
                                    ExpressionStatement assignmentStatement = ast.newExpressionStatement(assignment);
                                    rewrite.getListRewrite(method.getBody(), Block.STATEMENTS_PROPERTY).insertBefore(assignmentStatement, firstStatement, null);
                                }
                            }
                        }
                    }
                }
            }
        }

        //Reescribimos el archivo con los inputs cableados
        final TextEdit edits = rewrite.rewriteAST(document, null);
        try {
            edits.apply(document);
        } catch (MalformedTreeException | BadLocationException e) {
            // TODO: Define what to do!
        }

        try {
            FileUtils.writeToFile(seqFilesPrefix, document.get());
        } catch (final IOException e) {
            // TODO: Define what to do!
        }

        return wrapper;    }
}
