package ar.edu.taco.linedetector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import recoder.CrossReferenceServiceConfiguration;
import recoder.ParserException;
import recoder.ProgramFactory;
import recoder.convenience.TreeWalker;
import recoder.java.CompilationUnit;
import recoder.java.Identifier;
import recoder.java.JavaProgramFactory;
import recoder.java.PrettyPrinter;
import recoder.java.SourceVisitor;
import recoder.java.Statement;
import recoder.java.StatementBlock;
import recoder.java.declaration.DeclarationSpecifier;
import recoder.java.declaration.LocalVariableDeclaration;
import recoder.java.declaration.MethodDeclaration;
import recoder.java.declaration.VariableDeclaration;
import recoder.java.declaration.VariableSpecification;
import recoder.java.expression.literal.BooleanLiteral;
import recoder.java.expression.operator.CopyAssignment;
import recoder.java.reference.TypeReference;
import recoder.java.reference.UncollatedReferenceQualifier;
import recoder.java.statement.If;
import recoder.java.statement.Then;
import recoder.java.statement.While;
import recoder.kit.Transformation;
import recoder.list.generic.ASTArrayList;
import recoder.list.generic.ASTList;
import ar.uba.dc.rfm.fajita.translation.FajitaJavaCodeTranslator.FajitaPrettyPrinter;
import ar.uba.dc.rfm.tools.FileTools;

public class LoopUnrollTransformation extends Transformation {
    
    private static boolean runAgain = true;

    public static void javaUnroll(int unroll, String sourcepath, String destpath) throws IOException {
        String contents = FileTools.readFile(sourcepath);

        CrossReferenceServiceConfiguration recoder = new CrossReferenceServiceConfiguration();

        CompilationUnit compilationUnit = null;
        try {
            compilationUnit = recoder.getProgramFactory().parseCompilationUnit(contents);
        } catch (ParserException e) {
            e.printStackTrace();
        }

        transform(unroll, compilationUnit);

        LoopUnrollPrettyPrinter.print(destpath, compilationUnit);

    }

    private static void transform(int unroll, CompilationUnit compilationUnit) {
        while(runAgain) {
            runAgain = false;
            TreeWalker treeWalker = new TreeWalker(compilationUnit);
            SourceVisitor transformVisitor = new LoopUnrollTransformationVisitor(unroll, new LoopUnrollTransformation());
            while (treeWalker.next()) {
                treeWalker.getProgramElement().accept(transformVisitor);
            }
        }

    }

    public LoopUnrollTransformation() {
        super(new CrossReferenceServiceConfiguration());
    }

    public static class LoopUnrollTransformationVisitor extends SourceVisitor {

        private int unroll = 0;

        private boolean declaredTerminatesInTime = false;
        
        private boolean shouldExamine = false;

        private final LoopUnrollTransformation transformation;

        public LoopUnrollTransformationVisitor(int unroll, final LoopUnrollTransformation transformation) {
            this.unroll = unroll;
            this.transformation = transformation;
        }
        
        @Override
        protected void visitVariableDeclaration(VariableDeclaration x) {
            if(!shouldExamine) {
                return;
            }
            super.visitVariableDeclaration(x);
            if (x.getVariables().get(0).getName() == "terminatesInTime") {
                declaredTerminatesInTime = true;
            }
        }
        
        @Override
        public void visitMethodDeclaration(MethodDeclaration x) {
            super.visitMethodDeclaration(x);
            declaredTerminatesInTime = false;
            String methodName = x.getIdentifier().getText();
            shouldExamine = !methodName.startsWith("repOk");
        }

        @Override
        public void visitWhile(While x) {
            if(!shouldExamine) {
                return;
            }
            If iff = new If(x.getGuard(), new Then(x.getBody()));
            LocalVariableDeclaration terminatesInTimeDeclaration = buildTerminatesInTimeDeclaration();
            CopyAssignment terminatesInTime = setTerminatesInTime(true);
            If finalIf = new If(x.getGuard(), new Then(terminatesInTime));

            ASTList<Statement> replacement = new ASTArrayList<>();
            StatementBlock parent = (StatementBlock) x.getASTParent();
            ASTList<Statement> block = ((StatementBlock) x.getASTParent()).getBody();
            
            int index = 0;
            
            if (!declaredTerminatesInTime) {
                replacement.add(index++, terminatesInTimeDeclaration);
            }
            for (int i = 0; i <= unroll; i++) {
                replacement.add(index++, iff.deepClone());
            }
            replacement.add(index++, finalIf);
            doReplace(x, new StatementBlock(replacement));
            declaredTerminatesInTime = true;
            transformation.runAgain = true;
        }

        private CopyAssignment setTerminatesInTime(boolean value) {
            ProgramFactory factory = new JavaProgramFactory();
            Identifier id = factory.createIdentifier("terminatesInTime");
            UncollatedReferenceQualifier fajitaGoal = factory.createUncollatedReferenceQualifier(id);
            BooleanLiteral reachedLiteral = factory.createBooleanLiteral(value);
            return factory.createCopyAssignment(fajitaGoal, reachedLiteral);
        }

        private LocalVariableDeclaration buildTerminatesInTimeDeclaration() {
            ASTList<DeclarationSpecifier> initializationSpecifiers = new ASTArrayList<DeclarationSpecifier>();
            ProgramFactory factory = new JavaProgramFactory();
            Identifier fajitaGoalId = factory.createIdentifier("terminatesInTime");
            Identifier booleanId = factory.createIdentifier("boolean");
            TypeReference booleanReference = factory.createTypeReference(booleanId);
            LocalVariableDeclaration lvd = new LocalVariableDeclaration(initializationSpecifiers, booleanReference,
                    new VariableSpecification(fajitaGoalId, new BooleanLiteral(false)));
            return lvd;
        }
    }

    public static class LoopUnrollPrettyPrinter extends PrettyPrinter {

        protected LoopUnrollPrettyPrinter(FileWriter writer, Properties properties) {
            super(writer, properties);
        }

        public static void print(String outputFile, CompilationUnit compilationUnit) throws IOException {
            File file = new File(outputFile);
            if (file.exists())
                file.delete();
            file.getAbsoluteFile().getParentFile().mkdirs();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            Properties properties = new Properties();
            properties.put(INDENTATION_AMOUNT, "4");
            FajitaPrettyPrinter printer = new FajitaPrettyPrinter(writer, properties);
            printer.visitCompilationUnit(compilationUnit);
            writer.close();
        }

        @Override
        public boolean getBooleanProperty(String key) {
            return false;
        }

    }
}
