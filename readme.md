#docker run
docker pull goafabric/example-search-service-arm64v8:1.0.1-SNAPSHOT && \
docker run --name example-search-service --rm -p50800:50800 -e "elasticsearch.url=host.docker.internal:9200" goafabric/example-search-service-arm64v8:1.0.1-SNAPSHOT

#docker run native
docker pull goafabric/example-search-service-native:1.0.1-SNAPSHOT && \
docker run --name example-search-service --rm -p50800:50800 -e "elasticsearch.url=host.docker.internal:9200" goafabric/example-search-service-native:1.0.1-SNAPSHOT -Xmx64m
