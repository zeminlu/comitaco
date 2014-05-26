package ar.edu.taco.stryker.api.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import mujava.OpenJavaException;
import mujava.api.Mutant;
import mujava.api.MutantIdentifier;
import mujava.api.MutantsInformationHolder;
import mujava.app.MutantInfo;
import mujava.app.MutationRequest;
import mujava.app.Mutator;
import openjava.ptree.ParseTreeException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.log4j.Logger;

import ar.edu.taco.engine.StrykerStage;
import ar.edu.taco.stryker.api.impl.input.MuJavaFeedback;
import ar.edu.taco.stryker.api.impl.input.MuJavaInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
import ar.edu.taco.stryker.exceptions.FatalStrykerStageException;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

public class MuJavaController extends AbstractBaseController<MuJavaInput> {

    //	private static AtomicInteger compilationFailCount = new AtomicInteger(0);

    private int maxMethodsInFile = 1;

    public void setMaxMethodsInFile(int maxMethodsInFile) {
        this.maxMethodsInFile = maxMethodsInFile;
    }

    private List<MuJavaInput> fathers = Lists.newArrayList();


    private static MuJavaController instance;

    private int privateI = 0;

    private static Logger log = Logger.getLogger(MuJavaController.class);

    public static AtomicInteger mutantCount = new AtomicInteger(0);

    public int[] mutantsPerLevel = new int[StrykerStage.generationsWanted.get()];

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
                    mutantsPerLevel[0] = 1;

                    MuJavaInput input = queue.take();

