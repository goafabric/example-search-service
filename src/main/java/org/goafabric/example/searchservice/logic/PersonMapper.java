package org.goafabric.example.searchservice.logic;

import org.goafabric.example.searchservice.controller.dto.Person;
import org.goafabric.example.searchservice.persistence.entity.PersonEo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person map(PersonEo person);

    PersonEo map(Person person);

    List<Person> map(List<PersonEo> persons);

}
