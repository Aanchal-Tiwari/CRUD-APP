#!/usr/bin/env bash

# Check if Atlas CLI installed - see http://go/atlas-cli for more info
if ! command -v atlas &> /dev/null
then
    echo "Atlas CLI not found - installing..."
    if [[ "$OSTYPE" == "darwin"* ]]; then
        curl -fL https://statlas.prod.atl-paas.net/atlas-cli/darwin/atlas-latest-darwin-amd64.tar.gz | tar -xzp atlas
    else
        # assume linux otherwise
        curl -fL https://statlas.prod.atl-paas.net/atlas-cli/linux/atlas-latest-linux-amd64.tar.gz | tar -xzp atlas
    fi
    chmod +x atlas
    mv atlas /usr/local/bin/
fi
