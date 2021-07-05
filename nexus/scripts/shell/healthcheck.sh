#!/bin/bash

curl --silent --show-error \
  --retry 50 --retry-connrefused --retry-delay 6 \
  -4 \
  'http://localhost:8081'