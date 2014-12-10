package kodkod.engine.fol2sat;

import kodkod.engine.bool.BooleanFormula;
import kodkod.engine.bool.BooleanVisitor;
import kodkod.engine.bool.BooleanVariable;
import kodkod.engine.bool.ITEGate;
import kodkod.engine.bool.MultiGate;
import kodkod.engine.bool.NotGate;
import kodkod.engine.bool.Operator;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.satlab.SATSolver;
import kodkod.ast.Relation;
import kodkod.util.ints.IntSet;
import kodkod.instance.Bounds;

import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;


import rfm.alloy.Reporter;


public final class Bool2BEDTranslator implements BooleanVisitor<Integer, Integer> {


    // ----- Private members ----- //

    // A few constants for special chars as strings
    private final static String TAB     = "\t";
    private final static String NEWLINE = "\n";

    // The keys of this map are the labels of the (bool formula) nodes we
    // have already visited during a sharing-aware traversal. If a node was
    // visited, the value is the (bed) node number we chose for it.
    private Map<Integer, Integer> visited;

    // The smallest unused (bed) node number
    private int nextBedNode;

    // A handle to the open destination bedfile
    private Writer bedfile;


    // ----- Helper private methods for bedfile building -----

    private void makeRelsFile(String pathname, int primaryVars, Map<Relation,IntSet> varUsage, Bounds bounds) {
        try {
            int wmax = (int) Math.ceil(Math.log10((double) primaryVars));
            String wfmt = "%" + wmax + "d";

            BufferedWriter relsfile = new BufferedWriter(new FileWriter(pathname));
            for(Map.Entry<Relation,IntSet> entry : varUsage.entrySet()) {
                Relation rel = entry.getKey();
                IntSet vars  = entry.getValue();
                relsfile.write("[ " + String.format(wfmt, vars.min()) +
                    " .. " + String.format(wfmt, vars.max()) + " ]    ");

                int size  = bounds.upperBound(rel).size();
                relsfile.write(String.format(wfmt, size) + "    ");

                int arity = bounds.upperBound(rel).arity();
                StringBuilder sb = new StringBuilder();
                for(int d = 0; d < arity; ++d)
                    sb.append(bounds.upperBound(rel).project(d).size() +
                      (d + 1 < arity ? " x " : ""));
                relsfile.write(String.format("( %" + (4*wmax) + "s )    ", sb));
                relsfile.write(rel.toString() + "\n");
//                relsfile.write("\n" + bounds.upperBound(rel).toString() + "\n\n");
            }
            relsfile.close();
        } catch(Exception e) {
            System.err.println("Can't create " + pathname);
            e.printStackTrace();
        }
    }

    // Create a bedfile and initialize it
    private void startBedFile(String pathname, int primaryVars) {
        nextBedNode = 2 + primaryVars;
        try {
            bedfile = new BufferedWriter(new FileWriter(pathname));
            bedfile.write("/* BED data file (raw, handcrafted by rfm.alloy.CLI " +
              rfm.alloy.CLI.VERSION_CLI + " using Alloy " +
              rfm.alloy.CLI.VERSION_ALLOY + ") */\n\n");

            bedfile.write("inputs\n");
            for(int i = 0; i <= primaryVars; ++i) { // v0 included but unused
                if(i != 0 && i % 10 == 0) bedfile.write(NEWLINE);
                bedfile.write("v" + i);
                if(i != primaryVars) bedfile.write(" ");
            }
            bedfile.write(NEWLINE);
            bedfile.write("assign\n");
        } catch(Exception e) {
            System.err.println("Can't create " + pathname);
            e.printStackTrace();
        }
    }

    // Add a new variable node to the bedfile
    private void addBedVarNode(int nodeID, int varID) {
        addBedNode(nodeID, "v" + String.valueOf(varID), "ite", 0, 1);
    }

    // Add a new operation node to the bedfile
    private void addBedOpNode(int nodeID, String oper, int leftChild, int rightChild) {
        addBedNode(nodeID, "-", oper, leftChild, rightChild);
    }

    // Add a new node (general version) to the bedfile
    private void addBedNode(int nodeID, String varID, String oper, int leftChild, int rightChild) {
        try {
        bedfile.write(
            nodeID + TAB +
            varID + TAB +
            oper + TAB +
            leftChild + TAB +
            rightChild + NEWLINE
            );
        } catch(IOException e) {
            System.err.println("Can't write to file! Disk full, perhaps?");
            System.exit(1);
        } catch(NullPointerException e) {
            System.err.println("Can't add nodes before creating bedfile!");
            System.exit(1);
        }
    }

    // Finish and close the open bedfile
    private void finishBedFile(int rootNodeID) {
        try {
            bedfile.write("outputs\n");
            bedfile.write("mainf" + TAB + rootNodeID + NEWLINE);
            bedfile.close();
        } catch(Exception e) {
            System.err.println("Can't close the bedfile!");
            e.printStackTrace();
        }
    }



