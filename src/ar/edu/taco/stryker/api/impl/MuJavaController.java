package ar.edu.taco.stryker.api.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import mujava.OpenJavaException;
import mujava.api.Configuration;
import mujava.api.Mutant;
import mujava.api.MutantIdentifier;
import mujava.api.MutantsInformationHolder;
import mujava.app.MutantInfo;
import mujava.app.MutationRequest;
import mujava.app.Mutator;
import mujava.op.PRVO;
import openjava.ptree.ParseTreeException;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

import ar.edu.taco.engine.StrykerStage;
import ar.edu.taco.stryker.api.impl.input.MuJavaFeedback;
import ar.edu.taco.stryker.api.impl.input.MuJavaInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
import ar.edu.taco.stryker.exceptions.FatalStrykerStageException;
import ar.edu.taco.utils.FileUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

public class MuJavaController extends AbstractBaseController<MuJavaInput> {

    //	private static AtomicInteger compilationFailCount = new AtomicInteger(0);

    public static boolean feedbackOn = true;

    public static boolean fatherizationPruningOn = true;

    private static final String FILE_SEP = System.getProperty("file.separator");

    // If it is set to false then it will be assumed that if two hashes are
    // equal then that means that the two files are equal. Which of course
    // it is not necessarily true.
    private static final boolean EXTRA_DUPLICATES_CHECK = false;

    private static final int NOT_PRESENT = -1;

    private static MuJavaController instance;

    private static Logger log = Logger.getLogger(MuJavaController.class);

    private int privateI = 0;

    private int maxMethodsInFile = 1;

    //    private Map<String, Integer> filenameToMutatedLine = Maps.newConcurrentMap();
    private Map<MsgDigest, String> filesHash = Maps.newConcurrentMap();
    
    private List<OpenJMLInput> jmlInputs = new ArrayList<OpenJMLInput>(maxMethodsInFile);
    
    private String classToMutate;

    private List<MuJavaInput> fathers = Lists.newArrayList();

    public void setMaxMethodsInFile(int maxMethodsInFile) {
        this.maxMethodsInFile = maxMethodsInFile;
    }

    public synchronized static MuJavaController getInstance() {
        if (instance == null) {
            instance = new MuJavaController();
        }
        return instance;
    }

    private MuJavaController() {

    }

    @Override
    protected Runnable getRunnable() {
        return new Runnable() {

            @Override
            public void run() {
                try {
                    Configuration.add(PRVO.ENABLE_SUPER, Boolean.FALSE); //Boolean.FALSE para desactivar el uso de super
                    //                    Configuration.add(PRVO.ENABLE_THIS, Boolean.FALSE);     //Boolean.FALSE para desactivar el uso de this

                    MuJavaInput input = queue.take();

                    while (!willShutdown.get()) {
                        if (input.getMuJavaFeedback() != null && input.getMuJavaFeedback().isGetSibling()) {
                            queueNextRelevantSibling(input);
                        }
                        //						                        if (input.getMuJavaFeedback() == null) {
                        if (input.getMuJavaFeedback() == null || input.getMuJavaFeedback().isFatherable()) {
                            fatherize(input, input.getMuJavaFeedback() == null);
                        } else if (!input.getMuJavaFeedback().isFatherable()) {
                            //TODO calcular cuantos se hubieran generado en total si se lo hacia padre y sumarlo a pruned mutations
                            //No se si se puede, porque depende de los resultados de las mutaciones que hubiera tenido
                            StrykerStage.prunedFathers++;
                        }

                        input = queue.take();
                    }
                } catch (InterruptedException e1) {
                    //e1.printStackTrace();
                }
            }
        };
    }

    @Override
    protected int getQtyOfThreads() {
        return 1;
    }

    public static String obtainClassNameFromFileName(String fileName) {
        int lastBackslash = fileName.lastIndexOf("/");
        int lastDot = fileName.lastIndexOf(".");

        if (lastBackslash == NOT_PRESENT) {
            lastBackslash = 0;
        } else {
            lastBackslash += 1;
        }
        if (lastDot == NOT_PRESENT) {
            lastDot = fileName.length();
        }

        return fileName.substring(lastBackslash, lastDot);
    }

    public static boolean calculatePrunedMutations(Integer prevLMI[], Integer lineMutationIndexes[], MutantIdentifier mutatorsList[][]) {
        int prev = 0;
        for (int i = prevLMI.length - 1; i >= 0; --i) {
            int mult = 1;
            for (int j = i - 1 ; j >= 0; --j) {
                mult *= (mutatorsList[prevLMI.length - 1 - j].length + 1);
            }
            prev += prevLMI[i] * mult;
        }

        int next = 0;
        for (int i = lineMutationIndexes.length - 1; i >= 0; --i) {
            int mult = 1;
            for (int j = i - 1 ; j >= 0; --j) {
                mult *= (mutatorsList[lineMutationIndexes.length - 1 - j].length + 1);
            }
            next += lineMutationIndexes[i] * mult;
        }

        StrykerStage.prunedMutations += (Math.abs(next - prev) - 1);

        return (Math.abs(next - prev) - 1) > 0;
    }

