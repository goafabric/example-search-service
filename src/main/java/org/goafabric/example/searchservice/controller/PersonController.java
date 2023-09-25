package org.goafabric.example.searchservice.controller;

import org.goafabric.example.searchservice.controller.vo.Person;
import org.goafabric.example.searchservice.logic.PersonLogic;
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
    //@QueryMapping("getById")
    public Person getById(@PathVariable("id")  String id) {
        return personLogic.getById(id);
    }

    @GetMapping("findAll")
    //@QueryMapping("findAll")
    public List<Person> findAll() {
        List<Person> persons = personLogic.findAll();
        return persons;
    }

    @GetMapping("findByFirstName")
    //@QueryMapping("findByFirstName")
    public List<Person> findByFirstName(@RequestParam("firstName") String firstName) {
        return personLogic.findByFirstName(firstName);
    }

    @GetMapping("findByLastName")
    //@QueryMapping("findByLastName")
    public List<Person> findByLastName(@RequestParam("lastName") String lastName) {
        return personLogic.findByLastName(lastName);
    }

    @GetMapping("findByCity")
    public List<Person> findByCity(@RequestParam("city") String city) {
        return personLogic.findByCity(city);
    }

    @PostMapping(value = "save", consumes = "application/json")
    //@MutationMapping("save")
    public Person save(@RequestBody Person person) {
        return personLogic.save(person);
    }

}
