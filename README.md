# Stryker on TACO (comitaco)

**Stryker** repairs bugs in JML-annotated Java methods. Given a method that
violates its contract, Stryker mutates the statements you mark as suspicious
and accepts a candidate only if it passes runtime assertion checking against
every counterexample found so far *and* bounded verification with TACO. Along
the way, a SAT-based check ("variabilization") proves that whole regions of the
mutation space contain no fix and skips them.

This repository contains:

- **TACO** (Translation of Annotated COde), a bounded verifier that translates
  JML-annotated Java to DynAlloy/Alloy and solves it with a SAT solver;
- **Stryker**, built on top of TACO (`src/ar/edu/taco/stryker`);
- the **benchmarks** used to evaluate it (Chapter 3 of Luciano Zemin's PhD
  thesis, ITBA, *Using Specifications to Overcome the Limitations of Test
  Based Automated Program Repair*).

## Quick start

You need a **JDK 8 for x86_64** (e.g. Azul Zulu 8; JDK 7 works too), `bash`,
`git` and `curl`. Nothing else: the dependency jars and the right Ant version
are in the repository. This works on **macOS** (Intel, or Apple Silicon under
Rosetta), **Linux x86_64**, and **Windows through WSL2** (not natively). See
[docs/BUILDING.md](docs/BUILDING.md) for installing the JDK.

```bash
scripts/fetch-java7-classlib.sh   # once, on JDK 8: the old JML tools need a Java 7 class library
scripts/build.sh
scripts/run-benchmark.sh icse.singlylinkedlist.set1.StrykerSinglyLinkedListContains1Bug25DTest
```

No JDK and don't want to install one? With Docker, prefix the commands with
`scripts/docker.sh` (see [Docker](docs/BUILDING.md#alternative-docker)):
`scripts/docker.sh scripts/run-benchmark.sh ...`.

After about 10 seconds you should see:

```
================================ STRYKER RESULT ================================
FIX FOUND. Repaired class: StrykerLastFixFound.java  Full report: StrykerLastReports.txt
```

Stryker writes the fix into the subject file under `tests/`, so use
`--reset` the next time you run a benchmark. More in
[docs/RUNNING.md](docs/RUNNING.md).

## Documentation

| Document | What it covers |
|---|---|
| [docs/BUILDING.md](docs/BUILDING.md) | Requirements, installing JDK 8 (or 7) on macOS and Linux, the Java 7 class library, building, Eclipse, troubleshooting |
| [docs/RUNNING.md](docs/RUNNING.md) | Running benchmarks, reading the results, the benchmark suites, writing your own benchmark, configuration keys |
| [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) | How a repair run works: TACO pipeline, Stryker controllers, queues, feedback/pruning, files on disk |
| [docs/THESIS-MAPPING.md](docs/THESIS-MAPPING.md) | Where each thesis algorithm (Chapter 3) lives in the code, and where the code differs |
| [docs/DEPENDENCIES.md](docs/DEPENDENCIES.md) | Every jar in `lib/`: what it is, who uses it, where it came from |
| [docs/KNOWN-LIMITATIONS.md](docs/KNOWN-LIMITATIONS.md) | Behaviours and bugs to be aware of when running or changing Stryker |
| [docs/BRANCHES.md](docs/BRANCHES.md) | History of the branches and what was merged into `master` |
| [AGENTS.md](AGENTS.md) | Short operational guide for coding agents (also useful for humans in a hurry) |

The main classes also carry Javadoc; start with `TacoMain.run`,
`StrykerStage`, `SimpleStrykerAPI` and the controllers in
`src/ar/edu/taco/stryker/api/impl/`.

## Repository layout

| Path | Contents |
|---|---|
| `src/` | TACO and Stryker sources (`ar.edu.taco...`), plus patched JML2/MultiJava classes (`org/`) |
| `tests/` | Subject programs: the (buggy) JML-annotated classes that benchmarks analyse and repair |
| `unittest/` | JUnit 3 drivers. Each Stryker benchmark is a driver that configures TACO/Stryker and runs it on a subject |
| `benchmarks/` | Subject variants that are not compiled by the build (see its README) |
| `config/` | TACO configuration files (`genericTest.properties` is the base for the benchmarks) and `log4j.xml` |
| `specs/` | JML specifications of JDK classes, used by the JML parser |
| `xml/` | Precomputed tight upper bounds for ROOPS classes (used when `useTightUpperBounds=true`) |
| `lib/` | Dependency jars (see [docs/DEPENDENCIES.md](docs/DEPENDENCIES.md)); `lib/stryker/jml4c.jar` is the runtime assertion checker |
| `ant/` | Apache Ant 1.9.13 (Ant 1.10+ cannot run on Java 7) |
| `scripts/` | `build.sh`, `run-benchmark.sh`, `smoke-test.sh`, `docker.sh` |
| `docker/` | Optional Docker environment (JDK 7 on Linux x86_64) |
| `.githooks/` | Optional pre-push check (see [docs/RUNNING.md](docs/RUNNING.md#checking-a-change)) |

## Why "comitaco"?

Stryker started as an add-on on top of TACO, and the TACO repository that
held the Stryker classes lived in SVN. When Luciano Zemin moved the team to
git, that repository became *comitaco*: **commit** + **TACO**. Read in
Spanish, *comí taco* means "I ate taco".

## License

TACO is distributed under the GNU General Public License v2 (see `COPYING`);
licenses of bundled third-party components are in `LICENSES/` and `ant/`.
