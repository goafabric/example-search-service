package org.goafabric.example.searchservice.persistence;

import org.goafabric.example.searchservice.persistence.entity.PersonEo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEo, String> {
    List<PersonEo> findByFirstName(String firstName);
    List<PersonEo> findByLastName(String lastName);

    List<PersonEo> findByLastNameStartsWithIgnoreCase(String lastName);
    List<PersonEo> findByAddress_City(@Param("city") String city);

    List<PersonEo> findByAddress_StreetContainsIgnoreCase(String street);
}

