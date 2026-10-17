# Shared helpers for the scripts in this directory. Source it, don't run it.

STRYKER_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

die() {
    echo "error: $*" >&2
    exit 2
}

# Prints the architecture the JVM in the JDK home $1 runs as (its os.arch: x86_64, aarch64...).
jvm_arch() {
    "$1/bin/java" -XshowSettings:properties -version 2>&1 | sed -n 's/^ *os\.arch = //p'
}

# Succeeds if the MiniSat 2.2 library that lib/alloy4.jar bundles for macOS has an arm64 slice,
# as the one in this repository has since 2026 (see docs/DEPENDENCIES.md). Before, it was
# x86_64 only.
bundled_minisat_has_arm64() {
    unzip -p "$STRYKER_ROOT/lib/alloy4.jar" x86-mac/libminisat220.jnilib 2>/dev/null \
        | file - | grep -q arm64
}

# Succeeds if lib/alloy4.jar bundles MiniSat 2.2 for arm64 Linux (aarch64-linux), as the one in
# this repository has since 2026.
bundled_minisat_has_aarch64_linux() {
    unzip -l "$STRYKER_ROOT/lib/alloy4.jar" aarch64-linux/libminisat220.so > /dev/null 2>&1
}

# Prints 7, 8, 21 or 25 if $1 is the home of a JDK 7, 8, 21 or 25 that can run Stryker on this
# machine, or fails. An arm64 JDK can only load MiniSat, the SAT solver TACO uses, if
# lib/alloy4.jar bundles it for arm64 on that system (macOS or Linux); otherwise, on a Mac, the
# JDK must be an x86_64 build, which runs under Rosetta on Apple Silicon.
jdk_version() {
    local home="$1" version
    [ -x "$home/bin/java" ] && [ -x "$home/bin/javac" ] || return 1
    version="$("$home/bin/java" -version 2>&1 \
        | sed -n -e 's/.*version "1\.\([78]\)[._].*/\1/p' -e 's/.*version "\(2[15]\)[."].*/\1/p' | head -1)"
    [ -n "$version" ] || return 1
    if [ "$(jvm_arch "$home")" = "aarch64" ]; then
        if [ "$(uname -s)" = "Darwin" ]; then
            bundled_minisat_has_arm64 || return 1
        else
            bundled_minisat_has_aarch64_linux || return 1
        fi
    fi
    echo "$version"
}

