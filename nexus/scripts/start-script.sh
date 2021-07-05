cd /opt/sonatype/nexus
echo "lol"
# we might have to put some logic here to wait
# for nexus container to start and create filesystem

exec ./bin/nexus run & \
    sh /scripts/healthcheck.sh && \
    /scripts/provision.sh

tail -f /dev/null