package ar.edu.taco.stryker.api.impl;

import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
import ar.edu.taco.utils.FileUtils;

import com.google.common.collect.Lists;

public class StrykerJavaFileInstrumenter {

    private static List<String> ioImports = Lists.newArrayList(
            "java.io.BufferedReader", "java.io.BufferedWriter", "java.io.ByteArrayInputStream", "java.io.DataInputStream",
            "java.io.File", "java.io.FileInputStream", "java.io.FileNotFoundException", "java.io.FileOutputStream",
            "java.io.FileWriter", "java.io.IOException", "java.io.InputStream", "java.io.InputStreamReader",
            "java.io.OutputStream", "ar.edu.taco.utils.FileUtils");

    @SuppressWarnings("unchecked")
    public static OpenJMLInputWrapper instrument(final OpenJMLInputWrapper wrapper) {

        final String oldFilename = wrapper.getFilename();
        
        //aca la estoy cagandooo
        final String newFilename = oldFilename.replace(
                oldFilename.split("/")[oldFilename.split("/").length - 1], "instrumented/" +
                oldFilename.split("/")[oldFilename.split("/").length - 1]/*.replace(".java", "_INSTRUMENTED.java")*/);
        final String seqFileName = oldFilename.replaceFirst(
                oldFilename.split("/")[oldFilename.split("/").length - 1], "sequential/" +
                oldFilename.split("/")[oldFilename.split("/").length - 1]/*.replace(".java", "_SEQUENTIAL.java")*/);
        String source = "";

        try {
            source = FileUtils.readFile(oldFilename);
        } catch (final IOException e1) {
            // TODO: Define what to do!
        }

        final IDocument document = new Document(source);
        final ASTParser parser = ASTParser.newParser(AST.JLS4);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);
        parser.setSource(document.get().toCharArray());

        // Parse the source code and generate an AST.
        final CompilationUnit unit = (CompilationUnit) parser.createAST(null);

        //Record modifications to the AST
        unit.recordModifications();

        //Add imports to enable file output of the instrumented code
        final AST ast = unit.getAST();

        StrykerASTVisitor visitor = new StrykerASTVisitor(wrapper, ast, seqFileName);
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
                            
                            method.accept(visitor);
                            
                        }
                    }
                }
            }
        }

        //Agregamos los imports para el FileUtiles
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
            System.out.println(document.get());
            FileUtils.writeToFile(newFilename, document.get());
            
        } catch (final IOException e) {
            // TODO: Define what to do!
        }

        return new OpenJMLInputWrapper(
                newFilename,
                wrapper.getJunitInputs(),
                wrapper.getConfigurationFile(),
                wrapper.getOverridingProperties(),
                wrapper.getMethod(),
                wrapper.getMap());
    }
}
