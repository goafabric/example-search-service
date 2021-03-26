package org.goafabric.example.searchservice.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "#{T(org.goafabric.example.searchservice.crossfunctional.TenantRequestContext).getPrefix()}persons")
//@Document(indexName = "persons")
public class PersonBo {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String firstName;

    @Field(type = FieldType.Text)
    private String lastName;

    //@Field(type = FieldType.Keyword)
    //private String userId;
}
