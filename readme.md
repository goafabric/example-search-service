#docker run
[[ "$(uname -m)" == "arm64"  ]] && ARCH="-arm64v8" && \
docker pull goafabric/example-search-service${ARCH}:1.0.3-SNAPSHOT && \
docker run --name example-search-service --rm -p50800:50800 -e "elasticsearch.url=172.17.0.1:9200" goafabric/example-search-service${ARCH}:1.0.3-SNAPSHOT

#docker run native
docker pull goafabric/example-search-service-native:1.0.3-SNAPSHOT && \
docker run --name example-search-service --rm -p50800:50800 -e "elasticsearch.url=172.17.0.1:9200" goafabric/example-search-service-native:1.0.3-SNAPSHOT -Xmx64m

#elastic
docker run -d --name elasticsearch-search --rm -p 9200:9200  -e "discovery.type=single-node" -e "ES_JAVA_OPTS=-Xmx256m" docker.elastic.co/elasticsearch/elasticsearch:7.15.1
                    
#mongodb
docker run -d --name mongodb --rm -p27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongodb -e MONGO_INITDB_ROOT_PASSWORD=mongodb mongo:4.4.5