    @SuppressWarnings("unused")
    public Boolean mutateAndQueue(MutantInfo mutantIdentifier, File fileToMutate, MuJavaInput muJavaInput, int fatherIndex, Integer[] childLineMutationIndexes,
            MutantsInformationHolder mih, Mutator mut, List<Integer> lastMutatedLines) {
        StrykerStage.mutationsGenerated++;

        if (muJavaInput.getMuJavaFeedback().getLineMutationIndexes().length < childLineMutationIndexes.length) {
            System.out.println("PROBLEMONNN");
        }
        log.debug("Generation finished. Generated mutants: 1");
        log.debug("Creating files for mutants");
        log.debug("Check that mutant is unique: "+ mutantIdentifier);
        File tempFile = new File(mutantIdentifier.getPath());
        //        int mutatedLine = mutantIdentifier.getMutatedLine();

        MsgDigest msgDigest = null;
        DigestOutputStream dos;
        File duplicatesTempFile = null;
        if (EXTRA_DUPLICATES_CHECK) {
            try {
                String content = FileUtils.readFile(mutantIdentifier.getPath());
                String tunedContent = "";
                String lines[] = content.split("\n");
                for (int i = 0; i < lines.length; ++i) {
                    String line = lines[i];
                    if (line.contains("//mutGenLimit")) {
                        tunedContent += line.substring(0, line.indexOf("//mutGenLimit")) + "\n";
                    } else {
                        tunedContent += line + "\n";
                    }
                }
                duplicatesTempFile = File.createTempFile("forDuplicates", null);
                dos = new DigestOutputStream(new FileOutputStream(duplicatesTempFile, false), MessageDigest.getInstance("MD5"));
                dos.write(tunedContent.getBytes());
                dos.flush();
                dos.close();
                byte[] digest = dos.getMessageDigest().digest();
                msgDigest = new MsgDigest(digest);
            } catch (Exception e) {
                // Handle Exceptions
            }
        } else {
            msgDigest = new MsgDigest(mutantIdentifier.getMD5digest());
            duplicatesTempFile = tempFile;
        }
        log.debug("fileToMutate= "+fileToMutate);
        log.trace("fileToMutate.getAbsolutePath()= "+fileToMutate.getAbsolutePath());
        //        log.trace("mutatedLine= "+mutatedLine);
        //        log.trace("filenameToMutatedLine.get(fileToMutate.getAbsolutePath())= "+filenameToMutatedLine.get(fileToMutate
        //                .getAbsolutePath()));
        //        Integer lastMutatedLine = filenameToMutatedLine.get(fileToMutate.getAbsolutePath());
        //        log.debug("last mutated line = "+lastMutatedLine);
        if (filesHash.containsKey(msgDigest)) {
            //        if (lastMutatedLine != null && (lastMutatedLine > mutatedLine) || filesHash.containsKey(msgDigest)) {
            //            if(lastMutatedLine != null) {
            //                log.debug("lastmutadtedline > mutadtedline = "+(lastMutatedLine > mutatedLine));
            //            } else {
            //                log.debug("lastmutadtedline  = null");
            //            }
            log.debug("filesHash.containsKey(msgDigest) = "+filesHash.containsKey(msgDigest));
            //            if (EXTRA_CHECK && filesHash.containsKey(msgDigest)) {
            if (EXTRA_DUPLICATES_CHECK && isFalseDuplicate(filesHash.get(msgDigest), duplicatesTempFile)) {
                //                if (isFalseDuplicate(filesHash.get(msgDigest), duplicatesTempFile)) {
                // If it is a false duplicate we don't have to delete the file
                log.debug("False duplicated file");
                //                }
            } else {
                // We have to delete this new mutant since it will be a duplicate
                log.debug("Duplicated file");
                //mutantCount.decrementAndGet();
                if (!tempFile.delete()) {
                    log.error("Couldn't remove file " + tempFile.getName());
                }
                StrykerStage.duplicateMutations++;
                return null;
            }
        }

        String currentClasspath = System.getProperty("java.class.path")+OpenJMLController.PATH_SEP+System.getProperty("user.dir")+FILE_SEP+"generated";

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        long nanoPrev = System.currentTimeMillis();
        int compilationResult = javaCompiler.run(null, new NullOutputStream(), new NullOutputStream(),  new String[]{"-classpath", currentClasspath, tempFile.getAbsolutePath()});
        StrykerStage.compilationMillis += System.currentTimeMillis() - nanoPrev;
        if ( compilationResult == 0 ){
            log.info("Compilation succeeded. Adding this file");

            filesHash.put(msgDigest, duplicatesTempFile.getAbsolutePath());
            //            filenameToMutatedLine.put(tempFile.getAbsolutePath(), mutatedLine);
            MuJavaFeedback newFeedback = new MuJavaFeedback(childLineMutationIndexes, muJavaInput.getMuJavaFeedback().getLineMutatorsList(), lastMutatedLines, muJavaInput.getMuJavaFeedback().getMutableLines());
            newFeedback.setMut(mut);
            newFeedback.setMutantsInformationHolder(mih);
            newFeedback.setFatherIndex(fatherIndex);
            OpenJMLInput output = new OpenJMLInput(tempFile.getAbsolutePath(),
                    muJavaInput.getMethod(),
                    muJavaInput.getConfigurationFile(),
                    muJavaInput.getOverridingProperties(),
                    muJavaInput.getOriginalFilename(),
                    newFeedback,
                    muJavaInput.getMutantsToApply(),
                    muJavaInput.getSyncObject());
            log.debug("Adding task to the list");
            jmlInputs.add(output);
            if(jmlInputs.size() >= maxMethodsInFile) {
                OpenJMLInputWrapper wrapper = createJMLInputWrapper(jmlInputs, classToMutate);
                log.info("Creating output for OpenJMLController");

                //                if (feedbackOn) {
                //                    wrapper = StrykerJavaFileInstrumenter.instrumentForSequentialOutput(wrapper, lastMutatedLines);
                //                }

                OpenJMLController.getInstance().enqueueTask(wrapper);
                log.debug("Adding task to the OpenJMLController");
                jmlInputs.clear();
            }
            StrykerStage.mutationsQueuedToOJMLC++;
            return true;
        } else {
            StrykerStage.nonCompilableMutations++;
            return false;
        }
    }

    private int getFeedbackIndex(Integer mutID, List<Integer> curLineNumbersList, List<Integer> mutableLines) {
        int lineNumber = mutableLines.get(mutID);

        //        int curMutID = -1;

        //        for (int i = 0; i < opTypes.size(); ++i) {
        //            if (!(opTypes.get(i) != null)) {
        //                continue;
        //            }
        //            if (++curMutID == mutID) {
        //                return opTypes.size() - i - 1;
        //            }
        //        }
        return curLineNumbersList.indexOf(lineNumber);
    }

