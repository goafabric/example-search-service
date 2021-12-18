package org.goafabric.example.searchservice.persistence.transformer;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.example.searchservice.crossfunctional.TenantIdInterceptor;
import org.goafabric.example.searchservice.logic.PersonLogic;
import org.goafabric.example.searchservice.service.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DatabaseProvisioning {
    @Value("${database.provisioning.goals:}")
    String goals;

    @Autowired
    PersonLogic personRepository;

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
        if (!personRepository.findAll().iterator().hasNext()) {
            createDemoData("0");
            createDemoData("5a2f");
        }
    }

    private void createDemoData(String tenantId) {
        TenantIdInterceptor.setTenantId(tenantId);
        personRepository.save(Person.builder()
                .firstName("Homer").lastName("Simpson")
                .build());

        personRepository.save(Person.builder()
                .firstName("Bart").lastName("Simpson")
                .build());

        personRepository.save(Person.builder()
                .firstName("Monty").lastName("Burns")
                .build());
    }
}
