package org.goafabric.example.searchservice.mapper;

import org.goafabric.example.searchservice.persistence.PersonBo;
import org.goafabric.example.searchservice.service.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person map(PersonBo person);

    PersonBo map(Person person);

    List<Person> map(List<PersonBo> countries);

}
