package org.goafabric.example.searchservice.crossfunctional;


import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableElasticsearchRepositories(basePackages = "org.goafabric.example.searchservice.persistence")
public class ElasticClientConfig {
    @Value("${elasticsearch.url}")
    private String elasticSearchUrl;

    /*
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
       return new ElasticsearchRestTemplate(client());
    }
     */

    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration
            = ClientConfiguration.builder()
            .connectedTo(elasticSearchUrl)
            .build();
        log.info("Elasticsearch has started.");
        return RestClients.create(clientConfiguration).rest();
    }


}
