cd /opt/sonatype/nexus
exec yes | dnf update bash
exec yes | dnf install unzip
exec yes | dnf install zip
exec sh /config/shell/install-groovy.sh && \
    sdk install groovy && \
    ./bin/nexus run & \
    sh /config/shell/healthcheck.sh && \
    /config/shell/provision.sh

tail -f /dev/null