                    while (!willShutdown.get() || !queue.isEmpty()) {
                        if (input.getMuJavaFeedback() != null) {
                            queueNextRelevantSibling(input);
                        }
                        if (input.getMuJavaFeedback() == null || input.getMuJavaFeedback().isFatherable()) {
                            fatherize(input);
                        }
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

    private static final String FILE_SEP = System.getProperty("file.separator");

    // If it is set to false then it will be assumed that if two hashes are
    // equal then that means that the two files are equal. Which of course
    // it is not necessarily true.
    private static final boolean EXTRA_CHECK = true;

    private static final int NOT_PRESENT = -1;

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

    private Map<String, Integer> filenameToMutatedLine = Maps.newConcurrentMap();
    private Map<MsgDigest, String> filesHash = Maps.newConcurrentMap();
    private List<OpenJMLInput> jmlInputs = new ArrayList<OpenJMLInput>(maxMethodsInFile);
    String classToMutate;

    public boolean mutateAndQueue(MutantInfo mutantIdentifier, File fileToMutate, MuJavaInput muJavaInput, int fatherIndex, Integer[] childLineMutationIndexes) {
        log.debug("Generation finished. Generated mutants: 1");
        log.debug("Creating files for mutants");
        log.debug("Check that mutant is unique: "+ mutantIdentifier);
        File tempFile = new File(mutantIdentifier.getPath());
        int mutatedLine = mutantIdentifier.getMutatedLine();

        MsgDigest msgDigest = new MsgDigest(mutantIdentifier.getMD5digest());
        log.debug("fileToMutate= "+fileToMutate);
        log.trace("fileToMutate.getAbsolutePath()= "+fileToMutate.getAbsolutePath());
        log.trace("mutatedLine= "+mutatedLine);
        log.trace("filenameToMutatedLine.get(fileToMutate.getAbsolutePath())= "+filenameToMutatedLine.get(fileToMutate
                .getAbsolutePath()));
        Integer lastMutatedLine = filenameToMutatedLine.get(fileToMutate.getAbsolutePath());
        log.debug("last mutated line = "+lastMutatedLine);
        if (lastMutatedLine != null && (lastMutatedLine > mutatedLine) || filesHash.containsKey(msgDigest)) {
            if(lastMutatedLine != null) {
                log.debug("lastmutadtedline > mutadtedline = "+(lastMutatedLine > mutatedLine));
            } else {
                log.debug("lastmutadtedline  = null");
            }
            log.debug("filesHash.containsKey(msgDigest) = "+filesHash.containsKey(msgDigest));
            if (EXTRA_CHECK && filesHash.containsKey(msgDigest)) {
                if (isFalseDuplicate(filesHash.get(msgDigest), tempFile)) {
                    // If it is a false duplicate we don't have to delete the file
                    log.debug("False duplicated file");
                    return false;
                }
            }
            // We have to delete this new mutant since it will be a duplicate
            log.debug("Duplicated file");
            //mutantCount.decrementAndGet();
            if (!tempFile.delete()) {
                log.error("Couldn't remove file " + tempFile.getName());
            }
            return false;
        }

        String currentClasspath = System.getProperty("java.class.path")+OpenJMLController.PATH_SEP+System.getProperty("user.dir")+FILE_SEP+"generated";

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = javaCompiler.run(null, new NullOutputStream(), new NullOutputStream(),  new String[]{"-classpath", currentClasspath, tempFile.getAbsolutePath()});

        if ( compilationResult == 0 ){
            log.info("Compilation succeeded. Adding this file");

            filesHash.put(msgDigest, tempFile.getAbsolutePath());
            filenameToMutatedLine.put(tempFile.getAbsolutePath(), mutatedLine);
            MuJavaFeedback newFeedback = new MuJavaFeedback(childLineMutationIndexes, muJavaInput.getMuJavaFeedback().getLineMutatorsList());
            newFeedback.setFatherIndex(fatherIndex);
            OpenJMLInput output = new OpenJMLInput(tempFile.getAbsolutePath(),
                    muJavaInput.getJunitInputs(), muJavaInput.getMethod(),
                    muJavaInput.getConfigurationFile(),
                    muJavaInput.getOverridingProperties(),
                    muJavaInput.getOriginalFilename(),
                    newFeedback,
                    muJavaInput.getMutantsToApply(),
                    muJavaInput.getSyncObject());
            log.debug("Adding task to the list");
            jmlInputs.add(output);
            if(jmlInputs.size() >= maxMethodsInFile) { //TODO ver si se juntan N o no... no va a andar si no lo genero ante 1, por el caso inicial
                OpenJMLInputWrapper wrapper = createJMLInputWrapper(jmlInputs, classToMutate);
                log.info("Creating output for OpenJMLController");

                wrapper = StrykerJavaFileInstrumenter.instrumentForSequentialOutput(wrapper);
                wrapper.setForSeqProcessing(true);

                OpenJMLController.getInstance().enqueueTask(wrapper);
                log.debug("Adding task to the OpenJMLController");
                jmlInputs.clear();
            }
        }
        return true;
    }

    public ImmutablePair<List<MutantIdentifier>, Integer[]> calculateNextRelevantSonMutantIdentifiersLists(Integer[] lineMutationIndexes, MutantIdentifier[][] mutatorsList, Integer feedback) {
        List<MutantIdentifier> ret = Lists.newArrayList();

        //TODO si se acaban tooodos los indices, qué hacemos??
        try {
            while (lineMutationIndexes[feedback] + 1 > mutatorsList[feedback].length) { //si me paso de rosca de la linea
                if (feedback + 1 >= lineMutationIndexes.length) {
                    return null;
                }
                feedback++;
            } 
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Dio ArrayIndexOutOfBoundsException");
        }

        lineMutationIndexes[feedback]++;
        for (int i = feedback - 1; i >= 0; --i) {
            lineMutationIndexes[i] = 0;
        }

        for (int i = 0; i < lineMutationIndexes.length; ++i) {
            if (lineMutationIndexes[i] > 0) {
                ret.add(mutatorsList[i][lineMutationIndexes[i]]);
            }
        }

        return new ImmutablePair<List<MutantIdentifier>, Integer[]>(ret, lineMutationIndexes);
    }

    public MutantIdentifier[][] getMutatorsList(List<MutantIdentifier> mutantIdentifiers) {
        Map<Integer, List<MutantIdentifier>> theMap = Maps.newTreeMap();
        for (MutantIdentifier mutantIdentifier : mutantIdentifiers) {
            List<MutantIdentifier> theList = theMap.get(mutantIdentifier.getAffectedLine());
            if (theList != null) {
                theList.add(mutantIdentifier);
            } else {
                List<MutantIdentifier> newList = Lists.newArrayList();
                newList.add(mutantIdentifier);
                theMap.put(mutantIdentifier.getAffectedLine(), newList);
            }
        }

        Set<Integer> lines = Sets.newTreeSet(theMap.keySet());
        MutantIdentifier[][] mutantIdentifiersList = new MutantIdentifier[lines.size()][];
        Collection<List<MutantIdentifier>> theEntrySet = theMap.values();
        int i = 0;
        for (List<MutantIdentifier> list : theEntrySet) {
            MutantIdentifier[] curArray = new MutantIdentifier[list.size()];
            int j = 0;
            for (MutantIdentifier mutantIdentifier : list) {
                curArray[j] = mutantIdentifier;
                ++j;
            }
            mutantIdentifiersList[i] = curArray;
            ++i;
        }

        return mutantIdentifiersList;
    }

    public void fatherize(MuJavaInput input) {
        MuJavaInput inputAsFather = new MuJavaInput(input.getFilename(), input.getMethod(), input.getJunitInputs(), 
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
                if (mutant.getKey().equalsIgnoreCase(input.getMethod())) {
                    mutantsInformationHolder = mutant.getValue();
                }
            }
            List<MutantIdentifier> mutantIdentifiers = mutantsInformationHolder.getMutantsIdentifiers();
            //Me quedo solo con los mutantidentifiers que afectan solo 1 linea en el metodo en cuestión.
            mutantIdentifiers = new LinkedList<MutantIdentifier>(Collections2.filter(mutantIdentifiers, new Predicate<MutantIdentifier>() {
                public boolean apply(MutantIdentifier arg0) {
                    return arg0.isOneLineInMethodOp();
                };
            }));

            MutantIdentifier[][] mutatorsList = getMutatorsList(mutantIdentifiers);
            if (mutatorsList.length == 0) {
                return; //No tiene más mutaciones posibles, es una hoja del arbol de mutaciones.
            }
            Integer[] lineMutationIndexes = new Integer[mutatorsList.length];
            for (int i = 0; i < lineMutationIndexes.length; ++i) {
                lineMutationIndexes[i] = 0;
            }//inicializar todo en 0 si no lo hace
            MuJavaFeedback newFeedback = new MuJavaFeedback(lineMutationIndexes, mutatorsList);
            newFeedback.setMutantsInformationHolder(mutantsInformationHolder);
            newFeedback.setMut(mut);
            newFeedback.setMutateUntilLine(0);
            muJavaInput.setMuJavaFeedback(newFeedback);
            ImmutablePair<List<MutantIdentifier>, Integer[]> firstSonMutantIdentifiersLists = calculateNextRelevantSonMutantIdentifiersLists(lineMutationIndexes, mutatorsList, muJavaInput.getMuJavaFeedback().getMutateUntilLine());

            if (firstSonMutantIdentifiersLists == null) {
                System.out.println("No tiene ni siquiera 1 hijo este NUEVO PADRE!!!!");
            }

            if (!Mutator.checkCompatibility(firstSonMutantIdentifiersLists.getLeft())) {
                System.out.println("Generó una lista de mutaciones donde al menos 2 de ellas afectan la misma linea");
                throw new IllegalArgumentException();
            }

            fathers.add(inputAsFather);//se agrega el nuevo padre a la lista de padres

            List <MutantIdentifier> curIdentifiers = mutantsInformationHolder.getMutantsIdentifiers();
            curIdentifiers.clear();
            curIdentifiers.addAll(firstSonMutantIdentifiersLists.getLeft());
            List<MutantInfo> mil = mut.writeMutants(input.getMethod(), mutantsInformationHolder, true);
            MutantInfo mutantInfo = mil.get(0);
            //            HashMap<String, List<String>> mutantsFolders = mut.mutantsFolders;
            //            mut.resetMutantFolders();
            //            List<String> folders = mutantsFolders.entrySet().iterator().next().getValue();

            //Encolo el hijo
            if (!mutateAndQueue(mutantInfo, fileToMutate, muJavaInput, fathers.size() - 1, firstSonMutantIdentifiersLists.getRight())) {
                //TODO generar otro hijo que ande bien y reintentar
            }
        } catch (ClassNotFoundException | OpenJavaException e) {
            // TODO: Define what to do!
        } catch (ParseTreeException e) {
            // TODO: Define what to do!
        }
    }

