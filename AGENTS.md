# AGENTS.md

Guide for coding agents (and humans in a hurry) working on this repository:
Stryker, a program-repair tool for JML-annotated Java, built on the TACO
bounded verifier. Human-oriented docs are in `README.md` and `docs/`.

## Environment (hard constraints)

- **JDK 8, 21, 25 or 7 only.** Not 11, not 17, not 27. The JML parser and
  the runtime assertion checker cannot use the class library of Java 8 or
  later, so on JDK 8 and later they read a **Java 7 class library**,
  `lib/java7/rt.jar` (git-ignored; create it once with
  `scripts/fetch-java7-classlib.sh`). `TacoMain.run` throws
  `IllegalStateException` on other versions or when the class library is
  missing, and the Ant build fails. See `ar.edu.taco.Java7ClassLibrary`.
- On **JDK 21 and 25** runs need `--add-opens` for `java.base/java.lang` and
  `java.base/java.util` (TACO reflects into JDK objects of counterexamples)
  and `--enable-native-access=ALL-UNNAMED` (TACO loads MiniSat itself). The
  scripts add them (`java21plus_opts` in `scripts/common.sh`); the shared
  IntelliJ run configuration passes them in `JDK_JAVA_OPTIONS`. Results there
  are close to JDK 8's but vary more between runs; use JDK 8 (or 7) to
  compare with earlier results. See `docs/BUILDING.md`, "JDK 21 or 25".
- **x86_64 or arm64** (native MiniSat via JNI). `lib/alloy4.jar` bundles
  MiniSat 2.2 for macOS as a universal library and for Linux on x86 and arm64,
  so on Apple Silicon a native arm64 JDK 8, 21 or 25 works and is the
  fastest; x86_64 JDKs run under Rosetta. JDK 7 only exists for x86_64.
- **macOS or Linux.** Windows only through WSL2 (follow the Linux steps
  inside it, with the clone in the Linux file system); native Windows does not
  work.
- **Working directory = repository root.** Paths such as `config/`, `tests/`,
  `bin/` and `lib/stryker/jml4c.jar` are resolved against it.
- Use the **bundled Ant** (`ant/bin/ant`, 1.9.16) or the scripts, never a
  system Ant (1.10+ needs Java 8).
- The scripts find the JDK themselves (JDK 8 first, then 7, 21 and 25);
  otherwise set `STRYKER_JAVA_HOME`. If no JDK is installed, see `docs/BUILDING.md` (Azul
  Zulu 8u504 for macOS aarch64 or x64, and Linux x64). If Docker is
  available instead, prefix any command with `scripts/docker.sh` (optional
  environment: JDK 7 on x86_64, JDK 8 on arm64; the repository is mounted,
  builds are interchangeable).
- **The scripts' build stays Java 7 bytecode** (`source`/`target` 1.7) on
  every JDK (ECJ 3.8.1 compiles nothing newer). IntelliJ compiles to Java 8
  bytecode, because `javac` 20+ cannot target 7; the JML tools accept both.

## Commands

```bash
scripts/fetch-java7-classlib.sh                   # once, on JDK 8 or later (not needed on JDK 7)
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
- `build.xml` (`lib.classpath`) and the IntelliJ library
  (`.idea/libraries/lib.xml`) must list the same jars, in the same order.
  `lib/stryker/jml4c.jar` must stay off both. The IntelliJ module's output
  folder must stay `bin/`.
- The `*2018.jar` files in `lib/` are prebuilt snapshots; see
  `docs/DEPENDENCIES.md` before replacing one.
- `OpenJMLController` does runtime assertion checking with **JML4C**, not
  OpenJML (historical name).
- Whatever rewrites the JML4C batch files (`adaptSiblingsFileToJML4C`,
  `RacTimeoutInstrumenter`) must not add or remove lines: `MuJavaController`
  maps JML4C's error lines back to mutants by line number.
- Known limitations and behaviours that look like bugs are listed in
  `docs/KNOWN-LIMITATIONS.md`; check there before "fixing" something, since
  changing it may change experimental results.
- TACO gets some verdicts wrong, both ways (e.g. a postfix `++` in a `return`
  loses its update, both branches of `?:` run): see
  `docs/KNOWN-LIMITATIONS.md`, "TACO's verdicts". They are left unfixed on
  purpose, since fixing them changes verdicts; on a subject that uses those
  constructs, TACO's verdict may not match Java.

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
  Java 8+ language feature or API (on JDK 8 and later the build compiles against
  the Java 7 class library, so Java 8 APIs fail the build). IntelliJ's
  language level is 8, so it won't flag them: `scripts/build.sh` will.
- Keep changes behaviour-preserving unless asked otherwise: this code produced
  published experimental results. When a change alters search behaviour, say
  so and re-run the smoke test, comparing TACO-call and pruning counts.
- Comments and docs in English. Existing Spanish comments stay.
