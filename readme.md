# docker compose
go to /src/deploy/docker and do "./stack up"

# run jvm multi image
docker run --pull always --pull always --name example-search-service --rm -p50800:50800 -e spring.data.mongodb.host=host.docker.internal goafabric/example-search-service:3.0.2-SNAPSHOT

# run native image
docker run --pull always --name example-search-service-native --rm -p50800:50800 -e spring.data.mongodb.host=host.docker.internal goafabric/example-search-service-native:3.0.2-SNAPSHOT -Xmx32m

# run native image arm
docker run --pull always --name example-search-service-native --rm -p50800:50800 -e spring.data.mongodb.host=host.docker.internal goafabric/example-search-service-native-arm64v8:3.0.2-SNAPSHOT -Xmx32m

# mongodb
docker run --name mongodb --rm -p27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongodb -e MONGO_INITDB_ROOT_PASSWORD=mongodb mongo:6.0.1

# elasticsearch
docker run --name elasticsearch-search --rm -p 9200:9200  -e "discovery.type=single-node" -e "xpack.security.enabled=false" -e "ES_JAVA_OPTS=-Xmx256m" docker.elastic.co/elasticsearch/elasticsearch:8.4.3

