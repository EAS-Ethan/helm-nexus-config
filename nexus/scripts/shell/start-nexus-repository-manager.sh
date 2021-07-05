cd /opt/sonatype/nexus
exec sh /config/shell/install-groovy.sh && \ 
    ./bin/nexus run & \
    sh /config/shell/healthcheck.sh && \
    /config/shell/provision.sh

tail -f /dev/null