package org.goafabric.example.searchservice.persistence.mongo;


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
@Document("#{@tenantIdBean.getPrefix()}skill")
public class SkillBo {
    @Id
    private String id;

    private String name;
    private String description;
}
