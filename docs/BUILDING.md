# Building

## Requirements

| Requirement | Why |
|---|---|
| **JDK 8** (recommended), **JDK 21**, **JDK 25** or **JDK 7** | TACO and Stryker run on Java 7, 8, 21 and 25; `TacoMain.run` and the Ant build refuse other versions. The JML parser (`lib/jml-release.jar`) and the runtime assertion checker JML4C (`lib/stryker/jml4c.jar`) read the JDK's class library, and they cannot use the one of Java 8 or later, so on JDK 8 and later they read a Java 7 class library instead (next row). |
| On JDK 8 and later, a **Java 7 class library** | A Java 7 `rt.jar` at `lib/java7/rt.jar`. `scripts/fetch-java7-classlib.sh` puts it there: one 68 MB download, checksum-verified. Not needed on JDK 7. See [The Java 7 class library](#the-java-7-class-library). |
| **x86_64**, or **arm64 on macOS** | The SAT solver (MiniSat 2.2, used through JNI) is bundled in `lib/alloy4.jar` for macOS as a universal library (x86_64 and arm64, see [DEPENDENCIES.md](DEPENDENCIES.md)) and for Linux only for Intel (`amd64-linux`, `x86-linux`). On Apple Silicon a native arm64 JDK 8, 21 or 25 is the fastest option; an x86_64 JDK runs under Rosetta, and JDK 7 only exists for x86_64. ARM Linux (aarch64) is not supported. |
| macOS or Linux | Windows only through WSL2 (see [Windows](#windows-use-wsl2)). Without a local JDK, the optional [Docker environment](#alternative-docker) covers all three. |
| `bash`, `git`, `curl` | Used by the scripts (`git` restores the subject files, `curl` downloads the class library). |

Nothing else is needed: the dependency jars and Apache Ant 1.9.16 (the last
1.9 release) are in the repository. Use the bundled Ant (the scripts do):
Ant 1.10+ requires Java 8 and fails on JDK 7 with
`UnsupportedClassVersionError ... 52.0`.

**Which JDK?** All of them compile the project to the same class files (Java 7
bytecode, checked byte for byte), and the smoke-test benchmarks give the same
results on all of them. JDK 8 is the default. JDK 7 was the JDK of the
original experiments: on JDK 8, TACO's Alloy models list their declarations in
a different order, so other benchmarks may get different counterexamples and
differ in TACO calls or pruning (see
[KNOWN-LIMITATIONS.md](KNOWN-LIMITATIONS.md#environment)). JDK 21 and 25 are
close to JDK 8 but not identical, and vary more from run to run (see
[JDK 21 or 25](#jdk-21-or-25-optional)).

Tested on macOS (Apple Silicon) natively with Azul Zulu 8u504 and Eclipse
Temurin 21.0.12.1 and 25.0.4.1 for arm64, and under Rosetta with Azul Zulu 8u504, Oracle
JDK 8u261, Oracle JDK 7u71, Azul Zulu 7u352 and Temurin 21.0.12.1 (all
x86_64); and on Linux x86_64 (Debian 12, in the Docker environment) with Azul
Zulu 8u504 and 7u352.

## Installing a JDK 8

Azul Zulu 8 is free; 8u504 is the build tested here. You don't need to make
it your default Java: the scripts find it on their own (see
[How the scripts find the JDK](#how-the-scripts-find-the-jdk)). After
installing it, fetch the [Java 7 class library](#the-java-7-class-library)
once:

```bash
scripts/fetch-java7-classlib.sh
```

### macOS

Download the build for your Mac, **aarch64** on Apple Silicon (it runs
natively) or **x64** on an Intel Mac, and either put it where macOS registers
JDKs (no `sudo` needed for your user's folder):

```bash
# Apple Silicon; on an Intel Mac, use macosx_x64 instead of macosx_aarch64
curl -LO https://cdn.azul.com/zulu/bin/zulu8.96.0.205-ca-jdk8.0.504-macosx_aarch64.tar.gz
tar -xzf zulu8.96.0.205-ca-jdk8.0.504-macosx_aarch64.tar.gz
mkdir -p ~/Library/Java/JavaVirtualMachines
mv zulu8.96.0.205-ca-jdk8.0.504-macosx_aarch64 ~/Library/Java/JavaVirtualMachines/zulu-8.jdk
```

or keep it anywhere and point the scripts at it:

```bash
export STRYKER_JAVA_HOME=$HOME/jdks/zulu8.96.0.205-ca-jdk8.0.504-macosx_aarch64/Contents/Home
```

If you downloaded the archive with a browser, macOS may block the binaries;
clear the quarantine flag with `xattr -dr com.apple.quarantine <jdk dir>`.
Other JDK 8 builds work too (Oracle 8u261 x64 was tested). On Apple Silicon,
an x86_64 JDK also works, under Rosetta
(`softwareupdate --install-rosetta --agree-to-license`), but runs took up to
twice as long as with the native arm64 JDK.

### Linux (x86_64)

```bash
curl -LO https://cdn.azul.com/zulu/bin/zulu8.96.0.205-ca-jdk8.0.504-linux_x64.tar.gz
sudo mkdir -p /usr/lib/jvm
sudo tar -xzf zulu8.96.0.205-ca-jdk8.0.504-linux_x64.tar.gz -C /usr/lib/jvm
```

The scripts look in `/usr/lib/jvm` automatically. To install it somewhere else
(no `sudo` needed), extract it anywhere and set `STRYKER_JAVA_HOME` to the
extracted directory. Don't register it with `update-alternatives` as the
system Java unless you want to.

### The Java 7 class library

On JDK 8 and later, the JML parser, JML4C and the parser used for
variabilization read a Java 7 `rt.jar` instead of the JDK's own class
library, and the build compiles against it (so a use of a newer API fails the
build instead of confusing the JML tools). The class library is not in the repository (61 MB);
`lib/java7/` is git-ignored.

```bash
scripts/fetch-java7-classlib.sh                     # downloads Azul Zulu 7u352 and extracts its rt.jar
scripts/fetch-java7-classlib.sh --from <JDK 7 home> # or copies it from a JDK 7 you have
```

The download is the Zulu 7u352 Linux x64 archive (an OpenJDK build; the
classes in `rt.jar` are the same on every platform); the script checks the
archive's and the extracted file's SHA-256. To use a Java 7 `rt.jar` stored
elsewhere, set `STRYKER_CLASSLIB` for the scripts, or pass
`-Dstryker.classlib=<path>` to Ant and to the JVM of a run started some other
way (e.g. from an IDE). Without either, runs look for `lib/java7/rt.jar` under
the working directory, which must be the repository root anyway.

### JDK 7 (optional)

JDK 7 still works and needs no class library. Azul Zulu 7u352 is its last
free release:

```bash
# macOS (x64 build; on Apple Silicon it needs Rosetta:
#   softwareupdate --install-rosetta --agree-to-license)
curl -LO https://cdn.azul.com/zulu/bin/zulu7.56.0.11-ca-jdk7.0.352-macosx_x64.tar.gz
tar -xzf zulu7.56.0.11-ca-jdk7.0.352-macosx_x64.tar.gz
mkdir -p ~/Library/Java/JavaVirtualMachines
mv zulu7.56.0.11-ca-jdk7.0.352-macosx_x64/zulu-7.jdk ~/Library/Java/JavaVirtualMachines/

# Linux x86_64
curl -LO https://cdn.azul.com/zulu/bin/zulu7.56.0.11-ca-jdk7.0.352-linux_x64.tar.gz
sudo mkdir -p /usr/lib/jvm
sudo tar -xzf zulu7.56.0.11-ca-jdk7.0.352-linux_x64.tar.gz -C /usr/lib/jvm
```

If both are installed, the scripts use JDK 8; set `STRYKER_JAVA_HOME` to the
JDK 7 to use it instead.

### JDK 21 or 25 (optional)

TACO and Stryker also run on JDK 21 and 25, the two latest long-term-support
releases, with the same [Java 7 class library](#the-java-7-class-library) as
JDK 8. Eclipse Temurin 21.0.12.1 and 25.0.4.1 were tested. For 25, replace 21
with 25 below:

```bash
# macOS on Apple Silicon (on an Intel Mac, use x64 instead of aarch64)
curl -L -o temurin-21.tar.gz https://api.adoptium.net/v3/binary/latest/21/ga/mac/aarch64/jdk/hotspot/normal/eclipse
tar -xzf temurin-21.tar.gz
mkdir -p ~/Library/Java/JavaVirtualMachines
mv jdk-21.* ~/Library/Java/JavaVirtualMachines/temurin-21.jdk

# Linux x86_64
curl -L -o temurin-21.tar.gz https://api.adoptium.net/v3/binary/latest/21/ga/linux/x64/jdk/hotspot/normal/eclipse
sudo mkdir -p /usr/lib/jvm
sudo tar -xzf temurin-21.tar.gz -C /usr/lib/jvm
```

The scripts prefer JDK 8, and then 7, 21 and 25, so to use a newer JDK
alongside them, point `STRYKER_JAVA_HOME` at it (e.g.
`~/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home`).

Runs on JDK 21 and 25 need these JVM options:

```
--add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED
--enable-native-access=ALL-UNNAMED
```

The scripts and the Ant `unittest` target add them, and the shared IntelliJ
run configuration passes them (see [IntelliJ IDEA](#intellij-idea)); a run
started another way needs them too.

- `--add-opens`: TACO accesses fields of counterexample objects through
  reflection, those of JDK classes included (e.g. the `serialVersionUID` of an
  exception), and Java 17 and later only allow that in opened packages.
  Without the options, such a TACO call fails earlier than on JDK 8, with
  another error.
- `--enable-native-access`: DynAlloy extracts MiniSat into
  `<java.io.tmpdir>/alloy4tmp40-<user>/binary` and, on Java 7 and 8, adds that
  folder to the native library path while running, which newer Java does not
  allow. There TACO loads MiniSat from that folder itself
  (`ar.edu.taco.SatSolverLibraries`), so no `-Djava.library.path` is needed;
  this option keeps JDK 24 and later from warning about it.

**Results on JDK 21 and 25 are close to JDK 8's, but not identical, and less
reproducible.** The order of the declarations in TACO's Alloy models depends
on identity hash codes, and the order changes the counterexamples MiniSat
finds. On JDK 8 the first model of a run came out the same in almost every
run tried; on JDK 21 it often did not. On macOS arm64, with
one run each of 40 benchmarks from every family, 38 gave the same report on
JDK 8 and 21, and two found the same fix with one TACO call more or less. Run
three times each, those two and a third benchmark gave the same result every
time on JDK 8, and varied on JDK 21 (TACO calls 8, 10 and 9; 17, 17 and 16;
and once "No fix was found"). `-XX:hashCode=3` made JDK 21's first model the
same in every run tried, but in another order than JDK 8's: 36 of the 40 then
matched JDK 8, and two found fixes that JDK 8 does not, so it is not used.
Use JDK 8 (or 7) to compare with earlier results.

On JDK 25, 36 of the same 40 benchmarks matched JDK 8. The four that
differed are the ones that differed with `-XX:hashCode=3` on JDK 21: two
found fixes that JDK 8 does not, one reached the 5-minute limit where JDK 8
reports "No fix was found", and one took a TACO call more. Run three times
each, four benchmarks varied less than on JDK 21: three gave the same result
every time (one of them a fix that JDK 8 does not find), and one took 9, 9
and 8 TACO calls.

Other Java versions, such as 11 or 17, are refused: they were not tested.
JDK 27 does not work: it is not a long-term-support release, and the
`commons-lang` 2.4 that TACO uses fails as soon as it reads JDK 27's version
string.

### Windows: use WSL2

Native Windows is not supported: the bundled SAT solvers include no 64-bit
Windows library (and no MiniSat 2.2 at all for Windows), parts of Stryker
assume `/` as the path separator (with `\` they crash), and the scripts are
bash. Use **WSL2** on an x86_64 PC instead: it is a real Linux x86_64, so the
[Linux](#linux-x86_64) instructions apply unchanged.

- Install a distribution (e.g. `wsl --install -d Ubuntu`) and work inside it.
- Clone the repository inside the Linux file system (e.g. `~/comitaco`), not
  under `/mnt/c/...`: Stryker writes thousands of small files per run, and
  access to Windows drives from WSL is much slower.
- Windows on ARM is not supported (WSL there is ARM Linux).
- Alternatively, use the [Docker environment](#alternative-docker) through
  Docker Desktop.

### Alternative: Docker

The native setup above is the main path. If you'd rather not install a JDK,
there is an optional Docker environment (`docker/Dockerfile`): Linux x86_64
with Azul Zulu 7u352 and the tools the scripts need. Your working copy is
mounted into it, so you work on your own files exactly as with the native
setup, and builds made inside and outside the container are interchangeable.

```bash
scripts/docker.sh scripts/run-benchmark.sh --reset icse.singlylinkedlist.set1.StrykerSinglyLinkedListContains1Bug25DTest
scripts/docker.sh scripts/smoke-test.sh
scripts/docker.sh                 # a shell inside the environment
```

- The first call builds the image `stryker-jdk7` (about a minute, needs
  internet); `scripts/docker.sh --rebuild ...` rebuilds it.
- Commands run as your user, so files written into the repository are yours.
- Give Docker at least 6 GB of memory (Docker Desktop: *Settings >
  Resources*): runs use a 4 GB Java heap.
- **macOS**: Docker Desktop. On Apple Silicon the image runs under x86_64
  emulation; the smoke test took about 2¼ minutes instead of 2.
- **Linux**: Docker Engine; on x86_64 the image runs natively.
- **Windows**: Docker Desktop with the WSL2 backend on an x86_64 PC; run
  `scripts/docker.sh` from a WSL2 shell (untested).

Tested with Docker Desktop on macOS (Apple Silicon).

### How the scripts find the JDK

`scripts/common.sh` uses, in this order:

1. `$STRYKER_JAVA_HOME`, which must then be a usable JDK 7, 8, 21 or 25;
2. `$JAVA_HOME`, if it is a usable JDK 8 or 7 (a newer JDK there is only used
   if no JDK 8 or 7 is found, so JDK 8 stays the default);
3. otherwise the first JDK 8, failing that the first JDK 7, then 21, then 25,
   among `$JAVA_HOME`, `/Library/Java/JavaVirtualMachines/*` and
   `~/Library/Java/JavaVirtualMachines/*` (macOS), and `/usr/lib/jvm/*`,
   `/opt/*jdk*` and `/opt/*java*` (Linux).

"Usable" means `bin/java` and `bin/javac` exist, `java -version` says 1.7,
1.8, 21 or 25, and, on a Mac, an arm64 JDK is only accepted when
`lib/alloy4.jar` bundles MiniSat for arm64 (this repository's does). On JDK 8
and later the scripts also need the class library: `$STRYKER_CLASSLIB`, or
`lib/java7/rt.jar`.

## Building

```bash
scripts/build.sh            # same as: scripts/build.sh build
```

This compiles `src/`, `tests/` (the subject programs) and `unittest/` (the
drivers) into `bin/`, copies resources (e.g. the precompiled `.djals` modules)
next to the classes, and writes `bin/runtime.classpath`, which
`scripts/run-benchmark.sh` uses. A full build takes about 10 seconds. You
rarely need to call it directly: `run-benchmark.sh` rebuilds incrementally
before every run.

`scripts/build.sh` passes its arguments to Ant, so other targets work too:

| Target | What it does |
|---|---|
| `build` (default) | Compile everything into `bin/` |
| `clean` | Delete `bin/` |
| `jar` | Build `dist/taco.jar` (all jars merged in, `ar.edu.taco.TacoMain` as main class) |
| `unittest` | Run **every** driver under `unittest/`, including hundreds of Stryker benchmarks. This takes many hours; use `scripts/run-benchmark.sh` for single benchmarks |
| `junitreport` | HTML report from a previous `unittest` run |

Without the script: `JAVA_HOME=<jdk> ant/bin/ant -noclasspath build`
(`-noclasspath` keeps a `CLASSPATH` environment variable from leaking in). On
JDK 8 and later the build reads `lib/java7/rt.jar`; pass
`-Dstryker.classlib=<rt.jar>` to use another.

Notes for maintainers:

- The project is compiled with the Eclipse compiler (ECJ 3.8.1, through
  `lib/jdtCompilerAdapter.jar`), like the 2018 working version. The bundled
  Ant and ECJ also run on JDK 21 and 25, and compile the same class files as
  on JDK 8.
- The target stays Java 7 (`source`/`target` 1.7) on every JDK: ECJ 3.8.1
  compiles nothing newer, and Java 7 is what the original experiments used.
  On JDK 8 and later the build compiles against the Java 7 class library
  (`ar.edu.taco.Java7ClassLibrary` documents who reads it at run time). The
  JML tools, which read the compiled subjects in `bin/`, also accept them
  compiled for Java 8, as IntelliJ compiles them: the smoke-test benchmarks
  gave the same results that way on JDK 21. What they cannot use is the class
  library of Java 8 or later.
- The library list in `build.xml` (`lib.classpath`) and the IntelliJ library
  (`.idea/libraries/lib.xml`) must stay in sync, in the same order. `lib/stryker/jml4c.jar` is intentionally on neither: Stryker runs it
  as a separate process and loads it in its own class loader.
- `benchmarks/` is deliberately not compiled.

## IntelliJ IDEA

The repository is an IntelliJ project (`.idea/` and `comitaco.iml`): one
module with the source folders `src`, `tests` and `unittest`, the dependency
jars in one project library (`lib`, in the same order as `build.xml`), Java 8
language level and bytecode, and `bin/` as the output folder. Keep the output
in `bin/`: the scripts run from it, and Stryker resolves the subjects' compiled
classes there. The level is 8, not 7 like the scripts' build, because `javac`
cannot compile for Java 7 from JDK 20 on, and JDK 21 and 25 should work as
the SDK. (JDK 25's `javac` warns that compiling for Java 8 will be removed in
a future release.)

1. *File > Open*, and select the repository folder.
2. *File > Project Structure > Project*: set the SDK to your JDK 8, 21 or 25 (on
   Apple Silicon, preferably a native arm64 build) and keep the language level
   at 8. The project names the SDK `1.8`; if IntelliJ says it is missing, pick
   yours. A JDK 7 cannot be the SDK, since it cannot compile for Java 8; use
   the scripts for runs on JDK 7.
3. Fetch the [Java 7 class library](#the-java-7-class-library) once
   (`scripts/fetch-java7-classlib.sh`). Runs look for `lib/java7/rt.jar` under
   their working directory.
4. *Build > Build Project* compiles into `bin/` with `javac`, for Java 8. The
   scripts compile with the Eclipse compiler instead, for Java 7; the two can
   be mixed, since each only recompiles what changed. IntelliJ won't flag
   Java 8 language features, or uses of Java 8 methods: `scripts/build.sh`
   will.
5. Run a driver: the shared run configuration *Stryker: SinglyLinkedList
   contains, 1 bug*, or right-click any driver under `unittest/` > *Run*. For
   drivers you start yourself, set these in the run configuration (or once in
   the JUnit template: *Run > Edit Configurations > Edit configuration
   templates > JUnit*):
   - VM options `-Xmx4g -Xss8m`; add `-XX:-TieredCompilation` for an x86_64
     JDK under Rosetta;
   - environment variable `JDK_JAVA_OPTIONS` set to the
     [JDK 21 and 25 options](#jdk-21-or-25-optional), as in the shared run
     configuration. Only Java 9 and later read it, so runs on JDK 8 are
     unaffected (and newer JDKs print a `NOTE: Picked up JDK_JAVA_OPTIONS`
     line);
   - working directory: the repository root (`$PROJECT_DIR$`). TACO and
     Stryker resolve `config/`, `tests/`, `bin/` and `lib/` against it;
   - *Shorten command line*: none. Stryker hands the JVM's class path to JML4C
     and to the compiler of the counterexample tests, so it must be the real
     one.

What applies to script runs applies here too (see [RUNNING.md](RUNNING.md)):
the JUnit result does not tell you whether a fix was found (look at
`StrykerLastReports.txt` and the console), and a successful run leaves the fix
in the subject file under `tests/` (restore it with `git checkout -- tests`).

Tested with IntelliJ IDEA 2026.2.3 on macOS (Apple Silicon), with Oracle JDK
8u261 as the project SDK and the earlier Java 7 language level. The Java 8
level was checked outside the IDE, compiling as IntelliJ does
(`javac --release 8`) and running the smoke-test benchmarks on JDK 21, and
one of them through IntelliJ's JUnit runner on JDK 21 and 25.

Eclipse is no longer supported: its project files were removed in 2026, and
Eclipse 2024-09 (4.33) and later cannot compile Java 7 projects anyway.

## Troubleshooting

| Symptom | Cause and fix |
|---|---|
| `error: a JDK 8, 7, 21 or 25 is required but none was found` | Install a JDK 8 (see above), or set `STRYKER_JAVA_HOME` if the scripts don't find yours. |
| `STRYKER_JAVA_HOME=... is not a usable JDK 7, 8, 21 or 25` | It is another Java version, or a JRE (no `javac`). On a Mac, an arm64 JDK also needs a `lib/alloy4.jar` with MiniSat for arm64, which this repository's has. |
| `on JDK 8, TACO and Stryker need a Java 7 class library` (scripts), `On JDK 8 the build needs a Java 7 class library` (Ant), or `IllegalStateException: On Java 8, TACO and Stryker need a Java 7 class library` (a run); the same with 21 or 25 | Run `scripts/fetch-java7-classlib.sh`. A run started from an IDE looks for `lib/java7/rt.jar` under its working directory, which must be the repository root, or uses `-Dstryker.classlib=<rt.jar>`. |
| `Stryker requires JDK 7, 8, 21 or 25 (found Java 17 ...)` from Ant | `JAVA_HOME` points to another Java. Use `scripts/build.sh`, or set `JAVA_HOME` to a JDK 8, 7, 21 or 25. |
| `IllegalStateException: TACO and Stryker run on Java 7, 8, 21 or 25` | The run used another JVM, e.g. the JDK of an IDE run configuration. |
| `UnsupportedClassVersionError ... 52.0` when building | A system Ant 1.10+ is running on JDK 7. Use `scripts/build.sh` or `ant/bin/ant`. |
| IntelliJ: `Abnormal build process termination` ... `bin/java: No such file or directory` | IntelliJ's own settings list a JDK that is no longer installed, and it tried to run its build process with it. In *File > Project Structure > Platform Settings > SDKs*, remove the entries whose JDK is gone, and make sure the `1.8` SDK points to an installed JDK 8. |
| Runs on an x86_64 JDK 8 on Apple Silicon take up to twice as long as on JDK 7 | Tiered compilation under Rosetta. The scripts turn it off there (`-XX:-TieredCompilation`); add that option if you set `STRYKER_JVM_OPTS` or run from an IDE, or use the native arm64 JDK 8, which is faster anyway. |
| `UnsatisfiedLinkError` mentioning `minisat` | No MiniSat library for the JVM's architecture: aarch64 Linux, or an arm64 macOS JDK with a `lib/alloy4.jar` older than 2026 (x86_64-only MiniSat). An outdated copy in `$TMPDIR/alloy4tmp40-<user>/binary`, extracted by an older jar, has the same effect; TACO deletes those itself, but if it reports that it could not, delete that folder. |
| `warning: tests/ has local changes` | A previous run wrote its fix into `tests/`. Use `--reset`. |
| The run hangs, or reports "No fix was found" for a benchmark that should be fixable | Check `stryker-console.log` and `stryker.log` for exceptions, and use `--timeout`. Past causes, all fixed: running on Java 8 (before it was supported); a repository path containing a space, `generated` or `output`; a temp directory path containing a directory whose name starts with `a`; a method under repair with a parameter named `c` (every IntroClass benchmark). A "no fix" can also come from unsound pruning, and then a rerun may find the fix. See [KNOWN-LIMITATIONS.md](KNOWN-LIMITATIONS.md). |
