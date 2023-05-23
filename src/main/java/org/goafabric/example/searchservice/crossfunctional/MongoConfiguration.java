package org.goafabric.example.searchservice.crossfunctional;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import static java.util.Collections.singletonList;

@Configuration
@Profile("mongodb")
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchClientAutoConfiguration.class, org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration.class, org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration.class})
public class MongoConfiguration {

    @Configuration
    @ConditionalOnMissingClass("de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration")
    static class MongoClientConfig extends AbstractMongoClientConfiguration {
        @Value("${spring.data.mongodb.authentication-database}") private String authDb;
        @Value("${spring.data.mongodb.authentication-database}") private String db;
        @Value("${spring.data.mongodb.username}") private String user;
        @Value("${spring.data.mongodb.password}") private String password;
        @Value("${spring.data.mongodb.host:localhost}") private String host;
        @Value("${spring.data.mongodb.port:27017}") private Integer port;

        @Override
        protected String getDatabaseName() {
            return db;
        }

        @Override
        protected void configureClientSettings(MongoClientSettings.Builder builder) {
            builder.credential(MongoCredential.createCredential(user, authDb, password.toCharArray()))
                    .applyToClusterSettings(settings -> {
                        settings.hosts(singletonList(new ServerAddress(host, port)));
                    });
        }
    }
}
