# Running Stryker

Set up first: [BUILDING.md](BUILDING.md). Everything below works the same on
macOS and Linux.

## Concepts

- **Subject**: a JML-annotated class under `tests/` with a buggy method, e.g.
  `tests/icse/singlylinkedlist/set1/SinglyLinkedListContains1Bug25D.java`.
- **Mutable lines**: Stryker only mutates statements annotated with
  `//mutGenLimit N` where `N > 0`; `N` bounds how many times that statement can
  be mutated. Lines marked `//mutGenLimit 0` or not marked are left alone.
  There is no automatic fault localization: these marks *are* the fault
  localization. In the data-structure benchmarks only the lines where bugs were
  injected are marked (the `.txt` file next to each subject lists them).
- **Driver**: a JUnit class under `unittest/` that configures TACO and Stryker
  for one subject method and runs them, e.g.
  `unittest/icse/singlylinkedlist/set1/StrykerSinglyLinkedListContains1Bug25DTest.java`.
  A benchmark is run by running its driver.
- **A run**: TACO checks the method against its contract. If it finds a
  counterexample (the check is SAT) and the driver enabled
  `attemptToCorrectBug`, the counterexample is turned into a JUnit test and
  Stryker searches for a fix. See [ARCHITECTURE.md](ARCHITECTURE.md).

## Running one benchmark

```bash
scripts/run-benchmark.sh [options] <fully.qualified.DriverClass>
```

