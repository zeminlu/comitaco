# check=skip=FromPlatformFlagConstDisallowed
# Optional environment for running Stryker without installing a JDK 7:
# Linux x86_64 with Azul Zulu 7 and the tools the scripts use.
#
# The repository is not copied into the image. scripts/docker.sh mounts your
# working copy at /work and runs the given command there, e.g.
#
#     scripts/docker.sh scripts/run-benchmark.sh --reset <DriverClass>
#
# Always linux/amd64: the bundled SAT solver libraries exist only for x86_64.
FROM --platform=linux/amd64 debian:bookworm-slim

ARG ZULU=zulu7.56.0.11-ca-jdk7.0.352-linux_x64
ARG ZULU_SHA256=8a7387c1ed151474301b6553c6046f865dc6c1e1890bcf106acc2780c55727c8

RUN apt-get update \
 && apt-get install -y --no-install-recommends bash ca-certificates curl git procps \
 && rm -rf /var/lib/apt/lists/* \
 && curl -fsSL -o /tmp/zulu7.tar.gz "https://cdn.azul.com/zulu/bin/${ZULU}.tar.gz" \
 && echo "${ZULU_SHA256}  /tmp/zulu7.tar.gz" | sha256sum -c - \
 && mkdir -p /usr/lib/jvm \
 && tar -xzf /tmp/zulu7.tar.gz -C /usr/lib/jvm \
 && rm /tmp/zulu7.tar.gz \
 && git config --system --add safe.directory '*'

ENV STRYKER_JAVA_HOME=/usr/lib/jvm/${ZULU}
WORKDIR /work
CMD ["bash"]
