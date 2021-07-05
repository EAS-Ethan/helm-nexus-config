#!/bin/bash


exec cd /opt/sonatype/nexus & \
    curl -s get.sdkman.io | bash &&  \
    chmod a+x "/root/.sdkman/bin/sdkman-init.sh"  && \
    /bin/bash -c source "/root/.sdkman/bin/sdkman-init.sh"; sdk install groovy