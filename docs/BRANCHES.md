# Branches and history

## The 2026 convergence

Until 2026, `master` (last commit `edf6892a`, 2018-06-15) could not be built
from a clone: its `build.xml` referenced jars that were never committed, and
`.classpath` expected four sibling Eclipse projects. The version actually used
in 2018 lived on the `weedqueue-2018*` branches. `master` was brought up to
that version with these commits on top of `edf6892a`:

1. **Build**: dependency jars and Ant committed, `build.xml` and `.classpath`
   fixed (from `weedqueue-2018` and `weedqueue-2018-roopsbinheap-fixbhnode`).
2. **WeedQueue**: the shared queue that coordinates the controllers and ends
   the run when there is no work left (from `weedqueue-2018`).
3. **Three bug fixes** from `weedqueue-2018-roopsbinheap-fixbhnode`
   (cherry-picked, with `cherry picked from` references).
4. **WeedQueue race fix** (new): a run could end early with "No fix was
   found" (see [KNOWN-LIMITATIONS.md](KNOWN-LIMITATIONS.md)).
5. **Fresh checkout runnable**: scripts, Java 7 guard, and fixes for a missing
   output directory and for paths (temp directories starting with `a`,
   repository paths with spaces or containing `generated`) that made runs hang
   or silently find nothing (new).
6. **Counterexample pool** (new): room for 10000 counterexamples instead of
   50, and a counterexample that cannot be stored no longer hangs the run.
7. **Search fixes** (new): a TACO error during feedback no longer prunes the
   whole search, a counterexample that cannot become a test no longer hangs
   the run, a feedback jump is applied only once, and a feedback line with no
   mutations left no longer re-queues the same candidate (four commits).
8. **BinomialHeap ROOPS variants** into `benchmarks/` (from the `roopsbinheap`
   branches).
9. **No leaks between runs** (new): class files left in `tests/` no longer
   reach `bin/`, and `--reset` removes them.
10. **Smoke test and optional pre-push hook** (new; there is no CI).
11. **Optional Docker environment** (new): JDK 7 on Linux x86_64, with the
   repository mounted.
12. **Documentation**, including Javadoc.

After the convergence, `src/` is identical to
`weedqueue-2018-roopsbinheap-fixbhnode` plus master's floating-point commit
`edf6892a`, apart from the fixes in steps 4 to 7. The smoke-test benchmarks
give byte-identical fixes with the same statistics on both.

## Since the convergence

The tag `converged-2026-10` marks the end of the convergence (JDK 7 only, with
an Eclipse project). After it:

1. **`System.out` in counterexamples** (new): a counterexample that stores
   `System.out` in a field is no longer lost (see
   [KNOWN-LIMITATIONS.md](KNOWN-LIMITATIONS.md)).
2. **JDK 8** (new): TACO and Stryker run on JDK 8, with a Java 7 class library
   for the JML tools (see [BUILDING.md](BUILDING.md)). JDK 7 still works.
3. **IntelliJ instead of Eclipse** (new): the Eclipse project files were
   replaced by an IntelliJ IDEA project.
4. **Ant 1.9.16** (new): the bundled Ant, from 1.9.13 to the last 1.9 release.

## Branches on GitHub

The old branches are kept as an archive. "Ahead" is the number of commits not
in the pre-convergence `master`.

| Branch | Last commit | Status |
|---|---|---|
| `weedqueue-2018-roopsbinheap-fixbhnode` | 2018-11-30 | **Merged.** Latest 2018 working version: WeedQueue, prebuilt jars, BinomialHeap ROOPS variants, bug fixes. Its history replays master's history under new hashes (forked 2015-08-18), so it looks far more divergent than it is |
| `weedqueue-2018-roopsbinheap` | 2018-05-31 | **Merged** (through the branch above). Same code as `weedqueue-2018`; rewrote the ICSE BinomialHeap subjects as ROOPS drop-ins |
| `weedqueue-2018` | 2018-05-31 | **Merged.** "2018 Working Version": master as of 2018-04-24 plus the WeedQueue commits and the prebuilt jars |
| `debug-false-negative` | 2018-09-18 | **Not merged**: a debugging harness (hard-coded start indexes, batch size 1, enqueues commented out) branched from `fixbhnode`'s parent |
| `variablizeMultipleInputs` | 2015-03-18 | **Not merged**: an experiment that re-checks variabilization against up to 3 counterexamples to prune more. Sound in principle, but as written the extra checks hit the verdict cache (keyed by file, not by input) and always pick the same next input. Worth revisiting as an idea |
| `weedQueue`, `newWeedQueue` | 2015-08 | Original WeedQueue work, merged through `weedqueue-2018` |
| `roopsbinheap` | 2015-08-28 | BinomialHeap experiments on the ROOPS class, continued in `weedqueue-2018-roopsbinheap` |
| `clusterCompilationMode`, `newClusterCompilationMode`, `clusterCompilationModeWithSimplJNI` | 2015-10/11 | Experiments with running on a cluster and calling TACO through JNI. Not analysed, not merged |
| `rebaseStrykerICSE2015` | 2015-08-27 | 70 commits ahead. Not analysed, not merged |
| `rebasedTaco` | 2015-06-13 | 62 commits ahead. Not analysed, not merged |
| `batcheddirectedmutationswithreloader` | 2014-11-20 | Class-reloader experiment, 1 commit ahead. Not merged |
| `concoParser` | 2013-11-07 | 1 commit ahead ("parser"). Not merged |
| `merge-new-version` | 2016-03-17 | "New TACO version - Rev 116", 1 commit ahead. Not merged |
| `method-name` | 2017-02-14 | 1 commit ahead ("Provisory"). Not merged |
| `fixes`, `new-tpsh-version`, `old-version`, `patch-for-arrays` | 2016-2017 | Fully contained in `master` |

## Related work outside this repository

- The distributed version of Stryker (thesis Chapter 4) was developed
  separately and is not in this repository.
- `zeminlu/stryker` is an earlier, standalone Maven prototype of Stryker
  (2014), not this code base.
- The source repositories of the bundled tool chain jars are listed in
  [DEPENDENCIES.md](DEPENDENCIES.md).
