#docker run
docker pull goafabric/example-search-service-arm64v8:1.0.0-SNAPSHOT 
docker run --name spring-boot-example-service --rm -p50800:50800 -e "elasticsearch.url=host.docker.internal:9200" goafabric/example-search-service-arm64v8:1.0.0-SNAPSHOT