    private ImmutablePair<List<MutantIdentifier>, Integer[]> calculateNextRelevantSonMutantIdentifiersLists(
            Integer[] lineMutationIndexes, 
            MutantIdentifier[][] mutatorsList,
            int feedback,
            List<Pair<Integer, Integer>> sideChangeIndexes,
            boolean mutateRight, 
            boolean gaveUNSAT) {
        List<MutantIdentifier> ret = Lists.newArrayList();

        //TODO si se acaban tooodos los indices, que hacemos?? Creo que esto es cuando retorno null
        Integer prevLMI[] = lineMutationIndexes.clone();
        try {
            int curIndex = lineMutationIndexes[feedback];

            if (((!mutateRight && !gaveUNSAT) || (mutateRight && gaveUNSAT)) && sideChangeIndexes.get(feedback) != null
                    && curIndex < sideChangeIndexes.get(feedback).getRight()) {
                curIndex = sideChangeIndexes.get(feedback).getRight();
            } else if (!mutateRight && sideChangeIndexes.get(feedback) != null && gaveUNSAT 
                    && curIndex < sideChangeIndexes.get(feedback).getLeft()) {
                curIndex = sideChangeIndexes.get(feedback).getLeft();
            }

            while (curIndex + 1 > mutatorsList[lineMutationIndexes.length - feedback - 1].length) { //si me paso de rosca de la linea
                if (++feedback >= lineMutationIndexes.length) {
                    Integer newLMI[] = new Integer[prevLMI.length];
                    
                    for (int i = 0; i < newLMI.length; ++i) {
                        newLMI[i] = mutatorsList[newLMI.length - i - 1].length;
                    }
                    
                    MuJavaController.calculatePrunedMutations(prevLMI, newLMI, mutatorsList);
                    StrykerStage.prunedMutations++; //Porque el calculador no ve el ultimo que se saltea en este caso

                    return null;
                }
                curIndex = lineMutationIndexes[feedback];
            }
            lineMutationIndexes[feedback] = curIndex + 1;
            for (int i = feedback - 1; i >= 0; --i) {
                lineMutationIndexes[i] = 0;
            }
            for (int i = 0; i < lineMutationIndexes.length; ++i) {
                if (lineMutationIndexes[i] > 0) {
                    ret.add(mutatorsList[lineMutationIndexes.length - i - 1][mutatorsList[lineMutationIndexes.length - i - 1].length - lineMutationIndexes[i]]);
                }
            }

            if (calculatePrunedMutations(prevLMI, lineMutationIndexes, mutatorsList)) {
                StrykerStage.relevantFeedbacksFound++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Dio ArrayIndexOutOfBoundsException");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Null Pointer");
        }

        return new ImmutablePair<List<MutantIdentifier>, Integer[]>(ret, lineMutationIndexes);
    }

    public boolean isSkippeableLeftMutantIdentifier(MutantIdentifier identifier) {
        if (!identifier.getMutOp().equals(Mutant.PRVOL) && !identifier.getMutOp().equals(Mutant.PRVOL_SMART)) {
            return false;
        } else {
        	return identifier.isMutantATailChangeOfTheLeftSideOfAnAssignmentExpression();
//            String original = identifier.getOriginal().toString();
//            String mutant = identifier.getMutant().toString();
//            int lastOriginalDotIndex = original.lastIndexOf(".");
//            int lastMutantDotIndex = mutant.lastIndexOf(".");
//            return lastMutantDotIndex != -1 && lastOriginalDotIndex != -1 &&
//                    original.substring(lastOriginalDotIndex).equals(mutant.substring(lastMutantDotIndex));
        }
    }
    
    
 
    
    //<[][], <[i1, i2, i3], [<j1,k1>, <j2,k2>...]>>
    
