package org.goafabric.example.searchservice.crossfunctional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mongodb")
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchClientAutoConfiguration.class, org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration.class, org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration.class})
public class MongoConfiguration {
}
