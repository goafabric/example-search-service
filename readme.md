# docker compose
go to /src/deploy/docker and do "./stack up"
                                                       
databases and application are seperated

# mongodb
docker run --name mongodb --rm -p27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongodb -e MONGO_INITDB_ROOT_PASSWORD=mongodb mongo:6.0.1

# run jvm multi image
docker run --pull always --pull always --name example-search-service --rm -p50800:50800 -e spring.data.mongodb.host=host.docker.internal goafabric/example-search-service:3.0.1-SNAPSHOT

# run native image
docker run --pull always --name example-search-service-native --rm -p50800:50800 -e spring.data.mongodb.host=host.docker.internal goafabric/example-search-service-native:3.0.1-SNAPSHOT -Xmx32m

# run native image arm
docker run --pull always --name example-search-service-native-arm64v8 --rm -p50800:50800 -e spring.data.mongodb.host=host.docker.internal goafabric/example-search-service-native-arm64v8:3.0.1-SNAPSHOT -Xmx32m