    public ImmutablePair<MutantIdentifier[][], Pair<List<Integer>, List<Pair<Integer, Integer>>>> getMutatorsList(List<MutantIdentifier> mutantIdentifiers) {
        Map<Integer, Pair<Pair<List<MutantIdentifier>, List<MutantIdentifier>>, List<MutantIdentifier>>> theMap = Maps.newTreeMap();

        for (MutantIdentifier mutantIdentifier : mutantIdentifiers) {
            Integer affectedLine = mutantIdentifier.isGuardMutation() ? mutantIdentifier.getMutGenLimitLine() : mutantIdentifier.getAffectedLine();
            Pair<Pair<List<MutantIdentifier>, List<MutantIdentifier>>, List<MutantIdentifier>> theList = theMap.get(affectedLine);
            if (theList != null && theList.getRight() != null) {
                if (mutantIdentifier.getMutOp().equals(Mutant.PRVOL) || mutantIdentifier.getMutOp().equals(Mutant.PRVOL_SMART)) {
                    if (isSkippeableLeftMutantIdentifier(mutantIdentifier)) {
                        theList.getLeft().getRight().add(mutantIdentifier);
                    } else {
                        theList.getLeft().getLeft().add(mutantIdentifier);
                    }
                } else if (mutantIdentifier.isGuardMutation()) {
                    theList.getRight().add(mutantIdentifier);
//                    leftIndexMap.put(affectedLine, leftIndexMap.get(affectedLine) + 1);
                } else {
                    theList.getRight().add(mutantIdentifier);
//                    leftIndexMap.put(affectedLine, leftIndexMap.get(affectedLine) + 1);
                }
            } else {
                List<MutantIdentifier> newRightList = Lists.newLinkedList();
                List<MutantIdentifier> newLeftSkippableList = Lists.newLinkedList();
                List<MutantIdentifier> newLeftUnskippableList = Lists.newLinkedList();
                if (mutantIdentifier.getMutOp().equals(Mutant.PRVOL) || mutantIdentifier.getMutOp().equals(Mutant.PRVOL_SMART)) {
                    if (isSkippeableLeftMutantIdentifier(mutantIdentifier)) {
                        newLeftSkippableList.add(mutantIdentifier);
                    } else {
                        newLeftUnskippableList.add(mutantIdentifier);
                    }
//                    leftIndexMap.put(affectedLine, 0);
                } else if (mutantIdentifier.isGuardMutation()) {
//                  leftIndexMap.put(affectedLine, 1);
                    newRightList.add(mutantIdentifier);
                } else {
//                    leftIndexMap.put(affectedLine, 1);
                    newRightList.add(mutantIdentifier);
                }

                theMap.put(affectedLine, new ImmutablePair<Pair<List<MutantIdentifier>, List<MutantIdentifier>>, List<MutantIdentifier>>(
                        new ImmutablePair<List<MutantIdentifier>, List<MutantIdentifier>>(newLeftUnskippableList, newLeftSkippableList), newRightList));
            }
        }
        
        MutantIdentifier[][] mutantIdentifiersList = new MutantIdentifier[theMap.size()][];
        List<Pair<Pair<List<MutantIdentifier>, List<MutantIdentifier>>, List<MutantIdentifier>>> theEntrySet = Lists.newLinkedList(theMap.values());
        List<Pair<Integer, Integer>> leftIndexList = Lists.newArrayList();
        LinkedList<Pair<Integer, Integer>> correctLeftIndexList = Lists.newLinkedList();

        
        List<List<MutantIdentifier>> theLists = Lists.newArrayList();
        for (Pair<Pair<List<MutantIdentifier>, List<MutantIdentifier>>, List<MutantIdentifier>> pair : theEntrySet) {
            List<MutantIdentifier> theList = Lists.newArrayList();

            List<MutantIdentifier> rightIdentifiers = pair.getRight();
            List<MutantIdentifier> leftSkippableIdentifiers = pair.getLeft().getRight();
            List<MutantIdentifier> leftUnskippableIdentifiers = pair.getLeft().getLeft();
            
            leftIndexList.add(new ImmutablePair<Integer, Integer>(rightIdentifiers.size() + leftSkippableIdentifiers.size(), rightIdentifiers.size()));
            
            theList.addAll(leftUnskippableIdentifiers);
            theList.addAll(leftSkippableIdentifiers);
            theList.addAll(rightIdentifiers);
            
            theLists.add(theList);
        }

        for (Pair<Integer, Integer> integer : leftIndexList) {
            correctLeftIndexList.addFirst(integer);
        }

        LinkedList<Integer> correctLineNumbersList = Lists.newLinkedList();

        for (Integer integer : theMap.keySet()) {
            correctLineNumbersList.addFirst(integer);
        }

        int i = 0;

        for (List<MutantIdentifier> theList : theLists) {
            MutantIdentifier[] curArray = new MutantIdentifier[theList.size()];
            int j = 0;
            for (MutantIdentifier mutantIdentifier : theList) {
                curArray[j] = mutantIdentifier;
                ++j;
            }
            mutantIdentifiersList[i] = curArray;
            ++i;
        }

        return new ImmutablePair<MutantIdentifier[][], Pair<List<Integer>, List<Pair<Integer, Integer>>>>(mutantIdentifiersList, new ImmutablePair<List<Integer>, List<Pair<Integer, Integer>>>(correctLineNumbersList, correctLeftIndexList));
    }

