#!/bin/bash


exec curl -s get.sdkman.io | bash &&  \
    chmod a+x "/root/.sdkman/bin/sdkman-init.sh"  && \
    source "/root/.sdkman/bin/sdkman-init.sh" && \
    echo "sdkman_auto_answer=true" > ~/.sdkman/etc/config && \
    sdk install java && \
    sdk install groovy 