package org.goafabric.example.searchservice.persistence;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.example.searchservice.controller.dto.Address;
import org.goafabric.example.searchservice.controller.dto.Person;
import org.goafabric.example.searchservice.controller.dto.Skill;
import org.goafabric.example.searchservice.crossfunctional.HttpInterceptor;
import org.goafabric.example.searchservice.logic.PersonLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class DatabaseProvisioning {
    @Value("${database.provisioning.goals:}")
    String goals;

    @Autowired
    PersonLogic personLogic;

    @Autowired
    ApplicationContext applicationContext;

    public void run() {
        if (goals.contains("-import-demo-data")) {
            log.info("Importing demo data ...");
            importDemoData();
            log.info("Demo data import done ...");
        }

        if (goals.contains("-terminate")) {
            log.info("Terminating app ...");
            SpringApplication.exit(applicationContext, () -> 0); //if an exception is raised, spring will automatically terminate with 1
        }
    }

    private void importDemoData() {
        HttpInterceptor.setTenantId("0");
        boolean dataExists = false;
        try {
            dataExists = personLogic.findAll().iterator().hasNext();
        }
        catch (DataAccessException e) {} //happens on first elastic run

        if (!dataExists) {
            createDemoData("0");
            createDemoData("5a2f");
        } else {
            log.info("Demo data already exists, skipping import ...");
        }
    }

    private void createDemoData(String tenantId) {
        HttpInterceptor.setTenantId(tenantId);
        personLogic.save(Person.builder()
                .firstName("Homer").lastName("Simpson")
                .address(createAddress("Evergreen Terace 1"))
                .skills(createSkills())
                .build());

        personLogic.save(Person.builder()
                .firstName("Bart").lastName("Simpson")
                .address(createAddress("Everblue Terace 1"))
                .skills(createSkills())
                .build());

        personLogic.save(Person.builder()
                .firstName("Monty").lastName("Burns")
                .address(createAddress("Monty Mansion"))
                .skills(createSkills())
                .build());
    }

    private Address createAddress(String street) {
        return Address.builder()
                .street(street).city("Springfield")
                .build();
    }

    private List<Skill> createSkills() {
        return Arrays.asList(
                Skill.builder()
                        .name("java")
                        .description("functional")
                        .build(),
                Skill.builder()
                        .name("go")
                        .description("gopher")
                        .build());
    }

}
