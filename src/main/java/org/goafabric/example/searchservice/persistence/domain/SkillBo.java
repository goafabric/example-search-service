package org.goafabric.example.searchservice.persistence.domain;


import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document("#{@tenantIdBean.getPrefix()}skill")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "#{@tenantIdBean.getPrefix()}skill", createIndex = false)
public record SkillBo(
    @Id
    String id,
    String name,
    String description
) {}
