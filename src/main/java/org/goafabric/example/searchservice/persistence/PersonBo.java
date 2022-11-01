package org.goafabric.example.searchservice.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document("#{@tenantIdBean.getPrefix()}person")
//@org.springframework.data.elasticsearch.annotations.Document(indexName = "#{@tenantIdBean.getPrefix()}person")
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
