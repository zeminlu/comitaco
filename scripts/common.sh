# Shared helpers for the scripts in this directory. Source it, don't run it.

STRYKER_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

die() {
    echo "error: $*" >&2
    exit 2
}

# Prints the home of a JDK 7 installation, or fails.
# Looks at $STRYKER_JAVA_HOME, $JAVA_HOME, /usr/libexec/java_home -v 1.7 (macOS)
# and the usual Linux locations, in that order.
find_jdk7() {
    local candidates=()
    [ -n "${STRYKER_JAVA_HOME:-}" ] && candidates+=("$STRYKER_JAVA_HOME")
    [ -n "${JAVA_HOME:-}" ] && candidates+=("$JAVA_HOME")
    if [ -x /usr/libexec/java_home ]; then
        local mac_home
        mac_home="$(/usr/libexec/java_home -v 1.7 2>/dev/null)" && candidates+=("$mac_home")
    fi
    local dir
    for dir in /usr/lib/jvm/*7* /opt/*jdk*7*; do
        [ -d "$dir" ] && candidates+=("$dir")
    done

    local home
    for home in ${candidates[@]+"${candidates[@]}"}; do
        if [ -x "$home/bin/java" ] && [ -x "$home/bin/javac" ] \
            && "$home/bin/java" -version 2>&1 | grep -q 'version "1\.7'; then
            echo "$home"
            return 0
        fi
    done
    return 1
}

# Sets JAVA_HOME to a JDK 7 or exits with instructions.
require_jdk7() {
    JAVA_HOME="$(find_jdk7)" || die "a JDK 7 is required but none was found.
Install one (e.g. Azul Zulu 7, x86_64 build on Apple Silicon) and point
STRYKER_JAVA_HOME at it. See docs/BUILDING.md."
    export JAVA_HOME
    if [ "$(uname -s)" = "Darwin" ] && [ "$(uname -m)" = "arm64" ] \
        && ! file "$JAVA_HOME/bin/java" | grep -q x86_64; then
        die "$JAVA_HOME is not an x86_64 JDK. The bundled SAT solver libraries
only exist for Intel, so use an x86_64 JDK 7 (it runs under Rosetta)."
    fi
}

# Runs the bundled Ant 1.9 (Ant 1.10+ cannot run on Java 7).
run_ant() {
    "$STRYKER_ROOT/ant/bin/ant" -noclasspath -f "$STRYKER_ROOT/build.xml" "$@"
}
