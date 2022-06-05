#!/usr/bin/env bash

set -xeu
cd "$(dirname "$0")/.."

source ./bin/build-include.sh
export MICROS_DEPLOY_SERVICE="democrud-bookapplication"


export MICROS_DEPLOY_COMMAND="atlas"
export MICROS_DEPLOY_DOCKER_IMAGE_NAME="docker.atl-paas.net/atlassian/democrud-bookapplication"

export MICROS_DEPLOY_ENVIRONMENT="$1"
./bin/micros-deploy.sh
