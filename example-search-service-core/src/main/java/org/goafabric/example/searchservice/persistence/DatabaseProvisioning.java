package org.goafabric.example.searchservice.persistence;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.example.searchservice.crossfunctional.HttpInterceptor;
import org.goafabric.example.searchservice.logic.PersonLogic;
import org.goafabric.example.searchservice.service.Address;
import org.goafabric.example.searchservice.service.Person;
import org.goafabric.example.searchservice.service.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
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
        }

        if (goals.contains("-terminate")) {
            log.info("Terminating app ...");
            SpringApplication.exit(applicationContext, () -> 0); //if an exception is raised, spring will automatically terminate with 1
        }
    }

    private void importDemoData() {
        personLogic.deleteAll();
        if (!personLogic.findAll().iterator().hasNext()) {
            createDemoData("0");
            createDemoData("5a2f");
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