# Prints the home of the JDK to use, or fails: $STRYKER_JAVA_HOME if set (it must be a usable
# JDK 7, 8, 21 or 25), else $JAVA_HOME if it is a usable JDK 8 or 7, else the first JDK 8, then
# the first JDK 7, 21 and 25, among $JAVA_HOME and the usual places (macOS:
# /Library/Java/JavaVirtualMachines and its ~/Library counterpart; Linux: /usr/lib/jvm, /opt).
# So JDK 8 stays the default even when $JAVA_HOME is a newer JDK, as it often is nowadays.
find_jdk() {
    if [ -n "${STRYKER_JAVA_HOME:-}" ]; then
        jdk_version "$STRYKER_JAVA_HOME" > /dev/null || return 1
        echo "$STRYKER_JAVA_HOME"
        return 0
    fi
    local candidates=() dir home wanted version
    if [ -n "${JAVA_HOME:-}" ]; then
        version="$(jdk_version "$JAVA_HOME" || true)"
        if [ "$version" = 8 ] || [ "$version" = 7 ]; then
            echo "$JAVA_HOME"
            return 0
        fi
        candidates+=("$JAVA_HOME")
    fi
    for dir in /Library/Java/JavaVirtualMachines/*/Contents/Home \
        "$HOME"/Library/Java/JavaVirtualMachines/*/Contents/Home \
        /usr/lib/jvm/* /opt/*jdk* /opt/*java*; do
        [ -d "$dir" ] && candidates+=("$dir")
    done
    for wanted in 8 7 21 25; do
        for home in ${candidates[@]+"${candidates[@]}"}; do
            if [ "$(jdk_version "$home")" = "$wanted" ]; then
                echo "$home"
                return 0
            fi
        done
    done
    return 1
}

# Sets JAVA_HOME to a JDK 7, 8, 21 or 25 (see find_jdk) and STRYKER_JAVA_VERSION to its version,
# or exits with instructions. On JDK 8 and later it also sets STRYKER_CLASSLIB to the Java 7 class
# library (rt.jar) that the JML tools need: $STRYKER_CLASSLIB if set, else lib/java7/rt.jar,
# which scripts/fetch-java7-classlib.sh creates.
require_jdk() {
    if ! JAVA_HOME="$(find_jdk)"; then
        if [ -n "${STRYKER_JAVA_HOME:-}" ]; then
            die "STRYKER_JAVA_HOME=$STRYKER_JAVA_HOME is not a usable JDK 7, 8, 21 or 25. See docs/BUILDING.md."
        fi
        die "a JDK 8, 7, 21 or 25 is required but none was found.
Install one (e.g. Azul Zulu 8) and, if the scripts don't find it, point
STRYKER_JAVA_HOME at it. See docs/BUILDING.md."
    fi
    export JAVA_HOME
    STRYKER_JAVA_VERSION="$(jdk_version "$JAVA_HOME")"
    export STRYKER_JAVA_VERSION
    if [ "$STRYKER_JAVA_VERSION" != 7 ]; then
        STRYKER_CLASSLIB="${STRYKER_CLASSLIB:-$STRYKER_ROOT/lib/java7/rt.jar}"
        [ -f "$STRYKER_CLASSLIB" ] || die "on JDK $STRYKER_JAVA_VERSION, TACO and Stryker need a Java 7 class library, and
$STRYKER_CLASSLIB does not exist. Run scripts/fetch-java7-classlib.sh once,
or set STRYKER_CLASSLIB to a Java 7 rt.jar. See docs/BUILDING.md."
        export STRYKER_CLASSLIB
    fi
}

# Succeeds if the JVM of $JAVA_HOME is an x86_64 one running under Rosetta on an Apple Silicon
# Mac (hw.optional.arm64 is 1 there even when the shell itself runs under Rosetta).
under_rosetta() {
    [ "$(uname -s)" = "Darwin" ] && [ "$(sysctl -n hw.optional.arm64 2>/dev/null)" = 1 ] \
        && [ "$(jvm_arch "$JAVA_HOME")" = "x86_64" ]
}

# JVM options for a Stryker run when STRYKER_JVM_OPTS is not set. Java 8 has no permanent
# generation, so -XX:MaxPermSize is only passed to Java 7. An x86_64 JDK 8 under Rosetta also
# gets -XX:-TieredCompilation, Java 7's default: with tiered compilation, runs there took up to
# twice as long, with the same results. A native JVM is faster with it, so it keeps it.
default_jvm_opts() {
    if [ "${STRYKER_JAVA_VERSION:-}" = 7 ]; then
        echo "-Xmx4g -Xss8m -XX:MaxPermSize=512m"
    elif under_rosetta; then
        echo "-Xmx4g -Xss8m -XX:-TieredCompilation"
    else
        echo "-Xmx4g -Xss8m"
    fi
}

# JVM options a Stryker run needs on JDK 21 and 25, whatever STRYKER_JVM_OPTS says (see
# docs/BUILDING.md, "JDK 21 or 25"). TACO accesses fields of counterexample objects through
# reflection, those of JDK classes included (the serialVersionUID of an exception, for example),
# which Java 17 and later only allow in opened packages: without the --add-opens, such a TACO call
# fails earlier than on JDK 8, with another error. --enable-native-access lets TACO load MiniSat
# (SatSolverLibraries) without the warning JDK 24 and later print.
java21plus_opts() {
    if [ "${STRYKER_JAVA_VERSION:-}" = 21 ] || [ "${STRYKER_JAVA_VERSION:-}" = 25 ]; then
        echo "--add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --enable-native-access=ALL-UNNAMED"
    fi
}

# Runs the bundled Ant 1.9 (Ant 1.10+ cannot run on Java 7).
run_ant() {
    local opts=()
    [ -n "${STRYKER_CLASSLIB:-}" ] && opts=("-Dstryker.classlib=$STRYKER_CLASSLIB")
    "$STRYKER_ROOT/ant/bin/ant" -noclasspath -f "$STRYKER_ROOT/build.xml" ${opts[@]+"${opts[@]}"} "$@"
}