    public void queueNextRelevantSibling(MuJavaInput input) {
        try {
            MuJavaInput father = fathers.get(input.getMuJavaFeedback().getFatherIndex());
            MutantIdentifier[][] mutatorsList = father.getMuJavaFeedback().getLineMutatorsList();
            Integer[] lineMutationIndexes = input.getMuJavaFeedback().getLineMutationIndexes(); //inicializar todo en 0 si no lo hace
            ImmutablePair<List<MutantIdentifier>, Integer[]> nextRelevantSiblingMutantIdentifiersLists = calculateNextRelevantSonMutantIdentifiersLists(lineMutationIndexes, mutatorsList, input.getMuJavaFeedback().getMutateUntilLine());

            if (nextRelevantSiblingMutantIdentifiersLists == null) {
                System.out.println("No hay más siblings para este padre!");
                return;
            }

            if (!Mutator.checkCompatibility(nextRelevantSiblingMutantIdentifiersLists.getLeft())) {
                System.out.println("Generó una lista de mutaciones donde al menos 2 de ellas afectan la misma linea");
                throw new IllegalArgumentException();
            }

            MutantsInformationHolder mutantsInformationHolder = father.getMuJavaFeedback().getMutantsInformationHolder();
            Mutator mut = father.getMuJavaFeedback().getMut();

            List <MutantIdentifier> curIdentifiers = mutantsInformationHolder.getMutantsIdentifiers();
            curIdentifiers.clear();
            curIdentifiers.addAll(nextRelevantSiblingMutantIdentifiersLists.getLeft());
            List<MutantInfo> mil = mut.writeMutants(input.getMethod(), mutantsInformationHolder, true);
            mil = mut.generateMutants();
            MutantInfo mutantInfo = mil.get(0);
            //            HashMap<String, List<String>> mutantsFolders = mut.mutantsFolders;
            //            mut.resetMutantFolders();
            //            List<String> folders = mutantsFolders.entrySet().iterator().next().getValue();

            File fileToMutate;

            fileToMutate = new File(father.getFilename());
            if (!fileToMutate.exists()) {
                throw new IllegalStateException("The file " + input.getFilename() + " doesn't exist. Can't continue.");
                //              return Lists.newArrayList();
            }

            //Encolo el hijo
            if (!mutateAndQueue(mutantInfo, fileToMutate, father, fathers.size() - 1, nextRelevantSiblingMutantIdentifiersLists.getRight())) {
                //TODO generar otro hijo que ande bien y reintentar
            }
        } catch (ClassNotFoundException e) {
            // TODO: Define what to do!
        } catch (OpenJavaException e) {
            // TODO: Define what to do!
        } catch (ParseTreeException e) {
            // TODO: Define what to do!
        }
    }
    //
    //    public void execute(MuJavaInput input) {
    //
    //        try {
    //            File fileToMutate;
    //            String methodToCheck;
    //            HashSet<Mutant> mutOps;
    //            int generationsLeft;
    //            MuJavaInput muJavaInput;
    //
    //            fileToMutate = new File(input.getFilename());
    //            if (!fileToMutate.exists()) {
    //                throw new IllegalStateException("The file " + input.getFilename()
    //                        + " doesn't exist. Can't continue.");
    //                //				return Lists.newArrayList();
    //            }
    //            methodToCheck = input.getMethod();
    //            mutOps = Sets.newHashSet(input.getMutantsToApply());
    //            classToMutate = obtainClassNameFromFileName(input.getFilename());
    //            generationsLeft = input.getQtyOfGenerations().get();
    //            muJavaInput = input;
    //
    //            final File tmpDir = createWorkingDirectory();
    //
    //            log.debug("Generating mutants...");
    //
    //
    //            String[] methods1 = new String[] {methodToCheck};
    //            Mutant[] mutops1 = new Mutant[mutOps.size()];
    //            mutOps.toArray(mutops1);
    //            MutationRequest req1 = new MutationRequest(classToMutate, methods1, mutops1, fileToMutate.getParent() + FILE_SEP, tmpDir.getAbsolutePath() + FILE_SEP);
    //            Mutator mut = new Mutator(req1);
    //
    //            //TODO
    //            //ME DA LA SENSACION QUE ACA ES DONDE INTERVENGO SOBRE QUE SE GENERA ACTUALLY
    //            //Necesito filtrar los mutantIdentifiers quedandome solo con los que afectan las líneas que me darían una clase de equivalencia hija de la actual
    //            //Y como voy a generar clases de equivalencias varias, y cada una de ellas con un archivo por cada instancia de esa clase de equivalencia, necesito cambiar lo que sigue para escriba archivos con esas instancias
    //
    //
    //            //Computo las clases de equivalencia hijas
    //            //Por cada una, genero todas las combinaciones de MutantIdentifiers que entran en esa clase
    //            //Hago un writeMutants para el mutantinformationholder por cada una cambiando esa lista de mutantidentifier por la que corresponde de la instancia
    //
    //            Map<String, MutantsInformationHolder> mutantsInformationHoldersMap = new HashMap<>(); //mut.obtainMutants();
    //            MutantsInformationHolder mutantsInformationHolder = null;
    //            for (Entry<String, MutantsInformationHolder> mutant : mutantsInformationHoldersMap.entrySet()) {
    //                if (mutant.getKey().equalsIgnoreCase(input.getMethod())) {
    //                    mutantsInformationHolder = mutant.getValue();
    //                }
    //            }
    //            List<MutantIdentifier> mutantIdentifiers = new LinkedList<>();// mutantsInformationHolder.getMutantsIdentifiers();
    //            //Me quedo solo con los mutantidentifiers que afectan solo 1 linea en el metodo en cuestión.
    //            mutantIdentifiers = new LinkedList<MutantIdentifier>(Collections2.filter(mutantIdentifiers, new Predicate<MutantIdentifier>() {
    //                public boolean apply(MutantIdentifier arg0) {
    //                    //                    return arg0.isOneLineInMethodOp();
    //                    return true;
    //                };
    //            }));
    //
    //            int[] equivalenceClass = input.getMuJavaFeedback().getMutationEquivalenceClass();
    //            int[][] childEquivalenceClasses = calculateChildEquivalenceClasses(equivalenceClass);
    //
    //            for (int i = 0; i < childEquivalenceClasses.length ; ++i) {
    //                for (int j = 0; j < childEquivalenceClasses[i].length; ++j) {
    //                    int[] childEquivalenceClass = childEquivalenceClasses[i];
    //
    //                    List<List<MutantIdentifier>> childEquivalenceClassMutantIdentifiersLists = new LinkedList<List<MutantIdentifier>>(); //calculateChildEquivalenceClassMutantIdentifiersLists(childEquivalenceClass, mutantIdentifiers)
    //
    //                    for (List<MutantIdentifier> list : childEquivalenceClassMutantIdentifiersLists) {
    //                        //                        mutantsInformationHolder.setMutantIdentifiers(list);
    //                        //                      List<MutantInfo> mil = writeMutants(input.getMethod(), mutantsInformationHolder, true);
    //                        List<MutantInfo> mil = mut.generateMutants();
    //                        HashMap<String, List<String>> mutantsFolders = mut.mutantsFolders;
    //                        mut.resetMutantFolders();
    //                        List<String> folders = mutantsFolders.entrySet().iterator().next().getValue();
    //
    //                        //                        log.warn("MutantCount: " + mutantCount.addAndGet(genMutants.getMutantsIdentifiers().size()));
    //
    //                        //                        MutatorsInfo info = MutatorsInfo.newInstance();
    //                        //                        for (Mutant mutant : Mutant.values()) {
    //                        //                            if (info.isSupported(mutant)) {
    //                        //                                System.out.println("mutOps.add(Mutant." + mutant.name() + ");");
    //                        //                            }
    //                        //                        }
    //
    //                        log.debug("Generation finished. Generated mutants: "+mil.size());
    //                        List<MuJavaInput> nextGenerationInputs = new ArrayList<MuJavaInput>();
    //
    //                        //          OpenJMLController outputController = OpenJMLController.getInstance();
    //
    //                        log.debug("Creating files for mutants");
    //                        for (MutantInfo mutantIdentifier : mil) {
    //                            log.debug("Check that mutant is unique: "+ mutantIdentifier);
    //                            File tempFile = new File(mutantIdentifier.getPath());
    //                            int mutatedLine = mutantIdentifier.getMutatedLine();
    //                            Mutant opUsed = mutantIdentifier.getOpUsed();
    //
    //                            MsgDigest msgDigest = new MsgDigest(mutantIdentifier.getMD5digest());
    //                            log.debug("generationsLeft= "+generationsLeft);
    //                            log.debug("fileToMutate= "+fileToMutate);
    //                            log.trace("fileToMutate.getAbsolutePath()= "+fileToMutate.getAbsolutePath());
    //                            log.trace("mutatedLine= "+mutatedLine);
    //                            log.trace("filenameToMutatedLine.get(fileToMutate.getAbsolutePath())= "+filenameToMutatedLine.get(fileToMutate
    //                                    .getAbsolutePath()));
    //                            Integer lastMutatedLine = filenameToMutatedLine.get(fileToMutate.getAbsolutePath());
    //                            log.debug("last mutated line = "+lastMutatedLine);
    //                            if (
    //                                    /*generationsLeft != 0
    //                                    &&*/ (lastMutatedLine != null && (lastMutatedLine > mutatedLine) 
    //                                    || filesHash.containsKey(msgDigest) ) ) {
    //                                if(lastMutatedLine != null) {
    //                                    log.debug("lastmutadtedline > mutadtedline = "+(lastMutatedLine > mutatedLine));
    //                                } else {
    //                                    log.debug("lastmutadtedline  = null");
    //                                }
    //                                log.debug("filesHash.containsKey(msgDigest) = "+filesHash.containsKey(msgDigest));
    //                                if (EXTRA_CHECK && filesHash.containsKey(msgDigest)) {
    //                                    if (isFalseDuplicate(filesHash.get(msgDigest), tempFile)) {
    //                                        // If it is a false duplicate we don't have to delete
    //                                        // the file
    //                                        log.debug("False duplicated file");
    //                                        continue;
    //                                    }
    //                                }
    //                                // We have to delete this new mutant since it will be a
    //                                // duplicate
    //                                log.debug("Duplicated file");
    //                                //                  mutantCount.decrementAndGet();
    //                                if (!tempFile.delete()) {
    //                                    log.error("Couldn't remove file " + tempFile.getName());
    //                                }
    //                                continue;
    //                            }
    //
    //                            String currentClasspath = System.getProperty("java.class.path")+OpenJMLController.PATH_SEP+System.getProperty("user.dir")+FILE_SEP+"generated";
    //
    //                            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
    //                            int compilationResult = javaCompiler.run(null, new NullOutputStream(), new NullOutputStream(),  new String[]{"-classpath", currentClasspath, tempFile.getAbsolutePath()});
    //
    //                            if ( compilationResult == 0 ){
    //                                log.info("Compilation succeeded. Adding this file");
    //
    //                                filesHash.put(msgDigest, tempFile.getAbsolutePath());
    //                                filenameToMutatedLine.put(tempFile.getAbsolutePath(), mutatedLine);
    //                                OpenJMLInput output = new OpenJMLInput(tempFile.getAbsolutePath(),
    //                                        muJavaInput.getJunitInputs(), muJavaInput.getMethod(),
    //                                        muJavaInput.getConfigurationFile(),
    //                                        muJavaInput.getOverridingProperties(),
    //                                        muJavaInput.getOriginalFilename(),
    //                                        muJavaInput.getMuJavaFeedback(),
    //                                        muJavaInput.getMutantsToApply(),
    //                                        muJavaInput.getSyncObject());
    //                                log.debug("Adding task to the list");
    //                                jmlInputs.add(output);
    //                                if ((generationsLeft - 1) > 0) {
    //                                    int newNumberOfGenerations = generationsLeft - 1;
    //                                    //                      log.warn("nuevo mutante: "+newNumberOfGenerations);
    //                                    MuJavaInput mji = new MuJavaInput(tempFile.getAbsolutePath(),
    //                                            muJavaInput.getMethod(), muJavaInput.getJunitInputs(),
    //                                            muJavaInput.getMutantsToApply(),
    //                                            new AtomicInteger(newNumberOfGenerations),
    //                                            muJavaInput.getConfigurationFile(),
    //                                            muJavaInput.getOverridingProperties(),
    //                                            muJavaInput.getOriginalFilename(), output);
    //                                    nextGenerationInputs.add(mji);
    //                                }
    //                                if(jmlInputs.size() >= maxMethodsInFile) {
    //                                    OpenJMLInputWrapper wrapper = createJMLInputWrapper(jmlInputs, classToMutate);
    //                                    log.info("Creating output for OpenJMLController");
    //
    //                                    //--------------Acá llamamos al instrumentador
    //                                    wrapper = StrykerJavaFileInstrumenter.instrumentForSequentialOutput(wrapper);
    //                                    wrapper.setForSeqProcessing(true);
    //                                    //CREAR UN MuJavaFeedback para meter acá
    //                                    //wrapper.setFeedback(feedback);
    //                                    //                                    wrapper.setForSeqProcessing(false);
    //                                    //-----------Ahora Rack va a ejecutar ese codigo tuneado
    //                                    //-----------y va a generar los archivos con las ramas ejecutadas
    //
    //                                    //                        wrapper.setForSeqProcessing(false);
    //                                    OpenJMLController.getInstance().enqueueTask(wrapper);
    //                                    log.debug("Adding task to the OpenJMLController");
    //                                    jmlInputs.clear();
    //                                }
    //                            } 
    //                            //          else {
    //                            //              log.warn("Compilation failed. Ignoring this file. Compilations failed: " + compilationFailCount.incrementAndGet() );
    //                            //          }
    //
    //                        }
    //                    }
    //                }
    //            }
    //
    //        } catch (ClassNotFoundException | OpenJavaException e) {
    //            // TODO: Define what to do!
    //        }
    //    }

