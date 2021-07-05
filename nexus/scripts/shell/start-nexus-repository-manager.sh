cd /opt/sonatype/nexus
exec dnf install unzip
exec ./bin/nexus run & \
    sh /config/shell/healthcheck.sh && \
    /config/shell/provision.sh

tail -f /dev/null