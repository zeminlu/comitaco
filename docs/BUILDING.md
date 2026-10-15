# Building

## Requirements

| Requirement | Why |
|---|---|
| **JDK 7** (exactly; not 8 or later) | The JML parser (`lib/jml-release.jar`) and the runtime assertion checker JML4C (`lib/stryker/jml4c.jar`) cannot read Java 8+ class files. On Java 8 TACO fails with obscure errors or Stryker rejects every mutant and never finishes; Java 9+ additionally breaks class-loader hacks in the code. `TacoMain.run` and the Ant build refuse to run on anything but Java 7. |
| **x86_64** JDK and OS | The SAT solver (MiniSat 2.2, used through JNI) is bundled in `lib/alloy4.jar` only for Intel (`x86-mac`, `amd64-linux`, `x86-linux`). On Apple Silicon use an x86_64 JDK, which runs under Rosetta. ARM Linux (aarch64) is not supported. |
| macOS or Linux | Windows only through WSL2 (see [Windows](#windows-use-wsl2)). Without a local JDK 7, the optional [Docker environment](#alternative-docker) covers all three. |
| `bash`, `git` | Used by the scripts (`git` restores the subject files). |

Nothing else is needed: the dependency jars and Apache Ant 1.9.13 are in the
repository. Do not use a system Ant: Ant 1.10+ requires Java 8 and fails on
JDK 7 with `UnsupportedClassVersionError ... 52.0`.

Tested with Oracle JDK 7u71 and Azul Zulu 7u352 on macOS (Apple Silicon,
Rosetta), and with Azul Zulu 7u352 on Linux x86_64 (Debian 12).

## Installing a JDK 7

Azul Zulu 7 is free and still downloadable. 7u352 is its last release. You
don't need to make it your default Java: the scripts find it on their own (see
[How the scripts find the JDK](#how-the-scripts-find-the-jdk)).

### macOS

On Apple Silicon, install Rosetta first if you haven't:

```bash
softwareupdate --install-rosetta --agree-to-license
```

Then download the **x64** build and either put it where macOS registers JDKs
(no `sudo` needed for your user's folder):

```bash
curl -LO https://cdn.azul.com/zulu/bin/zulu7.56.0.11-ca-jdk7.0.352-macosx_x64.tar.gz
tar -xzf zulu7.56.0.11-ca-jdk7.0.352-macosx_x64.tar.gz
mkdir -p ~/Library/Java/JavaVirtualMachines
mv zulu7.56.0.11-ca-jdk7.0.352-macosx_x64/zulu-7.jdk ~/Library/Java/JavaVirtualMachines/
/usr/libexec/java_home -v 1.7      # prints .../zulu-7.jdk/Contents/Home
```

or keep it anywhere and point the scripts at it:

```bash
export STRYKER_JAVA_HOME=$HOME/jdks/zulu7.56.0.11-ca-jdk7.0.352-macosx_x64/zulu-7.jdk/Contents/Home
```

If you downloaded the archive with a browser, macOS may block the binaries;
clear the quarantine flag with `xattr -dr com.apple.quarantine <jdk dir>`.
An Oracle JDK 7 (e.g. 7u71, x64) works as well.

### Linux (x86_64)

```bash
curl -LO https://cdn.azul.com/zulu/bin/zulu7.56.0.11-ca-jdk7.0.352-linux_x64.tar.gz
sudo mkdir -p /usr/lib/jvm
sudo tar -xzf zulu7.56.0.11-ca-jdk7.0.352-linux_x64.tar.gz -C /usr/lib/jvm
```

The scripts look in `/usr/lib/jvm` automatically. To install it somewhere else
(no `sudo` needed), extract it anywhere and set `STRYKER_JAVA_HOME` to the
extracted directory. Don't register it with `update-alternatives` as the
system Java unless you want to.

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

The native setup above is the main path. If you'd rather not install a JDK 7,
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

`scripts/common.sh` takes the first of these that is a JDK 7 (it checks
`java -version`):

1. `$STRYKER_JAVA_HOME`
2. `$JAVA_HOME`
3. `/usr/libexec/java_home -v 1.7` (macOS)
4. `/usr/lib/jvm/*7*` and `/opt/*jdk*7*` (Linux)

On Apple Silicon it also rejects a JDK that is not x86_64.

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

Without the script: `JAVA_HOME=<jdk7> ant/bin/ant -noclasspath build`
(`-noclasspath` keeps a `CLASSPATH` environment variable from leaking in).

Notes for maintainers:

- The project is compiled with the Eclipse compiler (ECJ 3.8.1, through
  `lib/jdtCompilerAdapter.jar`), like the 2018 working version and like Eclipse.
- The library list in `build.xml` (`lib.classpath`) and `.classpath` must stay
  in sync. `lib/stryker/jml4c.jar` is intentionally on neither: Stryker runs it
  as a separate process and loads it in its own class loader.
- `benchmarks/` is deliberately not compiled.

## Eclipse

The repository is an Eclipse project named `taco` with source folders `src`,
`tests` and `unittest`, all libraries on `.classpath`, and Java 1.7 compliance
in `.settings/`.

1. **Eclipse version.** Eclipse 2024-09 (4.33) and later cannot compile Java
   1.7 projects ([JDT dropped compliance levels below 1.8](https://www.eclipse.org/lists/jdt-dev/msg02389.html)).
   Use Eclipse 2024-06 (4.32) or older. Launching JUnit on a JDK 7 from recent
   Eclipse versions is untested; the project was developed with Eclipse
   releases from 2014-2018. If Eclipse gives you trouble, use the scripts:
   they are the tested path.
2. *Preferences > Java > Installed JREs > Add...*: add the JDK 7 (x86_64).
3. *File > Import > Existing Projects into Workspace*, select the repository.
4. *Project > Properties > Java Build Path > Libraries*: make sure the JRE
   System Library is the JDK 7.
5. Right-click a driver (e.g.
   `unittest/icse/singlylinkedlist/set1/StrykerSinglyLinkedListContains1Bug25DTest.java`)
   > *Run As > JUnit Test*. In the run configuration:
   - *JRE* tab: the JDK 7;
   - *Arguments* tab: VM arguments `-Xmx4g -Xss8m -XX:MaxPermSize=512m`, and the
     working directory must be the project root (the default). TACO and Stryker
     resolve `config/`, `tests/`, `bin/` and `lib/stryker/jml4c.jar` against it.

What applies to script runs applies here too (see [RUNNING.md](RUNNING.md)):
the JUnit result does not tell you whether a fix was found (look at
`StrykerLastReports.txt` and the console), and a successful run leaves the fix
in the subject file under `tests/` (restore it with
`git checkout -- tests`).

## Troubleshooting

| Symptom | Cause and fix |
|---|---|
| `error: a JDK 7 is required but none was found` | Install a JDK 7 or set `STRYKER_JAVA_HOME` (see above). |
| `... is not an x86_64 JDK` | Apple Silicon with an arm64 JDK. Use the x64 build of the JDK 7. |
| `Stryker requires JDK 7 (found Java 1.8 ...)` from Ant | `JAVA_HOME` points to another Java. Use `scripts/build.sh`, or set `JAVA_HOME` to the JDK 7. |
| `IllegalStateException: TACO and Stryker require Java 7` | The run used another JVM, e.g. the JRE of an Eclipse launch configuration. |
| `UnsupportedClassVersionError ... 52.0` when building | A system Ant 1.10+ is being used. Use `scripts/build.sh` or `ant/bin/ant`. |
| `UnsatisfiedLinkError` mentioning `minisat` | The JVM is not x86_64 (arm64 macOS JDK, or aarch64 Linux). |
| `warning: tests/ has local changes` | A previous run wrote its fix into `tests/`. Use `--reset`. |
| The run hangs, or reports "No fix was found" for a benchmark that should be fixable | Check `stryker-console.log` and `stryker.log` for exceptions, and use `--timeout`. Past causes, all fixed: running on Java 8; a repository path containing a space, `generated` or `output`; a temp directory path containing a directory whose name starts with `a`; a method under repair with a parameter named `c` (every IntroClass benchmark). A "no fix" can also come from unsound pruning, and then a rerun may find the fix. See [KNOWN-LIMITATIONS.md](KNOWN-LIMITATIONS.md). |
