package org.goafabric.example.searchservice.persistence;

import org.goafabric.example.searchservice.persistence.entity.PersonBo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonBo, String> {
    List<PersonBo> findByFirstName(String firstName);
    List<PersonBo> findByLastName(String lastName);

    List<PersonBo> findByLastNameStartsWithIgnoreCase(String lastName);
    List<PersonBo> findByAddress_City(@Param("city") String city);

    List<PersonBo> findByAddress_StreetContainsIgnoreCase(String street);
}

