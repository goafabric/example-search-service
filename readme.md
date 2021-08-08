#docker run
[[ "$(uname -m)" == "arm64"  ]] && ARCH="-arm64v8" && \
docker pull goafabric/example-search-service${ARCH}:1.0.2-SNAPSHOT && \
docker run --name example-search-service --rm -p50800:50800 -e "elasticsearch.url=172.17.0.1:9200" goafabric/example-search-service${ARCH}:1.0.2-SNAPSHOT

#docker run native
docker pull goafabric/example-search-service-native:1.0.2-SNAPSHOT && \
docker run --name example-search-service --rm -p50800:50800 -e "elasticsearch.url=172.17.0.1:9200" goafabric/example-search-service-native:1.0.2-SNAPSHOT -Xmx64m

#elastic
docker run -d --name elasticsearch-search --rm -p 9200:9200  -e "discovery.type=single-node" -e "ES_JAVA_OPTS=-Xmx256m" docker.elastic.co/elasticsearch/elasticsearch:7.14.0
