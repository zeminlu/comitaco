# Thesis to code

The algorithms of Chapter 3 of the thesis (*Stryker: Scaling Multiline
Specification-Based Program Repair*, section *Specification-Based Mutants
Pruning*, source `chapters/chapter3/spec-based-pruning.tex` in the thesis
repository) and where they are implemented. The pseudocode is sequential; the
implementation is the same algorithm spread over the asynchronous controllers
described in [ARCHITECTURE.md](ARCHITECTURE.md).

Algorithm numbers are as printed in the thesis: Algorithm 1 is the sample
method `getNode`, Algorithm 2 is `getFeedback` (label `get-feedback`) and
Algorithm 3 is `iterateWhilePruning` (label `alg-pruning`). Chapter 4
(distributed Stryker, Algorithms 4-6) is not implemented in this repository.

## Representation

| Thesis | Code |
|---|---|
| Mutable statements, bounded by `//mutGenLimit k` | Lines of the subject marked `//mutGenLimit N`, `N > 0` (no automatic fault localization) |
| Statements numbered bottom-to-top | Same: position 0 of `lineMutationIndexes` is the bottom-most mutable line |
| Mutation array `[j1 \| j2 \| ... \| jm]`, `0 <= ji <= ki` | `Integer[] lineMutationIndexes` in `MuJavaFeedback`; 0 means "not mutated" |
| `ki`, number of mutations of statement `Si` | Row lengths of the `Mutation[][]` returned by `MuJavaController.getMutatorsList` (rows in ascending line order, so position `i` is row `n-1-i`) |
| Lexicographic traversal, leftmost position least significant | `MuJavaController.calculateNextRelevantSonMutationsLists` advances the array with carry from position 0 |
| Repeated mutations of a statement, hashing to avoid duplicates | Fathers: mutants that are mutated again (`MuJavaController.fatherize`); duplicates removed by hashing |

## Algorithm 2: getFeedback

```
k = 0; unsat? = true
while (k < m && unsat?):
    M = M_U^{JML,args}(k+1)      // first k+1 statements variabilized
    unsat? = TACO(M)
    if unsat?: k = k+1
return k
```

| Pseudocode | Code |
|---|---|
| Whole algorithm | `DarwinistController.computateFeedback` -> `variablize`, run on `MuJavaController`'s thread (section 5 of ARCHITECTURE.md) |
| `M_U^{JML,args}(k+1)` | `VariablizationData.variablizeNext`: replaces the next statement, bottom-up and cumulatively, by fresh variables (`customvar_N` parameters) |
| Contract requiring the failing input `args` | `DarwinistController.getTacoMainWithFixedInput`: TACO runs with the input fixed to the counterexample that made the candidate fail |
| `unsat? = TACO(M)` | `tacoMain.run(...)`, `satVerdict = ...isSAT()`; loop `while (satVerdict == false)` |
| `k < m` | `variablizeNext` returns `false` when nothing else can be variabilized |
| `return k` | `feedback.setSkipUntilMutID(vdata.getLastVariablizedMutID() - 1)`, then `MuJavaController.getFeedbackIndex` converts it to the array position `k` |

About the `-1`: mutation IDs are written into the source 1-based
(`StrykerJavaFileInstrumenter`), so that negative IDs can mark lines that must
not be variabilized; the `-1` turns the ID into a 0-based index into the list
of mutable lines. `skipUntilMutID` is a line index, not `k`;
`getFeedbackIndex` finds that line's position in the current mutation array.
The resulting `k` is the number of statements (bottom-up) whose
variabilization was UNSAT before the first SAT one, which is what the
pseudocode returns.

Differences from the pseudocode:

- The right-hand side and the left-hand side of a statement are variabilized
  in separate TACO calls. Their outcome also drives skipping inside a single
  statement (`MuJavaFeedback.mutateRight`).
- The loop also stops when the variabilized method does not compile or TACO
  fails to analyse it; a TACO error stops it like a SAT verdict.
- TACO verdicts are cached by file content (`VariablizedSATVerdicts`).

## Algorithm 3: iterateWhilePruning

| Pseudocode | Code |
|---|---|
| `Q.pop()` | `MuJavaController` taking a `MuJavaInput` from queue 1 of `WeedQueue` |
| `getNumberOfMutationsPerMutableStatement()`, `new ArrayIterator(k1..km)` | `getMutatorsList`, plus the initial `lineMutationIndexes` set up in `fatherize` |
| `M.applyMutsAndUpdatePendingMuts(it.next())` | `calculateNextRelevantSonMutationsLists` (next array) and `buildNextBatchSiblingsFile` (writes up to 100 mutants as methods of one class, through muJava's `Mutator`, compiled with JML4C) |
| `passesRAC? = passesRAC? && JML-RAC(M', i)` for every input in `S` | `OpenJMLController`: AND over inputs with early exit (`for (...; ... && !failed; ...)`). **Differences:** at most 9 inputs, taken round-robin, and a 3-second timeout counts as a pass |
| `passesTACO? = TACO(M')` | `DarwinistController.validateCandidate`. Inverted sense: TACO SAT means a counterexample exists, i.e. the candidate is **not** a fix |
| `return M'` | Not immediately: `lastRACAnalysis` first re-runs RAC on **all** stored counterexamples (5-second timeout, also counted as a pass), and only then `fixFound`. Needed because the earlier RAC step checks at most 9 inputs |
| `Q.add(M')` if further mutations are allowed | `MuJavaController.fatherize`, when the candidate is fatherable (`MuJavaFeedback.isFatherable()`) |
| `I` = failing input from RAC or TACO | A new TACO counterexample is turned into a JUnit test and added to the pool (`DarwinistController.queueFalseCandidate`); RAC failures reuse the stored inputs |
| `k = getFeedback(M', I)` | A feedback request (`MuJavaInput` with `computateFeedback=true`) on queue 1 |
| `setToZeroAllPositionsInRange(0, k-1)`, `advanceOneStartingAtIndex(k)` | `calculateNextRelevantSonMutationsLists`. It also skips mutations that don't compile and applies the right/left-hand-side skipping, so it can jump further than this |
| `return "not fixable"` when `k >= m` or the iterator is exhausted | The carry overflows: no more siblings for that father. The whole run ends with "No fix was found" when no work is left in any queue (`WeedQueue`) |

Beyond the pseudocode, `UnskippableMuJavaController` first enumerates the
left-hand-side mutations (PRVOL_SMART) that the feedback can never prune, and
feeds each as a new base to the search.
