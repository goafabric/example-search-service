package org.goafabric.example.searchservice.crossfunctional;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
@Profile("mongodb")
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchClientAutoConfiguration.class, org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration.class, org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration.class})
public class MongoConfiguration {

    @Configuration
    static class MongoClientConfig extends AbstractMongoClientConfiguration {
        @Value("${spring.data.mongodb.authentication-database}") private String authDb;
        @Value("${spring.data.mongodb.authentication-database}") private String db;
        @Value("${spring.data.mongodb.username}") private String user;
        @Value("${spring.data.mongodb.password}") private String password;

        @Override
        protected String getDatabaseName() {
            return db;
        }

        @Override
        protected void configureClientSettings(MongoClientSettings.Builder builder) {
            builder.credential(MongoCredential.createCredential(user, authDb, password.toCharArray()));
        }
    }

}
