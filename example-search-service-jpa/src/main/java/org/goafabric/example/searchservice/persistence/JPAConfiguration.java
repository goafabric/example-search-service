package org.goafabric.example.searchservice.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.goafabric.example.searchservice.persistence")
public class JPAConfiguration {
}
