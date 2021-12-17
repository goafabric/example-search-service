package org.goafabric.example.searchservice.service;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.example.searchservice.logic.PersonLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/persons",
        produces = "application/json")

@RestController
@Slf4j
public class PersonService {
    @Autowired
    private PersonLogic personLogic;

    @GetMapping("")
    public String welcome () {
        return "welcome";
    }

    @GetMapping("getById/{id}")
    public Person getById(@PathVariable("id") String id) {
        return personLogic.getById(id);
    }

    @GetMapping("findAll")
    public List<Person> findAll() {
        List<Person> persons = personLogic.findAll();
        return persons;
    }

    @GetMapping("findByFirstName")
    public List<Person> findByFirstName(@RequestParam("firstName") String firstName) {
        return personLogic.findByFirstName(firstName);
    }

    @GetMapping("findByLastName")
    public List<Person> findByLastName(@RequestParam("lastName") String lastName) {
        return personLogic.findByLastName(lastName);
    }


    @PostMapping(value = "save", consumes = "application/json")
    public Person save(@RequestBody Person person) {
        return personLogic.save(person);
    }

}