    public void fatherize(MuJavaInput input, boolean first) {
        File firstDir = null;
        File firstFile = null;
        if (first) {
            StrykerJavaFileInstrumenter.cleanMutGenLimit0(input);
            try {
                firstDir = createWorkingDirectory();
                File old = new File(input.getFilename());
                firstFile = new File(firstDir.getAbsolutePath() + input.getFilename().substring(input.getFilename().lastIndexOf(FILE_SEP)));
                firstFile.createNewFile();
                Files.copy(old, firstFile);
            } catch (IOException e) {
                // Handle Exceptions
            }
        } else {
            StrykerJavaFileInstrumenter.decrementUnmutatedLimits(input);
        }
        MuJavaInput inputAsFather = new MuJavaInput(first ? firstFile.getAbsolutePath() : input.getFilename(), 
                input.getMethod(), 
                input.getMutantsToApply(), input.getQtyOfGenerations(), input.getConfigurationFile(), 
                input.getOverridingProperties(), input.getOriginalFilename(), input.getSyncObject());
        try {
            File fileToMutate;
            String methodToCheck;
            HashSet<Mutant> mutOps;
            MuJavaInput muJavaInput;

            fileToMutate = new File(input.getFilename());
            if (!fileToMutate.exists()) {
                throw new IllegalStateException("The file " + input.getFilename() + " doesn't exist. Can't continue.");
                //              return Lists.newArrayList();
            }
            methodToCheck = input.getMethod();
            mutOps = Sets.newHashSet(input.getMutantsToApply());
            classToMutate = obtainClassNameFromFileName(input.getFilename());
            muJavaInput = inputAsFather;

            File tmpDir = createWorkingDirectory();

            log.debug("Generating mutants...");

            String[] methods1 = new String[] {methodToCheck};
            Mutant[] mutops1 = new Mutant[mutOps.size()];
            mutOps.toArray(mutops1);
            MutationRequest req1 = new MutationRequest(classToMutate, methods1, mutops1, fileToMutate.getParent() + FILE_SEP, tmpDir.getAbsolutePath() + FILE_SEP);
            Mutator mut = new Mutator(req1);

            Map<String, MutantsInformationHolder> mutantsInformationHoldersMap = mut.obtainMutants();
            MutantsInformationHolder mutantsInformationHolder = null;
            for (Entry<String, MutantsInformationHolder> mutant : mutantsInformationHoldersMap.entrySet()) {
                if (mutant.getKey().equalsIgnoreCase(input.getMethod())) {
                    mutantsInformationHolder = mutant.getValue();
                }
            }
            List<MutantIdentifier> mutantIdentifiers = mutantsInformationHolder.getMutantsIdentifiers();
            //Me quedo solo con los mutant identifiers que afectan solo 1 linea en el metodo en cuestion.
            mutantIdentifiers = new LinkedList<MutantIdentifier>(Collections2.filter(mutantIdentifiers, new Predicate<MutantIdentifier>() {
                public boolean apply(MutantIdentifier arg0) {
                    return arg0.isOneLineInMethodOp() && (!arg0.getMutOp().equals(Mutant.PRVOL) || isSkippeableLeftMutantIdentifier(arg0));
                    //                    return arg0.isOneLineInMethodOp() && !arg0.getMutant().toString().contains("super");
                };
            }));

            Pair<MutantIdentifier[][], Pair<List<Integer>, List<Pair<Integer, Integer>>>> mutatorsData = getMutatorsList(mutantIdentifiers);
            MutantIdentifier[][] mutatorsList = mutatorsData.getLeft();
            if (mutatorsList.length == 0) {
                return; //No tiene más mutaciones posibles, es una hoja del arbol de mutaciones.
            }
            
            List<Integer> mutableLines;
            if (first) {
                File fileToMutateForFirst;
                String methodToCheckForFirst;
                HashSet<Mutant> mutOpsForFirst;

                fileToMutateForFirst = new File(input.getFilename());
                if (!fileToMutateForFirst.exists()) {
                    throw new IllegalStateException("The file " + input.getFilename() + " doesn't exist. Can't continue.");
                    //              return Lists.newArrayList();
                }
                methodToCheckForFirst = input.getMethod();
//                mutOpsForFirst = Sets.newHashSet();
//                mutOpsForFirst.add(Mutant.PRVOL); //solo de izquierda
//                mutOpsForFirst.add(Mutant.PRVOR_REFINED);
//                mutOpsForFirst.add(Mutant.PRVOU_REFINED);
//                mutOpsForFirst.add(Mutant.AODS);
//                mutOpsForFirst.add(Mutant.AODU);
//                mutOpsForFirst.add(Mutant.AOIS);
//                mutOpsForFirst.add(Mutant.AOIU);
//                mutOpsForFirst.add(Mutant.AORB);
//                mutOpsForFirst.add(Mutant.AORS);
//                mutOpsForFirst.add(Mutant.AORU);
//                mutOpsForFirst.add(Mutant.ASRS);
//                mutOpsForFirst.add(Mutant.COD);
//                mutOpsForFirst.add(Mutant.COI);
//                mutOpsForFirst.add(Mutant.COR);
//                mutOpsForFirst.add(Mutant.LOD);
//                // mutOpsForFirst.add(Mutant.LOI);
//                mutOpsForFirst.add(Mutant.LOR);
//                mutOpsForFirst.add(Mutant.ROR);
//                mutOpsForFirst.add(Mutant.SOR); 

                String classToMutateForFirst = obtainClassNameFromFileName(input.getFilename());

                File tmpDirForFirst = createWorkingDirectory();

                log.debug("Generating mutants...");

                String[] methods1ForFirst = new String[] {methodToCheckForFirst};
                Mutant[] mutops1ForFirst = new Mutant[input.getMutantsToApply().size()];
                input.getMutantsToApply().toArray(mutops1ForFirst);
                MutationRequest req1ForFirst = new MutationRequest(classToMutateForFirst, methods1ForFirst, mutops1ForFirst, fileToMutateForFirst.getParent() + FILE_SEP, tmpDirForFirst.getAbsolutePath() + FILE_SEP);
                Mutator mutForFirst = new Mutator(req1ForFirst);

                Map<String, MutantsInformationHolder> mutantsInformationHoldersMapForFirst = mutForFirst.obtainMutants();
                MutantsInformationHolder mutantsInformationHolderForFirst = null;
                for (Entry<String, MutantsInformationHolder> mutant : mutantsInformationHoldersMapForFirst.entrySet()) {
                    if (mutant.getKey().equalsIgnoreCase(input.getMethod())) {
                        mutantsInformationHolderForFirst = mutant.getValue();
                    }
                }
                List<MutantIdentifier> mutantIdentifiersForFirst = mutantsInformationHolderForFirst.getMutantsIdentifiers();
                //Me quedo solo con los mutant identifiers que afectan solo 1 linea en el metodo en cuestion.
                mutantIdentifiersForFirst = new LinkedList<MutantIdentifier>(Collections2.filter(mutantIdentifiersForFirst, new Predicate<MutantIdentifier>() {
                    public boolean apply(MutantIdentifier arg0) {
                        return arg0.isOneLineInMethodOp();//solo identifiers no-skippeables
                    };
                }));

                Pair<MutantIdentifier[][], Pair<List<Integer>, List<Pair<Integer, Integer>>>> mutatorsDataForFirst = getMutatorsList(mutantIdentifiersForFirst);
                MutantIdentifier[][] mutatorsListForFirst = mutatorsDataForFirst.getLeft();
                if (mutatorsListForFirst.length == 0) {
                    return; //No tiene más mutaciones posibles, es una hoja del arbol de mutaciones.
                }
                
                List<Integer> invertedMutableLinesListForFirst = mutatorsDataForFirst.getRight().getLeft();
                LinkedList<Integer> straightMutableLinesListForFirst = Lists.newLinkedList();
                for (Integer integer : invertedMutableLinesListForFirst) {
                    straightMutableLinesListForFirst.addFirst(integer);
                }
                mutableLines = Lists.newArrayList(straightMutableLinesListForFirst);
            } else {
                mutableLines = input.getMuJavaFeedback().getMutableLines();
            }
            
            Integer[] lineMutationIndexes = new Integer[mutatorsList.length];
            for (int i = 0; i < lineMutationIndexes.length; ++i) {
                lineMutationIndexes[i] = 0;
            }//inicializar todo en 0 si no lo hace
            
//            if (first) {
//                lineMutationIndexes[0] = 9;
//                lineMutationIndexes[1] = 3;
//                lineMutationIndexes[2] = 0;
//                lineMutationIndexes[3] = 0;
//            }
            
            
            MuJavaFeedback newFeedback = new MuJavaFeedback(lineMutationIndexes, mutatorsList, null, mutableLines);
            newFeedback.setSkipUntilMutID(null);
            if (input.getMuJavaFeedback() != null) {
                newFeedback.setMutantsInformationHolder(input.getMuJavaFeedback().getMutantsInformationHolder());
                newFeedback.setMut(input.getMuJavaFeedback().getMut());
//                newFeedback.setLastMutatedLines(input.getMuJavaFeedback().getLastMutatedLines());
            }
            muJavaInput.setMuJavaFeedback(newFeedback);


            fathers.add(muJavaInput);//se agrega el nuevo padre a la lista de padres
            //			System.out.println("Nuevo padre con index: " + (fathers.size() - 1));

            //Encolo el hijo

            MuJavaInput baseSibling = new MuJavaInput(muJavaInput.getFilename(), muJavaInput.getMethod(), muJavaInput.getMutantsToApply(), muJavaInput.getQtyOfGenerations(), muJavaInput.getConfigurationFile(), muJavaInput.getOverridingProperties(), muJavaInput.getOriginalFilename(), muJavaInput.getSyncObject());
            MuJavaFeedback baseSiblingFeedback = new MuJavaFeedback(lineMutationIndexes, muJavaInput.getMuJavaFeedback().getLineMutatorsList(), new ArrayList<Integer>(), muJavaInput.getMuJavaFeedback().getMutableLines());
            baseSiblingFeedback.setMut(mut);
            baseSiblingFeedback.setMutantsInformationHolder(mutantsInformationHolder);
            baseSiblingFeedback.setFatherIndex(fathers.size() - 1);
            baseSiblingFeedback.setMutateRight(true);
            baseSibling.setMuJavaFeedback(baseSiblingFeedback);
            
            
            queueNextRelevantSibling(baseSibling);
            
        } catch (ClassNotFoundException | OpenJavaException e) {
            e.printStackTrace();
            // Handle Exceptions
        } catch (ParseTreeException e) {
            e.printStackTrace();
            // Handle Exceptions
        }
    }

