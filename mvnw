#!/usr/bin/env bash
set -euo pipefail
VERSION=3.10.1
ROOT="${HOME}/.cache/testforge"
HOME_MVN="${ROOT}/apache-maven-${VERSION}"
if [[ ! -x "${HOME_MVN}/bin/mvn" ]]; then
  mkdir -p "${ROOT}"
  ARCHIVE="${ROOT}/apache-maven-${VERSION}-bin.tar.gz"
  echo "Downloading Apache Maven ${VERSION} into ${ROOT}..."
  curl -fL "https://dlcdn.apache.org/maven/maven-3/${VERSION}/binaries/apache-maven-${VERSION}-bin.tar.gz" -o "${ARCHIVE}"
  tar -xzf "${ARCHIVE}" -C "${ROOT}"
  rm -f "${ARCHIVE}"
fi
exec "${HOME_MVN}/bin/mvn" "$@"
