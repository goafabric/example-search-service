package org.goafabric.example.searchservice.persistence.elastic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "#{@tenantIdBean.getPrefix()}person")
public class PersonBo {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Field(type = FieldType.Nested, includeInParent = true)
    private AddressBo address;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<SkillBo> skills;
}
