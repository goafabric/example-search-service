package org.goafabric.example.searchservice;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import java.util.HashMap;

public class ElasticTestContainer implements BeforeAllCallback {

    private static final ElasticsearchContainer container =
            new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:8.6.1")
                    .withExposedPorts(9200, 9300)
                    .withEnv(new HashMap<>()  {{
                        put("ES_JAVA_OPTS", "-Xms256m -Xmx256m");
                        put("discovery.type", "single-node");
                        put("xpack.security.enabled", "false");
                        put("xpack.security.transport.ssl.enabled", "false");
                        put("xpack.security.http.ssl.enabled", "false");
                    }});
    
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (!container.isCreated()) {
            container.start();
            System.setProperty("spring.elasticsearch.uris", "http://localhost:" + container.getMappedPort(9200));
        }
    }
}


//https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.1.0-M2-Release-Notes
//https://www.baeldung.com/spring-dynamicpropertysource
