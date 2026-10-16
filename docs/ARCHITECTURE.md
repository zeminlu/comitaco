# Architecture

How a repair run works, from the driver to the fix. Class names refer to
`src/ar/edu/taco/...`; most of the classes mentioned here have Javadoc with
more detail. For the algorithms behind it, see
[THESIS-MAPPING.md](THESIS-MAPPING.md).

## 1. TACO: finding the bug

A driver (JUnit class under `unittest/`) sets configuration keys and calls
`TacoMain.run(configFile, overridingProperties)`, which:

1. refuses to run on anything but Java 7, or Java 8 with a Java 7 class
   library for the JML tools (`Java7ClassLibrary`);
2. parses the JML-annotated sources (`JmlStage`, `SimpleJmlStage`; `JfslStage`
   for JFSL), translates them to JDynAlloy (`JDynAlloyStage` and friends), then
   to DynAlloy (`DynalloyStage`) and Alloy (`AlloyStage`), and solves the model
   with a SAT solver (MiniSat 2.2 through JNI);
3. if the check is **SAT**, a counterexample exists: `SnapshotStage` and
   `JUnitStage` turn it into a JUnit test under `generated/`;
4. if `attemptToCorrectBug=true` and the check was SAT, the counterexample test
   is adapted (`editTestFileToCompile`, written under `output/`), compiled,
   loaded and stored as the first entry of the counterexample pool
   (`StrykerStage.junitInputs` / `junitFiles`, room for 10000), and
   `StrykerStage.execute()` starts the repair.

Throughout Stryker, "SAT" from TACO means "a counterexample exists" (the
candidate is still wrong) and "UNSAT" means "correct within the bounds".

## 2. Stryker: the components

`StrykerStage.execute()` fixes the mutation operators (PRVOL_SMART,
PRVOR_REFINED, PRVOU_REFINED, AODS, AODU, AOIS, AOIU, AORB, AORS, AORU, ASRS,
COD, COI, COR, LOD, LOR, ROR, SOR) and hands over to `StrykerInitialStage`,
which uses `SimpleStrykerAPI`:

- `start()` starts four singleton controllers, each with one thread
  (`AbstractBaseController`);
- `fixBug(...)` enqueues the initial task to `UnskippableMuJavaController` and
  polls every 500 ms until `DarwinistController` shuts down, then returns the
  fixes found.

```
                    TacoMain.run ── TACO check SAT + attemptToCorrectBug
                         │
                    StrykerStage ─ StrykerInitialStage ─ SimpleStrykerAPI
                         │ initial task
                         ▼
              UnskippableMuJavaController ── left-hand-side mutations that
                 │                 │          can never be pruned
       new bases │ (q1)            │ validate-only candidates (q2)
                 ▼                 ▼
   ┌─────────────────────┐ mutants (q2) ┌──────────────────────┐ passes (q3) ┌──────────────────────┐
   │ MuJavaController    │ ───────────▶ │ OpenJMLController    │ ──────────▶ │ DarwinistController  │
   │ generate & search,  │              │ RAC on counter-      │             │ TACO on candidate,   │
   │ compute feedback    │ ◀─────────── │ examples (JML4C)     │             │ final RAC, fix found │
   └─────────────────────┘  RAC failed: └──────────────────────┘             └──────────┬───────────┘
              ▲             feedback request (q1)                                       │
              └──────────── TACO SAT: new counterexample + feedback request (q1) ───────┘
```

| Component | Job |
|---|---|
| `UnskippableMuJavaController` | First stage. Generates the PRVOL_SMART (left-hand side) mutations that the feedback can never prune ("unskippable"). Each combination goes to `OpenJMLController` as a validate-only candidate (`feedback.isStop()`) and to `MuJavaController` as a new base to mutate further. |
| `MuJavaController` | The search. Generates mutants with muJava, walks the mutation space (section 4), batches mutants into files compiled by JML4C, and, on its own thread, computes feedback (variabilization) when asked to. |
| `OpenJMLController` | Runtime assertion checking (RAC) with **JML4C** (the name is historical; OpenJML is not used). Runs each candidate on up to 9 stored counterexamples. Candidates that pass go to `DarwinistController`; failures trigger a feedback request. |
| `DarwinistController` | Validation with TACO. SAT: the candidate is wrong; its counterexample is added to the pool and feedback is requested. UNSAT: the candidate is re-checked by RAC on **all** stored counterexamples (`lastRACAnalysis`); if it passes, `fixFound` writes the report and, since `finishOnFirstFix` is on, stops the run. |
| `WeedQueue` (`StrykerStage.weedQueue`) | The three queues (q1 MuJava, q2 RAC, q3 Darwinist) and the termination rule (section 3). |
| `VariablizationData`, `StrykerVariablizerVisitor`, `VariablizedSATVerdicts`, `LoopUnrollTransformation` | Variabilization (section 5): what to replace, the rewriting, a verdict cache, and loop unrolling of the method under analysis. |
| `input/` | Messages between controllers: `MuJavaInput` (q1), `OpenJMLInput` (q2), `DarwinistInput` (q3), and `MuJavaFeedback`, the search state each candidate carries. |
| `StrykerJavaFileInstrumenter` | Source rewriting helpers (e.g. numbering the `//mutGenLimit` lines with mutation IDs). |

## 3. Queues and termination

