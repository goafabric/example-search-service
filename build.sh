#!/bin/bash
git pull
time mvn clean spring-boot:build-image
docker push goafabric/example-search-service-native:1.0.1-SNAPSHOT
docker run --name example-search-service --rm -p50800:50800 goafabric/example-search-service-native:1.0.1-SNAPSHOT