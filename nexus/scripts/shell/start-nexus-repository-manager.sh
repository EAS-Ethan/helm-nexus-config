cd /opt/sonatype/nexus
exec ./bin/nexus run & \
    sh /config/healthcheck.sh && \
    /config/provision.sh

tail -f /dev/null