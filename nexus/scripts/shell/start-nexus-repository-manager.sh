#!/bin/bash

exec yes | dnf install unzip
exec yes | dnf install zip


exec sh /config/shell/install-groovy.sh && \
    source "/root/.sdkman/bin/sdkman-init.sh" && \
    sdk install groovy

exec ./bin/nexus run & \
    sh /config/shell/healthcheck.sh && \
    /config/shell/provision.sh

tail -f /dev/null