package org.goafabric.example.searchservice;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.core.io.ClassPathResource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.org.yaml.snakeyaml.Yaml;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestContainerExtension implements BeforeAllCallback {

    static {
        if (getAppConfiguration().get("spring.profiles.active").equals("mongodb")) {
            createMongoContainer();
        } else {
            createElasticContainer();
        }
    }

    private static GenericContainer createElasticContainer() {
        var container =  new GenericContainer(
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
        return container;
    }

    private static GenericContainer createMongoContainer() {
        var container = new GenericContainer(
                DockerImageName.parse("mongo").withTag("6.0.4"))
                .withExposedPorts(27017)
                .withEnv(new HashMap<>() {{
                    put("MONGO_INITDB_ROOT_USERNAME", "mongodb");
                    put("MONGO_INITDB_ROOT_PASSWORD", "mongodb");
                }});

        container.start();
        System.setProperty("spring.data.mongodb.port", String.valueOf(container.getMappedPort(27017)));
        return container;
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
    }

    private static Map<String, Object> getAppConfiguration() {
        try {
            return new Yaml().load(new ClassPathResource("application.yml").getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


// https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.1.0-M2-Release-Notes
//https://www.baeldung.com/spring-dynamicpropertysource
