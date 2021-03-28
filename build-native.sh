#!/bin/bash
git pull
time mvn clean spring-boot:build-image
#time mvn clean spring-boot:build-image install
docker run --name example-search-service --rm -p50800:50800 -e "elasticsearch.url=172.17.0.1:9200" goafabric/example-search-service-native:1.0.1-SNAPSHOT -Xmx64m
