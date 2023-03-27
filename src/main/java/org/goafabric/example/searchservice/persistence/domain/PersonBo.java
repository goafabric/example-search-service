package org.goafabric.example.searchservice.persistence.domain;


import org.springframework.data.annotation.Id;

import java.util.List;

@org.springframework.data.mongodb.core.mapping.Document("#{@tenantIdBean.getPrefix()}person")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "#{@tenantIdBean.getPrefix()}person", createIndex = false)
public record PersonBo(
    @Id
    String id,
    String firstName,
    String lastName,
    AddressBo address,
    List<SkillBo> skills
) {}
