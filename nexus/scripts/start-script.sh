cd /opt/sonatype/nexus
exec ./bin/nexus run & \
    sh /scripts/healthcheck.sh && \
    /scripts/provision.sh

tail -f /dev/null