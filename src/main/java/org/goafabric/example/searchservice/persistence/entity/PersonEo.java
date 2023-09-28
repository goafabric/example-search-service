package org.goafabric.example.searchservice.persistence.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@org.springframework.data.mongodb.core.mapping.Document("#{@tenantIdBean.getPrefix()}person")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "#{@tenantIdBean.getPrefix()}person", createIndex = false)
@RedisHash("#{@tenantIdBean.getPrefix()}address")
public record PersonEo(
    @Id
    String id,
    @Indexed
    String firstName,
    @Indexed
    String lastName,
    List<AddressEo> address,
    List<SkillEo> skills
) {}