    //    private int[][] calculateChildEquivalenceClasses(int[] equivalenceClass) {
    //        int maxValue = 0;
    //        Set<Integer> maxIndexes = Sets.newHashSet();
    //
    //        for (int i = 0; i < equivalenceClass.length; i++) {
    //            int cur = equivalenceClass[i];
    //            if (cur > maxValue) {
    //                maxValue = cur;
    //                maxIndexes.clear();
    //            }
    //            maxIndexes.add(i);
    //        }
    //
    //        Set<Set<Integer>> powerSet = Sets.powerSet(maxIndexes);
    //        powerSet.remove(Sets.newHashSet());
    //        int[][] childClasses = new int[powerSet.size()][equivalenceClass.length];
    //        int i = 0;
    //        for (Set<Integer> set : powerSet) {
    //            int[] base = equivalenceClass.clone();
    //            for (Integer index : set) {
    //                base[index]++;
    //            }
    //            childClasses[i] = base;
    //            ++i;
    //        }
    //
    //        return childClasses;
    //    }

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
            OpenJMLInput newInput = new OpenJMLInput(oji.getFilename(), oji.getJunitInputs(), methodName, oji.getConfigurationFile(), oji.getOverridingProperties(), oji.getOriginalFilename(), oji.getFeedback(), oji.getMutantsToApply(), oji.getSyncObject());
            map.put(methodName, newInput);
            Files.copy(from, newFile);
            for (OpenJMLInput input: jmlInputs) {
                try {
                    methodName = input.getMethod() + (index++);
                    String codeToAdd = getMethod(input.getFilename(), input.getMethod());
                    log.debug("Code to add: " + codeToAdd);
                    insertNewMethod(input.getMethod(), methodName, newFile.getAbsolutePath(), codeToAdd);
                    newInput = new OpenJMLInput(input.getFilename(), input.getJunitInputs(), methodName, input.getConfigurationFile(), input.getOverridingProperties(), input.getOriginalFilename(), input.getFeedback(), input.getMutantsToApply(), input.getSyncObject());
                    map.put(methodName, newInput);
                } catch (NoSuchElementException e) {

                }
            }
            OpenJMLInputWrapper ojiw = new OpenJMLInputWrapper(newFile.getPath(), oji.getJunitInputs(), 
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

    // private File[] getFilesOfLastGeneration(int genNum, File tmpDir) {
    // File[] filesOfLastGen;
    // if (genNum == 0) {
    // // We are on the initial generation.
    // filesOfLastGen = new File[1];
    // filesOfLastGen[0] = fileToMutate;
    // } else {
    // filesOfLastGen = tmpDir.listFiles(new GenFileFilter(genNum - 1));
    // }
    // return filesOfLastGen;
    // }

    //    private DigestOutputStream getDigestOutputStream(File tempFile)
    //            throws IllegalStateException {
    //        try {
    //            return new DigestOutputStream(new FileOutputStream(tempFile, true),
    //                    MessageDigest.getInstance("MD5"));
    //        } catch (NoSuchAlgorithmException e) {
    //            throw new IllegalStateException("MD5 implementation not found.");
    //        } catch (FileNotFoundException e) {
    //            throw new IllegalStateException("Temp file not found.");
    //        }
    //    }
    //
    //    private MutantsInformationHolder generateMutants(File mutatedFile,
    //            String method, HashSet<Mutant> mutOps, String classToMutate)
    //                    throws IllegalStateException {
    //        try {
    //            return Api.generateMutants(mutatedFile, classToMutate, method,
    //                    mutOps);
    //        } catch (OpenJavaException e1) {
    //            throw new IllegalStateException("Failed to generate mutants.");
    //        }
    //    }
    //
    //    private int writeMutant(CompilationUnit compUnit,
    //            MutantIdentifier mutantIdentifier,
    //            DigestOutputStream digestOutputStream) throws IllegalStateException {
    //        try {
    //            return Api.writeMutant(compUnit, mutantIdentifier, new PrintWriter(
    //                    digestOutputStream));
    //        } catch (ParseTreeException e) {
    //            throw new IllegalStateException("Failed to parse java file.");
    //        }
    //    }

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

    //    private File createTempFile(String className, int generation, int index, String from, File dir, String uniqueIdentifier)
    //            throws IllegalStateException {
    //        StringBuilder sb = new StringBuilder();
    //        sb.append("genLeft").append(generation).append("_index").append(index);
    //        if (from == null || from.isEmpty()) {
    //            sb.append("_from").append(from);
    //        }
    //        File tempDir = new File(dir, uniqueIdentifier + FILE_SEP +sb.toString() /*+ OpenJMLController.MUTANTS_DEST_PACKAGE.replaceAll("\\.", FILE_SEP)*/);
    //        File tempFile = new File(tempDir.getAbsolutePath() + FILE_SEP + className + ".java");
    //        log.debug("The tempfile is: "+tempFile.getAbsolutePath());
    //        try {
    //            Files.createParentDirs(tempFile);
    //            tempFile.createNewFile();
    //        } catch (IOException e) {
    //            throw new IllegalStateException();
    //        }
    //        return tempFile;
    //    }

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

    //    private String getMutsPerLevel(int[] values){
    //        String result = "";
    //        for (int i = 0; i < values.length; i++){
    //            result = result + " | " + values[i];
    //        }
    //        result = result + " |";
    //        return result;
    //    }

}
