package org.goafabric.example.searchservice.persistence.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("#{@tenantIdBean.getPrefix()}person")
public class PersonBo {
    @Id
    private String id;

    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

    private AddressBo address;

    //@DBRef
    private List<SkillBo> skills;
}
