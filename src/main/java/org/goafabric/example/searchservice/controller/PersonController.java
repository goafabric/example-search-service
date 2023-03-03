package org.goafabric.example.searchservice.controller;

import org.goafabric.example.searchservice.controller.dto.Person;
import org.goafabric.example.searchservice.logic.PersonLogic;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/persons",
        produces = "application/json")

@RestController
public class PersonController {
    private final PersonLogic personLogic;

    public PersonController(PersonLogic personLogic) {
        this.personLogic = personLogic;
    }

    @GetMapping("getById/{id}")
    @QueryMapping("getById")
    public Person getById(@PathVariable("id") @Argument String id) {
        return personLogic.getById(id);
    }

    @GetMapping("findAll")
    @QueryMapping("findAll")
    public List<Person> findAll() {
        List<Person> persons = personLogic.findAll();
        return persons;
    }

    @GetMapping("findByFirstName")
    @QueryMapping("findByFirstName")
    public List<Person> findByFirstName(@RequestParam("firstName") @Argument("firstName") String firstName) {
        return personLogic.findByFirstName(firstName);
    }

    @GetMapping("findByLastName")
    @QueryMapping("findByLastName")
    public List<Person> findByLastName(@RequestParam("lastName") @Argument("lastName") String lastName) {
        return personLogic.findByLastName(lastName);
    }

    @GetMapping("findByCity")
    public List<Person> findByCity(@RequestParam("city") @Argument("city") String city) {
        return personLogic.findByCity(city);
    }

    @PostMapping(value = "save", consumes = "application/json")
    @MutationMapping("save")
    public Person save(@RequestBody @Argument Person person) {
        return personLogic.save(person);
    }

}
