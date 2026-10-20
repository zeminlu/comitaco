# Known limitations

Behaviours to be aware of when running or changing Stryker. Many of them
affect the search, and therefore experimental results: don't "fix" them
without re-running benchmarks and saying so.

## Environment

- **Java 7, 8, 21 or 25 only.** The JML parser and JML4C cannot use the
  class library of Java 8 or later, so on Java 8 and later they read a Java 7
  class library (`lib/java7/rt.jar`, see
  [BUILDING.md](BUILDING.md#the-java-7-class-library)), and the scripts compile
  the project to Java 7 bytecode on every JDK. `TacoMain.run` and the build
  refuse other versions: 17 was not tested, and 27 fails at once (the
  `commons-lang` 2.4 that TACO uses cannot parse its version string, `27`).
  Support for Java 21 and 25 (2026) needed three changes: RAC runs that time out no longer depend on
  `Thread.stop`, which throws on Java 20+ (see "RAC timeouts count as
  passes"; before, a benchmark whose mutants loop forever reported "No fix was
  found" on Java 21); TACO loads MiniSat itself, since DynAlloy's trick for
  adding it to the library path fails there; and runs get `--add-opens` and
  `--enable-native-access` options (see
  [BUILDING.md](BUILDING.md#jdk-21-or-25-optional)).
- **Results on Java 21 and 25 are close to Java 8's, but vary more between
  runs.** The order of the declarations in TACO's Alloy models depends on
  identity hash codes, and it came out the same from run to run far more
  often on Java 8 than on Java 21. In one run each of 40 benchmarks, 38 gave
  the same report on Java 8 and 21, and 36 on Java 8 and 25. Repeated, three
  benchmarks gave the same result every time on Java 8 and varied on Java 21,
  once to "No fix was found"; Java 25 varied less. Details, and why
  `-XX:hashCode=3` is not used, in
  [BUILDING.md](BUILDING.md#jdk-21-or-25-optional). Making the order
  independent of hash codes would also change results on Java 7 and 8, so it
  was not done.
- **Results on Java 8 can differ from Java 7's.** Java 8 changed the iteration
  order of `HashMap`, and TACO writes the declarations of its Alloy models in
  that order, so the same program gets the same model with its declarations
  in a different order, and MiniSat can return different (equally valid)
  counterexamples. The smoke-test benchmarks give the same results on both.
  Comparing Java 7 with Java 17, which has Java 8's iteration order, five of
  six more single-bug benchmarks also matched, and the sixth varies from run
  to run on Java 7 too (see "Search behaviour"). Other benchmarks may differ in
  TACO calls, pruning, or whether a fix is found. Use Java 7 to compare with
  the original experiments.
- **An x86_64 Java 8 under Rosetta is slower with tiered compilation**, its
  default: runs took up to twice as long as on Java 7. The scripts pass
  `-XX:-TieredCompilation` in that case, which matches Java 7's default and
  gives the same results. A native JVM (arm64 on Apple Silicon) is faster
  with tiered compilation, so it gets no such option.
- **x86_64 or arm64.** MiniSat 2.2 is bundled for macOS as a universal
  library (x86_64 and arm64, since 2026) and for Linux on Intel and, since
  2026, arm64 (see [DEPENDENCIES.md](DEPENDENCIES.md)). Other architectures
  have no MiniSat library. JDK 7 only exists for x86_64.
- **No native Windows.** There is no 64-bit Windows SAT solver library (and
  no MiniSat 2.2 for Windows at all), some code uses the path separator as a
  regular expression (`\` makes it throw), and the scripts are bash. Use WSL2
  (see [BUILDING.md](BUILDING.md#windows-use-wsl2)).
- **The working directory must be the repository root**: `config/`, `tests/`,
  `bin/` and `lib/stryker/jml4c.jar` are resolved against it.

## Running

- **A successful run overwrites the subject file under `tests/`** with the fix
  and strips most `//mutGenLimit` marks. Restore with `--reset` or
  `git checkout -- tests`.
- **The JUnit result of a driver says nothing about the repair.** It only
  asserts TACO's verdict on the original method. Use the `STRYKER RESULT`
  block of `run-benchmark.sh`, `StrykerLastReports.txt`, or the console line
  `No fix was found`.
- **One repair per JVM, one run at a time per checkout.** Controllers are
  singletons shut down at the end of a run, and run state lives in static
  fields of `StrykerStage`. Runs share `tests/`, `output/`, `generated/` and the
  report files.
- **Runs could end early (fixed in 2026).** `WeedQueue` used to compute the
  remaining work from the queues and the in-flight slots, and an item that
  another thread was in the middle of taking was invisible for a moment. If a
  controller checked at that moment, the run ended with "No fix was found"
  even though work was pending. It happened rarely (seen once in about 30 runs
  of a small benchmark), but the 2018 code has the same race, so a "no fix"
  outcome from runs made with it is not fully reliable.
- **An unexpected exception can make a run never end.** The controllers' loops
  only catch `InterruptedException`; any other exception ends that
  controller's thread without acknowledging its work item, so the queues never
  drain and the run waits forever. Use `run-benchmark.sh --timeout`. (The case
  seen in practice, a TACO counterexample that cannot be turned into a test,
  is handled since 2026: that candidate just gets no feedback.)
- **A counterexample that stores `System.out` in a field could not become a
  test (fixed in 2026).** The snapshot builder creates every object of a
  counterexample with a public constructor and default arguments, which fails
  for `PrintStream`; the counterexample was then lost, and the run ended with
  "No fix was found". It was seen on Java 8, where TACO's models list their
  declarations in a different order and MiniSat chose such a counterexample
  for `icse.singlylinkedlist.set1.StrykerSinglyLinkedListContains1Bug25DTest`,
  but nothing prevents it on Java 7. `PrintStream` objects are now `System.out`.
- **A counterexample in which two parameters are the same object can be
  lost.** `UnitTestBuilder` declares each parameter with its declared type,
  building an `Object` parameter as `new Object()` whatever its value, and a
  parameter whose value it has already declared reuses that variable, without
  a cast. In `icse.nodecachinglinkedlist.set3.StrykerNodeCachingLinkedListAddFirst1Bug6ITest`,
  whose `addFirst(Object o, LinkedListNode newNode)` got a counterexample with
  `o == newNode`, the test said `LinkedListNode newNode = o;`, did not
  compile, and the counterexample was not added to the pool. It was seen in
  one run on Java 21 (see "Results on Java 21" above), but any JDK can get
  such a counterexample.
- **A run could break the next run of the same benchmark (fixed in 2026).**
  Feedback compiles the variabilized subject in place, leaving a `.class` file
  in `tests/`; the build copied it into `bin/`, and the next run of that
  benchmark loaded it and ended at once with "No fix was found". The build no
  longer copies class files from `tests/`, and `--reset` removes them.
- **Every IntroClass benchmark reported "No fix was found" (fixed in 2026).**
  Each counterexample test is rewritten (`TacoMain.editTestFileToCompile`)
  into a version whose test method declares some locals of its own, next to
  the parameters of the method under repair, which the test also declares as
  locals. One of those locals was named `c`, and every IntroClass subject has
  a parameter `c` (`median(int a, int b, int c)`, `smallest`, `grade`). The
  test did not compile, the counterexample never entered the pool, RAC had no
  input to run, and no candidate reached TACO. Only javac's message showed it;
  an error is logged now as well. This code dates from 2017-07-23 and is on
  `master` and every `weedqueue-2018*` branch, so IntroClass results produced
  with those are not meaningful. The rewritten test still uses `clazz`,
  `instance`, `method`, `e`, `fileClasspath`, `className` and `methodName`; a
  method under repair with a parameter of one of those names has the same
  problem (no subject in `tests/` has one).
- **After a fix is found, a controller thread can keep spinning**:
  `WeedQueue.clearQueues` never finishes while a work item is still in
  flight. This is harmless when drivers run from the scripts (`JUnitCore`) or
  from IntelliJ, since both exit the JVM at the end. On fast runs (seen on a
  native arm64 JVM) the shutdown can also print an `InterruptedException` and
  a `NullPointerException` in `MuJavaController.fatherize`, after the fix was
  found and reported; the result is not affected.
- **Temp files are never cleaned up by Stryker**: each run writes thousands of
  mutant files to `java.io.tmpdir`. `run-benchmark.sh` gives each run its own
  temp directory and deletes it.
- **`ant unittest` runs every driver**, including hundreds of Stryker
  benchmarks (many hours).
- **`unittest/examples/gcd` does not work**: the batch files generated for
  `examples.stryker.gcd.Gcd` don't compile (`The nested type Gcd cannot hide an
  enclosing type`), so the run makes no progress.
- **`FloatTest` fails** (`ClassCastException: JBlock cannot be cast to
  AlloyExpression`) both with and without master's 2018 floating-point commit:
  TACO's floating-point support does not work with the bundled jars. The
  Stryker benchmarks don't use floating point.
- **`maxStrykerMethodForFile` has no effect**: `MuJavaController` stores it but
  never uses it. The batch size is the constant `batchSize` (100).

## Search behaviour

- **Runtime assertion checking tries at most 9 counterexamples per mutant**,
  taken round-robin from a static cursor shared by all mutants
  (`OpenJMLController`). Only the final check before accepting a fix
  (`DarwinistController.lastRACAnalysis`) runs all of them.
- **RAC timeouts count as passes**: 3 seconds per input in `OpenJMLController`,
  5 seconds in `lastRACAnalysis`. A mutant that does not terminate on an input
  therefore passes RAC on that input. The run is then cancelled
  (`RacTimeout`): every method and loop body of the JML4C batch starts with a
  `RacTimeout.check()`, which throws once the run's thread is interrupted.
  A run stuck where there are no checks (a helper class of the subject, such
  as a node class, or the JDK) is stopped with `Thread.stop()` after 2 more
  seconds on Java 19 and earlier; on Java 20+ its thread is abandoned and
  keeps a core busy until the JVM exits. Until 2026 every timed-out run was
  stopped with `Thread.stop()`; the smoke-test benchmarks and a benchmark
  whose mutants loop forever give the same results either way.
- **The counterexample pool holds 10000 counterexamples**
  (`StrykerStage.MAX_COUNTEREXAMPLES`). Until 2026 it held 50, and a run that
  found more than 50 distinct counterexamples hung: the feedback step failed
  with `File name does not correspond to any stored input! Broken invariant!`
  and took `MuJavaController`'s thread down with it. Now, if the pool ever
  fills up (a warning is logged), or a counterexample's test class fails to
  load, that counterexample is not stored and its candidate gets no feedback:
  the search goes on without pruning for it. RAC and TACO still decide what
  counts as a fix, so results stay correct.
- **Results can vary between runs, single-bug benchmarks included, and
  pruning can remove a real fix.** Two things change from run to run, and
  both decide which counterexample a feedback check is computed with:
  - which counterexamples RAC tries: at most 9, starting from a cursor shared
    by all mutants, while other threads keep adding counterexamples, so
    whether a given mutant passes RAC depends on timing;
  - the counterexamples themselves: for the same program, TACO generates the
    same Alloy declarations but in a different order in each run, so MiniSat
    can return a different counterexample, the initial one included.

  Seen in practice:
  - `icse.bintree.set1.StrykerBinTreeInsert2Bug4Ix6DTest` (two bugs): 2 of 7
    runs on an idle machine reported "No fix was found". A mutant passed RAC,
    TACO refuted it, and the feedback computed with TACO's new counterexample
    pruned the rest of its father's children, including the fix the other 5
    runs found.
  - `icse.nodecachinglinkedlist.set2.StrykerNodeCachingLinkedListAddFirst1Bug6ITest`
    (one bug): 5 of 8 runs reported "No fix was found"; the other 3 found
    `[ 8 ]` with 5 TACO calls. In every run the first mutant failed RAC and
    its feedback made two TACO checks, with the initial counterexample as the
    fixed input. The variabilized program of the second check was
    byte-identical in all runs; only the encoded input (the `requires`
    predicate of the Alloy model) differed. With some inputs that check was
    UNSAT, which pruned every remaining mutation of the statement, `[ 8 ]`
    included, after 7 mutants.

  In both cases TACO verifies the fix that was pruned, so that feedback was
  unsound. Why some inputs lead to it is not known yet. It is not the
  loop-unrolling bound: `addFirst` has no loop, and in the BinTree case each
  unrolled loop copy gets its own fresh variables. Treat a "no fix" as "not
  found in this run", and expect the visited and pruned counts to vary. The
  smoke-test benchmarks gave the same results in every run.
- **A correct mutant can fail RAC.** In
  `icse.binomialheap.set2.StrykerBinomialHeapInsert1Bug10DTest`, all 34
  mutants of the only mutable statement fail RAC on the initial
  counterexample, including `temp1 != null && temp2 != null` (operator COD),
  which TACO verifies. Nothing is pruned, and every run reports "No fix was
  found". Not investigated yet; one possibility is that the counterexample
  test does not rebuild exactly the state TACO found.
- **A TACO error during variabilization stops the feedback** at that step, like
  a SAT verdict. Until 2026 it counted as UNSAT and the loop went on, which
  could prune the whole search: in
  `icse.singlylinkedlist.set1.StrykerSinglyLinkedListContains3Bug7Dx10Dx20DTest`
  a `java.lang.Exception` in the counterexample (its `serialVersionUID` does not
  fit in Alloy's integers) made every feedback call fail, and the run reported
  "No fix was found" after one candidate. It now finds a fix. On Java 21,
  without `--add-opens` (see [BUILDING.md](BUILDING.md#jdk-21-or-25-optional)), a
  counterexample holding such an exception (a `NullPointerException` in
  `ase2016.singlylinkedlist.StrykerSinglyLinkedListContains1Bug6Test`) makes
  TACO fail earlier, with an `InaccessibleObjectException`; the feedback
  stops at the same step either way, and with the option the error is the
  same as on Java 8.
- **Two traversal bugs were fixed in 2026** without being seen in practice:
  after a skipped sibling (duplicate or non-compiling) the feedback jump was
  applied again, skipping siblings nothing had ruled out; and a feedback line
  with no mutations left in the current father made the same candidate be
  queued again.
- **If no mutable statement can be variabilized at all**, `computateFeedback`
  still marks the feedback as UNSAT without calling TACO. Its skip index then
  points at the bottom-most line (position 0), so no statement is skipped, but
  the remaining right-hand-side mutations of that line can be.
- **Feedback after a failed final RAC check uses the first counterexample**:
  candidates coming from `OpenJMLController` don't carry the failing input, and
  `lastRACAnalysis` only records it when the check ends in an exception. This
  only reduces pruning.
- **Variabilization verdicts are cached by file content**, not by the
  counterexample used (`VariablizedSATVerdicts`). A cached UNSAT still justifies
  pruning; a cached SAT may hide pruning that another input would allow.
- **The right-hand side and left-hand side of a statement are variabilized
  separately**, which the thesis pseudocode does not show (see
  [THESIS-MAPPING.md](THESIS-MAPPING.md)).

## TACO's verdicts

Bugs in how TACO translates Java and JML, found in 2026 while documenting
Marcelo Frias's TACO ([mffrias/TACO](https://github.com/mffrias/TACO)) and
reproduced in this repository with small classes, on Java 7. They are not
fixed here on purpose: a fix changes TACO's verdicts, and with them the
results of the experiments. Their causes, and options for fixing them, are in
that repository's
[FIXES-FOR-MASTER.md](https://github.com/mffrias/TACO/blob/docs/docs/FIXES-FOR-MASTER.md#7-found-not-fixed),
under the entry given for each one.

For Stryker, a wrong UNSAT on a candidate can make it accept a candidate that
is not a fix in Java (the final RAC check only runs the counterexamples found
so far), and a wrong UNSAT in a feedback check prunes candidates; a wrong SAT
rejects a correct candidate. The thesis experiments ran with these bugs;
whether any reported fix depends on them has not been checked. The first five
were also checked with integer arithmetic (`useJavaArithmetic` off), as the
benchmarks run: in each, a method that Java gets wrong is reported correct,
and in the first four, its correct twin also gets a counterexample.

- **A postfix `++` or `--` in a `return` or in an `if` condition loses its
  update** (F5): `return this.n++;` returns `n` and never increments it, and
  `if (isPos(i++))` passes `i` to `isPos` and leaves `i` as it was. As a
  statement of its own (`i++;`) or in a declaration (`int x = n++;`) it is
  right. muJava's AOIS operator inserts these operators, so some AOIS mutants
  are analysed as a different program from the one Java runs.
- **Both branches of `c ? a : b` are evaluated** (F6): the calls and
  dereferences of both branches run before the condition picks one, so
  `b ? bump() : 0` runs `bump()` with `b` false, and
  `next != null ? next.get() : 0` gets a spurious `NullPointerException`.
- **The right side of an assignment is evaluated before the left side** (F7),
  while Java evaluates the left side first: in `me().v = g();`, `g()` runs
  before `me()`.
- **`Boolean.equals` is inverted** (F8) in TACO's model of `java.lang.Boolean`
  (`simplejml/builtin/JBoolean`): `a.equals(a)` is false. `Integer.equals` is
  right.
- **`normal_behavior` does not forbid exceptions** (F12): a method that throws
  (here a `NullPointerException`) where a `normal_behavior` case applies is
  reported correct.
- **`signals_only` in a lightweight specification case never holds** (F10):
  every method with such a case gets a counterexample. And a lightweight case
  written after an `exceptional_behavior` case loses its postcondition for
  normal returns.
- **Pure-method calls in invariants** (F11): a method that breaks an
  invariant calling a pure method (`invariant pos();`) is reported correct,
  vacuously. And with several classes in one analysis, a class can get the
  invariant calls of the classes translated before it, which, depending on
  the order of `relevantClasses`, makes the analysis fail or its checks
  vacuous.
- **The fixed input of a feedback check identifies objects by `equals`**
  (F13, in `lib/jdynalloy2018.jar`): two distinct objects that are `equals`
  become one object in the input, so the check can be UNSAT wrongly and prune.
  No class of the thesis benchmarks overrides `equals`; two example drivers
  use one that does (`examples.stryker.multikeymap.MultiKey`,
  `roops.core.objects.ArrayList`).
- **With Java arithmetic** (`useJavaArithmetic`), which three Stryker drivers
  use, none of them from the thesis benchmarks (`StrykerMultiKeyMapTest`,
  `StrykerCacheListTest`, `StrykerSinglyLinkedListTest`):
  - scope inference can allow too few integer values: `return x + 2;` with
    `requires x == 3; ensures \result == x + 1;` is UNSAT (the model has
    `exactly 5 JavaPrimitiveIntegerValue`), and SAT with `inferScope` off or
    without Java arithmetic. Not investigated further, and not in that page:
    Marcelo's TACO gets it right;
  - a negative value reached through a field of a fixed input
    (`thiz.f = -5`) makes Alloy stop, since the input names a predicate that
    does not exist (`..._literal_Minus5` for `..._literal_minus_5`; F15). In
    Stryker, that feedback check fails, which stops the feedback (see "A TACO
    error during variabilization" above).
- **Loop invariants** (only here: Marcelo's TACO now unrolls every loop and
  drops its invariant). A `while` with a `loop_invariant` is analysed by
  havocking the variables the loop body assigns, but not the fields that the
  methods called in the body assign, so a method whose loop changes a field
  only through a call can be reported correct. With `checkNullDereference`,
  `throw` is havocked too, so any loop whose body may throw gets a spurious
  counterexample; and the loop condition's copy is havocked apart from the
  variables it was computed from, so an invariant that needs the condition is
  not inductive (a spurious counterexample). A `do`-`while` with a
  `loop_invariant` fails with a `ClassCastException`
  (`BlockStatementsVisitor.visitJmlLoopStatement`). Only 11 of the 1286
  subjects in `tests/` have a `loop_invariant`, none of them used by a Stryker
  driver.
- **Modular reasoning** (`modularReasoning`) fails with a
  `NullPointerException` (`JDynAlloyMutator.visit(JHavoc)`, from
  `PruneVisitor`) when a called method has no `assignable` clause. No Stryker
  driver uses it (F18).

The same page also lists bugs that came later in Marcelo's line (his TACO
started from this repository) and that this repository does not have (F1, F2,
F9, F14, F16).

## Code

- **Naming**: `OpenJMLController` does runtime assertion checking with JML4C,
  not OpenJML; `DarwinistController` is TACO validation plus feedback;
  "variablize" and "computate" are the code's spellings.
- **Unused code**: `StrykerASTVisitor`, `StrykerDuplicateVariablesVanisherASTVisitor`,
  `ClassLoaderTools`, and several `StrykerJavaFileInstrumenter` methods
  (`instrumentForSequentialOutput`, `replaceMethodBodies`, ...) have no callers.
  In `DarwinistController.computateFeedback`, the `else if (unvariablizable)`
  branch can never run.
- **Concurrency**: shared static state (`StrykerStage`) is read and written
  from several threads without synchronization; many exceptions are caught
  and ignored.
- **Fragile paths**: Stryker derives package and class names from file paths
  and assumes `/` as separator. Three path problems were fixed in 2026; all of
  them used to make runs hang or silently report "No fix was found":
  - a temp directory containing a directory whose name starts with `a`
    (`MuJavaController.batchPackageStart`);
  - a repository path containing a space (JML4C is now started with an
    argument list);
  - a repository path containing `generated` or `output`
    (`TacoMain.replaceBelowWorkingDir`).
