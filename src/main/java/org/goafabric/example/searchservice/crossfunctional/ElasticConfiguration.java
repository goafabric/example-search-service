package org.goafabric.example.searchservice.crossfunctional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("elasticsearch")
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration.class, org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration.class, org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration.class})
public class ElasticConfiguration {
}
