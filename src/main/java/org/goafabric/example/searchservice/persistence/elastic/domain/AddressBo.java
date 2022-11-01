package org.goafabric.example.searchservice.persistence.elastic.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@Document(indexName = "#{@tenantIdBean.getPrefix()}address")
public class AddressBo {
    @Id
    private String id;

    private String street;
    private String city;
}
