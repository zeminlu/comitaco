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

import ar.edu.taco.stryker.api.impl.input.DarwinistInput;
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
                wrapper.getMap(),
                wrapper.getOriginalFilename());

        newWrapper.setSeqFilesPrefix(seqFileName);
        newWrapper.setOldFilename(oldFilename);

        return newWrapper;
    }

    @SuppressWarnings("unchecked")
    public static void replaceMethodBodies(final DarwinistInput darwinistInput) {

        final String originalFilename = darwinistInput.getOriginalFilename();
        final String oldFilename = darwinistInput.getOldFilename();
        final String seqFilesPrefix = darwinistInput.getSeqFilesPrefix();

        String source = "";

        try {
            source = FileUtils.readFile(originalFilename);
        } catch (final IOException e1) {
            // TODO: Define what to do!
        }

        IDocument document = new Document(source);

        org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
        parser.setSource(document.get().toCharArray());

        // Parse the source code and generate an AST.
        CompilationUnit unit = (CompilationUnit) parser.createAST(null);

        AST ast = unit.getAST();

        Map<String, String> replaceMap = Maps.newHashMap();

        // to iterate through methods
        List<AbstractTypeDeclaration> types = unit.types();
        for (final AbstractTypeDeclaration type : types) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                // Class def found
                final List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (final BodyDeclaration body : bodies) {
                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        final MethodDeclaration method = (MethodDeclaration)body;
                        if (darwinistInput.getSeqMethod().contains(method.getName().toString())) {
                            String seqSource = "";
                            try {
                                seqSource = FileUtils.readFile(seqFilesPrefix + "_" + darwinistInput.getSeqMethod());
                            } catch (final IOException e1) {
                                // TODO: Define what to do!
                            }

                            String previousBody = source.substring(method.getBody().getStartPosition(), 
                                    method.getBody().getStartPosition() + method.getBody().getLength());

                            replaceMap.put(previousBody, "{" + seqSource + "}");
                            break;
                        }
                    }
                }
            }
        }
        for (Entry<String, String> entry : replaceMap.entrySet()) {
            source = source.replace(entry.getKey(), entry.getValue());
        }

        //Seteo de package con .seq
        //No se deberia usar mas ahora que procesa todo el darwinistcontroller
        //        String packageHeader = "package ";
        //        int indexOfPackageContent = source.indexOf(packageHeader, 0) + packageHeader.length();
        //        String packageContent = source.substring(indexOfPackageContent, source.indexOf(';', indexOfPackageContent));
        //        source = source.replace(packageHeader + packageContent, packageHeader + packageContent + ".seq");

        ////////////////////////////////////// Duplicate Variables Vanisher /////////////////////////////////////////

        document = new Document(source);

        parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
        parser.setSource(document.get().toCharArray());

        // Parse the source code and generate an AST.
        unit = (CompilationUnit) parser.createAST(null);

        ast = unit.getAST();

        StrykerDuplicateVariablesVanisherASTVisitor vanisherVisitor = new StrykerDuplicateVariablesVanisherASTVisitor(ast);

        // to iterate through methods
        types = unit.types();
        for (final AbstractTypeDeclaration type : types) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                // Class def found
                final List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (final BodyDeclaration body : bodies) {
                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        final MethodDeclaration method = (MethodDeclaration)body;
                        if (darwinistInput.getSeqMethod().contains(method.getName().toString())) {
                            Set<String> variables = Sets.newHashSet();
                            vanisherVisitor.setVariables(variables);
                            method.accept(vanisherVisitor);
                            //                            SimpleName newMethodName = ast.newSimpleName(darwinistInput.getMethod());
                            //                            vanisherVisitor.getRewrite().replace(method.getName(), newMethodName, null);
                            break;
                        }
                    }
                }
            }
        }

        //Agregamos los imports para las relevant classes
        //        ListRewrite lrw = visitor.getRewrite().getListRewrite(unit, CompilationUnit.IMPORTS_PROPERTY);
        //        for(Entry<Object,Object> o : wrapper.getOverridingProperties().entrySet()){
        //            if(o.getKey().equals("relevantClasses")) {
        //                String classes[] = ((String)o.getValue()).split(",");
        //                    for (String string : classes) {
        //                        if (string.equals(wrapper.get))
        //                        final ImportDeclaration id = ast.newImportDeclaration();
        //                        final String classToImport = importString;
        //                        id.setName(ast.newName(classToImport.split("\\.")));
        //                        lrw.insertLast(id, null);
        //
        //                    }
        //                }
        //            }
        //        }   


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
    }



    //Hay que englobar cada formula (1 por cada ensures)
    //Luego, conjuncion de ellas con un and
    //Por ultimo, englobo toda esa conjuncion y le clavo el ! adelante.
    //Cabe destacar que por ahora tenemos solo un ensures por linea, en 1 linea.
    //A futuro, soportar de ser posible mediante parseo, que la formula de c/ensures sea multilinea.
    @SuppressWarnings("unchecked")
    public static void negatePostconditions(final DarwinistInput wrapper) {

        final String variablizedFilename = wrapper.getSeqFilesPrefix();

        String source = "";

        try {
            source = FileUtils.readFile(variablizedFilename);
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
                        //Veo si es uno de los que tengo que variabilizar
                        if (wrapper.getMethod().contains(method.getName().toString())) {
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
                                    postcondition = postcondition.concat(" && (" + formula.substring(0, formula.lastIndexOf(';')) + ")");
                                }

                                postcondition = postcondition.replaceFirst(" && ", "");

                                String negPostcondition = "    @ ensures (" + postcondition + ");\n";

                                //Tengo la postcondicion negada
                                //Tengo que reemplazar todas las lineas de ensures, por esta.
                                //Como ya saque cada ensures que encontré, basta con poner la negada al comienzo
                                int indexOfFirstNewline = blockComment.indexOf("\n", 0);
                                blockComment = blockComment.substring(0, indexOfFirstNewline + 1) + negPostcondition + blockComment.substring(indexOfFirstNewline + 1);

                                //Y finalmente, record de reemplazar la postcondicion
                                if (!replacements.containsKey(blockCommentBackup)) {
                                    replacements.put(blockCommentBackup, blockComment);
                                }
                            }
                            break;
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
            FileUtils.writeToFile(variablizedFilename, source);
        } catch (final IOException e) {
            // TODO: Define what to do!
        }

    }

    @SuppressWarnings("unchecked")
    public static void fixInput(final DarwinistInput darwinistInput) {
        /*
         * Parsear en el ast todas las variable declarations y guardarlas
         * Parsear y convertir los updateValue a sentencias de asignacion
         * Parsear el invoke para saber qué variables son parametros y cuales son para la instancia
         * Insertar todo en orden al comienzo de los metodos secuenciales
         */

        final String variablizedFilename = darwinistInput.getSeqFilesPrefix();

        String source = "";

        try {
            source = FileUtils.readFile(variablizedFilename);
        } catch (final IOException e1) {
            // TODO: Define what to do!
        }

        final IDocument document = new Document(source);

        final org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
        parser.setSource(document.get().toCharArray());

        // Parse the source code and generate an AST.
        final CompilationUnit unit = (CompilationUnit) parser.createAST(null);

        //Creo el document con su ast y rewrite donde voy a aplicar el cableo de input y el if que tira
        //la runtimeexception, con vistas a usarlo para reemplazar el substring del body del metodo original secuencial
        //concatenandole el input y englobando lo secuencial en un else
        final IDocument inputTunedDocument = new Document(";");
        org.eclipse.jdt.core.dom.ASTParser inputTunedParser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
        inputTunedParser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_STATEMENTS);
        inputTunedParser.setSource(inputTunedDocument.get().toCharArray());
        Block inputTunedBlock = (Block) inputTunedParser.createAST(null);
        AST inputTunedAst = inputTunedBlock.getAST();
        final ASTRewrite inputTunedRewrite = ASTRewrite.create(inputTunedAst);
        final ListRewrite inputTunedListRewrite = inputTunedRewrite.getListRewrite(inputTunedBlock, Block.STATEMENTS_PROPERTY);

        // to iterate through methods
        final List<AbstractTypeDeclaration> types = unit.types();
        for (final AbstractTypeDeclaration type : types) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                // Class def found
                final List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (final BodyDeclaration body : bodies) {
                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        final MethodDeclaration method = (MethodDeclaration)body;
                        //Veo si es uno de los que tengo que cablear
                        if (darwinistInput.getMethod().contains(method.getName().toString())) {
                            //Parsear en el ast todas las variable declarations y guardarlas
                            //Parsear y convertir los updateValue a sentencias de asignacion
                            //Parsear el invoke para saber qué variables son parametros y cuales son para la instancia
                            String inputSource = "";

                            try {
                                inputSource = FileUtils.readFile(darwinistInput.getSeqMethodInput());
                            } catch (final IOException e1) {
                                // TODO: Define what to do!
                            }

                            final IDocument inputDocument = new Document(inputSource);

                            final org.eclipse.jdt.core.dom.ASTParser inputParser = org.eclipse.jdt.core.dom.ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS4);
                            inputParser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);
                            inputParser.setSource(inputDocument.get().toCharArray());

                            //Declaraciones de variables en el input (de la instancia y parametros del metodo)
                            final List<VariableDeclarationStatement> vbstatements = Lists.newArrayList();
                            //Argumentos del metodo a testear
                            final List<SimpleName> methodArguments = Lists.newArrayList();
                            //UpdateValues
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
                                                        //Omito declaracion de 'method' y de 'instance'
                                                        if (fragments.size() == 1 
                                                                && (fragments.get(0).getName().getIdentifier().equalsIgnoreCase("method") 
                                                                        || fragments.get(0).getName().getIdentifier().equalsIgnoreCase("instance"))) {
                                                            continue;
                                                        } else {
                                                            //agrego la declaracion de variable actual
                                                            vbstatements.add(vds);
                                                        }

                                                    } else if (statement instanceof ExpressionStatement) {
                                                        ExpressionStatement es = (ExpressionStatement) statement;
                                                        if (es.getExpression() instanceof MethodInvocation) {
                                                            MethodInvocation esInvocation = (MethodInvocation) es.getExpression();
                                                            if (esInvocation.getName().getIdentifier().equalsIgnoreCase("updateValue")) {
                                                                List<Expression> arguments = esInvocation.arguments();
                                                                if (arguments.size() == 3) {
                                                                    //Agrego el update value actual
                                                                    variableAssignments.add(esInvocation);
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

                            List<VariableDeclarationStatement> newvds = ASTNode.copySubtrees(inputTunedAst, vbstatements);

                            List<SingleVariableDeclaration> parameters = method.parameters();
                            Map<VariableDeclarationStatement, SimpleName> expsMap = Maps.newHashMap();
                            for (int i = 0 ; i < methodArguments.size() ; ++ i) {
                                SimpleName arg = methodArguments.get(i);
                                SingleVariableDeclaration svd = parameters.get(i);
                                for (VariableDeclarationStatement vds : newvds) {
                                    VariableDeclarationFragment vdf = ((VariableDeclarationFragment)vds.fragments().get(0));
                                    if (vdf.getName().getIdentifier().equalsIgnoreCase(arg.getIdentifier())) {
                                        //Inserto en el mapa qué argumento del input corresponde a qué argumento del metodo original
                                        expsMap.put(vds, svd.getName());
                                        break;
                                    }
                                }
                            }

                            //Pasar updateValues de los variableAssignments a assignments
                            //Lista con las asignaciones producto de parsear los updatevalues
                            List<ExpressionStatement> assignmentStatements = Lists.newArrayList();
                            for (MethodInvocation updateValue : variableAssignments) {
                                List<Expression> updateArgs = updateValue.arguments();
                                if (updateArgs.size() != 3) {
                                    //TODO es un updatevalue muy raro
                                }

                                Assignment assignment = inputTunedAst.newAssignment();

                                Expression firstArg = updateArgs.get(0);
                                FieldAccess fieldAccess = inputTunedAst.newFieldAccess();
                                fieldAccess.setName(inputTunedAst.newSimpleName(((StringLiteral)updateArgs.get(1)).getLiteralValue()));

                                if (firstArg instanceof SimpleName) {
                                    SimpleName firstArgSimpleName = (SimpleName) firstArg;
                                    if (firstArgSimpleName.getIdentifier().equals("instance")) {
                                        fieldAccess.setExpression(inputTunedAst.newThisExpression());
                                    } else {
                                        fieldAccess.setExpression((Expression)ASTNode.copySubtree(inputTunedAst, firstArg));
                                    }
                                } else {
                                    fieldAccess.setExpression((Expression)ASTNode.copySubtree(inputTunedAst, firstArg));
                                }
                                assignment.setLeftHandSide(fieldAccess);
                                assignment.setOperator(Operator.ASSIGN);
                                //Extraigo el valor a asignar del updatevalue (3er argumento, indice 2)
                                Expression rhsExpression = (Expression)ASTNode.copySubtree(inputTunedAst, updateArgs.get(2));
                                assignment.setRightHandSide(rhsExpression);
                                assignmentStatements.add(inputTunedAst.newExpressionStatement(assignment));
                            }

                            //Inserciones de declaraciones de variables, declaraciones de argumentos para comparar y asignaciones del input

                            //Inserto las declaraciones de las variables
                            for (VariableDeclarationStatement vds : newvds) {
                                //Como ahora necesito las declaraciones de los argumentos para comparar luego, no los omito
                                inputTunedListRewrite.insertLast(vds, null);
                            }

                            for (ExpressionStatement assignmentStatement : assignmentStatements) {
                                //Inserto las asignaciones del input (updatevalues parseados y convertidos)
                                inputTunedListRewrite.insertLast(assignmentStatement, null);
                            }
                            
                            Set<Entry<VariableDeclarationStatement, SimpleName>> oldToNewParams = expsMap.entrySet();
                            for (Entry<VariableDeclarationStatement, SimpleName> entry : oldToNewParams) {
                                Assignment assignment = inputTunedAst.newAssignment();
                                assignment.setLeftHandSide(inputTunedAst.newSimpleName(entry.getValue().getIdentifier()));
                                assignment.setOperator(Operator.ASSIGN);
                                assignment.setRightHandSide(inputTunedAst.newSimpleName(((VariableDeclarationFragment)entry.getKey().fragments().get(0)).getName().getIdentifier()));
                                inputTunedListRewrite.insertLast(inputTunedAst.newExpressionStatement(assignment), null);
                            }

                            //Saco el ; que puse como source inicial
                            inputTunedListRewrite.remove((ASTNode)inputTunedBlock.statements().get(0), null);

                            //Reescribimos el archivo con los inputs cableados
                            final TextEdit inputTunedEdits = inputTunedRewrite.rewriteAST(inputTunedDocument, null);
                            try {
                                inputTunedEdits.apply(inputTunedDocument);
                            } catch (MalformedTreeException | BadLocationException e) {
                                // TODO: Define what to do!
                            }

                            String bodyToWrap = source.substring(method.getBody().getStartPosition() + 1, 
                                    method.getBody().getStartPosition() + method.getBody().getLength() - 1);

                            String bodyWrapped = inputTunedDocument.get() + "\n" + bodyToWrap;

                            source = source.replace(bodyToWrap, bodyWrapped);

                            break;
                        }
                    }
                }
            }
        }

        try {
            FileUtils.writeToFile(variablizedFilename, source);
        } catch (final IOException e) {
            // TODO: Define what to do!
        }

    }

    @SuppressWarnings("unchecked")
    public static boolean variablizeMethods(final DarwinistInput darwinistInput) {

        String variablizedFilename = darwinistInput.getSeqVariablizedFilename();
        if (variablizedFilename == null) {
            variablizedFilename = darwinistInput.getSeqFilesPrefix();
            darwinistInput.setSeqVariablizedFilename(variablizedFilename);
        }
        String source = "";

        Integer previousVar = null;

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

        final AST ast = unit.getAST();

        boolean variablized = false;

        String varPrefix = "customvar_";
        ASTRewrite rewrite = ASTRewrite.create(ast);
        // to iterate through methods
        final List<AbstractTypeDeclaration> types = unit.types();
        for (final AbstractTypeDeclaration type : types) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                // Class def found
                final List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (final BodyDeclaration body : bodies) {
                    if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        final MethodDeclaration method = (MethodDeclaration)body;
                        //Veo si es uno de los que tengo que variabilizar
                        if (darwinistInput.getMethod().contains(method.getName().toString())) {
                            List<Statement> statements = method.getBody().statements();
                            //Itero por los statements de abajo hacia arriba
                            //Como lo estoy haciendo sobre el codigo con input fixed, es un ifstatement
                            for (int i = statements.size() - 1 ; i >= 0 ; --i) {
                                Statement statement = statements.get(i);
                                        if (statement instanceof ExpressionStatement 
                                                && unit.lastTrailingCommentIndex(statement) >= 0
                                                && unit.firstLeadingCommentIndex(statement) >= 0) {
                                            //Es expression statement y tiene comentario
                                            Expression expression = ((ExpressionStatement) statement).getExpression();
                                            if (expression instanceof Assignment) {
                                                //Es una asignacion
                                                Assignment assignment = (Assignment) expression;
                                                ///RHS de la asignacion
                                                Expression rhs = assignment.getRightHandSide();
                                                //Veo si es una variable que ya inserte yo
                                                //Aca esto no va a andar porque si es variable repetida aumento el numero igual
                                                if (rhs instanceof SimpleName 
                                                        && ((SimpleName)rhs).toString().toLowerCase().contains(varPrefix)) {
                                                    Integer previousVarNumber = Integer.valueOf(((SimpleName)rhs).toString().toLowerCase().replace(varPrefix, ""));
                                                    if (previousVar == null) {
                                                        previousVar = previousVarNumber;
                                                    } else {
                                                        previousVar = previousVar > previousVarNumber ? previousVar : previousVarNumber;
                                                    }
                                                    continue;
                                                }
                                                //Tomar el id de mutante
                                                int commentIndex = unit.firstLeadingCommentIndex(statement);
                                                LineComment mutIDCommentNode;
                                                String mutID = null;
                                                //Ojo esto que si no hay mutId no corta
                                                while (true) {
                                                    mutIDCommentNode = ((LineComment) unit.getCommentList().get(commentIndex));
                                                    mutID = source.substring(mutIDCommentNode.getStartPosition(), mutIDCommentNode.getStartPosition() + mutIDCommentNode.getLength());
                                                    if (!mutID.contains("mutID")) {
                                                        ++commentIndex;
                                                    } else {
                                                        break;
                                                    }
                                                }
                                                //Listo, si no encontre previas, es 0, sino 1 mas que la anterior
                                                previousVar = previousVar == null ? 0 : previousVar + 1;
                                                ITypeBinding binding = assignment.resolveTypeBinding();

                                                //Buscamos todas las ocurrencias del mutID encontrado, y reemplazamos por esta variable
                                                String otherMutID = null;
                                                for (int k = i ; k >= 0 ; --k) {
                                                    statement = statements.get(k);
                                                    if (statement instanceof ExpressionStatement 
                                                            && unit.lastTrailingCommentIndex(statement) >= 0) {
                                                        //Es expression statement y tiene comentario
                                                        expression = ((ExpressionStatement) statement).getExpression();
                                                        if (expression instanceof Assignment) {
                                                            //Es una asignacion
                                                            assignment = (Assignment) expression;
                                                            ///RHS de la asignacion
                                                            rhs = assignment.getRightHandSide();
                                                            commentIndex = unit.firstLeadingCommentIndex(statement);
                                                            //Ojo esto que si no hay mutId no corta
                                                            while (true) {
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
                                                            //Generamos un nuevo nombre de variable en funcion de los ya asignados
                                                            String variableName = varPrefix + previousVar;
                                                            //Debo reemplazar la RHS por una variable del mismo tipo
                                                            //Dicha variable hay que agregarla como argumento al método
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
                                                            rewrite.replace(rhs, variableSimpleName, null);
                                                            variablized = true;
                                                            ++previousVar;
                                                        }
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                    }
                            break;
                        }
                    }
                }
            }
        }

        //Reescribimos el archivo con los metodos secuenciales que fallaron pero variabilizados
        final TextEdit edits = rewrite.rewriteAST(document, null);
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

        return variablized;
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
