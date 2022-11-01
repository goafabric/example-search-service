package org.goafabric.example.searchservice.persistence.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document("#{@tenantIdBean.getPrefix()}address")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "#{@tenantIdBean.getPrefix()}address")
public class AddressBo {
    @Id
    private String id;

    private String street;
    private String city;
}
