#!/usr/bin/env bash
# Builds the project into bin/ with JDK 7, 8 or 21 and the bundled Ant.
# Usage: scripts/build.sh [ant targets...]   (default target: build)
set -eo pipefail
. "$(dirname "$0")/common.sh"

require_jdk
echo "Using JDK $STRYKER_JAVA_VERSION at $JAVA_HOME"
cd "$STRYKER_ROOT"
run_ant "$@"
