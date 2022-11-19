# docker compose
go to /src/deploy/docker and do "./stack up"

# run jvm multi image
docker pull goafabric/example-search-service:3.0.0-RC3 && docker run --name example-search-service --rm -p50900:50900 goafabric/example-search-service:3.0.0-RC3

# run native image
docker pull goafabric/example-search-service-native:3.0.0-RC3 && docker run --name example-search-service-native --rm -p50900:50900 goafabric/example-search-service-native:3.0.0-RC3 -Xmx32m

# run native image arm
docker pull goafabric/example-search-service-native-arm64v8:3.0.0-RC3 && docker run --name example-search-service-native-arm64v8 --rm -p50900:50900 goafabric/example-search-service-native-arm64v8:3.0.0-RC3 -Xmx32m
