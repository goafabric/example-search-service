package org.goafabric.example.searchservice.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonBo, String> {
    List<PersonBo> findByFirstName(String firstName);

}

