#!/bin/bash


exec cd /opt/sonatype/nexus & \
    curl -s "https://get.sdkman.io" | sh &&  \
    chmod a+x "/root/.sdkman/bin/sdkman-init.sh/root/.sdkman/bin/sdkman-init.sh"  && \
    source "/root/.sdkman/bin/sdkman-init.sh" && \
    sdk install groovy