#!/usr/bin/env bash

set -xeu
cd "$(dirname "$0")/.."

function micros_service_deploy {
    "$MICROS_DEPLOY_COMMAND" \
         micros service deploy \
        -s "$MICROS_DEPLOY_SERVICE" \
        -u cutover \
        --env "$MICROS_DEPLOY_ENVIRONMENT" \
                --file "target/classes/$MICROS_DEPLOY_SERVICE.sd.yml" \
        ${EXTRA_MICROS_DEPLOYMENT_ARGS:-}
}

echo "Using $MICROS_DEPLOY_COMMAND to deploy $MICROS_DEPLOY_SERVICE into $MICROS_DEPLOY_ENVIRONMENT"
micros_service_deploy
echo "Deployment complete!"
