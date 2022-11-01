package org.goafabric.example.searchservice.persistence.mongo;

import org.goafabric.example.searchservice.persistence.mongo.domain.PersonBo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonBo, String> {
    List<PersonBo> findByFirstName(String firstName);
    List<PersonBo> findByLastName(String lastName);

    List<PersonBo> findByLastNameStartsWithIgnoreCase(String lastName);
    List<PersonBo> findByAddress_City(@Param("city") String city);
}

