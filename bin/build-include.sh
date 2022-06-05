#!/bin/bash

set -xeu

docker version

if [ -n "${bamboo_capability_system_jdk_JDK_11:-}" ]; then
  export JAVA_HOME="${bamboo_capability_system_jdk_JDK_11}"
  export PATH="$JAVA_HOME/bin:$PATH"
fi

./bin/install-atlas.sh

atlas plugin install -n spin
atlas plugin install -n micros
atlas plugin install -n poco

./bin/install-nebulae.sh
