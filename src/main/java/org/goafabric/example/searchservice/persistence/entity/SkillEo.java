package org.goafabric.example.searchservice.persistence.entity;


import org.springframework.data.annotation.Id;

//@org.springframework.data.mongodb.core.mapping.Document("#{@tenantIdBean.getPrefix()}skill")
//@org.springframework.data.elasticsearch.annotations.Document(indexName = "#{@tenantIdBean.getPrefix()}skill", createIndex = false)
public record SkillEo(
    @Id
    String id,
    String name,
    String description
) {}
