package org.goafabric.example.searchservice.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends PagingAndSortingRepository<PersonBo, String> {
    List<PersonBo> findByFirstName(String firstName);
    List<PersonBo> findByLastName(String lastName);

    List<PersonBo> findByLastNameStartsWithIgnoreCase(String lastName);
    List<PersonBo> findByAddress_City(@Param("city") String city);
}

