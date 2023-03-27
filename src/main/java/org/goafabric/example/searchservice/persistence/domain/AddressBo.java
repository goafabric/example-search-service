package org.goafabric.example.searchservice.persistence.domain;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document("#{@tenantIdBean.getPrefix()}address")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "#{@tenantIdBean.getPrefix()}address", createIndex = false)
public record AddressBo(
        @Id
        String id,
        String street,
        String city
) {}

