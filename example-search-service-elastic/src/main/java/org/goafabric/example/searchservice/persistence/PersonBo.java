package org.goafabric.example.searchservice.persistence;

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
@Document(indexName = "#{@tenantIdBean.getPrefix()}person")
public class PersonBo {
    @Id
    private String id;

    //@Field(type = FieldType.Text)
    private String firstName;

    //@Field(type = FieldType.Text)
    private String lastName;
}
