package org.goafabric.example.searchservice.lucene;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RuleTest {

    record BillingFile(String id) {}

    private static class ADTProcessor {
        private final Logger log = LoggerFactory.getLogger(this.getClass());

        public BillingFile create() {
            return new BillingFile("42");
        }

        public BillingFile validate(BillingFile billingFile) {
            log.info("validating file with id: " + billingFile.id());
            if (!billingFile.id().equals("42")) {
                throw new IllegalStateException("Validation failed");
            }
            return billingFile;
        }

        public BillingFile encrypt(BillingFile billingFile) {
            log.info("encrypting file with id: " + billingFile.id());
            return new BillingFile(Base64.getEncoder().encodeToString(billingFile.id().getBytes(StandardCharsets.UTF_8)));
        }

        public void send(BillingFile billingFile) {
            log.info("sending file with id: " + billingFile.id());
        }

    }

    @Test
    public void testOldSchool() {
        var processor = new ADTProcessor();

        var billingFile = processor.create();
        billingFile = processor.validate(billingFile);

        billingFile = processor.encrypt(billingFile);

        processor.send(billingFile);
    }

    @Test
    public void testNested() {
        var processor = new ADTProcessor();

        processor.send(
            processor.encrypt(
                processor.validate(
                        processor.create())));
    }


    private static class FluentADTProcessor {
        private final Logger log = LoggerFactory.getLogger(this.getClass());
        private BillingFile billingFile;

        public static FluentADTProcessor getInstance() {
            return new FluentADTProcessor();
        }

        public FluentADTProcessor create(String id) {
            this.billingFile = new BillingFile(id);
            return this;
        }

        public FluentADTProcessor validate() {
            log.info("validating file with id: " + billingFile.id());
            if (!billingFile.id().equals("42")) {
                throw new IllegalStateException("Validation failed");
            }
            return this;
        }

        public FluentADTProcessor encrypt() {
            log.info("encrypting file with id: " + billingFile.id());
            billingFile = new BillingFile(Base64.getEncoder().encodeToString(billingFile.id().getBytes(StandardCharsets.UTF_8)));
            return this;
        }

        public FluentADTProcessor send() {
            log.info("sending file with id: " + billingFile.id());
            return this;
        }

    }

    @Test
    public void testFluentAPI() {
        FluentADTProcessor
                .getInstance()
                .create("42")
                .validate()
                .encrypt()
                .send();
    }
    
}
