#!/bin/sh

set -e
set -x

npm install -g npm-cli-adduser
rm -f package-lock.json
npm-cli-adduser --registry=http://nexus.widas.de/repository/npm-all/ --username $NPM_USERNAME --password $NPM_PASSWORD --email $NPM_EMAIL
npm-cli-adduser --registry=http://nexus.widas.de/repository/npm-internal/ --username $NPM_USERNAME --password $NPM_PASSWORD --email $NPM_EMAIL
echo "registry=$CIM_NPM_REGISTRY" >> .npmrc