The controllers exchange work through `StrykerStage.weedQueue`. Each item is
counted from the moment it is enqueued until its consumer calls `ack` (taking
it does not remove it from the count; it stays "in flight"). `ack` returns the
number of items not yet acknowledged across the three queues; when that
reaches 0 there is no work anywhere and all controllers are shut down. This is how a run ends when the mutation space is
exhausted without a fix ("No fix was found"). A run also ends at the first fix
(`MuJavaController.finishOnFirstFix`). `UnskippableMuJavaController` still uses
its own queue from `AbstractBaseController`.

## 4. The search

**Mutable statements** are the lines of the method marked `//mutGenLimit N`
with `N > 0`. `MuJavaController.getMutatorsList` asks muJava for the mutations
applicable to each of them.

**Mutation arrays.** A candidate is described by `Integer[]
lineMutationIndexes` (in `MuJavaFeedback`), one position per mutable line,
each value saying which mutation of that line is applied (0 = none). Position
0 is the **bottom-most** mutable line and the **least significant** digit: the
array is advanced like a counter with carry
(`calculateNextRelevantSonMutationsLists`). Rows of the mutation list are kept
in ascending line order, so position `i` maps to row `n-1-i`.

**Fathers.** A mutant that can still be mutated further becomes a "father"
(`fatherize`); its children apply further mutations on top. This is how
statements get mutated more than once (higher-order mutants). Duplicate
mutants are detected by hashing.

**Batches.** Mutants are written as up to 100 renamed methods of one wrapper
class (`buildNextBatchSiblingsFile`), in a fresh temp directory
`<java.io.tmpdir>/<n>/a<n>/aOpenJMLInWrap<i>/`, and compiled with
`java -jar lib/stryker/jml4c.jar` in a separate process (on Java 8 and 21,
against the Java 7 class library). Methods that don't compile are identified from the compiler output and skipped. Before
compiling, `RacTimeoutInstrumenter` inserts a `RacTimeout.check()` at the
start of every method and loop body, so that a RAC run that times out can be
cancelled. It inserts them on existing lines: the compiler's errors are
mapped back to mutants by line number.

## 5. Feedback: pruning with variabilization

When a candidate fails (RAC fails, or TACO finds a counterexample), Stryker
asks how far up it can jump in the mutation array:

1. A feedback request (a `MuJavaInput` with `computateFeedback=true`) goes to
   q1. `MuJavaController`'s thread calls `DarwinistController.computateFeedback`.
2. `computateFeedback` unrolls the method's loops, numbers the mutable lines
   with mutation IDs, and calls `variablize`.
3. `variablize` repeatedly replaces the next mutable statement, bottom-up and
   cumulatively, by fresh variables (`VariablizationData.variablizeNext`: first
   the right-hand side, then the left-hand side, as separate steps) and runs
   TACO on the result with the input fixed to the failing counterexample
   (`getTacoMainWithFixedInput`). **UNSAT** means no value of the replaced
   expressions makes the method correct on that input, so no mutation of
   those statements can fix it: keep going. **SAT** stops. It also stops when
   nothing else can be variabilized, the method doesn't compile, or TACO fails.
4. The result is stored as `MuJavaFeedback.skipUntilMutID =
   lastVariablizedMutID - 1`. Mutation IDs are written 1-based (so negative IDs
   can mark non-variabilizable lines); the `-1` makes it a 0-based index into
   the list of mutable lines.
5. `MuJavaController.getFeedbackIndex` turns that line into the position `k`
   in `lineMutationIndexes`, and the next candidate is computed by zeroing
   positions `0..k-1` and advancing position `k` (with carry). Every
   combination in between is pruned without being tried.

Verdicts are cached by file content (`VariablizedSATVerdicts`), and the same
variabilized method is never sent to TACO twice.

## 6. Files and directories during a run

| Where | What |
|---|---|
| `tests/<subject>.java` | Overwritten with candidates during validation; after a successful run it contains the fix |
| `generated/`, `output/` | TACO counterexample tests (raw and adapted) and the last Alloy model (`output/output.als`) |
| `<java.io.tmpdir>/` | Thousands of mutant files and batch wrappers; never cleaned up by Stryker (`run-benchmark.sh` uses a private temp directory and deletes it) |
| `StrykerLastReports.txt`, `StrykerLastFixFound.java` | Written by `fixFound` |
| `stryker.log` | Log of `MuJavaController`, `OpenJMLController` and `DarwinistController` (see `config/log4j.xml`); everything else goes to the console |

## 7. Threading and global state

- One thread per controller, plus the main thread waiting in
  `SimpleStrykerAPI.fixBug`.
- Controllers are singletons and `StrykerStage` keeps the run state in static
  fields (counterexample pool, statistics, the queue). One repair per JVM.
- `DarwinistController.validateCandidate` and `computateFeedback` are
  `synchronized` because both overwrite the subject file on disk, from
  different threads.
- `OpenJMLController` and `DarwinistController.lastRACAnalysis` run each RAC
  test on a single-thread executor, with a daemon thread, so that it can be
  timed out. On a timeout `RacTimeout.endTimedOutRun` interrupts the test,
  which the inserted checks turn into an exception, and replaces the
  executor.
- `RegresionTestBase` (the drivers' base class) configures muJava globally
  (banned methods, operator options) before each run.

## 8. Where to start reading

1. `TacoMain.run`, the end of the method: how Stryker is started.
2. `SimpleStrykerAPI`, `WeedQueue`.
3. `MuJavaController`: the runnable, `queueNextRelevantSibling`,
   `calculateNextRelevantSonMutationsLists`, `getFeedbackIndex`.
4. `OpenJMLController`: the runnable.
5. `DarwinistController`: `validateCandidate`, `lastRACAnalysis`,
   `computateFeedback`, `variablize`.
6. `VariablizationData.variablizeNext`.
