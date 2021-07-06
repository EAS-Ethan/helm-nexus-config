#!/bin/bash


exec yes | dnf install unzip
exec yes | dnf install zip
exec echo "sdkman_auto_answer=true" > ~/.sdkman/etc/config
exec bash /config/shell/install-groovy.sh && \
    ./bin/nexus run & \
    sh /config/shell/healthcheck.sh && \
    /config/shell/provision.sh

tail -f /dev/null