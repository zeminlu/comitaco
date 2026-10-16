# Dependencies

All dependencies are committed under `lib/` (and Ant under `ant/`); nothing
is downloaded at build time. The build classpath is `lib.classpath` in
`build.xml`, which mirrors the IntelliJ library `.idea/libraries/lib.xml`
(keep them in sync).

## Tool chain jars

These are the components TACO and Stryker are built on. The `*2018.jar` files
are prebuilt snapshots taken from the 2018 working version of Stryker; their
exact source commits were not recorded. The "likely source" column is
inferred from build dates inside the jars and the history of the source
repositories.

| Jar | What it is | Used for | Likely source |
|---|---|---|---|
| `alloy4.jar` | Alloy 4.1.10 (RFM Group build, 2013), including Kodkod, SAT4J and the native SAT solvers (`x86-mac`, `amd64-linux`, `x86-linux` folders) | Solving the Alloy models TACO produces. MiniSat 2.2 through JNI is the default solver | RFM Group (UBA) build of Alloy 4; since 2026, MiniSat 2.2 for macOS also has an arm64 slice (below) |
| `dynalloy42018.jar` | DynAlloy: Alloy extended with actions/programs | Translation stage DynAlloy -> Alloy, Alloy analysis driver, JNI library setup | [zeminlu/dynalloy4](https://github.com/zeminlu/dynalloy4) `master` (built 2018-05-31, after its last commit of 2018-04-18) |
| `jdynalloy2018.jar` | JDynAlloy: a DynAlloy-based intermediate language for Java | Translation stage JML -> JDynAlloy -> DynAlloy | [zeminlu/jdynalloy](https://github.com/zeminlu/jdynalloy) `master` (built 2018-06-25, the date of its last commit) |
| `mujava2018.jar` | muJava++, an extension of muJava (mutation testing), with OpenJava | Generating mutants (`mujava.api`, `mujava.app.Mutator`), mutation operators | [saiema/MuJava](https://github.com/saiema/MuJava), most likely branch `mujava2-dev-1.7` (built 2018-05-31) |
| `openjava2018.jar` | OpenJava (Java 1.6/1.7 compatible fork), the parser and metaprogramming layer muJava uses | Used by muJava | [saiema/OJ-with-Java-1.6](https://github.com/saiema/OJ-with-Java-1.6) (built 2018-05-31) |
| `jml-release.jar` | JML2 tools on MultiJava (2012 build) | Parsing JML-annotated sources in TACO (`JmlStage`). Cannot use the class library of Java 8 or later | JML2 (jmlspecs) |
| `stryker/jml4c.jar` | JML4c, a JML runtime assertion checker compiler (2011) | Compiling mutant batches with runtime assertion checks; run by Stryker as `java -jar lib/stryker/jml4c.jar` and loaded in its own class loader. Cannot use the class library of Java 8 or later | JML4c (University of Texas at El Paso) |
| `recoder.jar` | RECODER Java source transformation framework (2011) | Loop unrolling before variabilization (`LoopUnrollTransformation`) | RECODER |
| `org.eclipse.*` (JDT core 3.8.1 and its runtime dependencies) | Eclipse Java Development Tools | AST parsing and rewriting in Stryker (instrumentation, variabilization), and the Eclipse compiler used by the build | Eclipse 3.8 / Juno |
| `jdtCompilerAdapter.jar` | Ant adapter for the Eclipse compiler | Build only (`build.xml` loads it with `<compilerclasspath>`) | Eclipse 3.8.1 |
| `edu.mit.csail.sdg.annotations_0.2.5.jar` | JFSL annotations | TACO's JFSL front end | MIT SDG |

## MiniSat 2.2 on Apple Silicon

`x86-mac/libminisat220.jnilib` in `alloy4.jar`, the MiniSat 2.2 library
TACO's default solver loads on macOS, is a universal library (x86_64 and
arm64) since 2026. Its x86_64 slice is the original 2012 build, byte for byte.
The arm64 slice is compiled by `scripts/build-minisat220.sh` from MiniSat
2.2.0 and Kodkod's JNI glue as published with Alloy 5.1.0 (both MIT, see
`LICENSES/MiniSat-2.2.txt`), with pinned sources, checksums, and no fused
multiply-add, so that the solver behaves like the x86_64 build: the smoke-test
benchmarks give the same fixes, TACO calls and pruned mutants on a native
arm64 JDK 8 as under Rosetta. The script can rebuild the jar (macOS with the
Xcode command line tools). The same `alloy4.jar` is in
[mffrias/dynalloy4](https://github.com/mffrias/dynalloy4), where it was built.
Linux has no arm64 (`aarch64-linux`) library yet.

DynAlloy extracts these libraries into
`<java.io.tmpdir>/alloy4tmp40-<user>/binary` and never overwrites a file
already there, so a copy from an older `alloy4.jar` (x86_64-only MiniSat)
would make an arm64 JVM fail with an `UnsatisfiedLinkError`. Since 2026
`TacoMain.run` first deletes extracted libraries that differ from the jar's
(`ar.edu.taco.SatSolverLibraries`). The scripts are not affected either way:
each run gets its own temp directory.

## Not in the repository: the Java 7 class library

On JDK 8 and later, the JML parser, JML4C and the variabilization parser read a
Java 7 `rt.jar` (`lib/java7/rt.jar`, git-ignored) instead of the JDK's own
class library, because they cannot use the class library of Java 8 or later. It is not committed
(61 MB): `scripts/fetch-java7-classlib.sh` downloads Azul Zulu 7u352 for Linux
x64 (an OpenJDK build, GPL v2 with the Classpath Exception), checks its
SHA-256, and extracts only `jre/lib/rt.jar`, or copies the `rt.jar` of a local
JDK 7 (`--from`). Who reads it, and how to point at another copy:
`ar.edu.taco.Java7ClassLibrary` and
[BUILDING.md](BUILDING.md#the-java-7-class-library).

## Libraries

| Jar | Used for |
|---|---|
| `guava-16.0.1.jar` | Collections, hashing, file utilities (Stryker) |
| `commons-lang3-3.3.2.jar`, `commons-lang-2.4.jar` | Pairs/tuples (Stryker) and string utilities |
| `commons-configuration-1.6.jar`, `commons-collections-3.2.1.jar`, `commons-logging-1.1.1.jar` | TACO configuration (`TacoConfigurator`) |
| `log4j-1.2.15.jar` | Logging (`config/log4j.xml`) |
| `javassist.jar`, `reflections-0.9.9-RC1.jar` | Required by muJava |
| `antlr-4.3-complete.jar` | Parser runtime for TACO's JFSL front end (`JfslParser`) |
| `junit-4.8.2.jar` | Running the drivers (JUnit 3 style tests through `JUnitCore`) and the generated counterexample tests |
| `junit.jar`, `org.hamcrest.core_1.3.0...jar` | Not on the build classpath |

## Rebuilding the tool chain jars

The build process of the `*2018.jar` files was not recorded. If you need to
change DynAlloy, JDynAlloy, muJava or OpenJava, clone the repositories above,
build them with a JDK 7, 8, 21 or 25 (Java 7 target), and replace the jar. Before
2018 these were Eclipse projects referenced from `.classpath` instead of jars
(see [BRANCHES.md](BRANCHES.md)). Rerun a few benchmarks before and after
replacing a jar: the produced fixes and the TACO call counts should not
change.
