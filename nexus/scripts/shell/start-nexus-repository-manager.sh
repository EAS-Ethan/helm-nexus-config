#!/bin/bash
cd /opt/sonatype/nexus
echo "nexus.scripts.allowCreation=true" >> /nexus-data/etc/nexus.properties


exec yes | dnf install unzip
exec yes | dnf install zip
exec bash /config/shell/install-groovy.sh &
exec ./bin/nexus run & \
    sh /config/shell/healthcheck.sh && \
    /config/shell/provision.sh

tail -f /dev/null