    // ----- Constructors -----

    private Bool2BEDTranslator(BooleanFormula formula) {
        visited = new TreeMap<Integer, Integer>();
        bedfile = null;
    }


    // ----- Public interface -----

    // Translate a BooleanFormula to a bedfile

    public static void translate(BooleanFormula formula, String basePath, int primaryVars, Map<Relation,IntSet> varUsage, Bounds bounds) {
        //final SATSolver solver = factory.instance();
        Bool2BEDTranslator translator = new Bool2BEDTranslator(formula);
        translator.makeRelsFile(basePath + ".rels", primaryVars, varUsage, bounds);
        translator.startBedFile(basePath + ".bed", primaryVars);
        int rootNode = formula.accept(translator, 0);
        translator.finishBedFile(rootNode);
    }


    // ----- BooleanVisitor interface implementation -----
    //
    //  Note: ints representing BED node IDs start with bn ('bed node')
    //

    // Visit an N-ary conjunction or disjunction
    // This gets translated to n-1 binary ands/ors

    public Integer visit(MultiGate gate, Integer arg) {
        int inputs = gate.size();
        assert(inputs >= 2);
        String oper = (gate.op().equals(Operator.Nary.AND) ? "and" : "or");
        int bnNewOp, bnThisInput, bnLastInput, inputLabel;

        // handle first input before the loop
        inputLabel = gate.input(0).label();
        if( ! visited.containsKey(inputLabel) )
            bnLastInput = gate.input(0).accept(this, arg);
        else
            bnLastInput = visited.get(inputLabel);

    // consider weird case of a single input
    bnNewOp = bnLastInput;

        // loop over rest of inputs, creating n-1 binary operations
        for(int i = 1; i < inputs; ++i) {
            inputLabel = gate.input(i).label();
            if( ! visited.containsKey(inputLabel) )
                bnThisInput = gate.input(i).accept(this, arg);
            else
                bnThisInput = visited.get(inputLabel);

            bnNewOp = nextBedNode++;
            addBedOpNode(bnNewOp, oper, bnLastInput, bnThisInput);
            bnLastInput = bnNewOp;
        }

        visited.put(gate.label(), bnNewOp);
        return bnNewOp;
    }


    // Visit an if-then-else(c, p, q)
    // This gets translated to ((c imp p) and (not c imp q))

    public Integer visit(ITEGate gate, Integer arg) {
        int inputLabel, bnCond, bnNotCond, bnThen, bnElse, bnImpl1, bnImpl2, bnNewITE;

        // process condition
        inputLabel = gate.input(0).label();
        if( ! visited.containsKey(inputLabel) )
             bnCond = gate.input(0).accept(this, arg);
        else bnCond = visited.get(inputLabel);

        // process then branch
        inputLabel = gate.input(1).label();
        if( ! visited.containsKey(inputLabel) )
             bnThen = gate.input(1).accept(this, arg);
        else bnThen = visited.get(inputLabel);

        // process else branch
        inputLabel = gate.input(2).label();
        if( ! visited.containsKey(inputLabel) )
             bnElse = gate.input(2).accept(this, arg);
        else bnElse = visited.get(inputLabel);

        // build "condition implies then" node
        bnImpl1 = nextBedNode++;
        addBedOpNode(bnImpl1, "imp", bnCond, bnThen);

        // build "not condition implies else" node
        bnNotCond = nextBedNode++;
        addBedOpNode(bnNotCond, "not", bnCond, bnCond);
        bnImpl2 = nextBedNode++;
        addBedOpNode(bnImpl2, "imp", bnNotCond, bnElse);

        // build final conjunction
        bnNewITE = nextBedNode++;
        addBedOpNode(bnNewITE, "and", bnImpl1, bnImpl2);

        visited.put(gate.label(), bnNewITE);
        return bnNewITE;
    }


    // Visit a negation

    public Integer visit(NotGate gate, Integer arg) {
        int bnInput;
        int inputLabel = gate.input(0).label();
        if( ! visited.containsKey(inputLabel) )
            bnInput = gate.input(0).accept(this, arg);
        else
            bnInput = visited.get(inputLabel);

        int bnNewNode = nextBedNode++;
        addBedOpNode(bnNewNode, "not", bnInput, bnInput);
        visited.put(gate.label(), bnNewNode);
        return bnNewNode;
    }


    // Visit a variable

    public Integer visit(BooleanVariable gate, Integer arg) {
        int label = gate.label();         // labels from KK are [1..N]
        addBedVarNode(label+1, label);    // BED node ID for v is v+1
        visited.put(label, label+1);      // visited label -> assigned node ID
        return label+1;                   // new node ID
    }

}