    public void queueNextRelevantSibling(MuJavaInput input) {
        try {
            MuJavaInput father = fathers.get(input.getMuJavaFeedback().getFatherIndex());
            MutantIdentifier[][] mutatorsList = father.getMuJavaFeedback().getLineMutatorsList();
            
            Integer[] lineMutationIndexes = input.getMuJavaFeedback().getLineMutationIndexes();
            

            File fileToMutate;
            String methodToCheck;
            HashSet<Mutant> mutOps;

            fileToMutate = new File(father.getFilename());
            if (!fileToMutate.exists()) {
                throw new IllegalStateException("The file " + father.getFilename() + " doesn't exist. Can't continue.");
                //              return Lists.newArrayList();
            }
            methodToCheck = father.getMethod();
            mutOps = Sets.newHashSet(father.getMutantsToApply());
            classToMutate = obtainClassNameFromFileName(father.getFilename());

            //Encolo el hijo
            Boolean validMut = false;
            while (!validMut) {
                final File tmpDir = createWorkingDirectory();

                log.debug("Generating mutants...");

                String[] methods1 = new String[] {methodToCheck};
                Mutant[] mutops1 = new Mutant[mutOps.size()];
                mutOps.toArray(mutops1);
                MutationRequest req1 = new MutationRequest(classToMutate, methods1, mutops1, fileToMutate.getParent() + FILE_SEP, tmpDir.getAbsolutePath() + FILE_SEP);
                Mutator mut = new Mutator(req1);

                Map<String, MutantsInformationHolder> mutantsInformationHoldersMap = mut.obtainMutants();
                MutantsInformationHolder mutantsInformationHolder = null;
                for (Entry<String, MutantsInformationHolder> mutant : mutantsInformationHoldersMap.entrySet()) {
                    if (mutant.getKey().equalsIgnoreCase(father.getMethod())) {
                        mutantsInformationHolder = mutant.getValue();
                    }
                }

                List<MutantIdentifier> mutantIdentifiers = mutantsInformationHolder.getMutantsIdentifiers();
                //Me quedo solo con los mutantidentifiers que afectan solo 1 linea en el metodo en cuestion.
                mutantIdentifiers = new LinkedList<MutantIdentifier>(Collections2.filter(mutantIdentifiers, new Predicate<MutantIdentifier>() {
                    public boolean apply(MutantIdentifier arg0) {
                        return arg0.isOneLineInMethodOp() && (!arg0.getMutOp().equals(Mutant.PRVOL) || isSkippeableLeftMutantIdentifier(arg0));
                        //                        return arg0.isOneLineInMethodOp() && !arg0.getMutant().toString().contains("super");
                    };
                }));
                Pair<MutantIdentifier[][], Pair<List<Integer>, List<Pair<Integer, Integer>>>> mutatorsPair = getMutatorsList(mutantIdentifiers);
                mutatorsList = mutatorsPair.getLeft();
                if (mutatorsList.length == 0) {
                    return; //No tiene mas mutaciones posibles, es una hoja del arbol de mutaciones.
                }

                int feedbackIndex = (input.getMuJavaFeedback() == null || input.getMuJavaFeedback().getSkipUntilMutID() == null) ? 0 : getFeedbackIndex(
                        input.getMuJavaFeedback().getSkipUntilMutID(), mutatorsPair.getRight().getLeft(), input.getMuJavaFeedback().getMutableLines());

                ImmutablePair<List<MutantIdentifier>, Integer[]> nextRelevantSiblingMutantIdentifiersLists = 
                        calculateNextRelevantSonMutantIdentifiersLists(lineMutationIndexes.clone(), mutatorsList, feedbackIndex, mutatorsPair.getRight().getRight(), input.getMuJavaFeedback().isMutateRight(), input.getMuJavaFeedback().isUNSAT());

                if (nextRelevantSiblingMutantIdentifiersLists == null) {
                    System.out.println("No hay mas siblings para este padre!");
                    return;
                } else if (nextRelevantSiblingMutantIdentifiersLists.getRight().length > mutatorsList.length) {
                    System.out.println("ALTO PROBLEMA");
                } else if (nextRelevantSiblingMutantIdentifiersLists.getLeft().size() == 0) {
                    System.out.println("LOCOOOOO, NO TENGO NADA A LA IZQUIERDAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }

                lineMutationIndexes = nextRelevantSiblingMutantIdentifiersLists.getRight();

                System.out.print("Por generar el caso: [");
                for (Integer integer : lineMutationIndexes) {
                    System.out.print(" " + integer);
                }
                System.out.println(" ] y el index de su padre es: " + input.getMuJavaFeedback().getFatherIndex());
                
                System.out.print("Por generar el caso: [");
                for (MutantIdentifier identifier : nextRelevantSiblingMutantIdentifiersLists.getLeft()) {
                    System.out.print(" " + identifier.toString());
                }
                System.out.println(" ] y el index de su padre es: " + input.getMuJavaFeedback().getFatherIndex());

                if (!Mutator.checkCompatibility(nextRelevantSiblingMutantIdentifiersLists.getLeft())) {
                    System.out.println("Genero una lista de mutaciones donde al menos 2 de ellas afectan la misma linea");
                    throw new IllegalArgumentException();
                }

                List<Integer> mutatedLines = Lists.newArrayList();

                for (MutantIdentifier identifier : nextRelevantSiblingMutantIdentifiersLists.getLeft()) {
                    Integer affectedLine = identifier.isGuardMutation() ? identifier.getMutGenLimitLine() : identifier.getAffectedLine();
                    mutatedLines.add(affectedLine);
                }

                List <MutantIdentifier> curIdentifiers = mutantsInformationHolder.getMutantsIdentifiers();
                curIdentifiers.clear();
                curIdentifiers.addAll(nextRelevantSiblingMutantIdentifiersLists.getLeft());
                List<MutantInfo> mil = mut.writeMutants(father.getMethod(), mutantsInformationHolder, true);
                MutantInfo mutantInfo = mil.get(0);
                mut.resetMutantFolders();
                //            HashMap<String, List<String>> mutantsFolders = mut.mutantsFolders;
                //            mut.resetMutantFolders();
                //            List<String> folders = mutantsFolders.entrySet().iterator().next().getValue();

                validMut = mutateAndQueue(mutantInfo, fileToMutate, father, input.getMuJavaFeedback().getFatherIndex(), lineMutationIndexes, mutantsInformationHolder, mut, mutatedLines);
                if (validMut == null) {
                    System.out.println("Mutacion omitida por ser duplicado");
                    validMut = false;
                    input.getMuJavaFeedback().setSkipUntilMutID(null);
                } else if (!validMut) {
                    System.out.println("Mutacion omitida por no compilar");
                    MuJavaInput mujavainput = new MuJavaInput(mutantInfo.getPath(), input.getMethod(), input.getMutantsToApply(), new AtomicInteger(0), input.getConfigurationFile(), input.getOverridingProperties(), input.getOriginalFilename(), input.getSyncObject());
                    MuJavaFeedback newFeedback = new MuJavaFeedback(lineMutationIndexes, father.getMuJavaFeedback().getLineMutatorsList(), mutatedLines, father.getMuJavaFeedback().getMutableLines());
                    newFeedback.setMut(mut);
                    newFeedback.setMutantsInformationHolder(mutantsInformationHolder);
                    newFeedback.setFatherIndex(input.getMuJavaFeedback().getFatherIndex());

                    newFeedback.setFatherable(true);
                    newFeedback.setGetSibling(false);
                    mujavainput.setMuJavaFeedback(newFeedback);
                    MuJavaController.getInstance().enqueueTask(mujavainput);
                }
            }
            System.out.println("Mutacion valida");
        } catch (ClassNotFoundException e) {
            // Handle Exceptions
        } catch (OpenJavaException e) {
            // Handle Exceptions
        } catch (ParseTreeException e) {
            // Handle Exceptions
        }
    }

    private OpenJMLInputWrapper createJMLInputWrapper(
            List<OpenJMLInput> jmlInputs, String classToMutate) {
        log.debug("jmlInputs: " + jmlInputs.toString());
        if( jmlInputs.isEmpty() ){
            throw new IllegalArgumentException("You must provide at least one OpenJMLInput.");
        }
        OpenJMLInput oji = jmlInputs.remove(0);

        String originalFilename = oji.getOriginalFilename();
        String originalMethod = oji.getMethod();
        File newDir = createWorkingDirectory();
        String dirString = newDir.getAbsolutePath();
        String newPath = "a"+dirString.substring(dirString.lastIndexOf(FILE_SEP)+1).replaceAll("-", "")+(FILE_SEP+"aOpenJMLInWrap" + privateI);
        File newDir2 = new File(newDir, newPath);

        Map<String,OpenJMLInput> map = new HashMap<String, OpenJMLInput>();
        int index = 0;
        try {
            newDir2.mkdirs();
            File newFile = new File(newDir2, classToMutate + ".java");
            if(!newFile.createNewFile()){
                throw new IllegalStateException("Couldn't create the file");
            }
            File from = new File(oji.getFilename());
            String methodName = oji.getMethod();
            index++;
            OpenJMLInput newInput = new OpenJMLInput(oji.getFilename(), methodName, oji.getConfigurationFile(), oji.getOverridingProperties(), oji.getOriginalFilename(), oji.getFeedback(), oji.getMutantsToApply(), oji.getSyncObject());
            map.put(methodName, newInput);
            Files.copy(from, newFile);
            for (OpenJMLInput input: jmlInputs) {
                try {
                    methodName = input.getMethod() + (index++);
                    String codeToAdd = getMethod(input.getFilename(), input.getMethod());
                    log.debug("Code to add: " + codeToAdd);
                    insertNewMethod(input.getMethod(), methodName, newFile.getAbsolutePath(), codeToAdd);
                    newInput = new OpenJMLInput(input.getFilename(), methodName, input.getConfigurationFile(), input.getOverridingProperties(), input.getOriginalFilename(), input.getFeedback(), input.getMutantsToApply(), input.getSyncObject());
                    map.put(methodName, newInput);
                } catch (NoSuchElementException e) {

                }
            }
            OpenJMLInputWrapper ojiw = new OpenJMLInputWrapper(newFile.getPath(), 
                    oji.getConfigurationFile(), oji.getOverridingProperties(), originalMethod, map, originalFilename);
            return ojiw;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String getMethod(String filename, String methodName) throws FileNotFoundException {
        StringBuilder builder = new StringBuilder();
        Scanner scan = new Scanner(new File(filename));
        scan.useDelimiter("\n");
        boolean methodFound = false;
        boolean canBreak = true;
        int balance = -1;
        //		int j = 0;
        while(scan.hasNext()){
            String str = scan.next();
            if(methodFound) {
                if(str.contains("this.listSize = this.listSize - 1;") ||
                        str.contains("this.modCount = this.modCount + 1;") ||
                        str.contains("if (this.cacheSize < this.maximumCacheSize) {") ||
                        str.contains("this.cacheSize = this.cacheSize + 1;")) {
                    //					j++;
                }

            }
            if(str.contains(" " + methodName)) {
                methodFound = true;
                if(!str.contains("{")) {
                    canBreak = false;
                }
            }
            if(str.contains("{")) {
                //				System.out.println("1: "+((" "+str+" ").split("\\{").length - 1));
                balance += ((" "+str+" ").split("\\{").length - 1);
                if(!methodFound) {
                    builder = new StringBuilder();
                }
                canBreak = true;
            }
            if(str.contains("}")) {
                //				System.out.println("1: "+((" "+str+" ").split("\\}").length - 1));
                balance -= ((" "+str+" ").split("\\}").length - 1);
                if(!methodFound) {
                    builder = new StringBuilder();
                }
                canBreak = true;
            }
            if((balance == 0 && !str.contains("}")) || methodFound) {
                builder.append(str+"\n");
                if(balance == 0 && methodFound && canBreak) {
                    break;
                }
            }
        }
        scan.close();
        if(builder.length() == 0) {
            throw new NoSuchElementException("The method name was not found in this file");
        }
        return builder.toString();
    }

    private void insertNewMethod(String originalMethodName, String newMethodName, String filename, String codeToAdd) throws IOException {
        codeToAdd = codeToAdd.replaceAll(originalMethodName, newMethodName);
        String tempFileName = filename + "_temp";
        File destFile = new File(tempFileName);
        destFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(destFile);
        Scanner scan = new Scanner(new File(filename));
        scan.useDelimiter("\n");
        boolean classFound = false;
        while(scan.hasNext()){
            String str = scan.next();
            if(!classFound && str.contains(" class ")) {
                classFound = true;
                fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
                if(!str.contains("{")) {
                    fos.write((scan.next() + "\n").getBytes(Charset.forName("UTF-8")));
                }
            } else if (classFound){
                fos.write(codeToAdd.getBytes(Charset.forName("UTF-8")));
                fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
                break;
            } else {
                fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
            }
        }

        while(scan.hasNext()){
            fos.write((scan.next() + "\n").getBytes(Charset.forName("UTF-8")));
        }
        fos.close();
        scan.close();
        File originalFile = new File(filename);
        originalFile.delete();

        File newFile = new File(filename);
        newFile.createNewFile();

        Files.move(destFile, newFile);
    }

    /**
     * Used in case the EXTRA_CHECK flag is on. In cases were two hashes are
     * equal this method is called so as to assure that it is not a false
     * duplicate (equal hash but different file). Returns true if files are
     * equal or false if otherwise.
     */
    private boolean isFalseDuplicate(String originalFileName, File tempFile)
            throws IllegalStateException {
        File original = new File(originalFileName);
        try {
            if (!Files.equal(original, tempFile)) {
                log.error("FALSE DUPICATE");
                log.error("Original:" + original.getAbsolutePath());
                log.error("False duplicate:" + tempFile.getAbsolutePath());
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
            //			throw new IllegalStateException(
            //					"An error occured while opening files " + originalFileName
            //							+ " and " + tempFile.getName());
        }

    }

    /**
     * Sets up the necessary work environment prior to generating the mutants.
     * 
     * @throws FatalStrykerStageException
     */
    private File createWorkingDirectory() throws IllegalStateException {
        File tmpDir = null;
        if ((tmpDir = Files.createTempDir()) == null) {
            throw new IllegalStateException(
                    "Couldn't create work environment: failed"
                            + "to create temp directory");
        }

        log.trace("Using directory " + tmpDir + " as working directory.");
        return tmpDir;
    }

    public class GenFileFilter implements FilenameFilter {

        private int genNum;

        public GenFileFilter(int genNum) {
            this.genNum = genNum;
        }

        @Override
        public boolean accept(File dir, String name) {
            return name.startsWith("gen" + genNum + "_");
        }
    }

    /**
     * byte[] wrapper so as to be able to add digest to collections.
     */
    public static class MsgDigest {

        private byte[] digest;

        public MsgDigest(byte[] digest) {
            this.digest = digest;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(digest);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            MsgDigest other = (MsgDigest) obj;
            if (!Arrays.equals(digest, other.digest))
                return false;
            return true;
        }
    }

    public List<MuJavaInput> getFathers() {
        return fathers;
    }
}
