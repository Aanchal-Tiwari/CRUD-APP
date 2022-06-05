#!/usr/bin/env bash

set -eu
cd "$(dirname "$0")/.."

if [ -z "${1:-}" ]; then
    echo "Usage: $0 micros-environment"
    exit 1
fi

set -x

# Ensure login access to Micros
if atlas micros --version >/dev/null 2>&1; then
  atlas micros login
else
  echo "Error: micros plugin for atlas not installed. Exiting"
  exit 1
fi

# Ensure temporary write permissions to docker registry
if atlas packages --version >/dev/null 2>&1; then
  atlas packages permission grant
else
  echo "Error: packages plugin for atlas not installed. Exiting"
  exit 1
fi

mvn deploy

export MICROS_DEPLOY_COMMAND="atlas"
export MICROS_DEPLOY_SERVICE="democrud-bookapplication"
export MICROS_DEPLOY_DOCKER_IMAGE_NAME="docker.atl-paas.net/atlassian/democrud-bookapplication"
export MICROS_DEPLOY_ENVIRONMENT="$1"

./bin/micros-deploy.sh
