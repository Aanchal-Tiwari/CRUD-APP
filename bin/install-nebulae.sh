#!/usr/bin/env bash

set -xeu

./bin/install-atlas.sh

# install docker-compose if missing
if ! [[ $(docker-compose version 2>/dev/null) ]] && ! [ -x /usr/local/bin/docker-compose ]; then
  curl -L -s "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose \
  && chmod +x /usr/local/bin/docker-compose
fi

# install nebulae
atlas plugin install --name nebulae

