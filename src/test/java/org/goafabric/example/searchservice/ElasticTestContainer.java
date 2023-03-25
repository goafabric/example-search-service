package org.goafabric.example.searchservice;

import org.testcontainers.elasticsearch.ElasticsearchContainer;

public class ElasticTestContainer {
    public static void get() {
        var container = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:8.6.1")
                .withExposedPorts(9200, 9300);
        container.addEnv("ES_JAVA_OPTS","-Xms256m -Xmx256m");
        container.addEnv("discovery.type","single-node");
        container.addEnv("xpack.security.enabled","false");
        container.addEnv("xpack.security.transport.ssl.enabled","false");
        container.addEnv("xpack.security.http.ssl.enabled","false");
        container.start();
        System.setProperty("spring.elasticsearch.uris", "http://localhost:" + container.getMappedPort(9200));
    }
}
