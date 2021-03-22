package org.goafabric.example.searchservice.logic;

import org.goafabric.example.searchservice.mapper.PersonMapper;
import org.goafabric.example.searchservice.persistence.PersonRepository;
import org.goafabric.example.searchservice.service.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonLogic {
    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    public Person getById(String id) {
        return personMapper.map(
                personRepository.findById(id).get());
    }

    public List<Person> findAll() {
        return null;
        //List<Person> x = personRepository.findAll().stream().map(personMapper::map).collect(Collectors.toList());
        //return personMapper.map(
          //      personRepository.findAll());
    }

    public List<Person> findByFirstName(String firstName) {
        return personMapper.map(
                personRepository.findByFirstName(firstName));
    }

    public List<Person> findByLastName(String lastName) {
        return personMapper.map(
                personRepository.findByLastName(lastName));
    }

    public Person save(Person person) {
        return personMapper.map(
                personRepository.save(
                        personMapper.map(person)));
    }

}
