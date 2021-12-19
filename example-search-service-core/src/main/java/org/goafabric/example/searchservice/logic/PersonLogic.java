package org.goafabric.example.searchservice.logic;

import org.goafabric.example.searchservice.persistence.PersonRepository;
import org.goafabric.example.searchservice.service.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        return personMapper.map(
                StreamSupport.stream(personRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList()));
    }

    public List<Person> findByFirstName(String firstName) {
        return personMapper.map(
                personRepository.findByFirstName(firstName));
    }

    public List<Person> findByLastName(String lastName) {
        return personMapper.map(
                personRepository.findByLastName(lastName));
    }

    public List<Person> findByCity(String city) {
        return personMapper.map(
                personRepository.findByAddress_City(city));
    }

    public Person save(Person person) {
        return personMapper.map(
                personRepository.save(
                        personMapper.map(person)));
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

}
