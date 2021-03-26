#docker run
docker pull goafabric/example-search-service-arm64v8:1.0.1-SNAPSHOT && \
docker run --name example-search-service --rm -p50800:50800 -e "elasticsearch.url=172.17.0.1:9200" goafabric/example-search-service-arm64v8:1.0.1-SNAPSHOT

#docker run native
#docker run --name elasticsearch-search -p 9200:9200  -e "discovery.type=single-node" -e "ES_JAVA_OPTS=-Xmx256m" docker.elastic.co/elasticsearch/elasticsearch:7.11.2
docker pull goafabric/example-search-service-native:1.0.1-SNAPSHOT && \
docker run --name example-search-service --rm -p50800:50800 -e "elasticsearch.url=172.17.0.1:9200" goafabric/example-search-service-native:1.0.1-SNAPSHOT -Xmx64m
