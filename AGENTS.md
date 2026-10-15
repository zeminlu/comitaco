# AGENTS.md

Guide for coding agents (and humans in a hurry) working on this repository:
Stryker, a program-repair tool for JML-annotated Java, built on the TACO
bounded verifier. Human-oriented docs are in `README.md` and `docs/`.

## Environment (hard constraints)

- **JDK 7 only.** Not 8, not 11, not 17. The JML parser and the runtime
  assertion checker cannot read newer class files; `TacoMain.run` throws
  `IllegalStateException` on other versions and the Ant build fails.
- **x86_64 only** (native MiniSat via JNI). On Apple Silicon use an x86_64
  JDK 7 under Rosetta. No ARM Linux.
- **macOS or Linux.** Windows only through WSL2 (follow the Linux steps
  inside it, with the clone in the Linux file system); native Windows does not
  work.
- **Working directory = repository root.** Paths such as `config/`, `tests/`,
  `bin/` and `lib/stryker/jml4c.jar` are resolved against it.
- Use the **bundled Ant** (`ant/bin/ant`, 1.9.13) or the scripts, never a
  system Ant (1.10+ needs Java 8).
- If no JDK 7 is installed, see `docs/BUILDING.md` (Azul Zulu 7u352 for
  macOS x64 / Linux x64) and set `STRYKER_JAVA_HOME`. If Docker is available
  instead, prefix any command with `scripts/docker.sh` (optional environment
  with JDK 7; the repository is mounted, builds are interchangeable).

## Commands

```bash
scripts/build.sh                                  # compile into bin/ (~10 s)
scripts/run-benchmark.sh --reset <DriverClass>    # run one repair benchmark
scripts/run-benchmark.sh --help
```

Exit status of `run-benchmark.sh`: `0` fix found, `1` no fix (search space
exhausted), `2` usage/environment error, `3` build failed, `4` no Stryker
verdict, `124` timeout. Read the `STRYKER RESULT` block or the exit status,
**not** the JUnit `OK`: drivers pass whether or not a fix is found.

After any change to Stryker or TACO, run the smoke test. It checks that four
reference benchmarks still give exactly the expected results (fix or not,
which fix, TACO calls, pruned mutants); these four have given identical
results in every run so far, so a difference almost certainly is a behaviour
change. It needs a clean `tests/`.

```bash
scripts/smoke-test.sh           # ~2 min, all must PASS
scripts/smoke-test.sh --quick   # ~40 s, the two singly-linked-list benchmarks
```

If a change is meant to alter the search, update `EXPECTED` in
`scripts/smoke-test.sh` and say so in the commit message. There is no CI; the
optional pre-push hook (`git config core.hooksPath .githooks`) runs the quick
smoke test when a push touches code.

Do **not** run `ant unittest` / `scripts/build.sh unittest`: it runs every
driver, including hundreds of Stryker benchmarks (many hours).

## Things that will bite you

- A successful run **overwrites the subject file under `tests/`** with the fix
  (and strips most `//mutGenLimit` marks). Never commit those changes. Use
  `--reset` or `git checkout -- tests`.
- Run **one Stryker benchmark per JVM** and **one run at a time per
  checkout**: controllers are singletons, and runs share `tests/`, `output/`,
  `generated/` and the `StrykerLast*` report files.
- Outputs of a run (repository root, all git-ignored): `StrykerLastFixFound.java`,
  `StrykerLastReports.txt`, `stryker-console.log`, `stryker.log`, `output/`,
  `generated/`.
- Results are not deterministic for every benchmark, single-bug ones
  included: the same benchmark can find a fix in one run and report "No fix
  was found" in the next, because pruning is sometimes unsound (see
  `docs/KNOWN-LIMITATIONS.md`, "Search behaviour"). Treat a "no fix" as "not
  found in this run" and repeat the run before drawing conclusions. The smoke
  test uses benchmarks that have given identical results in every run.
- `tests/` holds subject programs, not tests. Drivers (JUnit) are in
  `unittest/`. `benchmarks/` is intentionally not compiled.
- Mutable statements are the ones marked `//mutGenLimit N` (N > 0) in the
  subject. There is no automatic fault localization.
- `build.xml` (`lib.classpath`) and `.classpath` must list the same jars.
  `lib/stryker/jml4c.jar` must stay off both.
- The `*2018.jar` files in `lib/` are prebuilt snapshots; see
  `docs/DEPENDENCIES.md` before replacing one.
- `OpenJMLController` does runtime assertion checking with **JML4C**, not
  OpenJML (historical name).
- Known limitations and behaviours that look like bugs are listed in
  `docs/KNOWN-LIMITATIONS.md`; check there before "fixing" something, since
  changing it may change experimental results.

## Code map

- `src/ar/edu/taco/TacoMain.java`: entry point (`run`); TACO pipeline, then
  Stryker when `attemptToCorrectBug=true` and TACO finds a bug.
- `src/ar/edu/taco/engine/StrykerStage.java`: starts Stryker, holds run-wide
  static state (counterexample pool, statistics, `weedQueue`).
- `src/ar/edu/taco/stryker/api/impl/`: the controllers
  (`UnskippableMuJavaController` -> `MuJavaController` -> `OpenJMLController`
  -> `DarwinistController`), `WeedQueue`, variabilization
  (`VariablizationData`, `StrykerVariablizerVisitor`) and the message types
  (`input/`).
- How it fits together: `docs/ARCHITECTURE.md`. Thesis algorithms to code:
  `docs/THESIS-MAPPING.md`. The classes above have Javadoc.

## Conventions

- Java 7 source level: no lambdas, streams, default methods or any other
  Java 8+ language feature or API.
- Keep changes behaviour-preserving unless asked otherwise: this code produced
  published experimental results. When a change alters search behaviour, say
  so and re-run the smoke test, comparing TACO-call and pruning counts.
- Comments and docs in English. Existing Spanish comments stay.
