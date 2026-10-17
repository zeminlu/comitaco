# Optional environment for running Stryker without installing a JDK: Linux with
# Azul Zulu and the tools the scripts use, built for the host's architecture.
#
# - amd64 (x86_64): Zulu 7, the JDK of the original experiments.
# - arm64: Zulu 8, since there is no JDK 7 for arm64, plus the Java 7 class
#   library that the JML tools read on JDK 8 (see docs/BUILDING.md), taken
#   from the same Zulu 7 archive as scripts/fetch-java7-classlib.sh takes it.
#
# lib/alloy4.jar bundles MiniSat 2.2 for both (amd64-linux and aarch64-linux).
#
# The repository is not copied into the image. scripts/docker.sh mounts your
# working copy at /work and runs the given command there, e.g.
#
#     scripts/docker.sh scripts/run-benchmark.sh --reset <DriverClass>
FROM debian:bookworm-slim

ARG TARGETARCH
ARG ZULU7=zulu7.56.0.11-ca-jdk7.0.352-linux_x64
ARG ZULU7_SHA256=8a7387c1ed151474301b6553c6046f865dc6c1e1890bcf106acc2780c55727c8
ARG RT_JAR_SHA256=b7e1f8d3248ab22e4f4c313211938b7abb3d0736a36de230bda4f9248e887f6e
ARG ZULU8_ARM64=zulu8.96.0.205-ca-jdk8.0.504-linux_aarch64
ARG ZULU8_ARM64_SHA256=c79f5fd740702a1336a9e6da2262be03e1a6e4255a94d198b66c49896be6b478

RUN apt-get update \
 && apt-get install -y --no-install-recommends bash ca-certificates curl git procps unzip \
 && rm -rf /var/lib/apt/lists/* \
 && mkdir -p /usr/lib/jvm /opt/java7 \
 && curl -fsSL -o /tmp/zulu7.tar.gz "https://cdn.azul.com/zulu/bin/${ZULU7}.tar.gz" \
 && echo "${ZULU7_SHA256}  /tmp/zulu7.tar.gz" | sha256sum -c - \
 && tar -xzf /tmp/zulu7.tar.gz -C /tmp "${ZULU7}/jre/lib/rt.jar" \
 && mv "/tmp/${ZULU7}/jre/lib/rt.jar" /opt/java7/rt.jar \
 && echo "${RT_JAR_SHA256}  /opt/java7/rt.jar" | sha256sum -c - \
 && case "${TARGETARCH}" in \
      amd64) tar -xzf /tmp/zulu7.tar.gz -C /usr/lib/jvm \
             && ln -s "/usr/lib/jvm/${ZULU7}" /usr/lib/jvm/stryker-jdk ;; \
      arm64) curl -fsSL -o /tmp/zulu8.tar.gz "https://cdn.azul.com/zulu/bin/${ZULU8_ARM64}.tar.gz" \
             && echo "${ZULU8_ARM64_SHA256}  /tmp/zulu8.tar.gz" | sha256sum -c - \
             && tar -xzf /tmp/zulu8.tar.gz -C /usr/lib/jvm \
             && rm /tmp/zulu8.tar.gz \
             && ln -s "/usr/lib/jvm/${ZULU8_ARM64}" /usr/lib/jvm/stryker-jdk ;; \
      *) echo "unsupported architecture: ${TARGETARCH}" >&2; exit 1 ;; \
    esac \
 && rm -rf /tmp/zulu7.tar.gz "/tmp/${ZULU7}" \
 && git config --system --add safe.directory '*'

ENV STRYKER_JAVA_HOME=/usr/lib/jvm/stryker-jdk
# Read on JDK 8 (the arm64 image); a JDK 7 uses its own class library.
ENV STRYKER_CLASSLIB=/opt/java7/rt.jar
WORKDIR /work
CMD ["bash"]
