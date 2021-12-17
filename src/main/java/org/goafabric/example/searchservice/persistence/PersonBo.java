package org.goafabric.example.searchservice.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Document(indexName = "#{@tenantIdBean.getPrefix()}persons")
@Document("#{@tenantIdBean.getPrefix()}persons")
public class PersonBo {
    @Id
    private String id;

    //@Field(type = FieldType.Text)
    private String firstName;

    //@Field(type = FieldType.Text)
    private String lastName;
}
