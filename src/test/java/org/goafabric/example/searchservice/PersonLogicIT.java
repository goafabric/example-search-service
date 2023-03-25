package org.goafabric.example.searchservice;

import org.goafabric.example.searchservice.logic.PersonLogic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonLogicIT {
    @Autowired
    private PersonLogic personLogic;

    @BeforeAll
    public static void init() {
        ElasticTestContainer.get();
    }

    @Test
    public void findAll() {
        personLogic.findAll();
    }
}