#docker compose
Starting the docker-compose inside -core/src/deploy/docker via ./stack up
will give you all databases needed

#docker run
[[ "$(uname -m)" == "arm64"  ]] && ARCH="-arm64v8" && \
docker pull goafabric/example-search-service${ARCH}:2.0.0 && \
docker run --name example-search-service --rm -p50800:50800 -e "elasticsearch.url=172.17.0.1:9200" goafabric/example-search-service${ARCH}:2.0.0

#docker run native
docker pull goafabric/example-search-service-native:2.0.0 && \
docker run --name example-search-service --rm -p50800:50800 -e "elasticsearch.url=172.17.0.1:9200" goafabric/example-search-service-native:2.0.0 -Xmx64m

#elastic
docker run -d --name elasticsearch-search --rm -p 9200:9200  -e "discovery.type=single-node" -e "ES_JAVA_OPTS=-Xmx256m" docker.elastic.co/elasticsearch/elasticsearch:7.15.1
                    
#mongodb
docker run -d --name mongodb --rm -p27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongodb -e MONGO_INITDB_ROOT_PASSWORD=mongodb \
mongo:4.4.5

                         
#mongoexpress
docker run  --rm --name mongoexpress -e ME_CONFIG_MONGODB_SERVER=mongodb:mongodb@192.168.4.23 -p 8081:8081 mongo-express

#pgadmin
docker run --rm --name pgadmin -p 8081:80 -v pgadmin_data:/var/lib/pgadmin -e 'PGADMIN_DEFAULT_EMAIL=admin@admin.org'  -e 'PGADMIN_DEFAULT_PASSWORD=admin' dpage/pgadmin4