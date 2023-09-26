package org.goafabric.example.searchservice.persistence.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@org.springframework.data.mongodb.core.mapping.Document("#{@tenantIdBean.getPrefix()}person")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "#{@tenantIdBean.getPrefix()}person", createIndex = false)
@RedisHash("address")
public record PersonBo(
    @Id
    String id,
    String firstName,
    String lastName,
    AddressBo address,
    List<SkillBo> skills
) {}
