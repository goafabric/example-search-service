package org.goafabric.example.searchservice.persistence.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("#{@tenantIdBean.getPrefix()}address")
public class AddressBo {
    @Id
    private String id;

    private String street;
    private String city;
}
