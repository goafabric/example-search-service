package org.goafabric.example.searchservice.persistence;

import lombok.extern.slf4j.Slf4j;
import org.goafabric.example.searchservice.crossfunctional.TenantIdInterceptor;
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
    PersonRepository personRepository;

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
        personRepository.save(PersonBo.builder()
                .firstName("Homer").lastName("Simpson")
                .build());

        personRepository.save(PersonBo.builder()
                .firstName("Bart").lastName("Simpson")
                .build());

        personRepository.save(PersonBo.builder()
                .firstName("Monty").lastName("Burns")
                .build());
    }
}
