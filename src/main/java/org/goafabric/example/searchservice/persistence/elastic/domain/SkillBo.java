package org.goafabric.example.searchservice.persistence.elastic.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "#{@tenantIdBean.getPrefix()}skill")
public class SkillBo {
    @Id
    private String id;

    private String name;
    private String description;
}
