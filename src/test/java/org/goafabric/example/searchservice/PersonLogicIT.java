package org.goafabric.example.searchservice;

import org.goafabric.example.searchservice.logic.PersonLogic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(TestContainerExtension.class)
class PersonLogicIT {
    @Autowired
    private PersonLogic personLogic;


    @Test
    public void findAll() {
        assertThat(personLogic.findAll()).isNotNull().hasSize(3);
    }
}