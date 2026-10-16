# Shared helpers for the scripts in this directory. Source it, don't run it.

STRYKER_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

die() {
    echo "error: $*" >&2
    exit 2
}

# Prints 7 or 8 if $1 is the home of a JDK 7 or 8 that can run Stryker on this machine, or
# fails. On Apple Silicon the JDK must be an x86_64 build (it runs under Rosetta): the bundled
# SAT solver libraries only exist for Intel.
jdk_version() {
    local home="$1" version
    [ -x "$home/bin/java" ] && [ -x "$home/bin/javac" ] || return 1
    version="$("$home/bin/java" -version 2>&1 | sed -n 's/.*version "1\.\([78]\)[._].*/\1/p' | head -1)"
    [ -n "$version" ] || return 1
    if [ "$(uname -s)" = "Darwin" ] && [ "$(uname -m)" = "arm64" ] \
        && ! file "$home/bin/java" | grep -q x86_64; then
        return 1
    fi
    echo "$version"
}

# Prints the home of the JDK to use, or fails: $STRYKER_JAVA_HOME if set (it must be a usable
# JDK 7 or 8), else $JAVA_HOME if it is one, else the first JDK 8, then the first JDK 7, found
# in the usual places (macOS: /Library/Java/JavaVirtualMachines and its ~/Library counterpart;
# Linux: /usr/lib/jvm, /opt).
find_jdk() {
    if [ -n "${STRYKER_JAVA_HOME:-}" ]; then
        jdk_version "$STRYKER_JAVA_HOME" > /dev/null || return 1
        echo "$STRYKER_JAVA_HOME"
        return 0
    fi
    if [ -n "${JAVA_HOME:-}" ] && jdk_version "$JAVA_HOME" > /dev/null; then
        echo "$JAVA_HOME"
        return 0
    fi
    local candidates=() dir home wanted
    for dir in /Library/Java/JavaVirtualMachines/*/Contents/Home \
        "$HOME"/Library/Java/JavaVirtualMachines/*/Contents/Home \
        /usr/lib/jvm/* /opt/*jdk* /opt/*java*; do
        [ -d "$dir" ] && candidates+=("$dir")
    done
    for wanted in 8 7; do
        for home in ${candidates[@]+"${candidates[@]}"}; do
            if [ "$(jdk_version "$home")" = "$wanted" ]; then
                echo "$home"
                return 0
            fi
        done
    done
    return 1
}

# Sets JAVA_HOME to a JDK 7 or 8 (see find_jdk) and STRYKER_JAVA_VERSION to 7 or 8, or exits
# with instructions. On JDK 8 it also sets STRYKER_CLASSLIB to the Java 7 class library
# (rt.jar) that the JML tools need: $STRYKER_CLASSLIB if set, else lib/java7/rt.jar, which
# scripts/fetch-java7-classlib.sh creates.
require_jdk() {
    if ! JAVA_HOME="$(find_jdk)"; then
        if [ -n "${STRYKER_JAVA_HOME:-}" ]; then
            die "STRYKER_JAVA_HOME=$STRYKER_JAVA_HOME is not a usable JDK 7 or 8
(on Apple Silicon it must be an x86_64 build, which runs under Rosetta). See docs/BUILDING.md."
        fi
        die "a JDK 8 or 7 is required but none was found.
Install one (e.g. Azul Zulu 8; on Apple Silicon, the x86_64 build) and, if the
scripts don't find it, point STRYKER_JAVA_HOME at it. See docs/BUILDING.md."
    fi
    export JAVA_HOME
    STRYKER_JAVA_VERSION="$(jdk_version "$JAVA_HOME")"
    export STRYKER_JAVA_VERSION
    if [ "$STRYKER_JAVA_VERSION" = 8 ]; then
        STRYKER_CLASSLIB="${STRYKER_CLASSLIB:-$STRYKER_ROOT/lib/java7/rt.jar}"
        [ -f "$STRYKER_CLASSLIB" ] || die "on JDK 8, TACO and Stryker need a Java 7 class library, and
$STRYKER_CLASSLIB does not exist. Run scripts/fetch-java7-classlib.sh once,
or set STRYKER_CLASSLIB to a Java 7 rt.jar. See docs/BUILDING.md."
        export STRYKER_CLASSLIB
    fi
}

# JVM options for a Stryker run when STRYKER_JVM_OPTS is not set. Java 8 has no permanent
# generation, so -XX:MaxPermSize is only passed to Java 7. On Java 8, tiered compilation is
# turned off, as it is by default on Java 7: with it, runs took up to twice as long on
# Apple Silicon (Rosetta), with the same results.
default_jvm_opts() {
    if [ "${STRYKER_JAVA_VERSION:-}" = 7 ]; then
        echo "-Xmx4g -Xss8m -XX:MaxPermSize=512m"
    else
        echo "-Xmx4g -Xss8m -XX:-TieredCompilation"
    fi
}

# Runs the bundled Ant 1.9 (Ant 1.10+ cannot run on Java 7).
run_ant() {
    local opts=()
    [ -n "${STRYKER_CLASSLIB:-}" ] && opts=("-Dstryker.classlib=$STRYKER_CLASSLIB")
    "$STRYKER_ROOT/ant/bin/ant" -noclasspath -f "$STRYKER_ROOT/build.xml" ${opts[@]+"${opts[@]}"} "$@"
}
