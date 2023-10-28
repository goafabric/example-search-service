package org.goafabric.example.searchservice.logic;

import org.goafabric.example.searchservice.controller.dto.Person;
import org.goafabric.example.searchservice.persistence.PersonRepository;
import org.goafabric.example.searchservice.persistence.entity.PersonEo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PersonLogic {
    private final PersonMapper personMapper;

    private final PersonRepository personRepository;

    public PersonLogic(PersonMapper personMapper, PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

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
        List<PersonEo> persons = personRepository.findByFirstName(firstName);
        //List<SkillBo> skills = persons.get(0).getSkills();
        return personMapper.map(persons);
    }

    public List<Person> findByLastName(String lastName) {
        return personMapper.map(
                personRepository.findByLastName(lastName));
    }

    public List<Person> findByCity(String city) {
        return personMapper.map(
                personRepository.findByAddress_City(city));
    }

    public List<Person> findByStreet(String street) {
        return personMapper.map(
                personRepository.findByAddress_StreetContainsIgnoreCase(street));
    }

    public Person save(Person person) {
        final PersonEo personEo = personMapper.map(person);
        return personMapper.map(
                personRepository.save(
                        personEo));
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

}
