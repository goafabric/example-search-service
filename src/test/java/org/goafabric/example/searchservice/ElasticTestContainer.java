package org.goafabric.example.searchservice;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.core.io.ClassPathResource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.org.yaml.snakeyaml.Yaml;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

public class ElasticTestContainer implements BeforeAllCallback {

    private static final GenericContainer container;

    static {
        container = new GenericContainer(
                DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch").withTag("8.6.1"))
                .withExposedPorts(9200, 9300)
                .withEnv(new HashMap<>() {{
                    put("ES_JAVA_OPTS", "-Xms256m -Xmx256m");
                    put("discovery.type", "single-node");
                    put("xpack.security.enabled", "false");
                    put("xpack.security.transport.ssl.enabled", "false");
                    put("xpack.security.http.ssl.enabled", "false");
                }});
        container.start();
        System.setProperty("spring.elasticsearch.uris", "http://localhost:" + container.getMappedPort(9200));
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Map<String, Object> props = new Yaml().load(new ClassPathResource("application.yml").getInputStream());
        var x = props.get("spring.autoconfigure.exclude");
    }

}


// https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.1.0-M2-Release-Notes
//https://www.baeldung.com/spring-dynamicpropertysource