| Option | Meaning |
|---|---|
| `--reset` | Restore `tests/` from git before running, and remove what earlier runs left there. Use it whenever you re-run a benchmark: a successful run leaves the fix in the subject file. |
| `--variant FILE` | Temporarily replace a subject class with `FILE` (see [BinomialHeap ROOPS variants](#binomialheap-roops-variants)). |
| `--timeout SECS` | Stop the run after `SECS` seconds. |
| `--no-build` | Skip the incremental build. |
| `--keep-temp` | Keep the temp directory with all generated mutants. |

Environment variables: `STRYKER_JAVA_HOME` (the JDK 8, 7, 21 or 25 to use),
`STRYKER_CLASSLIB` (on JDK 8 and later, the Java 7 `rt.jar`; default
`lib/java7/rt.jar`) and `STRYKER_JVM_OPTS` (default `-Xmx4g -Xss8m`, plus
`-XX:MaxPermSize=512m` on JDK 7, or `-XX:-TieredCompilation` for an x86_64
JDK under Rosetta).

Every script in this document also runs in the optional Docker environment:
prefix it with `scripts/docker.sh` (see
[BUILDING.md](BUILDING.md#alternative-docker)).

Exit status: `0` fix found, `1` no fix found (search space exhausted), `2`
usage or environment error, `3` build failed, `4` the run ended without a
Stryker verdict (crash, or the TACO check found no bug), `124` timed out.

Good first runs:

| Driver | Expected |
|---|---|
| `icse.singlylinkedlist.set1.StrykerSinglyLinkedListContains1Bug25DTest` | Fix in ~10 s, 3 TACO calls |
| `icse.singlylinkedlist.set1.StrykerSinglyLinkedListContains2Bug4Dx10DTest` | Fix in ~15 s, 14 TACO calls, 11 mutants pruned |
| `icse.binomialheap.set1.StrykerBinomialHeapFindMinimum1Bug11DTest` | Fix in ~1 min |
| `ase2016.introclass.median.StrykerIntroClassMedian_0cdfa335_003Test` | Fix in ~10 s, 1 TACO call |

Run one benchmark per JVM: the controllers are singletons that are shut down
at the end of a run (the script does this for you).

### Running several benchmarks

Each data-structure suite has `Commands-<N>Bug.txt` files listing its drivers
by number of injected bugs (and `set*/SetCommands-<N>Bug.txt` per set):

```bash
while read -r driver; do
  scripts/run-benchmark.sh --reset --timeout 3600 "$driver" > "results-$driver.log" 2>&1
  echo "$driver exit=$?"
done < unittest/icse/singlylinkedlist/Commands-1Bug.txt
```

Results can vary from run to run, for single-bug benchmarks too (see
[KNOWN-LIMITATIONS.md](KNOWN-LIMITATIONS.md#search-behaviour)), so repeat a
run before reading much into a "no fix". Runs are long-lived and CPU heavy; don't run several at
once from the same checkout (they share `tests/`, `output/` and the report files). Use separate
clones to parallelize.

## Reading the results

`run-benchmark.sh` ends with a `STRYKER RESULT` block. Ignore the JUnit
`OK`/`FAILURES` line for this purpose: the driver only asserts TACO's verdict on
the original method, so it reports `OK` whether or not a fix was found.

| File (repository root) | Contents |
|---|---|
| `StrykerLastFixFound.java` | The repaired class |
| `StrykerLastReports.txt` | Report for each fix found: the mutant file, the mutation indexes of the solution, and statistics (time, TACO calls and time, generated, duplicate, non-compilable and pruned mutants, how many candidates went through each stage) |
| `stryker-console.log` | Everything the run printed, including `No fix was found` |
| `stryker.log` | Detailed log of the three main controllers (MuJava, OpenJML/RAC, Darwinist) |
| `tests/...` | The subject file, overwritten with the fix (most `//mutGenLimit` marks are stripped). Restore with `--reset` or `git checkout -- tests` |
| `generated/`, `output/` | TACO intermediate files: the JUnit counterexample tests and the Alloy model of the last analysis |

The script clears the report files before each run, and runs Stryker with its
own temp directory (thousands of mutant files), deleted afterwards unless you
pass `--keep-temp`.

## Benchmark suites

| Drivers in | Count | Contents |
|---|---|---|
| `unittest/icse/{singlylinkedlist,nodecachinglinkedlist,bintree,binomialheap}` | ~90 each | Data structures with 1 to 6 injected bugs, in five sets (`set1`-`set5`). File names encode the bug count and lines, e.g. `Contains2Bug4Dx10D` |
| `unittest/ase2016/introclass/{grade,median,smallest}` | 197 | IntroClass student programs, with JML contracts written by the authors (IntroClass has no formal specs) |
| `unittest/ase2016/{singlylinkedlist,nodecachinglinkedlist,bintree,binomialheap}` | 41 | Data-structure cases prepared for an ASE 2016 submission |
| `unittest/pldi` | 97 | Earlier data-structure experiments with long chains of injected bugs |
| `unittest/realbugs` | 5 | "Real bug" cases for singly linked list and binary tree methods (subjects in `tests/realbugs`) |
| `unittest/roops/core/objects` | 4 | ROOPS BinomialHeap drivers, used with the variants below |
| `unittest/ar/edu/taco/regresion/stryker` | 39 | Small examples and regression cases |
| `unittest/examples/gcd` | 126 | Currently broken, see [KNOWN-LIMITATIONS.md](KNOWN-LIMITATIONS.md) |

The rest of `unittest/` is TACO's own regression suite (no Stryker).

Which exact runs produced the numbers in the thesis and papers is not recorded
in the repository.

## BinomialHeap ROOPS variants

`benchmarks/binomialheap-roops-variants/set1..set5/` holds the 90 ICSE
BinomialHeap subjects rewritten as drop-in replacements for
`roops.core.objects.BinomialHeap`, so they can be analysed with the ROOPS
drivers and their precomputed tight bounds (`xml/`). Run them with
`--variant`, choosing the driver by method:

| Variant file name starts with | Driver |
|---|---|
| `BinomialHeapExtractMin` | `roops.core.objects.BinomialHeapTestExtractMin` |
| `BinomialHeapFindMinimum` | `roops.core.objects.BinomialHeapTestFindMin` |
| `BinomialHeapInsert` | `roops.core.objects.BinomialHeapTestInsert` |

```bash
scripts/run-benchmark.sh \
  --variant benchmarks/binomialheap-roops-variants/set1/BinomialHeapFindMinimum1Bug11D.java \
  roops.core.objects.BinomialHeapTestFindMin
```

The script copies the variant over `tests/roops/core/objects/BinomialHeap.java`,
rebuilds, runs, and restores the original afterwards.

## Writing your own benchmark

1. **Subject.** Put the class under `tests/<package>/`. It needs a JML contract
   for the method (`requires`/`ensures`, and class invariants if relevant).
   Mark the statements Stryker may change with `//mutGenLimit 1`; mark others
   `//mutGenLimit 0` or leave them unmarked. For example:

   ```java
   current = current.next; //mutGenLimit 0
   ...
   return false; //mutGenLimit 1
   ```

2. **Driver.** Copy a driver of a similar subject (e.g. one under
   `unittest/icse/singlylinkedlist/set1/`) and adapt it:

   ```java
   package mypackage;

   import ar.edu.taco.regresion.CollectionTestBase;
   import ar.uba.dc.rfm.dynalloy.visualization.VizException;

   public class StrykerMyListContainsTest extends CollectionTestBase {
       @Override
       protected String getClassToCheck() {
           return "mypackage.MyList";
       }

       public void test_containsTest() throws VizException {
           setConfigKeyRelevantClasses("mypackage.MyList,mypackage.MyListNode");
           setConfigKeyTypeScopes("mypackage.MyList:1,mypackage.MyListNode:5");
           setConfigKeyLoopUnroll(5);
           setConfigKeyGenerateUnitTestCase(true);
           setConfigKeyAttemptToCorrectBug(true);
           // ...the other keys used by the driver you copied
           check(GENERIC_PROPERTIES, "contains_0", true);
       }
   }
   ```

   `check(config, "<method>_0", true)` analyses the method and asserts that
   TACO finds a counterexample (`true`) in the original method. The `_0` suffix
   is part of TACO's method naming; copy it as in the existing drivers.
   `GENERIC_PROPERTIES` is `config/genericTest.properties`.
3. **Run** it: `scripts/run-benchmark.sh mypackage.StrykerMyListContainsTest`.

### Configuration keys

Drivers set keys through `setConfigKey...` methods of
`unittest/ar/edu/taco/regresion/RegresionTestBase.java`; they override
`config/genericTest.properties`. The ones the Stryker drivers use most:

| Driver method | Property | Meaning |
|---|---|---|
| `setConfigKeyAttemptToCorrectBug` | `attemptToCorrectBug` | Run Stryker when TACO finds a bug. Required |
| `setConfigKeyGenerateUnitTestCase` | `generateUnitTestCase` | Turn TACO counterexamples into JUnit tests. Required by Stryker |
| `setConfigKeyRelevantClasses` | `relevantClasses` | Classes included in the analysis |
| `setConfigKeyRelevancyAnalysis` | `relevancyAnalisys` | Compute the relevant classes automatically |
| `setConfigKeyTypeScopes` | `type_scopes` | Maximum number of objects per class, `Class:N,...` |
| `setConfigKeyObjectScope` | `objectScope` | Default object scope |
| `setConfigKeyInferScope` | `inferScope` | Infer scopes |
| `setConfigKeyLoopUnroll` | `dynalloy.toAlloy.loopUnroll` | Loop unrolling bound |
| `setConfigKeyIntBithwidth` | `int.bitwidth` | Integer bit width |
| `setConfigKeyUseJavaArithmetic` | `useJavaArithmetic` | Model Java integer overflow |
| `setConfigKeyCheckNullDereference` | `checkNullDereference` | Treat null dereferences as errors |
| `setConfigKeyCheckArithmeticException` | `checkArithmeticException` | Treat arithmetic exceptions as errors |
| `setConfigKeySkolemizeInstanceInvariant` / `...InstanceAbstraction` | `skolemizeInstanceInvariant` / `skolemizeInstanceAbstraction` | TACO encoding options |
| `setConfigKeyRemoveQuantifiers` | `dynalloy.toAlloy.removeQuantifiers` | TACO encoding option |
| `setConfigKeyUseJavaSBP` | `useJavaSBP` | Symmetry-breaking predicates |
| `setConfigKeyUseTightUpperBounds` | `useTightUpperBounds` | Use precomputed bounds from `xml/` (only exist for ROOPS classes) |
| `setConfigKeyMaxStrykerMethodsPerFile` | `maxStrykerMethodForFile` | Has no effect (see [KNOWN-LIMITATIONS.md](KNOWN-LIMITATIONS.md)) |

## Checking a change

There is no CI. After changing TACO or Stryker, run the smoke test:

```bash
scripts/smoke-test.sh           # ~2 min
scripts/smoke-test.sh --quick   # ~40 s, only the two singly-linked-list benchmarks
```

It runs the four benchmarks of the [table above](#running-one-benchmark) and
checks the exact outcome: fix found or not, which fix
(`LineMutationIndexes`), number of TACO calls and of pruned mutants. These
four benchmarks have given identical numbers in every run so far, on macOS and
Linux (not every benchmark does, see
[KNOWN-LIMITATIONS.md](KNOWN-LIMITATIONS.md#search-behaviour)), so a `FAIL`
most likely means the search behaves differently. If that is intended, update `EXPECTED` in
`scripts/smoke-test.sh` and explain it in the commit message. The output of
every run goes to `smoke-test.log`. It refuses to run if `tests/` has local
changes, and restores `tests/` at the end.

### Optional pre-push hook

```bash
git config core.hooksPath .githooks
```

From then on, `git push` runs `scripts/smoke-test.sh --quick` when the pushed
commits touch code (`src/`, `lib/`, `tests/`, `unittest/`, `scripts/`,
`build.xml`), and aborts the push if a benchmark changed.
Documentation-only pushes are not checked, nothing runs on commit, and the
hook lets the push through (with a warning) when no usable JDK 7, 8, 21 or 25
(with the Java 7 class library on JDK 8 and later) is set up, or `tests/` has local changes. Skip it once with `git push --no-verify`; turn it
off with `git config --unset core.hooksPath`.

## Running without the scripts

What the script does, if you need to do it by hand (from the repository root,
which must be the working directory):

```bash
scripts/build.sh
mkdir -p /tmp/stryker-run
"$JDK8/bin/java" -Djava.io.tmpdir=/tmp/stryker-run -Xmx4g -Xss8m \
  -cp "$(cat bin/runtime.classpath)" org.junit.runner.JUnitCore <DriverClass>
```

On JDK 8 and later the run reads the Java 7 class library from
`lib/java7/rt.jar` under the working directory; add
`-Dstryker.classlib=<rt.jar>` to use another. On an x86_64 JDK under Rosetta,
add `-XX:-TieredCompilation`; on JDK 7, add `-XX:MaxPermSize=512m`; on JDK 21 and
25, add the options listed in [BUILDING.md](BUILDING.md#jdk-21-or-25-optional).

`ar.edu.taco.TacoMain` also has a command line (`-cf <config> -c <class> -m
<method>`), but the drivers set many more options than it exposes; use
drivers for Stryker.
